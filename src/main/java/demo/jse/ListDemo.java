package demo.jse;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.ListUtils;

import domain.Book;
import util.MyUtil;

public class ListDemo {

    public static void main(String[] args) {
        ListDemo demo = new ListDemo();
        demo.testRemoveSubList();
    }

    @SuppressWarnings("unchecked")
    public void testRemoveSubList() {
        Book b1 = new Book("a", "aa", 11);
        Book b2 = new Book("b", "bb", 22);
        Book b3 = new Book("c", "cc", 33);
        Book b4 = new Book("d", "dd", 44);
        Book b5 = new Book("e", "ee", 55);
        List<Book> books = MyUtil.toList(b1, b2, b3, b4, b5);
        List<Book> toDelete = MyUtil.toList(b2, b4);

        List<Book> remains = ListUtils.removeAll(books, toDelete);
        for (Book book : remains) {
            System.out.println(book.getTitle());
        }
    }
}
