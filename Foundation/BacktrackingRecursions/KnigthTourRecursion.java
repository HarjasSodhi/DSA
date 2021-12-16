import java.util.*;

public class KnigthTourRecursion {
    public static Scanner scn = new Scanner(System.in);

    // public static boolean KNT(int sr, int sc, int er, int ec, int move, int[]
    // dirX, int[] dirY, int[][] check) {
    // check[sr][sc]=move;
    // if (move==(er*ec)-1){
    // return true;
    // }
    // boolean recans=false;
    // for (int i = 0; i < 8; i++) {
    // int r = sr + dirX[i];
    // int c = sc + dirY[i];
    // if (r >= 0 && c >= 0 && r <= er && c <= ec && check[r][c] == -1) {
    // recans= recans||KNT(r, c, er, ec, move+1, dirX, dirY, check);
    // if(recans){
    // return recans;
    // }
    // }
    // }
    // check[sr][sc] = -1;
    // return recans;
    // }

    public static boolean knightTour(int sr, int sc, int[][] board, int move, int[] dirX, int[] dirY) {
        board[sr][sc] = move; // block

        if (move == 63) {
            return true;
        }

        boolean res = false;
        for (int d = 0; d < 8; d++) {
            int r = sr + dirX[d];
            int c = sc + dirY[d];

            if (r >= 0 && c >= 0 && r < board.length && c < board[0].length && board[r][c] == -1) {
                res = res || knightTour(r, c, board, move + 1, dirX, dirY);
                if (res)
                    return res;

            }
        }

        board[sr][sc] = -1; // free
        return res;
    }

    public static void knightTour() {
        int n = 8;
        int[][] board = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                board[i][j] = -1;

        int[] dirX = { 2, 1, -1, -2, -2, -1, 1, 2 };
        int[] dirY = { 1, 2, 2, 1, -1, -2, -2, -1 };
        knightTour(0, 0, board, 0, dirX, dirY);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                System.out.print(board[i][j] + " ");
            System.out.println();
        }

    }

    public static void main(String[] args) {
        // int[] dirX = { 2, 1, -1, -2, -2, -1, 1, 2 };
        // int[] dirY = { 1, 2, 2, 1, -1, -2, -2, -1 };
        // int move=0;
        // int n = scn.nextInt();
        // int[][] check = new int[n][n];
        // for (int i = 0; i < n; i++) {
        // for (int j = 0; j < n; j++)
        // check[i][j] = -1;
        // }
        // KNT(0, 0, n - 1, n - 1,move,dirX,dirY,check);

        // for (int i = 0; i < n; i++) {
        // for (int j = 0; j < n; j++)
        // System.out.print(check[i][j]+"\t");
        // System.out.println();
        // }
        // }
        knightTour();
    }
}