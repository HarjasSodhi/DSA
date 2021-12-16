import java.io.*;
import java.util.*;

public class CelebrityProblem {

    public static void main(String[] args) throws Exception {
        // write your code here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][n];

        for (int j = 0; j < n; j++) {
            String line = br.readLine();
            for (int k = 0; k < n; k++) {
                arr[j][k] = line.charAt(k) - '0';
            }
        }

        findCelebrity(arr);

    }

    public static void findCelebrity(int[][] arr) {
        // if a celebrity is there print it's index (not position), if there is not then
        // print "none"
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            st.push(i);
        }
        while (st.size() != 1) {
            int a = st.pop();
            int b = st.pop();
            if (arr[b][a] == 0 && arr[a][b] == 0) {
                System.out.println("none");
                return;
            }
            if (arr[a][b] == 0) {
                st.push(a);
            }
            if (arr[b][a] == 0) {
                st.push(b);
            }
        }
        int c = st.pop();
        for (int i = 0; i < arr.length; i++) {
            if (arr[c][i] == 1) {
                System.out.println("none");
                return;
            }
        }
        System.out.println(c);
    }

}