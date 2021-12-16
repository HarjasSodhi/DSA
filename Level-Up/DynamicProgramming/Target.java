import java.util.*;

public class Target {

    public static int permutation(int[] arr, int target, int[] dp) {
        if (target == 0) {
            return dp[target] = 1;
        }

        if (dp[target] != -1) {
            return dp[target];
        }

        int count = 0;

        for (int i = 0; i < arr.length; i++) {
            if (target - arr[i] >= 0) {
                count += permutation(arr, target - arr[i], dp);
            }
        }
        return dp[target] = count;
    }

    public static int combination(int[] arr, int target, int n, int[][] dp) {
        if (target == 0) {
            return dp[n][target] = 1;
        }

        if (dp[n][target] != -1) {
            return dp[n][target];
        }

        int count = 0;

        for (int i = n; i > 0; i--) {
            if (target - arr[i - 1] >= 0) {
                count += combination(arr, target - arr[i - 1], i, dp);
            }
        }
        return dp[n][target] = count;
    }

    // https://classroom.pepcoding.com/the-placement-program-pitampura-mar-14-2021/116/classvideos/5267/
    // why we can use 1d dp here explainantion
    // short ans= overwrite the same array
    // time complexity remains same
    // space complexity reduces to o(n)
    public static int combinationTabu(int[] arr, int target, int[] dp) {
        Arrays.fill(dp, 0);
        dp[0] = 1;
        for (int i = 0; i < arr.length; i++) {
            for (int j = arr[i]; j <= target; j++) {
                if (target - j >= 0) {
                    dp[j] += dp[j - arr[i]];
                }
            }
        }
        return dp[target];
    }

    public static int permutation_Tabu(int[] arr, int target, int[] dp) {
        dp[0] = 1;
        for (int i = 1; i <= target; i++) {
            if (target == 0) {
                return dp[target] = 1;
            }

            int count = 0;

            for (int j = 0; j < arr.length; j++) {
                if (i - arr[j] >= 0) {
                    count += dp[i - arr[j]];// we will subtract arr of j and not j because then we will make the i as
                                            // destination target and get the right ans;
                }
            }
            dp[i] = count;
        }
        return dp[target];
    }

    // -1 : not explored, 0 : false, 1 : true
    public static int targetSum(int[] arr, int n, int tar, int[][] dp) {
        if (n == 0 || tar == 0) {
            return dp[n][tar] = (tar == 0 ? 1 : 0);
        }

        if (dp[n][tar] != -1)
            return dp[n][tar];

        boolean res = false;
        if (tar - arr[n - 1] >= 0)
            res = res || targetSum(arr, n - 1, tar - arr[n - 1], dp) == 1;
        res = res || targetSum(arr, n - 1, tar, dp) == 1;

        return dp[n][tar] = res ? 1 : 0;
    }

    // =====================================================================================================

    public static boolean targetSum_DP(int[] arr, int N, int Tar, boolean[][] dp) {
        for (int n = 0; n <= N; n++) {
            for (int tar = 0; tar <= Tar; tar++) {
                if (n == 0 || tar == 0) {
                    dp[n][tar] = (tar == 0);
                    continue;
                }

                if (tar - arr[n - 1] >= 0)
                    dp[n][tar] = dp[n][tar] || dp[n - 1][tar - arr[n - 1]];
                dp[n][tar] = dp[n][tar] || dp[n - 1][tar];
            }
        }

        return dp[N][Tar];
    }

    // back Engineering
    public static int targetSum_path(int[] arr, int N, boolean[][] dp, int tar, String psf) {
        if (N == 0 || tar == 0) {
            if (tar == 0) {
                System.out.println(psf);
                return 1;
            }
            return 0;
        }

        int count = 0;
        if (tar - arr[N - 1] >= 0 && dp[N - 1][tar - arr[N - 1]])
            count += targetSum_path(arr, N - 1, dp, tar - arr[N - 1], psf + arr[N - 1] + " ");
        if (dp[N - 1][tar])
            count += targetSum_path(arr, N - 1, dp, tar, psf);

        return count;
    }

    public static int knapSack(int W, int wt[], int val[], int n, int[][] dp) {
        if (n == 0 || W == 0) {
            return dp[n][W] = 0;
        }

        if (dp[n][W] != -1)
            return dp[n][W];

        int maxAns = 0;
        if (W - wt[n - 1] >= 0)
            maxAns = Math.max(maxAns, knapSack(W - wt[n - 1], wt, val, n - 1, dp) + val[n - 1]);
        maxAns = Math.max(maxAns, knapSack(W, wt, val, n - 1, dp));

        return dp[n][W] = maxAns;
    }

    public static int unboundedKnapsack(int[] wt, int[] val, int bagWeight) {
        int[] dp = new int[bagWeight + 1];
        for (int weight = 0; weight <= bagWeight; weight++) {
            for (int i = 0; i < wt.length; i++) {
                if (weight - wt[i] >= 0)
                    dp[weight] = Math.max(dp[weight], dp[weight - wt[i]] + val[i]);
            }
        }

        return dp[bagWeight];
    }

    public int findTargetSumWays(int[] nums, int n, int sum, int target, int[][] dp) {
        if (n == 0) {
            return dp[n][sum] = target == sum ? 1 : 0;
        }

        if (dp[n][sum] != -1)
            return dp[n][sum];

        int count = 0;
        count += findTargetSumWays(nums, n - 1, sum + nums[n - 1], target, dp);
        count += findTargetSumWays(nums, n - 1, sum - nums[n - 1], target, dp);

        return dp[n][sum] = count;
    }

    // 494
    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0, n = nums.length;
        for (int ele : nums)
            sum += ele;
        if (target > sum || target < -sum)
            return 0;

        int[][] dp = new int[n + 1][2 * sum + 1];
        for (int[] d : dp)
            Arrays.fill(d, -1);

        return findTargetSumWays(nums, n, sum, sum + target, dp);
    }

    // 416
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int ele : nums)
            sum += ele;

        if (sum % 2 != 0)
            return false;
        int tar = sum / 2, n = nums.length;
        boolean[][] dp = new boolean[n + 1][tar + 1];
        return targetSum_DP(nums, n, tar, dp);
    }

    // 688
    int dx[] = { 2, 1, -1, -2, -2, -1, 1, 2 };
    int dy[] = { 1, 2, 2, 1, -1, -2, -2, -1 };
    public double knightProbability_(int n, int k, int row, int col, double[][][] dp) {
        if (k == 0)
            return dp[k][row][col] = 1.0;
        if (dp[k][row][col] != 0.0)
            return dp[k][row][col];

        double count = 0;
        for (int d = 0; d < 8; d++) {
            int r = row + dx[d];
            int c = col + dy[d];

            if (r >= 0 && c >= 0 && r < n && c < n) {
                count += knightProbability_(n, k - 1, r, c, dp);
            }
        }

        return dp[k][row][col] = count / 8.0;
    }

    public double knightProbability(int n, int k, int row, int column) {
        double[][][] dp = new double[k + 1][n + 1][n + 1];
        return knightProbability_(n, k, row, column, dp);
    }
    
    public static void main(String[] args) {
        int[] arr = { 2, 3, 5, 7 };
        int target = 10;
        int n = arr.length;
        int[][] dp = new int[n + 1][target + 1];
        for (int[] e : dp) {
            Arrays.fill(e, -1);
        }
        int[] dp1D = new int[target + 1];
        // System.out.println(combination(arr, target, n, dp));
        // Arrays.fill(dp1D, -1);
        // int a = permutation(arr, target, dp1D);
        // int b = permutation_Tabu(arr, target, dp1D);
        // System.out.println(a + " " + b);
        System.out.println(combinationTabu(arr, target, dp1D));
        System.out.println(combination(arr, target, n, dp));
        // for (int i = 0; i < dp.length; i++) {
        // for (int j = 0; j < dp[0].length; j++) {
        // System.out.print(dp[i][j] + "\t");
        // }
        // System.out.println();
        // }
        // for (int i = 0; i < dp1D.length; i++) {
        // System.out.println(dp1D[i]);
        // }
    }
}