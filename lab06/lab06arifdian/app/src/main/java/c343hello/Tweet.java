package c343hello;
import java.lang.String;
/**
 * Created by arifdian on 5/16/17.
 */

public class Tweet
{
    String author = "";
    String content = "";

    public Tweet(String author, String content)
    {
        this.author = author;
        this.content = content;
    }

    public String contains()
    {
        if(content != "")
        {
            return "true";
        }
        else
        {
            return "false";
        }
    }

    public String getAuthor()
    {
        return author;
    }

    public String getContent()
    {
        return content;
    }

    public void print()
    {
        System.out.println(author);
        System.out.println(content);
    }

    public static void main(String[] args)
    {
        Tweet a = new Tweet("lalal","heifuwgerurhoruhg");
        a.contains();
        a.print();

        Tweet b = new Tweet("ajex","hehehehehhe");
        b.contains();
        b.print();

        Tweet c = new Tweet("baba","lololololol");
        c.contains();
        c.print();
    }

}