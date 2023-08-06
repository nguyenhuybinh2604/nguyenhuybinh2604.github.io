import java.util.*;

public class BTVN_Buoi4_Recursion {

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

    //https://leetcode.com/problems/decode-string/submissions/973299606/
    public String decodeString_394_v2(String s) {
        // tim ngoac dong gan nhat -> truoc do phai co 1 ngoac mo
        int i = s.indexOf(']');

        //neu khong co ngoac -> tra nguyen string
        if (i == -1) return s;

        //tim diem dau
        int j = s.substring(0, i).lastIndexOf('[');

        //tim so lan lap
        int k = j;
        while (k > 0 && s.charAt(k - 1) >= '0' && s.charAt(k - 1) <= '9') k--;
        int count = Integer.parseInt(s.substring(k, j));

        //String o giua
        String mid = s.substring(j + 1, i);

        //ghep chuoi
        StringBuilder sb = new StringBuilder();
        sb.append(s.substring(0, k));
        for (int l = 0; l < count; l++) sb.append(mid);
        sb.append(s.substring(i + 1));

        return decodeString_394_v2(sb.toString());
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

    public int findTheWinner_1823_v2(int n, int k) {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            list.add(i);
        }

        return find(list, k, 0);
    }

    int find(List<Integer> list, int k, int startPos) {
        if (list.size() == 1) return list.get(0);

        int layoffPos = (startPos + k - 1) % list.size();
        list.remove(layoffPos);

        return find(list, k, layoffPos);
    }

    public void recurse_22(List<String> res, int left, int right, String s, int n) {
        if (s.length() == n * 2) {
            res.add(s);
            return;
        }

        if (left < n) {
            recurse_22(res, left + 1, right, s + "(", n);
        }

        if (right < left) {
            recurse_22(res, left, right + 1, s + ")", n);
        }
    }

    public List<String> generateParenthesis_22(int n) {
        List<String> res = new ArrayList<String>();
        recurse_22(res, 0, 0, "", n);
        return res;
    }

    int mod = (int) 1e9 + 7;

    //https://leetcode.com/problems/count-good-numbers/submissions/973776379/
    public int countGoodNumbers_1922(long n) {
        //deciding n/2 or n/2+1 depending on n is even or odd
        long powerOf5 = (n % 2 == 0 ? (n / 2) : (n / 2) + 1);

        //second power would be n/2 only irrespective of even or odd
        long powerOf4 = n / 2;

        return (int) (pow_1922(5,powerOf5)*pow_1922(4,powerOf4) % mod);
    }

    public long pow_1922(int base, long power) {
        if (power == 0) return 1;
        if (power == 1) return base;
        long temp = pow_1922(base, power / 2);
        if (power % 2 == 0) return (temp * temp) % mod;
        else return (base * temp * temp) % mod;
    }
}
