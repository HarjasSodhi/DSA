import java.util.*;

public class SaddlePoint {
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

    public static void SP(int[][] arr) {
        int JofMin = -1;
        for (int i = 0; i < arr.length; i++) {
            int max = (int) -1e9 - 1;
            int min = (int) 1e9 + 1;
            for (int j = 0; j < arr[0].length; j++) {
                if (min > arr[i][j]) {
                    min = arr[i][j];
                    JofMin = j;
                }
            }
            for (int k = 0; k < arr.length; k++) {
                if (arr[k][JofMin] > max) {
                    max = arr[k][JofMin];
                }
            }
            if (max == min) {
                System.out.println(max);
                return;
            }
        }
        System.out.println("Invalid input");
    }

    public static void main(String[] args) {
        int n = scn.nextInt();
        int m = scn.nextInt();
        int[][] arr = new int[n][m];
        arr = insert(n, m);
        SP(arr);
    }
}