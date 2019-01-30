package c343hello;
import java.util.*;

/**
 * Created by arifdian on 5/16/17.
 */

public class MyCardSet implements CardSet
{

    String[] suits = {"S","C","H","D"};
    String[] ranks = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
    String[] deck;

    public MyCardSet()
    {
        initialize();
    }

    public void initialize()
    {
        deck = new String[52];
        int i = 0;
        for(int z = 0; z < ranks.length; z++)
        {
            for(int y = 0; y < suits.length; y++)
            {
                deck[i] = ranks[z] + suits[y];
                i++;
            }
        }
    }

    public String drawRandomCard()
    {
        Random rand = new Random();
        return deck[rand.nextInt(deck.length)];
    }

    public static void main(String[] args)
    {
        MyCardSet play = new MyCardSet();
        System.out.println(play.drawRandomCard());
        System.out.println(play.drawRandomCard());
        System.out.println(play.drawRandomCard());
    }

}
