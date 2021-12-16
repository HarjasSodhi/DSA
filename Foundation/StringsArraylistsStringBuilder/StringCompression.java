import java.util.*;

public class StringCompression {
    public static Scanner scn = new Scanner(System.in);

    public static void SC(String str) {
        String ans1 = "";
        String ans2 = "";
        int count = 1;
        for (int i = 0; i < str.length(); i++) {
            if (i == str.length() - 1) {
                ans1 = ans1 + str.charAt(i);
                ans2 = ans2 + str.charAt(i);
                if (count > 1) {
                    ans1 = ans1 + count;
                }
            } else if (str.charAt(i) == str.charAt(i + 1)) {
                count++;
            } else {
                ans2 = ans2 + str.charAt(i);
                ans1 = ans1 + str.charAt(i);
                if (count > 1) {
                    ans1 = ans1 + count;
                }
                count = 1;
            }
        }
        System.out.println(ans2);
        System.out.println(ans1);
    }

    public static void main(String[] args) {
        String str = scn.nextLine();
        SC(str);
    }
}