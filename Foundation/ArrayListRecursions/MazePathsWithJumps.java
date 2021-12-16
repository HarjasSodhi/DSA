import java.util.*;

public class MazePathsWithJumps {
    public static Scanner scn = new Scanner(System.in);

    public static ArrayList<String> MPJ(int sr, int sc, int er, int ec) {
        if (sr == er && sc == ec) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }
        ArrayList<String> myAns = new ArrayList<>();
        for (int i = 1; sc + i <= ec; i++) {
            ArrayList<String> recans1 = MPJ(sr, sc + i, er, ec);
            for (String s : recans1) {
                myAns.add("h" + i + s);
            }
        }
        for (int i = 1; sr + i <= er; i++) {
            ArrayList<String> recans2 = MPJ(sr + i, sc, er, ec);
            for (String s : recans2) {
                myAns.add("v" + i + s);
            }
        }
        for (int i = 1; sc + i <= ec && sr + i <= er; i++) {
            ArrayList<String> recans3 = MPJ(sr + i, sc + i, er, ec);
            for (String s : recans3) {
                myAns.add("d" + i + s);
            }
        }
        return myAns;
    }

    public static void main(String[] args) {
        int n = scn.nextInt();
        int m = scn.nextInt();
        System.out.println(MPJ(0, 0, n - 1, m - 1));
    }
}