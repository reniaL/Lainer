package demo.commons;

import java.util.Map;
import java.util.TreeMap;

public class MapDemo {
    
    public static void main(String[] args) {
        testTreeMap();
    }
    
    public static void testTreeMap() {
        Map<String, String> map = new TreeMap<>();
        map.put("c", "a");
        map.put("a", "z");
        map.put("b", "d");
    
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry.getKey());
        }
    }
}
