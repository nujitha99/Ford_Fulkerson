package FordFulkerson;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    private int vCount;
    private int[][] adj; //stores the graph edges

    //constructor
    Graph(int vCount) {
        this.vCount = vCount;
        adj = new int[vCount][vCount];
        for (int i = 0; i < vCount; i++) {
            for (int j = 0; j < vCount; j++) {
                adj[i][j] = 0;
            }
        }
    }

    //returns edge count
    int getvCount() {
        return vCount;
    }

    //returns the graph
    int[][] getAdj() {
        return adj;
    }

    void addEdge(int i, int j, int weight) {
        adj[i][j] = weight;
    }

    void removeEdge(int i, int j) {
        adj[i][j] = 0;
    }

    void modifyEdgeCapacity(int i, int j, int weight) {
        adj[i][j] = weight;
    }

    //returns boolean if has an edge between given edges
    private boolean hasEdge(int i, int j) {
        return adj[i][j] != 0;
    }

    //return neighbour vertices of the given node
    List<Integer> neighbours(int vertex) {
        List<Integer> edges = new ArrayList<>();
        for (int i = 0; i < vCount; i++)
            if (hasEdge(vertex, i))
                edges.add(i);
        return edges;
    }

    void printGraph() {
        for (int i = 0; i < vCount; i++) {
            List<Integer> edges = neighbours(i);
            System.out.print(i + ": ");
            for (Integer edge : edges) {
                System.out.print(edge + " ");
            }
            System.out.println();
        }
    }
}
