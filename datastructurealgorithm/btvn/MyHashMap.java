//https://leetcode.com/problems/design-hashmap/submissions/1005268253/

public class MyHashMap {
//    // dung array de luu hashmap
//    Integer[] hashArray;
//
//    public MyHashMap() {
//        // key tu 0->10^6 -> 10^6 + 1 phan tu
//        this.hashArray = new Integer[1000001];
//    }
//
//    // ghi de gia tri moi
//    public void put(int key, int value) {
//        this.hashArray[key] = value;
//    }
//
//    // neu phan tu = null -> chua duoc gan key
//    public int get(int key) {
//        if (this.hashArray[key] != null) return this.hashArray[key];
//        else return -1;
//    }
//
//    public void remove(int key) {
//        if (this.hashArray[key] != null) this.hashArray[key] = null;
//    }

    // cach 2 - dung hash
    class ListNode {
        int key, val;
        ListNode next;
        public ListNode(int key, int val, ListNode next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }



}
