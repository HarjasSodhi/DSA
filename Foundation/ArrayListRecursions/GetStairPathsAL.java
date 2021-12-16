import java.util.*;

public class GetStairPathsAL {
    public static Scanner scn = new Scanner(System.in);

    public static ArrayList<String> SP(int n) {
        if (n == 0) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }
        if (n < 0)
            return new ArrayList<>();
        ArrayList<String> myAns = new ArrayList<>();
        ArrayList<String> recans1 = SP(n - 1);//i=1;
        for (String s : recans1) {
            myAns.add('1' + s);
        }
        ArrayList<String> recans2 = SP(n - 2);//i=2;
        for (String s : recans2) {
            myAns.add('2' + s);
        }
        ArrayList<String> recans3 = SP(n - 3);//i=3;
        for (String s : recans3) {
            myAns.add('3' + s);
        }
        return myAns;
    }
//can also be used for dice question and values of i can also be in an array;
    public static void main(String[] args) {
        int n = scn.nextInt();
        System.out.println(SP(n));
    }
}