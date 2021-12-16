import java.util.*;

public class MultiplicationTable {
    public static Scanner scn = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("enter the value of n");
        int n = scn.nextInt();
        for (int i = 1; i <= 10; i++) {
            System.out.print(n + "*" + i + "=" + n * i);
            System.out.println();
        }

    }
}
