import java.util.*;

public class LongestLength {
    public static Scanner scn = new Scanner(System.in);

    public static int LL(int sr, int sc, int er, int ec, int[][] dir, int[][] check) {
        if (sc == ec && sr == er) {
            return 0;
        }
        check[sr][sc] = 1;
        int LongestLength = -1;
        for (int i = 0; i < dir.length; i++) {
            int r = sr + dir[i][0];
            int c = sc + dir[i][1];
            if (r >= 0 && c >= 0 && r <= er && c <= ec && check[r][c] == 0) {
                int recans = LL(r, c, er, ec, dir, check);
                if (recans != -1 && recans + 1 > LongestLength) {
                    LongestLength = recans + 1;
                }
            }
        }
        check[sr][sc] = 0;
        return LongestLength;
    }

    public static void main(String[] args) {
        int[][] dir = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };
        int n = scn.nextInt();
        int m = scn.nextInt();
        int[][] check = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++)
                check[i][j] = scn.nextInt();
        }
        System.out.println(LL(0, 0, n - 1, m - 1, dir, check));
    }
}