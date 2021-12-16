import java.util.*;

public class MazePath {
    public static Scanner scn = new Scanner(System.in);

    public static void MP1(int n, int m, String ans, ArrayList<String> res) {
        if (n == 0 && m == 0) {
            res.add(ans);
            return;
        }
        if (n > 0)
            MP1(n - 1, m, ans + "v", res);
        if (m > 0)
            MP1(n, m - 1, ans + "h", res);
    }

    public static int MP2(int n, int m, String ans, ArrayList<String> res) {
        if (n == 0 && m == 0) {
            res.add(ans);
            return 1;
        }
        int count = 0;
        if (n > 0)
            count += MP2(n - 1, m, ans + "v", res);
        if (m > 0)
            count += MP2(n, m - 1, ans + "h", res);
        return count;
    }

    public static void main(String[] args) {
        int n = scn.nextInt();
        int m = scn.nextInt();
        ArrayList<String> res = new ArrayList<>();
        int k = MP2(n - 1, m - 1, "", res);
        System.out.println(k);
        for (String string : res) {
            System.out.println(string);
        }
    }
}