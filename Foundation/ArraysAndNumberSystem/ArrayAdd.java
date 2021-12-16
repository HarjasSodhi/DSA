import java.util.*;

public class ArrayAdd {
    public static Scanner scn = new Scanner(System.in);

    public static int[] insert(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scn.nextInt();
        }
        return arr;
    }

    public static void display(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0 && i == 0)
                continue;
            System.out.println(arr[i]);
        }
    }

    public static void AA(int[] arr1, int[] arr2) {
        int p = arr1.length;
        int q = arr2.length;
        int r = Math.max(p, q);
        int[] ans = new int[r + 1];
        int carry = 0;
        for (int i = r; i >= 0; i--) {
            int temp = (p - 1 >= 0 ? arr1[p - 1] : 0) + (q - 1 >= 0 ? arr2[q - 1] : 0) + carry;
            ans[i] = temp % 10;
            carry = temp / 10;
            p--;
            q--;
        }
        display(ans);
    }

    public static void main(String[] args) {
        int n = scn.nextInt();
        int[] arr1 = new int[n];
        arr1 = insert(n);
        int j = scn.nextInt();
        int[] arr2 = new int[j];
        arr2 = insert(j);
        AA(arr1, arr2);
    }
}