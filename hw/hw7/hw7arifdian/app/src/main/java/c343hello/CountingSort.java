package c343hello;
import java.util.*;
/**
 * Created by arifdian on 6/6/17.
 */

public class CountingSort
{
    //A is an unsorted array of n integer keys
    int [] A = new int[10];
    //C will be a sorted array of n integer keys (we can preserve the input array A)
    int [] C = new int[A.length];

    // get the maximum integer in the unsorted list.
    public static int getMax(int a[])
    {
        // store biggest in max_int
        int max_int = a[0];
        for(int i = 1; i < a.length; i++)
        {
            // if max_int is smaller than the next, assign the value to max_int
            if (max_int < a[i])
            {
                max_int = a[i];
            }
        }
        return max_int;
    }

    // sort method to arrange the integers in ascending order.
    public int[] sort(int[] a, int m)
    {
        for(int i = 0; i < a.length; i++)
        {
            C[a[i]] = C[a[i]] + 1;
        }
        // display the first step of counting sort.
        // number of numbers calculated through for loop of each element.
        System.out.println("The frequency is " + Arrays.toString(C));

        for(int i = 1; i <= m; i++)
        {
            C[i] += C[i - 1];
        }
        // the second step of counting sort.
        // add the number with the previous as it loops ascendinglly.
        // resulted in key with respect to position.
        System.out.println("the CountSum is " + Arrays.toString(C));

        int[] sorted = new int[a.length];

        // insert to final position (sorted) with respect to the key.
        for(int i = a.length - 1; i >= 0; i--)
        {
            sorted[--C[a[i]]] = a[i];
        }
        return sorted;
    }

    public static void main(String[] args)
    {
        CountingSort sortit = new CountingSort();
        int [] a = {1, 4, 2, 7, 1, 9, 8, 0, 3, 5};
        int [] b = sortit.sort(a, getMax(a));
        System.out.println("Sorted array: " + Arrays.toString(b));
        System.out.println("Max number: " + getMax(a));
        System.out.println("--------------------------------------------------------");
        System.out.println("Analysis: ");
        System.out.println("The running time of the counting sort algorithm is O(n)");
        System.out.println("we need to use m(the largest key value) through getMax to compare with the rest of numbers,");
        System.out.println("then we can get sorted array.And the each time we compare it is the n (the numbers of records)");
        System.out.println("If  k < n, the running time will not be changed O(n), and the counting sort will be effective.");
        System.out.println("If  n < k, the running time will be O(k) where k is maximum integer");
    }
}
