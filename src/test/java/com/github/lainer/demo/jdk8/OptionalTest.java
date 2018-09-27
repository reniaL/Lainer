package com.github.lainer.demo.jdk8;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.Optional;

import org.junit.Test;

public class OptionalTest {
    
    @Test
    public void empty() {
        Optional<String> op = Optional.empty();
        assertFalse(op.isPresent());
    }
    
    @Test(expected = NullPointerException.class)
    public void of_exception() {
        Optional.of(null);
    }
    
    @Test
    public void ofNullable() {
        Optional<String> op = Optional.ofNullable(null);
        assertFalse(op.isPresent());
    }
    
    @Test
    public void ifPresent() {
        Optional<String> op = Optional.of("hello");
        op.ifPresent(System.out::println);
    }
    
    /**
     * when text is null, getDefault() will be called in both cases
     */
    @Test
    public void orElse_null() {
        String text = null;
        
        System.out.println("Using orElseGet:");
        String defaultText = Optional.ofNullable(text).orElseGet(this::getDefault);
        assertEquals("Default Value", defaultText);
        
        System.out.println("Using orElse:");
        defaultText = Optional.ofNullable(text).orElse(getDefault());
        assertEquals("Default Value", defaultText);
    }
    
    /**
     * when text is not null, getDefault() will NOT be called for orElseGet()
     */
    @Test
    public void orElse_notNull() {
        String text = "hello";
        
        System.out.println("Using orElseGet:");
        String defaultText = Optional.ofNullable(text).orElseGet(this::getDefault);
        assertEquals("hello", defaultText);
        
        System.out.println("Using orElse:");
        defaultText = Optional.ofNullable(text).orElse(getDefault());
        assertEquals("hello", defaultText);
    }
    
    @Test
    public void map() {
        Optional<Integer> op = Optional.ofNullable(null);
        op.map(x -> x + 5).ifPresent(System.out::println); // does nothing, no exception
    
        op = Optional.of(2);
        op.map(x -> x + 5).ifPresent(System.out::println);
    }
    
    public String getDefault() {
        System.out.println("Getting Default Value");
        return "Default Value";
    }
}
