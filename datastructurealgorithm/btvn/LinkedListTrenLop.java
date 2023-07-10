public class LinkedListTrenLop {
    class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode reverseList(ListNode head) {
        ListNode reverse = null;
        while (head.next != null) {
            ListNode temp = head;
            head = head.next;
            temp.next = reverse;
            reverse = temp;
        }
        return reverse;
    }
}
