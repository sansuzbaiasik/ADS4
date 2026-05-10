import java.util.*;

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
