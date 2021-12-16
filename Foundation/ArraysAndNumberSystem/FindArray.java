import java.util.*;

public class FindArray {
    public static Scanner scn = new Scanner(System.in);

    public static int[] InsertData(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            System.out.println("enter the value at index " + i);
            arr[i] = scn.nextInt();
        }
        return arr;
    }

    public static int FA(int[] arr, int n) {
        for (int i = 0; i < arr.length; i++)
            if (arr[i] == n)
                return i;
        return -1;
    }

    public static void main(String[] args) {
        System.out.println("enter the size of the array");
        int n = scn.nextInt();
        int[] arr = new int[n];
        arr = InsertData(n);
        System.out.println("enter the element to find:(if not available ->-1)");
        int index = FA(arr, scn.nextInt());
        System.out.println("the element found at index " + index);
    }
}
