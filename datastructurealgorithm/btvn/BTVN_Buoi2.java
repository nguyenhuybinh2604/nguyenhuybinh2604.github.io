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

    //https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string/submissions/959340437/
    public int strStr_28(String haystack, String needle) {
        return haystack.indexOf(needle);
    }

    
}
