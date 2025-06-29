import HackerRank.HackerRank;
import LeetCode.LeetCode75;
import LeetCode.Leet_10;
import LeetCode.Leet_20;

public class Main {
    public static void main(String[] args) {

        Leet_10 leet_10 = new Leet_10();
        Leet_20 leet_20 = new Leet_20();
        HackerRank hackerRank = new HackerRank();
        LeetCode75 leetCode75 = new LeetCode75();

        int[] arr1 = {1000000000,1000000000,1000000000,1000000000};
        String str1 = "abc";
        String str2 = "qrfts";


        System.out.println(leetCode75.mergeAlternately(str1,str2));

    }
}
