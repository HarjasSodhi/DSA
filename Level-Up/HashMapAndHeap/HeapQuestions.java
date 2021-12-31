import java.util.*;

public class HeapQuestions {
    // https://classroom.pepcoding.com/the-placement-program-pitampura-mar-14-2021/116/classvideos/5687

    // https://practice.geeksforgeeks.org/problems/kth-smallest-element5635/1
    // nlog(k)
    // you can also use your own heap class and get
    // n + klog(n) ,which, is better
    // check this sol in video at 50min
    public static int kthSmallest(int[] arr, int l, int r, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
            return b - a;
        });
        while (l <= r) {
            pq.add(arr[l]);
            if (l >= k) {
                pq.remove();
            }
            l++;
        }
        return pq.peek();
    }

    // 378
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int m = matrix[0].length;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
            int i1 = a / m, j1 = a % m;
            int i2 = b / m, j2 = b % m;

            return matrix[i1][j1] - matrix[i2][j2];
        });

        for (int i = 0; i < n; i++)
            pq.add(i * m + 0);

        int r = 0, c = 0;
        while (k-- > 0) {
            int idx = pq.remove();
            r = idx / m;
            c = idx % m;
            if (c + 1 < m)
                pq.add(r * m + c + 1);
        }

        return matrix[r][c];
    }

    // 347
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                map.put(nums[i], (map.get(nums[i]) + 1));
            } else {
                map.put(nums[i], 1);
            }
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return b[1] - a[1];
        });

        map.forEach((key, value) -> {
            int[] temp = { key, value };
            pq.add(temp);
        });
        int[] ans = new int[k];
        int i = 0;
        while (k-- > 0) {
            ans[i] = pq.remove()[0];
            i++;
        }
        return ans;
    }

    // 973
    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            int temp1 = a[0] * a[0] + a[1] * a[1];
            int temp2 = b[0] * b[0] + b[1] * b[1];
            // no need to calculate root as root of smaller will be smaller only
            return temp2 - temp1;
        });
        for (int i = 0; i < points.length; i++) {
            pq.add(points[i]);
            if (i >= k) {
                pq.remove();
            }
        }
        int[][] ans = new int[k][2];
        for (int i = 0; i < k; i++) {
            int[] temp = pq.remove();
            ans[i][0] = temp[0];
            ans[i][1] = temp[1];
        }
        return ans;
    }

    // leetcode 778
    public int swimInWater(int[][] grid) {
        int[][] dir = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
        int maxHeight = 0;
        int n = grid.length;
        int m = grid[0].length;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
            int i1 = a / m, j1 = a % m;
            int i2 = b / m, j2 = b % m;
            return grid[i1][j1] - grid[i2][j2];
        });
        boolean[][] vis = new boolean[n][m];
        pq.add(0);
        vis[0][0] = true;
        while (pq.size() != 0) {
            int idx = pq.remove();
            int r = idx / m;
            int c = idx % m;
            vis[r][c] = true;
            maxHeight = Math.max(maxHeight, grid[r][c]);
            if (r == n - 1 && c == m - 1) {
                break;
            }
            for (int[] arr : dir) {
                int newR = r + arr[0];
                int newC = c + arr[1];
                if (newR >= 0 && newC >= 0 && newR < n && newC < m && !vis[newR][newC]) {
                    pq.add(newR * m + newC);
                }
            }
        }
        return maxHeight;
    }

    // 1642
    public int furthestBuilding(int[] heights, int bricks, int ladders) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        int n = heights.length;
        for (int i = 1; i < n; i++) {
            int currDiff = heights[i] - heights[i - 1];
            if (currDiff > 0) {
                pq.add(currDiff);

                if (pq.size() > ladders) {
                    bricks -= pq.remove();
                }

                if (bricks < 0)
                    return i - 1;
            }
        }

        return n - 1;
    }

    // 632
    public int[] smallestRange(List<List<Integer>> nums) {

        int n = nums.size();

        // {r,c}
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            int r1 = a[0], c1 = a[1];
            int r2 = b[0], c2 = b[1];

            return nums.get(r1).get(c1) - nums.get(r2).get(c2);

        });

        int maxValue = -(int) 1e9;

        for (int i = 0; i < n; i++) {
            pq.add(new int[] { i, 0 });
            maxValue = Math.max(maxValue, nums.get(i).get(0));
        }

        int range = (int) 1e9, sp = -1, ep = -1;
        while (pq.size() == n) {

            int[] re = pq.remove();
            int r = re[0], c = re[1], val = nums.get(r).get(c);

            if (maxValue - val < range) {
                range = maxValue - val;
                sp = val;
                ep = maxValue;
            }

            c++;
            if (c < nums.get(r).size()) {
                pq.add(new int[] { r, c });
                maxValue = Math.max(maxValue, nums.get(r).get(c));
            }
        }

        return new int[] { sp, ep };
    }

    // 781
    // best Test case=[0,0,1,1,1,2]
    // can also be done with hashmap
    public int numRabbits(int[] answers) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int e : answers) {
            pq.add(e);
        }
        int num = -1, ans = 0, freq = 0;
        while (pq.size() != 0) {
            if (pq.peek() == num && freq > 0) {
                freq--;
            } else if (pq.peek() == 0) {
                ans++;
            } else {
                ans += pq.peek() + 1;
                num = pq.peek();
                freq = num;
            }
            pq.remove();
        }
        return ans;
    }

    // 1218
    // public int longestSubsequence(int[] arr, int difference) {

    // }

    // 1027
    // public int longestArithSeqLength(int[] nums) {

    // }

}