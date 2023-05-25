import java.util.HashMap;
import java.util.Map;

public class Solution {

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
            resultArray[i]=resultArray[i-1]+nums[i];
        }
        return resultArray;
    }
}
