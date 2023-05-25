import java.util.HashMap;
import java.util.Map;

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

    public boolean isRectangleOverlap_836(int[] rec1, int[] rec2) {
        boolean returnValue = false;
        if ((rec1[0]>rec2[2] || rec1[2]>rec2[0]) &&
                (rec1[1]>rec2[3] || rec1[3]>rec2[1])) returnValue=true;
        return returnValue;
    }
}
