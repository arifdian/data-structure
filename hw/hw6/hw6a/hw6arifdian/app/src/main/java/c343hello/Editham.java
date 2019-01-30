package c343hello;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by arifdian on 6/1/17.
 */

public class Editham
{
    String first = "";
    String second = "";

    public void getDNA()
    {
        Scanner read = new Scanner(System.in);
        System.out.println("1st dna: ");
        first = read.nextLine();
        Scanner read2 = new Scanner(System.in);
        System.out.println("2nd dna: ");
        second = read2.nextLine();
    }

    public int hamDNA(String a, String b)
    {
        int count = 0;
        for(int i = 0; i < a.length(); i++)
        {
            if(a.charAt(i) != b.charAt(i))
            {
                count += 1;
            }
        }
        return count;
    }

    public int editDNA(String a, String b)
    {
        int[] first = new int[a.length() + 1];
        int[] second = new int[b.length() + 1];
        int[][] countable = new int [first.length][second.length];

        first[0] = 0;
        second[0] = 0;

        // loop through dna a and store in first
        for(int i = 0; i < a.length(); i++)
        {
            first[i+1] = a.charAt(i);
        }

        //loop through dna b and store in 2nd
        for(int i = 0; i < b.length(); i++)
        {
            second[i+1] = b.charAt(i);
        }

        //loop through dna a and store in 2d array
        for(int i = 0; i <= a.length(); i++)
        {
            countable[i][0] = i;
        }

        // loop though dna b and store in 2d array
        for(int i = 0; i <= b.length(); i++)
        {
            countable[0][i] = i;
        }


        for(int i = 1; i <= a.length(); i++)
        {
            for(int j = 1; j <= b.length(); j++)
            {
                if(first[i] == second[j])
                {
                    countable[i][j] = Math.min (countable[i-1][j] + 1, Math.min(countable[i][j-1] + 1, countable[i-1][j-1]));
                }
                else
                {
                    countable[i][j] = Math.min (countable[i-1][j] + 1, Math.min(countable[i][j-1] + 1, countable[i-1][j-1] + 1));
                }
            }
        }
        return countable[a.length()][b.length()];
    }

    public static void main(String[] args)
    {
        Editham a = new Editham();
        a.getDNA();
        System.out.println("       ");
        System.out.println(a.first);
        System.out.println(a.second);
        System.out.println("       ");
        System.out.println("Hamming distance: " + a.hamDNA(a.first, a.second));
        System.out.println("edit distance: " + a.editDNA(a.first, a.second));
    }
}
