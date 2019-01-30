package c343hello;

/**
 * Created by mitja on 2017-05-15.
 */

public class RunTimes {

    public void countInstructions(int n) {

        // very simple counter for logging running times:
        int instructioncounter;


        //
        // Example 1:
        //
        int sum1;
        int i;
        sum1 = 0;
        instructioncounter = 0;

        for(i = 1, instructioncounter++; i <= n; i ++, instructioncounter++) {
            sum1++;
            instructioncounter++;
        }
        System.out.println ("in Example 1, for size n = " + n + ", instructioncounter is: " + instructioncounter);

        //
        // Example 7:
        //
        instructioncounter = 0;
        int[] a = new int[n+1];
        int[] b = new int[n+1];
        int[][] d = new int[n+1][n+1];
        for(i = 0; i < a.length; i++) {
            a[i] = (int)(Math.random() * 10);
        }
        for(i = 0; i < b.length; i++) {
            b[i] = (int)(Math.random() * 10);
        }
        int c;
        int j;

        d[0][0] = 0;
        for(i = 1, instructioncounter++; i <= n; i ++, instructioncounter++) {
            for(j = 1, instructioncounter++; j <= n; j ++, instructioncounter++) {
                if (a[i] == b[j]) {
                    instructioncounter++;
                    c = 0;
                } else {
                    instructioncounter++;
                    c = 1;
                }
                d[i][j] = Math.min(
                        d[i-1][j] + 1,
                        Math.min(
                                d[i][j-1] + 1,
                                d[i-1][j-1] + c
                        )
                );
                instructioncounter++;
            }
        }

        System.out.println ("in Example 7, for size n = " + n + ", instructioncounter is: " + instructioncounter);

    }  // end of countInstructions()


}  // end of class RunTimes
