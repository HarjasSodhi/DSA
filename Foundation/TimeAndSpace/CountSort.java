import java.util.*;

public class CountSort {
    public static Scanner scn = new Scanner(System.in);

    public static void CS1(int[] arr) {
        int greatest = (int) -1e9;
        for (int i = 0; i < arr.length; i++) {
            if (greatest < arr[i])
                greatest = arr[i];
        }
        int[] freq = new int[greatest + 1];
        for (int i = 0; i < arr.length; i++) {
            freq[arr[i]]++;
        }
        int idx = 0;
        for (int i = 0; i < freq.length; i++) {
            for (int j = 0; j < freq[i]; j++) {
                arr[idx] = i;
                idx++;
            }
        }
        Display(arr);
    }

    public static void CS2(int[] arr) {
        int greatest = -(int) 1e9;
        int minimum = (int) 1e9;
        for (int i = 0; i < arr.length; i++) {
            if (greatest < arr[i])
                greatest = arr[i];
            if (arr[i] < minimum)
                minimum = arr[i];
        }
        minimum = Math.abs(minimum);
        int[] freq = new int[greatest + 1 + minimum];
        for (int i = 0; i < arr.length; i++) {
            freq[arr[i] + minimum]++;
        }
        int idx = 0;
        for (int i = 0; i < freq.length; i++) {
            for (int j = 0; j < freq[i]; j++) {
                arr[idx] = i - minimum;
                idx++;
            }
        }
        Display(arr);
    }

    public static void Display(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    public static void CSStable(int[] arr) {
        int greatest = -(int) 1e9;
        int minimum = (int) 1e9;
        for (int i = 0; i < arr.length; i++) {
            if (greatest < arr[i])
                greatest = arr[i];
            if (arr[i] < minimum)
                minimum = arr[i];
        }
        int[] freq = new int[greatest - minimum + 1];
        for (int i = 0; i < arr.length; i++) {
            freq[arr[i] - minimum]++;
        }
        for (int i = 1; i < freq.length; i++) {
            freq[i] = freq[i] + freq[i - 1];
        }
        int[] ans = new int[arr.length];
        for (int i = arr.length - 1; i >= 0; i--) {
            int pos = freq[arr[i] - minimum] - 1;
            ans[pos] = arr[i];
            freq[arr[i] - minimum]--;
        }
        for (int i = 0; i < ans.length; i++) {
            arr[i] = ans[i];
        }
        Display(arr);
    }

    public static void main(String[] args) {
        int n = scn.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scn.nextInt();
        }
        // CS1(arr);
        // CS2(arr);
        CSStable(arr);
    }
}