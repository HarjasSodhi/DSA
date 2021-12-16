import java.util.*;

public class InsertionSort {
    public static Scanner scn = new Scanner(System.in);

    public static void IS(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int j = i;
            if (arr[i] < arr[i - 1]) {
                while (j > 0 && arr[j] < arr[j - 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                    j--;
                }
            }
        }
    }

    public static void main(String[] args) {
        int n = scn.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scn.nextInt();
        }
        IS(arr);
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}