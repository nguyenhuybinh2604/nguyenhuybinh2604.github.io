
public class Main {
    public static void main(String[] args) {
        BTVN_Buoi7 btvn_buoi7 = new BTVN_Buoi7();
        int[][] myArray1 = {{1,2,3,4},{5,1,2,3},{9,5,1,2}};
        int[] myArray2 = {1};
        System.out.println(btvn_buoi7.isToeplitzMatrix_766(myArray1));
        MyLinkedList linkedList = new MyLinkedList();
        linkedList.addAtHead(2);
        linkedList.addAtHead(1);
        linkedList.addAtTail(3);
        linkedList.addAtIndex(2,4);
        linkedList.deleteAtIndex(2);

    }
}
