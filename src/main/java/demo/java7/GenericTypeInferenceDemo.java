package demo.java7;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class GenericTypeInferenceDemo {
    
    public static void main(String[] args) {
//        testMap();
        testList();
    }
    
    public static void testMap() {
        Map<String, Integer> map = new HashMap<>();
        map.put("a", 1);
        map.put("b", 99);
        System.out.println(map);
    }
    
    // fail
    public static void testList() {
        List<Integer> list = new ArrayList<>();
        list.add(100);
        list.addAll(new ArrayList<>());
        System.out.println(list);
    }

}
