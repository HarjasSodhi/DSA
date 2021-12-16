public class validSudoku {
    // this will not work as it is not covering all the cases of and condition;
    // int mask = (1 << (box[i][j] -
    // '0'));if(((row[i]&mask)==1)||((col[j]&mask)==1)||((mat[i/3][j/3]&mask)==1))
    // {
    // return false;
    // }else
    // {
    // row[i] ^= (mask);
    // col[j] ^= (mask);
    // mat[i / 3][j / 3] ^= (mask);
    // }

    // this will work
    public boolean isValidSudoku(char[][] box) {
        int[] row = new int[9];
        int[] col = new int[9];
        int[][] mat = new int[3][3];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (box[i][j] == '.')
                    continue;
                else {
                    int mask = (1 << (box[i][j] - '0'));
                    if ((row[i] & mask) == 0 && (col[j] & mask) == 0 && (mat[i / 3][j / 3] & mask) == 0) {

                        row[i] ^= (mask);
                        col[j] ^= (mask);
                        mat[i / 3][j / 3] ^= (mask);
                    } else {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}