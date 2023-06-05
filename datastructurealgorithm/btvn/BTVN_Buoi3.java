import java.util.*;

public class BTVN_Buoi3 {
    //https://leetcode.com/problems/third-maximum-number/submissions/963019487/
    public int thirdMax(int[] nums) {
        Arrays.sort(nums);
        int count = 1;
        //do sort tu be den lon nen chay vong for nguoc lai
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                count++;
                //neu dem du 3 gia tri max khac nhau thi tra kq va thoat vong for
                if (count == 3) return nums[i];
            }
        }
        //neu chay het vong for nhung chua dat duoc 03 count thi return gia tri max dau tien
        return nums[nums.length - 1];
    }

    //https://leetcode.com/problems/height-checker/submissions/963675403/
    public int heightChecker_1051(int[] heights) {
        int count = 0;
        //tao mang expected height tu mang goc + tien hanh sort
        int[] expectedHeights = Arrays.copyOf(heights, heights.length);
        Arrays.sort(expectedHeights);
        //dem so phan tu khac nhau
        for (int i = 0; i < heights.length; i++) if (heights[i] != expectedHeights[i]) count++;
        return count;
    }

    //https://leetcode.com/problems/sort-the-people/submissions/963684595/
    public String[] sortPeople_2418(String[] names, int[] heights) {
        //chuyen data vao treemap sap xep giam dan theo key = height
        Map<Integer, String> treeMap = new TreeMap<>(Collections.reverseOrder());
        for (int i = 0; i < heights.length; i++) treeMap.put(heights[i], names[i]);
        int i = 0;
        //chuyen value ra mang names
        for (Map.Entry<Integer, String> entry : treeMap.entrySet()) {
            names[i] = entry.getValue();
            i++;
        }
        return names;
    }

    //https://leetcode.com/problems/sort-array-by-increasing-frequency/submissions/963710396/
    public int[] frequencySort_1636(int[] nums) {

        // Dem tan suat xuat hien
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        // thiet ke comparator de sap xep theo tan suat tang dan
        // va theo gia tri neu tan suat nhu nhau
        Comparator<Integer> customComparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer num1, Integer num2) {
                int freq1 = freqMap.get(num1);
                int freq2 = freqMap.get(num2);
                if (freq1 != freq2) {
                    return freq1 - freq2;
                } else {
                    return num2 - num1;
                }
            }
        };

        // Thuc hien sap xep
        Integer[] boxedNums = new Integer[nums.length];
        for (int i = 0; i < nums.length; i++) {
            boxedNums[i] = nums[i];
        }

        Arrays.sort(boxedNums, customComparator);

        // Chuyen nguoc ve mang ket qua va tra ra
        for (int i = 0; i < nums.length; i++) {
            nums[i] = boxedNums[i];
        }
        return nums;
    }

    //https://leetcode.com/problems/sorting-the-sentence/submissions/963726698/
    public String sortSentence_1859(String s) {
        StringBuilder result = new StringBuilder();
        Map<Integer, String> treeMap = new TreeMap<>();
        s = s + ' ';
        int index = 0;
        while (index < s.length()) {
            String subStr = s.substring(index, s.indexOf(' ', index));
            treeMap.put(Character.getNumericValue(subStr.charAt(subStr.length() - 1)), subStr.substring(0, subStr.length() - 1));
            index = s.indexOf(' ', index) + 1;
        }
        for (Map.Entry<Integer, String> entry : treeMap.entrySet()) {
            result.append(entry.getValue() + ' ');
        }
        result.replace(result.length() - 1, result.length(), "");
        return result.toString();
    }

    //https://leetcode.com/problems/longest-subsequence-with-limited-sum/submissions/963768935/
    public int[] answerQueries_2389(int[] nums, int[] queries) {
        Map<Integer, Integer> map = new HashMap<>();

        //sort ca day so va day tong so tu be den lon
        Arrays.sort(nums);
        int[] sortedQueries = Arrays.copyOf(queries, queries.length);
        Arrays.sort(sortedQueries);

        int startIndex = 0;
        int sum = 0;
        //duyet tung so tong
        for (int i = 0; i < sortedQueries.length; i++) {
            //duyet day so tu startIndex gan nhat den n
            for (int j = startIndex; j < nums.length; j++) {
                if (sum + nums[j] <= sortedQueries[i]) {
                    sum += nums[j];

                    //tan dung startIndex lam count
                    startIndex++;
                } else {
                    //neu cong them so tiep theo vuot qua so tong -> nhat so hien tai luu vao map
                    //luu ket qua dem so , key = queries[i]; value = count
                    map.put(sortedQueries[i], startIndex);
                    break;
                }
            }
            map.put(sortedQueries[i], startIndex);
        }

        int[] answer = new int[queries.length];
        for (int i = 0; i < queries.length; i++) answer[i] = map.get(queries[i]);
        return answer;
    }

    //https://leetcode.com/problems/find-the-difference/submissions/963776081/
    public char findTheDifference_389(String s, String t) {
        char[] charS = s.toCharArray();
        char[] charT = t.toCharArray();
        Arrays.sort(charS);
        Arrays.sort(charT);
        for (int i = 0; i < s.length(); i++) if (charS[i] != charT[i]) return charT[i];
        return charT[t.length() - 1];
    }

    //https://leetcode.com/problems/sort-colors/submissions/963782152/
    public void sortColors_75(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                if (nums[j] < nums[i]) {
                    int tmp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = tmp;
                }
            }
        }
    }
}
