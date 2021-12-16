import java.util.*;

public class Subsequence {
    public static Scanner scn = new Scanner(System.in);

    public static void SBQ1(String ques, String ans) {
        if (ques.length() == 0) {
            System.out.println(ans);
            return;
        }
        char ch = ques.charAt(0);
        SBQ1(ques.substring(1), ans);
        SBQ1(ques.substring(1), ans + ch);

    }

    public static int SBQ2(String ques, String ans) {
        if (ques.length() == 0) {
            System.out.println(ans);
            return 1;
        }
        int count = 0;
        char ch = ques.charAt(0);
        count += SBQ2(ques.substring(1), ans);
        count += SBQ2(ques.substring(1), ans + ch);
        return count;
    }

    public static void SBQ3(String ques, String ans, ArrayList<String> res) {
        if (ques.length() == 0) {
            res.add(ans);
            return;
        }
        char ch = ques.charAt(0);
        SBQ3(ques.substring(1), ans, res);
        SBQ3(ques.substring(1), ans + ch, res);

    }

    public static void main(String[] args) {
        String ques = scn.nextLine();

        // int a=SBQ2(ques,"");
        // System.out.println(a);
        ArrayList<String> res = new ArrayList<>();
        SBQ3(ques, "", res);
        for (int i = res.size() - 1; i >= 0; i--) {
            System.out.println(res.get(i));
        }
    }
}