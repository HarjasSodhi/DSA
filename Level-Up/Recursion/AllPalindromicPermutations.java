import java.util.*;

public class AllPalindromicPermutations {

    public static Scanner scn = new Scanner(System.in);

    public static boolean isPalindrome(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != str.charAt(str.length() - i - 1))
                return false;
        }
        return true;
    }

    // method 1
    public static void printPermutations(String ques, String asf, ArrayList<String> ans) {
        if (ques.length() == 0) {
            if (isPalindrome(asf) && !ans.contains(asf))
                ans.add(asf);
            return;
        }
        for (int i = 0; i < ques.length(); i++) {
            printPermutations(ques.substring(0, i) + ques.substring(i + 1, ques.length()), asf + ques.charAt(i), ans);
        }
    }

    // method 2
    public static void generatepw(int cs, int ts, HashMap<Character, Integer> fmap, Character oddc, String asf) {
        if (cs == ts) {
            String rev = "";
            for (int i = asf.length() - 1; i >= 0; i--) {
                rev += asf.charAt(i);
            }

            if (oddc != null) {
                asf += oddc;
            }
            asf += rev;
            System.out.println(asf);
            return;
        }
        for (char ch : fmap.keySet()) {
            int freq = fmap.get(ch);
            if (freq > 0) {
                fmap.put(ch, freq - 1);
                generatepw(cs + 1, ts, fmap, oddc, asf + ch);
                fmap.put(ch, freq);
            }
        }
    }

    public static void main(String[] args) {
        String str = scn.next();
        HashMap<Character, Integer> fmap = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            fmap.put(ch, fmap.getOrDefault(ch, 0) + 1);
        }

        // write your code here
        Character oddc = null;
        int oddNum = 0;
        int len = 0;
        for (char ch : fmap.keySet()) {
            int freq = fmap.get(ch);
            if (freq % 2 != 0) {
                oddc = ch;
                oddNum++;
            }
            freq = freq / 2;
            fmap.put(ch, freq);
            len += freq;
        }
        if (oddNum > 1) {
            System.out.println(-1);
            return;
        }
        generatepw(0, len, fmap, oddc, "");
    }

}