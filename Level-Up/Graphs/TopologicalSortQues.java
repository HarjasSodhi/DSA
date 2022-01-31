import java.util.*;

public class TopologicalSortQues {

    // https://classroom.pepcoding.com/the-placement-program-pitampura-mar-14-2021/116/classvideos/6744
    // leetcode 207
    // with BFS
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] indegree = new int[numCourses];
        @SuppressWarnings({ "unchecked" })
        ArrayList<Integer>[] graph = new ArrayList[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] e : prerequisites) {
            indegree[e[0]]++;
            graph[e[1]].add(e[0]);
        }

        LinkedList<Integer> que = new LinkedList<>();
        for (int i = 0; i < numCourses; i++)
            if (indegree[i] == 0)
                que.addLast(i);

        while (que.size() != 0) {
            int popped = que.removeFirst();
            for (int e : graph[popped]) {
                indegree[e]--;
                if (indegree[e] == 0) {
                    que.addLast(e);
                }
            }
        }

        // checking for cycle
        // because if cycle exists , then indegree of all the edges of the cycle will
        // not be 0;
        for (int i = 0; i < numCourses; i++)
            if (indegree[i] != 0)
                return false;

        return true;
    }

    // with DFS
    // new technique
    // mark visited 1 while checking path in dfs
    // mark visited 2 when backtracking and path is complete
    // make call to visited 0 neighbours only
    // if you have neighbour that has visited 1, then that means you are on that
    // path and cycle exists ,so return false
    public boolean canFinish2(int numCourses, int[][] prerequisites) {
        @SuppressWarnings({ "unchecked" })
        ArrayList<Integer>[] graph = new ArrayList[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] e : prerequisites) {
            graph[e[1]].add(e[0]);
        }
        int[] vis = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (vis[i] == 0)
                if (!canFinishDFSHelper(i, graph, vis))
                    return false;
        }
        return true;
    }

    public boolean canFinishDFSHelper(int src, ArrayList<Integer>[] graph, int[] vis) {
        vis[src] = 1;
        for (Integer e : graph[src]) {
            if (vis[e] == 0) {
                if (!canFinishDFSHelper(e, graph, vis))
                    return false;
            } else if (vis[e] == 1) {
                return false;
            }
        }
        vis[src] = 2;
        return true;
    }

    // leetcode 210
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] indegree = new int[numCourses];
        int[] ans = new int[numCourses];
        int idx = 0;
        @SuppressWarnings({ "unchecked" })
        ArrayList<Integer>[] graph = new ArrayList[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] e : prerequisites) {
            indegree[e[0]]++;
            graph[e[1]].add(e[0]);
        }

        LinkedList<Integer> que = new LinkedList<>();
        for (int i = 0; i < numCourses; i++)
            if (indegree[i] == 0)
                que.addLast(i);

        while (que.size() != 0) {
            int popped = que.removeFirst();
            ans[idx] = popped;
            idx++;
            for (int e : graph[popped]) {
                indegree[e]--;
                if (indegree[e] == 0) {
                    que.addLast(e);
                }
            }
        }

        for (int i = 0; i < numCourses; i++)
            if (indegree[i] != 0)
                return new int[0];

        return ans;
    }

    // leetcode 329
    public int longestIncreasingPath(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] indegree = new int[n][m];
        int[][] dirs = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int[] e : dirs) {
                    int newR = i + e[0];
                    int newC = j + e[1];
                    if (newR >= 0 && newC >= 0 && newC < m && newR < n && matrix[i][j] < matrix[newR][newC]) {
                        indegree[newR][newC]++;
                    }
                }
            }
        }
        LinkedList<Integer> que = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (indegree[i][j] == 0)
                    que.addLast(i * m + j);
            }
        }

        int level = 0;
        while (que.size() != 0) {
            int size = que.size();
            while (size-- > 0) {
                int popped = que.removeFirst();
                int oldR = popped / m;
                int oldC = popped % m;
                for (int[] e : dirs) {
                    int newR = oldR + e[0];
                    int newC = oldC + e[1];
                    if (newR >= 0 && newC >= 0 && newC < m && newR < n && matrix[oldR][oldC] < matrix[newR][newC]) {
                        indegree[newR][newC]--;
                        if (indegree[newR][newC] == 0) {
                            que.addLast(newR * m + newC);
                        }
                    }
                }
            }
            level++;
        }

        return level;
    }

}