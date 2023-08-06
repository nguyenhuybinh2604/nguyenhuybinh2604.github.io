public class BTVN_Buoi8_LinkedList {

    // https://leetcode.com/problems/reverse-linked-list/submissions/993678904/
    public ListNode reverseList_206(ListNode head) {
        // luu node di theo sau de chuyen ket noi
        ListNode tempPrev = null;
        // node hien hanh
        ListNode temp = head;
        while (head != null) {
            // luu tam node dau
            temp = head;
            // day node dau ra sau
            head = head.next;
            // link node tiep theo tu node hien tai ve node prev
            temp.next = tempPrev;
            // day node prev len de link o lan tiep theo
            tempPrev = temp;
        }
        return temp;
    }

    // https://leetcode.com/problems/remove-linked-list-elements/submissions/994435051/
    public ListNode removeElements_203(ListNode head, int val) {
        // 1 node prev de giu diem ngat cua list
        ListNode prev = null;
        // node temp chay tu dau den cuoi list, bat dau bang head
        ListNode temp = head;
        // duyet list cho den khi gap null
        while (temp != null) {
            // neu tim thay node dung dieu kien xoa
            if (temp.val == val) {
                // neu node do trung voi head => xoa bo head, day ca temp va head len truoc 1 node
                if (temp == head) {
                    head = head.next;
                    temp = head;
                } else {
                    // neu dap ung dk xoa nhung khong phai la head: bo qua node temp, noi tu prev sang temp.next
                    temp = temp.next;
                    prev.next = temp;
                }
                // neu node khong dung dieu kien xoa, bo qua, dich chuyen ca temp va prev len truoc 1 node
            } else {
                prev = temp;
                temp = temp.next;
            }
        }
        return head;
    }

    // https://leetcode.com/problems/merge-two-sorted-lists/submissions/994790456/
    public ListNode mergeTwoLists_21(ListNode list1, ListNode list2) {
        // node origin de luu lai goc cua chuoi
        ListNode org = new ListNode();
        // 2 node temp de duyet qua 2 chuoi
        ListNode tmp1 = list1;
        ListNode tmp2 = list2;
        // node temp de noi chuoi result
        ListNode tmp = org;
        // neu ca 2 chuoi deu con phan tu,
        while (tmp1 != null && tmp2 != null) {
            // so sanh gia tri cua tmp1 & tmp2,
            if (tmp1.val > tmp2.val) {
                // lay gia tri nho hon noi vao tmp
                tmp.next = tmp2;
                // day ca tmp va tmp2 len 1 node
                tmp = tmp2;
                tmp2 = tmp2.next;
                // so sanh gia tri cua tmp1 & tmp2,
            } else {
                // lay gia tri nho hon noi vao tmp
                tmp.next = tmp1;
                // day ca tmp va tmp1 len 1 node
                tmp = tmp1;
                tmp1 = tmp1.next;
            }
        }
        // neu duyet xong ma 1 trong 2 chuoi chua ket thuc (tmp!=null) -> duyet not chuoi do
        while (tmp1 != null) {
            tmp.next = tmp1;
            tmp = tmp1;
            tmp1 = tmp1.next;
        }
        while (tmp2 != null) {
            tmp.next = tmp2;
            tmp = tmp2;
            tmp2 = tmp2.next;
        }
        // tra ve head chinh la node next cua goc
        return org.next;
    }

    // https://leetcode.com/problems/delete-node-in-a-linked-list/submissions/994801396/
    public void deleteNode_237(ListNode node) {
        // khong xoa node ma thay the bang value cua node tiep theo,
        node.val = node.next.val;
        //xoa node tiep theo
        node.next = node.next.next;
    }

    //https://leetcode.com/problems/remove-nodes-from-linked-list/submissions/994847829/
    public ListNode removeNodes_2487(ListNode head) {
        // Dao nguoc chuoi tu cuoi len dau
        ListNode tail = reverseList_206(head);

        // chuoi dao nguoc phai co thu tu tang dan, duyet va xoa cac node nam ngoai quy tac nay
        // bien minVal de luu gia tri cua node lon nhat tung trai qua
        int minVal = tail.val;
        ListNode temp = tail;
        // khi duyet list, neu gap node be hon minVal -> loai bo
        while (temp.next != null) {
            if (temp.next.val < minVal) {
                temp.next = temp.next.next;
                // neu gap node lon hon hoac bang -> chuyen temp sang node do
            } else {
                temp = temp.next;
                // update minVal;
                minVal = temp.val;
            }
        }
        // Dao nguoc list ket qua 1 lan nua de tra ve thu tu ban dau
        head = reverseList_206(tail);
        return  head;
    }

}
