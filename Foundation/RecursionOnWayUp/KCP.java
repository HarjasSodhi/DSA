import java.util.*;

public class KCP {
    public static Scanner scn = new Scanner(System.in);

    public static void NKPC1(String ques, String ans, String[] arr, ArrayList<String> res) {
        if (ques.length() == 0) {
            res.add(ans);
            return;
        }

        char ch = ques.charAt(0);
        int idx = ch - '0';
        String letters = arr[idx];
        for (int i = 0; i < letters.length(); i++) {
            NKPC1(ques.substring(1), ans + letters.charAt(i), arr, res);
        }
    }

    public static int NKPC2(String ques, String ans, String[] arr, ArrayList<String> res) {
        if (ques.length() == 0) {
            res.add(ans);
            return 1;
        }

        char ch = ques.charAt(0);
        int idx = ch - '0';
        int count = 0;
        String letters = arr[idx];
        for (int i = 0; i < letters.length(); i++) {
            count += NKPC2(ques.substring(1), ans + letters.charAt(i), arr, res);
        }
        return count;
    }

    public static void main(String[] args) {
        String ques = scn.nextLine();
        String[] arr = { ".;", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tu", "vwx", "yz" };
        ArrayList<String> res = new ArrayList<>();
        int n = NKPC2(ques, "", arr, res);
        System.out.println(n);
        for (String string : res) {
            System.out.println(string);
        }
    }
}