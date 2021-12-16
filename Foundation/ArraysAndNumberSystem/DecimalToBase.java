import java.util.*;

public class DecimalToBase {
    public static Scanner scn = new Scanner(System.in);

    public static void DTB(int num, int base) {
        int ans = 0;
        int mul = 1;
        while (num != 0) {
            ans = ans + ((num % base) * mul);
            num = num / base;
            mul *= 10;
        }
        System.out.println(ans);
    }

    public static void main(String[] args) {
        int num = scn.nextInt();
        int base = scn.nextInt();
        DTB(num, base);
    }
}