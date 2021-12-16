import java.util.*;

public class BinarySearch {

    public static int binarySearch(int[] arr, int si, int ei, int num) {
        int ans = -1;
        while (si <= ei) {
            int mid = (ei + si) / 2;
            if (arr[mid] == num) {
                ans = mid;
                break;
            } else if (arr[mid] > num) {
                ei = mid - 1;
            } else {
                si = mid + 1;
            }
        }
        return ans;
    }

    // leetcode 34
    public int[] searchRange(int[] nums, int target) {
        int si = 0;
        int ei = nums.length - 1;
        int siAns = findSi(nums, target, si, ei);
        int eiAns = findEi(nums, target, si, ei);
        int[] ans = { siAns, eiAns };
        return ans;
    }

    public static int findEi(int[] arr, int num, int si, int ei) {
        int eiAns = -1;

        while (si <= ei) {
            int mid = (ei + si) / 2;
            if (arr[mid] == num) {
                eiAns = mid;
                si = mid + 1;
            } else if (arr[mid] > num) {
                ei = mid - 1;
            } else {
                si = mid + 1;
            }
        }
        return eiAns;
    }

    public static int findSi(int[] arr, int num, int si, int ei) {
        int siAns = -1;
        while (si <= ei) {
            int mid = (ei + si) / 2;
            if (arr[mid] == num) {
                siAns = mid;
                ei = mid - 1;
            } else if (arr[mid] > num) {
                ei = mid - 1;
            } else {
                si = mid + 1;
            }
        }
        return siAns;
    }

    public static int perfectPosition(int[] arr, int num) {
        int si = 0;
        int ei = arr.length - 1;
        int mid = si;
        while (si <= ei) {
            mid = (ei + si) / 2;
            if (arr[mid] == num) {
                return mid;
            } else if (arr[mid] > num) {
                ei = mid - 1;
            } else {
                si = mid + 1;
            }
        }
        return arr[mid] > num ? mid : mid + 1;
    }

    public static int[] CeilFloor(int[] arr, int num) {
        int si = 0;
        int ei = arr.length - 1;
        while (si <= ei) {
            int mid = (ei + si) / 2;
            if (arr[mid] == num) {
                return new int[] { mid, mid };
            } else if (arr[mid] > num) {
                ei = mid - 1;
            } else {
                si = mid + 1;
            }
        }
        return new int[] { ei, si };// floor and ceil is the order
    }

    public static int ClosestNum(int[] arr, int num) {
        int[] ans = CeilFloor(arr, num);
        if (ans[1] == -1)
            return arr[ans[0]];
        if (ans[0] == arr.length)
            return arr[ans[1]];
        return Math.abs(arr[ans[0]] - num) > Math.abs(arr[ans[1]] - num) ? arr[ans[1]] : arr[ans[0]];
    }

    // leetcode 74
    public boolean searchMatrix(int[][] matrix, int target) {
        int n = matrix.length, m = matrix[0].length;
        int si = 0, ei = (m * n) - 1;
        while (si <= ei) {
            int mid = (si + ei) / 2;
            int r = mid / m, c = mid % m;
            if (matrix[r][c] == target) {
                return true;
            } else if (matrix[r][c] > target) {
                ei = mid - 1;
            } else {
                si = mid + 1;
            }
        }
        return false;
    }

    // leetcode 658
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int pos = perfectPosition(arr, x);
        int n = arr.length;
        int ll = pos - k >= 0 ? pos - k : 0;
        int rl = pos + k < n ? pos + k : n - 1;
        while (rl - ll + 1 > k) {
            if (Math.abs(arr[ll] - x) > Math.abs(arr[rl] - x)) {
                ll++;
            } else {
                rl--;
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = ll; i <= rl; i++) {
            ans.add(arr[i]);
        }
        return ans;
    }

}