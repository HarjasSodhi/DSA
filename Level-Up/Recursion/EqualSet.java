import java.util.*;

public class EqualSet {
    public static int equalSet(int[] arr, int idx, int sum1, String set1, int sum2, String set2) {
        if (idx == arr.length) {
            if (sum1 == sum2) {
                System.out.println(set1 + "--" + set2);
                return 1;
            }
            return 0;
        }
        int count = 0;
        count += equalSet(arr, idx + 1, sum1 + arr[idx], set1 + arr[idx] + " ", sum2, set2);
        count += equalSet(arr, idx + 1, sum1, set1, sum2 + arr[idx], set2 + arr[idx] + " ");
        return count;
    }

    public static void ksubsets(int[] arr, int idx, int[] subsetSum, ArrayList<ArrayList<Integer>> ans) {
        if (idx == arr.length) {
            int s = subsetSum[0];
            for (int i = 1; i < subsetSum.length; i++) {
                if (s != subsetSum[i])
                    return;
            }
            for (int i = 0; i < ans.size(); i++) {
                System.out.print(ans.get(i) + " ");
            }
            System.out.println();
            return;
        }

        for (int i = 0; i < ans.size(); i++) {
            ArrayList<Integer> list = ans.get(i);
            list.add(arr[idx]);
            subsetSum[i] += arr[idx];
            ksubsets(arr, idx + 1, subsetSum, ans);
            list.remove(list.size() - 1);
            subsetSum[i] -= arr[idx];
            if (list.size() == 0)
                break;
        }

    }

    public static void equalSet(int[] arr, int k) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < k; i++)
            ans.add(new ArrayList<>());

        int sum = 0;
        for (int ele : arr)
            sum += ele;

        if (sum % k != 0)
            return;

        int[] sumArray = new int[k];
        ksubsets(arr, 0, sumArray, ans);
        System.out.println(ans);
    }

    public static void main(String[] args) {
        int[] arr = { 10, 20, 30, 40, 50, 60, 70, 80 };
        // //has repetition
        // equalSet(arr, 0, 0, " ", 0, "");
        // //generates unique permu
        // equalSet(arr, 1 , 10, "10 ", 0, "");
        equalSet(arr, 0);
    }
}
