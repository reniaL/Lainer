package demo.algorithm;

import java.util.ArrayList;
import java.util.List;


public class CutPipesDemo {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * 返回水管的最高总售价
     * @param length int整型 水管的总长度，多少米。范围在1米-100米之间。
     * @return int整型
     */
    public int calc (int length) {
        int[] points = {0, 1, 5, 8, 9, 10, 17, 18, 20, 24, 30};
        List<Integer> result = new ArrayList<>();
        result.add(0);
        for (int i = 1; i < length+1; i++) {
            List<Integer> temp = new ArrayList<>();
            for (int cutLength = 1; cutLength < points.length; cutLength++) {
                if (i >= cutLength) {
                    temp.add(result.get(i - cutLength) + points[cutLength]);
                }
            }
            result.add(max(temp));
        }
        return result.get(result.size() - 1);
    }
    
    private int max(List<Integer> list) {
        if (list == null || list.isEmpty()) {
            return 0;
        }
        
        int result = 0;
        for (Integer i : list) {
            if (i > result) {
                result = i;
            }
        }
        return result;
    }
    
    public static void main(String[] args) {
        CutPipesDemo instance = new CutPipesDemo();
        System.out.println(instance.calc(1));
        System.out.println(instance.calc(3));
        System.out.println(instance.calc(5));
        System.out.println(instance.calc(8));
        System.out.println(instance.calc(99));
        System.out.println(instance.calc(100));
    }
}
