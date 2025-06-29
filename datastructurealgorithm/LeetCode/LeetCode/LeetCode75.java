package LeetCode;

public class LeetCode75 {
    public String mergeAlternately(String word1, String word2) {
        StringBuilder result = new StringBuilder();
        int i1 = 0;
        int i2 = 0;
        while (i1<word1.length() || i2<word2.length()) {
            if (i1<word1.length()) {
                result.append(word1.charAt(i1));
                i1+=1;
            }
            if (i2<word2.length()) {
                result.append(word2.charAt(i2));
                i2+=1;
            }
        }

        return result.toString();
    }

    public String gcdOfStrings(String str1, String str2) {
        int n1 = str1.length();
        int n2 = str2.length();
        return null;
    }

}
