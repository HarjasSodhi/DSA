import java.util.*;

public class SpanOfArray {
    public static Scanner scn = new Scanner(System.in);

    public static int max(int[] arr) {
        int max = -(int) 1e9-1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    public static int[] insert(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scn.nextInt();
        }
        return arr;
    }

    public static int least(int[] arr) {
        int least = (int) 1e9+1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < least) {
                least = arr[i];
            }
        }
        return least;
    }

    public static int span(int[] arr) {
        int max = max(arr);
        int least = least(arr);
        return max - least;
    }

    public static void main(String[] args) {
        int n = scn.nextInt();
        int[] arr = new int[n];
        arr = insert(n);
        int i = span(arr);
        System.out.println(i);
    }
}
