import java.util.*;

public class SearchInSorted {
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

    public static void SS(int[][] arr, int data) {
        int n = arr.length;
        int m = arr[0].length;
        for (int i = 0; i < n; i++) {
            if (arr[i][0] < data && arr[i][m - 1] > data) {
                int si = 0;
                int ei = m - 1;
                while (si <= ei) {
                    int mid = (si + ei) / 2;
                    if (arr[i][mid] == data) {
                        System.out.println(i);
                        System.out.println(mid);
                        return;
                    } else if (data > arr[i][mid])
                        si = mid + 1;
                    else
                        ei = mid - 1;
                }
            }
        }
        System.out.println("Not Found");
    }

    public static void main(String[] args) {
        int n = scn.nextInt();
        int m = scn.nextInt();
        int[][] arr = new int[n][m];
        arr = insert(n, m);
        int data = scn.nextInt();
        SS(arr, data);
    }
}