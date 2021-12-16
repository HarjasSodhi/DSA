import java.util.*;

public class MazePathBacktracking {
    public static Scanner scn = new Scanner(System.in);

    public static int MP(int sr, int sc, int er, int ec, String ans, int[][] dir, char[] dirs) {
        if (sc == ec && sr == er) {
            System.out.println(ans);
            return 1;
        }
        int count = 0;
        for (int i = 0; i < dir.length; i++) {
            int r = sr + dir[i][0];
            int c = sc + dir[i][1];
            if (r <= er && c <= ec) {
                count += MP(r, c, er, ec, ans + dirs[i], dir, dirs);
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[][] dir = { { 0, 1 }, { 1, 1 }, { 1, 0 } };
        char[] dirs = { 'h', 'd', 'v' };
        int n = scn.nextInt();
        int m = scn.nextInt();
        System.out.println(MP(0, 0, n - 1, m - 1, "", dir, dirs));
    }
}