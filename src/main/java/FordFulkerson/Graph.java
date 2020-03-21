package FordFulkerson;

/*
package FordFulkerson;

import java.util.List;
import java.util.ArrayList;

public class Graph {
    private int vCount;
    private int[][] adj;

    public int getvCount() {
        return vCount;
    }

    public int[][] getAdj() {
        return adj;
    }

    public Graph(int vCount) {
        this.vCount = vCount;
        adj = new int[vCount][vCount];
        for (int i = 0; i < vCount; i++) {
            for (int j = 0; j < vCount; j++) {
                adj[i][j] = 0;
            }
        }
    }

    public void addEdge(int i, int j, int weight) {
        adj[i][j] = weight;
    }

    public void removeEdge(int i, int j) {
        adj[i][j] = 0;
    }

    public boolean hasEdge(int i, int j) {
        if (adj[i][j] != 0) {
            return true;
        }
        return false;
    }

    public List<Integer> neighbours(int vertex) {
        List<Integer> edges = new ArrayList<Integer>();
        for (int i = 0; i < vCount; i++)
            if (hasEdge(vertex, i))
                edges.add(i);
        return edges;
    }

    public void printGraph() {
        for (int i = 0; i < vCount; i++) {
            List<Integer> edges = neighbours(i);
            System.out.print(i + ": ");
            for (int j = 0; j < edges.size(); j++) {
                System.out.print(edges.get(j) + " ");
            }
            System.out.println();
        }
    }
}
*/


import org.jgrapht.Graphs;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.*;

import java.util.Collections;
import java.util.List;

public class Graph {
    public static void main(String args[]) {

        SimpleDirectedWeightedGraph<String, DefaultWeightedEdge> graph =
                new SimpleDirectedWeightedGraph<String, DefaultWeightedEdge>
                        (DefaultWeightedEdge.class);

        graph.addVertex("vertex1");
        graph.addVertex("vertex2");
        graph.addVertex("vertex3");
        graph.addVertex("vertex4");
        graph.addVertex("vertex5");


        DefaultWeightedEdge e1 = graph.addEdge("vertex1", "vertex2");
        graph.setEdgeWeight(e1, 5);

        DefaultWeightedEdge e2 = graph.addEdge("vertex2", "vertex3");
        graph.setEdgeWeight(e2, 3);

        DefaultWeightedEdge e3 = graph.addEdge("vertex4", "vertex5");
        graph.setEdgeWeight(e3, 6);

        DefaultWeightedEdge e4 = graph.addEdge("vertex2", "vertex4");
        graph.setEdgeWeight(e4, 2);

        DefaultWeightedEdge e5 = graph.addEdge("vertex5", "vertex4");
        graph.setEdgeWeight(e5, 4);

        DefaultWeightedEdge e6 = graph.addEdge("vertex2", "vertex5");
        graph.setEdgeWeight(e6, 9);

        DefaultWeightedEdge e7 = graph.addEdge("vertex4", "vertex1");
        graph.setEdgeWeight(e7, 7);

        DefaultWeightedEdge e8 = graph.addEdge("vertex3", "vertex2");
        graph.setEdgeWeight(e8, 2);

        DefaultWeightedEdge e9 = graph.addEdge("vertex1", "vertex3");
        graph.setEdgeWeight(e9, 10);

        DefaultWeightedEdge e10 = graph.addEdge("vertex3", "vertex5");
        graph.setEdgeWeight(e10, 1);

        System.out.println(Graphs.neighborListOf(graph, "vertex1"));

/*
        System.out.println("Shortest path from vertex1 to vertex5:");
        List shortest_path = Collections.singletonList(DijkstraShortestPath.findPathBetween(graph, "vertex1", "vertex5"));
        System.out.println(shortest_path);
*/

    }

}
