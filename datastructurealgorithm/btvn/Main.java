
public class Main {
    public static void main(String[] args) {
        MyLinkedList linkedList = new MyLinkedList();
        ListNode head = new ListNode(5);
        ListNode node1 = new ListNode(2);
        head.next = node1;
        ListNode node2 = new ListNode(13);
        node1.next = node2;
        ListNode node3 = new ListNode(3);
        node2.next = node3;
        ListNode node4 = new ListNode(8);
        node3.next = node4;
        BTVN_Buoi8 btvn_buoi8 = new BTVN_Buoi8();
        System.out.println(btvn_buoi8.removeNodes_2487(head).val);
    }
}

//5,2,13,3,8