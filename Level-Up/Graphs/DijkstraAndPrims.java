import java.util.*;

public class DijkstraAndPrims {

    public class Edge {
        int v;
        int wt;

        Edge(int v, int wt) {
            this.v = v;
            this.wt = wt;
        }
    }

    public void addEdge(int u, int v, int wt, ArrayList<Edge>[] arr) {
        arr[u].add(new Edge(v, wt));
    }

    public ArrayList<Edge>[] Dijkstra1(ArrayList<Edge>[] graph, int src) {
        int size = graph.length;
        @SuppressWarnings({ "unchecked" })
        ArrayList<Edge>[] myGraph = new ArrayList[size];
        for (int i = 0; i < size; i++) {
            myGraph[i] = new ArrayList<>();
        }

        class pair {
            int v = 0;
            int wsf = 0;
            int w = 0;
            int parent = 0;

            pair(int v, int wsf, int w, int parent) {
                this.v = v;
                this.wsf = wsf;
                this.w = w;
                this.parent = parent;
            }
        }

        PriorityQueue<pair> pq = new PriorityQueue<>((a, b) -> {
            return a.wsf - b.wsf;
        });

        boolean[] vis = new boolean[size];

        pq.add(new pair(src, 0, 0, -1));

        while (pq.size() != 0) {
            pair popped = pq.remove();
            if (vis[popped.v])
                continue;

            vis[popped.v] = true;
            if (popped.parent != -1)
                addEdge(popped.parent, popped.v, popped.w, myGraph);

            for (Edge nbr : graph[popped.v]) {
                if (!vis[nbr.v]) {
                    pq.add(new pair(nbr.v, popped.wsf + nbr.wt, nbr.wt, popped.v));
                }
            }
        }

        return myGraph;
    }

    public int[] Dijkstra2(ArrayList<Edge>[] graph, int src) {
        int size = graph.length;

        class pair {
            int v = 0;
            int wsf = 0;

            pair(int v, int wsf) {
                this.v = v;
                this.wsf = wsf;
            }
        }

        PriorityQueue<pair> pq = new PriorityQueue<>((a, b) -> {
            return a.wsf - b.wsf;
        });

        int[] dist = new int[size];
        Arrays.fill(dist, (int) 1e9);
        pq.add(new pair(src, 0));
        dist[src] = 0;

        while (pq.size() != 0) {
            pair popped = pq.remove();
            for (Edge nbr : graph[popped.v]) {
                if (popped.wsf + nbr.wt < dist[nbr.v]) {
                    pq.add(new pair(nbr.v, popped.wsf + nbr.wt));
                    dist[nbr.v] = popped.wsf + nbr.wt;
                }
            }
        }

        return dist;
    }

    public ArrayList<Edge>[] PrimsAlgo(ArrayList<Edge>[] graph, int src) {
        int size = graph.length;
        @SuppressWarnings({ "unchecked" })
        ArrayList<Edge>[] myGraph = new ArrayList[size];
        for (int i = 0; i < size; i++) {
            myGraph[i] = new ArrayList<>();
        }
        
        class pair {
            int v = 0;
            int wsf = 0;
            int w = 0;
            int parent = 0;

            pair(int v, int wsf, int w, int parent) {
                this.v = v;
                this.wsf = wsf;
                this.w = w;
                this.parent = parent;
            }
        }

        PriorityQueue<pair> pq = new PriorityQueue<>((a, b) -> {
            return a.w - b.w;
        });

        boolean[] vis = new boolean[size];

        pq.add(new pair(src, 0, 0, -1));

        while (pq.size() != 0) {
            pair popped = pq.remove();
            if (vis[popped.v])
                continue;

            vis[popped.v] = true;
            if (popped.parent != -1)
                addEdge(popped.parent, popped.v, popped.w, myGraph);

            for (Edge nbr : graph[popped.v]) {
                if (!vis[nbr.v]) {
                    pq.add(new pair(nbr.v, popped.wsf + nbr.wt, nbr.wt, popped.v));
                }
            }
        }

        return myGraph;
    }

    // leetcode 743
    public int networkDelayTime(int[][] times, int n, int k) {
        @SuppressWarnings({ "unchecked" })
        ArrayList<Edge>[] graph = new ArrayList[n + 1]; // n+1 size because the graph is 1 indexed
        for (int i = 0; i < n + 1; i++)
            graph[i] = new ArrayList<>();

        for (int i = 0; i < times.length; i++) {
            graph[times[i][0]].add(new Edge(times[i][1], times[i][2]));
        }

        int[] dist = Dijkstra2(graph, k);
        int max = 0;
        for (int i = 1; i < n + 1; i++) { // skip 0th index because 0th index node is not present and infinity will be
                                          // there by default
            max = Math.max(dist[i], max);
        }

        return max == (int) 1e9 ? -1 : max;
    }

}