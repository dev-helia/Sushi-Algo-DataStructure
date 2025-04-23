package Stack;

public class MyStack_arr {
    // ❎ 你把 rear 当成“栈顶的格子”，但它其实是“下一个可插入的位置”！
    private int size;
    private int[] arr;
    private int rear;
    private int capacity;

    public MyStack_arr(int capacity) {
        size = 0;
        this.capacity = capacity;
        arr = new int[capacity];
        rear = 0;
    }

    public void push(int x) {
        if (isFull()) {
            return;
        }
        arr[rear] = x;
        rear++;
        size++;
    }

    public int pop() {
        if (isEmpty())
            return Integer.MIN_VALUE;
        size--;
        rear--;
        return arr[rear];
    }

    public int peek() {
        return arr[rear - 1];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public int size() {
        return size;
    }
}
