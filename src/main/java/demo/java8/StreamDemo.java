package demo.java8;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import util.MyUtil;
import domain.Book;

public class StreamDemo {

    public static void main(String[] args) {
        testList();
        testMap();
        arrayToStream();
    }

    public static void testList() {
        List<Integer> nums = MyUtil.toList(6, 3, null, 9, 4, null, 8, 5);
        
        // filter, collect
        List<Integer> list = nums.stream().filter(num -> num != null).collect(Collectors.toList());
        System.out.println(list);

        // sorted
        System.out.println(list.stream().sorted().collect(Collectors.toList()));
        
        // match
        System.out.println(list.stream().allMatch(e -> e > 5));
        System.out.println(list.stream().anyMatch(e -> e > 5));
        
        // map
        List<Book> books = MyUtil.toList(new Book("a", "James", 20), new Book("b", "Mars", 50));
        List<String> authors = books.stream().map(b -> b.getAuthor()).collect(Collectors.toList());
        System.out.println(authors);
    }
    
    public static void testMap() {
        List<Book> books = MyUtil.toList(new Book("c", "Zach", 88), new Book("a", "James", 20), new Book("b", "Mars", 50));
        
        // 无法保证Map的实现类
        Map<String, Book> map = books.stream().collect(Collectors.toMap(Book::getTitle, Function.identity()));
        for (Entry<String, Book> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue().getAuthor());
        }

        // 使用具体的Map实现类
        System.out.println("\nsorted:");
        map = books.stream().collect(
                Collectors.toMap(Book::getTitle, Function.identity(), (v1, v2) -> v1, LinkedHashMap::new));
        for (Entry<String, Book> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue().getAuthor());
        }
    }
    
    public static void arrayToStream() {
    	int[] arr = {3, 4, 5, 6};
    	IntStream stream = Arrays.stream(arr); // got IntStream
		System.out.println(stream.sum()); // 18
		
		Stream<int[]> stream2 = Stream.of(arr); // got Stream<int[]>
		System.out.println(stream2.count()); // 1
    	
    	Integer[] arr2 = {3, 4, 5, 6};
    	Stream<Integer> stream3 = Stream.of(arr2); // got Stream<Integer>
		System.out.println(stream3.count()); // 4
    }

}
