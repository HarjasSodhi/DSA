import java.util.*;

public class BaseSubtraction {
    public static Scanner scn = new Scanner(System.in);

    public static void BS(int base, int b1, int b2) {
        int temp;
        int borrow = 0;
        int ans = 0;
        int mul = 1;
        while (b1 != 0 || b2 != 0) {
            temp = (b2 > 0 ? b2 % 10 : 0) - (b1 > 0 ? b1 % 10 : 0) + borrow;
            borrow = 0;
            b1 = b1 / 10;
            b2 = b2 / 10;
            if (temp < 0) {
                temp = temp + base;
                borrow = -1;
            }
            ans = ans + (temp * mul);
            mul = mul * 10;
        }
        System.out.println(ans);
    }

    public static void main(String[] args) {
        int base = scn.nextInt();
        int b1 = scn.nextInt();
        int b2 = scn.nextInt();
        BS(base, b1, b2);
    }
}
