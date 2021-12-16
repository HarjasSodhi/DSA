import java.util.*;

public class ModuloRange {
    public static Scanner scn = new Scanner(System.in);


    public static int MR(int sr, int sc, int er, int ec, String ans, int[][] dir, char[] dirs, int[][] check) {
        if (sc == ec && sr == er) {
            System.out.println(ans);
            return 1;
        }
        int range=(int)1e9+7;
        check[sr][sc] = 1;
        int count = 0;
        for (int i = 0; i < dir.length; i++) {
            int r = sr + dir[i][0];
            int c = sc + dir[i][1];
            if (r >= 0 && c >= 0 && r <= er && c <= ec && check[r][c] == 0) {
                count =(count%range + MR(r, c, er, ec, ans + dirs[i], dir, dirs, check)%range)%range;
            }
        }
        check[sr][sc] = 0;
        return count;
    }

    /*
     --------------MODULAS RANGE FORMULAS----------
     FOR RANGE (-C,C)
     FOR A+B=(A%C + B%C) %C
     FOR A-B=(A%C - B%C + C)%C
     FOR A*B=(A%C * B%C)%C
     NO FORMULA FOR division
     */


    public static void main(String[] args) {
        int[][] dir = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };
        char[] dirs = { 't', 'l', 'd', 'r' };
        int n = scn.nextInt();
        int m = scn.nextInt();
        /*
        for question------https://practice.geeksforgeeks.org/problems/special-matrix4201/1
        int[][] check;
        for(int i=1;i<=blocked.length;i++){
                int row=blocked[i][0]-1;
                int column=blocked[i][1]-1;
                check[row][column]=1;

        */
        int[][] check = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++)
                check[i][j] = scn.nextInt();
        }
         System.out.println(MR(0, 0, n - 1, m - 1, "", dir, dirs, check));
        
    }
}