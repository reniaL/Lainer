package demo.algorithm;

import java.util.Arrays;

public class PlantTreesDemo {
    
    /**
     * positions 表示一条路上可种树的位置，1表示树，0表示空。两棵树不能相邻。问能不能再种 target 棵树。
     */
    public static void plantTrees(int[] positions, int target) {
        int count = 0;
        int i=0;
        while (i < positions.length) {
            if (positions[i] == 1) {
                i+=2;
            } else if (positions[i] == 0 && (i == positions.length-1 || positions[i + 1] == 0) && (i == 0 || positions[i - 1] == 0)) {
                count++;
                positions[i] = 1;
                i+=2;
            } else {
                i++;
            }
        }
        System.out.println("count: " + count);
        System.out.println(Arrays.toString(positions));
        System.out.println(count >= target);
    }
    
    public static void main(String[] args) {
        int[] positions = {0, 0, 1, 0, 0, 0, 0, 1, 0, 0};
        int target = 1;
        plantTrees(positions, target);
    }
}
