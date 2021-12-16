import java.util.*;

public class MazePathWithJump {
    public static Scanner scn = new Scanner(System.in);

    public static void MP1(int n, int m, String ans, ArrayList<String> res) {
        if (n == 0 && m == 0) {
            res.add(ans);
            return;
        }
        for (int i = 1; m - i >= 0; i++) {
            MP1(n, m - i, ans + "h" + i, res);
        }
        for (int i = 1; n - i >= 0; i++) {
            MP1(n - i, m, ans + "v" + i, res);
        }
        for (int i = 1; m - i >= 0 || n - i > 0; i++) {
            MP1(n - i, m - i, ans + "d" + i, res);
        }
    }

    public static int MP2(int n, int m, String ans, ArrayList<String> res) {
        if (n == 0 && m == 0) {
            res.add(ans);
            return 1;
        }
        int count = 0;
        for (int i = 1; m - i >= 0; i++) {
            count += MP2(n, m - i, ans + "h" + i, res);
        }
        for (int i = 1; n - i >= 0; i++) {
            count += MP2(n - i, m, ans + "v" + i, res);
        }
        for (int i = 1; m - i >= 0 || n - i > 0; i++) {
            count += MP2(n - i, m - i, ans + "d" + i, res);
        }
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