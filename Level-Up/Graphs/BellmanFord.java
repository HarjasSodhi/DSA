import java.util.*;

public class BellmanFord {

    // https://classroom.pepcoding.com/the-placement-program-pitampura-mar-14-2021/116/classvideos/6868
    // belmann ford- single source to all points of the graph in shortest distance
    // can detect -ve cycle
    // also takes account for the number of edges, as we increase them one by
    // one(see class);
    public int bellmanFord(int n, int[][] graph, int src, int dst) {
        int[] dis = new int[n];
        Arrays.fill(dis, (int) (1e8));
        dis[src] = 0;
        boolean containsNegativeCycle = false;

        for (int i = 1; i <= n; i++) { // we run the loop till equal to n beacause n-1 edges are enough to create a mst
                                       // and if updation is done on n=i then -ve cycle is present
            int[] nDis = new int[n];
            for (int j = 0; j < n; j++) {
                nDis[j] = dis[j];
            }
            boolean didUpdate = false;

            for (int[] edge : graph) {
                int u = edge[0];
                int v = edge[1];
                int wt = edge[2];

                if (dis[u] + wt < nDis[v]) {
                    nDis[v] = dis[u] + wt;
                    didUpdate = true;
                }
            }
            dis = nDis;

            if (i == n && didUpdate)
                containsNegativeCycle = true; // if updation is done when i==n then -ve cycle is present
            if (!didUpdate)
                break; // break the loop if answer did not update as it will not update in the future
                       // as well;
        }

        System.out.println(containsNegativeCycle);

        return dis[dst];
    }

    // leetcode 787
    // will not work with dijjkstra
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        int[] dis = new int[n];
        Arrays.fill(dis, (int) (1e8));
        dis[src] = 0;

        for (int i = 1; i <= k + 1; i++) {
            int[] nDis = new int[n];
            for (int j = 0; j < n; j++) {
                nDis[j] = dis[j];
            }

            for (int[] edge : flights) {
                int u = edge[0];
                int v = edge[1];
                int wt = edge[2];

                if (dis[u] + wt < nDis[v]) {
                    nDis[v] = dis[u] + wt;
                }
            }
            dis = nDis;
        }

        return dis[dst] == 1e8 ? -1 : dis[dst];
    }

}