import java.util.*;

public class HollowRhombus {
    public static Scanner scn = new Scanner(System.in);

    public static void HRP() {
        System.out.println("enter the value of n");
        int n = scn.nextInt();
        int nsp = 1;
        int nst = (n / 2) + 1;
        for (int row = 1; row <= n; row++) {
            for (int cst = 1; cst <= nst; cst++)
                System.out.print("*\t");
            for (int csp = 1; csp <= nsp; csp++)
                System.out.print("\t");
            for (int cst = 1; cst <= nst; cst++)
                System.out.print("*\t");
            if (row <= n / 2) {
                nsp = nsp + 2;
                nst--;
            } else {
                nsp = nsp - 2;
                nst++;
            }

            System.out.println();
        }
    }

    public static void main(String[] args) {
        HRP();
    }
}
