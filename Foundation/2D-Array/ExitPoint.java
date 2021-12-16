import java.util.*;

public class ExitPoint {
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

    public static void EP(int[][] arr) {
        int i = 0;
        int j = 0;
        while (true) {
            for (int k = j; k <= arr[0].length; k++) {
                if (arr[i][j] == 0) {
                    j++;
                    if (j >= arr[0].length) {
                        System.out.println(i);
                        System.out.println(j - 1);
                        return;
                    }
                } else {
                    i++;
                    break;
                }
            }
            for (int l = i; l <= arr.length; l++) {
                if (arr[i][j] == 0) {
                    i++;
                    if (i >= arr.length) {
                        System.out.println(i - 1);
                        System.out.println(j);
                        return;
                    }
                } else {
                    j--;
                    break;
                }
            }
            for (int k = j; k >= -1; k--) {
                if (arr[i][j] == 0) {
                    j--;
                    if (j < 0) {
                        System.out.println(i);
                        System.out.println(j + 1);
                        return;
                    }
                } else {
                    i--;
                    break;
                }
            }
            for (int l = i; l >= -1; l--) {
                if (arr[i][j] == 0) {
                    i--;
                    if (i < 0) {
                        System.out.println(i + 1);
                        System.out.println(j);
                        return;
                    }
                } else {
                    j++;
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        int n = scn.nextInt();
        int m = scn.nextInt();
        int[][] arr = new int[n][m];
        arr = insert(n, m);
        EP(arr);
    }
}