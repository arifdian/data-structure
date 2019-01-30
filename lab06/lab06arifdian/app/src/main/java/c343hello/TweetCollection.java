package c343hello;
import java.net.*;
import java.io.*;
import java.util.*;
/**
 * Created by arifdian on 5/18/17.
 */

public class TweetCollection
{

    ArrayList<Tweet> list = new ArrayList<Tweet>();

    public String countTweet(String author)
    {
        int count = 0;
        for(int i=0; i < list.size(); i++)
        {
           if(list.get(i).getAuthor().equals(author))
           {
                count += 1;
           }
        }
        System.out.println(author + " " + count);
        return "";
    }

    public String mostTweet()
    {
        String name = "";
        String[] mylist = new String[48];
        int count = 0;
        int ans = 0;

        for(int i=0; i < 48; i++)
        {
            mylist[i] = list.get(i).getAuthor();
        }

        for(int i=0; i < list.size(); i++)
        {
            int j;
            for(j=0; j < list.size(); j++)
            {
                if((list.get(i).getAuthor()).equals(list.get(j).getAuthor()))
                {
                    count = count + 1;
                }
                else
                {
                    count = count + 0;
                }
            }

            if(count > ans)
            {
                ans = count;
                count = 0;
                name += mylist[i];
            }
        }

        System.out.println(name + ans + "---" + count);
        return "";
    }

    public static void main(String[] args) throws Exception
    {
        TweetCollection tc = new TweetCollection();
        URL fromlink = new URL("http://homes.soic.indiana.edu/classes/summer2017/csci/c343-mitja/test2017/tweet-data-May11.txt");
        Scanner in = new Scanner(fromlink.openStream());

        String str = "";

        while (in.hasNext())
        {
            str += in.nextLine();
        }

        String[] separate = str.split(" ");
        int howlong = 0;
        for(int i=0; i < separate.length; i++)
        {
            if(separate[i].substring(0,1).equals("@"))
            {
                howlong = howlong + 1;
            }
        }

        String[] author = new String[howlong];
        String[] content = new String[separate.length];

        int count = 0;
        int times = 0;

        for(int i=0; i < separate.length; i++)
        {
            String target = separate[i];
            if(target.substring(0,1).equals("@"))
            {
                author[count] = target;
                count += 1;
                times += 1;
            }
            else
            {
                content[times] += target;
            }
        }

        for(int i=0; i < author.length; i++)
        {
            tc.list.add(new Tweet(author[i],content[i]));
        }

        in.close();
        System.out.println(author.length);
        System.out.println(tc.list.size());
        System.out.println(tc.list.get(0).getAuthor());
        System.out.println(tc.list.get(47).getAuthor());
        tc.countTweet("@markcote873:");
        tc.countTweet("@EvanSinar:");
        tc.mostTweet();
    }
}
