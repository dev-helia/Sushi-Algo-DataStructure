package Queue;

import java.util.Stack;

public class MyQueueUsingStack {
    Stack<Integer> stack1;
    Stack<Integer> stack2;
    // ❎不需要size了
    // int size;

    public MyQueueUsingStack() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
        // int size = 0;
    }

    // 入队：直接压入 stack1
    public void push(int x) {
        stack1.push(x);
    }

    // 出队：先确保 stack2 不是空的，再 pop
    public int pop() {
        shiftStacksIfNeeded();
        return stack2.pop(); // 不能放在 return 后更新 size
    }

    // 查看队头元素
    public int peek() {
        shiftStacksIfNeeded();
        return stack2.peek();
    }

    // 判空：两个都空才是空
    public boolean empty() {
        return stack1.isEmpty() && stack2.isEmpty();
    }

    // 辅助函数：把 stack1 的元素倒进 stack2
    private void shiftStacksIfNeeded() {
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
    }
}
