import java.util.ArrayDeque;
import java.util.Deque;

// https://leetcode.com/problems/number-of-recent-calls/submissions/996052504/
public class RecentCounter {
    // do khoang thoi gian ping gioi han trong [t-3000;t] nen dung queue de quan ly moc thoi gian
    Deque<Integer> queue;

    public RecentCounter() {
        this.queue = new ArrayDeque<>();
    }

    public int ping(int t) {
        // chi giu lai cac element co moc thoi gian trong t-3000
        while (!queue.isEmpty() && queue.peek() < t - 3000) {
            queue.remove();
        }
        // them element duoc tao tai thoi diem t
        queue.add(t);
        // tra ket qua la tong so element
        return queue.size();
    }

}
