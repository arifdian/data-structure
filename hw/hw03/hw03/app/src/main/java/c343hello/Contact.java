package c343hello;
import java.util.*;
import java.lang.String;
/**
 * Created by arifdian on 5/22/17.
 */

public class Contact
{
    String fname = "";
    String lname = "";
    String email = "";

    public Contact(String fname, String lname, String email)
    {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
    }

    public String listing()
    {

        return "First name: " + fname +
                " Last name: " + lname +
                " Email: " + email;
    }

    public Boolean contains()
    {
        Boolean ans = false;
        for(int i=0; i < email.length(); i++)
        {
            if(email.charAt(i) == 's' && email.charAt(i+1) == 't')
            {
                ans = true;
            }
        }
        return ans;
    }

}
