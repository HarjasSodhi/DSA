import java.util.*;

public class WWayPrint {
    public static Scanner scn = new Scanner(System.in);

    public static void WW(int[][] arr) {
        boolean right = true;
        boolean left = false;
        int n = arr.length;
        int m = arr[0].length;
        for (int i = 0; i < n; i++) {
            if (right == true) {
                for (int j = 0; j < m; j++) {
                    System.out.print(arr[i][j] + " ");
                }
                left = true;
                right = false;
                continue;
            }
            if (left == true) {
                for (int j = m - 1; j >= 0; j--) {
                    System.out.print(arr[i][j] + " ");
                }
                left = false;
                right = true;
                // no continue needed here as the loop is already ending.
            }
        }
    }

    public static int[][] insert(int n, int m) {
        int[][] arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = scn.nextInt();
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        int n = scn.nextInt();
        int m = scn.nextInt();
        int[][] arr = new int[n][m];
        arr = insert(n, m);
        WW(arr);
    }
}