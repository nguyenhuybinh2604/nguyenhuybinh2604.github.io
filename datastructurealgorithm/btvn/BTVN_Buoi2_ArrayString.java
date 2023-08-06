import java.util.Arrays;

public class BTVN_Buoi2_ArrayString {

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

    //https://leetcode.com/problems/split-a-string-in-balanced-strings/submissions/961607467/
    public int balancedStringSplit_1221(String s) {
        int countR = 0;
        int countL = 0;
        int countTotal = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'R') {
                countR++;
                if (countR == countL) {
                    countTotal++;
                    countL = 0;
                    countR = 0;
                }
            } else {
                countL++;
                if (countR == countL) {
                    countTotal++;
                    countL = 0;
                    countR = 0;
                }
            }
        }
        return countTotal;
    }

    // https://leetcode.com/problems/check-if-two-string-arrays-are-equivalent/submissions/961649906/
    public boolean arrayStringsAreEqual_1662(String[] word1, String[] word2) {
        int strIndexA = 0;
        int charIndexA = 0;
        int strIndexB = 0;
        int charIndexB = 0;
        while (strIndexA < word1.length && charIndexA < word1[strIndexA].length()
                && strIndexB < word2.length && charIndexB < word2[strIndexB].length()) {
            if (word1[strIndexA].charAt(charIndexA) == word2[strIndexB].charAt(charIndexB)) {
                if (charIndexA == word1[strIndexA].length() - 1) {
                    charIndexA = 0;
                    strIndexA++;
                } else charIndexA++;
                if (charIndexB == word2[strIndexB].length() - 1) {
                    charIndexB = 0;
                    strIndexB++;
                } else charIndexB++;
                if (strIndexA == word1.length && charIndexA == 0
                        && strIndexB == word2.length && charIndexB == 0) return true;
            } else return false;
        }
        return false;
    }

    //https://leetcode.com/problems/longest-mountain-in-array/submissions/962553806/
    public int longestMountain_845(int[] arr) {
        int up = 0;
        int down = 0;
        int length = 0;
        int maxLength = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == arr[i - 1]) {
                up = 0;
                down = 0;
            } else if (arr[i] > arr[i - 1]) {
                up++;
                if (i < arr.length - 1 && arr[i] > arr[i + 1]) {
                    down = 0;
                }
            } else if (arr[i] < arr[i - 1]) {
                down++;
                if (i < arr.length - 1 && arr[i] <= arr[i + 1]) {
                    if (up > 0) length = up + down;
                    if (length > maxLength) maxLength = length;
                    up = 0;
                } else {
                    if (up > 0) length = up + down;
                    if (length > maxLength) maxLength = length;
                }
            }
        }
        if (maxLength != 0) return maxLength + 1;
        else return 0;
    }

    //https://leetcode.com/problems/find-pivot-index/submissions/962522716/
    public int pivotIndex_724(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int sumLeft = 0;
            int sumRight = 0;
            if (i == 0) {
                for (int j = i + 1; j < nums.length; j++) sumRight += nums[j];
            } else if (i == nums.length - 1) {
                for (int j = 0; j < i; j++) sumLeft += nums[j];
            } else if (i > 0 && i < nums.length - 1) {
                for (int jL = 0; jL < i; jL++) sumLeft += nums[jL];
                for (int jR = i + 1; jR < nums.length; jR++) sumRight += nums[jR];
            }
            if (sumLeft == sumRight) return i;
        }
        return -1;
    }

    //https://leetcode.com/problems/truncate-sentence/submissions/962476540/
    public String truncateSentence_1816(String s, int k) {
        int index = 0;
        s = s + ' ';
        for (int i = 0; i < k; i++) {
            index = s.indexOf(' ', index + 1);
        }
        return s.substring(0, index);
    }

    //https://leetcode.com/problems/reverse-string/submissions/962482310/
    public void reverseString_344(char[] s) {
        char replacement;
        for (int i = 0; i < s.length / 2; i++) {
            replacement = s[i];
            s[i] = s[s.length - 1 - i];
            s[s.length - 1 - i] = replacement;
        }
    }

    //https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/submissions/962695500/
    public int findMinArrowShots_452(int[][] points) {
        Arrays.sort(points, (a, b) -> {
            if(a[0] == b[0]) {
                return Integer.compare(b[1], a[1]);
            }
            return Integer.compare(a[0], b[0]);
        });
        boolean[] hasBeenBurst = new boolean[points.length];
        int numOfArrows = 0;
        for (int i = 0; i < points.length; i++) {
            if (!hasBeenBurst[i]) {
                numOfArrows++;
                hasBeenBurst[i]=true;
                int leftBound = points[i][0];
                int rightBound = points[i][1];
                for (int j = i + 1; j < points.length; j++) {
                    if (!hasBeenBurst[j] && rightBound >= points[j][0] && points[j][1] >= leftBound) {
                        hasBeenBurst[j] = true;
                        leftBound = Math.max(leftBound, points[j][0]);
                        rightBound = Math.min(rightBound, points[j][1]);
                    }
                }
            }
        }
        return numOfArrows;
    }

}
