import java.util.*;

public class wordbreak {
    public static Scanner scn = new Scanner(System.in);

    public static void wordBreak(String str, String ans, HashSet<String> dict) {
        if (str.length() == 0) {
            System.out.println(ans);
            return;
        }
        for (int i = 0; i < str.length(); i++) {
            String pow = str.substring(0, i + 1);// potential word
            if (dict.contains(pow)) {
                wordBreak(str.substring(i + 1), ans + pow + " ", dict);
            }
        }
    }

    public static void main(String[] args) {
        int n = scn.nextInt();
        HashSet<String> dict = new HashSet<>();
        for (int i = 0; i < n; i++) {
            dict.add(scn.next());
        }
        String sentence = scn.next();
        wordBreak(sentence, "", dict);
    }
}
