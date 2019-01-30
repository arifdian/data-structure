package c343hello;
import java.util.*;
import java.io.*;
import android.content.res.AssetManager;
import android.app.Activity;
import java.lang.String;
/**
 * Created by arifdian on 5/22/17.
 */

public class ContactCollection
{
    private Activity gActivity;
    ArrayList<Contact> contact = new ArrayList<Contact>();

    public ContactCollection(Activity pActivity) throws IOException
    {
        this.gActivity = pActivity;
    }


    public String readContactsFile() throws IOException
    {
        // here's where we get access to the plain-text file:
        AssetManager assets = this.gActivity.getAssets();
        InputStream inStream = assets.open("contacts.txt");
        Scanner in = new Scanner(new InputStreamReader(inStream));
        String line = "";
        while (in.hasNext())
        {
            line += in.nextLine();
        }
        System.out.println(line);
        return line;
    }

    public String countcontacts()
    {
        System.out.println(contact.size());
        return "";
    }

    public String listbyalphabet(char a)
    {
        String results = "";
        for(int i=0;  i < contact.size(); i++)
        {
            if(contact.get(i).fname.charAt(0) == a)
            {
                results = results + contact.get(i).fname + contact.get(i).lname + ", ";
            }
        }
        System.out.println(results);
        return "";
    }
}
