package com.github.lainer.demo.json;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;
import org.junit.Test;

public class JacksonTest {
    
    private ObjectMapper objectMapper = new ObjectMapper();
    
    @Test
    @SuppressWarnings("unchecked")
    public void testReadFile() {
        File file = new File("/temp/channels.js");
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
    
    /**
     * node manipulation
     */
    @Test
    public void testJsonNode() {
        ObjectNode root = objectMapper.createObjectNode();
        root.put("code", 123);
        root.put("name", "Mary");
        
        ArrayNode list = objectMapper.createArrayNode();
        for (int i = 0; i < 5; i++) {
            list.add(i);
        }
        root.put("list", list);
        System.out.println(root);
        System.out.println(root.get("name"));
        System.out.println(root.path("list"));
    }
}
