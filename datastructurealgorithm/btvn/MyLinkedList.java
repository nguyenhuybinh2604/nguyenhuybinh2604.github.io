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
public class MyLinkedList {

    class Node {
        int value;
        Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    Node head;
    int size;

    public MyLinkedList() {
        head = null;
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
    }

    public int get(int index) {
        if (index < 0 || index >= size) {
            return -1;
        }

        Node temp = head;
        while (index > 0) {
            temp = temp.next;
            index--;
        }
        return temp.value;
    }

    public void addAtHead(int val) {
        size++;
        Node newNode = new Node(val);

        newNode.next = head;
        head = newNode;
    }

    public void addAtTail(int val) {
        if (head == null) {
            addAtHead(val);
            return;
        }

        size++;
        Node newNode = new Node(val);

        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newNode;
    }

    public void addAtIndex(int index, int val) {
        if (index > size) {
            return;
        }

        if (index == 0) {
            addAtHead(val);
            return;
        }
        if (index == size) {
            addAtTail(val);
            return;
        }

        size++;
        Node newNode = new Node(val);
        Node temp = head;
        while (index > 1) {
            temp = temp.next;
            index--;
        }
        newNode.next = temp.next;
        temp.next = newNode;
    }

    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) {
            return;
        }

        size--;
        if (index == 0) {
            head = head.next;
            return;
        }

        Node temp = head;
        while (index > 1) {
            temp = temp.next;
            index--;
        }

        temp.next = temp.next.next;
    }
}

