import java.util.*;

public class FloodFill {
    public static Scanner scn = new Scanner(System.in);

    public static int FF(int sr, int sc, int er, int ec, String ans, int[][] dir, char[] dirs, int[][] check) {
        if (sc == ec && sr == er) {
            System.out.println(ans);
            return 1;
        }
        check[sr][sc] = -1;
        int count = 0;
        for (int i = 0; i < dir.length; i++) {
            int r = sr + dir[i][0];
            int c = sc + dir[i][1];
            if (r >= 0 && c >= 0 && r <= er && c <= ec && check[r][c] == 1) {
                count += FF(r, c, er, ec, ans + dirs[i], dir, dirs, check);
            }
        }
        check[sr][sc] = 1;
        return count;
    }

    public static int FF2(int sr, int sc, int er, int ec, String ans, int[][] dir, char[] dirs, int[][] check) {
        if (sc == ec && sr == er) {
            System.out.println(ans);
            return 1;
        }
        check[sr][sc] = 1;
        int count = 0;
        for (int i = 0; i < dir.length; i++) {
            int r = sr + dir[i][0];
            int c = sc + dir[i][1];
            if (r >= 0 && c >= 0 && r <= er && c <= ec && check[r][c] == 0) {
                count += FF2(r, c, er, ec, ans + dirs[i], dir, dirs, check);
            }
        }
        check[sr][sc] = 0;
        return count;
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
         System.out.println(FF(0, 0, n - 1, m - 1, "", dir, dirs, check));
        //System.out.println(FF2(0, 0, n - 1, m - 1, "", dir, dirs, check));
    }
}