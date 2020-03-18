package FordFulkerson;

import java.util.LinkedList;

public class Test {

    public static void main(String[] args) {
        Graph g = new Graph(6);

        System.out.println("Graph:");
        // add Edges
        g.addEdge(0, 1, 10);
        g.addEdge(0, 2, 8);
        g.addEdge(1, 2, 5);
        g.addEdge(1, 3, 5);
        g.addEdge(2, 1, 4);
        g.addEdge(2, 4, 10);
        g.addEdge(4, 3, 10);
        g.addEdge(4, 5, 14);
        g.addEdge(3, 4, 6);
        g.addEdge(3, 5, 3);

        // print Graph
        g.printGraph();

        // Ford-Fulkerson Max Flow Algorithm
        System.out.print("Ford-Fulkerson Max Flow: ");
        System.out.println(FordFulkerson(g, 0, 5));
    }

    public static int FordFulkerson(Graph g, int source, int dest) {
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
        int parent[] = new int[V];

        int max_flow = 0; // max flow value

        // while a path exists from source to dest loop
        while (bfs(rg, source, dest, parent)) {
            // to store path flow
            int path_flow = Integer.MAX_VALUE;

            // find maximum flow of path filled by bfs
            for (int i = dest; i != source; i = parent[i]) {
                int j = parent[i];
                path_flow = Math.min(path_flow, rg.getAdj()[j][i]);
            }

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

    public static boolean bfs(Graph rg, int source, int dest, int parent[]) {
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
                if ((seen[j] == false) && (rg.getAdj()[i][j] > 0)) {
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
