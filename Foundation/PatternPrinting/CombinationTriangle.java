import java.util.*;

public class CombinationTriangle {
    public static Scanner scn = new Scanner(System.in);

    public static void NCR(int row) {

    }

    public static void CT() {
        System.out.println("enter the value of n");
        int n = scn.nextInt();
        int nst = 1;
        for (int row = 0; row < n; row++) {
            int ncr = 1;
            for (int cst = 0; cst < nst; cst++) {
                System.out.print(ncr + "\t");
                ncr = ((row - cst) * ncr) / (cst + 1);
            }
            nst++;
            System.out.println();
        }

    }

    public static void main(String[] args) {
        CT();
    }
}
