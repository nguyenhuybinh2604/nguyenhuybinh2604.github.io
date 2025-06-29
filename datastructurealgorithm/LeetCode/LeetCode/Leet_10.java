package LeetCode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Leet_10 {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n1 = nums1.length, n2 = nums2.length;

        // ensure the first array variable is the smaller array to save search time
        if (n1 > n2)
            return findMedianSortedArrays(nums2, nums1);

        // total number of numbers on the left of the "merged" median
        int half = (n1 + n2 + 1) / 2;

        // set initial cursors for the smaller array
        int low1 = 0;
        int high1 = n1;

        while (low1 <= high1) {

            // set initial search point
            int mid1 = (low1 + high1) / 2;

            // corresponding point in the larger array = half of the cells - number of checking cells in the smaller
            int mid2 = half - mid1;

            int l1 = Integer.MIN_VALUE;
            int l2 = Integer.MIN_VALUE;
            int r1 = Integer.MAX_VALUE;
            int r2 = Integer.MAX_VALUE;

            if (mid1 > 0) l1 = nums1[mid1 - 1];
            if (mid2 > 0) l2 = nums2[mid2 - 1];
            if (mid1 < n1) r1 = nums1[mid1];
            if (mid2 < n2) r2 = nums2[mid2];

            if (l1 <= r2 && l2 <= r1) {
                if ((n1 + n2) % 2 == 0)
                    return ((double) (Math.max(l1, l2) + Math.min(r1, r2)) / 2.0);
                else return Math.max(l1, l2);
                // binary search in the left of nums1 if its right most value left to the cursor (l1) still larger than the left most value in nums2 (r2)
            } else if (l1 > r2) {
                high1 = mid1 - 1;
            } else {
                low1 = mid1 + 1;
            }

        }

        // If the code reaches here, the input arrays were not sorted.
        return 0;
    }

    private int lo, maxLen;   //lo is used to track the starting index of the longest palindrome, maxLen is the length of the longest palindrome

    public String longestPalindrome(String s) {
        int n = s.length();
        lo = 0;
        maxLen = 0;

        // return the string if it has no more than 1 character
        if (n <= 1) return s;

        // continue in remaining cases
        // run through all i positions to find the longest palindrome, expanding to both sides of the string from that i position
        for (int i = 0; i < n - 1; i++) {

            // in case of odd length strings, starting points for both directions is i
            expandPalidrome(s, i, i);

            // in case of even length strings, starting points for both directions is i and i+1
            // this is why i only runs to below n-1
            expandPalidrome(s, i, i + 1);
        }

        return s.substring(lo, lo + maxLen);
    }

    // this procedure expands on string st, from the j-th char backwards and from the k-th char forward
    // it only updates values of lo and maxLen if a longer palindrome is found
    private void expandPalidrome(String st, int j, int k) {
        int n = st.length();

        // if extended chars from both sides match
        while (j >= 0 && k < n && st.charAt(j) == st.charAt(k)) {

            // continue to extend
            j--;
            k++;
        }

        // update max palindrome length if exceeds the current
        if (maxLen < k - j - 1) {
            maxLen = k - j - 1;

            // starting point of the current max palindrome is the last j where it was still true
            lo = j + 1;
        }
    }

    // No6 03.04.2024 ZigZagConversion
    public String convert(String s, int numRows) {

        // get the length of whole string
        int n = s.length();

        // initiate the list of empty strings where characters are stored in sorted order
        // there are numRows rows -> there are numRows strings in the list
        List<String> myList = new ArrayList<>(Collections.nCopies(numRows, ""));

        // instantly return if it does not zigzag (only <=1 rows)
        if (numRows <= 1) return s;

        // get the size of space between 2 zigzag columns
        int space = numRows - 2;
        int currRow = 0;

        // run through the string to pick the chars and put them in right places
        for (int i = 0; i < n; i++) {

            // d is the index of s[i] in a block of numRows + space chars.
            int d = i % (numRows + space);

            // get the row index of s[i]
            // if s[i] belong to the columns
            if (d >= 0 && d <= numRows - 1) {
                currRow = d;

                // if s[i] belongs to the diagonals
            } else {
                currRow = numRows + space - d;
            }

            // pull the string out, add s[i], put it back in
            String st = myList.get(currRow) + s.charAt(i);
            myList.set(currRow, st);
        }

        // export the result
        StringBuilder sb = new StringBuilder();

        for (String str : myList) {
            sb.append(str);
        }

        return sb.toString();
    }

    public String convert_2(String s, int numRows) {
        char[] c = s.toCharArray();
        int len = c.length;
        StringBuffer[] sb = new StringBuffer[numRows];
        for (int i = 0; i < sb.length; i++) sb[i] = new StringBuffer();

        int i = 0;
        while (i < len) {
            for (int idx = 0; idx < numRows && i < len; idx++) // vertically down
                sb[idx].append(c[i++]);
            for (int idx = numRows - 2; idx >= 1 && i < len; idx--) // obliquely up
                sb[idx].append(c[i++]);
        }
        for (int idx = 1; idx < sb.length; idx++)
            sb[0].append(sb[idx]);
        return sb[0].toString();
    }

    // 11.04.2024
    public int reverse(int x) {

        // get absolute x, assign it to a replicate number to start dividing
        int rep = Math.abs(x);

        // result variable stored as long to check with integer overflow
        int result = 0;

        // operate as long as there is a non-zero digit in rep
        while (rep > 0) {

            if (result < Integer.MAX_VALUE / 10) {
                // take the last digit of rep and add to the last of result
                // if 0 is added while the result is also 0, it stays at 0 until a non-zero digit is added
                result = result * 10 + rep % 10;

                // remove the last digit from rep to continue with the next digit in the next loop, if any
                rep = rep / 10;

                // marginal cases at Int.Max & Min
            } else if (result == Integer.MAX_VALUE / 10) {

                // if original number > 0 and rep is the last digit & it is not exceeding 7
                if (rep <= 7 && x > 0)
                    return result * 10 + rep;

                    // if the original number < 0 and rep is the last digit & it is not exceeding 8
                else if (rep <= 8 && x < 0) {
                    return -result * 10 - rep;
                }

                // all other cases exceed int limits hence return 0
                else return 0;

                // while there are still digits left and the result already exceeds Int.Limits/10:
            } else return 0;
        }

        // return the result in normal cases. marginal cases have been dealt with above
        if (x < 0) return -result;
        else return result;

    }

    // 11.04.2024
    public int myAtoi(String s) {
        int n = s.length();
        int result = 0;
        boolean signCheck = false;
        boolean isPositive = true;
        boolean isNumber = false;
        for (int i = 0; i < n; i++) {

            // continue to add if the next char is a digit
            if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {

                // turn ON the status of the number accumulation phase
                if (!isNumber && result == 0) isNumber = true;

                // if the result already exceeds Int.Max/Min /10 in the prev step -> adding another digit will exceed limits
                if (result > Integer.MAX_VALUE / 10)
                    if (isPositive) return Integer.MAX_VALUE;
                    else return Integer.MIN_VALUE;

                    // if the result is marginal, crop the last digit to max 7 (if the whole number is positive) or max 8 (vice versa),
                    // then return the result together with its sign
                else if (result == Integer.MAX_VALUE / 10) {
                    if (isPositive) {

                        // if its still not at the last digit
                        if (i + 1 < n && s.charAt(i + 1) >= '0' && s.charAt(i + 1) <= '9') return Integer.MAX_VALUE;

                            // if its already the last digit
                        else
                            return result * 10 + Math.min(7, Character.getNumericValue(s.charAt(i)));

                    } else

                        // if its still not at the last digit
                        if (i + 1 < n && s.charAt(i + 1) >= '0' && s.charAt(i + 1) <= '9') return Integer.MIN_VALUE;

                            // if its already the last digit
                        else
                            return -result * 10 - Math.min(8, Character.getNumericValue(s.charAt(i)));

                    // if we reach this phase no number limit is breached
                } else {
                    result = result * 10 + Character.getNumericValue(s.charAt(i));
                }

                // if the current char is not a digit & the number accumulation phase is ON -> end the loop
            } else if (isNumber) {
                i = n;

                // if we reach this stage, the number counting phase is not ON. record the sign of the number
            } else if (s.charAt(i) == '-' && !signCheck) {
                isPositive = false;
                signCheck = true;
            } else if (s.charAt(i) == '+' && !signCheck) {
                // the sign is + by default
                signCheck = true;

                // if the leading chars (number phase is not ON) are not spaces
            } else if (s.charAt(i) != ' ' || (s.charAt(i) == ' ' && signCheck)) {
                i = n;
            }
        }

        // return the result in normal cases. marginal cases have been dealt with above
        if (isPositive) return result;
        else return -result;
    }

    // 12.04.2024
    public boolean isPalindrome(int x) {
        // instantly false if there is a minus ahead
        if (x < 0) return false;

        // a copy of x to build the replicate
        int original = x;

        // the replicate starting at 0
        int rep = 0;

        // keep taking the last digit of the copy and put to the last of the replicate
        // thus de facto building the replicate from right to left of x
        while (original > 0) {

            // quit if there is still one more digit and the current build threatens to exceed MaxInt
            if (rep > Integer.MAX_VALUE / 10) return false;

                // quit if its right on the brink and the last digit is >2 thus threatens to exceed MaxInt
            else if (rep == Integer.MAX_VALUE / 10 && original > 2) return false;

            // other cases proceed building the replicate as usual
            rep = rep * 10 + original % 10;
            original = original / 10;
        }

        // compare the replicate result with initial x
        if (rep == x) return true;
        else return false;
    }

    // 12.04.2024
    public boolean isMatch_original(String s, String p) {

        // checking from right to left
        int indexS = s.length() - 1;
        int indexP = p.length() - 1;
        char seq;

        while (indexS >= 0 && indexP >= 0) {

            // look for any sequence in the pattern
            if (p.charAt(indexP) == '*') {

                // get the character for the sequence
                seq = p.charAt(indexP - 1);

                // if its an unlimited sequence of any character -> definitely match
                if (seq == '.')
                    return true;

                    // if the character of the sequence doesn't match the next char in s then go to the next sequence/char in p
                else if (s.charAt(indexS) != seq)
                    indexP -= 2;

                    // if it does match, go back in s until it doesn't, then restart the pattern
                else {
                    do {
                        if (s.charAt(indexS) == seq) indexS--;

                        // while the begin of S is still not overreached
                    } while (indexS >= 0 && s.charAt(indexS) == seq);
                    indexP -= 2;
                }
            }

            // in case of single digit matching
            else if (p.charAt(indexP) == '.' || p.charAt(indexP) == s.charAt(indexS)) {
                indexS--;
                indexP--;

                // if single characters don't match then the whole part failed
            } else return false;
        }

        // if s is fully checked return true. the remaining of p if any can be ignored
        if (indexS == -1) {
            if (indexP == -1)
                return true;
            else if (indexP == 1 && p.charAt(indexP) == '*')
                return true;
            else return false;
        } else return false;
    }

    public boolean isMatch(String s, String p) {
        // corner case
        if (s == null || p == null) return false;

        int m = s.length();
        int n = p.length();

        // M[i][j] represents if the 1st i characters in s can match the 1st j characters in p
        boolean[][] M = new boolean[m + 1][n + 1];

        // initialization:
        // 1. M[0][0] = true, since empty string matches empty pattern
        M[0][0] = true;

        // 2. M[i][0] = false(which is default value of the boolean array) since empty pattern cannot match non-empty string
        // 3. M[0][j]: what pattern matches empty string ""? It should be #*#*#*#*..., or (#*)* if allow me to represent regex using regex :P,
        // and for this case we need to check manually:
        // as we can see, the length of pattern should be even && the character at the even position should be *,
        // thus for odd length, M[0][j] = false which is default. So we can just skip the odd position, i.e. j starts from 2, the interval of j is also 2.
        // and notice that the length of repeat sub-pattern #* is only 2, we can just make use of M[0][j - 2] rather than scanning j length each time
        // for checking if it matches #*#*#*#*.
        for (int j = 2; j < n + 1; j += 2) {
            if (p.charAt(j - 1) == '*' && M[0][j - 2]) {
                M[0][j] = true;
            }
        }

        // Induction rule is very similar to edit distance, where we also consider from the end. And it is based on what character in the pattern we meet.
        // 1. if p.charAt(j) == s.charAt(i), M[i][j] = M[i - 1][j - 1]
        //    ######a(i)
        //    ####a(j)
        // 2. if p.charAt(j) == '.', M[i][j] = M[i - 1][j - 1]
        // 	  #######a(i)
        //    ####.(j)
        // 3. if p.charAt(j) == '*':
        //    1. if p.charAt(j - 1) != '.' && p.charAt(j - 1) != s.charAt(i), then b* is counted as empty. M[i][j] = M[i][j - 2]
        //       #####a(i)
        //       ####b*(j)
        //    2.if p.charAt(j - 1) == '.' || p.charAt(j - 1) == s.charAt(i):
        //       ######a(i)
        //       ####.*(j)
        //
        // 	  	 #####a(i)
        //    	 ###a*(j)
        //      2.1 if p.charAt(j - 1) is counted as empty, then M[i][j] = M[i][j - 2]
        //      2.2 if counted as one, then M[i][j] = M[i - 1][j - 2]
        //      2.3 if counted as multiple, then M[i][j] = M[i - 1][j]

        // recap:
        // M[i][j] = M[i - 1][j - 1]
        // M[i][j] = M[i - 1][j - 1]
        // M[i][j] = M[i][j - 2]
        // M[i][j] = M[i][j - 2]
        // M[i][j] = M[i - 1][j - 2]
        // M[i][j] = M[i - 1][j]
        // Observation: from above, we can see to get M[i][j], we need to know previous elements in M, i.e. we need to compute them first.
        // which determines i goes from 1 to m - 1, j goes from 1 to n + 1

        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                char curS = s.charAt(i - 1);
                char curP = p.charAt(j - 1);
                if (curS == curP || curP == '.') {
                    M[i][j] = M[i - 1][j - 1];
                } else if (curP == '*') {
                    char preCurP = p.charAt(j - 2);
                    if (preCurP != '.' && preCurP != curS) {
                        M[i][j] = M[i][j - 2];
                    } else {
                        M[i][j] = (M[i][j - 2] || M[i - 1][j - 2] || M[i - 1][j]);
                    }
                }
            }
        }

        return M[m][n];
    }
}