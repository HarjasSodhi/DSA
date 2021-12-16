import java.util.*;

public class Rhombus {
    public static Scanner scn = new Scanner(System.in);

    public static void RP() {
        System.out.println("enter the value of n");
        int n = scn.nextInt();
        int nsp = n/2;
        int nst = 1;
        for (int row = 1; row <= n; row++) {
            for (int csp = 1; csp <= nsp; csp++)
                System.out.print("\t");
            for (int cst = 1; cst <= nst; cst++)
                System.out.print("*\t");
            if (row <= n / 2) {
                nsp--;
                nst = 2 + nst;
            } else {
                nsp++;
                nst = nst - 2;
            }

            System.out.println();
        }
    }

    public static void main(String[] args) {
        RP();
    }
}