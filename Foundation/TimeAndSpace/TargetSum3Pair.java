import java.util.*;

public class TargetSum3Pair {
    public static Scanner scn = new Scanner(System.in);

    public static void QS(int[] arr, int si, int ei) {
        if (si > ei)
            return;
        int ptr = si - 1;
        int itr = si;
        while (itr <= ei) {
            if (arr[itr] <= arr[ei]) {
                ptr++;
                int temp = arr[ptr];
                arr[ptr] = arr[itr];
                arr[itr] = temp;
                itr++;
            } else
                itr++;
        }
        QS(arr, si, ptr - 1);
        QS(arr, ptr + 1, ei);
    }

    public static void TS(int[] arr, int k) {
        QS(arr, 0, arr.length - 1);
        int i = 0;
        int j = arr.length - 1;
        int l;
        while (i < j) {
            l = i+1;
            if (arr[i] == arr[l]) {
                i++;
                continue;
            }
                while (l < j) {
                    if (arr[i] + arr[j] + arr[l] == k) {
                        System.out.println(arr[i] + ", " + arr[l] + ", " + arr[j]);
                        l++;
                        j--;
                    } else if (arr[i] + arr[j] + arr[l] < k)
                        l++;
                    else
                        j--;
                }
                i++;
                j = arr.length - 1;
            }
        }

    public static void main(String[] args) {
        int n = scn.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scn.nextInt();
        }
        int k = scn.nextInt();
        TS(arr, k);
    }
}