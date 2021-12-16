import java.util.*;

public class BaseToDecimal {
    public static Scanner scn = new Scanner(System.in);

    public static void BTD(int num, int base) {
        int pow = 1;
        int ans = 0;
        while (num != 0) {
            int temp = num % 10;
            ans = ans + (temp * pow);
            pow = pow * base;
            num = num / 10;
        }
        System.out.println(ans);
    }

    public static void main(String[] args) {
        int num = scn.nextInt();
        int base = scn.nextInt();
        BTD(num, base);
    }
}