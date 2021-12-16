import java.util.*;

public class DecodeWaysAL {
    public static Scanner scn = new Scanner(System.in);

    public static ArrayList<String> DW(String str, char[] arr) {
        if (str.length() == 0) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }
        if (str.charAt(0) == '0') {
            return new ArrayList<>();
        }
        char Fc = arr[str.charAt(0) - '0'];
        ArrayList<String> recans1 = DW(str.substring(1), arr);
        ArrayList<String> myAns = new ArrayList<>();
        for (String s : recans1) {
            myAns.add(Fc + s);
        }
        if (str.length() > 1) {
            int num = (str.charAt(0) - '0') * 10 + (str.charAt(1) - '0');
            if (num <= 26) {
                ArrayList<String> recans2 = DW(str.substring(2), arr);
                for (String s : recans2) {
                    myAns.add(arr[num] + s);
                }
            }
        }

        return myAns;
    }

    public static void main(String[] args) {
        char arr[] = { '.', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
                's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
        String n = scn.nextLine();
        System.out.println(DW(n, arr));
    }
}