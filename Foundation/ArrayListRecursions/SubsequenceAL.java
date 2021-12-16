import java.util.*;

public class SubsequenceAL {
    public static Scanner scn = new Scanner(System.in);

    public static ArrayList<String> SB(String str) {
        if (str.length() == 0) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }
        char FC = str.charAt(0);
        ArrayList<String> recans = SB(str.substring(1));
        ArrayList<String> myAns = new ArrayList<>(recans);
        for (String s : recans) {
            myAns.add(FC + s);
        }

        return myAns;
    }

    public static void main(String[] args) {
        String n = scn.nextLine();
        System.out.println(SB(n));
    }
}