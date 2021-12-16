import java.util.*;

public class questions {

    // https://classroom.pepcoding.com/the-placement-program-pitampura-mar-14-2021/116/classvideos/5810
    // inversion count GFG
    public static long inversionCount(long arr[], long N) {
        if (N == 0)
            return 0;

        long[] sortedArray = new long[(int) N];
        return inversionCount(arr, sortedArray, 0, N - 1);
    }

    public static long inversionCount(long[] arr, long[] sortedArray, long si, long ei) {
        if (si >= ei)
            return 0;

        long mid = (si + ei) / 2;
        long count = 0;

        count += inversionCount(arr, sortedArray, si, mid);
        count += inversionCount(arr, sortedArray, mid + 1, ei);

        count += totalInversionCount(arr, sortedArray, si, mid, ei);
        return count;
    }

    public static long totalInversionCount(long[] arr, long[] sortedArray, long si, long mid, long ei) {
        int i = (int) si, j = (int) mid + 1, k = (int) si;
        long count = 0;

        while (i <= mid && j <= ei) {
            if (arr[i] <= arr[j])
                sortedArray[k++] = arr[i++];
            else {
                sortedArray[k++] = arr[j++];
                count += mid - i + 1;
            }
        }

        while (i <= mid || j <= ei)
            sortedArray[k++] = arr[i <= mid ? i++ : j++];

        while (si <= ei)
            arr[(int) si] = sortedArray[(int) si++];

        return count;
    }

    // leetcode 1
    public int[] twoSum(int[] arr, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(target - arr[i])) {
                return new int[] { i, map.get(target - arr[i]) };
            } else {
                map.put(arr[i], i);
            }
        }
        return new int[] { -1, -1 };
    }

    // leetcode 167
    public int[] twoSumSorted(int[] numbers, int target) {
        int i = 0;
        int j = numbers.length - 1;
        while (i < j) {
            if (numbers[i] + numbers[j] == target) {
                return new int[] { i + 1, j + 1 };// arr is 1-indexed
            } else if (numbers[i] + numbers[j] > target) {
                j--;
            } else {
                i++;
            }
        }
        return new int[] { -1, -1 };
    }

    public List<List<Integer>> twoSumSortedDuplicates(int[] numbers, int target, int si, int ei) {
        List<List<Integer>> ans = new ArrayList<>();
        int i = si;
        int j = ei;
        while (i < j) {
            if (numbers[i] + numbers[j] == target) {
                List<Integer> tempAns = new ArrayList<>();
                tempAns.add(numbers[i]);
                tempAns.add(numbers[j]);
                ans.add(tempAns);
                while (i < j && numbers[i] == numbers[i + 1]) {
                    i++;
                }
                i++;
                while (i < j && numbers[j] == numbers[j - 1]) {
                    j--;
                }
                j--;
            } else if (numbers[i] + numbers[j] > target) {
                j--;
            } else {
                i++;
            }
        }
        return ans;
    }

    // leetcode 15
    public List<List<Integer>> threeSum(int[] nums, int target, int si) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        int n = nums.length;
        int prev = 0;
        for (int i = si; i < nums.length - 2; i++) {
            if (i != si && nums[i] == prev) {
                continue;
            } else {
                prev = nums[i];
                List<List<Integer>> tempAns = twoSumSortedDuplicates(nums, target - nums[i], i + 1, n - 1);
                for (int j = 0; j < tempAns.size(); j++) {
                    List<Integer> temp = tempAns.get(j);
                    temp.add(nums[i]);
                    ans.add(temp);
                }
            }
        }
        return ans;
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        int n = nums.length;
        int prev = 0;
        for (int i = 0; i < n - 3; i++) {
            if (i != 0 && nums[i] == prev) {
                continue;
            } else {
                prev = nums[i];
                List<List<Integer>> tempAns = threeSum(nums, target - nums[i], i + 1);
                for (int j = 0; j < tempAns.size(); j++) {
                    List<Integer> temp = tempAns.get(j);
                    temp.add(nums[i]);
                    ans.add(temp);
                }
            }
        }
        return ans;
    }

    public List<List<Integer>> KSum(int[] nums, int target, int k, int si, int ei) {
        if (k == 2) {
            return twoSumSortedDuplicates(nums, target, si, ei);
        }
        List<List<Integer>> ans = new ArrayList<>();
        int prev = 0;
        for (int i = si; i < ei - k + 1; i++) {
            if (i != si && nums[i] == prev) {
                continue;
            } else {
                prev = nums[i];
                List<List<Integer>> tempAns = KSum(nums, target - nums[i], k - 1, i + 1, ei);
                for (int j = 0; j < tempAns.size(); j++) {
                    List<Integer> temp = tempAns.get(j);
                    temp.add(nums[i]);
                    ans.add(temp);
                }
            }
        }
        return ans;
    }

    // leetcode 454
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int ans = 0;
        for (int i : nums1) {
            for (int j : nums2) {
                if (map.containsKey(i + j)) {
                    map.put(i + j, map.get(i + j) + 1);
                } else {
                    map.put(i + j, 1);
                }
            }
        }

        for (int i : nums3) {
            for (int j : nums4) {
                if (map.containsKey(0 - (i + j))) {
                    ans += map.get(0 - (i + j));
                }
            }
        }

        return ans;
    }

    // leetcode 33
    public int search(int[] nums, int target) {
        int si = 0;
        int ei = nums.length - 1;
        while (si <= ei) {
            int mid = (si + ei) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[si] <= nums[mid]) {
                if (target < nums[mid] && target >= nums[si]) {
                    ei = mid - 1;
                } else {
                    si = mid + 1;
                }
            } else {
                if (target > nums[mid] && target <= nums[ei]) {
                    si = mid + 1;
                } else {
                    ei = mid - 1;
                }
            }
        }
        return -1;
    }

    // leetcode 81
    public boolean searchDuplicates(int[] nums, int target) {
        int si = 0;
        int ei = nums.length - 1;
        while (si <= ei) {
            int mid = (si + ei) / 2;
            if (nums[mid] == target || nums[si] == target) {
                return true;
            }
            if (nums[si] < nums[mid]) {
                if (target < nums[mid] && target >= nums[si]) {
                    ei = mid - 1;
                } else {
                    si = mid + 1;
                }
            } else if (nums[ei] > nums[mid]) {
                if (target > nums[mid] && target <= nums[ei]) {
                    si = mid + 1;
                } else {
                    ei = mid - 1;
                }
            } else {
                si++;
            }
        }
        return false;
    }

    // leetcode 153
    public int findMin(int[] nums) {
        int si = 0;
        int ei = nums.length - 1;
        while (si < ei) {
            int mid = (si + ei) / 2;
            if (nums[mid] < nums[ei]) {
                ei = mid;
            } else {
                si = mid + 1;
            }
        }
        return nums[si];
    }

    // leetcode 154
    public int findMinDuplicates(int[] nums) {
        int si = 0;
        int ei = nums.length - 1;
        while (si < ei) {
            int mid = (si + ei) / 2;
            if (nums[mid] < nums[ei]) {
                ei = mid;
            } else if (nums[mid] > nums[ei]) {
                si = mid + 1;
            } else {
                ei--;
            }
        }
        return nums[si];
    }

    // // leetcode 875
    // public int minEatingSpeed(int[] piles, int h) {

    // }

    // // leetcode 1011
    // public int shipWithinDays(int[] weights, int days) {

    // }

    // leetcode 4
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int x = nums1.length, y = nums2.length;
        int mid = x + y;

        int prevNum = 0;

        int i = 0, j = 0, k = 0;
        while (i < x && j < y) {
            if (k == mid / 2) {
                if (mid % 2 != 0) {
                    return nums1[i] < nums2[j] ? (double) nums1[i] : (double) nums2[j];
                } else {
                    return (double) ((nums1[i] < nums2[j] ? (double) nums1[i] : (double) nums2[j]) + (double) prevNum)
                            / (double) 2;
                }
            }
            if (nums1[i] < nums2[j]) {
                prevNum = nums1[i];
                i++;
            } else {
                prevNum = nums2[j];
                j++;
            }
            k++;
        }

        while (i < x) {
            if (k == mid / 2) {
                if (mid % 2 != 0) {
                    return (double) nums1[i];
                } else {
                    return (double) ((double) nums1[i] + (double) prevNum) / (double) 2;
                }
            }
            prevNum = nums1[i];
            i++;
            k++;
        }

        while (j < y) {
            if (k == mid / 2) {
                if (mid % 2 != 0) {
                    return (double) nums2[j];
                } else {
                    return (double) ((double) nums2[j] + (double) prevNum) / (double) 2;
                }
            }
            prevNum = nums2[j];
            j++;
            k++;
        }

        return 0;
    }

    public double findMedianSortedArraysBS(int[] nums1, int[] nums2) {
        
    }

}