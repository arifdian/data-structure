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
    private Vector<LinkedList<Integer>>  adjList; //Adjacency list
    private Vector<Boolean> visited;
    private Vector<Integer> nodeEnum; //list of nodes pre visit
    public AdjGraph() {
        init();
    }
    public AdjGraph(boolean ifdigraph) {
        init();
        digraph =ifdigraph;
    }
    public void init() {
        nodeList = new Vector<String>();
        adjList = new Vector<LinkedList<Integer>>();
        visited = new Vector<Boolean>();
        nodeEnum = new Vector<Integer>();
        totalNode = totalEdge = 0;
        digraph = false;
    }
    //set vertices
    public void setVertex(String[] nodes) {
        for(int i = 0; i < nodes.length; i ++) {
            nodeList.add(nodes[i]);
            adjList.add(new LinkedList<Integer>());
            visited.add(false);
            totalNode ++;
        }
    }
    //add a vertex
    public void addVertex(String label) {
        nodeList.add(label);
        adjList.add(new LinkedList<Integer>());
    }
    public int getNode(String node)
    {
        for(int i = 0; i < nodeList.size(); i ++) {
            if(nodeList.elementAt(i).equals(node)) return i;
        }
        return -1;
    }
    //return the number of vertices
    public int length() {
        return nodeList.size();
    }
    //add edge from v1 to v2
    public void setEdge(int v1, int v2, int weight)
    {
        LinkedList<Integer> tmp = adjList.elementAt(v1);
        if(adjList.elementAt(v1).contains(v2) == false)
        {
            tmp.add(v2);
            adjList.set(v1,  tmp);
            totalEdge ++;
        }
    }
    public void setEdge(String v1, String v2, int weight)
    {
        if((getNode(v1) != -1) && (getNode(v2) != -1))
        {
            //add edge from v1 to v2
            setEdge(getNode(v1), getNode(v2), weight);
            //for digraph, add edge from v2 to v1 as well
            if(digraph == false) setEdge(getNode(v2), getNode(v1), weight);
        }
    }

    public void setVertices(String[] nodes)
    {
        for(int i = 0; i < nodes.length; i ++)
        {
            nodeList.add(nodes[i]);
            adjList.add(new LinkedList<Integer>());
            visited.add(false);
            totalNode ++;
        }
    }

    //it is important to keep track if a vertex is visited or not (for traversal)
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
        //clean up before traverse
        nodeEnum.clear();
        for(int i = 0; i < nodeList.size(); i ++)
        {
            visited.set(i, false);
        }
    }
    public void walk(String method)
    {
        clearWalk();
        //traverse the graph
        for(int i = 0; i < nodeList.size(); i ++) {
            if(ifVisited(i) == false) {
                if(method.equals("BFS")) BFS(i); //i as the start node
                else if(method.equals("DFS")) DFS(i); //i as the start node
                else {
                    System.out.println("unrecognized traversal order: " + method);
                    System.exit(0);
                }
            }
        }
        System.out.println(method + ":");
        displayEnum();
    }

    public void DFS(int v)
    {
        setVisited(v);
        LinkedList<Integer> neighbors = adjList.elementAt(v);
        for(int i = 0; i < neighbors.size(); i ++) {
            int v1 = neighbors.get(i);
            if(ifVisited(v1) == false) DFS(v1);
        }
    }

    public void BFS(int s)
    {
        ArrayList<Integer> toVisit = new ArrayList<Integer>();
        toVisit.add(s);
        while(toVisit.size() > 0) {
            int v = toVisit.remove(0); //first-in, first-visit
            setVisited(v);
            LinkedList<Integer> neighbors = adjList.elementAt(v);
            for(int i = 0; i < neighbors.size(); i ++)
            {
                int v1 = neighbors.get(i);
                if((ifVisited(v1) == false) && (toVisit.contains(v1) == false))
                {
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
    //topological sort using queue -- to be implemented

    public void topSort()
    {
        int[] indegree = new int[totalNode];
        Queue<Integer> q = new LinkedList<Integer>();

        for (int i = 0; i < totalNode; i++)
        {
            for (int j = 0; j < adjList.elementAt(i).size(); j++)
            {
                indegree[adjList.elementAt(i).get(j)]++;
            }

            if (indegree[i] == 0)
            {
                q.add(i);
            }

            int count = 0;
            Vector<String> sorted = new Vector<String>();

            while (q.isEmpty() == false)
            {
                int a = q.poll();
                count++;
                sorted.add(nodeList.get(a));

                for (int node : adjList.elementAt(a))
                {
                    if (indegree[node]-- == 0)
                        q.add(node);
                    node--;

                }
            }

            for (String k : sorted)
            {
                System.out.print(k + ",");
            }
        }
    }

    public void myFloyd()
    {
        int[][] graph = new int[totalNode][totalNode];

        for(int i=0;i < totalNode;i++)
        {
            graph[i][i]=0;
            for(int j=0;j < totalNode;j++)
            {
                if(i==j)
                {

                    graph[i][j] = 0;
                }
                else
                {
                    graph[i][j]= 100;
                }
            }
        }


        for(int i = 0; i < totalNode; i++)
        {
            LinkedList<Integer> neighbors = adjList.get(i);
            for(int j = 0; j < neighbors.size(); j++)
            {
                int x = neighbors.get(j);
                graph[i][x] = 1;
            }
        }


        for(int k=0;k < totalNode;k++)
        {
            for(int i=0;i < totalNode;i++)
            {
                for(int j=0;j < totalNode;j++)
                {
                    if(graph[i][j] > graph[i][k] + graph[k][j])
                    {
                        graph[i][j] = graph[i][k] + graph[k][j];
                    }
                }
            }
        }

        for(int i=0;i<totalNode;i++)
        {
            System.out.print(" " + nodeList.get(i));
        }
        System.out.println(" ");

        for(int z =0; z<nodeList.size(); z++)
        {
            System.out.print(nodeList.get(z) + " ");
            for(int r=0; r<nodeList.size(); r++)
            {
                System.out.print(graph[z][r] + " ");

            }
            System.out.println(" ");
        }
    }

    public static void main(String[] args)
    {
        AdjGraph lab13 = new AdjGraph(true);
        String[] nodes = {"A","B","C","D","E"};
        lab13.setVertices(nodes);
        lab13.setEdge(nodes[0],nodes[1],1); // A -> B
        lab13.setEdge(nodes[0],nodes[2],1); // A -> C
        lab13.setEdge(nodes[2],nodes[3],1); // C -> D
        lab13.setEdge(nodes[3],nodes[4],1); // D -> E
        lab13.setEdge(nodes[1],nodes[3],1); // B -> D
        lab13.setEdge(nodes[4],nodes[1],1); // E -> B
        lab13.setEdge(nodes[2],nodes[4],1); // C -> E
        lab13.myFloyd();
    }
}
