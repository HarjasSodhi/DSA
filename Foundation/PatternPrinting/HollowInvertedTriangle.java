import java.util.*;

public class HollowInvertedTriangle {
    public static Scanner scn = new Scanner(System.in);

    public static void HIT() {
        System.out.println("enter the value of n");
        int n = scn.nextInt();
        int nspb = 1;
        int nspa = n - 3;
        for (int i = 1; i <= n; i++)
            System.out.print("*\t");
        System.out.println();
        for (int row = 2; row <= (n / 2) + 1; row++) {
            for (int csp = 1; csp <= nspb; csp++)
                System.out.print("\t");
            System.out.print("*");
            for (int csp = 1; csp <= nspa; csp++)
                System.out.print("\t");
            if (row != (n / 2) + 1)
                System.out.print("*\t");
            System.out.println();
            nspb++;
            nspa = nspa - 2;
        }
        int nst = 3;
        for (int row = 1; row <= n / 2; row++) {
            nspb = nspb - 1;
            for (int csp = 1; csp < nspb; csp++)
                System.out.print("\t");
            for (int cst = 1; cst <= nst; cst++)
                System.out.print("*\t");
            System.out.println();
            nst = nst + 2;
        }
    }

    public static void main(String[] args) {
        HIT();
    }
}