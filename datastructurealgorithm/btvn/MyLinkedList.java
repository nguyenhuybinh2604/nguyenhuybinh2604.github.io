import java.util.*;

//Design your implementation of the linked list. You can choose to use a singly or doubly linked list.
//        A node in a singly linked list should have two attributes: val and next. val is the value of the current node,
//        and next is a pointer/reference to the next node.
//        If you want to use the doubly linked list, you will need one more attribute prev to indicate the previous node
//        in the linked list. Assume all nodes in the linked list are 0-indexed.
//
//        Implement the MyLinkedList class:
//
//        MyLinkedList() Initializes the MyLinkedList object.
//        int get(int index) Get the value of the indexth node in the linked list. If the index is invalid, return -1.
//        void addAtHead(int val) Add a node of value val before the first element of the linked list.
//        After the insertion, the new node will be the first node of the linked list.
//        void addAtTail(int val) Append a node of value val as the last element of the linked list.
//        void addAtIndex(int index, int val) Add a node of value val before the indexth node in the linked list.
//        If index equals the length of the linked list, the node will be appended to the end of the linked list.
//        If index is greater than the length, the node will not be inserted.
//        void deleteAtIndex(int index) Delete the indexth node in the linked list, if the index is valid.

//https://leetcode.com/problems/design-linked-list/submissions/993383347/
public class MyLinkedList {
    // linked list 2 chieu -> tang toc do tinh toan vi co the iterate tu dau gan node hon
    class Node {
        // Moi node co gia tri, node o truoc va o sau
        int value;
        Node prev;
        Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    // mot list co ca node dau va duoi, co the trung nhau
    Node head;
    Node tail;
    int size;

    public MyLinkedList() {
        // khoi tao voi node dau va cuoi = null
        head = null;
        tail = null;
        size = 0;
    }

    public void showList() {
        int idx = 0;
        Node temp = head;
        while (temp != null) {
            System.out.println(idx + " : " + temp.value);
            temp = temp.next;
            idx++;
        }
        System.out.println();
    }

    public int get(int index) {
        if (index < 0 || index >= size) {
            return -1;
        }
        // neu gan o dau hon thi chay tu dau
        if (index < size - index) {
            Node temp = head;
            while (index > 0) {
                temp = temp.next;
                index--;
            }
            return temp.value;
            // neu gan o duoi hon thi chay tu duoi
        } else {
            Node temp = tail;
            int revIdx = size - 1;
            while (revIdx > index) {
                temp = temp.prev;
                revIdx--;
            }
            return temp.value;
        }
    }

    public void addAtHead(int val) {
        Node newNode = new Node(val);
        // tang size cua list
        size++;
        // chap node tiep theo vao node dau tien
        newNode.next = head;
        // dich chuyen head sang node moi
        head = newNode;
        // truong hop moi add node dau tien cua list thi dong thoi lay lam tail
        if (size == 1) tail = newNode;
        // gan link chieu nguoc lai
        if (newNode.next != null) newNode.next.prev = newNode;
    }

    public void addAtTail(int val) {
        Node newNode = new Node(val);
        // tang size cua list
        size++;
        // chap node moi vao sau duoi
        newNode.prev = tail;
        // dich chuyen duoi ve node moi
        tail = newNode;
        // neu la node dau tien cua list thi dong thoi lay lam head
        if (size == 1) head = newNode;
        // gan link chieu nguoc lai
        if (newNode.prev != null) newNode.prev.next = newNode;
    }

    public void addAtIndex(int index, int val) {
        // neu idx ngoai range thi chi return
        if (index < 0 || index > size) {
            return;
        }

        // add head neu dung o dau list
        if (index == 0) {
            addAtHead(val);
            return;
        }

        // neu index = length thi add vao cuoi
        if (index == size) {
            addAtTail(val);
            return;
        }

        // cac truong hop idx con lai
        Node newNode = new Node(val);

        // tim tu dau neu idx gan head hon gan tail
        if (index < size - index) {
            Node temp = head;
            while (index > 1) {
                temp = temp.next;
                index--;
            }
            // chen newNode giua temp va temp.next, bao gom link ca 2 chieu
            newNode.next = temp.next;
            newNode.prev = temp;
            temp.next = newNode;
            newNode.next.prev = newNode;

            // nguoc lai di tu duoi len neu idx gan tail hon gan head
        } else {
            Node temp = tail;
            int revIdx = size - 1;
            while (revIdx > index) {
                temp = temp.prev;
                revIdx--;
            }
            // chen newNode giua temp va temp.prev, bao gom link ca 2 chieu
            newNode.next = temp;
            newNode.prev = temp.prev;
            temp.prev = newNode;
            newNode.prev.next = newNode;
        }
        // sau khi add xong thi tang size (khong bao gom cac lan add
        size++;
    }

    public void deleteAtIndex(int index) {
        // neu ngoai range thi chi return
        if (index < 0 || index >= size) {
            return;
        }

// neu o dau thi xoa head
        if (index == 0) {
            head = head.next;
            // neu la element duy nhat thi xoa ca tail
            if (size == 1) {
                tail = null;
                // neu khong thi xoa prev link cua head moi
            } else head.prev = null;
            //giam size
            size--;
            return;
        }
// neu o cuoi thi xoa tail
        if (index == size - 1) {
            tail = tail.prev;
            tail.next = null;
            // khong can lap lai nhu tren vi da xay ra tai truong hop index = 0
            size--;
            return;
        }

        // cac truong hop con lai
        // neu gan voi head hon -> chay tu dau
        if (index < size - index) {
            Node temp = head;
            while (index > 1) {
                temp = temp.next;
                index--;
            }
            // link ca 2 chieu, bo qua node can xoa (temp.next)
            temp.next = temp.next.next;
            temp.next.prev = temp;
// neu gan voi tail hon -> chay tu cuoi len
        } else {
            Node temp = tail;
            int revIdx = size - 1;
            while (revIdx > index) {
                temp = temp.prev;
                revIdx--;
            }
            // link ca 2 dau, bo qua node can xoa (temp)
            temp.next.prev = temp.prev;
            temp.prev.next = temp.next;
        }
        // giam size
        size--;
    }
}

