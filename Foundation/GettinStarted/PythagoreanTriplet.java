import java.util.*;

public class PythagoreanTriplet {
    public static Scanner scn1 = new Scanner(System.in);

    public static void CheckPT() {
        System.out.println("enter the value of first side");
        int greater = scn1.nextInt();
        System.out.println("enter the value of 2nd side");
        int side1 = scn1.nextInt();
        if (side1 > greater) {
            int temp = side1;
            side1 = greater;
            greater = temp;
        }
        System.out.println("enter the value of third side");
        int side2 = scn1.nextInt();
        if (side2 > greater) {
            int temp1 = side2;
            side2 = greater;
            greater = temp1;
        }
        if (greater * greater == side1 * side1 + side2 * side2) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }

    }

    public static void main(String[] args) {
        CheckPT();
    }
}