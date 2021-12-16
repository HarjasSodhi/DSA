import java.util.*;

public class FirstAndLastIndex {
    public static Scanner scn = new Scanner(System.in);

    public static int[] insert(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scn.nextInt();
        }
        return arr;
    }

    public static int FLI(int[] arr, int i, int data, int[] ans, boolean boolvar) {
        if (i == arr.length)
            return -1;
        if (arr[i] == data && boolvar == true) {
            ans[0] = i;
            boolvar = false;
        }
        int recans = FLI(arr, i + 1, data, ans, boolvar);
        if (recans == -1)
            if (arr[i] == data) {
                ans[1] = i;
                recans = 1;
            }
        return recans;

    }
    // public static boolean FLI2(int[] arr, int i, int data, int[] ans){
    //     if(i==arr.length)return false;
    //     if(arr[i]==data)ans[0]=i;
    //     boolean res =FLI2(arr, i+1, data, ans);
    //     if(res)return true;
    //     if(arr[i]==data) arr[1]=i;
    //     return false;
    // }

    public static void main(String[] args) {
        int n = scn.nextInt();
        int[] arr = new int[n];
        arr = insert(n);
        int i = 0;
        int[] Ai = new int[2];
        int data = scn.nextInt();
        boolean boolvar = true;
        FLI(arr, i, data, Ai,boolvar);
        System.out.println(Ai[0] + "\n" + Ai[1]);
    }
}