import java.util.*;

public class KadanesAlgo {

    // https://zoom.us/rec/play/qUJ9LOXcCY_KAlxsoHziOnuP4wGuMDJWf8oNPqIjbq9H8U8n0GicPh2brF3eiZKPQtGYjDmTAeVzy08a.F22KqhbIaerWypKt?continueMode=true&_x_zm_rtaid=FOPnH1MCSne6Fwg_6hLV1Q.1638110379475.19046ae6f8ecfe1c3dddd5d50d7f3759&_x_zm_rhtaid=343
    // kadanes Algo returns max subarray sum present inside a array.

    public int[] KadanesAlgorithm(int[] arr) {
        int currSum = 0;
        int maxSum = -(int) 1e9;
        int idx = 0;
        int n = arr.length;

        int gsi = -1;
        int gei = -1;

        int si = -1;

        while (idx < n) {
            if (currSum == 0)
                si = idx;

            currSum += arr[idx];
            if (maxSum < currSum) {
                gsi = si;
                gei = idx;
                maxSum = currSum;
            }

            if (currSum < 0)
                currSum = 0;
            idx++;
        }

        return new int[] { maxSum, gsi, gei };
    }

    // leetcode 1191
    public int kadanes(int[] arr, int k) {
        int mod = (int) (1e9 + 7);

        long csum = 0;
        long msum = 0;
        int i = 0;

        while (k > 0) {
            while (i < arr.length) {
                csum += arr[i];

                if (csum < 0) {
                    csum = 0;
                }

                msum = Math.max(csum, msum);
                i++;
            }
            i = 0;
            k--;
        }

        return (int) (msum % mod);
    }

    public int kConcatenationMaxSum(int[] arr, int k) {
        int ans_for_2 = 0;
        long mod = (int) (1e9 + 7);

        long arr_sum = 0;
        for (int e : arr) {
            arr_sum += e;
        }

        for (int i = 1; i <= 2; i++) {
            int sum = kadanes(arr, i);

            if (k == i) {
                return sum;
            }

            if (i == 2) {
                ans_for_2 = sum;
            }
        }

        if (arr_sum < 0) {
            return (int) (ans_for_2 % mod);
        }

        int ans = (int) (ans_for_2 + ((k - 2) * (arr_sum) % mod));

        return Math.max(ans, 0);
    }

    // https://practice.geeksforgeeks.org/problems/maximum-sum-rectangle2948/1
    int kadane_neg(int[] arr) {
        int csum = 0;
        int msum = -(int) (1e9);

        for (int i = 0; i < arr.length; i++) {
            csum += arr[i];

            msum = Math.max(msum, csum);

            if (csum < 0) {
                csum = 0;
            }
        }

        return msum;
    }

    int maximumSumRectangle(int R, int C, int M[][]) {
        int ans = -(int) (1e9);
        for (int fixed_row = 0; fixed_row < R; fixed_row++) {
            int[] pre = new int[C];

            for (int row = fixed_row; row < R; row++) {
                for (int col = 0; col < C; col++) {
                    pre[col] = pre[col] + M[row][col];
                }

                int sum = kadane_neg(pre);
                ans = Math.max(ans, sum);
            }
        }

        return ans;
    }

    // leetcode 1074
    public int subarrayCountWithSumEqualTarget(int[] arr, int tar) {
        HashMap<Integer, Integer> map = new HashMap<>();

        int csum = 0;
        int count = 0;
        map.put(0, 1);
        for (int i = 0; i < arr.length; i++) {
            csum += arr[i];

            if (map.containsKey(csum - tar)) {
                count += map.get(csum - tar);
            }
            map.put(csum, map.getOrDefault(csum, 0) + 1);
        }

        return count;
    }

    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        int R = matrix.length;
        int C = matrix[0].length;

        int ans = 0;

        for (int fixed_row = 0; fixed_row < R; fixed_row++) {
            int[] pre = new int[C];

            for (int row = fixed_row; row < R; row++) {
                for (int col = 0; col < C; col++) {
                    pre[col] = pre[col] + matrix[row][col];
                }

                ans += subarrayCountWithSumEqualTarget(pre, target);
            }
        }

        return ans;
    }

}