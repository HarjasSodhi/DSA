import java.util.*;

public class BaseMultiplication {
    public static Scanner scn = new Scanner(System.in);

    public static void BM(int base, int b1, int b2) {
        int ans = 0;
        int carry = 0;
        int mul2 = 1;
        int tempans = 0;
        while (b2 != 0) {
            int mul1 = 1;
            int tempnum = b1;
            int onedigitprod = 0;
            while (tempnum != 0 || carry != 0) {
                tempans = (tempnum % 10) * (b2 % 10) + carry;
                tempnum = tempnum / 10;
                carry = 0;
                if (tempans >= base) {
                    carry = tempans / base;
                    tempans = tempans % base;
                }
                onedigitprod = (tempans) * mul1 + onedigitprod;
                mul1 = mul1 * 10;

            }
            b2 = b2 / 10;
            ans = BA(base, ans, (onedigitprod * mul2));
            mul2 = mul2 * 10;
        }
        System.out.println(ans);
    }

    public static int BA(int base, int b1, int b2) {
        int ans = 0;
        int mul = 1;
        int carry = 0;
        int temp = 0;
        while (b1 != 0 || b2 != 0 || carry != 0) {
            temp = (b1 > 0 ? b1 % 10 : 0) + (b2 > 0 ? b2 % 10 : 0) + carry;
            carry = 0;
            b1 = b1 / 10;
            b2 = b2 / 10;
            if (temp >= base) {
                carry = temp / base;
                temp = temp % base;
            }
            ans = ans + (temp * mul);
            mul = mul * 10;
        }
        return ans;
    }

    public static void main(String[] args) {
        int base = scn.nextInt();
        int b1 = scn.nextInt();
        int b2 = scn.nextInt();
        BM(base, b1, b2);
    }
}