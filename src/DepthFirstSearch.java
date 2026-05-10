import java.util.*;

public class DepthFirstSearch {
    private final Map<String, Boolean> marked;
    private final List<String> visitOrder;

    public DepthFirstSearch(Graph g, String source) {
        marked = new LinkedHashMap<>();
        visitOrder = new ArrayList<>();
        for (String v : g.vertices()) {
            marked.put(v, false);
        }
        dfs(g, source);
    }

    private void dfs(Graph g, String v) {
        marked.put(v, true);
        visitOrder.add(v);
        System.out.println("Visited: " + v);
        for (String w : g.adj(v)) {
            if (!marked.getOrDefault(w, false)) {
                System.out.println("  Exploring edge " + v + " -> " + w);
                dfs(g, w);
            } else {
                System.out.println("  Edge " + v + " -> " + w + " already visited, skip");
            }
        }
    }

    public List<String> getVisitOrder() {
        return visitOrder;
    }
}
