package demo.algorithm;




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
        int pivot = begin;
        for (int i = begin+1; i <= end; i++) {
            if (arr[i] <= arr[begin]) {
                pivot += 1;
                swap(arr, i, pivot);
            }
        }
        swap(arr, begin, pivot);
        return pivot;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
}
