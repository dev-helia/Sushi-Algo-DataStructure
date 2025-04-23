package Stack;

import java.util.*;

// 把 q1 全部搬进 q2

// 所以 q2.peek() 永远是 q1 里原来最早的元素 =❌不符合 stack 的“后进先出”
class MyStack {
    // Queue<Integer> q1;
    // Queue<Integer> q2;
    // int size;
    Queue<Integer> q;

    public MyStack() {
        // // data
        // q1 = new LinkedList<>();
        // // stack
        // q2 = new LinkedList<>();
        q = new LinkedList<>();
        // size = 0;
    }
    
    public void push(int x) {
        // q1.add(x);
        // size++;
        q.add(x);
        int n = q.size();
        while (n-- > 1) {
            q.add(q.poll()); // 把前面旧的都转到后面去
        }
    }
    
    public int pop() {
        // shift();
        // size--;
        // return q2.poll();
        return q.poll();
    }
    
    public int top() {
        // shift();
        // return q2.peek();
        return q.peek();
    }
    
    public boolean empty() {
        // return size == 0;
        return q.isEmpty();
    }
    
    // private void shift(){
    //     while (!q1.isEmpty()) {
    //         q2.add(q1.poll());
    //     }
    // }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */