import java.util.*;

public class SmallestNumberPattern {

    public static Scanner scn = new Scanner(System.in);

    public static void main(String[] args) {
        String str = scn.nextLine();
        Stack<Integer> st = new Stack<>();
        int num = 1;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == 'd') {
                st.push(num);
                num++;
            } else {
                st.push(num);
                num++;
                while (st.size() > 0) {
                    System.out.print(st.pop());
                }
            }
        }
        st.push(num);
        while (st.size() > 0) {
            System.out.print(st.pop());
        }
    }

}