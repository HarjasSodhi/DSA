import java.util.*;

public class MazePathsAL {
    public static Scanner scn = new Scanner(System.in);

    public static ArrayList<String> MP(int sr, int sc, int er, int ec) {
        if (sr == er && sc == ec) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }
        ArrayList<String> myAns = new ArrayList<>();
        if (sc <= ec) {
            ArrayList<String> recans1 = MP(sr, sc + 1, er, ec);
            for (String s : recans1) {
                myAns.add('h' + s);
            }
        }
        if (sr <= er) {
            ArrayList<String> recans2 = MP(sr + 1, sc, er, ec);
            for (String s : recans2) {
                myAns.add('v' + s);
            }
        }
        if(sr<=er&&sc<=ec){
            ArrayList<String> recans3 = MP(sr + 1, sc+1, er, ec);
            for (String s : recans3) {
                myAns.add('d' + s);
            }
        }
        return myAns;
    }

    public static void main(String[] args) {
        int n = scn.nextInt();
        int m = scn.nextInt();
        System.out.println(MP(0, 0, n - 1, m - 1));
    }
}