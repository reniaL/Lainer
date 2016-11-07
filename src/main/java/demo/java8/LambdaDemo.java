package demo.java8;

import java.util.List;

import util.MyUtil;

public class LambdaDemo {

    public static void main(String[] args) {
         testThread();
         testThreadByLambda();
//        testList();
    }

    public static void testList() {
        List<Integer> list = MyUtil.toList(2, 5, 1, 3, 4);
        list.forEach(e -> System.out.println(e));
        // list.forEach(System.out::println);
    }

    public static void testThread() {
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                System.out.println("running~");
            }
        });
        t.start();
    }

    public static void testThreadByLambda() {
        Thread t = new Thread(() -> System.out.println("running~"));
        t.start();
    }

}
