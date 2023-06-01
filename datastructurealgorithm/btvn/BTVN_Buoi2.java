import java.util.Arrays;

public class BTVN_Buoi2 {

    //https://leetcode.com/problems/find-numbers-with-even-number-of-digits/submissions/958275448/
    public int findNumbers_1295(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            int countDigit = 0;
            while (nums[i] != 0) {
                countDigit++;
                nums[i] /= 10;
            }
            if (countDigit % 2 == 0) count++;
        }
        return count;
    }

    //https://leetcode.com/problems/merge-sorted-array/submissions/958280336/
    public void merge_88(int[] nums1, int m, int[] nums2, int n) {
        for (int i = m; i < m + n; i++) {
            nums1[i] = nums2[i - m];
        }
        Arrays.sort(nums1);
    }

    //https://leetcode.com/problems/remove-element/submissions/958289864/
    public int removeElement_27(int[] nums, int val) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == val) {
                count++;
                nums[i] = 51;
            }
        }
        Arrays.sort(nums);
        return nums.length - count;
    }

    //https://leetcode.com/problems/duplicate-zeros/submissions/960241728/
    public void duplicateZeros_1089(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] == 0) {
                for (int j = arr.length - 1; j > i + 1; j--) {
                    arr[j] = arr[j - 1];
                }
                arr[i + 1] = 0;
                i++;
            }
        }
    }

    //https://leetcode.com/problems/move-zeroes/submissions/960277572/
    public void moveZeroes_283(int[] nums) {
        int lastZero = nums.length - 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] == 0) {
                for (int j = i; j < lastZero; j++) {
                    nums[j] = nums[j + 1];
                }
                if (lastZero >= 0) {
                    nums[lastZero] = 0;
                    lastZero--;
                }
            }
        }
    }

    public int longestMountain_845(int[] arr) {

        return 0;
    }

    //https://leetcode.com/problems/first-unique-character-in-a-string/submissions/960346654/
    public int firstUniqChar_387(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (i == 0) {
                if (s.substring(i + 1).indexOf(s.charAt(i)) == -1)
                    return i;
            } else if (s.substring(0, i).indexOf(s.charAt(i)) == -1 && s.substring(i + 1).indexOf(s.charAt(i)) == -1)
                return i;
        }
        return -1;
    }
}
