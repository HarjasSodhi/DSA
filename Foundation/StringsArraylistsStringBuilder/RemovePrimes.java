import java.util.*;

public class RemovePrimes {
    public static Scanner scn = new Scanner(System.in);

    public static void RP() {
        int n = scn.nextInt();
        ArrayList<Integer> arr = new ArrayList<Integer>(n);
        ArrayList<Integer> ans = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            int a = scn.nextInt();
            arr.add(a);
        }
        for (int i = 0; i < arr.size(); i++) {
            int num = arr.get(i);
            boolean isprime = true;
            for (int j = 2; j * j <= num; j++) {
                if (num % j == 0) {
                    isprime = false;
                    break;
                }
            }
            if (!isprime) {
                ans.add(arr.get(i));
            }
        }
        //use arr.clear()[->O(n)] and push elements of ans in it if you want to modify the original array.
        System.out.println(ans);
    }

    public static void main(String[] args) {
        RP();
    }
}