import java.util.*;

public class BTVN_Buoi9 {

    // https://leetcode.com/problems/valid-parentheses/submissions/996840884/
    public boolean isValid_20(String s) {
        // tao stack chua cac dau mo ngoac (, [ va {
        Deque<Character> bracketStack = new ArrayDeque<>();
        // duyet het string
        for (int i = 0; i < s.length(); i++) {
            // add vao stack theo thu tu, neu s[i] la 1 trong 3 dau mo ngoac
            if (s.charAt(i) == '(' || s.charAt(i) == '[' || s.charAt(i) == '{') bracketStack.push(s.charAt(i));
            // khi gap dau dong ngoac dau tien -> phai co dau mo ngoac tuong ung nam tren cung cua stack
            if (s.charAt(i) == ')') {
                // neu gap dau dong ngoac nhung stack empty -> thieu mo ngoac -> false
                if (bracketStack.isEmpty()) return false;
                    // neu lam thanh 1 cap thi pop dau mo ngoac ra khoi stack
                else if (bracketStack.peek() == '(') bracketStack.pop();
                    // neu dau mo ngoac trong stack khac dau dong ngoac tai vi tri i -> khong cung loai -> false
                else if (bracketStack.peek() != '(') return false;
                // tuong tu nhu tren
            } else if (s.charAt(i) == ']') {
                if (bracketStack.isEmpty()) return false;
                else if (bracketStack.peek() == '[') bracketStack.pop();
                else if (bracketStack.peek() != '[') return false;
            } else if (s.charAt(i) == '}') {
                if (bracketStack.isEmpty()) return false;
                else if (bracketStack.peek() == '{') bracketStack.pop();
                else if (bracketStack.peek() != '{') return false;
            }
        }
        // neu duyet het string nhung van con thua dau mo ngoac -> false
        if (bracketStack.size() != 0) return false;
        // neu chay het string va khong gap truong hop false nao -> true
        return true;
    }

    // https://leetcode.com/problems/majority-element-ii/submissions/996879724/
    public List<Integer> majorityElement_229(int[] nums) {
        // hashmap de dem so lan xuat hien
        HashMap<Integer, Integer> countMap = new HashMap<>();
        // list chua answer
        List<Integer> ans = new ArrayList<>();
        // duyet het mang nums
        for (int i = 0; i < nums.length; i++) {
            // dem so lan xuat hien
            countMap.put(nums[i], countMap.getOrDefault(nums[i], 0) + 1);
            // add vao ket qua neu lan +1 nums[i] vua du vuot qua n/3
            if (countMap.getOrDefault(nums[i], 0) - 1 <= nums.length / 3 && countMap.getOrDefault(nums[i], 0) > nums.length / 3)
                ans.add(nums[i]);
            if (ans.size()==2) return ans;
        }
        return ans;
    }

}
