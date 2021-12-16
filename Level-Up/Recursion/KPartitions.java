import java.util.*;

public class KPartitions {

    public static Scanner scn = new Scanner(System.in);

    public static int counter = 1;

    public static void solution(int i, int n, int k, int rssf, ArrayList<ArrayList<Integer>> ans) {

        // write your code here
        if (i == n + 1) {
            if (rssf == k) {
                System.out.print(counter + ". ");
                counter++;
                for (ArrayList<Integer> e : ans) {
                    System.out.print(e + " ");
                }
                System.out.println();
            }
            return;
        }

        for (int j = 0; j < ans.size(); j++) {
            ArrayList<Integer> temp = ans.get(j);
            ans.remove(j);
            if (temp.size() == 0) {
                temp.add(i);
                ans.add(j, temp);
                solution(i + 1, n, k, rssf + 1, ans);
                ans.remove(j);
                temp.remove(temp.size() - 1);
                ans.add(j, temp);
                break;
            } else {
                temp.add(i);
                ans.add(j, temp);
                solution(i + 1, n, k, rssf, ans);
                ans.remove(j);
                temp.remove(temp.size() - 1);
                ans.add(j, temp);
            }
        }

    }

    public static void main(String[] args) {
        int n = scn.nextInt();
        int k = scn.nextInt();
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            ans.add(new ArrayList<>());
        }
        solution(1, n, k, 0, ans);
    }
}