import java.util.*;

public class MergeSort {
    public static Scanner scn = new Scanner(System.in);

    public static int[] MS(int[] arr, int si, int ei) {
        if (ei - si == 0) {
            return new int[] { arr[si] };
        }
        int[] recans1 = MS(arr, si, (si + ei) / 2);
        int[] recans2 = MS(arr, ((si + ei) / 2) + 1, ei);
        System.out.println("Merging these two arrays ");
        System.out.print("left array -> ");
        print(recans1);
        System.out.print("right array -> ");
        print(recans2);
        int n = recans1.length, m = recans2.length;
        int[] sol = new int[n + m];
        int i = 0, j = 0, k = 0;
        while (i < n && j < m) {
            if (recans1[i] < recans2[j]) {
                sol[k] = recans1[i];
                i++;
                k++;
            } else {
                sol[k] = recans2[j];
                j++;
                k++;
            }
        }
        while (i < n) {
            sol[k] = recans1[i];
            i++;
            k++;
        }
        while (j < m) {
            sol[k] = recans2[j];
            j++;
            k++;
        }
        return sol;
    }

    public static void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int n = scn.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scn.nextInt();
        }
        int[] sol = MS(arr, 0, arr.length - 1);
        System.out.print("Sorted Array -> ");
        print(sol);
    }
}