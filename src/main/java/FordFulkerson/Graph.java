package FordFulkerson;

import java.util.List;
import java.util.ArrayList;

public class Graph {
    private int vCount;
    private int[][] adj;

    int getvCount() {
        return vCount;
    }

    int[][] getAdj() {
        return adj;
    }

    Graph(int vCount) {
        this.vCount = vCount;
        adj = new int[vCount][vCount];
        for (int i = 0; i < vCount; i++) {
            for (int j = 0; j < vCount; j++) {
                adj[i][j] = 0;
            }
        }
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

    private boolean hasEdge(int i, int j) {
        return adj[i][j] != 0;
    }

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
