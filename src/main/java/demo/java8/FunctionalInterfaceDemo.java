package demo.java8;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import domain.Book;

public class FunctionalInterfaceDemo {

    private static Book B1 = new Book("Java in Action", "Mars", 40);
    private static Book B2 = new Book("Thinking in Java", "Jack", 80);
    private static Book B3 = new Book("Design Pattern", "Jack", 100);

    public static void main(String[] args) {
//         testPredicate();
//        testConsumer();
         testFunction();
    }

    public static void testPredicate() {
        Predicate<Integer> evenPre = e -> e % 2 == 0;
        System.out.println(evenPre.test(1));
        System.out.println(evenPre.test(4));
        System.out.println();

        Predicate<Integer> oddPre = evenPre.negate();
        System.out.println(oddPre.test(1));
        System.out.println(oddPre.test(4));
        System.out.println();

        Predicate<Integer> oddGte3Pre = oddPre.and(e -> e >= 3);
        System.out.println(oddGte3Pre.test(1));
        System.out.println(oddGte3Pre.test(4));
        System.out.println(oddGte3Pre.test(5));
    }

    public static void testConsumer() {
        Consumer<Book> output = e -> System.out.println(String.format("%s by %s, $%d", e.getTitle(), e.getAuthor(),
                e.getPrice()));
        output.accept(B1);
        output.accept(B2);
    }

    public static void testFunction() {
        Function<Book, String> extractAuthorFunc = e -> e.getAuthor();
        System.out.println(extractAuthorFunc.apply(B1));
        System.out.println(extractAuthorFunc.apply(B2));

        Function<Book, String> getAuthorEmailFunc = extractAuthorFunc.andThen(e -> e + "@abc.com");
        System.out.println(getAuthorEmailFunc.apply(B3));
    }

}
