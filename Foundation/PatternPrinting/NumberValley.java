import java.util.*;

public class NumberValley {
    public static Scanner scn = new Scanner(System.in);

    public static void NV() {
        System.out.println("enter the value of n");
        int n = scn.nextInt();
        int nst = 1;
        int nsp = (2 * n) - 3;
        for (int row = 1; row <= n; row++) {
            for (int cst = 1; cst <= nst; cst++)
                if (cst != n)
                    System.out.print(cst + "\t");
            for (int csp = 1; csp <= nsp; csp++)
                System.out.print("\t");
            for (int cst = row; cst > 0; cst--) {

                System.out.print(cst + "\t");
            }
            nst++;
            nsp -= 2;
            System.out.println();
        }
    }

    public static void main(String[] args) {
        NV();
    }
}
