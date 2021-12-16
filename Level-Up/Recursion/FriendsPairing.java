import java.util.*;

public class FriendsPairing {
    public static Scanner sc = new Scanner(System.in);

    public static void solution(int count, int n, boolean[] used, String asf) {
        if (count == n) {
            System.out.println(asf);
            return;
        }
        int fup = 1;// first unused num
        while (fup <= n && used[fup]) {
            fup++;
        }
        used[fup] = true;
        solution(count + 1, n, used, asf + "(" + fup + ") ");
        for (int i = fup + 1; i <= n; i++) {
            if (!used[i]) {
                used[i] = true;
                solution(count + 2, n, used, asf + "(" + fup + "," + i + ") ");
                used[i] = false;
            }
        }
        used[fup] = false;
    }

    public static void main(String[] args) {
        int n = sc.nextInt();
        boolean[] used = new boolean[n + 1];
        solution(0, n, used, "");
    }
}