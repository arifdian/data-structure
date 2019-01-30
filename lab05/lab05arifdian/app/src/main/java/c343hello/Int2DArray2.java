package c343hello;

/**
 * Created by arifdian on 5/17/17.
 */

public class Int2DArray2 implements Int2DArray
{
    int[][] array = new int[5][2];

    public Int2DArray2(int size)
    {
        this.array = new int[size][2];
    }

    public void insertArray(int x, int position)
    {
        if(x % 2 == 0)
        {
            array[position][0] = x;
        }
        else
        {
            array[position][1] = x;
        }
    }

    public String checkArray()
    {
        if(array.length == 0)
        {
            return "null";
        }
        else
        {
            return "not null";
        }
    }

    public int noofelem()
    {
        return array.length;
    }

    public String oddoreven(int x)
    {
        String ans = "";
        for(int i=0; i < array.length; i++)
        {
            if(array[i][0] == x)
            {
                ans = "even";
                break;
            }
            else if(array[i][1] == x)
            {
                ans = "odd";
                break;
            }
            else
            {
                ans = "not in array";
            }
        }
        return ans;
    }

    public String sort()
    {
        for (int k=0; k < array.length - 1; k++)
        {
            for(int i=0; i < array.length - 1; i++)
            {
                if ((array[i][0] > array[i + 1][0]) || (array[i][1] > array[i+1][1]))
                {
                    int c = array[i][0];
                    array[i][0] = array[i + 1][0];
                    array[i + 1][0] = c;

                    int d = array[i][1];
                    array[i][1] = array[i + 1][1];
                    array[i + 1][1] = d;
                }
            }
        }

        String sorteven = "";
        String sortodd = "";
        for(int a = 0; a < array.length; a++)
        {
            sorteven = sorteven + array[a][0] + " ";
            sortodd = sortodd + array[a][1] + " ";
        }

        return "even = " + sorteven + "odd = " + sortodd;
    }

    public static void main(String[] args)
    {
        Int2DArray2 a = new Int2DArray2(3);
        a.insertArray(3,0);
        a.insertArray(2,1);
        a.insertArray(1,2);
        System.out.println(a);
        System.out.println(a.checkArray());
        System.out.println(a.noofelem());
        System.out.println(a.oddoreven(3));
        System.out.println(a.sort());
        Int2DArray2 b = new Int2DArray2(710000000);
    }
}
