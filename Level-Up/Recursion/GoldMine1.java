public class GoldMine1 {
    // https://practice.geeksforgeeks.org/problems/gold-mine-problem2608/1
    static int maxGold(int n, int m, int M[][]) {
        int[][] dirs = { { -1, 1 }, { 0, 1 }, { 1, 1 } };
        int maxGold = 0;
        for (int i = 0; i < n; i++) {
            maxGold = Math.max(maxGold, helper(i, 0, M, dirs, n, m));
        }
        return maxGold;
    }

    static int helper(int sr, int sc, int M[][], int[][] dirs, int n, int m) {
        int maxGold = 0;
        int val = M[sr][sc];
        M[sr][sc] = -1;
        for (int i = 0; i < dirs.length; i++) {
            int r = sr + dirs[i][0];
            int c = sc + dirs[i][1];
            if (r >= 0 && c >= 0 && r < n && c < m && M[r][c] != -1) {
                int recAns = helper(r, c, M, dirs, n, m);
                if (recAns > maxGold)
                    maxGold = recAns;
            }
        }
        M[sr][sc] = val;
        return maxGold + val;
    }
}
