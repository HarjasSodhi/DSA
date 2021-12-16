import java.util.*;

public class StairPath {
    public static Scanner scn = new Scanner(System.in);

    public static void SP1(int ques, String ans, ArrayList<String> res) {
        if (ques == 0) {
            res.add(ans);
            return;
        }
        for (int i = 1; i <= 3 && ques - 1 >= 0; i++) {
            SP1(ques - i, ans + i, res);
        }
    }

    public static int SP2(int ques, String ans, ArrayList<String> res) {
        if (ques == 0) {
            res.add(ans);
            return 1;
        }
        int count = 0;
        for (int i = 1; i <= 3 && ques - 1 >= 0; i++) {
            count += SP2(ques - i, ans + i, res);
        }
        return count;
    }

    public static void main(String[] args) {
        int ques = scn.nextInt();
        ArrayList<String> res = new ArrayList<>();
        int n = SP2(ques, "", res);
        System.out.println(n);
        for (String string : res) {
            System.out.println(string);
        }
    }
}