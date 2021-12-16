public class CrossWord {
    static char[][] box = { { '+', '-', '+', '+', '+', '+', '+', '+', '+', '+' },
            { '+', '-', '+', '+', '+', '+', '+', '+', '+', '+' }, { '+', '-', '-', '-', '-', '-', '-', '-', '+', '+' },
            { '+', '-', '+', '+', '+', '+', '+', '+', '+', '+' }, { '+', '-', '+', '+', '+', '+', '+', '+', '+', '+' },
            { '+', '-', '-', '-', '-', '-', '-', '+', '+', '+' }, { '+', '-', '+', '+', '+', '-', '+', '+', '+', '+' },
            { '+', '+', '+', '+', '+', '-', '+', '+', '+', '+' }, { '+', '+', '+', '+', '+', '-', '+', '+', '+', '+' },
            { '+', '+', '+', '+', '+', '+', '+', '+', '+', '+' } };

    static String[] words = { "agra", "norway", "england", "gwalior" };

    public static void print(char[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }
    }

    public static boolean isSafeH(int r, int c, int idx) {
        String word = words[idx];
        int l = word.length();
        int len = 0, C = c;

        while (C < 10 && box[r][C++] != '+') {
            len++;
        }

        if (len != l || (c > 0 && (box[r][c - 1] != '+' && box[r][c - 1] != word.charAt(0))))
            return false;

        for (int i = 0; i < word.length(); i++) {
            if (box[r][c + i] != '-' && box[r][c + i] != word.charAt(i))
                return false;
        }

        return true;
    }

    public static boolean isSafeV(int r, int c, int idx) {
        String word = words[idx];
        int l = word.length();
        int len = 0, R = r;
        while (R < 10 && box[R++][c] != '+') {
            len++;
        }

        if (len != l || (r > 0 && (box[r - 1][c] != '+' && box[r - 1][c] != word.charAt(0))))
            return false;

        for (int i = 0; i < word.length(); i++) {
            if (box[r + i][c] != '-' && box[r + i][c] != word.charAt(i))
                return false;
        }

        return true;
    }

    public static int placeH(int r, int c, int idx) {
        int loc = 0;
        String word = words[idx];
        for (int i = 0; i < word.length(); i++) {
            if (box[r][c + i] == '-') {
                box[r][c + i] = word.charAt(i);
                loc ^= (1 << i);
            }
        }
        return loc;
    }

    public static int placeV(int r, int c, int idx) {
        int loc = 0;
        String word = words[idx];
        for (int i = 0; i < word.length(); i++) {
            if (box[r + i][c] == '-') {
                box[r + i][c] = word.charAt(i);
                loc ^= (1 << i);
            }
        }
        return loc;
    }

    public static void unPlaceH(int r, int c, int idx, int loc) {
        String word = words[idx];
        for (int i = 0; i < word.length(); i++) {
            if ((loc & (1 << i)) != 0) {
                box[r][c + i] = '-';
                // loc^=(1<<i);//not neccesary as this int is not global and is getting
                // destroyed;
            }
        }
    }

    public static void unPlaceV(int r, int c, int idx, int loc) {
        String word = words[idx];
        for (int i = 0; i < word.length(); i++) {
            if ((loc & (1 << i)) != 0) {
                box[r + i][c] = '-';
                // loc^=(1<<i);//not neccesary as this int is not global and is getting
                // destroyed;
            }
        }
    }

    public static void crossWord(int idx) {
        if (idx == words.length) {
            print(box);
            return;
        }

        for (int i = 0; i < box.length; i++) {
            for (int j = 0; j < box[0].length; j++) {
                boolean as = isSafeV(i, j, idx);
                if (as) {
                    int loc = placeV(i, j, idx);
                    crossWord(idx + 1);
                    unPlaceV(i, j, idx, loc);
                }
                boolean sa = isSafeH(i, j, idx);
                if (sa) {
                    int loc = placeH(i, j, idx);
                    crossWord(idx + 1);
                    unPlaceH(i, j, idx, loc);
                }
            }
        }

    }

    public static void main(String[] args) {
        crossWord(0);
    }
}
