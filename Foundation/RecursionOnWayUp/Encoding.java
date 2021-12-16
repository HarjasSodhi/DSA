import java.util.*;

public class Encoding {
    public static Scanner scn = new Scanner(System.in);

    public static void EC(String ques, String ans, ArrayList<String> res, char[] arr) {
        if (ques.length() == 0) {
            res.add(ans);
            return;
        }
        if (ques.charAt(0) == '0')
            return;
        char ch = arr[ques.charAt(0) - '0'];
        EC(ques.substring(1), ans + ch, res, arr);
        if (ques.length() > 1) {
            String str = ques.substring(0, 2);
            int num = (str.charAt(0) - '0') * 10 + (str.charAt(1) - '0');
            if (num <= 26)
                EC(ques.substring(2), ans + arr[num], res, arr);
        }
    }

    public static void main(String[] args) {
        char arr[] = { '.', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
                's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
        String ques = scn.nextLine();
        ArrayList<String> res = new ArrayList<>();
        EC(ques, "", res, arr);
        for (String string : res) {
            System.out.println(string);
        }
    }
}