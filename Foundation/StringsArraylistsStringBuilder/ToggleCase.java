import java.util.*;

public class ToggleCase {
    public static Scanner scn = new Scanner(System.in);

    public static String TC(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if ((int) str.charAt(i) >= 97) {
                char k = (char) ((int) str.charAt(i) - 32);
                sb.append(k);
            } else {
                char k = (char) ((int) str.charAt(i) + 32);
                sb.append(k);
            }

        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String str = scn.nextLine();
        String ans = TC(str);
        System.out.println(ans);
    }
}