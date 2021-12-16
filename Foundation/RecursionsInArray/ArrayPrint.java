import java.util.*;

public class ArrayPrint {
    public static Scanner scn = new Scanner(System.in);

    public static int[] insert(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scn.nextInt();
        }
        return arr;
    }

    public static void ArrDisplay(int[] arr, int i) {
        if (i < 0) {
            return;
        }
        ArrDisplay(arr, i - 1);
        System.out.println(arr[i]);
        return;
    }

    public static void main(String[] args) {
        int n = scn.nextInt();
        int[] arr = new int[n];
        arr = insert(n);
        ArrDisplay(arr, n - 1);
    }
}