package c343hello;
import java.util.*;
import java.lang.String;

public class DNADist
{
    public int hamDNA(String a, String b)
    {
        int count = 0;
        for(int i = 0; i < 100; i++)
        {
            if(a.charAt(i) != b.charAt(i))
            {
                count += 1;
            }
        }
        return count;
    }
}
