package demo.algorithm;

public class MergeSortDemo {
    
    public static void main(String[] args) {
        int[] arr = { 3, 9, 6, 1, 0, 5, 2, 8, 4, 7 };
        int[] tempArr = new int[arr.length];
        mergeSort(arr, 0, arr.length, tempArr);
        for (int i : arr) {
            System.out.print(i + ", ");
        }
    }
    
    /**
     * @param begin inclusive
     * @param end exclusive
     */
    private static void mergeSort(int[] arr, int begin, int end, int[] tempArr) {
        if (end - begin < 2) {
            return;
        }
        
        int middle = (begin + end) / 2;
        mergeSort(arr, begin, middle, tempArr);
        mergeSort(arr, middle, end, tempArr);
        merge(arr, begin, middle, end, tempArr);
        copyArray(tempArr, arr);
    }
    
    private static void merge(int[] arr, int begin, int middle, int end, int[] tempArr) {
        int leftIndex = begin;
        int rightIndex = middle;
        for (int i = begin; i < end; i++) {
            if (leftIndex < middle && (rightIndex >= end || arr[leftIndex] <= arr[rightIndex])) {
                tempArr[i] = arr[leftIndex];
                leftIndex++;
            } else {
                tempArr[i] = arr[rightIndex];
                rightIndex++;
            }
        }
    }
    
    private static void copyArray(int[] tempArr, int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = tempArr[i];
        }
    }
    
}
