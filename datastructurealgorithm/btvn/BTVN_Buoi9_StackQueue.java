import java.util.*;

public class BTVN_Buoi9_StackQueue {

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
            if (ans.size() == 2) return ans;
        }
        return ans;
    }

    //https://leetcode.com/problems/remove-duplicate-letters/submissions/1002828845/
    public String removeDuplicateLetters_319(String s) {
        // luu lan xuat hien cuoi cung cua s[i] trong chuoi
        // size = 26 do chi co 26 chu cai in thuong
        Integer[] lastIndex = new Integer[26];
        // chay tu dau den cuoi s -> cac index dai hon ghi de len index cu -> cuoi cung la index xa nhat
        for (int i = 0; i < s.length(); i++) lastIndex[s.charAt(i) - 'a'] = i;
        Stack<Integer> ansStack = new Stack<>();
        // array de theo doi ki tu da duoc duyet chua
        boolean[] checked = new boolean[26];

        for (int i = 0; i < s.length(); i++) {
            // moi ki tu duoc dai dien boi 1 stt tuong ung tu 0-26
            int curr = s.charAt(i) - 'a';
            // neu gap ki tu da duoc duyet thi bo qua (moi ki tu chi lay 1 lan)
            if (checked[curr]) continue;
            // neu ki tu dang duyet be hon ki tu duoc sap trong stack, dong thoi ki tu trong stack chua phai la ki tu cung loai cuoi cung
            // thi go het tat ca cac ki tu da sap (dap ung dieu kien) de lay ki tu dang duyet
            while (!ansStack.isEmpty() && curr < ansStack.peek() && i < lastIndex[ansStack.peek()]) {
                // neu 1 ki tu bi go thi xem nhu chua duoc duyet
                checked[ansStack.peek()] = false;
                // thuc hien go khoi stack
                ansStack.pop();
            }
            // day ki tu moi vao
            ansStack.push(curr);
            // danh dau la da duyet
            checked[curr] = true;
        }
        StringBuilder ansStr = new StringBuilder();
        while (!ansStack.isEmpty()) {
            // noi lai chuoi dap an, dung so da convert + 'a' de chuyen nguoc thanh ki tu
            ansStr.append((char) (ansStack.pop() + 'a'));
        }
        // dao nguoc chuoi do go stack tu cuoi len
        return ansStr.reverse().toString();
    }

    public long subArrayRanges_2104(int[] nums) {
        Arrays.sort(nums);
        long ans = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                ans += (nums[j] - nums[i]);
            }
        }
        return ans;
    }

    // https://leetcode.com/problems/longest-absolute-file-path/submissions/1011074733/
    public int lengthLongestPath_388(String input) {
        // stack de luu vet do dai cua parent folder
        Deque<Integer> stack = new ArrayDeque<>();

        // bien ket qua
        int maxLength = 0;

        // feed vao do dai truoc file/folder dau tien
        stack.push(0);

        // chia chuoi thanh cac folder/file
        String[] strArray = input.split("\n");

        // duyet tung chuoi folder/file
        for (String s : strArray) {
            // so lan lui vao, neu khong lui vao -> lastIndex tra ve -1 -> so lan = 0;
            int noOfTabs = s.lastIndexOf("\t") + 1;

            // level cua folder, neu khong lui vao -> level 1;
            int level = noOfTabs + 1;

            // back ve parent folder cua folder/file hien tai
            while (level < stack.size()) stack.pop();

            // do dai cua link hien tai = do dai tinh den parent folder + ten file (tru di so lan tab) + "\";
            int currentLength = stack.peek() + s.length() - noOfTabs + 1;

            // neu chuoi co chua file
            if (s.contains(".")) {
                // update do dai lon nhat tim duoc, do la file nen bo di "\" add o tren
                maxLength = Math.max(currentLength - 1, maxLength);

                // neu khong phai la file -> la folder -> day do dai cua folder vao stack
            } else stack.push(currentLength);
        }
        return maxLength;
    }

    public long continuousSubarrays_2762(int[] nums) {
        Deque<Integer> maxQ = new ArrayDeque<>();
        Deque<Integer> minQ = new ArrayDeque<>();
        int left = 0;
        long res = 0;

        for (int right = 0; right < nums.length; right++) {
            while (!maxQ.isEmpty() && nums[maxQ.peekLast()] < nums[right]) {
                maxQ.pollLast();
            }
            maxQ.offerLast(right);

            while (!minQ.isEmpty() && nums[minQ.peekLast()] > nums[right]) {
                minQ.pollLast();
            }
            minQ.offerLast(right);

            while (!maxQ.isEmpty() && !minQ.isEmpty() && nums[maxQ.peekFirst()] - nums[minQ.peekFirst()] > 2) {
                if (maxQ.peekFirst() < minQ.peekFirst()) {
                    left = maxQ.peekFirst() + 1;
                    maxQ.pollFirst();
                } else {
                    left = minQ.peekFirst() + 1;
                    minQ.pollFirst();
                }
            }

            res += right - left + 1;
        }
        return res;
    }

}
