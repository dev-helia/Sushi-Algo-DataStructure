package Heap;

public class MyMinHeap {

    private int[] arr;      // 用来存储堆元素的数组
    private int size;       // 当前堆中元素个数
    private int capacity;   // 堆的容量

    public MyMinHeap(int capacity) {
        this.capacity = capacity;
        arr = new int[capacity];
        size = 0;
    }

    /**
     * 插入一个新元素到堆中，保证堆序性质
     */
    public void offer(int val) {
        if (size == capacity) {
            // 如果满了，可以选择扩容或者抛异常
            return; // 这里简单直接返回，不处理满的情况
        }
        // 将新元素放到数组末尾
        arr[size] = val;
        // 对新元素进行上浮操作，维护小顶堆性质
        siftUp(size);
        size++;
    }

    /**
     * 返回堆顶元素（最小值），但不移除它
     */
    public int peek() {
        // 🟧判空
        if (size == 0) {
            return Integer.MIN_VALUE; // 或者抛出异常
        }
        // 🟧 什么意思 最小值是arr[0]?
        return arr[0];
    }

    /**
     * 移除并返回堆顶元素（最小值）
     * 🟧 不会
     */
    public int poll() {
        // 🟧判空
        if (size == 0) {
            return Integer.MIN_VALUE; // 或者抛出异常
        }
        int min = arr[0]; // 🧡 第一步：先保存“堆顶元素”，这是我们要 return 的！

        arr[0] = arr[size - 1]; // 🧡 第二步：把数组最后那个元素搬到堆顶来
        size--;
        siftDown(0); // 🧡 第三步：开始从堆顶向下“下沉”新元素
        return min;
    }

    /**
     * 从 index 位置开始上浮，直到堆序满足
     */
    private void siftUp(int index) {
        while (index > 0) {
            int parent = (index - 1) / 2;  // 父节点索引
            if (arr[index] < arr[parent]) {
                swap(index, parent);
                index = parent;
            } else {
                break;
            }
        }
    }

    /**
     * 从 index 位置开始下沉，直到堆序满足
     */
    private void siftDown(int index) {
        while (true) {
            int left = 2 * index + 1;      // 左子节点索引
            int right = 2 * index + 2;     // 右子节点索引
            int smallest = index;
            // 找出三个节点中最小的
            if (left < size && arr[left] < arr[smallest]) {
                smallest = left;
            }
            if (right < size && arr[right] < arr[smallest]) {
                smallest = right;
            }
            // 如果当前节点比左右孩子都小，则堆序满足
            if (smallest == index) {
                break;
            }
            // 否则交换并继续下沉
            swap(index, smallest);
            index = smallest;
        }
    }

    /**
     * 交换数组中两个索引位置的元素
     */
    private void swap(int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    /**
     * 返回当前堆中的元素个数
     */
    public int size() {
        return size;
    }
}
