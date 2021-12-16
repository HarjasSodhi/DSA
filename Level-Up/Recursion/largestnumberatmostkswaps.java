import java.util.*;

public class largestnumberatmostkswaps {

    public static Scanner scn = new Scanner(System.in);
    public static int max;

    public static void findMaximum(String str, int k, int i) {
        int curr = Integer.parseInt(str);
        if (curr > max)
            max = curr;
        if (k == 0 || i == str.length()) {
            return;
        }
        int idx = i + 1;
        while (idx < str.length()) {
            String newStr = str.substring(0, i) + str.charAt(idx) + str.substring(i + 1, idx) + str.charAt(i)
                    + str.substring(idx + 1);

            findMaximum(newStr, k - 1, i + 1);
            idx++;
        }
    }

    public static void main(String[] args) {
        String str = scn.nextLine();
        max = Integer.parseInt(str);
        findMaximum(str, 4, 0);
        System.out.println(max);
    }
}
