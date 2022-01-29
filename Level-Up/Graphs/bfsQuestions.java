import java.util.*;

public class bfsQuestions {

    // leetcode 994
    // example of multisource BFS
    public int orangesRotting(int[][] grid) {
        LinkedList<Integer> que = new LinkedList<>();
        int level = 0;
        int numOfOranges = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 2)
                    que.addLast(i * grid[0].length + j);
                else if (grid[i][j] == 1)
                    numOfOranges++;
            }
        }
        int[][] dirs = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
        while (que.size() != 0 && numOfOranges > 0) {
            int size = que.size();
            while (size-- > 0) {
                int rotten = que.removeFirst();
                int r = rotten / grid[0].length;
                int c = rotten % grid[0].length;

                for (int[] e : dirs) {
                    int newR = r + e[0];
                    int newC = c + e[1];
                    if (newR >= 0 && newC >= 0 && newR < grid.length && newC < grid[0].length
                            && grid[newR][newC] == 1) {
                        grid[newR][newC] = 2; // mark the new pos visited according to second type of BFS algo
                        que.addLast(newR * grid[0].length + newC);
                        numOfOranges--;
                    }
                }
            }
            level++;
        }
        return numOfOranges == 0 ? level : -1;
    }

    public void numEnclavesHelper(int r, int c, int[][] grid) {
        LinkedList<Integer> que = new LinkedList<>();
        que.addLast(r * grid[0].length + c);
        grid[r][c] = 0;
        int[][] dirs = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
        while (que.size() != 0) {
            int size = que.size();
            while (size-- > 0) {
                int removedEdge = que.removeFirst();
                int tempR = removedEdge / grid[0].length;
                int tempC = removedEdge % grid[0].length;
                for (int i = 0; i < 4; i++) {
                    int newR = tempR + dirs[i][0];
                    int newC = tempC + dirs[i][1];
                    if (newR >= 0 && newC >= 0 && newR < grid.length && newC < grid[0].length
                            && grid[newR][newC] == 1) {
                        que.addLast(newR * grid[0].length + newC);
                        grid[newR][newC] = 0;
                    }
                }
            }
        }
    }

    // leetcode 785
    // bipartite graph - A bipartite graph, also called a bigraph, is a set of graph
    // vertices decomposed into two disjoint sets such that no two graph vertices
    // within the same set are adjacent
    // https://classroom.pepcoding.com/the-placement-program-pitampura-mar-14-2021/116/classvideos/6690
    // cycle less graph is always bipartite
    // even len cycle graph is bipartite
    // odd cycle graph is not bipartite
    public boolean isBipartite(int[][] graph) {
        // -1->unvisited
        // 0-> visited with blue color
        // 1->visited with green color
        int[] visWithColor = new int[graph.length];
        Arrays.fill(visWithColor, -1); // mark all nodes unvisited
        boolean ans = true;
        for (int i = 0; i < graph.length; i++) { // we need a loop because, there might be multiple unconnected
                                                 // components in the graph
            if (visWithColor[i] == -1)
                ans = ans && isBipartiteHelper(i, visWithColor, graph);
        }
        return ans;
    }

    public boolean isBipartiteHelper(int src, int[] visWithColor, int[][] graph) {
        // in a even cycle, -> the repeated node in que, will be entered at the same
        // level/color
        // in a odd cycle, -> the repeated node in que, will be entered at the different
        // level/color
        // using this we can tell if the graph is biPartite or not
        int color = 0;
        LinkedList<Integer> que = new LinkedList<>();
        que.addFirst(src);
        while (que.size() != 0) {
            int size = que.size();
            while (size-- > 0) {
                int edge = que.removeFirst();
                if (visWithColor[edge] != -1) { // indicated node is already visited
                    if (color != visWithColor[edge]) {// check if the color of prev visit is same as color of curr visit
                        return false;// if not return true
                    }
                    continue;
                }
                visWithColor[edge] = color; // mark visited with current color

                for (int i : graph[edge]) {
                    if (visWithColor[i] == -1)
                        que.addLast(i);
                }
            }
            color = (color + 1) % 2; // toggle between colors-> 0 and 1
        }
        return true;
    }

    // leetcode 1020
    public int numEnclaves(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (i == 0 || j == 0 || i == grid.length - 1 || j == grid[0].length - 1)
                    if (grid[i][j] == 1)
                        numEnclavesHelper(i, j, grid);
            }
        }

        int ans = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1)
                    ans++;
            }
        }
        return ans;
    }

    // leetcode 542
    public int[][] updateMatrix(int[][] mat) {
        LinkedList<Integer> que = new LinkedList<>();
        int n = mat.length;
        int m = mat[0].length;
        boolean[][] vis = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (mat[i][j] == 0) {
                    que.addLast(i * m + j);
                    vis[i][j] = true;
                }
            }
        }

        int level = 0;
        int[][] dirs = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
        while (que.size() != 0) {
            int size = que.size();
            while (size-- > 0) {
                int popped = que.removeFirst();
                int oldR = popped / m;
                int oldC = popped % m;
                for (int[] e : dirs) {
                    int newR = oldR + e[0];
                    int newC = oldC + e[1];
                    if (newR >= 0 && newC >= 0 && newC < m && newR < n && !vis[newR][newC]) {
                        mat[newR][newC] = level + 1;
                        vis[newR][newC] = true;
                        que.addLast(newR * m + newC);
                    }
                }
            }
            level++;
        }

        return mat;
    }

}