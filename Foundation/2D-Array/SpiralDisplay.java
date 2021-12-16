import java.util.*;

public class SpiralDisplay {
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

    public static void SP(int[][] arr, int n, int m) {
        int minc = 0;
        int minr = 0;
        int maxc = m - 1;
        int maxr = n - 1;
        int total = n * m;
        int check = 0;
        while (check < total) {
            for (int i = minr, j = minc; i <= maxr && check < total; i++) {
                System.out.println(arr[i][j]);
                check++;
            }
            minc++;
            for (int j = minc, i = maxr; j <= maxc && check < total; j++) {
                System.out.println(arr[i][j]);
                check++;
            }
            maxr--;
            for (int i = maxr, j = maxc; i >= minr && check < total; i--) {
                System.out.println(arr[i][j]);
                check++;
            }
            maxc--;
            for (int j = maxc, i = minr; j >= minc && check < total; j--) {
                System.out.println(arr[i][j]);
                check++;
            }
            minr++;
        }
    }

    public static void main(String[] args) {
        int n = scn.nextInt();
        int m = scn.nextInt();
        int[][] arr = new int[n][m];
        arr = insert(n, m);
        SP(arr, n, m);
    }
}