package HackerRank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HackerRank {

    // 29Mar2024. No1
    public int birthdayCakeCandles(List<Integer> candles) {
        int n = candles.size();
        int count = 0;
        int maxHeight = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            if (candles.get(i) > maxHeight) {

                // reset counter when encounter a taller candle. certainly at least one candle exists
                count = 1;

                // update to new height
                maxHeight = candles.get(i);

            } else if (candles.get(i) == maxHeight) {

                // add to count if meet a candle at the same max height
                count++;
            }
        }
        return count;
    }

    public static List<Integer> compareTriplets(List<Integer> a, List<Integer> b) {
        Integer[] count = {0, 0};
        for (int i = 0; i < 3; i++) {
            if (a.get(i) > b.get(i)) count[0]++;
            else if (a.get(i) < b.get(i)) count[1]++;
        }
        return Arrays.asList(count);
    }

    public static void staircase(int n) {
        char char1 = ' '; // Character to be repeated
        char char2 = '#'; // Character to be repeated
        for (int i = 1; i <= n; i++) {
            String str1 = String.valueOf(char1).repeat(n-i);
            String str2 = String.valueOf(char2).repeat(i);
            System.out.println(str1+str2);
        }
    }

}
