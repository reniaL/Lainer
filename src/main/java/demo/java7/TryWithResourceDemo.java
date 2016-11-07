package demo.java7;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import util.JakeReader;

public class TryWithResourceDemo {

    private static final Logger log = LoggerFactory.getLogger(TryWithResourceDemo.class);

    public static void main(String[] args) {
        try {
//             testTry();
            testTryWithResources();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    // 最终会抛出哪个异常？
    public static void testTry() {
        JakeReader reader = new JakeReader();
        try {
            reader.read();
        } finally {
            reader.close();
        }
    }

    public static void testTryWithResource() {
        try (JakeReader readerA = new JakeReader()) {
            readerA.read();
        }
    }

    public static void testTryWithResources() {
        try (JakeReader readerA = new JakeReader("a");
                JakeReader readerB = new JakeReader("b")) {
            readerA.read();
            readerB.read();
        }
    }

}
