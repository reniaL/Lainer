package demo.java7;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import util.JakeReader;

public class TryWithResourceDemo {
    
    private static final Log log = LogFactory.getLog(JakeReader.class);

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
