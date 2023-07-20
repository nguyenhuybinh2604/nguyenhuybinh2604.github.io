// https://leetcode.com/problems/design-circular-deque/submissions/997749144/
public class MyCircularDeque {
    // deque 2 dau theo doi bang front & last
    Integer[] deque;
    int size;
    int front;
    int last;

    public MyCircularDeque(int k) {
        // max size cua deque = k
        deque = new Integer[k];
        // khoi tao cac bien ban dau =0;
        size = 0;
        front = 0;
        last = 0;
    }

    public boolean insertFront(int value) {
        // neu con trong thi insert
        if (!this.isFull()) {
            // neu front dang o 0 thi khi lui ve quay vong lai length - 1;
            if (front == 0) {
                front = deque.length - 1;
                deque[front] = value;
                // neu khong thi lui 1 buoc nhu bt
            } else deque[--front] = value;
            size++;
            // truong hop la element dau tien thi 2 dau bang nhau
            if (size == 1) last = front;
            return true;
        } else return false;
    }

    public boolean insertLast(int value) {
        // neu con trong thi insert
        if (!this.isFull()) {
            // neu last = cuoi deque thi quay vong lai 0
            if (last == deque.length - 1) {
                last = 0;
                deque[last] = value;
                // neu khong thi cong 1 buoc nhu bt
            } else deque[++last] = value;
            size++;
            // truong hop la element dau tien thi 2 dau bang nhau
            if (size == 1) front = last;
            return true;
        } else return false;
    }

    public boolean deleteFront() {
        // chi xoa neu con element
        if (!this.isEmpty()) {
            // neu front o cuoi thi tien len 1 buoc = quay ve 0
            if (front == deque.length - 1) front = 0;
            else front++;
            size--;
            if (size == 0) last = front;
            return true;
        } else return false;
    }

    public boolean deleteLast() {
        // chi xoa neu con element
        if (!this.isEmpty()) {
            // neu last o dau thi lui 1 buoc quay ve length - 1;
            if (last == 0) last = deque.length - 1;
            else last--;
            size--;
            if (size == 0) front = last;
            return true;
        } else return false;
    }

    public int getFront() {
        if (!this.isEmpty()) return deque[front];
        else return -1;
    }

    public int getRear() {
        if (!this.isEmpty()) return deque[last];
        else return -1;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public boolean isFull() {
        return this.size == deque.length;
    }

}
