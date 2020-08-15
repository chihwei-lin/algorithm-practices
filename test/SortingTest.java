package test;

import sorting.Sorting;

import java.util.Arrays;

public class SortingTest {
    public static void main(String[] args) {
        Sorting sorting = new Sorting();
        System.out.printf("%-20s %-30s\n", "Original array:", sorting.toString());

        System.out.printf("%-20s %-30s\n", "Bubble Sort: ", Arrays.toString(sorting.BubbleSort()));

        System.out.printf("%-20s %-30s\n", "Selection Sort: ", Arrays.toString(sorting.SelectionSort()));

        System.out.printf("%-20s %-30s\n", "Insertion Sort: ", Arrays.toString(sorting.InsertionSort()));

        System.out.printf("%-20s %-30s\n", "Shell Sort: ", Arrays.toString(sorting.ShellSort()));

        System.out.printf("%-20s %-30s\n", "Merge Sort: ", Arrays.toString(sorting.MergeSort()));

        System.out.printf("%-20s %-30s\n", "Quick Sort: ", Arrays.toString(sorting.QuickSort()));
    }
}
