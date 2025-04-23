package algo.Sort;

import java.util.Arrays;

/**
 * input : int[] nums
 */
public class Solution {
    // ======== selection bubble insertion (iteration) ========
    // TC和SC都是一样的, 那难道就没有区别吗?
    /**
     * 1. Insertion Sort 是唯一在“部分有序”时性能变强的
     * (1)Selection 还是照常找最小 → 没变快
     * (2)Bubble 还会两两比较 → 没变快
     * (3)Insertion 直接一轮扫过去 → 所以这个 while 根本一次都不进！！ → 变成 O(n)
     * 2. Seletion Sort 是唯一不稳定的(稳定性：结果是否保持“相等元素的顺序”)
     * (1)Bubble 相邻交换 → 保持原顺序
     * (2)Insertion 插入插在前面 → 顺序稳定
     * (3)Selection Sort(3a 和 3b 是值相等但“身份不同”的 3)
     */


    /**
     * Selection Sort. 用swap. 每轮最后只swap一次. 保存minIndex
     * 
     * @param nums the array to be sorted
     * TC: O(n^2)
     * SC: O(1)
     * [4,2,2,6,7,1,1] -> [1,1,2,2,4,6,7]
     * ascending
     * [4,2] -> [2,4]
     */
    public void selectionSort(int[] nums) {
        // iteration
        for (int i = 0; i < nums.length; i++) { 
             int minIndex = i;
            // iteration 其实就是找第i小的数
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[minIndex]) {
                    minIndex = j;
                }
            }
            swap(i, minIndex, nums);
        }
    }
    
    /**
     * buble sort ascending. 也是swap, 但是不存minIndex,每次比较都swap.只和邻居比较,往后冒泡
     * 
     * @param nums the array
     * TC: O(^2)
     * sc: o(1)
     */
    public void bubbleSort(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = 0; j < nums.length - 1 - i; j++) {
                if (nums[j] > nums[j + 1]) {
                    swap(i, j, nums);
                }
            }
        }
    }
    
    /**
     * Private helper for swapping.
     * 
     * @param i one index 
     * @param j the other index
     * @param nums the array
     * TC: O(1)
     * SC: O(1)
     */
    private void swap(int i, int j, int[] nums) { 
        int temp = nums[j];
        nums[j] = nums[i];
        nums[i] = temp;
    }

    /**
     * Insertion sort. 往后copy.
     * 
     * @param nums the integer array
     * TC:O(n^2)
     * SC:O(1)
     * ascending
     * [3,1] -> [3,3] -> break -> [1,3]
     * [5,4,3] -> [3,4,5]
     *  1. [5,4,3] -> [5, 5, 3] → [4, 5, 3]
     *  2. [4, 5, 3] -> [4, 5, 5] → [4, 4, 5] → [3, 4, 5]
     */
    public void insertionSort(int[] nums) {
        // iteration
        for (int i = 1; i < nums.length; i++) { // 从第二个开始 默认第一个是排序好的
            int current = nums[i]; // 要插入的值
            int j = i - 1; // 前面一个位置
            while (j >= 0 && nums[j] > current) { // 可以 = 0 结束是-1
                nums[j + 1] = nums[j]; // 往后copy
                j--;
            }
            nums[j + 1] = current; 
        }
    }

    // ======== merge and quick(recursion) ======== 
    /**
     * 1.merge sort稳定, quick sort不稳定
     * 
     */
   
    /**
     * Merge sort. Divide and conquer.
     * 
     * @param nums the numbers
     * TC:
     * 1.劈到每一层只有 1 个元素为止 → 一共 log₂n 层(每一次“劈半”都会 创建新数组，把原数组的内容 复制一遍)
     * 2.merge 每一层处理 n 个元素
     * 3.T(n) = n * log n = O(n log n)
     * SC:
     * 1.每次合并都要新数组；递归栈为 O(log n) 可合并进 O(n)
     * Merge Sort ≈ 分层劈半 + 每层扫一遍
     * → log n 层 × n 数量
     * → 时间是 O(n log n)
     * 同时牺牲空间开新数组 → 空间 O(n)
     */
    public int[] mergeSort(int[] nums) {
        if (nums.length <= 1) return nums;

        int mid = nums.length / 2;

        int[] left = mergeSort(Arrays.copyOfRange(nums, 0, mid));
        int[] right = mergeSort(Arrays.copyOfRange(nums, mid, nums.length));

        return merge(left, right);
    }

    /**
     * Helper function return new array.
     * 长度可能不一样.
     * 牺牲空间来满足时间.
     * 
     * @param left left array
     * @param right right array
     * @return new array
     * TC: TC = O(m + n) = O(n) 
     * SC: SC = O(m + n)
     */
    private int[] merge(int[] left, int[] right) {
    //长度还是可能不一样的
    int[] newArr = new int[left.length + right.length];
    int index = 0;
    int l = 0;
    int r = 0;
    while (l < left.length && r < right.length) {
        if (left[l] < right[r]) {
            newArr[index] = left[l];
            l++;
        } else {
            newArr[index] = right[r];
            r++;
        }
        index++;
    }
        // 剩余的 left 全部放进来
    while (l < left.length) {
        newArr[index++] = left[l++];
    }

    // 剩余的 right 全部放进来
    while (r < right.length) {
        newArr[index++] = right[r++];
    }

    return newArr;
   }
    
   /**
   * Quick sort. 先把每个放在正确的位置.
   * 选择 pivot	通常是末尾、中间或随机
   * partition	把数组按 pivot 分成左右两部分
   * 递归排序	对左边、右边分别调用 quickSort
   * 
   * @param nums the array
   * TC:
   * SC:
   * [4, 2, 7, 3, 5] 5 -> [4, 2, 3, 5, 7]
   */
    public void quickSort(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
    }

    /**
     * Helper function for quicksort.
     * Overwirte.
     * 
     * @param nums
     * @param low
     * @param high
     */
    private void quickSort(int[] nums, int low, int high) {
        if (low >= high) return;

        int pivotIndex = partition(nums, low, high);

        quickSort(nums, low, pivotIndex - 1);
        quickSort(nums, pivotIndex + 1, high);
    }

    /**
     * 用的swap.
     * 
     * @param nums
     * @param low
     * @param high
     * @return
     * ✨ partition 过程：
     * j	nums[j]	pivot=5	nums[j] < pivot?	操作	nums 状态	i
     * 0	9	5	❌	不动	[9, 3, 7, 6, 2, 8, 5]	0
     * 1	3	5	✅	swap(0,1)，i++	[3, 9, 7, 6, 2, 8, 5]	1
     * 2	7	5	❌	不动		1
     * 3	6	5	❌	不动		1
     * 4	2	5	✅	swap(1,4)，i++	[3, 2, 7, 6, 9, 8, 5]	2
     * 5	8	5	❌	不动		2
     */
    private int partition(int[] nums, int low, int high) {
        int pivot = nums[high]; // 选择最后一个作为 pivot
        int i = low; // i 表示：<= pivot 的区域右边界

        for (int j = low; j < high; j++) {
            if (nums[j] < pivot) {
                swap(nums, i, j); // 小的换到左边
                i++;
            }
        }

        swap(nums, i, high); // 把 pivot 放到中间
        return i; // pivot 的最终位置
    }


    // ======== other ======== 
    public void heapSort(int[] nums) {

    }

    public void radixSort(int[] nums) {

    }

    public void countingSort(int[] nums) {

    }
}
