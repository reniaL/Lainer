package demo.jse;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;

/**
 * Jackson to handle json
 * @author huanran.chen
 */
public class JacksonDemo {
    
    public static void main(String[] args) {
        test();
    }
    
    @SuppressWarnings("unchecked")
    private static void test() {
        File file = new File("E:\\channels.txt");
        ObjectMapper mapper = new ObjectMapper();
        try {
            long start = System.currentTimeMillis();
            List<Map<String, Object>> list = mapper.readValue(file, List.class);
            long end = System.currentTimeMillis();
            System.out.println("size: " + list.size());
            System.out.println("time: " + (end - start) + " ms.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
