import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class FromNowOn {
    // 2
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode tmpA = l1;
        ListNode tmpB = l2;
        ListNode tmpNode;

        // tao node head de giu cho tra kq ve sau
        ListNode head = new ListNode(0);
        tmpNode = head;

        // so nho khi cong 2 chu so
        int addedVal = 0;


        int valA, valB, valSum;

        // duyet cho den khi ve het chuoi hoac het so nho
        while (tmpA != null || tmpB != null || addedVal != 0) {

            // lay node tiep theo o list 1
            if (tmpA != null) {

                // lay value cua node va day sang node tiep theo
                valA = tmpA.val;
                tmpA = tmpA.next;

                // neu node khong ton tai => tai diem do value node = 0
            } else valA = 0;

            // lay node tiep theo o list 2
            if (tmpB != null) {

                // lay value cua node va day sang node tiep theo
                valB = tmpB.val;
                tmpB = tmpB.next;

                // neu node khong ton tai => tai diem do value node = 0
            } else valB = 0;

            // cong 2 chu so va so nho, ket qua lay so don vi ghep vao node tiep theo (chia lay du cho 10)
            valSum = (valA + valB + addedVal) % 10;

            // phan chia lay nguyen cho 10 (sau khi cong so nho) -> lam so nho cho phep cong tiep theo
            addedVal = (valA + valB + addedVal) / 10;

            // tao node moi lap vao sau node hien hanh
            tmpNode.next = new ListNode(valSum);

            // day node tmp sang node moi
            tmpNode = tmpNode.next;
        }

        // tra ket qua la head next
        return head.next;
    }

    // 3
    public int lengthOfLongestSubstring(String s) {

        // bien dem do dai va luu do dai max
        int maxLen = 0, currLen = 0;
        int lastIndex = -1;

        // hashset de theo doi ky tu co lap lai khong
        HashMap<Character, Integer> charMap = new HashMap<>();

        // duyet het 1 vong string
        for (int i = 0; i < s.length(); i++) {

            // neu chua gap char do trong set, hoac char do da xuat hien nhung thuoc substring truoc
            // -> cong 1 do dai va add vao set de theo doi
            if (!charMap.containsKey(s.charAt(i)) || charMap.get(s.charAt(i)) <= lastIndex) {
                currLen++;

                // neu gap lai 1 char -> capture maxLen va reset counter
            } else {
                maxLen = Math.max(maxLen, currLen);

                // tinh lai diem bat dau unique substring tu vi tri char trung lap
                lastIndex = s.substring(0, i).lastIndexOf(s.charAt(i));
                currLen = i - lastIndex;
            }

            //update vi tri char moi de theo doi
            charMap.put(s.charAt(i), i);
        }
        return Math.max(maxLen, currLen);
    }

    // 4
//    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
//
//    }

}
