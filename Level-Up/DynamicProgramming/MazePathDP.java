public class MazePathDP {

    public static int MazePathRec(int sr, int sc, int n, int m) {
        if (sr == n - 1 && sc == m - 1) {
            return 1;
        }
        int count = 0;
        if (sr + 1 < n)
            count += MazePathRec(sr + 1, sc, n, m);
        if (sc + 1 < m)
            count += MazePathRec(sr, sc + 1, n, m);
        if (sr + 1 < n && sc + 1 < m)
            count += MazePathRec(sr + 1, sc + 1, n, m);
        return count;
    }

    public static int MazePathMemo(int[][] dp, int sr, int sc, int n, int m) {
        if (sr == n - 1 && sc == m - 1) {
            dp[sr][sc] = 1;
            return 1;
        }
        if (dp[sr][sc] != 0) {
            return dp[sr][sc];
        }
        int count = 0;
        if (sr + 1 < n)
            count += MazePathMemo(dp, sr + 1, sc, n, m);
        if (sc + 1 < m)
            count += MazePathMemo(dp, sr, sc + 1, n, m);
        if (sr + 1 < n && sc + 1 < m)
            count += MazePathMemo(dp, sr + 1, sc + 1, n, m);
        dp[sr][sc] = count;
        return count;
    }

    // observation-start moving in reverse direction as each sell is dependent on
    // its cell in the forward direction
    public static int MazePathTabu(int[][] dp, int sr, int sc, int n, int m) {
        for (int i = n - 1; i >= sr; i--) {
            for (int j = m - 1; j >= sc; j--) {
                if (i == n - 1 && j == m - 1) {
                    dp[i][j] = 1;
                    continue;
                }
                int count = 0;
                if (i + 1 < n)
                    count += dp[i + 1][j];
                if (j + 1 < m)
                    count += dp[i][j + 1];
                if (i + 1 < n && j + 1 < m)
                    count += dp[i + 1][j + 1];
                dp[i][j] = count;
            }
        }
        return dp[sr][sc];
    }

    public static void main(String[] args) {
        int n = 2;
        int m = 2;
        int[][] dp = new int[n][m];
        System.out.println(MazePathRec(0, 0, n, m));
        System.out.println(MazePathMemo(dp, 0, 0, n, m));
        System.out.println(MazePathTabu(dp, 0, 0, n, m));
    }
}
