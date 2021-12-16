import java.util.*;

public class AllIndexes {
    public static Scanner scn = new Scanner(System.in);

    public static int[] insert(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scn.nextInt();
        }
        return arr;
    }

    public static int[] AI2(int[] arr, int i, int data, int count) {
        if (i == arr.length)
            return new int[count];
        if (arr[i] == data)
            count++;
        int[] ans = AI2(arr, i + 1, data, count);
        if (arr[i] == data) {
            ans[count - 1] = i;
        }
        return ans;
    }

    public static void display(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0)
                System.out.println(arr[i]);
        }
    }

    public static int[] AI(int[] arr, int i, int data, int[] ans) {
        if (i == arr.length)
            return ans;
        if (arr[i] == data)
            ans[i] = 1;
        AI(arr, i + 1, data, ans);
        return ans;
    }

    public static void main(String[] args) {
        int n = scn.nextInt();
        int[] arr = new int[n];
        arr = insert(n);
        int i = 0;
        int data = scn.nextInt();
        int count = 0;
        int[] Ai = AI2(arr, i, data, count);
        for (int j = 0; j < Ai.length; j++) {
            System.out.println(Ai[j]);
        }
        // display(Ai);
    }
}