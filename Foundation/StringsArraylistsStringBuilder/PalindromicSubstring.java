import java.util.*;

public class PalindromicSubstring {
    public static Scanner scn = new Scanner(System.in);

    public static boolean PC(String str, int si, int ei) {
        boolean ispalindrom = true;
        while (si <= ei) {
            if (str.charAt(si) == str.charAt(ei)) {
                si++;
                ei--;
                continue;
            } else {
                return false;
            }
        }
        return ispalindrom;
    }

    public static void PSC(String str) {
        for (int i = 0; i < str.length(); i++) {
            for (int j = i; j < str.length(); j++) {
                if (PC(str, i, j)) {
                    System.out.println(str.substring(i, j + 1));
                }
            }
        }
    }

    public static void main(String[] args) {
        String str = scn.nextLine();
        PSC(str);
    }
}