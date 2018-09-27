package demo.algorithm;


import java.util.Arrays;

public class QuickSortDemo {
    
    public static void main(String[] args) {
        int[] arr = {3, 9, 6, 1, 0, 5, 2, 8, 4, 7};
        quickSort(arr, 0, arr.length-1);
        for (int i : arr) {
            System.out.print(i + ", ");
        }
    }

    private static void quickSort(int[] arr, int begin, int end) {
        if (begin >= end) {
            return;
        }
        
        int pivot = partition(arr, begin, end);
        quickSort(arr, begin, pivot-1);
        quickSort(arr, pivot+1, end);
    }
    
    private static int partition(int[] arr, int begin, int end) {
        // 以第一个元素为基准数（支点）
        // pivot 值是基准数的索引，随着下面的循环、判断，不断后移到最终正确的位置
        int pivot = begin;
        for (int i = begin+1; i <= end; i++) {
            if (arr[i] <= arr[begin]) {
                pivot += 1;
                swap(arr, i, pivot);
            }
        }

        // 最后做一次交换，因为基准数一直在开头没动过，要交换到正确的位置
        swap(arr, begin, pivot);
        System.out.println(Arrays.toString(arr));
        return pivot;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
}
