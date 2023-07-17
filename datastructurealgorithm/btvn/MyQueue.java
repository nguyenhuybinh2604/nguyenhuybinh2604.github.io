import java.util.ArrayDeque;
import java.util.Deque;
// Cach dung array:
// https://leetcode.com/problems/implement-queue-using-stacks/submissions/995076419/

// Cach dung stack:
// https://leetcode.com/problems/implement-queue-using-stacks/submissions/995083767/

public class MyQueue {
    // dung 2 stack de rot cac element trong queue qua lai
    Deque<Integer> stack1;
    Deque<Integer> stack2;

    public MyQueue() {
        this.stack1 = new ArrayDeque<>();
        this.stack2 = new ArrayDeque<>();
    }

    public void push(int x) {
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }
        stack2.push(x);
        while (!stack2.isEmpty()) {
            stack1.push(stack2.pop());
        }

    }

    public int pop() {
        return stack1.pop();
    }

    public int peek() {
        return stack1.peek();
    }

    public boolean empty() {
        return stack1.isEmpty();
    }

}
