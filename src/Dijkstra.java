import java.util.*;

public class Dijkstra {
    private final Map<String, Integer> dist;
    private final Map<String, String> prev;

    public Dijkstra(Map<String, List<int[]>> graph, List<String> vertices, String source) {
        dist = new HashMap<>();
        prev = new HashMap<>();

        for (String v : vertices) {
            dist.put(v, Integer.MAX_VALUE);
            prev.put(v, null);
        }
        dist.put(source, 0);

        PriorityQueue<String> pq = new PriorityQueue<>(Comparator.comparingInt(dist::get));
        pq.add(source);

        Set<String> visited = new HashSet<>();

        while (!pq.isEmpty()) {
            String u = pq.poll();
            if (visited.contains(u)) continue;
            visited.add(u);

            List<int[]> neighbors = graph.getOrDefault(u, new ArrayList<>());
            for (int[] neighbor : neighbors) {
                String v = vertices.get(neighbor[0]);
                int weight = neighbor[1];

                if (dist.get(u) != Integer.MAX_VALUE && dist.get(u) + weight < dist.get(v)) {
                    dist.put(v, dist.get(u) + weight);
                    prev.put(v, u);
                    pq.add(v);
                }
            }
        }
    }

    public List<String> getPath(String target) {
        List<String> path = new ArrayList<>();
        String current = target;
        while (current != null) {
            path.add(0, current);
            current = prev.get(current);
        }
        return path;
    }

    public int getDistance(String target) {
        return dist.getOrDefault(target, Integer.MAX_VALUE);
    }
}
