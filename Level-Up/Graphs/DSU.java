import java.util.*;

public class DSU {
    // disjoint union set
    // union find
    // https://classroom.pepcoding.com/the-placement-program-pitampura-mar-14-2021/116/classvideos/6744
    // last one hour of video
    public int[] parent;
    public int[] size;

    public int DSUImplementation(int[][] edges) {
        int n = edges.length;
        parent = new int[n];
        size = new int[n];

        // initialise the arrays according to algo
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }

        boolean ContainsCycle = false;

        // create edges
        for (int[] e : edges) {
            int p1 = findParent(e[0]);
            int p2 = findParent(e[1]);

            if (p1 == p2) {
                ContainsCycle = true;
                continue;
            }

            mergeParent(p1, p2);
        }

        int gcc = 0; // find connected components
        for (int i = 0; i < n; i++) {
            findParent(i); // to make sure absolute parents are set correctly
            if (parent[i] == i)
                gcc++;
        }

        if (ContainsCycle)
            System.out.println("contains cycle");

        return gcc;
    }

    // path compression
    // this is the most important function
    // time complexity is based on inverse ackermann function
    // without this function the complexity of each element could be o(n) which will
    // make the entire function o(n2)
    // but with path complression and deciding the parent change on the bases of
    // size, the complexity of this function is
    // o(alpha(n));
    // where alpha is the inverse ackermann function
    // this function is really fast and should be used whenever needed
    public int findParent(int u) {
        if (parent[u] == u)
            return u; // return absolute parent
        // update parent
        return parent[u] = findParent(parent[u]);
    }

    public void mergeParent(int p1, int p2) {
        // decide which element's parent to update
        if (size[p1] < size[p2]) {
            parent[p1] = p2; // change parent
            size[p2] += size[p1]; // update size due to parent change
        } else {
            parent[p2] = p1;
            size[p1] += size[p2];
        }
    }

    // =================================================================================================================

    // leetcode 684
    public int[] findRedundantConnection(int[][] edges) {
        int[] ans = new int[2];
        int n = edges.length;
        parent = new int[n + 1]; // n+1 size because 1 based indexing
        size = new int[n + 1]; // n+1 size because 1 based indexing

        // initialise the arrays according to algo
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }

        // create edges
        for (int[] e : edges) {
            int p1 = findParent(e[0]);
            int p2 = findParent(e[1]);

            if (p1 == p2) {
                ans[0] = e[0];
                ans[1] = e[1];
            }

            mergeParent(p1, p2);
        }
        return ans;
    }

    // leetcode 1319
    public int makeConnected(int n, int[][] edges) {
        parent = new int[n];
        size = new int[n];

        // initialise the arrays according to algo
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }

        int Cycle = 0;

        // create edges
        for (int[] e : edges) {
            int p1 = findParent(e[0]);
            int p2 = findParent(e[1]);

            if (p1 == p2) {
                Cycle++;
                continue;
            }

            mergeParent(p1, p2);
        }

        int gcc = 0; // find connected components
        for (int i = 0; i < n; i++) {
            findParent(i); // to make sure absolute parents are set correctly
            if (parent[i] == i)
                gcc++;
        }
        if (gcc - 1 <= Cycle)
            return Math.min(Cycle, gcc - 1);

        return -1;
    }

    // leetcode 695
    public int maxAreaOfIsland(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        int[][] parent = new int[n][m];
        int[][] size = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    parent[i][j] = i * m + j;
                    size[i][j] = 1;
                }
            }
        }

        int[][] dirs = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    for (int[] e : dirs) {
                        int newR = i + e[0];
                        int newC = j + e[1];
                        if (newR >= 0 && newC >= 0 && newR < n && newC < m && grid[newR][newC] == 1) {
                            int p1 = findGridParent(i * m + j, parent, m);
                            int p2 = findGridParent(newR * m + newC, parent, m);
                            if (p1 == p2)
                                continue;
                            mergeGridParent(p1, p2, parent, size, m);
                        }
                    }
                }
            }
        }

        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                max = Math.max(max, size[i][j]);
            }
        }
        return max;
    }

    public int findGridParent(int idx, int[][] parent, int m) {
        int r = idx / m;
        int c = idx % m;
        if (parent[r][c] == idx) {
            return idx;
        }
        return parent[r][c] = findGridParent(parent[r][c], parent, m);
    }

    public void mergeGridParent(int p1, int p2, int[][] parent, int[][] size, int m) {
        int r1 = p1 / m;
        int c1 = p1 % m;
        int r2 = p2 / m;
        int c2 = p2 % m;
        if (size[r1][c1] < size[r2][c2]) {
            parent[r1][c1] = p2;
            size[r2][c2] += size[r1][c1];
        } else {
            parent[r2][c2] = p1;
            size[r1][c1] += size[r2][c2];
        }
    }

    // lintcode 434
    public class Point {
        int x = 0;
        int y = 0;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public List<Integer> numIslands2(int n, int m, Point[] operators) {
        int ans = 0;
        int[] parent = new int[n * m];
        for (int i = 0; i < n * m; i++)
            parent[i] = i;

        List<Integer> list = new ArrayList<>();

        int[][] grid = new int[n][m];

        int[][] dirs = { { 1, 0 }, { 0, 1 }, { 0, -1 }, { -1, 0 } };

        for (int j = 0; j < operators.length; j++) {
            Point operator = operators[j];
            if (grid[operator.x][operator.y] == 1) {
                list.add(ans);
                continue;
            }
            grid[operator.x][operator.y] = 1;
            for (int i = 0; i < 4; i++) {
                int newR = operator.x + dirs[i][0];
                int newC = operator.y + dirs[i][1];
                if (newR >= 0 && newC >= 0 && newR < n && newC < m && grid[newR][newC] == 1) {
                    int p1 = findParent(newR * m + newC);
                    int p2 = findParent(operator.x * m + operator.y);
                    if (p1 != p2) {
                        parent[p2] = p1;
                        ans--;
                    }
                }
            }
            ans++;
            list.add(ans);
        }
        return list;
    }

    // space complexity better
    public List<Integer> numIslands2Better(int n, int m, Point[] operators) {
        if (operators == null)
            return new ArrayList<>();
        int ans = 0;
        parent = new int[n * m];
        Arrays.fill(parent, -1);

        List<Integer> list = new ArrayList<>();

        int[][] dirs = { { 1, 0 }, { 0, 1 }, { 0, -1 }, { -1, 0 } };
        for (int j = 0; j < operators.length; j++) {
            Point operator = operators[j];
            if (parent[operator.x * m + operator.y] != -1) {
                list.add(ans);
                continue;
            }
            parent[operator.x * m + operator.y] = operator.x * m + operator.y;
            for (int i = 0; i < 4; i++) {
                int newR = operator.x + dirs[i][0];
                int newC = operator.y + dirs[i][1];
                if (newR >= 0 && newC >= 0 && newR < n && newC < m && parent[newR * m + newC] != -1) {
                    int p1 = findParent(newR * m + newC);
                    int p2 = findParent(operator.x * m + operator.y);
                    if (p1 != p2) {
                        parent[p2] = p1;
                        ans--;
                    }
                }
            }
            ans++;
            list.add(ans);
        }
        return list;
    }

    // https://www.codingninjas.com/codestudio/problems/smallest-equivalent-string_1381859
    public String smallestString(String s, String t, String str) {
        int[] ch = new int[26];
        for (int i = 0; i < 26; i++) {
            ch[i] = i;
        }
        for (int i = 0; i < s.length(); i++) {
            int p1 = findCharParent(s.charAt(i) - 'a', ch);
            int p2 = findCharParent(t.charAt(i) - 'a', ch);
            mergeCharParent(p1, p2, ch);
        }

        StringBuilder sb = new StringBuilder();
        for (char c : str.toCharArray()) {
            int p = findCharParent(c - 'a', ch);
            sb.append((char) (p + (int) 'a'));
        }

        return sb.toString();
    }

    public int findCharParent(int c, int[] ch) {
        if (ch[c] == c) {
            return c;
        }
        return ch[c] = findCharParent(ch[c], ch);
    }

    public void mergeCharParent(int p1, int p2, int[] ch) {
        if (p1 < p2) {
            ch[p2] = p1;
        } else {
            ch[p1] = p2;
        }
    }

    // leetcode 990
    public boolean equationsPossible(String[] equations) {
        parent = new int[26];
        for (int i = 0; i < 26; i++) {
            parent[i] = i; // initialise union find
        }
        for (String s : equations) {
            if (s.charAt(1) == '=') { // make connections first
                int p1 = findParent(s.charAt(0) - 'a');
                int p2 = findParent(s.charAt(3) - 'a');
                if (p1 != p2)
                    parent[p1] = p2;
            }
        }
        for (String s : equations) {
            if (s.charAt(1) == '!') { // check if connections are valid
                int p1 = findParent(s.charAt(0) - 'a');
                int p2 = findParent(s.charAt(3) - 'a');
                if (p1 == p2) // if parent of two components that are not supposed to be connected is same
                              // then return false
                    return false;
            }
        }
        return true;
    }

    // leetcode 1267
    public int countServers(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        int[][] parent = new int[n][m];
        int[][] size = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    parent[i][j] = i * m + j;
                    size[i][j] = 1;
                }
            }
        }

        int[][] dirs = { { 1, 0 }, { 0, 1 } };

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    int k = 0;
                    int newR = i;
                    int newC = j;
                    int p1 = findGridParent(i * m + j, parent, m);
                    while (k < 2) {
                        newR = dirs[k][0] + newR;
                        newC = dirs[k][1] + newC;

                        if (newR >= n || newC >= m) {
                            k++;
                            newR = i;
                            newC = j;
                        } else if (grid[newR][newC] == 1) {
                            int p2 = findGridParent(newR * m + newC, parent, m);
                            if (p1 != p2) {
                                parent[p2 / m][p2 % m] = p1;
                                size[p1 / m][p1 % m] += size[p2 / m][p2 % m];
                            }
                        }
                    }
                }
            }
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (size[i][j] > 1) {
                    int p3 = findGridParent(i * m + j, parent, m);
                    int r = p3 / m;
                    int c = p3 % m;
                    if (i == r && j == c) {
                        ans += size[r][c];
                    }
                }
            }
        }

        return ans;
    }

    // leetcode 1202
    // https://classroom.pepcoding.com/the-placement-program-pitampura-mar-14-2021/116/classvideos/6745#
    // last one hour
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        parent = new int[s.length()];
        size = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            parent[i] = i;
            size[i] = 1;
        }

        for (int i = 0; i < pairs.size(); i++) {
            int p1 = findParent(pairs.get(i).get(0));
            int p2 = findParent(pairs.get(i).get(1));
            if (p1 != p2) {
                mergeParent(p1, p2);
            }
        }

        HashMap<Integer, String> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            int p = findParent(i);
            if (map.containsKey(p)) {
                map.put(p, map.get(p) + s.charAt(i));
            } else {
                map.put(p, s.charAt(i) + "");
            }
        }

        for (Integer e : map.keySet()) {
            char[] chars = map.get(e).toCharArray();
            Arrays.sort(chars);
            String sorted = new String(chars);
            map.put(e, sorted);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            int p = findParent(i);
            sb.append(map.get(p).charAt(0));
            map.put(p, map.get(p).substring(1));
        }

        return sb.toString();
    }

    // https://classroom.pepcoding.com/the-placement-program-pitampura-mar-14-2021/116/classvideos/6756
    // leetcode 721
    // public List<List<String>> accountsMerge(List<List<String>> accounts) {

    // }

    // leetcode 839
    // public int numSimilarGroups(String[] strs) {
    
    // }

    // https://www.pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/graphs/optimize-water-distribution-official/ojquestion
    public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
        ArrayList<ArrayList<Integer>> newPipes = new ArrayList<>();
        for (int[] pipe : pipes) {
            ArrayList<Integer> temp = new ArrayList<>();
            for (int e : pipe)
                temp.add(e);
            newPipes.add(temp);
        }

        int ans = 0;
        parent = new int[n + 1];
        for (int i = 0; i < wells.length; i++) {
            ArrayList<Integer> temp = new ArrayList<>();
            temp.add(0);
            temp.add(i + 1);
            temp.add(wells[i]);
            newPipes.add(temp);
        }
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }
        Collections.sort(newPipes, (ArrayList<Integer> a, ArrayList<Integer> b) -> {
            return a.get(2) - b.get(2);
        });

        for (ArrayList<Integer> temp : newPipes) {
            int p1 = findParent(temp.get(0));
            int p2 = findParent(temp.get(1));
            if (p1 != p2) {
                ans += temp.get(2);
                parent[p2] = p1;
            }
        }

        return ans;
    }

    // leetcode 1584
    public int minCostConnectPoints(int[][] points) {
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        int ans = 0;

        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                ArrayList<Integer> temp = new ArrayList<>();
                temp.add(i);
                temp.add(j);
                temp.add(Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]));
                list.add(temp);
            }
        }

        Collections.sort(list, (ArrayList<Integer> a, ArrayList<Integer> b) -> {
            return a.get(2) - b.get(2);
        });

        int[] parent = new int[points.length + 1];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }

        for (ArrayList<Integer> temp : list) {
            int p1 = findParent(temp.get(0));
            int p2 = findParent(temp.get(1));
            if (p1 != p2) {
                ans += temp.get(2);
                parent[p2] = p1;
            }
        }

        return ans;
    }

    // leetcode 924
    // https://classroom.pepcoding.com/the-placement-program-pitampura-mar-14-2021/116/classvideos/6786
    public int minMalwareSpread(int[][] graph, int[] initial) {
        int n = graph.length;
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (graph[i][j] == 1) {
                    int p1 = findParent(i);
                    int p2 = findParent(j);
                    mergeParent(p1, p2);
                }
            }
        }

        Arrays.sort(initial);
        int[] freq = new int[n]; // number of edges
        for (int e : initial) {
            int p = findParent(e);
            freq[p]++;
        }

        int ans = -1;
        int max = 0;
        for (int i = 0; i < initial.length; i++) {
            int p = findParent(initial[i]);
            if (freq[p] > 1)
                continue;
            if (size[p] > max) {
                ans = initial[i];
                max = size[p];
            }
        }

        return ans == -1 ? initial[0] : ans;
    }

}