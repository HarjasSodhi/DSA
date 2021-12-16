import java.util.*;

public class Return1PathOnly {
    public static Scanner scn = new Scanner(System.in);

    // public static boolean FP(int sr, int sc, int er, int ec, String ans, int[][]
    // dir, char[] dirs) {
    // if (sr == er && sc == ec) {
    // System.out.println(ans);
    // return true;
    // }
    // boolean res = false;
    // for (int i = 0; i < dir.length; i++) {
    // int r = sr + dir[i][0];
    // int c = sc + dir[i][1];
    // if (r >= 0 && c >= 0 && r <= er && c <= er) {
    // res = res || FP(r, c, er, ec, ans + dirs[i], dir, dirs);
    // }
    // }
    // return res;
    // }

    public static boolean FP(int sr, int sc, int er, int ec, String ans, int[][] dir, char[] dirs, int[][] check) {
        if (sc == ec && sr == er) {
            System.out.println(ans);
            return true;
        }
        check[sr][sc] = -1;
        boolean res = false;
        for (int i = 0; i < dir.length; i++) {
            int r = sr + dir[i][0];
            int c = sc + dir[i][1];
            if (r >= 0 && c >= 0 && r <= er && c <= ec && check[r][c] == 1) {
                res = res || FP(r, c, er, ec, ans + dirs[i], dir, dirs, check);
            }
        }
        check[sr][sc] = 1;
        return res;
    }

    public static void main(String[] args) {
        int[][] dir = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };
        char[] dirs = { 't', 'l', 'd', 'r' };
        int n = scn.nextInt();
        int m = scn.nextInt();
        int[][] check = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++)
                check[i][j] = scn.nextInt();
        }
        FP(0, 0, n - 1, m - 1, "", dir, dirs, check);
    }
}