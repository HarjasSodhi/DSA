import java.util.*;

public class QuickSort {
    public static Scanner scn = new Scanner(System.in);

    public static void QS(int[] arr, int si, int ei) {
        if (si > ei)
            return;
        int ptr = si - 1;
        int itr = si;
        System.out.println("pivot -> " + arr[ei]);
        while (itr <= ei) {
            if (arr[itr] <= arr[ei]) {
                ptr++;
                System.out.println("Swapping " + arr[itr] + " and " + arr[ptr]);
                int temp = arr[ptr];
                arr[ptr] = arr[itr];
                arr[itr] = temp;
                itr++;
            } else
                itr++;
        }
        System.out.println("pivot index -> " + ptr);
        QS(arr, si, ptr - 1);
        QS(arr, ptr + 1, itr - 1);
    }

    // or
    public static void QS2(int[] arr, int si, int ei, int pivIdx) {
        if (si > ei)
            return;
        int ptr = si - 1;
        int itr = si;

        while (itr <= ei) {
            if (arr[itr] <= arr[pivIdx]) {
                ptr++;
                System.out.println("Swapping " + arr[itr] + " and " + arr[ptr]);
                int temp = arr[ptr];
                arr[ptr] = arr[itr];
                arr[itr] = temp;
                itr++;
            } else
                itr++;
        }

        QS2(arr, si, ptr - 1, ptr - 1);
        QS2(arr, ptr + 1, itr - 1, itr - 1);
    }

    // just like can be used for random index as well -> use random in range func and swap the pivot ele with last ele.
    public static void main(String[] args) {
        int n = scn.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scn.nextInt();
        }
        QS(arr, 0, arr.length - 1);
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}