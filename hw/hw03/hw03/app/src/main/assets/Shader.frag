//
//  Shader.fsh
//  lab02template
//
//  Created by Mitja Hmeljak on 2017-04-20.
//  Copyright  2017 CSCI C343 Summer 2017. All rights reserved.
//


precision mediump float;

uniform vec4 u_Color;

void main() {
    gl_FragColor = vec4( u_Color.r, u_Color.g, u_Color.b, u_Color.a );
}
