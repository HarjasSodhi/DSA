import java.util.*;

public class ArrayListRE {
    public static Scanner scn = new Scanner(System.in);

    public static void RE() {
        ArrayList<Integer> arr = new ArrayList<Integer>();
        int n = scn.nextInt();
        for (int i = 0; i < n; i++) {
            int a = scn.nextInt();
            arr.add(a);
        }
        int data = scn.nextInt();
        for (int i = arr.size() - 1; i >= 0; i--) {
            if (arr.get(i) == data) {
                Swap(arr, i);
                arr.remove(arr.size() - 1);
            }
        }
        System.out.println(arr);
    }

    public static void Swap(ArrayList<Integer> arr, int i) {
        int temp = arr.get(arr.size() - 1);
        arr.set(arr.size() - 1, arr.get(i));
        arr.set(i, temp);
    }

    public static void main(String[] args) {
        RE();
    }
}