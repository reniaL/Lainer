package demo.algorithm;

public class FindGreatestSumOfSubArrayDemo {
    
    /**
     * 查找一个数组中，和最大的连续子数组
     */
    public static int findGreatestSumOfSubArray(int[] arr) {
        // 参数校验
        if (arr == null || arr.length < 1) {
            throw new IllegalArgumentException("Array must contain an element");
        }
        
        // 记录最大的子数组和，开始时是最小的整数
        int max = Integer.MIN_VALUE;
        // 当前的和
        int curMax = 0;
        // 数组遍历
        for (int i : arr) {
            // 如果当前和小于等于0，就重新设置当前和
            if (curMax <= 0) {
                curMax = i;
            }
            // 如果当前和大于0，累加当前和
            else {
                curMax += i;
            }
            
            // 更新记录到的最在的子数组和
            if (max < curMax) {
                max = curMax;
            }
        }
        
        return max;
    }
    
    public static void main(String[] args) {
        int[] arr = {1, -2, 3, 10, -4, 7, 2, -5};
        System.out.println(findGreatestSumOfSubArray(arr));
    }
}
