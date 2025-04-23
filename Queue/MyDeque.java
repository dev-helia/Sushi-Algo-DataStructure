package Queue;


// ✅ addFront(int val) — 从头插入
// ✅ addRear(int val) — 从尾插入
// ✅ removeFront() — 从头删除
// ✅ removeRear() — 从尾删除
// ✅ peekFront() — 查看队头
// ✅ peekRear() — 查看队尾
// ✅ isEmpty() — 是否为空
// ✅ isFull() — 是否满了

// 📦 底层使用：循环数组
public class MyDeque {
    private int[] arr;
    private int size;
    private int capacity;
    private int front; // 指向队头元素
    private int rear;  // 指向“尾部下一个插入位置”

    public MyDeque(int capacity) {
        this.capacity = capacity;
        arr = new int[capacity];
        size = 0;
        front = 0;
        rear = 0;
    }

    // 从尾部插入元素
    public boolean addRear(int val) {
        if (isFull()) return false;
        arr[rear] = val;
        rear = (rear + 1) % capacity;
        size++;
        return true;
    }

    // 从头部插入元素
    public boolean addFront(int val) {
        if (isFull()) return false;
        front = (front - 1 + capacity) % capacity;
        arr[front] = val;
        size++;
        return true;
    }

    // 从头部删除元素
    public int removeFront() {
        if (isEmpty()) return Integer.MIN_VALUE;
        int val = arr[front];
        front = (front + 1) % capacity;
        size--;
        return val;
    }

    // 从尾部删除元素
    public int removeRear() {
        if (isEmpty()) return Integer.MIN_VALUE;
        rear = (rear - 1 + capacity) % capacity;
        int val = arr[rear];
        size--;
        return val;
    }

    // 查看队头元素
    public int peekFront() {
        if (isEmpty()) return Integer.MIN_VALUE;
        return arr[front];
    }

    // 查看队尾元素
    public int peekRear() {
        if (isEmpty()) return Integer.MIN_VALUE;
        return arr[(rear - 1 + capacity) % capacity];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }
}

