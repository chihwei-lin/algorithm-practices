package sorting;

import java.awt.*;
import java.util.Arrays;
import java.util.Random;

/**
 * 此文件用于练习排序算法
 * 所有排序定义参考 https://www.cnblogs.com/guoyaohua/p/8600214.html
 */

public class Sorting {
    private int[] elements;

    private final int SIZE = 20;

    public Sorting() {
        Random random = new Random();
        this.elements = new int[SIZE];

        // randomly generate SIZE number in range of 0 to 100
        for (int i = 0; i < SIZE; ++i) {
            this.elements[i] = random.nextInt(100);
        }
    }

    /**
     * 冒泡排序
     * <p>
     * 比较相邻的元素。如果第一个比第二个大，就交换它们两个；
     * 对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对，这样在最后的元素应该会是最大的数；
     * 针对所有的元素重复以上的步骤，除了最后一个；
     * 重复步骤1~3，直到排序完成。
     *
     * @return int[] copy
     */
    public int[] BubbleSort() {
        int[] copy = new int[this.SIZE];
        System.arraycopy(this.elements, 0, copy, 0, this.SIZE);

        int temp;           // defining temp variable outside the loop saves spaces
        for (int i = 0; i < this.SIZE; i++) {
            for (int j = 0; j < this.SIZE - i - 1; j++) {
                if (copy[j] > copy[j + 1]) {
                    temp = copy[j];
                    copy[j] = copy[j + 1];
                    copy[j + 1] = temp;
                }
            }
        }

        return copy;
    }

    /**
     * 选择排序
     * <p>
     * 数组分为未排序和已排序
     * 从未排序中取最小元素放入已排序
     * <p>
     * 初始状态：无序区为R[1..n]，有序区为空
     * 第i趟排序(i=1,2,3…n-1)开始时，当前有序区和无序区分别为R[1..i-1]和R(i..n）。该趟排序从当前无序区中-选出关键字最小的记录 R[k]
     * 将它与无序区的第1个记录R交换，使R[1..i]和R[i+1..n)分别变为记录个数增加1个的新有序区和记录个数减少1个的新无序区
     * n-1趟结束，数组有序化了。
     *
     * @return
     */
    public int[] SelectionSort() {
        int[] copy = new int[this.SIZE];
        System.arraycopy(this.elements, 0, copy, 0, this.SIZE);

        int temp;
        for (int i = 0; i < this.SIZE; i++) {
            int index = i;
            for (int j = i + 1; j < this.SIZE; j++) {
                if (copy[index] > copy[j])
                    index = j;
            }

            temp = copy[i];
            copy[i] = copy[index];
            copy[index] = temp;
        }

        return copy;
    }

    /**
     * 插入排序
     * <p>
     * 从第一个元素开始，该元素可以认为已经被排序；
     * 取出下一个元素，在已经排序的元素序列中从后向前扫描；
     * 如果该元素（已排序）大于新元素，将该元素移到下一位置；
     * 重复步骤3，直到找到已排序的元素小于或者等于新元素的位置；
     * 将新元素插入到该位置后；
     * 重复步骤2~5。
     *
     * @return
     */
    public int[] InsertionSort() {
        int[] copy = new int[this.SIZE];
        System.arraycopy(this.elements, 0, copy, 0, this.SIZE);

        int temp;
        for (int i = 1; i < this.SIZE; i++) {
            temp = copy[i];
            int j = i;
            while (j > 0 && temp < copy[j - 1]) {
                copy[j] = copy[j - 1];
                j--;
            }
            copy[j] = temp;         // insertion the value
        }

        return copy;
    }

    /**
     * 希尔排序
     *
     * 选择一个增量序列t1，t2，…，tk，其中ti>tj，tk=1；
     * 按增量序列个数k，对序列进行k 趟排序；
     * 每趟排序，根据对应的增量ti，将待排序列分割成若干长度为m 的子序列，分别对各子表进行直接插入排序。
     * 仅增量因子为1 时，整个序列作为一个表来处理，表长度即为整个序列的长度。
     *
     * @return
     */
    public int[] ShellSort() {
        int[] copy = new int[this.SIZE];
        System.arraycopy(this.elements, 0, copy, 0, this.SIZE);

        int temp;
        for (int gap = this.SIZE/2; gap > 0; gap = gap / 2){
             for(int i=gap;i<this.SIZE;i++){
                 temp = copy[i];
                 int j = i;
                 while(j >= gap && copy[j-gap] > temp){
                     copy[j] = copy[j-gap];
                     j -= gap;
                 }
                 copy[j] = temp;
             }
        }

        return copy;
    }

    /**
     * 归并排序
     *
     * 把长度为n的输入序列分成两个长度为n/2的子序列；
     * 对这两个子序列分别采用归并排序；
     * 将两个排序好的子序列合并成一个最终的排序序列。
     *
     */
    public int[] MergeSort(){
        int size = this.SIZE;
        int[] copy = new int[size];
        System.arraycopy(this.elements, 0, copy, 0, size);

        copy = MergeSort_Partition(copy);

        return copy;
    }

    private int[] MergeSort_Partition(int[] data){
        if(data.length == 1 || data.length == 0)    return data;

        int size = data.length;
        int[] left = new int[size/2];
        int[] right = new int[size - size/2];

        System.arraycopy(data, 0, left, 0, size/2);
        System.arraycopy(data, size/2, right, 0, size-size/2);

        data = MergeSort_Merge(MergeSort_Partition(left), MergeSort_Partition(right));

        return data;
    }

    private int[] MergeSort_Merge(int[] left, int[] right){
        if(left.length == 1 && right.length == 0)
            return left;
        else if(left.length == 0 && right.length == 1)
            return right;
        else if(left.length == 1 && right.length == 1)
            return (left[0] < right[0]) ? new int[]{left[0], right[0]} : new int[]{right[0], left[0]};

        int[] merged = new int[left.length+right.length];

        int leftIndex = 0, rightIndex = 0;

        while(leftIndex < left.length && rightIndex < right.length){
            if(left[leftIndex] <= right[rightIndex]){
                merged[leftIndex + rightIndex] = left[leftIndex];
                leftIndex++;
            }else{
                merged[leftIndex + rightIndex] = right[rightIndex];
                rightIndex++;
            }
        }

        if(leftIndex == left.length){
            while(rightIndex < right.length){
                merged[leftIndex + rightIndex] = right[rightIndex];
                rightIndex++;
            }
        }else{
            while(leftIndex < left.length){
                merged[rightIndex + leftIndex] = left[leftIndex];
                leftIndex++;
            }
        }

        return merged;
    }

    /**
     * 快速排序
     *
     * 从数列中挑出一个元素，称为 “基准”（pivot）
     * 重新排序数列，所有元素比基准值小的摆放在基准前面，所有元素比基准值大的摆在基准的后面（相同的数可以到任一边）。
     * 在这个分区退出之后，该基准就处于数列的中间位置。这个称为分区（partition）操作
     * 递归地（recursive）把小于基准值元素的子数列和大于基准值元素的子数列排序。
     *
     * @return
     */
    public int[] QuickSort(){
         int[] copy = new int[this.SIZE];
         System.arraycopy(this.elements, 0, copy, 0, this.SIZE);

         copy = QuickSort_Main(copy, 0, this.SIZE-1);

         return copy;
    }

    private int[] QuickSort_Main(int[] data, int lo, int hi){
        if(lo < hi){
            int pivot = QuickSort_Partition(data, lo, hi);

            QuickSort_Main(data, lo, pivot-1);
            QuickSort_Main(data, pivot+1, hi);
        }

        return data;
    }

    private int QuickSort_Partition(int[] data, int lo, int hi){
        int pivot = data[hi];
        int i = lo-1;
        for(int j=lo;j<hi;j++){
            if(data[j] < pivot){
               i++;

               int temp = data[i];
               data[i] = data[j];
               data[j] = temp;
            }
        }

        // swap
        int temp = data[i+1];
        data[i+1] = data[hi];
        data[hi] = temp;

        return i+1;
    }

    @Override
    public String toString() {
        return Arrays.toString(elements);
    }
}
