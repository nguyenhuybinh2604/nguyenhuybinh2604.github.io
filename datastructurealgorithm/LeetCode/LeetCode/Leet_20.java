package LeetCode;

import CustomClass.ListNode;

import java.util.*;

public class Leet_20 {
    public int maxArea(int[] height) {

        // 2 pointers to run from 2 sides inwards
        int i = 0;
        int j = height.length - 1;

        // set the maxArea as initial area
        int maxArea = (j - i) * Math.min(height[i], height[j]);

        // move as long as 2 pointers do not meet
        while (i < j) {

            // move the shorter side inwards hoping to be replaced by a longer side
            if (height[i] > height[j]) {
                j--;
                if (maxArea < (j - i) * Math.min(height[i], height[j]))
                    maxArea = (j - i) * Math.min(height[i], height[j]);

            } else {
                i++;
                if (maxArea < (j - i) * Math.min(height[i], height[j]))
                    maxArea = (j - i) * Math.min(height[i], height[j]);
            }
        }
        return maxArea;
    }

    // 13.04.2024
    public String intToRoman(int num) {

        // record each digit of num in a stack from right to left, to rebuild later in Roman format from left to right
        Stack<Integer> digits = new Stack<>();

        // keeping track of the number size
        int u = 1;

        while (num > 0) {
            digits.push((num % 10) * u);
            num = num / 10;
            u = u * 10;
        }

        // to store the converted to Roman number
        StringBuilder result = new StringBuilder();

        while (!digits.isEmpty()) {
            // get each digit from left to right
            int currentDigit = digits.pop();

            // Adding a string to the StringBuilder
            result.append(romanConvert(currentDigit));
        }
        return result.toString();
    }

    private String romanConvert(int n) {
        StringBuilder sb = new StringBuilder();

        // all of these arrays have first and last elements as dummy
        // Create an array of checkpoints
        int[] values = {0, 1, 5, 10, 50, 100, 500, 1000, 5000};

        // store corresponding Roman letters
        char[] letters = {'\u0000', 'I', 'V', 'X', 'L', 'C', 'D', 'M', 'X'};

        // and corresponding units
        int[] units = {0, 1, 1, 10, 10, 100, 100, 1000, 1000};

        // and corresponding units
        char[] unitLetter = {'\u0000', 'I', 'I', 'X', 'X', 'C', 'C', 'M', 'M'};

        // search for the range of n
        for (int i = 1; i < values.length - 1; i++) {
            // return if right on a checkpoint
            if (n == values[i])
                return sb.append(letters[i]).toString();

                // for case with n falls in between checkpoints
            else if (n > values[i] && n < values[i + 1]) {

                // if n is 1 "unit" below a checkpoint -> 1 x active unit in that range + that checkpoint
                if (n == values[i + 1] - units[i])
                    return sb.append(unitLetter[i]).append(letters[i + 1]).toString();

                    // if not -> prev checkpoint + k times the units, k is the difference between n and the last checkpoint in current unit
                else {
                    int k = (n - values[i]) / units[i];
                    sb.append(letters[i]);
                    for (int j = 0; j < k; j++) {
                        sb.append(unitLetter[i]);
                    }
                    return sb.toString();
                }

            }
        }
        return sb.toString();
    }

    public int romanToInt(String s) {
        int ans = 0, num = 0;

        // checking each letter from right to left
        for (int i = s.length() - 1; i >= 0; i--) {
            switch (s.charAt(i)) {
                case 'I':
                    num = 1;
                    break;
                case 'V':
                    num = 5;
                    break;
                case 'X':
                    num = 10;
                    break;
                case 'L':
                    num = 50;
                    break;
                case 'C':
                    num = 100;
                    break;
                case 'D':
                    num = 500;
                    break;
                case 'M':
                    num = 1000;
                    break;
            }

            // if a I is followed by a II, it's added to II.
            // if a I is followed by a V or X, it's subtracted from V or X.
            if (3 * num < ans) ans -= num;
            else ans += num;
        }
        return ans;
    }

    public String longestCommonPrefix(String[] strs) {

        // start with the first string as the prefix (match 100% with itself)
        String prefix = strs[0];

        // run through the list of all strings
        for (int index = 1; index < strs.length; index++) {

            // as long as the current prefix does not start from 0 in the next string (thus being the common prefix), decrease its length
            while (strs[index].indexOf(prefix) != 0) {

                // if not found, decrease its length until found
                // if nothing matched (length decreased to 0) -> always match with all first null prefixes
                prefix = prefix.substring(0, prefix.length() - 1);
            }

            if (prefix.isEmpty()) return "";
        }
        return prefix;
    }

    // 13.04.2024
    public List<List<Integer>> threeSum(int[] nums) {

        // sort the number array from low to high
        Arrays.sort(nums);

        // use hashset to store the triplets to avoid duplicate, numbers in the triplets are already sorted
        Set<List<Integer>> s = new HashSet<>();

        // use i, j, k to represent each number in the triplets, min length of nums is guaranteed to be 3
        for (int i = 0; i < nums.length - 2; i++) {

            // j and k run from both ends of the remaining part of nums after i is taken
            int j = i + 1;
            int k = nums.length - 1;

            // continue to shift j and k to find matching triplets
            while (j < k) {
                if (nums[i] + nums[j] + nums[k] == 0) {
                    s.add(Arrays.asList(nums[i], nums[j], nums[k]));

                    // skip duplicates
                    while (j < k && nums[j] == nums[j + 1]) j++;
                    while (j < k && nums[k] == nums[k - 1]) k--;
                    j++;
                    k--;

                    // if the current sum is larger than 0, move k to the left to find a smaller nums k
                } else if ((nums[i] + nums[j] + nums[k] > 0)) {
                    k--;

                    // on the contrary, move j to the right to find a larger nums j
                } else j++;
            }
        }
        return new ArrayList<>(s);
    }

    // 26.04.2024
    public int threeSumClosest(int[] nums, int target) {

        // sort the number array from low to high
        Arrays.sort(nums);

        int closet = nums[0] + nums[1] + nums[nums.length - 1];

        for (int i = 0; i < nums.length - 2; i++) {

            // j and k run from both ends of the remaining part of nums after i is taken
            int j = i + 1;
            int k = nums.length - 1;

            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];

                // instant return if the sum matched target
                if (sum == target) return target;

                // update the closest value in case a smaller difference is found
                if (Math.abs(target - sum) < Math.abs(target - closet)) {
                    closet = sum;
                }
                if (sum < target) {

                    // shift rightwards if the sum is less than the target (attempt to move the sum closer to target)
                    j++;

                    // eliminate series of similar left boundaries
                    while (j < k && nums[j] == nums[j - 1]) {
                        j++;
                    }
                } else {

                    // shift leftwards if the sum is more than the target (attempt to move the sum closer to target)
                    k--;

                    // eliminate series of similar left boundaries
                    while (j < k && nums[k] == nums[k + 1]) {
                        k--;
                    }
                }
            }
        }
        return closet;
    }

    public List<String> letterCombinations(String digits) {
        // the list to store answers
        List<String> combinations = new ArrayList<>();

        if (digits.isEmpty()) return combinations;

        StringBuilder sb = new StringBuilder();

        // Declare and initialize the HashMap
        HashMap<Character, String> dialpad = new HashMap<>();

        // Populate the HashMap with digit-letter mappings
        dialpad.put('2', "abc");
        dialpad.put('3', "def");
        dialpad.put('4', "ghi");
        dialpad.put('5', "jkl");
        dialpad.put('6', "mno");
        dialpad.put('7', "pqrs");
        dialpad.put('8', "tuv");
        dialpad.put('9', "wxyz");

        // initiate the iterations
        generateLetter(dialpad, sb, combinations, digits, 0);

        return combinations;
    }

    private void generateLetter(HashMap<Character, String> dialpad, StringBuilder sb, List<String> combinations, String digits, int layer) {
        // get all letters corresponding to the digit at layer-th index
        String letters = dialpad.get(digits.charAt(layer));

        // iterate through every character
        for (int i = 0; i < letters.length(); i++) {

            // first add the character to the end of sb
            sb.append(letters.charAt(i));

            // add the whole sb to the result list if the end of digits is reached (aka stop condition)
            if (layer == digits.length() - 1) combinations.add(sb.toString());

                // if not reached, continue with the next layer
            else generateLetter(dialpad, sb, combinations, digits, layer + 1);

            // remove the last character to make space for the next char in for loop
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        // result list
        Set<List<Integer>> s = new HashSet<>();

        // instant return if not enough digits
        if (nums.length < 4) return new ArrayList<>(s);

        // Firstly sort the nums array
        Arrays.sort(nums);

        // use h, i, j, k to represent each number in the quadlets, min length of nums is guaranteed to be 3
        for (int h = 0; h < nums.length - 3; h++) {

            // skip similar digits after already ran through once
            if (h > 0 && nums[h] == nums[h - 1]) continue;

            for (int i = h + 1; i < nums.length - 2; i++) {
                // skip similar digits after already ran through once
                if (i > h + 1 && nums[i] == nums[i - 1]) continue;

                // j and k run from both ends of the remaining part of nums after i is taken
                int j = i + 1;
                int k = nums.length - 1;

                // continue to shift j and k to find matching triplets
                while (j < k) {
                    if (isSumInRange(nums[h], nums[i], nums[j], nums[k]) && nums[h] + nums[i] + nums[j] + nums[k] == target) {
                        s.add(Arrays.asList(nums[h], nums[i], nums[j], nums[k]));

                        // skip duplicates
                        while (j < k && nums[j] == nums[j + 1]) j++;
                        while (j < k && nums[k] == nums[k - 1]) k--;
                        j++;
                        k--;

                        // if the current sum is larger than 0, move k to the left to find a smaller nums k
                    } else if (isSumInRange(nums[h], nums[i], nums[j], nums[k]) && (nums[h] + nums[i] + nums[j] + nums[k] > target)) {
                        k--;

                        // on the contrary, move j to the right to find a larger nums j
                    } else j++;
                }
            }
        }
        return new ArrayList<>(s);
    }

    public static boolean isSumInRange(int a, int b, int c, int d) {
        long sum = (long) a + b + c + d; // Use long to avoid overflow
        return sum >= Integer.MIN_VALUE && sum <= Integer.MAX_VALUE;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        // save the nodes in an array
        ListNode[] nodes = new ListNode[30];
        nodes[0] = head;
        int index = 1;

        // get each node until the end of the node list
        while (head.next != null) {
            nodes[index] = head.next;
            head = head.next;
            index++;
        }

        // get the index of the target cell to be deleted
        int target = index - n;

        // removing the head
        if (target == 0) return nodes[0].next;
            // removing the tail
        else if (n == 1) {
            nodes[target - 1].next = null;
            return nodes[0];
        } else {
            nodes[target - 1].next = nodes[target + 1];
            return nodes[0];
        }
    }


}



