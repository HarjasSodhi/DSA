import java.util.*;

public class Merge2Arrays {
    public static Scanner scn = new Scanner(System.in);

    public static void M2A(int[] arr1, int[] arr2) {
        int i = 0;
        int j = 0;
        while (j < arr2.length) {
            if (i == arr1.length || arr2[j] < arr1[i]) {
                System.out.println(arr2[j]);
                j++;
            } else {
                System.out.println(arr1[i]);
                i++;
            }
        }
    }

    public static void main(String[] args) {
        int n = scn.nextInt();
        int m = scn.nextInt();
        int[] arr1 = new int[n];
        int[] arr2 = new int[m];
        for (int i = 0; i < n; i++) {
            arr1[i] = scn.nextInt();
        }
        for (int i = 0; i < m; i++) {
            arr2[i] = scn.nextInt();
        }
        if (m > n)
            M2A(arr1, arr2);
        else
            M2A(arr2, arr1);
    }
}