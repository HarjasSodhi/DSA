import java.util.*;

public class BaseAddition {
    public static Scanner scn = new Scanner(System.in);

    public static void BA(int base, int b1, int b2) {
        int ans = 0;
        int mul = 1;
        int carry = 0;
        int temp = 0;
        while (b1 != 0 || b2 != 0 || carry != 0) {// add carry!=0 for one more iterartion.
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
        // ans=ans+carry*mul;->because one more iteration is needed.Use one of the two methods.
        System.out.println(ans);
    }

    public static void main(String[] args) {
        int base = scn.nextInt();
        int b1 = scn.nextInt();
        int b2 = scn.nextInt();
        BA(base, b1, b2);
    }
}