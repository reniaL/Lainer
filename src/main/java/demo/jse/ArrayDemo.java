package demo.jse;

import java.util.Arrays;

public class ArrayDemo {
    
    public static void main(String[] args) {
        arrayCopy();
    }
    
    private static void arrayCopy() {
        byte[] src = {1, 3, 5, 7, 9};
        byte[] dest = new byte[3];
        System.arraycopy(src, 1, dest, 0, 3);
        System.out.println(Arrays.toString(dest));
    }
}
