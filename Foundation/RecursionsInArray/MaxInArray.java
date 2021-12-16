import java.util.*;

public class MaxInArray {
    public static Scanner scn = new Scanner(System.in);

    public static int[] insert(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scn.nextInt();
        }
        return arr;
    }

    public static int Max(int[] arr, int i) {
        if (i == arr.length)
            return -1;
        return Math.max(arr[i], Max(arr, i + 1));
    }

    public static void main(String[] args) {
        int n = scn.nextInt();
        int[] arr = new int[n];
        arr = insert(n);
        int i = 0;
        int greatest = Max(arr, i);
        System.out.println(greatest);
    }
}