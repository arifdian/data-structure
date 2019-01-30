package c343hello;

/**
 * Created by arifdian on 5/23/17.
 */

//a simple BST tree (so we call it BSTJr) for C343
public class BSTJr <K extends Comparable<?super K>>
{
    BinNode<K> root;
    BinNode<K> curr;

    // TODO for C343/Summer 2017 - Lab 08
    // use "unbalanced" for balance checking: if a node is unbalanced, the tree will be unbalanced
    BinNode<K> unbalanced = null;
    public BSTJr() {
        root = null;
        curr = null;
    }
    public void build(K[] ks) {
        for(int i = 0; i < ks.length; i ++) insert(ks[i]);
    }
    public void insert(K k) {
        BinNode<K> t = new BinNode<K>(k);
        if(root == null) {
            root = curr = t;
        }
        else {
            curr = search(root, k);
            if(k.compareTo(curr.getKey()) < 0) curr.setLeft(t);
            else curr.setRight(t);
        }
    }
    public BinNode<K> search(BinNode<K> entry, K k) {
        if(entry == null) return null;
        else {
            entry.setSize(entry.getSize() + 1); //update the size of the subtree by one
            if(entry.isLeaf()) return entry;
            if(k.compareTo(curr.getKey()) < 0) {
                if(entry.getLeft() != null) return search(entry.getLeft(), k);
                else return entry;
            }
            else {
                if(entry.getRight() != null) return search(entry.getRight(), k);
                else return entry;
            }
        }
    }
    public void display() {
        if(root == null) return;
        System.out.println("Preorder enumeration: key(size-of-the-subtree)");
        preorder(root);
        System.out.println();
    }
    public void preorder(BinNode<K> entry) {
        System.out.print(entry.getKey() + "(" + entry.getSize() + ") ");
        if(entry.getLeft() != null) preorder(entry.getLeft());
        if(entry.getRight() != null) preorder(entry.getRight());
    }

    // TODO for C343/Summer 2017 - Lab 08
    // implement checkBalance(), perhaps write treeHeight(node) as helper function
    public int treeHeight(BinNode<K> a)
    {
        if(a == null)
        {
            return 0;
        }
        else
        {
            return 1 + Math.max(treeHeight(a.getLeft()), treeHeight(a.getRight()));
        }
    }


    public boolean checkBalance2(BinNode<K> a)
    {
        int max = treeHeight(root);

        if (a == null)
        {
            return true;
        }
        else
        {
            int left = treeHeight(a.getLeft());
            int right = treeHeight(a.getRight());
            if (Math.abs(left - right) < 2)
            {
                return checkBalance2(a.getLeft()) && checkBalance2(a.getRight());
            }
            else
            {
                return false;
            }
        }
    }

    public String checkBalance(BinNode<K> a)
    {
        if(checkBalance2(a) == true)
        {
            return "Balanced";
        }
        else
        {
            return "Unbalanced";
        }
    }

    public static void main(String[] argv) {
        BSTJr<Integer> tree = new BSTJr<Integer>();
        Integer[] ks = {37, 24, 42, 7, 2, 40, 120};
        tree.build(ks);
        tree.display();
        System.out.println(tree.checkBalance(tree.root));
    }
}