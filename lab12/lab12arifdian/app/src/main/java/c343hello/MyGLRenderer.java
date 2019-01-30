package c343hello;

import javax.microedition.khronos.opengles.GL10;
// GL10 is only needed for Android GLSurfaceView.Renderer callbacks, e.g. onSurfaceCreated() etc.

import javax.microedition.khronos.egl.EGLConfig;

import android.opengl.GLSurfaceView;
import android.opengl.GLES20;

import java.nio.FloatBuffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import android.util.Log;

/**
 * MyGLRenderer provides drawing instructions for a GLSurfaceView object. This class
 * must override the OpenGL ES drawing lifecycle methods:
 * <ul>
 *   <li>{@link android.opengl.GLSurfaceView.Renderer#onSurfaceCreated}</li>
 *   <li>{@link android.opengl.GLSurfaceView.Renderer#onDrawFrame}</li>
 *   <li>{@link android.opengl.GLSurfaceView.Renderer#onSurfaceChanged}</li>
 * </ul>
 */
public class MyGLRenderer implements GLSurfaceView.Renderer {

    // lab02template: 
    //   draw 10x10x10 axes at the coordinate system origin,
    //   including 1 unit "tick marks"
    //   we'll need 198 floating point values for vertices:
    private float[] myAxesData = new float[198];
        // vertex data structured thus:
        //   positionX, positionY, positionZ, 
        //   etc.

    // length of "tick mark":
    private float gLenTicks = 0.5f;

    private float[][] gColorData = {
            // color data in RGBA float format:
            {1.0f, 0.0f, 0.0f, 1.0f},  // red for X axis
            {0.0f, 1.0f, 0.0f, 1.0f},  // green for Y axis
            {0.0f, 0.0f, 1.0f, 1.0f},  // blue for Z axis
            {1.0f, 1.0f, 1.0f, 1.0f}
    };


    // the array of floating point values we'll use
    //   to pass coordinates for coord.sys axes vertices
    //    to the vertex shader's attribute variable:
    private FloatBuffer myVertexArray;

    private static final String TAG = "MyGLRenderer";

    private int myGLESProgram = -1;

    private int myViewPortWidth = -1;
    private int myViewPortHeight = -1;

    // lab02template:
    private int myFoVUniform = -1;
    private int myAspectUniform = -1;
    private int myNearUniform = -1;
    private int myFarUniform = -1;
    private int myTxUniform = -1;
    private int myTyUniform = -1;
    private int myColorUniform = -1;
    private int myVertexPositionAttribute = -1;

    private float myTouchXbegin = -1.0f;
    private float myTouchYbegin = -1.0f;
    private float myTouchXcurrent = -1.0f;
    private float myTouchYcurrent = -1.0f;
    private float myTouchXold = -1.0f;
    private float myTouchYold = -1.0f;

    // lab02template
    // store just X and Y coordinates for camera position
    //   since we only move the camera on the x-y plane:
    private float myTx = 5.0f;
    private float myTy = 8.0f;

    public String vertexShaderCode = "", fragmentShaderCode = "";


    // ------------------------------------------------------------------------
    // initialize OpenGL ES:
    // ------------------------------------------------------------------------
    private void setupGL() {

        // a Java byte buffer where to store the Vertex Buffer:
        ByteBuffer lByteBuffer;

        System.out.println("setupGL() ---------------------------------- ");
        String lGL_VERSION = GLES20.glGetString(GLES20.GL_VERSION);
        System.out.println("setupGL() - glGetString(GL_VERSION) returned " + lGL_VERSION);
        String lGL_SHADING_LANGUAGE_VERSION = GLES20.glGetString(GLES20.GL_SHADING_LANGUAGE_VERSION);
        System.out.println("setupGL() - glGetString(GL_SHADING_LANGUAGE_VERSION) returned " + lGL_SHADING_LANGUAGE_VERSION);
        System.out.println("setupGL() ---------------------------------- ");


        // get shaders ready -- load, compile, link GLSL code into GPU program:
        if (!this.loadShaders()) {
            System.err.println("setupGL() ---------------------------------- ");
            System.err.println("setupGL() hasn't been successful in creating OpenGL shaders");
            System.err.println("setupGL() ---------------------------------- ");
        }

        // allocate a byte buffer, for as many floting point numbers
        //   as there are individual coordinates (one for each x, y, etc.),
        //   multiplied times 4, since there are 4 bytes for each float:
        lByteBuffer = ByteBuffer.allocateDirect(myAxesData.length * 4);

        // prepare a Java byte buffer, it will contain all vertex coordinates defined in myAxesData:
        lByteBuffer.order(ByteOrder.nativeOrder());
        // treat the Java byte buffer as a floating-point buffer,
        //   and place it into myVertexArray:
        this.myVertexArray = lByteBuffer.asFloatBuffer();

        // Get location of attribute variable in the shader:
        this.myVertexPositionAttribute = GLES20.glGetAttribLocation(this.myGLESProgram, "a_Position");

        // get location of uniform variables in the shaders:
        this.myFoVUniform = GLES20.glGetUniformLocation(this.myGLESProgram, "u_FoV");
        this.myAspectUniform = GLES20.glGetUniformLocation(this.myGLESProgram, "u_Aspect");
        this.myNearUniform = GLES20.glGetUniformLocation(this.myGLESProgram, "u_Near");
        this.myFarUniform = GLES20.glGetUniformLocation(this.myGLESProgram, "u_Far");
        this.myTxUniform = GLES20.glGetUniformLocation(this.myGLESProgram, "u_Tx");
        this.myTyUniform = GLES20.glGetUniformLocation(this.myGLESProgram, "u_Ty");
        this.myColorUniform = GLES20.glGetUniformLocation(this.myGLESProgram, "u_Color");

        // in 3D, we need depth/Z-buffer:
        GLES20.glEnable(GLES20.GL_DEPTH_TEST);
        
        // glViewport(x, y, width, height)
        GLES20.glViewport ( 0, 0, this.myViewPortWidth, this.myViewPortHeight );

        // Set the background color:
        GLES20.glClearColor( 0.0f, 0.3f, 0.2f, 1.0f );

        // lab02template:
        buildAxes();

    } // end of setupGL()

    // ------------------------------------------------------------------------
    // lab02template
    // ------------------------------------------------------------------------
    private void buildAxes() {
        int i;
        int j=0;

        // build x axis: X,Y,Z coordinates
        myAxesData[j] = 0.0f; j++;
        myAxesData[j] = 0.0f; j++;
        myAxesData[j] = 0.0f; j++;

        myAxesData[j] = 10.0f; j++;
        myAxesData[j] = 0.0f; j++;
        myAxesData[j] = 0.0f; j++;

        for (i=1; i<=10; i++) {
            myAxesData[j] = (float)i; j++;
            myAxesData[j] = 0.0f; j++;
            myAxesData[j] = gLenTicks; j++;

            myAxesData[j] = (float)i; j++;
            myAxesData[j] = 0.0f; j++;
            myAxesData[j] = -gLenTicks; j++;
        }
        String lMessage = "X axis Ended at " + (float)i + " , " + 0.0f + " , " + -gLenTicks + " , " + j;
        // System.out.println(lMessage);


        // build y axis: X,Y,Z coordinates
        myAxesData[j] = 0.0f; j++;
        myAxesData[j] = 0.0f; j++;
        myAxesData[j] = 0.0f; j++;

        myAxesData[j] = 0.0f; j++;
        myAxesData[j] = 10.0f; j++;
        myAxesData[j] = 0.0f; j++;

        for (i=1; i<=10; i++) {
            myAxesData[j] = gLenTicks; j++;
            myAxesData[j] = (float)i; j++;
            myAxesData[j] = 0.0f; j++;

            myAxesData[j] = -gLenTicks; j++;
            myAxesData[j] = (float)i; j++;
            myAxesData[j] = 0.0f; j++;
        }
        lMessage = "Y axis Ended at " + -gLenTicks + " , " + (float)i + " , " + 0.0f + " , " + j;
        // System.out.println(lMessage);

        // build z axis: X,Y,Z coordinates
        myAxesData[j] = 0.0f; j++;
        myAxesData[j] = 0.0f; j++;
        myAxesData[j] = 0.0f; j++;

        myAxesData[j] = 0.0f; j++;
        myAxesData[j] = 0.0f; j++;
        myAxesData[j] = 10.0f; j++;

        for (i=1; i<=10; i++) {
            myAxesData[j] = gLenTicks; j++;
            myAxesData[j] = 0.0f; j++;
            myAxesData[j] = (float)i; j++;

            myAxesData[j] = -gLenTicks; j++;
            myAxesData[j] = 0.0f; j++;
            myAxesData[j] = (float)i; j++;
            lMessage = "Z axis Ended at " + -gLenTicks + " , " + 0.0f + " , " + (float)i + " , " + j;
            // System.out.println(lMessage);
        }

        // we only need to update the byte buffer once
        //   for vertex coordinates we prepared in myAxesData,
        //   since the axes vertices always stay the same:
        this.myVertexArray.put(myAxesData);
        this.myVertexArray.position(0);

    } // end of buildAxes()
    // ------------------------------------------------------------------------

    // ------------------------------------------------------------------------
    private void draw() {
        GLES20.glUseProgram(myGLESProgram);

        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);

        // pass values to uniform variables in the vertex shader:
        GLES20.glUniform1f(this.myFoVUniform, 90.0f);
        GLES20.glUniform1f(this.myAspectUniform, (float)(this.myViewPortWidth) / (float)(this.myViewPortHeight));
        GLES20.glUniform1f(this.myNearUniform, 10.0f);
        GLES20.glUniform1f(this.myFarUniform, 40.0f);
        GLES20.glUniform1f(this.myTxUniform, this.myTx);
        GLES20.glUniform1f(this.myTyUniform, this.myTy);
        String lMessage = "this.myFoVUniform, " + 90.0f + 
            "\n    this.myAspectUniform, " + (float)(this.myViewPortWidth) / (float)(this.myViewPortHeight) +
            "\n    this.myViewPortWidth, " + this.myViewPortWidth + 
            "\n    this.myViewPortHeight, " + this.myViewPortHeight + 
            "\n    this.myNearUniform, " + 10.0f + 
            "\n    this.myFarUniform, " + 40.0f +
            "\n    this.myTxUniform, " + this.myTx + 
            "\n    this.myTyUniform, " + this.myTy + " \n";

        // uncomment to debug at every redraw - warning, very vebose!
        // System.out.println(lMessage);
        

        // enable which vertex attributes the buffer data is going to use:
        GLES20.glEnableVertexAttribArray(myVertexPositionAttribute);

        // now call glVertexAttribPointer() to specify the location and data format
        //   of the array of generic vertex attributes at index,
        //   to be used at rendering time, when glDrawArrays() is going to be called.
        //
        // glVertexAttribPointer(indx: GLuint,
        //   _ size: GLint,
        //   _ type: GLenum,
        //   _ normalized: GLboolean,
        //   _ stride: GLsizei,
        //   _ ptr: UnsafePointer<Void>)
        // see https://www.khronos.org/opengles/sdk/docs/man/xhtml/glVertexAttribPointer.xml

        GLES20.glVertexAttribPointer(
                myVertexPositionAttribute,
                3,
                GLES20.GL_FLOAT,
                false,
                0,
                myVertexArray);

        GLES20.glLineWidth(1.0f);

        // finally... draw:
        
        // what color to use for the X axis:
        GLES20.glUniform4f(this.myColorUniform,
                gColorData[0][0],
                gColorData[0][1],
                gColorData[0][2],
                gColorData[0][3]);

        // lab02template:
        GLES20.glDrawArrays( GLES20.GL_LINES, 0, 22 );

        // what color to use for the Y axis:
        GLES20.glUniform4f(this.myColorUniform,
                gColorData[1][0],
                gColorData[1][1],
                gColorData[1][2],
                gColorData[1][3]);

        // lab02template:
        GLES20.glDrawArrays( GLES20.GL_LINES, 22, 22 );

        // what color to use for the Y axis:
        GLES20.glUniform4f(this.myColorUniform,
                gColorData[2][0],
                gColorData[2][1],
                gColorData[2][2],
                gColorData[2][3]);

        // lab02template:
        GLES20.glDrawArrays( GLES20.GL_LINES, 44, 22 );


    } // end of draw()
    // ------------------------------------------------------------------------






    // ------------------------------------------------------------------------
    @Override
    public void onSurfaceCreated(GL10 unused, EGLConfig config) {
        // called when the surface is created or recreated

        // this method is a convenient place to put code to create EGL resources
        // that need to be created when the rendering starts,
        // and that need to be recreated when the EGL context is lost.

        this.setupGL();
    }


    // ------------------------------------------------------------------------
    @Override
    public void onSurfaceChanged(GL10 unused, int width, int height) {
        // called after the surface is created and whenever the OpenGL ES surface size changes

        // Adjust the viewport based on geometry changes,
        // such as screen rotation
        GLES20.glViewport(0, 0, width, height);

        this.myViewPortHeight = height;
        this.myViewPortWidth = width;

    }


    // ------------------------------------------------------------------------
    @Override
    public void onDrawFrame(GL10 unused) {

        // called to draw the current frame
        this.draw();
    }


    // ------------------------------------------------------------------------
    private boolean loadShaders() {
        this.myGLESProgram = GLES20.glCreateProgram();

        int vertexShader = MyGLRenderer.loadShader(GLES20.GL_VERTEX_SHADER, this.vertexShaderCode);

        int fragmentShader = MyGLRenderer.loadShader(GLES20.GL_FRAGMENT_SHADER, this.fragmentShaderCode);

        GLES20.glAttachShader(myGLESProgram, vertexShader);
        GLES20.glAttachShader(myGLESProgram, fragmentShader);
        GLES20.glLinkProgram(myGLESProgram);

        return true;

    } // end of loadShaders()


    // ------------------------------------------------------------------------
    /**
     * Utility method for compiling a OpenGL shader.
     *
     * <p><strong>Note:</strong> When developing shaders, use the checkGlError()
     * method to debug shader coding errors.</p>
     *
     * @param type - Vertex or fragment shader type.
     * @param shaderCode - String containing the shader code.
     * @return - Returns an id for the shader.
     */
    public static int loadShader(int type, String shaderCode){

        // create a vertex shader type (GLES20.GL_VERTEX_SHADER)
        // or a fragment shader type (GLES20.GL_FRAGMENT_SHADER)
        int shader = GLES20.glCreateShader(type);

        // add the source code to the shader and compile it
        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);

        return shader;
    }

    /**
    * Utility method for debugging OpenGL calls. Provide the name of the call
    * just after making it:
    *
    * <pre>
    * mColorHandle = GLES20.glGetUniformLocation(mProgram, "vColor");
    * MyGLRenderer.checkGlError("glGetUniformLocation");</pre>
    *
    * If the operation is not successful, the check throws an error.
    *
    * @param glOperation - Name of the OpenGL call to check.
    */
    public static void checkGlError(String glOperation) {
        int error;
        while ((error = GLES20.glGetError()) != GLES20.GL_NO_ERROR) {
            Log.e(TAG, glOperation + ": glError " + error);
            throw new RuntimeException(glOperation + ": glError " + error);
        }
    }


    public void touchesBegan(float x, float y) {
        String lMessage = "Touch Began at " + x + " , " + y;

        // the position where the finger begins touching the screen:
        this.myTouchXbegin = (float)(x);
        this.myTouchYbegin = this.myViewPortHeight - (float)(y) - 1.0f;

        // the position where the finger currently touches the screen:
        this.myTouchXcurrent = this.myTouchXbegin;
        this.myTouchYcurrent = this.myTouchYbegin;

        // the last known position of the finger touching the screen:
        //   at redraw or at new (first) touch event:
        this.myTouchXold = this.myTouchXcurrent;
        this.myTouchYold = this.myTouchYcurrent;


    }

    public void touchesMoved(float x, float y) {
        String lMessage = "Touch Moved to " + x + " , " + y;

        // store "current" to "old" touch coordinates
        this.myTouchXold = this.myTouchXcurrent;
        this.myTouchYold = this.myTouchYcurrent;

        // get new "current" touch coordinates
        this.myTouchXcurrent = (float)(x);
        this.myTouchYcurrent = this.myViewPortHeight - (float)(y) - 1.0f;

        // lab02template:
        this.myTx += (this.myTouchXcurrent - this.myTouchXold) * 0.1f;
        this.myTy += (this.myTouchYcurrent - this.myTouchYold) * 0.1f;

        // System.out.println(lMessage);

    }

    public void touchesEnded(float x, float y) {
        String lMessage = "Touch Ended at " + x + " , " + y;

        // store "current" to "old" touch coordinates
        this.myTouchXold = this.myTouchXcurrent;
        this.myTouchYold = this.myTouchYcurrent;

        // get new "current" touch coordinates
        this.myTouchXcurrent = (float)(x);
        this.myTouchYcurrent = this.myViewPortHeight - (float)(y) - 1.0f;


        // System.out.println(lMessage);

    }



}
