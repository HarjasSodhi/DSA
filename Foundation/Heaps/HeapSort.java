public class HeapSort {
    public static int compareTo(int[] arr, int t, int o, boolean isIncreasing) {
        if (isIncreasing)
            return arr[o] - arr[t];
        else
            return arr[t] - arr[o];
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void downHeapify(int[] arr, int pi, int noOfEle, boolean isIncreasing) {
        int lc = 2 * pi + 1;
        int rc = 2 * pi + 2;
        int mi = pi;
        if (lc < noOfEle && compareTo(arr, mi, lc, isIncreasing) > 0) {
            mi = lc;
        }
        if (rc < noOfEle && compareTo(arr, mi, rc, isIncreasing) > 0) {
            mi = rc;
        }
        if (mi != pi) {
            swap(arr, mi, pi);
            downHeapify(arr, mi, noOfEle, isIncreasing);
        }
    }

    public static void heapSort(int[] arr, boolean isIncreasing) {
        for (int i = arr.length - 1; i >= 0; i--) {
            downHeapify(arr, i, arr.length, isIncreasing);
        }
        int j = arr.length - 1;
        while (j >= 0) {
            swap(arr, 0, j);
            downHeapify(arr, 0, --j, isIncreasing);
        }
    }

    public static void display(int[] arr) {
        for (int ele : arr)
            System.out.print(ele + " ");
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = { 10, 20, 30, -2, -3, -4, 5, 6, 7, 8, 9, 22, 11, 13, 14 };
        boolean isIncreasing = true;
        heapSort(arr, isIncreasing);

        display(arr);
    }
}