import java.util.*;

public class RoatateNum {
    public static Scanner scn = new Scanner(System.in);

    public static void CD() {
        System.out.println("enter the number");
        int d = scn.nextInt();
        int i = d;
        int count = 0;
        while (d != 0) {
            d = d / 10;
            count++;
        }
        RN(i, count);
    }

    public static int abs(int a) {
        if (a < 0)
            return -a;
        else
            return a;
    }

    public static int pow(int count) {
        int i = 1;
        for (int j = 0; j < count; j++) {
            i = i * 10;
        }
        return i;
    }

    public static void RN(int n, int count) {
        System.out.println("enter the factor");
        int k = scn.nextInt();
        int h;

        if (k > 0) {
            if (k > count) {
                k = k % count;
            }
            h = n % pow(k);
            n = n / pow(k);
            n = h * pow(count - k) + n;
            System.out.println(n);
        }
        if (k < 0) {
            if (abs(k) > count) {
                k = k % count;
            }
            h = n / pow(count + k);
            n = n % pow(count + k);
            n = n * pow(-k) + h;
            System.out.println(n);
        }
    }

    public static void main(String[] args) {
        CD();
    }
}