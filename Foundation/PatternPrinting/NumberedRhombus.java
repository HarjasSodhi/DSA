import java.util.*;

public class NumberedRhombus {
    public static Scanner scn = new Scanner(System.in);

    public static void NR() {
        System.out.println("enter the value of n");
        int n = scn.nextInt();
        int nst = 1;
        int nsp = n / 2;
        int num;
        for (int row = 1; row <= n; row++) {
            if (row <= n / 2) {
                num = row;
            } else {
                num = n - row + 1;
            }
            for (int csp = 1; csp <= nsp; csp++)
                System.out.print("\t");
            for (int cst = 1; cst <= nst; cst++) {
                System.out.print(num + "\t");
                if (cst <= nst / 2) {
                    num++;
                } else {
                    num--;
                }
            }
            System.out.println();
            if (row <= n / 2) {
                nst = nst + 2;
                nsp--;
            } else {
                nst = nst - 2;
                nsp++;
            }

        }
    }

    public static void main(String[] args) {
        NR();
    }
}
