import java.util.*;

public class QuickSelect {
    public static Scanner scn = new Scanner(System.in);

    public static void QS(int[] arr, int si, int ei, int k) {
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
        if (ptr == k) {
            System.out.println(arr[k]);
            return;
        }
        if (ptr > k)
            QS(arr, si, ptr - 1, k);
        if (ptr < k)
            QS(arr, ptr + 1, itr - 1, k);

    }

    public static void main(String[] args) {
        int n = scn.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scn.nextInt();
        }
        int k = scn.nextInt();
        QS(arr, 0, arr.length - 1, k - 1);
    }
}