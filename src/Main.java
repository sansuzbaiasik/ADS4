/*
 * Task 1 - DFS Trace (source: A)
 *
 * Graph adjacency lists:
 * A: C B D
 * B: A C E G
 * C: A B D
 * D: C A
 * E: G F B
 * F: G E
 * G: F B
 *
 * Step 1: Start at A, mark A. Stack: [A]
 * Step 2: Visit C (first neighbor of A). Stack: [A, C]
 * Step 3: A visited(skip), visit B (next of C). Stack: [A, C, B]
 * Step 4: A,C visited(skip), visit E (next of B). Stack: [A, C, B, E]
 * Step 5: Visit G (first of E). Stack: [A, C, B, E, G]
 * Step 6: Visit F (first of G). Stack: [A, C, B, E, G, F]
 * Step 7: G,E visited(skip), backtrack to G
 * Step 8: B visited(skip), backtrack to E
 * Step 9: F,B visited(skip), backtrack to B
 * Step 10: G visited(skip), backtrack to C
 * Step 11: Visit D (next of C). Stack: [A, C, B, E, G, F, D]
 * Step 12: C,A visited(skip), backtrack. DFS complete.
 *
 * DFS Visit Order: A -> C -> B -> E -> G -> F -> D
 *
 * -------------------------------------------------------
 *
 * Task 2 - BFS Trace (source: A)
 *
 * Step 1: Enqueue A. Queue: [A]
 * Step 2: Dequeue A, enqueue neighbors C, B, D. Queue: [C, B, D]
 * Step 3: Dequeue C, A/B/D already marked. Queue: [B, D]
 * Step 4: Dequeue B, enqueue E, G. Queue: [D, E, G]
 * Step 5: Dequeue D, C/A already marked. Queue: [E, G]
 * Step 6: Dequeue E, enqueue F. Queue: [G, F]
 * Step 7: Dequeue G, F/B already marked. Queue: [F]
 * Step 8: Dequeue F, G/E already marked. Queue: []
 *
 * BFS Visit Order: A -> C -> B -> D -> E -> G -> F
 *
 * -------------------------------------------------------
 *
 * Task 4 - Dijkstra Shortest Path (Edinburgh to Dundee)
 *
 * Roads: Edinburgh-Stirling(50), Edinburgh-Glasgow(70),
 *        Edinburgh-Perth(100), Stirling-Glasgow(50),
 *        Stirling-Perth(40), Perth-Dundee(60)
 *
 * Step 1: Start Edinburgh(0). Update Stirling=50, Glasgow=70, Perth=100
 * Step 2: Extract Stirling(50). Update Perth=90 (50+40 < 100)
 * Step 3: Extract Glasgow(70). No updates.
 * Step 4: Extract Perth(90). Update Dundee=150 (90+60)
 * Step 5: Extract Dundee(150). Done.
 *
 * Shortest Path: Edinburgh -> Stirling -> Perth -> Dundee
 * Total Distance: 150 miles
 */import java.util.*;


public class Main {

    public static void main(String[] args) {
        Graph graph = buildGraph();

        System.out.println("=== Task 1: Depth First Search (source: A) ===");
        DepthFirstSearch dfs = new DepthFirstSearch(graph, "A");
        System.out.println("DFS Visit Order: " + dfs.getVisitOrder());

        System.out.println();
        System.out.println("=== Task 2: Breadth First Search (source: A) ===");
        BreadthFirstSearch bfs = new BreadthFirstSearch(graph, "A");
        System.out.println("BFS Visit Order: " + bfs.getVisitOrder());

        System.out.println();
        System.out.println("=== Task 3: Comparison ===");
        System.out.println("DFS result: " + dfs.getVisitOrder());
        System.out.println("BFS result: " + bfs.getVisitOrder());

        System.out.println();
        System.out.println("=== Task 4 & 5: Dijkstra - Shortest path from Edinburgh to Dundee ===");
        runDijkstra();
    }

    private static Graph buildGraph() {
        Graph g = new Graph();
        String[] vertices = {"A", "B", "C", "D", "E", "F", "G"};
        for (String v : vertices) {
            g.addVertex(v);
        }

        String[][] edges = {
                {"A", "C"}, {"A", "B"}, {"A", "D"},
                {"B", "A"}, {"B", "C"}, {"B", "E"}, {"B", "G"},
                {"C", "A"}, {"C", "B"}, {"C", "D"},
                {"D", "C"}, {"D", "A"},
                {"E", "G"}, {"E", "F"}, {"E", "B"},
                {"F", "G"}, {"F", "E"},
                {"G", "F"}, {"G", "B"}
        };

        for (String[] edge : edges) {
            g.addEdge(edge[0], edge[1]);
        }

        return g;
    }

    private static void runDijkstra() {
        List<String> cities = Arrays.asList("Edinburgh", "Stirling", "Glasgow", "Perth", "Dundee");

        Map<String, List<int[]>> roadGraph = new HashMap<>();
        for (String city : cities) {
            roadGraph.put(city, new ArrayList<>());
        }

        addRoad(roadGraph, cities, "Edinburgh", "Stirling", 50);
        addRoad(roadGraph, cities, "Edinburgh", "Glasgow", 70);
        addRoad(roadGraph, cities, "Stirling", "Glasgow", 50);
        addRoad(roadGraph, cities, "Stirling", "Perth", 40);
        addRoad(roadGraph, cities, "Edinburgh", "Perth", 100);
        addRoad(roadGraph, cities, "Perth", "Dundee", 60);

        Dijkstra dijkstra = new Dijkstra(roadGraph, cities, "Edinburgh");

        List<String> path = dijkstra.getPath("Dundee");
        int totalDistance = dijkstra.getDistance("Dundee");

        System.out.println("Shortest path from Edinburgh to Dundee:");
        System.out.println("Path: " + String.join(" -> ", path));
        System.out.println("Total distance: " + totalDistance + " miles");
    }

    private static void addRoad(Map<String, List<int[]>> graph, List<String> cities, String from, String to, int weight) {
        int fromIdx = cities.indexOf(to);
        int toIdx = cities.indexOf(from);
        graph.get(from).add(new int[]{fromIdx, weight});
        graph.get(to).add(new int[]{toIdx, weight});
    }
}
