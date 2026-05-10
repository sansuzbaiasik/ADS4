import java.util.*;

public class Graph {
    private final int V;
    private final Map<String, List<String>> adj;
    private final List<String> vertices;

    public Graph() {
        adj = new LinkedHashMap<>();
        vertices = new ArrayList<>();
        V = 0;
    }

    public void addVertex(String v) {
        if (!adj.containsKey(v)) {
            adj.put(v, new ArrayList<>());
            vertices.add(v);
        }
    }

    public void addEdge(String v, String w) {
        adj.get(v).add(w);
    }

    public List<String> adj(String v) {
        return adj.getOrDefault(v, new ArrayList<>());
    }

    public List<String> vertices() {
        return vertices;
    }
}
