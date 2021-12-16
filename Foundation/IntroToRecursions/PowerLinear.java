import java.util.*;

public class PowerLinear {
    public static Scanner scn = new Scanner(System.in);

    public static int LP(int x, int n) {
        if (n < 1)
            return 1;
        return x * LP(x, n - 1);
    }

    public static void main(String[] args) {
        int x = scn.nextInt();
        int n = scn.nextInt();
        int ans = LP(x, n);
        System.out.println(ans);
    }
}