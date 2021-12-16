import java.util.*;

public class PartitionOfArray {
    public static Scanner scn = new Scanner(System.in);

    public static void partition(int[] arr, int n) {
        int ptr = -1;
        int itr = 0;
        while (itr < arr.length) {
            if (arr[itr] <= n) {
                ptr++;
                int temp = arr[ptr];
                arr[ptr] = arr[itr];
                arr[itr] = temp;
                itr++;
            } else
                itr++;
        }
    }

    public static void partitionPivot(int[] arr, int idx) {
        int ptr = -1;
        int itr = 0;
        int pivotnum = arr[idx];
        while (itr < arr.length) {
            if (arr[itr] <= pivotnum) {
                ptr++;
                int temp = arr[ptr];
                arr[ptr] = arr[itr];
                arr[itr] = temp;
                if (itr == idx)
                    idx = ptr;
                itr++;
            } else
                itr++;
        }
        int temp = arr[idx];
        arr[idx] = arr[ptr];
        arr[ptr] = temp;
        // Another method=swap pivot index with last index and then apply normal process for partition.
    }

    public static void main(String[] args) {
        int n = scn.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scn.nextInt();
        }
        // int num = scn.nextInt();
        int idx = scn.nextInt();
        // partition(arr, num);
        partitionPivot(arr, idx);
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
