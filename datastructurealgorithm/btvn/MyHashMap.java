// su dung nguyen 1 array
//https://leetcode.com/problems/design-hashmap/submissions/1005268253/

// su dung array + hash
// https://leetcode.com/problems/design-hashmap/submissions/1008092292/

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
    // 10^4 lan query x2, +7 -> so nguyen to
    int size = 20007;
    ListNode[] hashArray;

    class ListNode {
        int key, val;
        ListNode next;

        public ListNode(int key, int val, ListNode next) {
            this.key = key;
            this.val = val;
            // cac node co cung hashvalue duoc luu vao chung 1 linkedList co head la hashArray[hashValue]
            this.next = next;
        }
    }

    public MyHashMap() {
        // khoi tao array luu cac gia tri cua hashmap
        hashArray = new ListNode[size];
    }

    // ham hash: chia lay du cho array size -> tra ra index tren array
    private int hash(int key) {
        return key % size;
    }

    public void put(int key, int val) {
        // xoa neu co truoc khi add
        remove(key);
        // tinh gia tri hash
        int hash = hash(key);
        // tao node moi de vao dau linkedList
        ListNode newNode = new ListNode(key,val,hashArray[hash]);
        hashArray[hash] = newNode;
    }

    public int get(int key) {
        // tinh gia tri hash
        int hash = hash(key);
        // lay head cua linkedList tai hash
        ListNode tmp = hashArray[hash];
        // tim den khi gap node != null & match key
        while (tmp != null && tmp.key != key) tmp = tmp.next;
        if (tmp != null) return tmp.val;
        // neu chay het ma chua return -> khong tim thay -> return -1
        else return -1;
    }

    public void remove(int key) {
        // tinh gia tri hash
        int hash = hash(key);
        // lay head cua linkedList tai hash
        ListNode tmp = hashArray[hash];
        // neu khong ton tai head -> khong thuc hien gi va return
        if (tmp == null) return;
        // neu head dung bang gia tri can xoa -> doi head thanh node tiep theo
        if (tmp.key == key) hashArray[hash] = tmp.next;
        else {
            // neu khong thi di tim key o cac node tiep theo
            while (tmp.next != null) {
                if (tmp.next.key == key) {
                    // neu tim duoc thi link list bo qua tmp.next (xoa tmp.next) va return
                    tmp.next = tmp.next.next;
                    return;
                }
                // neu chua tim duoc thi duyet tiep node sau, den khi 1/ tim duoc hoac 2/ duyet het list
                tmp = tmp.next;
            }
        }
    }

}
