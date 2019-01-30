package c343hello;

import java.util.*;

// C343 lab 02 -- TODO

public class RandomDNA {

    public String nextDNA(int n) {
        String[] dna = {"A","T","C","G"};
        String s = "";
        for(int i=0; i<n; i++)
        {
            Random rand = new Random();
            int r = rand.nextInt(dna.length);
            s = s + dna[r];
        }
        return s;
    }

}

// C343 lab 02 -- TODO