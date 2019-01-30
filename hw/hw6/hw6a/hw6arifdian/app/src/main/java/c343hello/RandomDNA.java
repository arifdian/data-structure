package c343hello;

import java.util.Random;

public class RandomDNA
{

    public String nextDNA()
    {
        String[] dna = {"A","T","C","G"};
        String s = "";
        for(int i=0; i<100; i++)
        {
            Random rand = new Random();
            int r = rand.nextInt(dna.length);
            s = s + dna[r];
        }
        return s;
    }
}
