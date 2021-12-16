import java.util.*;

public class CryptoArithemetic {

    public static void Crypto_01(String unique, int idx, HashMap<Character, Integer> charIntMap, boolean[] usedNumbers,
            String s1, String s2, String s3) {
        if (idx == unique.length()) {
            int num1 = 0;
            int num2 = 0;
            int sum = 0;
            for (int i = 0; i < s1.length(); i++) {
                num1 = num1 * 10 + charIntMap.get(s1.charAt(i));
            }
            for (int i = 0; i < s2.length(); i++) {
                num2 = num2 * 10 + charIntMap.get(s2.charAt(i));
            }
            sum = num1 + num2;

            int recAns = 0;

            for (int i = 0; i < s3.length(); i++) {
                recAns = recAns * 10 + charIntMap.get(s3.charAt(i));
            }

            if (sum == recAns) {
                // char[] chars = unique.toCharArray();
                // Arrays.sort(chars);
                // String sorted = new String(chars);
                // for (int i = 0; i < sorted.length(); i++) {
                // System.out.print(sorted.charAt(i) + "-" + charIntMap.get(sorted.charAt(i)) +
                // " ");
                // }
                // System.out.println();

                // or

                for (int i = 0; i < 26; i++) {
                    char ch = (char) (i + 'a');
                    if (charIntMap.containsKey(ch))
                        System.out.print(ch + "-" + charIntMap.get(ch) + " ");
                }
                System.out.println();
            }
            return;
        }
        for (int i = 0; i <= 9; i++) {
            if (!usedNumbers[i]) {
                usedNumbers[i] = true;
                charIntMap.put(unique.charAt(idx), i);
                Crypto_01(unique, idx, charIntMap, usedNumbers, s1, s2, s3);
                usedNumbers[i] = false;
            }
        }
    }
}