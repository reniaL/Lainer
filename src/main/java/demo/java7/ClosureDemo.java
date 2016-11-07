package demo.java7;

public class ClosureDemo {

    public static void main(String[] args) throws InterruptedException {
        Thread t = testThread();
        System.out.println("thread created~");
        Thread.sleep(2000);
        System.out.println("thread starts");
        t.start();
    }

    private static Thread testThread() {
        String name = "hello";
        return new Thread(new Runnable() {

            @Override
            public void run() {
                System.out.println(name + " running~");
            }
        });
    }

}
