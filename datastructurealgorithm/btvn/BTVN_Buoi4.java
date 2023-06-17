import java.sql.Array;
import java.util.*;

public class BTVN_Buoi4 {

    //https://leetcode.com/problems/fibonacci-number/submissions/971850714/
    public int fib_509(int n) {
        if (n == 0) return 0;
        else if (n == 1) return 1;
        else return fib_509(n - 1) + fib_509(n - 2);
    }

    //method ho tro vi neu truyen subarray thi khong thay doi duoc s
    void swap_344(char[] s, int low, int high) {
        if (high - low + 1 <= 1) return;
        char tmp = s[low];
        s[low] = s[high];
        s[high] = tmp;
        swap_344(s, low + 1, high - 1);
    }

    //https://leetcode.com/problems/reverse-string/submissions/973167462/
    public void reverseString_344(char[] s) {
        swap_344(s, 0, s.length - 1);

    }

    //https://leetcode.com/problems/power-of-three/submissions/971857034/
    public boolean isPowerOfThree_326(int n) {
        if (n <= 0) return false;
        else if (n == 1) return true;
        else if (n % 3 != 0) return false;
        else return isPowerOfThree_326(n / 3);
    }

    //https://leetcode.com/problems/power-of-four/submissions/971859796/
    public boolean isPowerOfFour_342(int n) {
        if (n <= 0) return false;
        else if (n == 1) return true;
        else if (n % 4 != 0) return false;
        else return isPowerOfFour_342(n / 4);
    }


    //https://leetcode.com/problems/decode-string/submissions/973144754/
    public String decodeString_394(String s) {
        Stack<Integer> numStack = new Stack<>();
        Stack<StringBuilder> strBuild = new Stack<>();
        StringBuilder str = new StringBuilder();
        int num = 0;
        for (char c : s.toCharArray()) {
            if (c >= '0' && c <= '9') {
                num = num * 10 + c - '0';
            } else if (c == '[') {
                strBuild.push(str);
                str = new StringBuilder();
                numStack.push(num);
                num = 0;
            } else if (c == ']') {
                StringBuilder temp = str;
                str = strBuild.pop();
                int count = numStack.pop();
                while (count-- > 0) {
                    str.append(temp);
                }
            } else {
                str.append(c);
            }
        }
        return str.toString();
    }

    //https://leetcode.com/problems/find-the-winner-of-the-circular-game/submissions/973193671/
    public int findTheWinner_1823(int n, int k) {
        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i = 1; i <= n; i++) {
            queue.add(i);
        }
        for (int i = 1; i < n; i++) {
            int removeIndex;
            if (k % queue.size() != 0) removeIndex = k % queue.size();
            else removeIndex = queue.size();
            for (int j = 1; j < removeIndex; j++) {
                int firstElement = queue.peek();
                queue.remove();
                queue.add(firstElement);
            }
            queue.remove();
        }
        return queue.peek();
    }

    
}
