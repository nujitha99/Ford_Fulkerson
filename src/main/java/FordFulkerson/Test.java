package FordFulkerson;

import org.springframework.util.StopWatch;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Test {

    private int source = 0;
    private int sink = 5;
    private int vertexCount = 6;
    private Scanner sc = new Scanner(System.in);
    private Graph g;

    public static void main(String[] args) {
        Test test = new Test();
        String fileName = "src/main/java/FordFulkerson/6nodes.txt";
        test.readFile(fileName);
        test.execute();
    }

    private void execute() {
        int choice; //choice selection from the menu

        System.out.println("\n _________Welcome To the Program_________ \n Enter following options to execute..");
        System.out.println("1) Get Maximum Flow\n" +
                "2) Delete an edge & re-calculate max flow\n" +
                "3) Modify an edge capacity & re-calculate max flow\n" +
                "4) Calculate max flow for all four data sets and measure time\n" +
                "5) Exit the program");
        System.out.println();

        do {
            System.out.print("Enter your choice here: ");
            isIntegerInput();
            choice = sc.nextInt();
        } while (choice > 5 || choice < 1);


        switch (choice) {
            case 1:
                g.printGraph();
                System.out.println("\nCalculated Max-Flow is: " + FordFulkerson(g, source, sink) + "\n");
                execute();
            case 2:
                System.out.print("Enter source node: ");
                isIntegerInput();
                int node1 = sc.nextInt();

                System.out.print("Enter source node: ");
                isIntegerInput();
                int node2 = sc.nextInt();

                System.out.println("Removing edge...");
                g.removeEdge(node1, node2);
                System.out.println("Edge removed successfully!");

                System.out.println("\nCalculated Max-Flow updating is: " + FordFulkerson(g, source, sink) + "\n");

                execute();

            case 3:
                System.out.print("Enter start node: ");
                isIntegerInput();
                int nodeX = sc.nextInt();

                System.out.print("Enter end node: ");
                isIntegerInput();
                int nodeY = sc.nextInt();

                System.out.print("Enter new edge capacity: ");
                isIntegerInput();
                int capacity = sc.nextInt();

                System.out.println("Updating edge capacity...");
                g.modifyEdgeCapacity(nodeX, nodeY, capacity);
                System.out.println("Edge capacity updated successfully!");

                System.out.println("\nCalculated Max-Flow after updating is: " + FordFulkerson(g, source, sink) + "\n");

                execute();

            case 4:
                scalability();
                execute();
            case 5:
                System.out.println(" Quiting... \n \n Thank you!");
                System.exit(0);
        }
    }

    private void scalability(){
        StopWatch stopWatch = new StopWatch("My Stop Watch");

        stopWatch.start("6 nodes");
        System.out.println("Calculating time...");
        String fileName1 = "src/main/java/FordFulkerson/6nodes.txt";
        readDifferentFile(fileName1, 6);
        System.out.println("\nCalculated Max-Flow after updating is: " + FordFulkerson(g, source, 5));
        stopWatch.stop();

        stopWatch.start("12 nodes");
        String fileName2 = "src/main/java/FordFulkerson/6nodes.txt";
        readDifferentFile(fileName2, 6);
        System.out.println("\nCalculated Max-Flow after updating is: " + FordFulkerson(g, source, 5) + "\n");
        stopWatch.stop();

        stopWatch.start("24 nodes");
        String fileName3 = "src/main/java/FordFulkerson/6nodes.txt";
        readDifferentFile(fileName3, 6);
        System.out.println("\nCalculated Max-Flow after updating is: " + FordFulkerson(g, source, 5) + "\n");
        stopWatch.stop();

        stopWatch.start("36 nodes");
        String fileName4 = "src/main/java/FordFulkerson/6nodes.txt";
        readDifferentFile(fileName4, 6);
        System.out.println("\nCalculated Max-Flow after updating is: " + FordFulkerson(g, source, 5) + "\n");
        stopWatch.stop();

        System.out.println(stopWatch.prettyPrint());
    }

    //to ensure the user input is an integer input, else re-prompt
    private void isIntegerInput() {
        while (!sc.hasNextInt()) {
            System.out.println("Wrong input, please enter a valid integer : ");
            sc.next();
        }
    }

    private void readFile(String fileName) {
        g = new Graph(vertexCount);
        try {
            File file = new File(fileName);
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                String[] details = line.split(" ");
                int node1 = Integer.parseInt(details[0]);
                int node2 = Integer.parseInt(details[1]);
                int weight = Integer.parseInt(details[2]);

                g.addEdge(node1, node2, weight);
            }
            myReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readDifferentFile(String fileName, int vertexCount) {
        g = new Graph(vertexCount);
        try {
            File file = new File(fileName);
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                String[] details = line.split(" ");
                int node1 = Integer.parseInt(details[0]);
                int node2 = Integer.parseInt(details[1]);
                int weight = Integer.parseInt(details[2]);

                g.addEdge(node1, node2, weight);
            }
            myReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int FordFulkerson(Graph g, int source, int dest) {
        // error proof
        if (source == dest) {
            return 0;
        }
        int V = g.getvCount();

        // create residual graph
        Graph rg = new Graph(V);
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                rg.getAdj()[i][j] = g.getAdj()[i][j];
            }
        }

        // filled by BFS to store path
        int[] parent = new int[V];

        int max_flow = 0; // max flow value

        // while a path exists from source to dest loop
        while (bfs(rg, source, dest, parent)) {
            // to store path flow
            int path_flow = Integer.MAX_VALUE;

            ArrayList<Integer> pathView = new ArrayList<>();

            // find maximum flow of path filled by bfs
            for (int i = dest; i != source; i = parent[i]) {
                pathView.add(i);
                int j = parent[i];
                path_flow = Math.min(path_flow, rg.getAdj()[j][i]);
            }

            Collections.reverse(pathView);
            System.out.println("Augmenting path: " + pathView.toString().replace("[", "").replace("]", "") + " | Path flow: " + path_flow);
            pathView.clear();

            // update residual graph capacities
            // reverse edges along the path
            for (int i = dest; i != source; i = parent[i]) {
                int j = parent[i];
                rg.getAdj()[j][i] -= path_flow;
                rg.getAdj()[i][j] += path_flow;
            }

            // Add path flow to max flow
            max_flow += path_flow;
        }

        return max_flow;
    }

    private static boolean bfs(Graph rg, int source, int dest, int[] parent) {
        // array to store visited vertices
        boolean[] seen = new boolean[rg.getvCount()];
        for (int i = 0; i < rg.getvCount(); i++)
            seen[i] = false;

        LinkedList<Integer> q = new LinkedList<Integer>(); // queue-like

        // visit source
        q.add(source);
        seen[source] = true;
        parent[source] = -1;

        // loop through all vertices
        while (!q.isEmpty()) {
            int i = q.poll();
            // check neighbours of vertex i
            for (Integer j : rg.neighbours(i)) {
                // if not visited and positive value then visit
                if ((!seen[j]) && (rg.getAdj()[i][j] > 0)) {
                    q.add(j);
                    seen[j] = true;
                    parent[j] = i;
                }
            }
        }

        // return boolean that tells us if we ended up at the destination
        return seen[dest];
    }

}
