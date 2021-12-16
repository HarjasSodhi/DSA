import java.util.*;

public class Wakanda2 {
    public static Scanner scn = new Scanner(System.in);

    public static int[][] insert(int n, int m) {
        int[][] arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = scn.nextInt();
            }
        }
        return arr;
    }

    public static void WP2(int[][] arr) {
        for (int g = 0; g < arr[0].length + 1; g++) {
            for (int i = 0, j = g; i < arr.length && j < arr[0].length; i++, j++) {
                System.out.print(arr[i][j] + " ");
            }
        }
    }
    // public static void WW22(int[][]arr) {
    // for (int g = 0; g < arr.length; g++) {
    // for (int i = 0,j=g; i < arr.length-g&&j<arr[0].length; i++,j++) {
    // System.out.println(arr[i][j]);
    // }
    // }
    // }

    public static void main(String[] args) {
        int n = scn.nextInt();
        int m = scn.nextInt();
        int[][] arr = new int[n][m];
        arr = insert(n, m);
        WP2(arr);
        // WW22(arr);
    }
}