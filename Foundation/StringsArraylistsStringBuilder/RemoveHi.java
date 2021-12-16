import java.util.*;

public class RemoveHi {
    public static Scanner scn = new Scanner(System.in);

    public static void HI(String str) {
        String str1 = "";
        String str2 = "";
        int i = 0;
        while (i < str.length() - 1) {
            if (str.charAt(i) == 'h' && str.charAt(i + 1) == 'i') {
                if (i == 0) {
                    str = str.substring(i + 2, str.length());
                    continue;
                } else {
                    str1 = str.substring(0, i);
                    str2 = str.substring(i + 2, str.length());
                    str = str1 + str2;
                }
            } else
                i++;
        }
        System.out.println(str);
    }

    public static void HIT(String str) {
        String str1 = "";
        String str2 = "";
        int i = 0;
        while (i < str.length() - 1) {
            if (str.charAt(i) == 'h' && str.charAt(i + 1) == 'i'
                    && (i + 2 > str.length() - 1 || str.charAt(i + 2) != 't')) {
                if (i == 0) {
                    str = str.substring(i + 2, str.length());
                    continue;
                } else {
                    str1 = str.substring(0, i);
                    str2 = str.substring(i + 2, str.length());
                    str = str1 + str2;
                }
            } else if (str.charAt(i) == 'h' && str.charAt(i + 1) == 'i' && str.charAt(i + 2) == 't')
                i = i + 3;
            else
                i++;
        }
        System.out.println(str);
    }

    public static void main(String[] args) {
        String str = scn.nextLine();
        // HI(str);
        HIT(str);
    }
}