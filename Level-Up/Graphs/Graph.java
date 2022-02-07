import java.util.*;

public class Graph {

    public class Edge {
        int v;
        int wt;

        Edge(int v, int wt) {
            this.v = v;
            this.wt = wt;
        }

        public String toString() {
            return this.v + " + " + this.wt;
        }
    }

    public void construct() {
        int size = 11;
        @SuppressWarnings({ "unchecked" })
        // taken from stackOverflow
        ArrayList<Edge>[] arr = new ArrayList[size];
        for (int i = 0; i < size; i++) {
            arr[i] = new ArrayList<>();
        }

        addEdge(0, 1, 2, arr);
        addEdge(1, 3, 4, arr);
        addEdge(1, 2, 3, arr);
        addEdge(3, 4, 5, arr);
        addEdge(2, 4, 7, arr);
        addEdge(4, 9, 8, arr);
        addEdge(4, 10, 10, arr);
        addEdge(9, 10, 8, arr);
        addEdge(4, 6, 11, arr);
        addEdge(5, 7, 3, arr);
        addEdge(6, 7, 3, arr);

        removeEdge(2, 4, arr);
        removeEdge(4, 5, arr);
        removeEdge(4, 10, arr);
        removeEdge(4, 9, arr);
    }

    public void display(ArrayList<Edge>[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(i + " -> { ");
            for (int j = 0; j < arr[i].size(); j++) {
                Edge temp = arr[i].get(j);
                if (j == arr[i].size() - 1) {
                    System.out.print("{ " + temp.v + ", " + temp.wt + " }");
                } else {
                    System.out.print("{ " + temp.v + ", " + temp.wt + " }, ");
                }
            }
            System.out.print("}");
            System.out.println();
        }
    }

    public void addEdge(int u, int v, int wt, ArrayList<Edge>[] arr) {
        arr[u].add(new Edge(v, wt));

        arr[v].add(new Edge(u, wt)); // not done in directed graph
    }

    public int findEdge(int u, int v, ArrayList<Edge>[] arr) {
        for (int i = 0; i < arr[u].size(); i++) {
            Edge temp = arr[u].get(i);
            if (temp.v == v) {
                return i;
            }
        }
        return -1;
    }

    public void removeEdge(int u, int v, ArrayList<Edge>[] arr) {
        int idxV = findEdge(u, v, arr);
        arr[u].remove(idxV);

        int idxU = findEdge(v, u, arr); // not done in directed graph
        arr[v].remove(idxU); // not done in directed graph
    }

    public boolean hasPath(int src, int dest, ArrayList<Edge>[] arr) {
        boolean[] vis = new boolean[arr.length];
        return hasPathHelper(src, dest, arr, vis);
    }

    private boolean hasPathHelper(int src, int dest, ArrayList<Edge>[] arr, boolean[] vis) {
        if (src == dest)
            return true;
        vis[src] = true;
        for (int i = 0; i < arr[src].size(); i++) {
            Edge temp = arr[src].get(i);
            if (!vis[temp.v] && hasPathHelper(temp.v, dest, arr, vis)) {
                return true;
            }
        }
        // vis[src] = false;
        // not needed as wherever i come from ,ans will not change
        return false;
    }

    public int countPaths(int src, int dest, ArrayList<Edge>[] arr) {
        boolean[] vis = new boolean[arr.length];
        return countPathsHelper(src, dest, arr, vis);
    }

    private int countPathsHelper(int src, int dest, ArrayList<Edge>[] arr, boolean[] vis) {
        if (src == dest)
            return 1;
        vis[src] = true;
        int ans = 0;
        for (int i = 0; i < arr[src].size(); i++) {
            Edge temp = arr[src].get(i);
            if (!vis[temp.v]) {
                ans += countPathsHelper(temp.v, dest, arr, vis);
            }
        }
        vis[src] = false;
        return ans;
    }

    public class wtPair {
        String psf;
        int wsf;

        wtPair(String psf, int wsf) {
            this.psf = psf;
            this.wsf = wsf;
        }
    }

    public wtPair maxWeight(int src, int dest, ArrayList<Edge>[] arr) {
        boolean[] vis = new boolean[arr.length];
        return maxWeightHelper(src, dest, arr, vis);
    }

    private wtPair maxWeightHelper(int src, int dest, ArrayList<Edge>[] arr, boolean[] vis) {
        if (src == dest)
            return new wtPair(dest + "", 0);
        wtPair pair = new wtPair("", -1);
        vis[src] = true;
        for (Edge e : arr[src]) {
            if (!vis[e.v]) {
                wtPair temp = maxWeightHelper(e.v, dest, arr, vis);
                if (temp.wsf > -1 && pair.wsf < temp.wsf + e.wt) {
                    pair.wsf = temp.wsf + e.wt;
                    pair.psf = src + temp.psf;
                }
            }
        }
        vis[src] = false;
        return pair;
    }

    public wtPair minWeight(int src, int dest, ArrayList<Edge>[] arr) {
        boolean[] vis = new boolean[arr.length];
        return minWeightHelper(src, dest, arr, vis);
    }

    private wtPair minWeightHelper(int src, int dest, ArrayList<Edge>[] arr, boolean[] vis) {
        if (src == dest)
            return new wtPair(dest + "", 0);
        // return new pair
        wtPair pair = new wtPair("", Integer.MAX_VALUE);
        vis[src] = true;
        for (Edge e : arr[src]) {
            if (!vis[e.v]) {
                wtPair temp = maxWeightHelper(e.v, dest, arr, vis);
                if (temp.wsf < Integer.MAX_VALUE && pair.wsf > temp.wsf + e.wt) {
                    pair.wsf = temp.wsf + e.wt;
                    // add edge weigth and the weight returned
                    pair.psf = src + temp.psf;
                }
            }
        }
        vis[src] = false;
        return pair;
    }

    public ArrayList<ArrayList<Integer>> getConnectedComponents(ArrayList<Edge>[] arr) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        boolean[] vis = new boolean[arr.length];
        for (int i = 0; i < arr.length; i++) {
            if (vis[i])
                continue;
            ArrayList<Integer> temp = new ArrayList<>();
            getConnectedComponentsHelper(i, arr, vis, temp);
            ans.add(temp);
        }
        return ans;
    }

    public void getConnectedComponentsHelper(int src, ArrayList<Edge>[] arr, boolean[] vis, ArrayList<Integer> temp) {
        vis[src] = true;
        temp.add(src);
        for (Edge e : arr[src]) {
            if (!vis[e.v]) {
                getConnectedComponentsHelper(e.v, arr, vis, temp);
            }
        }
    }

    // a linear ordering of its vertices such that for every directed edge uv from
    // vertex u to vertex v, u comes before v in the ordering
    // basically postorder of the graph
    // not possible in cyclic graphs
    // also not possible in undireceted graph as the direction of edges is not known
    // cannot detect cycle on its own
    public void topoLogicalSorting1(ArrayList<Edge>[] arr) {
        int size = arr.length;
        ArrayList<Integer> list = new ArrayList<>();
        boolean[] vis = new boolean[size];

        for (int i = 0; i < size; i++) {
            if (!vis[i])
                topoLogicalDFSHelper(i, vis, arr, list);
        }

        for (int i = list.size() - 1; i >= 0; i--) {
            System.out.print(list.get(i) + " ");
        }
    }

    public void topoLogicalDFSHelper(int src, boolean[] vis, ArrayList<Edge>[] arr, ArrayList<Integer> list) {
        vis[src] = true;
        for (Edge e : arr[src]) {
            if (!vis[e.v]) {
                topoLogicalDFSHelper(e.v, vis, arr, list);
            }
        }
        list.add(src);
    }

    // Topological sort 2
    // done with BFS
    // called Kahn's Algo
    // can detect cycle as all elements with indegree > 0 after bfs is over are part
    // of a cycle.
    public void topoLogicalSorting2(ArrayList<Edge>[] arr) {
        int size = arr.length;
        ArrayList<Integer> list = new ArrayList<>();
        int[] Indegree = new int[size];

        for (ArrayList<Edge> allEdges : arr) {
            for (Edge e : allEdges) {
                Indegree[e.v]++;
            }
        }

        LinkedList<Integer> que = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            if (Indegree[i] == 0) {
                que.addLast(i);
            }
        }

        while (que.size() != 0) {
            int queSize = 0;
            while (queSize-- > 0) { // a level wise list can also be contructed
                int popped = que.removeFirst();
                list.add(popped);
                for (Edge e : arr[popped]) {
                    Indegree[e.v]--;
                    if (Indegree[e.v] == 0) {
                        que.addLast(e.v);
                    }
                }
            }
        }

        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");
        }
    }

    // in this BFS, we mark node visited,after it is rmeoved from the que,
    // this BFS helps in cycle detection
    public static void BFS1(int src, ArrayList<Edge>[] arr) {
        LinkedList<Integer> que = new LinkedList<>();
        boolean[] vis = new boolean[arr.length];
        que.addLast(src);
        int level = 0;
        boolean cycleExists = false;
        while (que.size() != 0) {
            int size = que.size();
            while (size-- > 0) {
                int temp = que.removeFirst();
                if (vis[temp]) {
                    cycleExists = true;
                    continue;
                }
                vis[temp] = true;
                for (Edge e : arr[temp]) {
                    if (!vis[e.v])
                        que.addLast(e.v);
                }
            }
            System.out.println(level);
            level++;
        }
        if (cycleExists) {
            System.out.println("Cycle Exists");
        }
    }

    // in this BFS,we mark node visited when it is added in the que,
    // this avoids redundant calls that are resulted due to a cycle
    // gives us shortest path between src node and other nodes
    public static void BFS2(int src, ArrayList<Edge>[] arr) {
        LinkedList<Integer> que = new LinkedList<>();
        int level = 0;
        boolean[] vis = new boolean[arr.length];
        que.addLast(src);
        vis[src] = true;
        while (que.size() != 0) {
            int size = que.size();
            while (size-- > 0) {
                int temp = que.removeFirst();
                for (Edge e : arr[temp]) {
                    if (!vis[e.v]) {
                        vis[e.v] = true;
                        que.addLast(e.v);
                    }
                }
            }
            System.out.println(level);
            level++;
        }
    }

    // minimun spannig tree
    // number of vertices=no of edges-1;
    // kruskal's algo
    public ArrayList<Edge>[] MSTKruskals(int n, int[][] edges) {
        int[] parent = new int[n];
        Arrays.sort(edges, (int[] a, int[] b) -> {
            return a[2] - b[2];
        });

        @SuppressWarnings({ "unchecked" })
        ArrayList<Edge>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            int p1 = findParent(edge[0], parent);
            int p2 = findParent(edge[1], parent);
            if (p1 != p2) {
                parent[p2] = p1;
                graph[edge[0]].add(new Edge(edge[1], edge[2]));
            }
        }
        return graph;
    }

    public int findParent(int u, int[] parent) {
        if (parent[u] == u)
            return u;
        return parent[u] = findParent(parent[u], parent);
    }

}