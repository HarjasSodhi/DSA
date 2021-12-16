import java.util.*;

public class NumInverse {
    public static Scanner scn = new Scanner(System.in);

    public static int pow(int a) {
        int i = 1;
        for (int j = 1; j < a; j++) {
            i = i * 10;
        }
        return i;
    }

    public static void CD() {
        System.out.println("enter the number");
        int d = scn.nextInt();
        int i = d;
        int count = 0;
        while (d != 0) {
            d = d / 10;
            count++;
        }
        NI(i, count);
    }

    public static void NI(int n, int count) {
        int num = 0;
        int h;
        for (int i = 1; i <= count; i++) {
            h = n % 10;
            n = n / 10;
            num = num + (i * pow(h));
        }
        System.out.println(num);
    }

    public static void main(String[] args) {
        CD();
    }
}