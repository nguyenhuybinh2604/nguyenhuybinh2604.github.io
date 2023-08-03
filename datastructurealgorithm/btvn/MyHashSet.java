import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// https://leetcode.com/problems/design-hashset/submissions/1009121038/
public class MyHashSet {

    // time excess
//    int size = 20007;
//    ListNode[] hashArray;
//
//    class ListNode {
//        int key;
//        MyHashSet.ListNode next;
//
//        public ListNode(int key, MyHashSet.ListNode next) {
//            this.key = key;
//            // cac node co cung hashvalue duoc luu vao chung 1 linkedList co head la hashArray[hashValue]
//            this.next = next;
//        }
//    }
//
//    // convert key thanh hash value
//    private int hash(int key) {
//        return key % size;
//    }
//
//    public MyHashSet() {
//        hashArray = new ListNode[size];
//    }
//
//    public void add(int key) {
//        int hash = hash(key);
//        // neu chua co thi add new node len dau
//        if (!this.contains(key)) {
//            ListNode newNode = new ListNode(key, hashArray[hash]);
//            hashArray[hash] = newNode;
//        }
//    }
//
//    public void remove(int key) {
//        int hash = hash(key);
//        ListNode tmp = hashArray[hash];
//        // neu tai hash chua co object -> tra luon ve null
//        if (tmp == null) return;
//        // neu node dau tien chua key -> tra luon
//        if (tmp.key == key)
//            hashArray[hash] = tmp.next;
//        else
//            // neu node dau tien khong chua key -> tim kiem va return ngay khi tim thay
//            while (tmp.next != null)
//                if (tmp.next.key == key) {
//                    tmp.next = tmp.next.next;
//                    return;
//                }
//        // neu duyet het ma khong return -> khong co node chua key do
//    }
//
//    public boolean contains(int key) {
//        int hash = hash(key);
//        ListNode tmp = hashArray[hash];
//        // duyet tai hash den khi tim thay key, neu co tra ve true
//        while (tmp != null) {
//            if (tmp.key == key) return true;
//            tmp = tmp.next;
//        }
//        // neu duyet het va khong tim thay -> tra ve false
//        return false;
//    }


    //    leetcode example
    // https://leetcode.com/problems/design-hashset/submissions/1009115906/
    private int size;
    // luu tru hashset tren 1 list chua cac linkedList
    private List<List<Integer>> hashList;

    public MyHashSet() {
        size = 1000;
        hashList = new ArrayList<>(size);
        // khoi tao empty hashList
        for (int i = 0; i < size; i++) {
            hashList.add(new LinkedList<>());
        }
    }

    private int hash(int key) {
        return key % size;
    }

    public void add(int key) {
        // tinh gia tri hash
        int index = hash(key);
        List<Integer> bucket = hashList.get(index);
        // neu khong tim thay key -> add moi
        if (!bucket.contains(key)) {
            bucket.add(key);
        }
    }

    public void remove(int key) {
        // tinh gia tri hash
        int index = hash(key);
        // lay list cac node co cung hash value
        List<Integer> bucket = hashList.get(index);
        // xoa key tu list
        bucket.remove(Integer.valueOf(key));
    }

    public boolean contains(int key) {
        int index = hash(key);
        List<Integer> bucket = hashList.get(index);
        // tra ket qua kiem tra list co chua key khong
        return bucket.contains(key);
    }

}
