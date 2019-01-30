package c343hello;
import java.io.*;
import java.net.*;
import java.util.*;

/**
 * Created by arifdian on 6/6/17.
 */

public class hashSearch
{

    public void search (String word)
    {
        int count = 0;
        String line = "";

        HashMap<String, LinkedList<Integer>> hash = new HashMap <String, LinkedList<Integer>>();

        try
        {
            File file = new File("text.txt");
            FileReader reader = new FileReader(file);
            BufferedReader buff = new BufferedReader(reader);


            while((line = buff.readLine()) != null)
            {
                count++;
                String words[] = line.split(" ");

                for (int i = 0; i < words.length; i++)
                {
                    String k = words[i];
                    if(hash.containsKey(k) == false)
                    {
                        hash.put(k,new LinkedList<Integer>());
                    }

                    LinkedList<Integer> value = hash.get(k);
                    if(value.contains(count) == false)
                    {
                        hash.get(k).add(count);
                    }
                }
            }

            if (hash.containsKey(word))
            {
                System.out.println("Word " + word + " is found on lines: " + hash.get(word).toString());
            }
            else
            {
                System.out.println("Word " + word + " is not found in this file.");
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

    }

    public static void main(String[] args)
    {
        hashSearch sample = new hashSearch();
        sample.search("algorithm");
        sample.search("data");
    }

}
