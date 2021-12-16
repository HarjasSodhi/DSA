import java.util.*;

public class Wakanda1 {
    public static Scanner scn = new Scanner(System.in);

    public static void WP(int[][] arr) {
        boolean down = true;
        boolean up = false;
        int n = arr.length;
        int m = arr[0].length;
        for (int j = 0; j < m; j++) {
            if (down == true) {
                for (int i = 0; i < n; i++) {
                    System.out.print(arr[i][j] + " ");
                }
                up = true;
                down = false;
                continue;
            }
            if (up == true) {
                for (int i = n - 1; i >= 0; i--) {
                    System.out.print(arr[i][j] + " ");
                }
                up = false;
                down = true;
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
        WP(arr);
    }
}