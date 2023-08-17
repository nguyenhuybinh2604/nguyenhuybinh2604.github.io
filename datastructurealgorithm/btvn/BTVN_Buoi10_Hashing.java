import java.util.*;

public class BTVN_Buoi10_Hashing {
    // https://leetcode.com/problems/contains-duplicate/submissions/1009154025/
    public boolean containsDuplicate_217(int[] nums) {
        // dung hashset de theo doi cac value da xuat hien
        HashSet<Integer> checkSet = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            // neu tim thay value trung lap tra ngay ve true
            if (checkSet.contains(nums[i])) return true;
            else checkSet.add(nums[i]);
        }
        // neu duyet het va khong thay gi -> false
        return false;
    }

    // https://leetcode.com/problems/number-of-good-pairs/submissions/1009220166/
    public int numIdenticalPairs_1512(int[] nums) {
        //Set hashMap
        HashMap<Integer, Integer> countMap = new HashMap<>();

        // Dem so lan xuat hien cua tung so
        for (int i = 0; i < nums.length; i++) {
            countMap.put(nums[i], countMap.getOrDefault(nums[i], 0) + 1);
        }

        // Dem tong so pair, cong thuc = n * (n-1)/2
        int count = 0;
        // Iterating through the HashMap
        Iterator<Map.Entry<Integer, Integer>> iterator = countMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, Integer> entry = iterator.next();
            Integer value = entry.getValue();
            count += value * (value - 1) / 2;
        }
        return count;
    }

    // https://leetcode.com/problems/sum-of-unique-elements/submissions/1009230045/
    public int sumOfUnique_1748(int[] nums) {
        // luu data dang set -> lay unique number
        HashSet<Integer> countSet = new HashSet<>();

        // mang danh dau so co lap lai hay khong
        // size = 101 do nums[i] tu 1->100;
        boolean[] isRepeated = new boolean[101];

        for (int i = 0; i < nums.length; i++) {
            // neu da gap nums[i] roi -> danh dau co lap lai
            if (countSet.contains(nums[i])) isRepeated[nums[i]] = true;
            countSet.add(nums[i]);
        }

        // khoi tao bien tinh tong
        int count = 0;

        // duyet qua toan bo set
        for (Integer element : countSet) {
            // cong tung so neu khong bi lap lai
            if (!isRepeated[element]) count += element;
        }

        return count;
    }

    // https://leetcode.com/problems/longest-consecutive-sequence/submissions/1009253108/
    public int longestConsecutive_128(int[] nums) {
        TreeSet<Integer> treeSet = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            // add tung so vao treeSet, sap xep tang dan
            treeSet.add(nums[i]);
        }
        if (treeSet.size() == 0) return 0;
        Integer tmp = treeSet.first();
        int maxCount = 1;
        int count = 1;

        for (Integer element : treeSet) {
            // +1 neu chuoi duoc noi lien
            if (element == tmp + 1) count++;

            // so sanh voi count hien tai de lay maxCount
            if (count > maxCount) maxCount = count;

            // neu chuoi bi gian doan thi reset counter
            if (element > tmp + 1) count = 1;
            tmp = element;
        }
        return maxCount;
    }

    public int minExtraChar_2707(String s, String[] dictionary) {
        int[] dp = new int[s.length() + 1];
        //dp[i] = so luong extra character tinh tu vi tri thu i den cuoi xau s

        for (int i = s.length() - 1; i >= 0; i--) {
            dp[i] = dp[i + 1] + 1;

            for (String w : dictionary) {
                if (i + w.length() <= s.length()) {
                    String subS = s.substring(i, i + w.length());

                    if (w.equals(subS)) {
                        dp[i] = Math.min(dp[i], dp[i + w.length()]);
                    }
                }
            }
        }

        return dp[0];
    }

}
