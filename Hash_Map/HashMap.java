package Hash_Map;

/**
 * 基础版单键存储 + 无冲突 + 定长数组 + 简易 hash 函数 (只有key)
 * 没有k-v就是个set 👉 “基于哈希表的去重集合（HashSet）”！
 * 数组里面放链表, 链表里面放k-v
 * ✅ 哈希函数	把 key 映射到 bucket	✔️你做到了！
 * ✅ 删除元素	remove(K key)	✔️你做到了！
 * ✅ 查找元素	get(K key)no contains yes ✔️你做到了！
 * ✅ 存储结构	数组 + 链表（或树）	✔️你做到了！
 * ✅ 泛型支持	支持任意类型 K, V	✔️你做到了！
 * ✅ 冲突处理	拉链法（LinkedList）或红黑树	✔️你做到了！
 * ✅ 动态扩容	超过负载因子后自动扩容	✔️你做到了！
 */

// ============================
//       链表节点结构
// ============================
class ListNode {
    HashItem item;
    ListNode next;

    public ListNode(HashItem item) {
        this.item = item;
        next = null;
    }
}

// class ListNode<K, V> {
//     HashItem<K, V> item;
//     ListNode<K, V> next;

//     public ListNode(HashItem<K, V> item) {
//         this.item = item;
//         next = null;
//     }
// }

// ============================
//         哈希项结构
// ============================
class HashItem {
    private int data;

    public HashItem(int data) {
        this.data = data;
    }

    public int getData() {
        return data;
    }

    public boolean equals(Object obj) { // 😭 是object不是hashitem
        if (this == obj)
            return true; // 😭自己和自己
        if (!(obj instanceof HashItem))
            return false; // 😭类别
        return ((HashItem) obj).data == this.data; // 😭 多态
    }
}

// ======= 泛型准备版（保留注释） =======
// class HashItem<K, V> {
//     private K key;
//     private V value;

//     public HashItem(K key, V value) {
//         this.key = key;
//         this.value = value;
//     }

//     public K getKey() {
//         return key;
//     }

//     public V getValue() {
//         return value;
//     }

//     public void setValue(V value) {
//         this.value = value;
//     }

//     public boolean equals(Object obj) {
//         if (this == obj)
//             return true;
//         if (!(obj instanceof HashItem))
//             return false;
//         HashItem<?, ?> other = (HashItem<?, ?>) obj;
//         return key.equals(other.key); // 只看 key 是否相同
//     }
// }

// ============================
//       主体 HashMap 类
// ============================
public class HashMap {
    private ListNode[] bucket;
    private int size;
    private int currentsize;
    private int FACTOR = 2;
    private int INITIAL_SIZE = 7;

    public HashMap() {
        bucket = new ListNode[size];
        currentsize = 0;
        size = INITIAL_SIZE;
    }

    public void addItem(HashItem item) {
        insertToBucket(bucket, item); // ✅ 插入！
        currentsize++;

        // ✅ 触发扩容条件
        if (currentsize >= size * 0.75) {
            expand();
        }
    }

    // public void put(K key, V value) {
    //     HashItem<K, V> item = new HashItem<>(key, value);
    //     insertToBucket(bucket, item);
    //     currentsize++;
    //     if (currentsize >= size * 0.75) expand();
    // }

    // public void put(int key, int value) {
    // HashItem item = new HashItem(key, value);
    // int hash = hashFunction(item);
    // ...
    // 逻辑都一样，只是判断 key 一致就更新 value
    // if (head.item.getKey() == key) {
    //     head.item.setValue(value); // 新增 setValue() 方法
    //     return;
    // }
    // ...

    public void removeItem(HashItem item) {
        int hash = hashFunction(item);
        if (bucket[hash] == null) {
            return;
        } else {
            ListNode head = bucket[hash];
            // 😭特殊情况：第一个节点就是目标
            if (head.item.equals(item)) {
                bucket[hash] = head.next; // 删除头结点
                return;
            }
            while (head.next != null) {
                if (head.next.item.equals(item)) {
                    head.next = head.next.next;
                    head = head.next; // 👈 没有这句你就卡死原地了哇！！
                    currentsize--;
                    return;
                }
            }
        }
    }

    public boolean contains(HashItem item) {
        int hash = hashFunction(item);
        ListNode head = bucket[hash];
        while (head != null) {
            if (head.item.equals(item))
                return true;
            head = head.next;
        }
        return false;
    }

    public int hashFunction(HashItem item) {
        int val = item.getData();
        return (val * 31 + val) % size; // module
    }

    // public Integer get(int key) {
    //     int hash = hashFunctionByKey(key);
    //     ListNode head = bucket[hash];
    //     while (head != null) {
    //         if (head.item.getKey() == key) return head.item.getValue();
    //         head = head.next;
    //     }
    //     return null;
    // }

    public void expand() {
        int newSize = FACTOR * size;
        ListNode[] newBucket = new ListNode[newSize];
        for (int i = 0; i < size; i++) {
            ListNode head = bucket[i];
            while (head != null) {
                insertToBucket(newBucket, head.item);
                head = head.next;
            }
        }
        this.size = newSize;
        this.bucket = newBucket;
    }

    private void insertToBucket(ListNode[] bucket, HashItem item) {
        int hash = (item.getData() * 31 + item.getData()) % bucket.length;
        // 插入到 bucket[hash]
        ListNode newNode = new ListNode(item); // 😭 记得加
        if (bucket[hash] == null) {
            bucket[hash] = newNode;
        } else {
            ListNode head = bucket[hash];
            while (head.next != null) { // 😭 条件
                if (head.item.equals(item))
                    return;
                head = head.next;
            }
            head.next = newNode;
        }
    }
}
