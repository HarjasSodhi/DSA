public class AllPalindromicPartitions {
    public static boolean isPalindrome(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != str.charAt(str.length() - i - 1))
                return false;
        }
        return true;
    }

    public static void solution(String str, String asf) {
        if (str.length() == 0) {
            System.out.println(asf);
        }
        for (int i = 1; i <= str.length(); i++) {
            String currStr = str.substring(0, i);
            if (isPalindrome(currStr)) {
                solution(str.substring(i, str.length()), asf + "(" + currStr + ") ");
            }
        }
    }

    public static void main(String[] args) {
        solution("pep", "");
    }
}
