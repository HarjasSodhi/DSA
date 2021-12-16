import java.util.*;

public class FindInArray {
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

    public static boolean FindEle(int[][] arr, int data) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (arr[i][j] == data) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int n = scn.nextInt();
        int m = scn.nextInt();
        int[][] arr = new int[n][m];
        arr = insert(n, m);
        int data = scn.nextInt();
        if (FindEle(arr, data))
            System.out.println("true");
        else
            System.out.println("false");
    }
}