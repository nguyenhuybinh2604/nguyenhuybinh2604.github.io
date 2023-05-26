import java.util.*;

public class Solution {

    //https://leetcode.com/problems/two-sum/submissions/952720344/
    public int[] twoSum_1(int[] nums, int target) {
        int[] result = new int[2];
        for (int i = 0; i < nums.length - 1; i++) {
            result[0] = i;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    result[1] = j;
                    return result;
                }
            }
        }
        return result;
    }

    //https://leetcode.com/problems/single-number/submissions/956498532/
    public int singleNumber_136(int[] nums) {
        int returnValue = 0;
        Map<Integer, Integer> result = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int count = result.getOrDefault(nums[i], 0);
            result.put(nums[i], count + 1);
        }
        for (Map.Entry<Integer, Integer> entry : result.entrySet()) {
            if (entry.getValue() == 1) {
                returnValue = entry.getKey();
                break;
            }
        }
        return returnValue;
    }

    //https://leetcode.com/problems/running-sum-of-1d-array/submissions/956538827/
    public int[] runningSum_1480(int[] nums) {
        int[] resultArray = new int[nums.length];
        resultArray[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            resultArray[i] = resultArray[i - 1] + nums[i];
        }
        return resultArray;
    }

    //https://leetcode.com/problems/max-consecutive-ones/submissions/956559028/
    public int findMaxConsecutiveOnes_485(int[] nums) {
        int currentRunning;
        if (nums[0] == 1) currentRunning = 1;
        else currentRunning = 0;
        int returnValue = currentRunning;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == 1) {
                currentRunning++;
                if (currentRunning > returnValue) returnValue = currentRunning;
            } else currentRunning = 0;
        }
        return returnValue;
    }

    //https://leetcode.com/problems/rectangle-overlap/submissions/957532834/
    public boolean isRectangleOverlap_836(int[] rec1, int[] rec2) {
        ;
        return ((rec1[2] > rec2[0] && rec2[2] > rec1[0]) &&
                (rec1[3] > rec2[1] && rec2[3] > rec1[1]));
    }

    //https://leetcode.com/problems/majority-element/submissions/956968921/
    public int majorityElement_169(int[] nums) {
        int returnValue = 0;
        int maxFreq = 0;
        Map<Integer, Integer> result = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int count = result.getOrDefault(nums[i], 0);
            result.put(nums[i], count + 1);
            if (count + 1 > maxFreq) maxFreq = count + 1;
        }
        for (Map.Entry<Integer, Integer> entry : result.entrySet()) {
            if (entry.getValue() == maxFreq) {
                returnValue = entry.getKey();
                break;
            }
        }
        return returnValue;
    }

    //  https://leetcode.com/problems/shuffle-the-array/submissions/956983719/
    public int[] shuffle_1470(int[] nums, int n) {
        int[] resultArray = new int[n * 2];
        for (int i = 0; i < nums.length; i++) {
            if (i < n) {
                resultArray[i * 2] = nums[i];
            } else {
                resultArray[(i + 1 - n) * 2 - 1] = nums[i];
            }
        }
        return resultArray;
    }

    //https://leetcode.com/problems/missing-number/submissions/957007756/
    public int missingNumber_268(int[] nums) {
        int returnValue = 0;
        int[] countArray = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            countArray[nums[i]] = 1;
        }
        for (int i = 0; i < countArray.length; i++) {
            if (countArray[i] == 0) {
                returnValue = i;
                break;
            }
        }
        return returnValue;
    }

    //https://leetcode.com/problems/build-array-from-permutation/submissions/957009556/
    public int[] buildArray_1920(int[] nums) {
        int[] ans = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            ans[i] = nums[nums[i]];
        }
        return ans;
    }

    //https://leetcode.com/problems/jump-game/submissions/957432274/
    public boolean canJump_55(int[] nums) {
        int maxIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > maxIndex) return false;
            maxIndex = Math.max(maxIndex, i + nums[i]);
        }
        return true;
    }

    //if using recursion
    private boolean jump(int[] nums, int currentIndex) {
        boolean result = false;
        if (currentIndex >= nums.length - 1) return true;
        else if (nums[currentIndex] == 0)
            return false;
        else {
            for (int i = 1; i <= nums[currentIndex]; i++) {
                result = jump(nums, currentIndex + i);
                if (result) break;
            }
        }
        return result;
    }
}
