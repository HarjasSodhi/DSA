import java.util.*;

public class CountHigh {
    public static Scanner scn = new Scanner(System.in);

    public static void HI(String str) {
        int hiCounter = 0;
        for (int i = 0; i < str.length() - 1; i++) {
            char c1 = str.charAt(i);
            char c2 = str.charAt(i + 1);
            if (c1 == 'h' && c2 == 'i') {
                hiCounter++;
            }

        }
        System.out.println(hiCounter);
    }
    public static void HIT(String str) {
        int hiCounter=0;
        int i=0;
        while(i<str.length()-2){
            if(str.charAt(i)=='h'&&str.charAt(i+1)=='i'&&str.charAt(i+2)!='t'){
                hiCounter++;
                i=i+2;
            }
            else if(str.charAt(i)=='h'&&str.charAt(i+1)=='i'&&str.charAt(i+2)=='t')i=i+3;
            else i++;
        }
        System.out.println(hiCounter);
    }

    public static void main(String[] args) {
        String str = scn.nextLine();
        HIT(str);
    }
}