public class Questions {

    // leetcode 746
    public static int stairs(int[] cost) {
        int[] dp = new int[cost.length];
        for (int i = 0; i < cost.length; i++) {
            if (i <= 1) {
                dp[i] = cost[i];
                continue;
            }
            dp[i] = Math.min(dp[i - 1], dp[i - 2]) + cost[i];
        }
        return Math.min(dp[dp.length - 1], dp[dp.length - 2]);
    }

    public static int boardPathMemo(int sp, int ep, int[] dp) {
        if (sp == ep) {
            return dp[sp] = 1;
        }
        if (dp[sp] != 0)
            return dp[sp];
        int count = 0;
        for (int i = 1; i <= 6 && sp + i <= ep; i++) {
            count += boardPathMemo(sp + i, ep, dp);
        }
        return dp[sp] = count;
    }

    public static int boardPathTabu(int sp, int ep) {
        int[] dp = new int[ep + 1];
        for (int i = ep; i >= sp; i--) {
            if (i == ep) {
                dp[i] = 1;
                continue;
            }
            int count = 0;
            for (int j = 1; j <= 6 && i + j <= ep; j++) {
                count += dp[i + j];
            }
            dp[i] = count;
        }
        return dp[sp];
    }

    public static int numDecoding(String str) {
        if (str.length() == 0) {
            return 1;
        }
        char ch = str.charAt(0);
        if (ch == '0')
            return 0;
        int count = 0;
        count += numDecoding(str.substring(1));
        if (str.length() > 1) {
            String s = str.substring(0, 2);
            int num = Integer.parseInt(s);
            if (num < 27) {
                count += numDecoding(str.substring(2));
            }
        }
        return count;
    }

    public static int numDecodingMemo(int idx, String str, int[] dp) {
        if (idx == str.length()) {
            return dp[idx] = 1;
        }
        if (dp[idx] != -1) {
            return dp[idx];
        }
        char ch = str.charAt(idx);
        if (ch == '0')
            return dp[idx] = 0;
        int count = 0;
        count += numDecodingMemo(idx + 1, str, dp);
        if (idx < str.length() - 1) {
            String s = str.substring(idx, idx + 2);
            int num = Integer.parseInt(s);
            if (num < 27) {
                count += numDecodingMemo(idx + 2, str, dp);
            }
        }
        return dp[idx] = count;
    }

    public static int numDecodingTabu(String str, int IDX) {
        int[] dp = new int[str.length() + 1];
        for (int i = dp.length; i >= 0; i--) {
            if (i == dp.length) {
                dp[i] = 1;
                continue;
            }
            char ch = str.charAt(i);
            if (ch == '0') {
                dp[i] = 0;
                continue;
            }
            int count = dp[i + 1];
            if (i < str.length() - 1) {
                String s = str.substring(i, i + 2);
                int num = Integer.parseInt(s);
                if (num < 27) {
                    count += dp[i + 2];
                }
            }
            dp[i] = count;
        }
        return dp[IDX];
    }

    public static int numDecodingOpti(String str) {
        int a = 1, b = 0;
        for (int i = str.length() - 1; i >= 0; i--) {
            int count = 0;
            char ch = str.charAt(i);
            if (ch != '0') {
                count += a;
                if (i < str.length() - 1) {
                    String s = str.substring(i, i + 2);
                    int num = Integer.parseInt(s);
                    if (num < 27) {
                        count += b;
                    }
                }
            }
            b = a;
            a = count;
        }
        return a;
    }

    public static int mod = (int) 1e9 + 7;

    public long numDecodings02_memo(String str, int idx, long[] dp) {
        if (idx == str.length()) {
            return dp[idx] = 1;
        }
        if (dp[idx] != -1) {
            return dp[idx];
        }
        char ch = str.charAt(idx);
        if (ch == '0')
            return dp[idx] = 0;
        long count = 0;
        if (ch == '*') {
            count = (count + 9 * numDecodings02_memo(str, idx + 1, dp)) % mod;
            if (idx < str.length() - 1) {
                char ch2 = str.charAt(idx + 1);
                if (ch2 == '*') {
                    count = (count + 15 * numDecodings02_memo(str, idx + 2, dp)) % mod;
                } else if (ch2 >= '0' && ch2 <= '6') {
                    count = (count + 2 * numDecodings02_memo(str, idx + 2, dp)) % mod;
                } else if (ch2 >= '7' && ch2 <= '9') {
                    count = (count + 1 * numDecodings02_memo(str, idx + 2, dp)) % mod;
                }
            }
        } else {
            count = (count + 1 * numDecodings02_memo(str, idx + 1, dp)) % mod;
            if (idx < str.length() - 1) {
                char ch2 = str.charAt(idx + 1);
                if (ch2 == '*' && ch == '1') {
                    count = (count + 9 * numDecodings02_memo(str, idx + 2, dp)) % mod;
                } else if (ch2 == '*' && ch == '2') {
                    count = (count + 6 * numDecodings02_memo(str, idx + 2, dp)) % mod;
                } else if (ch2 != '*') {
                    int num = (ch - '0') * 10 + (ch2 - '0');
                    if (num <= 26)
                        count = (count + 1 * numDecodings02_memo(str, idx + 2, dp)) % mod;
                }
            }
        }
        return dp[idx] = count;
    }

    static int maxGold(int n, int m, int M[][]) {
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, maxGoldHelper(i, 0, n, m, M));
        }
        return ans;
    }

    static int maxGoldHelper(int sr, int sc, int n, int m, int M[][]) {
        int gold = M[sr][sc];
        int count = 0;
        if (sr - 1 >= 0 && sc + 1 <= m - 1) {
            count = Math.max(count, maxGoldHelper(sr - 1, sc + 1, n, m, M));
        }

        if (sc + 1 <= m - 1) {
            count = Math.max(count, maxGoldHelper(sr, sc + 1, n, m, M));
        }

        if (sr + 1 <= n - 1 && sc + 1 <= m - 1) {
            count = Math.max(count, maxGoldHelper(sr + 1, sc + 1, n, m, M));
        }
        return count + gold;
    }

    static int maxGoldMemo(int n, int m, int M[][]) {
        int ans = 0;
        int[][] dp = new int[n][m];
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, maxGoldHelper(i, 0, n, m, M, dp));
        }
        return ans;
    }

    static int maxGoldHelper(int sr, int sc, int n, int m, int M[][], int[][] dp) {
        int gold = M[sr][sc];
        int count = 0;
        if (dp[sr][sc] != 0) {
            return dp[sr][sc];
        }
        if (sr - 1 >= 0 && sc + 1 <= m - 1) {
            count = Math.max(count, maxGoldHelper(sr - 1, sc + 1, n, m, M, dp));
        }

        if (sc + 1 <= m - 1) {
            count = Math.max(count, maxGoldHelper(sr, sc + 1, n, m, M, dp));
        }

        if (sr + 1 <= n - 1 && sc + 1 <= m - 1) {
            count = Math.max(count, maxGoldHelper(sr + 1, sc + 1, n, m, M, dp));
        }
        return dp[sr][sc] = count + gold;
    }

    static int maximumPath(int N, int Matrix[][]) {
        int ans = 0;
        int[][] dp = new int[N][N];
        for (int i = 0; i < N; i++) {
            ans = Math.max(ans, maximumPath(0, i, N, Matrix, dp));
        }
        return ans;
    }

    static int maximumPath(int sr, int sc, int n, int M[][], int[][] dp) {
        int val = M[sr][sc];
        int count = 0;
        if (dp[sr][sc] != 0) {
            return dp[sr][sc];
        }
        if (sr + 1 <= n - 1 && sc + 1 <= n - 1) {
            count = Math.max(count, maximumPath(sr + 1, sc + 1, n, M, dp));
        }

        if (sr + 1 <= n - 1) {
            count = Math.max(count, maximumPath(sr + 1, sc, n, M, dp));
        }

        if (sr + 1 <= n - 1 && sc - 1 >= 0) {
            count = Math.max(count, maximumPath(sr + 1, sc - 1, n, M, dp));
        }
        return dp[sr][sc] = count + val;
    }

    public static long countFriendsPairings_1(int n, int idx, long[] dp) {
        if (idx == n) {
            return dp[n] = 1;
        }
        if (dp[idx] != 0)
            return dp[idx];
        long count = 0;
        count = (count + countFriendsPairings_1(n, idx + 1, dp)) % (int) (10e9 + 7);

        if (idx < n - 1) {
            for (int i = idx + 1; i < n; i++) {
                count = (count + countFriendsPairings_1(n, idx + 2, dp)) % (int) (10e9 + 7);
            }
        }
        return dp[idx] = count;
    }

    public static long countFriendsPairings_2(int n, long[] dp) {
        if (n == 0) {
            return dp[n] = 1;
        }
        if (dp[n] != 0)
            return dp[n];
        long count = countFriendsPairings_2(n - 1, dp);
        count += n - 2 >= 0 ? countFriendsPairings_2(n - 2, dp) * (n - 1) : 0;
        return dp[n] = count % mod;
    }

    public static long countFriendsPairingsOpti(int n) {
        int a = 1, b = 1;
        for (int i = 2; i <= n; i++) {
            int sum = b + (a * (i - 1)) % mod;
            a = b;
            b = sum % mod;
        }
        return b;
    }

    public static long divideInKGroups(int n, int k, long[][] dp) {
        if (n == k || k == 1)
            return dp[n][k] = 1;
        if (dp[n][k] != -1) {
            return dp[n][k];
        }
        long count = divideInKGroups(n - 1, k - 1, dp);
        count = count + (divideInKGroups(n - 1, k, dp) * k);
        return dp[n][k] = count;
    }

    public static long divideInKGroupsTabu(int N, int K, long[][] dp) {
        for (int n = 1; n <= N; n++) {
            for (int k = 1; k <= K; k++) {
                if (n == k || k == 1) {
                    dp[n][k] = 1;
                    continue;
                }
                long count = dp[n - 1][k - 1];// divideInKGroups(n - 1, k - 1, dp);
                count = count + (dp[n - 1][k] * k);// (divideInKGroups(n - 1, k, dp) * k);
                return dp[n][k] = count;
            }
        }
        return dp[N][K];
    }

}