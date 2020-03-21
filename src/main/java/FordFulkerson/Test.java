package FordFulkerson;

import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Test {

    private SimpleDirectedWeightedGraph<String, DefaultWeightedEdge> graph =
            new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);

    public static void main(String[] args) {
        Test test = new Test();
        test.readFile();
    }

    private void readFile() {
        String fileName = "src/main/java/FordFulkerson/sample.txt";
        try {
            File file = new File(fileName);
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                String[] details = line.split(" ");
                String node1 = details[0];
                String node2 = details[1];
                String weight = details[2];

                graph.addVertex(node1);
                graph.addVertex(node2);

                DefaultWeightedEdge edge = graph.addEdge(node1, node2);
                graph.setEdgeWeight(edge, Double.parseDouble(weight));

                System.out.println(node1 + " " + node2 + " " + weight);

            }

            System.out.println(graph.toString());

            System.out.println("Shortest path from vertex1 to vertex5:");
            List shortest_path = Collections.singletonList(DijkstraShortestPath.findPathBetween(graph, "vertex1", "vertex5"));
            System.out.println(shortest_path);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
