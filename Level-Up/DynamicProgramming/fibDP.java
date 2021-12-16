public class fibDP {
    // steps to follow:-
    // Faith
    // TreeDiag
    // Code->Memoization
    // Observe
    // Tabulate
    // Optimise if Possible

    public static int fibRec(int n) {
        if (n <= 1) {
            return n;
        }
        return fibRec(n - 1) + fibRec(n - 2);
    }

    public static int FibMemo(int[] dp, int n) {
        if (n <= 1) {
            dp[n] = n;
            return n;
        }
        if (dp[n] != 0)
            return dp[n];
        int recAns = FibMemo(dp, n - 1) + FibMemo(dp, n - 2);
        dp[n] = recAns;
        return recAns;
    }

    // we can observe that if we fill a array from left hand side then we can get
    // the ans iteratively
    // we need to make the array of size n+1 because we will need access to nth
    // index;

    public static int fibTabu(int n, int[] dp) {
        for (int i = 0; i < dp.length; i++) {
            if (i <= 1) {
                dp[i] = i;
                continue;
            }
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    public static int fibOpti(int n) {
        int a = 0;
        int b = 1;
        for (int i = 2; i < n + 1; i++) {
            int sum = a + b;
            a = b;
            b = sum;
        }
        return b;
    }

    public static void main(String[] args) {
        int n = 55;
        int[] dp = new int[n + 1];
        System.out.println(fibRec(n));
        System.out.println(FibMemo(dp, n));
        System.out.println(fibTabu(n, dp));
        System.out.println(fibOpti(n));
    }
}