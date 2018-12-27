package com.github.lainer.demo.jse;

import java.util.regex.Pattern;

import org.junit.Test;

public class RegexTest {
    
    @Test
    public void testIgnoreCase() {
        // ignore case for whole regex
        Pattern pattern = Pattern.compile("x*abc", Pattern.CASE_INSENSITIVE);
        System.out.println(pattern.matcher("XXaBc").matches()); // true
        
        // ignore case for some parts
        System.out.println("xxaBc".matches("x*(?i)abc")); // true
        System.out.println("XXaBc".matches("x*(?i)abc")); // false
    }
}
