package c343package;

/**
 * Created by arifdian on 6/13/17.
 */
import java.util.*;
public class LongestPathInDAG
{
    public static class Graph
    {
        private int[][] adjacencyMatrix;
        private int numberOfVertices;

        public Graph(int numberOfVertices) {
            this.numberOfVertices = numberOfVertices;
            this.adjacencyMatrix = new int[numberOfVertices][numberOfVertices];
        }

        public void addEdge(int source, int destination) {
            adjacencyMatrix[source][destination] = 1;
        }


        public int getNumberOfVertices() {
            return numberOfVertices;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < numberOfVertices; i++) {
                List<Integer> list = new ArrayList<>();
                for (int j = 0; j < numberOfVertices; j++) {
                    if (adjacencyMatrix[i][j] == 1) {
                        list.add(j);
                    }
                }
                sb.append(i + " -> " + list + "\n");

            }
            return sb.toString();
        }


    }

    public static Integer[] topologicalSortWithLongestPath(Graph graph)
    {
        Integer[] longestPath = new Integer[graph.numberOfVertices];
        Integer[] inDegree = new Integer[graph.numberOfVertices];

        for (int sourceVertex = 0; sourceVertex < graph.numberOfVertices; sourceVertex++) {
            inDegree[sourceVertex] = 0;
            longestPath[sourceVertex] = 0;
            for (int destinationVertex = 0; destinationVertex < graph.numberOfVertices; destinationVertex++) {
                inDegree[sourceVertex] += graph.adjacencyMatrix[destinationVertex][sourceVertex];

            }
        }

        for (int i = 0; i < graph.numberOfVertices; i++) {

            if (inDegree[i] == 0) {
                inDegree[i] = -1;
                for (int k = 0; k < graph.numberOfVertices; k++) {
                    if (graph.adjacencyMatrix[i][k] == 1) {
                        inDegree[k] = inDegree[k] - 1;
                        longestPath[k] = Math.max(longestPath[k], longestPath[k] + 1);
                    }
                }
            }
        }

        return longestPath;

    }

    public static void main(String[] args) {
        Graph graph = new Graph(8);
        graph.addEdge(0, 2);
        graph.addEdge(0, 3);
        graph.addEdge(0, 4);
        graph.addEdge(1, 2);
        graph.addEdge(1, 7);
        graph.addEdge(2, 5);
        graph.addEdge(3, 5);
        graph.addEdge(3, 7);
        graph.addEdge(4, 7);
        graph.addEdge(5, 6);
        graph.addEdge(6, 7);

        System.out.println(graph);

        Integer[] longestPath = topologicalSortWithLongestPath(graph);
        for (Integer vertex = 0; vertex < graph.getNumberOfVertices(); vertex++)
        {
            System.out.println("Vertex(" + vertex+") longest path = " + longestPath[vertex]);
        }
    }
}
