import java.util.*;

public class BreadthFirstSearch {
    private final Map<String, Boolean> marked;
    private final List<String> visitOrder;

    public BreadthFirstSearch(Graph g, String source) {
        marked = new LinkedHashMap<>();
        visitOrder = new ArrayList<>();
        for (String v : g.vertices()) {
            marked.put(v, false);
        }
        bfs(g, source);
    }

    private void bfs(Graph g, String source) {
        Queue<String> queue = new LinkedList<>();
        marked.put(source, true);
        queue.add(source);
        System.out.println("Enqueue: " + source);

        while (!queue.isEmpty()) {
            String v = queue.poll();
            visitOrder.add(v);
            System.out.println("Visited: " + v);

            for (String w : g.adj(v)) {
                if (!marked.getOrDefault(w, false)) {
                    marked.put(w, true);
                    queue.add(w);
                    System.out.println("  Enqueue: " + w);
                } else {
                    System.out.println("  Node " + w + " already marked, skip");
                }
            }
        }
    }

    public List<String> getVisitOrder() {
        return visitOrder;
    }
}
