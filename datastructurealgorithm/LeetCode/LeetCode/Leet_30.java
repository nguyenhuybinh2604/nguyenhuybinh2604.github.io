package LeetCode;

import CustomClass.ListNode;

public class Leet_30 {

    // No23: 27.04.2024
    public ListNode mergeKLists(ListNode[] lists) {
        return mergeListRecursive(lists, 0, lists.length - 1);
    }

    private ListNode mergeListRecursive(ListNode[] lists, int left, int right) {
        // marginal cases
        if (right < left) return null;
        if (right == left) return lists[right];

        // divide the list in 2 parts and get the sorted combined node from each part
        int mid = (right + left) / 2;
        ListNode leftNode = mergeListRecursive(lists, left, mid);
        ListNode rightNode = mergeListRecursive(lists, mid + 1, right);

        ListNode dummyHead = new ListNode(0);
        ListNode currNode = dummyHead;

        // iterate through both lists and take the node with smaller value
        while (leftNode != null && rightNode != null) {
            if (leftNode.val < rightNode.val) {
                currNode.next = leftNode;
                leftNode = leftNode.next;
            } else {
                currNode.next = rightNode;
                rightNode = rightNode.next;
            }
            currNode = currNode.next;
        }

        // add the remainingn non-null chain to the combined sorted chain
        if (leftNode == null) currNode.next = rightNode;
        else currNode.next = leftNode;

        // return the head of the sorted chain, except the dummy head
        return dummyHead.next;
    }

    // No24: 27.04.2024
    public ListNode swapPairs(ListNode head) {
        // return when encountering null node or single node at the end
        if ((head == null) || (head.next == null))
            return head;

        // if not the cases above, it means there are 2 consecutive nodes. perform swap.
        // anchor the 2nd node
        ListNode n = head.next;

        // relink the 1st node to the 3rd
        head.next = swapPairs(head.next.next);

        // swap 2nd node to 1st by relink
        n.next = head;

        // return the new 1st node
        return n;
    }

    // No25: 27.04.2024
    public ListNode reverseKGroup(ListNode head, int k) {
        return null;
    }
}
