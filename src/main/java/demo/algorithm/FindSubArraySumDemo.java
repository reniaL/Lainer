package demo.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FindSubArraySumDemo {
    
    /**
     * 查找输入数组是否存在一个子数组，其和等于目标值
     */
    public boolean findSubArraySum(int[] arr, int target) {
        List<List<Integer>> fullList = new ArrayList<>();
        for (int i : arr) {
            // 复制上一轮遍历之后的全列表，并新增一个空列表元素
            List<List<Integer>> tempList = copy(fullList);
            tempList.add(new ArrayList<>());
            
            for (List<Integer> list : tempList) {
                // 对每个列表，都新增这一轮遍历的数值，检查列表之和是否和目标想到
                list.add(i);
                if (list.stream().mapToInt(x -> x).sum() == target) {
                    System.out.println(list);
                    return true;
                }
            }
            fullList.addAll(tempList);
        }
        return false;
    }
    
    // 深复制一个嵌套列表
    private List<List<Integer>> copy(List<List<Integer>> list) {
        return list.stream().map(x -> new ArrayList<>(x)).collect(Collectors.toList());
    }
    
    
    public static void main(String[] args) {
        FindSubArraySumDemo demo = new FindSubArraySumDemo();
        int[] arr = {2, 5, 8, 17, 21};
        int target = 31;
        System.out.println(demo.findSubArraySum(arr, target));
    }
}
