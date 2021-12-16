import java.util.*;

public class RotateArray {
    public static Scanner scn = new Scanner(System.in);

    public static int[] insert(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scn.nextInt();
        }
        return arr;
    }

    public static void display(int[] arr) {
        for (int i = 0; i < arr.length; i++)
            System.out.print(arr[i] + " ");
    }

    public static void InsertDelete(int[] arr, int k) {
        if (k > arr.length)
            k = k % arr.length;
        for (int j = 1; j <= k; j++) {
            int temp = arr[arr.length - 1];
            for (int i = arr.length - 1; i > 0; i--)
                arr[i] = arr[i - 1];
            arr[0] = temp;
        }
        display(arr);
    }

    public static void DeleteInsert(int[] arr, int k) {
        if (-k > arr.length)
            k = k % arr.length;
        for (int j = 1; j <= -k; j++) {
            int temp = arr[0];
            for (int i = 0; i < arr.length - 1; i++)
                arr[i] = arr[i + 1];
            arr[arr.length - 1] = temp;
        }
        display(arr);
    }

    public static void rotate(int[] arr) {
        System.out.println("enter the value of k");
        int k = scn.nextInt();
        if (k > 0)
            InsertDelete(arr, k);
        else
            DeleteInsert(arr, k);
    }

    public static void main(String[] args) {
        int n = scn.nextInt();
        int[] arr = new int[n];
        arr = insert(n);
        rotate(arr);
    }
}
