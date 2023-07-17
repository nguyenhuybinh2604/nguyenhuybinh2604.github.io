// https://leetcode.com/problems/implement-stack-using-queues/submissions/995065131/
public class MyStack {
    // stack xay tren nen 1 array xac dinh san size toi da
    // bien top dung de track diem tren cung cua stack
    int top;
    int[] stackArray;

    public MyStack() {
        this.top = -1;
        stackArray = new int[101];
    }

    public void push(int x) {
        this.stackArray[++this.top] = x;
    }

    public int pop() {
        return stackArray[top--];
    }

    public int top() {
        return stackArray[top];
    }

    public boolean empty() {
        return top == -1;
    }
}
