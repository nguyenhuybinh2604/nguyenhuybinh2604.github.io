import java.util.LinkedList;

// https://leetcode.com/problems/design-circular-queue/submissions/996370892/
public class MyCircularQueue {
    // thiet ke kieu array quay vong, gom do lon, diem dau va diem cuoi
    Integer[] circularQueue;
    int size;
    int first;
    int last;

    public MyCircularQueue(int k) {
        // k la do lon toi da cua queue, 0 <= size <= k
        this.circularQueue = new Integer[k];
        this.size = 0;
        this.first = 0;
        this.last = 0;
    }

    public boolean enQueue(int value) {
        // chi add neu queue chua full
        if (!this.isFull()) {
            // khi add moi, neu diem cuoi queue dang roi vao diem cuoi cua array -> quay ve dau array
            if (last == circularQueue.length - 1) last = 0;
            else last++;
            this.circularQueue[last] = value;
            // neu add element dau tien: diem dau cung la diem cuoi
            if (size == 0) first = last;
            this.size++;
            return true;
        } else return false;
    }

    public boolean deQueue() {
        // chi xoa neu queue co du lieu
        if (!this.isEmpty()) {
            // khi xoa neu diem dau queue trung voi diem cuoi cua array -> quay ve dau array
            if (first == circularQueue.length - 1) first = 0;
            else first++;
            this.size--;
            // neu xoa phan tu cuoi cung -> diem cuoi cung la diem dau
            if (size == 0) last = first;
            return true;
        } else return false;
    }

    public int Front() {
        if (!this.isEmpty()) {
            return circularQueue[first];
        } else return -1;
    }

    public int Rear() {
        if (!this.isEmpty()) {
            return circularQueue[last];
        } else return -1;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public boolean isFull() {
        return this.size == this.circularQueue.length;
    }

}
