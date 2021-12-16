import java.util.*;

public class PrintIncDec {
    public static Scanner scn = new Scanner(System.in);

    public static void IDP(int n) {
        if (n < 1)
            return;
        System.out.println(n);
        IDP(n - 1);
        System.out.println(n);
    }

    public static void main(String[] args) {
        int n = scn.nextInt();
        IDP(n);
    }
}
