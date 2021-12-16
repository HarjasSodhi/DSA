import java.util.*;

public class Wpattern {
    public static Scanner scn = new Scanner(System.in);

    public static void CL() {
        System.out.println("enter the value of n");
        int n = scn.nextInt();
        if (n == 2)
            System.out.print("*");
        else {
            int nsp = n - 1;
            int nspb = (n / 2);
            int nspa = 0;
            int nspaa = n / 2;
            for (int row = 1; row <= n; row++) {
                System.out.print("*");
                if (row <= n / 2)
                    for (int csp = 1; csp <= nsp; csp++)
                        System.out.print("\t");

                else {
                    for (int csp = 1; csp <= nspb; csp++)
                        System.out.print("\t");
                    if (row != n)
                        System.out.print("*");
                    for (int csp = 1; csp <= nspa; csp++)
                        System.out.print("\t");
                    if (row != (n / 2) + 1 && row != n)
                        System.out.print("*");
                    for (int csp = 1; csp <= nspaa; csp++)
                        System.out.print("\t");
                    nspb--;
                    nspa = nspa + 2;
                    nspaa--;
                }
                System.out.print("*");

                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        CL();
    }
}