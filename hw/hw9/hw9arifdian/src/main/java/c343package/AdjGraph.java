package c343package;

/**
 * Created by arifdian on 6/7/17.
 */

import java.util.*;
import java.io.*;

public class AdjGraph implements Graph {
    private boolean digraph;
    private int totalNode;
    private Vector<String> nodeList;
    private int totalEdge;
    private Vector<LinkedList<Integer>>  adjList; // adjacency list
    private Vector<Boolean> visited;
    private Vector<Integer> nodeEnum; // list of nodes pre visit
    public static String filename = "locations.txt";
    public int[][] table ;

    public AdjGraph() {
        init();
    }
    public AdjGraph(boolean ifdigraph) {
        init();
        digraph = ifdigraph;
    }
    public void init() {
        nodeList = new Vector<String>();
        adjList = new Vector<LinkedList<Integer>>();
        visited = new Vector<Boolean>();
        nodeEnum = new Vector<Integer>();
        totalNode = totalEdge = 0;
        digraph = false;


    }
    // set vertices
    public void setVertex(String[] nodes) {
        for(int i = 0; i < nodes.length; i ++) {
            nodeList.add(nodes[i]);
            adjList.add(new LinkedList<Integer>());
            visited.add(false);
            totalNode ++;

        }
    }
    // add a vertex
    public void addVertex(String label) {
        nodeList.add(label);
        visited.add(false);
        adjList.add(new LinkedList<Integer>());
        totalNode ++;
    }
    public int getNode(String node) {
        for(int i = 0; i < nodeList.size(); i ++) {
            if(nodeList.elementAt(i).equals(node)) return i;
        }
        return -1;
    }
    // return the number of vertices
    public int length() {
        return nodeList.size();
    }
    // add edge from v1 to v2
    public void setEdge(int v1, int v2, int weight) {
        LinkedList<Integer> tmp = adjList.elementAt(v1);
        if(adjList.elementAt(v1).contains(v2) == false) {
            tmp.add(v2);
            adjList.set(v1,  tmp);
            totalEdge ++;

        }
    }

    public void setEdge(String v1, String v2, int weight) {
        if((getNode(v1) != -1) && (getNode(v2) != -1)) {
            // add edge from v1 to v2
            setEdge(getNode(v1), getNode(v2), weight);
            // for undirected graphs, add edge from v2 to v1 as well
            if (digraph == false) setEdge(getNode(v2), getNode(v1), weight);
        }
    }

    // it is important to keep track if a vertex is visited or not (for traversal)
    public void setVisited(int v) {
        visited.set(v, true);
        nodeEnum.add(v);
    }
    public boolean ifVisited(int v) {
        return visited.get(v);
    }
    public void clearWalk() {
        // clean up before traversing
        nodeEnum.clear();
        for(int i = 0; i < nodeList.size(); i ++) visited.set(i, false);
    }
    public void walk(String method) {
        clearWalk();
        // traverse the graph
        for(int i = 0; i < nodeList.size(); i ++)
        {
            if(ifVisited(i) == false) {
                if(method.equals("BFS")) BFS(i);      // i is the start node
                else if(method.equals("DFS")) DFS(i); // i is the start node
                else {
                    System.out.println("unrecognized traversal order: " + method);
                    System.exit(0);
                }
            }
        }
        System.out.println(method + ":");
        displayEnum();
    }

    // Lab 13 TODO:
    //
    // write your walk2() method here.
    //



    public void DFS(int v) {
        setVisited(v);
        LinkedList<Integer> neighbors = adjList.elementAt(v);
        for(int i = 0; i < neighbors.size(); i ++) {
            int v1 = neighbors.get(i);
            if(ifVisited(v1) == false) DFS(v1);
        }
    }
    public void BFS(int s) {
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
    public void display() {
        System.out.println("total nodes: " + totalNode);
        System.out.println("total edges: " + totalEdge);
    }
    public void displayEnum() {
        for(int i = 0; i < nodeEnum.size(); i ++) {
            System.out.print(nodeList.elementAt(nodeEnum.elementAt(i)) + " ");
        }
        System.out.println();
    }

    public boolean checkRepeat(ArrayList<String> words,String word) {
        boolean checked = false;
        for(int i =0 ; i<words.size(); i++)
        {
            if(words.get(i).equals(word))
            {
                checked = true;
            }
        }
        return checked;
    }

    public String topSort() {
        int[] indegrees = new int[nodeList.size()];
        for (int i = 0; i < nodeList.size(); i++) {
            for (int j = 0; j < adjList.elementAt(i).size(); j++) {
                indegrees[adjList.elementAt(i).get(j)]++;
            }
        }

        Queue<Integer> q = new LinkedList<Integer>();
        for (int i = 0; i < nodeList.size(); i++) {
            if (indegrees[i] == 0) {
                q.add(i);
            }
        }

        int count = 0;
        Vector<Integer> topOrder = new Vector<>();
        while (!q.isEmpty()) {
            int u = q.poll();

            topOrder.add(u);

            for (int node : adjList.elementAt(u)) {
                if (--indegrees[node] == 0) {
                    q.add(node);
                }
            }
            count++;
        }

        if (count != nodeList.size()) {
            System.out.println("There has no topological order!");

        }
        String abc = "";

        for (int i : topOrder) {
            abc = abc + (nodeList.get(i) + " ");
        }
        return abc;
    }

    //create table
    public int getWeight(int i1,int i2){
        int cnt =0;
        table = new int[nodeList.size()][nodeList.size()];
        //add vertex and set edges
        try (BufferedReader br = new BufferedReader(new FileReader(filename)))
        {
            String currentLine;
            while ((currentLine = br.readLine()) != null)
            {
                String[] words = currentLine.split(" ");
                cnt ++;
                if (cnt>1) {
                    int firstOne = Integer.parseInt(words[0]);
                    int secondOne = Integer.parseInt(words[1]);
                    int thirdOne = Integer.parseInt(words[2]);
                    for(int i =0 ; i <nodeList.size();i++){
                        for (int j=0;j<nodeList.size();j++){
                            if(i==firstOne ) {
                                if (j==secondOne){
                                    table[i][j]=thirdOne;
                                }
                                else{
                                    table[i][j]=0;
                                }
                            }
                        }
                    }

                }
            }

        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return table[i1][i2];

    }
    // done




    public void longestPath(Vector<String> order) {
        int length = 0;
        int curMax = -1;
        for (int i = 0; i < order.size(); i++) {
            if(i==(order.size()-1)){
                break;
            }
            else {
                int firstOne = Integer.parseInt(order.get(i));
                int secondOne = Integer.parseInt(order.get(i+1));

                System.out.print(table[firstOne][secondOne]);
                // System.out.print(tableWeight(2, 4));
            }
        }
    }



    public static void main(String[] args) {
        int cnt = 0;
        AdjGraph agraph = new AdjGraph(true);
        ArrayList<String> nodes = new ArrayList<String>();
        //add vertex and set edges
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String currentLine;
            while ((currentLine = br.readLine()) != null) {
                String[] words = currentLine.split(" ");
                cnt ++;
                if (cnt>1) {
                    if(!agraph.checkRepeat(nodes,words[0])) {
                        nodes.add(words[0]);
                        agraph.addVertex(words[0]);
                    }

                    if (!agraph.checkRepeat(nodes,words[1])) {
                        agraph.addVertex(words[1]);
                        nodes.add(words[1]);
                    }

                    if(agraph.checkRepeat(nodes,words[0]) && agraph.checkRepeat(nodes,words[1])) {
                        agraph.setEdge(words[0],words[1],Integer.parseInt(words[2]));
                    }
                }
            }

        }
        catch (IOException e) {
            e.printStackTrace();
        }
        agraph.display();
        // created the topo list
        String abc = agraph.topSort();
        Vector<String> order2 = new Vector<String>();
        String[] order = abc.split(" ");
        for(int i = 0; i<order.length;i++)
        {
            // System.out.println(order[i]);
            order2.add(order[i]);
        }
        System.out.println(agraph.getWeight(2,4));
        //done creating
        // System.out.println(agraph.getWeight(2,4))
        agraph.longestPath(order2);
        // agraph.longestPath(order2);


    }
}
