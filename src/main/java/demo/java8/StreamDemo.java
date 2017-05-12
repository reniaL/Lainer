package demo.java8;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
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
//        testSort();
//        testList();
//        testMap();
//        arrayToStream();
//        testIntStream();
        testConcat();
    }
    
    public static void testSort() {
        List<Book> books = MyUtil.toList(new Book("c", "May", 20), new Book("a", "James", 20), new Book("b", "May", 20));
        Map<String, Book> map = new HashMap<>();
        books.forEach(x -> map.put(x.getTitle(), x));
        books.stream()
                .sorted(Comparator.comparing(Book::getPrice).thenComparing(Book::getAuthor).reversed()
                        .thenComparing(Book::getTitle)).forEach(x -> System.out.println(x.getTitle()));
        books.stream().sorted(new Comparator<Book>() {

            @Override
            public int compare(Book o1, Book o2) {
                Book book1 = map.get(o1.getTitle());
                Book book2 = map.get(o2.getTitle());
                return book1.getTitle().compareTo(book2.getTitle());
            }
        }).forEach(x -> System.out.println(x.getTitle()));
    }

    public static void testList() {
        List<Integer> nums = MyUtil.toList(6, 3, null, 9, 4, null, 8, 5);
        System.out.println(nums.stream().skip(20).limit(2).collect(Collectors.toList()));
        
        // filter, collect
        List<Integer> list = nums.stream().filter(num -> num != null).collect(Collectors.toList());
        System.out.println(list);
        
        // skip, limit
        System.out.println(nums.stream().skip(10).limit(7).collect(Collectors.toList()));

        // sorted
        System.out.println(list.stream().sorted().collect(Collectors.toList()));
        // chained comparing
        // Comparator.comparing(Book::getPrice).thenComparing(Book::getTitle).reversed()
        
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
        
        System.out.println("\nentry sort:");
        List<Entry<String, Book>> entries = map.entrySet().stream()
                .sorted(Entry.comparingByKey(Collections.reverseOrder())).collect(Collectors.toList());
        for (Entry<String, Book> entry : entries) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        // 使用具体的Map实现类
        System.out.println("\nsorted:");
        map = books.stream().collect(
                Collectors.toMap(Book::getTitle, Function.identity(), (v1, v2) -> v1, LinkedHashMap::new));
        for (Entry<String, Book> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue().getAuthor());
        }
    }
    
    public static void testIntStream() {
        List<Book> books = MyUtil.toList(new Book("c", "Zach", 88), new Book("a", "James", 20), new Book("b", "Mars", 50));
        System.out.println(books.stream().mapToInt(Book::getPrice).sum());
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
    
    public static void testConcat() {
        List<Integer> list1 = MyUtil.toList(1, 2, 3, 4);
        List<Integer> list2 = MyUtil.toList(5, 6, 7, 8);
        List<Integer> result = Stream.concat(list1.stream().filter(x -> x % 2 == 0), list2.stream().filter(x -> x % 2 == 0)).collect(Collectors.toList());
        System.out.println(result);
    }

}
