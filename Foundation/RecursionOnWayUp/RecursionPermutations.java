import java.util.*;

public class RecursionPermutations {
    public static Scanner scn = new Scanner(System.in);

    public static void PR(String ques, String ans) {
        if (ques.length() == 0) {
            System.out.println(ans);
            return;
        }
        for (int i = 0; i < ques.length(); i++) {
            if (i == 0)
                PR(ques.substring(1), ans + ques.charAt(0));
            else if (i == ques.length() - 1)
                PR(ques.substring(0, ques.length() - 1), ans + ques.charAt(ques.length() - 1));
            else
                PR(ques.substring(0, i) + ques.substring(i + 1, ques.length()), ans + ques.charAt(i));
        }
    }

    public static void PNR1(String ques) {
        int[] freq = new int[26];
        for (int i = 0; i < ques.length(); i++) {
            freq[ques.charAt(i) - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < freq.length; i++) {
            for (int j = 0; j < freq[i]; j++) {
                sb.append((char) (i + 'a'));
            }
        }
        PNR2(sb.toString(), "");
    }

    public static void PNR2(String ques, String ans) {
        if (ques.length() == 0) {
            System.out.println(ans);
            return;
        }
        char prev = '$';
        for (int i = 0; i < ques.length(); i++) {
            if (ques.charAt(i) != prev) {
                String ros = ques.substring(0, i) + ques.substring(i + 1);
                PNR2(ros, ans + ques.charAt(i));
            }
            prev = ques.charAt(i);
        }
    }

    public static void main(String[] args) {
        String ques = scn.nextLine();
        // PR(ques, "");
        PNR1(ques);
    }
}