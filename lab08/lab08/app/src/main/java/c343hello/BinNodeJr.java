package c343hello;

/**
 * Created by arifdian on 5/23/17.
 */

public class BinNodeJr <E extends Comparable<?super E>>
{
    private E value;
    private BinNodeJr<E> left;
    private BinNodeJr<E> right;

    public BinNodeJr(E e)
    {
        value = e;
        left = right = null;
    }

    public void setLeft(BinNodeJr<E> node)
    {
        left = node;
    }

    public void setRight(BinNodeJr<E> node)
    {
        right = node;
    }

    public boolean find(E q)
    {
        boolean ans = true;
        if(this.value != null)
        {
            if(this.value.compareTo(q) == 0)
            {
                ans = true;
            }
            else
            {
                if(this.left != null && this.right == null)
                {
                    return this.left.find(q);
                }
                else if(this.left == null && this.right != null)
                {
                    return this.right.find(q);
                }
                else if(this.left != null && this.right != null)
                {
                    return this.left.find(q) || this.right.find(q);
                }
                else
                {
                    ans = false;
                }
            }
        }
        else
        {
            ans = false;
        }
        return ans;
    }

    public static void main(String[] argv)
    {
        BinNodeJr<Integer> root = new BinNodeJr<Integer>(10);
        BinNodeJr<Integer> node1 = new BinNodeJr<Integer>(30);
        BinNodeJr<Integer> node2 = new BinNodeJr<Integer>(40);
        BinNodeJr<Integer> node3 = new BinNodeJr<Integer>(50);
        BinNodeJr<Integer> node4 = new BinNodeJr<Integer>(60);
        BinNodeJr<Integer> node5 = new BinNodeJr<Integer>(70);
        BinNodeJr<Integer> node6 = new BinNodeJr<Integer>(80);

        root.setLeft(node1);
        root.setRight(node2);

        node1.setLeft(node3);
        node1.setRight(node4);

        node2.setLeft(node5);
        node2.setRight(node6);

        //find() is to be implemented
        System.out.println("40 is found in the tree: " + root.find(40));
        //find(40) shall return true
        System.out.println("100 is found in the tree: " + root.find(100));
        //find(100) shall return false
        System.out.println("70 is found in the tree: " + root.find(70));
        //find(70) shall return true
    }
}
