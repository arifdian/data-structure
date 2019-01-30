package c343hello;
import java.lang.*;
/**
 * Created by arifdian on 5/15/17.
 */

public class RunTimes
{
    public int countInstruction1(int n)
    {
        //1st example
        int sum1 = 0;
        int count = 0;
        for(int i = 1; i <= n; i ++)
            sum1 ++;
            count += 2;
        return count;
    }

    public int countInstruction2(int n)
    {
        //2nd example
        int sum2 = 0;
        int count1 = 0;
        int count2 = 0;
        for(int i = 1; i <= n; i ++)
            for(int j = 1; j <= n; j ++)
                sum2 ++;
                count1 += 2;
            count2 += 1;
        return count1 + count2;
    }

    public int countInstruction3(int n)
    {
        //3rd example
        int sum3 = 0;
        int count1, count2;
        for(int i = 1; i <= n; i ++)
            for(int j = 1; j <= i; j ++)
                sum3 ++;
                count1 += 2;
            count2 += 1;
        return count1 + count2;
    }

    public int countInstruction4(int n)
    {
        int sum4 = 0;
        int count = 0;
        for(int i = 1; i <= n; i *= 2)
            sum4 ++;
            count += 2;
        return count;
    }

    public int countInstruction5(int n)
    {
        int sum5 = 0;
        int count1, count2;
        for(int k = 1; k <= n; k *= 2)
            for(int j = 1; j <= n; j ++)
                sum5 ++;
                count1 += 1;
            count2 += 1;
        return count1 + count2;
    }

    public int countInstruction6(int n)
    {
        int sum6 = 0;
        int count1, count2;
        for(int k = 1; k <= n; k *= 2)
            for(int j = 1; j <= k; j ++)
                sum6 ++;
                count1 += 1;
            count2 += 1;
        return count1 + count2;
    }

    public int countInstruction7(int n, int m)
    {
        int[] d[0][0] = 0;
        String a = "     ";
        String b = "     ";
        int c = 0;
        int count1, count2;
        for(int i = 1; i <= m; i ++)
        {
            for(int j = 1; j <= n; j ++)
            {
                if(a[i] == b[j])
                {
                    c = 0;
                    count1 += 2;
                }
                else
                {
                    c = 1;
                }
                d[i][j] = Math.min(d[i-1][j] + 1, d[i][j-1] + 1, d[i-1][j-1]+c);
            }
            count2 += 1;
        }
        return count1 + count2;
    }

    public static void main(String[] args)
    {
        RunTimes times = new RunTimes();

        System.out.println(times.countInstruction1(10));
        System.out.println(times.countInstruction1(100));
        System.out.println(times.countInstruction1(1000));

        System.out.println(times.countInstruction2(10));
        System.out.println(times.countInstruction2(100));
        System.out.println(times.countInstruction2(1000));

        System.out.println(times.countInstruction3(10));
        System.out.println(times.countInstruction3(100));
        System.out.println(times.countInstruction3(1000));

        System.out.println(times.countInstruction4(10));
        System.out.println(times.countInstruction4(100));
        System.out.println(times.countInstruction4(1000));

        System.out.println(times.countInstruction5(10));
        System.out.println(times.countInstruction5(100));
        System.out.println(times.countInstruction5(1000));

        System.out.println(times.countInstruction6(10));
        System.out.println(times.countInstruction6(100));
        System.out.println(times.countInstruction6(1000));

        System.out.println(times.countInstruction7(10));
        System.out.println(times.countInstruction7(100));
        System.out.println(times.countInstruction7(1000));
    }
}
