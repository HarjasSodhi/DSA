import java.util.*;

public class Arrow {
    public static Scanner scn = new Scanner(System.in);

    public static void AP() {
        System.out.println("enter the value of n");
        int n = scn.nextInt();
        int nst = 1;
        for (int row = 1; row <= n; row++) {
            if (row != (n + 1) / 2) {
                for (int csp = 0; csp < n / 2; csp++)
                    System.out.print("\t");
            }

            else {
                for (int i = 1; i <= n / 2; i++)
                    System.out.print("*\t");

            }
            for (int cst = 1; cst <= nst; cst++)
                System.out.print("*\t");
            if (row <= n / 2) {
                nst++;
            } else {
                nst--;
            }

            System.out.println();
        }
    }

    public static void main(String[] args) {
        AP();
    }
}
