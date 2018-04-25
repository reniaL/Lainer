package com.github.lainer.demo.json;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import domain.Customer;

/**
 * TODO JsonView
 */
public class JacksonTest {
    
    private ObjectMapper objectMapper = new ObjectMapper();
    
    @Test
    @SuppressWarnings("unchecked")
    public void testReadFile() throws IOException {
        File file = new File("/temp/channels.js");
        ObjectMapper mapper = new ObjectMapper();
        long start = System.currentTimeMillis();
        List<Map<String, Object>> list = mapper.readValue(file, List.class);
        long end = System.currentTimeMillis();
        System.out.println("size: " + list.size());
        System.out.println("time: " + (end - start) + " ms.");
    }
    
    /**
     * node manipulation
     */
    @Test
    public void testJsonNode() {
        ObjectNode root = objectMapper.createObjectNode();
        root.put("code", 123);
        root.put("name", "Mary");
        
        ArrayNode list = root.putArray("list");;
        for (int i = 0; i < 5; i++) {
            list.add(i);
        }
        System.out.println(root);
        System.out.println(root.get("name"));
        System.out.println(root.path("list"));
    }
    
    @Test
    public void testStringToJsonNode() throws IOException {
        Customer customer = new Customer.CustomerBuilder().id(1).name("Jack").age(20).gender("male").build();
        String content = objectMapper.writeValueAsString(customer);
        System.out.println(content);
    
        JsonNode jsonNode = objectMapper.readTree(content);
        System.out.println(jsonNode);
    }
    
    @Test
    public void testFilter() throws JsonProcessingException {
        SimpleFilterProvider filterProvider = new SimpleFilterProvider();
        filterProvider.addFilter("customerFilter", SimpleBeanPropertyFilter.serializeAllExcept("name", "age"));
        ObjectWriter writer = objectMapper.writer(filterProvider);
        Customer customer = new Customer.CustomerBuilder().id(1).name("Jack").age(20).gender("male").build();
        System.out.println(writer.writeValueAsString(customer));
        
        // 如果用默认的 writer ，会出错，因为 Customer 有 @JsonFilter，没有处理就会出错
//        System.out.println(objectMapper.writeValueAsString(customer));
    }
    
    /**
     * 使用 mixIn，可以不在目标类 (Customer) 上加注解，有点像多了一层代理
     */
    @Test
    public void testFilterWithMixIn() {
        ObjectMapper mapper = objectMapper.copy(); // 复制，以免 mixIn 影响原有的（一般是通用的） objectMapper
        mapper.addMixIn(Customer.class, CustomerMixIn.class);
        
        SimpleFilterProvider filterProvider = new SimpleFilterProvider().addFilter("customerMixInFilter",
                SimpleBeanPropertyFilter.serializeAllExcept("name", "age"));
        Customer customer = new Customer.CustomerBuilder().id(1).name("Jack").age(20).gender("male").build();
        try {
            System.out.println(mapper.writer(filterProvider).writeValueAsString(customer));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
    
    @JsonFilter("customerMixInFilter")
    class CustomerMixIn {
    }
}
