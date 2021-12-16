import java.util.*;

public class ASCIIDiff {
    public static Scanner scn = new Scanner(System.in);

    public static void CD(String str) {
        StringBuilder sb = new StringBuilder();
        int i;
        for (i = 0; i < str.length() - 1; i++) {
            int diff = str.charAt(i + 1) - str.charAt(i);
            sb.append(str.charAt(i)).append(diff);
        }
        sb.append(str.charAt(i));
        System.out.println(sb);
    }

    public static void main(String[] args) {
        String str = scn.nextLine();
        CD(str);
    }
}