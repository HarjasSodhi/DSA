import java.util.*;

public class ShellRotate {
    public static Scanner scn = new Scanner(System.in);

    public static void display(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void SR(int[][] arr, int s, int r) {
        int minc = s - 1;
        int minr = s - 1;
        int maxr = arr.length - s;
        int maxc = arr[0].length - s;
        int r2d = arr.length;
        int c2d = arr[0].length;
        int rshell = r2d - 2 * (s - 1);
        int cshell = c2d - 2 * (s - 1);
        int szshell = 2 * (rshell + cshell) - 4;
        r = r % szshell;
        if (r < 0) {
            r = r + szshell;
        }
        AR(arr, minc, maxc, minr, maxr, r);
    }

    public static void AR(int[][] arr, int minc, int maxc, int minr, int maxr, int r) {
        for (int j = 1; j <= r; j++) {
            int temp = arr[minr][minc];
            for (int i = minc; i < maxc; i++) {
                arr[minr][i] = arr[minr][i + 1];
            }
            for (int i = minr; i < maxr; i++) {
                arr[i][maxc] = arr[i + 1][maxc];
            }
            for (int i = maxc; i > minc; i--) {
                arr[maxr][i] = arr[maxr][i - 1];
            }
            for (int i = maxr; i > minr; i--) {
                arr[i][minc] = arr[i - 1][minc];
            }
            arr[minr + 1][minc] = temp;
        }
        display(arr);
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
        int s = scn.nextInt();
        int r = scn.nextInt();
        SR(arr, s, r);
    }
}