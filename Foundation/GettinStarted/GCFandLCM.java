import java.util.*;

public class GCFandLCM {
    public static Scanner scn = new Scanner(System.in);

    public static void GCF(int a, int b) {
        int greater = a;
        int num = b;
        if (b > a) {
            int temp = b;
            b = a;
            a = temp;
        }
        for (int i = b ; i > 0; i--) {
            if (a % i == 0 && b % i == 0) {
                System.out.printf("the GCF is %d\n", i);
                break;
            }
        }
        LCM(greater, num);
    }

    public static void LCM(int a, int b) {
        for (int i = a ; i > 0; i++) {
            if (i % a == 0 && i % b == 0) {
                System.out.printf("the LCM is %d\n", i);
                break;
            }
        }

    }

    public static void main(String[] args) {
        System.out.println("enter the first num");
        int a = scn.nextInt();
        System.out.println("enter the 2nd num");
        int b = scn.nextInt();
        GCF(a, b);
    }
}