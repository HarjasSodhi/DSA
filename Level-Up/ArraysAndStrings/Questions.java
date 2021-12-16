public class Questions {

    public static void swap(int[] arr, int si, int ei) {
        while (si < ei) {
            int temp = arr[si];
            arr[si] = arr[ei];
            arr[ei] = temp;
            si++;
            ei--;
        }
    }

    public static void rotate(int[] arr, int k) {
        int size = arr.length;
        k = ((k % size) + size) % size; // imp conversion technique
        if (k == 0)
            return;
        swap(arr, 0, size - k - 1);
        swap(arr, size - k, size - 1);
        swap(arr, 0, size - 1);
    }

    public static void binSort(int[] arr, int size) {
        int itr = 0;
        int ptr = -1;
        while (itr < size) {
            if (arr[itr] == 0) {
                ptr++;
                int temp = arr[ptr];
                arr[ptr] = arr[itr];
                arr[itr] = temp;
            }
            itr++;
        }
    }

    // leetcode 75
    public void sortColors(int[] arr) {
        int itr = 0;
        int ptr0 = -1;
        int size = arr.length;
        int ptr2 = size;
        while (itr < ptr2) {
            if (arr[itr] == 0) {
                ptr0++;
                int temp = arr[ptr0];
                arr[ptr0] = arr[itr];
                arr[itr] = temp;
                itr++;
                // we can do this as we can guarantee that number comning after swap will be one
                // only because ,if 2 would be present earlier , we would have swapped it to the
                // end already.
            } else if (arr[itr] == 2) {
                ptr2--;
                int temp = arr[ptr2];
                arr[ptr2] = arr[itr];
                arr[itr] = temp;
            } else {
                itr++;
            }
        }
    }

    // https://practice.geeksforgeeks.org/problems/max-sum-in-the-configuration/1
    public static int max_sum(int arr[], int size) {
        int currSum = 0;
        int arrSum = 0;
        for (int i = 0; i < size; i++) {
            currSum += (arr[i] * i);
            arrSum += arr[i];
        }
        int maxSum = currSum;
        for (int i = 1; i < size; i++) {
            currSum = currSum - arrSum + size * arr[i - 1];
            maxSum = Math.max(currSum, maxSum);
        }
        return maxSum;
    }

    public int maxArea(int[] height) {
        int si = 0;
        int ei = height.length - 1;
        int ans = 0;
        while (si < ei) {
            int tempAns = Math.min(height[si], height[ei]) * (ei - si);
            ans = Math.max(ans, tempAns);
            if (height[si] < height[ei]) {
                si++;
            } else {
                ei--;
            }
        }
        return ans;
    }

}