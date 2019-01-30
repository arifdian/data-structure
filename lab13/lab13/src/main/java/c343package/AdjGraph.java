package c343package;

/**
 * Created by arifdian on 6/7/17.
 */

import java.util.*;

public class AdjGraph implements Graph
{
    private boolean digraph;
    private int totalNode;
    private Vector<String> nodeList;
    private int totalEdge;
    private Vector<LinkedList<Integer>>  adjList; // adjacency list
    private Vector<Boolean> visited;
    private Vector<Integer> nodeEnum; // list of nodes pre visit
    private int numnode;

    public AdjGraph()
    {
        init();
    }

    public AdjGraph(boolean ifdigraph)
    {
        init();
        digraph = ifdigraph;
    }

    public void init()
    {
        nodeList = new Vector<String>();
        adjList = new Vector<LinkedList<Integer>>();
        visited = new Vector<Boolean>();
        nodeEnum = new Vector<Integer>();
        totalNode = totalEdge = 0;
        digraph = false;
    }

    // set vertices
    public void setVertex(String[] nodes)
    {
        for(int i = 0; i < nodes.length; i ++) {
            nodeList.add(nodes[i]);
            adjList.add(new LinkedList<Integer>());
            visited.add(false);
            totalNode ++;
        }
    }

    // add a vertex
    public void addVertex(String label)
    {
        nodeList.add(label);
        visited.add(false);
        adjList.add(new LinkedList<Integer>());
        totalNode ++;
    }

    public int getNode(String node)
    {
        for(int i = 0; i < nodeList.size(); i ++)
        {
            if(nodeList.elementAt(i).equals(node)) return i;
        }
        return -1;
    }

    // return the number of vertices
    public int length()
    {
        return nodeList.size();
    }

    // add edge from v1 to v2
    public void setEdge(int v1, int v2, int weight)
    {
        LinkedList<Integer> tmp = adjList.elementAt(v1);
        if(adjList.elementAt(v1).contains(v2) == false) {
            tmp.add(v2);
            adjList.set(v1,  tmp);
            totalEdge ++;
        }
    }

    public void setEdge(String v1, String v2, int weight)
    {
        if((getNode(v1) != -1) && (getNode(v2) != -1)) {
            // add edge from v1 to v2
            setEdge(getNode(v1), getNode(v2), weight);
            // for undirected graphs, add edge from v2 to v1 as well
            if (digraph == false) setEdge(getNode(v2), getNode(v1), weight);
        }
    }

    // it is important to keep track if a vertex is visited or not (for traversal)
    public void setVisited(int v)
    {
        visited.set(v, true);
        nodeEnum.add(v);
    }

    public boolean ifVisited(int v)
    {
        return visited.get(v);
    }

    public void clearWalk()
    {
        // clean up before traverse
        nodeEnum.clear();
        for(int i = 0; i < nodeList.size(); i ++)
        {
            visited.set(i, false);
        }
    }

    public void walk(String method)
    {
        clearWalk();
        // traverse the graph
        for(int i = 0; i < nodeList.size(); i++)
        {
            if(ifVisited(i) == false)
            {
                if(method.equals("BFS"))
                {
                    BFS(i);      // i is the start node
                }
                else if(method.equals("DFS"))
                {
                    DFS(i); // i is the start node
                }
                else
                {
                    System.out.println("unrecognized traversal order: " + method);
                    System.exit(0);
                }
            }
        }
        System.out.println(method + ":");
        displayEnum();
    }

    public void walk2(String method)
    {
        clearWalk();
        int noofcomp = 0;

        for(int i=0; i < nodeList.size(); i++)
        {

            if(ifVisited(i) == false)
            {
                numnode = 0;
                if(method.equals("BFS"))
                {
                    BFS(i);
                }
                else if(method.equals("DFS"))
                {
                    DFS(i);
                    if (numnode < 2)
                    {
                        System.out.println("Component " + noofcomp + " contains " + numnode + " node");
                    }
                    else
                    {
                        System.out.println("Component " + noofcomp + " contains " + numnode + " nodes");
                    }
                    noofcomp++;
                }
            }
        }
        System.out.println("Total Components: " + noofcomp);
    }

    public void DFS(int v)
    {
        numnode++;
        setVisited(v);
        LinkedList<Integer> neighbors = adjList.elementAt(v);
        for(int i = 0; i < neighbors.size(); i ++)
        {
            int v1 = neighbors.get(i);
            if(ifVisited(v1) == false) DFS(v1);
        }
    }

    public void BFS(int s)
    {
        ArrayList<Integer> toVisit = new ArrayList<Integer>();
        toVisit.add(s);
        while(toVisit.size() > 0) {
            int v = toVisit.remove(0); // first-in, first-visit
            setVisited(v);
            LinkedList<Integer> neighbors = adjList.elementAt(v);
            for(int i = 0; i < neighbors.size(); i ++) {
                int v1 = neighbors.get(i);
                if((ifVisited(v1) == false) && (toVisit.contains(v1) == false)) {
                    toVisit.add(v1);
                }
            }
        }
    }

    public void display()
    {
        System.out.println("total nodes: " + totalNode);
        System.out.println("total edges: " + totalEdge);
    }

    public void displayEnum()
    {
        for(int i = 0; i < nodeEnum.size(); i ++)
        {
            System.out.print(nodeList.elementAt(nodeEnum.elementAt(i)) + " ");
        }
        System.out.println();
    }

    public static void main(String[] args)
    {
        AdjGraph lab13 = new AdjGraph();
        String[] nodes = {"A","B","C","D","E"};
        lab13.setVertex(nodes);
        lab13.setEdge("A","B",1);
        lab13.setEdge("B","C",1);
        lab13.display();
        lab13.walk("DFS");
        lab13.walk("BFS");
        lab13.displayEnum();
        System.out.println("---------------");
        System.out.println(lab13.nodeEnum);
        System.out.println(lab13.nodeList);
        lab13.walk2("DFS");
    }
}
