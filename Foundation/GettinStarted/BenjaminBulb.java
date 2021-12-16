import java.util.*;

public class BenjaminBulb {
    public static Scanner scn = new Scanner(System.in);

    public static void BB() {
        System.out.println("enter the number of bulbs");
        int n = scn.nextInt();
        for (int i = 1; i * i < n; i++) {
            System.out.println(i * i);
        }
    }

    public static void main(String[] args) {
        BB();
    }
}