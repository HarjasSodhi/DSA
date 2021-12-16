import java.util.*;

public class RhombusOutline {
    public static Scanner scn = new Scanner(System.in);

    public static void RO() {
        System.out.println("enter the value of n");
        int n = scn.nextInt();
        int nspb = n / 2;
        int nspa = 0;
        int nst = 1;
        for (int row = 1; row <= n; row++) {
            for (int csp = 1; csp <= nspb; csp++)
                System.out.print("\t");
            System.out.print("*\t");
            for (int csp = 1; csp <= nspa; csp++)
                System.out.print("\t");
            if (nst > 1) {
                System.out.print("*\t");
            }
            System.out.println();
            if (row <= n / 2) {
                nst++;
                nspb--;
                nspa = 2 * row - 1;
            } else {
                nst--;
                nspa = nspa - 2;
                nspb++;
            }
        }

    }

    public static void main(String[] args) {
        RO();
    }
}