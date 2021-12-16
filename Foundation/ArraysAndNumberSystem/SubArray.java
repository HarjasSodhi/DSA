import java.util.*;

public class SubArray {
    public static Scanner scn = new Scanner(System.in);

    public static void SA(int[] arr, int n) {
        int k = 0;
        while (k < n) {
            for (int i = k; i < n; i++) {
                for (int j = k; j <= i; j++) {
                    System.out.print(arr[j] + "\t");
                }
                System.out.println();
            }
            k++;
        }
    }

    public static void InsertData(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scn.nextInt();
        }
        SA(arr, n);
    }

    public static void main(String[] args) {
        int n = scn.nextInt();
        InsertData(n);
    }
}