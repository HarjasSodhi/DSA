import java.util.*;

public class ArrayDiff {
    public static Scanner scn = new Scanner(System.in);

    public static int[] insert(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scn.nextInt();
        }
        return arr;
    }

    public static void display(int[] arr) {
        boolean numfound = false;
        for (int i = 0; i < arr.length; i++) {
            while (arr[i] == 0 && numfound == false) {
                i = i + 1;
            }
            numfound = true;
            System.out.println(arr[i]);
        }
    }

    public static void AD(int[] arr1, int[] arr2) {
        int p = arr2.length;
        int[] ans = new int[p];
        int j = 0;
        int s = 0;
        int q = arr1.length - 1;
        for (int i = p - 1; i >= 0; i--) {
            if (q - j >= 0) {
                if (arr2[i] < arr1[q - j]) {
                    arr2[i] += 10;
                    if (arr2[i - 1] == 0) {
                        while (arr2[i - 1 - s] == 0) {
                            arr2[i - 1 - s] = 9;
                            s++;
                        }
                        arr2[i - 1 - s]--;
                        s = 0;
                    } else {
                        arr2[i - 1]--;
                    }
                }
                ans[i] = arr2[i] - arr1[q - j];
                j++;
            } else {
                ans[i] = arr2[i];
            }
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
        AD(arr1, arr2);
    }
}