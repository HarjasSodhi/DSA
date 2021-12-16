import java.util.*;

public class BaseToBase {
    public static Scanner scn = new Scanner(System.in);

    public static void BTB(int num, int base1, int base2) {
        int pow = 1;
        int ans = 0;
        while (num != 0) {
            int temp = num % 10;
            ans = ans + (temp * pow);
            pow = pow * base1;
            num = num / 10;
        }
        DTB(ans, base2);
    }

    public static void DTB(int num, int base2) {
        int ans = 0;
        int mul = 1;
        while (num != 0) {
            ans = ans + ((num % base2) * mul);
            num = num / base2;
            mul *= 10;
        }
        System.out.println(ans);
    }

    public static void main(String[] args) {
        int basenum = scn.nextInt();
        int base1 = scn.nextInt();
        int base2 = scn.nextInt();
        BTB(basenum, base1, base2);
    }
}