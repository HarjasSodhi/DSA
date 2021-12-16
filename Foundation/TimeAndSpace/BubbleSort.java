import java.util.*;

public class BubbleSort {
    public static Scanner scn = new Scanner(System.in);

    public static void bubbleSort(int[] arr) {
        for (int li = arr.length - 1; li > 0; li--) {
            for (int i = 1; i <= li; i++) {
                boolean checker = isSmaller(arr, i, i - 1);
                if (checker) {
                    swap(arr, i, i - 1);
                }
            }
        }
    }

    public static void bubbleSortOpt(int[] arr) {
        for (int li = arr.length - 1; li > 0; li--) {
            boolean opt = true;
            for (int i = 1; i <= li; i++) {
                boolean checker = isSmaller(arr, i, i - 1);
                if (checker) {
                    opt = false;
                    swap(arr, i, i - 1);
                }
            }
            if (opt)
                break;
        }
    }

    public static void swap(int[] arr, int i, int j) {
        // System.out.println("Swapping " + arr[i] + " and " + arr[j]);
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static boolean isSmaller(int[] arr, int i, int j) {
        // System.out.println("Comparing " + arr[i] + " and " + arr[j]);
        if (arr[i] < arr[j]) {
            return true;
        } else {
            return false;
        }
    }

    public static void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+" ");
        }
    }

    public static void main(String[] args) throws Exception {
        int n = scn.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scn.nextInt();
        }
        bubbleSortOpt(arr);
        print(arr);
    }
}