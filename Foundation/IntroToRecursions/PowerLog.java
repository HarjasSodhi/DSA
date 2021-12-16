import java.util.*;

public class PowerLog {
    public static Scanner scn = new Scanner(System.in);

    public static int POW(int a, int n) {
        if (n < 1)
            return 1;
        int recAns = POW(a, n / 2);
        recAns *= recAns;
        return n % 2 == 0 ? recAns : a * recAns;
    }

    public static void main(String[] args) {
        int a = scn.nextInt();
        int n = scn.nextInt();
        int ans = POW(a, n);
        System.out.println(ans);
    }
}