public class BASS {

    // https://classroom.pepcoding.com/the-placement-program-pitampura-mar-14-2021/116/classvideos/6245

    // leetcode 121
    // [7,1,5,3,6,4]
    public int maxProfit(int[] prices) {
        int ans = 0;
        int n = prices.length;
        if (n <= 1)
            return 0;
        int sellptr = 1;
        int buyptr = 0;
        while (sellptr < n) {
            if (prices[sellptr] < prices[buyptr]) {
                buyptr = sellptr;
            } else {
                ans = Math.max(ans, prices[sellptr] - prices[buyptr]);
            }
            sellptr++;
        }
        return ans;
    }

    // another way
    public int maxProfit01(int[] prices) {
        int dpi0 = 0;
        int dpi1 = -(int) 1e9;
        for (int i = 0; i < prices.length; i++) {
            dpi0 = Math.max(dpi0, dpi1 + prices[i]);
            dpi1 = Math.max(dpi1, 0 - prices[i]);
        }
        return dpi0;
    }

    // leetcode 122
    public int maxProfit2(int[] prices) {
        int dpi0 = 0;
        int dpi1 = -(int) 1e9;
        for (int i = 0; i < prices.length; i++) {
            dpi1 = Math.max(dpi1, dpi0 - prices[i]); // buying stock from previous profit money
            dpi0 = Math.max(dpi0, dpi1 + prices[i]); // selling stock
        }
        return dpi0;
    }

    // leetcode 714
    public int maxProfit3(int[] prices, int fee) {
        int dpi0 = 0;
        int dpi1 = -(int) 1e9;
        for (int i = 0; i < prices.length; i++) {
            dpi0 = Math.max(dpi0, dpi1 + prices[i] - fee);
            dpi1 = Math.max(dpi1, dpi0 - prices[i]);
        }
        return dpi0;
    }

    // leetcode 309
    public int maxProfit4(int[] prices) {
        int dpi0 = 0;
        int dpi0Prev = 0;
        int dpi1 = -(int) 1e9;
        for (int i = 0; i < prices.length; i++) {
            int dp_i0 = dpi0;
            dpi0 = Math.max(dpi0, dpi1 + prices[i]);
            dpi1 = Math.max(dpi1, dpi0Prev - prices[i]);
            dpi0Prev = dp_i0;
        }
        return dpi0;
    }

    // leetcode 123
    public int maxProfit5(int[] prices) {
        int n = prices.length;
        int K = 2; // for k=2
        int[][][] dp = new int[n][K + 1][2];

        for (int i = 0; i < n; i++) {
            for (int k = 0; k < 3; k++) {
                for (int x = 0; x < 2; x++) {
                    if (k == 0) {
                        if (x == 0) {
                            dp[i][k][x] = 0;
                        } else {
                            dp[i][k][x] = -(int) 1e9;
                        }
                    } else {
                        if (i == 0) {
                            if (x == 0) {
                                dp[i][k][x] = 0;
                            } else {
                                // dp[i][k][x]=Math.max(-(int)1e9,0-prices[i]);
                                // or
                                dp[i][k][x] = 0 - prices[i]; // as on 0th day , when x=1 i have no option but to buy a
                                                             // stock
                            }
                        } else {
                            if (x == 0) {
                                dp[i][k][x] = Math.max(dp[i - 1][k][0], dp[i - 1][k][1] + prices[i]);
                            } else {
                                dp[i][k][x] = Math.max(dp[i - 1][k][1], dp[i - 1][k - 1][0] - prices[i]);
                            }
                        }
                    }
                }
            }
        }
        return dp[n - 1][K][0];
    }

    // o(1) space
    public int maxProfit5Optimised(int[] prices) {
        int n = prices.length;

        int dpi10 = 0;
        int dpi11 = -(int) 1e9;
        int dpi20 = 0;
        int dpi21 = -(int) 1e9;

        for (int i = 0; i < n; i++) {
            int p = prices[i];

            dpi20 = Math.max(dpi20, dpi21 + p);
            dpi21 = Math.max(dpi21, dpi10 - p);
            dpi10 = Math.max(dpi10, dpi11 + p);
            dpi11 = Math.max(dpi11, 0 - p);
        }

        return Math.max(dpi20, dpi10);
    }

    // leetcode 188
    public int maxProfit6(int K, int[] prices) {
        int n = prices.length;
        if (n <= 1)
            return 0;
        int[][][] dp = new int[n][K + 1][2];

        for (int i = 0; i < n; i++) {
            for (int k = 0; k < K + 1; k++) {
                for (int x = 0; x < 2; x++) {
                    if (k == 0) {
                        if (x == 0) {
                            dp[i][k][x] = 0;
                        } else {
                            dp[i][k][x] = -(int) 1e9;
                        }
                    } else {
                        if (i == 0) {
                            if (x == 0) {
                                dp[i][k][x] = 0;
                            } else {
                                // dp[i][k][x]=Math.max(-(int)1e9,0-prices[i]);
                                // or
                                dp[i][k][x] = 0 - prices[i]; // as on 0th day , when x=1 i have no option but to buy a
                                                             // stock
                            }
                        } else {
                            if (x == 0) {
                                dp[i][k][x] = Math.max(dp[i - 1][k][0], dp[i - 1][k][1] + prices[i]);
                            } else {
                                dp[i][k][x] = Math.max(dp[i - 1][k][1], dp[i - 1][k - 1][0] - prices[i]);
                            }
                        }
                    }
                }
            }
        }
        return dp[n - 1][K][0];
    }

    // o(k) space
    // public int maxProfit6Optimised(int K, int[] prices) {

    // }

}