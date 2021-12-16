import java.util.*;

public class AdvancedKPCAL {
    public static Scanner scn = new Scanner(System.in);

    public static ArrayList<String> ADKPC(String str, String[] arr) {
        if (str.length() == 0) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }
        String strArr1 = arr[str.charAt(0) - '0'];
        ArrayList<String> recans1 = ADKPC(str.substring(1), arr);
        ArrayList<String> myAns = new ArrayList<>();
        for (int i = 0; i < strArr1.length(); i++) {
            for (String s : recans1) {
                myAns.add(strArr1.charAt(i) + s);
            }
        }
        if (str.length() > 1) {
            int num = (str.charAt(0) - '0') * 10 + (str.charAt(1) - '0');
            if (num <= 11) {
                String strArr2 = arr[num];
                ArrayList<String> recans2 = ADKPC(str.substring(2), arr);
                for (int i = 0; i < strArr2.length(); i++) {
                    for (String s : recans2) {
                        myAns.add(strArr2.charAt(i) + s);
                    }
                }
            }
        }
        return myAns;
    }

    public static void main(String[] args) {
        String n = scn.nextLine();
        String[] arr = { ".;", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tu", "vwx", "yz", "+-*", "<>/%" };
        for (String s : ADKPC(n, arr)) {
            System.out.println(s);
        }
    }
}