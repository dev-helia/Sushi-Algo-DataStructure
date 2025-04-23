package Queue;

/**
 * 用循环数组实现
 * 🌼 小建议（⭐️可选加强）： 返回 -999 这种 magic number 比较危险，容易和真
 * 数据混淆。可以考虑改为 Integer.MIN_VALUE、抛异常、或改返回值类型为 Integer
 * （允许 null）更严谨。
 */
public class MyQueue {
    private int[] arr;
    private int front;
    private int rear;
    private int size;
    private int capacity;

    public MyQueue(int capacity) {
        this.capacity = capacity;
        arr = new int[capacity];
        front = 0;
        rear = 0;
        size = 0;
    }

    public boolean enqueue(int val) {
        if (size == capacity)
            return false;
        arr[rear] = val;
        rear = (rear + 1) % capacity;
        size++;
        return true;
    }

    public int dequeue() {
        if (size == 0)
            return -999;
        int res = arr[front];
        front = (front + 1) % capacity;
        size--;
        return res;
    }

    public int peek() {
        if (size == 0)
            return -999;
        return arr[front];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

}
