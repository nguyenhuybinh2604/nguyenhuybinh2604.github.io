import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

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


    public ListNode removeNodes_2487_usingStack(ListNode head) {
        Deque<ListNode> linkedStack = new ArrayDeque<>();
        ListNode tmp = head;
        while (tmp!=null) {
            // pop het cac gia tri be hon tmp.value
            while (!linkedStack.isEmpty() && linkedStack.peek().val<tmp.val) {
                linkedStack.pop();
            }
            // add tmp.val
            ListNode tmp2 = tmp.next;
            tmp.next = linkedStack.peek();
            linkedStack.push(tmp);
            tmp = tmp2;
        }
        return linkedStack.peek();
    }


}
