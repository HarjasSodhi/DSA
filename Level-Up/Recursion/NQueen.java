public class NQueen {

    public static boolean isSafeCombi(int sr, int sc, boolean[][] box) {
        int n = box.length;
        int m = box[0].length;
        int[][] dir = { { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 } };
        for (int i = 0; i < dir.length; i++) {
            for (int rad = 1; rad < n * m; rad++) {
                int r = sr + rad * dir[i][0];
                int c = sc + rad * dir[i][1];
                if (r >= 0 && c >= 0 && r < n && c < m) {
                    if (box[r][c]) {
                        return false;
                    }
                } else {
                    break;
                }
            }
        }
        return true;
    }

    public static int nQueenCombi(boolean[][] box, int bno, int tnq, String asf) {
        if (tnq == 0) {
            System.out.println(asf);
            return 1;
        }
        int n = box.length;
        int m = box[0].length;
        int count = 0;
        for (int i = bno; i < n * m; i++) {
            int r = i / m;
            int c = i % m;
            if (isSafeCombi(r, c, box)) {
                box[r][c] = true;
                count += nQueenCombi(box, i + 1, tnq - 1, asf + i + " ");
                box[r][c] = false;
            }
        }
        return count;
    }

    public static boolean isSafePerm(int sr, int sc, boolean[][] box) {
        int n = box.length;
        int m = box[0].length;
        int[][] dir = { { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 } };
        for (int i = 0; i < dir.length; i++) {
            for (int rad = 1; rad < n * m; rad++) {
                int r = sr + rad * dir[i][0];
                int c = sc + rad * dir[i][1];
                if (r >= 0 && c >= 0 && r < n && c < m) {
                    if (box[r][c]) {
                        return false;
                    }
                } else {
                    break;
                }
            }
        }
        return true;
    }

    public static int nQueenPerm(boolean[][] box, int bno, int tnq, String asf) {
        if (tnq == 0) {
            System.out.println(asf);
            return 1;
        }
        int n = box.length;
        int m = box[0].length;
        int count = 0;
        for (int i = bno; i < n * m; i++) {
            int r = i / m;
            int c = i % m;
            if (isSafePerm(r, c, box) && !box[r][c]) {
                box[r][c] = true;
                count += nQueenPerm(box, 0, tnq - 1, asf + i + " ");
                box[r][c] = false;
            }
        }
        return count;
    }

    // isSafe optimization //shadow Method//branch and bound method
    public static boolean[] row, col, diag, adiag;

    public static int nQueen_03_combi(boolean[][] box, int bno, int tnq, String asf) {
        if (tnq == 0) {
            System.out.println(asf);
            return 1;
        }
        int n = box.length;
        int m = box[0].length;
        int count = 0;
        for (int i = bno; i < n * m; i++) {
            int r = i / m;
            int c = i % m;
            if (!row[r] && !col[c] && !diag[r + c] && !adiag[r - c + m - 1]) {
                row[r] = col[c] = diag[r + c] = adiag[r - c + m - 1] = true;
                count += nQueen_03_combi(box, i + 1, tnq - 1, asf + i + " ");
                row[r] = col[c] = diag[r + c] = adiag[r - c + m - 1] = false;
            }
        }
        return count;
    }

    public static boolean nQueen_03_combi_onlyOneAns(boolean[][] box, int bno, int tnq, String asf) {
        if (tnq == 0) {
            System.out.println(asf);
            return true;
        }
        int n = box.length;
        int m = box[0].length;
        boolean isFound = false;
        for (int i = bno; i < n * m; i++) {
            int r = i / m;
            int c = i % m;
            if (!row[r] && !col[c] && !diag[r + c] && !adiag[r - c + m - 1]) {
                row[r] = col[c] = diag[r + c] = adiag[r - c + m - 1] = true;
                isFound = isFound || nQueen_03_combi_onlyOneAns(box, i + 1, tnq - 1, asf + i + " ");
                row[r] = col[c] = diag[r + c] = adiag[r - c + m - 1] = false;
                if (isFound)
                    return true;
            }
        }
        return isFound;
    }

    public static int nQueen_03_Perm(boolean[][] box, int bno, int tnq, String asf) {
        if (tnq == 0) {
            System.out.println(asf);
            return 1;
        }
        int n = box.length;
        int m = box[0].length;
        int count = 0;
        for (int i = bno; i < n * m; i++) {
            int r = i / m;
            int c = i % m;
            if (!row[r] && !col[c] && !diag[r + c] && !adiag[r - c + m - 1]) {
                row[r] = col[c] = diag[r + c] = adiag[r - c + m - 1] = true;
                count += nQueen_03_Perm(box, 0, tnq - 1, asf + i + " ");
                row[r] = col[c] = diag[r + c] = adiag[r - c + m - 1] = false;
            }
        }
        return count;
    }

    public static int nQueen_03_combi_final(boolean[][] box, int bno, int tnq, String asf, int floor) {
        if (tnq == 0) {
            System.out.println(asf);
            return 1;
        }
        int m = box[0].length;
        int count = 0;
        for (int i = 0; i < m; i++) {
            int r = floor;
            int c = i;
            if (!col[c] && !diag[r + c] && !adiag[r - c + m - 1]) {
                col[c] = diag[r + c] = adiag[r - c + m - 1] = true;
                count += nQueen_03_combi_final(box, i + 1, tnq - 1, asf + "(" + floor + " ," + i + ") ", floor + 1);
                col[c] = diag[r + c] = adiag[r - c + m - 1] = false;
            }
        }
        return count;
    }

    public static int nQueen_03_Perm_final(boolean[][] box, int bno, int tnq, String asf, int floor) {
        if (tnq == 0) {
            System.out.println(asf);
            return 1;
        }
        int n = box.length;
        int m = box[0].length;
        int count = 0;
        for (int j = 0; j < n; j++) {
            int r = j;
            if (!row[r]) {
                row[r] = true;
                for (int i = 0; i < m; i++) {
                    int c = i;
                    if (!col[c] && !diag[r + c] && !adiag[r - c + m - 1]) {
                        col[c] = diag[r + c] = adiag[r - c + m - 1] = true;
                        count += nQueen_03_Perm_final(box, i + 1, tnq - 1, asf + "(" + floor + "," + +i + ") ", j);
                        col[c] = diag[r + c] = adiag[r - c + m - 1] = false;
                    }
                }
                row[r] = false;
            }
        }
        return count;
    }

    public static int r = 0, c = 0, d = 0, ad = 0;

    public static int nQueen_03_combi_Bits(boolean[][] box, int bno, int tnq, String asf, int floor) {
        if (tnq == 0) {
            System.out.println(asf);
            return 1;
        }
        int m = box[0].length;
        int count = 0;
        for (int i = 0; i < m; i++) {
            int row = floor;
            int column = i;
            if ((c & (1 << column)) == 0 && (d & (1<<(row + column))) == 0 && (ad & (1<<(row - column + m - 1))) == 0) {
                c ^= (1 << column);
                d ^= (1 << (row + column));
                ad ^= (1 << (row - column + m - 1));
                count += nQueen_03_combi_Bits(box, i + 1, tnq - 1, asf + "(" + floor + " ," + i + ") ", floor + 1);
                c ^= (1 << column);
                d ^= (1 << (row + column));
                ad ^= (1 << (row - column + m - 1));
            }
        }
        return count;
    }

    public static void nqueen() {
        int n = 4, m = 4, tnq = 4;
        boolean[][] box = new boolean[n][m];
        // System.out.println(nQueenPerm(box, 0, tnq, ""));
        row = new boolean[n];
        col = new boolean[m];
        diag = new boolean[n + m - 1];
        adiag = new boolean[(n + m) - 1];
        System.out.println(nQueen_03_combi_Bits(box, 0, tnq, "", 0));
    }

    public static void main(String[] args) {
        nqueen();
    }
}