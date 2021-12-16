import java.util.*;

public class SudokuSolver {

    public static class pair {
        int r;
        int c;

        pair(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static boolean isSafeToPlace(int r, int c, char[][] box, int num) {
        // row
        for (int i = 0; i < 9; i++) {
            if (box[r][i] == num + '0')
                return false;
        }

        // column
        for (int i = 0; i < 9; i++) {
            if (box[i][c] == num + '0')
                return false;
        }

        // mini matrix
        int x = (r / 3) * 3;
        int y = (c / 3) * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (box[i + x][j + y] == num + '0')
                    return false;
            }
        }
        return true;
    }

    public static boolean sudokuSolver(char[][] box, ArrayList<pair> arr, int idx) {
        if (idx == arr.size()) {
            return true;
        }
        pair p = arr.get(idx);
        int r = p.r;
        int c = p.c;
        for (int i = 1; i <= 9; i++) {
            if (isSafeToPlace(r, c, box, i)) {
                box[r][c] = (char) (i + '0');
                if (sudokuSolver(box, arr, idx + 1))
                    return true;
                box[r][c] = '.';
            }
        }
        return false;
    }

    public static int[] row, col;
    public static int[][] mat;

    public static boolean sudokuSolverBits(char[][] box, ArrayList<pair> arr, int idx) {
        if (idx == arr.size()) {
            return true;
        }
        pair p = arr.get(idx);
        int r = p.r;
        int c = p.c;
        for (int i = 1; i <= 9; i++) {
            int mask = (1 << i);
            if ((row[r] & mask) == 0 && (col[c] & mask) == 0 && (mat[r / 3][c / 3] & mask) == 0) {
                row[r] ^= mask;
                col[c] ^= mask;
                mat[r / 3][c / 3] ^= mask;
                box[r][c] = (char) (i + '0');
                if (sudokuSolverBits(box, arr, idx + 1))
                    return true;
                row[r] ^= mask;
                col[c] ^= mask;
                mat[r / 3][c / 3] ^= mask;
                box[r][c] = '.';
            }
        }
        return false;
    }

    public static void sudokuSolver(char[][] box) {
        row = new int[9];
        col = new int[9];
        mat = new int[3][3];
        ArrayList<pair> arr = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (box[i][j] == '.')
                    arr.add(new pair(i, j));
                else {
                    row[i] ^= (1 << (box[i][j] + '0'));
                    col[j] ^= (1 << (box[i][j] + '0'));
                    mat[i / 3][j / 3] ^= (1 << (box[i][j] + '0'));
                }
            }
        }

        sudokuSolverBits(box, arr, 0);

    }}



//     int mask = (1 << (box[i][j] - '0'));if((row[i]&mask)==0&&(col[j]&mask)==0&&(mat[i/3][j/3]&mask)==0)
//     {

//         row[i] ^= (mask);
//         col[j] ^= (mask);
//         mat[i / 3][j / 3] ^= (mask);
//     }else{
//     return false;
// }