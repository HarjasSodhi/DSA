import java.util.*;

public class MazePathJumps {
    public static Scanner scn = new Scanner(System.in);

    public static int MPJ(int sr, int sc, int er, int ec, String ans, int[][] dir, char[] dirs, int[][] check) {
        if (sc == ec && sr == er) {
            System.out.println(ans);
            return 1;
        }
        int count = 0;
        check[sr][sc] = 1;
        for (int i = 0; i < dir.length; i++) {
            for (int rad = 1; rad <= Math.max(er + 1, ec + 1); rad++) {
                int r = sr + rad * dir[i][0];
                int c = sc + rad * dir[i][1];
                if (r >= 0 && c >= 0 && r <= er && c <= ec) {
                    if (check[r][c] == 0) {
                        count += MPJ(r, c, er, ec, ans + rad + dirs[i], dir, dirs, check);
                    }
                } else {
                    break;
                }
            }
        }
        check[sr][sc] = 0;
        return count;
    }

    public static void main(String[] args) {
        int n = scn.nextInt();
        int m = scn.nextInt();
        int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 }, { -1, 1 }, { 1, 1 }, { 1, -1 }, { -1, -1 } };
        char[] dirs = { 't', 'd', 'l', 'r', 'n', 'w', 's', 'e' };
        int[][] check = new int[n][m];
        System.out.println(MPJ(0, 0, n - 1, m - 1, "", dir, dirs, check));
    }
}