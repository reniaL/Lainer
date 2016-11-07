package demo.java8;

import java.util.Arrays;

public class MethodReferenceDemo {

    public static void main(String[] args) {
        testStaticMethod();
        testInstanceMethod();
    }

    private static void testStaticMethod() {
        Integer[] arr = { 3, 9, 5, 1 };
        Arrays.sort(arr, Something::comp);
    }

    private static void testInstanceMethod() {
        Something something = new Something();
        Converter<String, String> converter = something::startsWith;
        String converted = converter.convert("Java");
        System.out.println(converted); // "J"
    }

    @FunctionalInterface
    public static interface Converter<F, T> {
        T convert(F from);
    }

    public static class Something {
        public static int comp(Integer a, Integer b) {
            return 1;
        }
        
        String startsWith(String s) {
            return String.valueOf(s.charAt(0));
        }
    }
}
