import java.util.*;

public class GetKPCAL {
    public static Scanner scn = new Scanner(System.in);

    public static ArrayList<String> KPC(String str, String[] arr) {
        if (str.length() == 1) {
            ArrayList<String> base = new ArrayList<>();
            String chars = arr[str.charAt(0) - '0'];
            for (int i = 0; i < chars.length(); i++) {
                base.add(chars.charAt(i) + "");
            }
            return base;
        }
        String characters = arr[str.charAt(0) - '0'];
        ArrayList<String> recans = KPC(str.substring(1), arr);
        ArrayList<String> myAns = new ArrayList<>();
        for (int i = 0; i < characters.length(); i++) {
            for (String s : recans) {
                myAns.add(characters.charAt(i) + s);
            }
        }
        return myAns;
    }

    public static void main(String[] args) {
        String n = scn.nextLine();
        String[] arr = { ".;", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tu", "vwx", "yz" };
        System.out.println(KPC(n, arr));
    }
}