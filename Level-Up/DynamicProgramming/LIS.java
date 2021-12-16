import java.util.*;

public class LIS {

    // 300
    public int lengthOfLIS_memo(int[] nums, int n, int[] dp) {
        if (dp[n] != -1)
            return dp[n];
        int max = 1;
        for (int i = n - 1; i >= 0; i--) {
            if (nums[i] < nums[n]) {
                max = Math.max(max, lengthOfLIS_memo(nums, i, dp) + 1);
            }
        }
        return dp[n] = max;
    }

    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, -1);
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            max = Math.max(max, lengthOfLIS_memo(nums, i, dp));
        }
        return max;
    }

    public int lengthOfLISTabu(int[] nums) {
        int[] dp = new int[nums.length];
        int ans = -1;
        for (int i = 0; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    public int lengthOfLDSTabu(int[] nums) {
        int[] dp = new int[nums.length];
        int ans = -1;
        for (int i = 0; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] < nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    // public int LongestBitonicSequence(int[] nums) {
        
    // }

    //github

}