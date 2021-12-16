import java.util.*;

public class PrintDecreasing {
    public static Scanner scn = new Scanner(System.in);

    public static void DP(int a, int n) {
        if (a > n)
            return;
        DP(a + 1, n);
        System.out.println(a);
    }

    public static void main(String[] args) {
        int n = scn.nextInt();
        DP(1, n);
    }
}