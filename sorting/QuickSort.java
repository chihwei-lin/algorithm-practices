package sorting;

import java.util.Arrays;

public class QuickSort {
    public static void sort(int[] nums, int lo, int hi){
        if(nums == null || nums.length == 0)
            return;

        int i = lo, j = hi;
        int pivot = nums[lo];

        while(i<j){
            while(i<j && nums[i] < pivot)
                i++;
            while(i<j && nums[j] > pivot)
                j--;

            if(nums[i] == nums[j] && i < j)
                i++;
            else
                swap(nums, i, j);
        }

        if(i-1 > lo)    sort(nums, 0, i-1);
        if(j+1 < hi)    sort(nums, j+1, hi);
    }

    private static void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args){
        int[] arr = new int[]{49, 38, 65, 97, 76, 13, 27, 49};
        System.out.println(Arrays.toString(arr));

        System.out.println("=============Sort================");
        sort(arr, 0, arr.length-1);
        System.out.println(Arrays.toString(arr));
    }
}
