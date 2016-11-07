package util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JakeReader implements AutoCloseable {

    private static final Logger log = LoggerFactory.getLogger(JakeReader.class);

    private String name;
    private boolean readException;
    private boolean closeException;

    public JakeReader() {
        this("jake", true, true);
    }

    public JakeReader(String name) {
        this(name, true, true);
    }

    public JakeReader(String name, boolean readException, boolean closeException) {
        log.info("init reader: " + name);
        this.name = name;
        this.readException = readException;
        this.closeException = closeException;
    }

    public void read() {
        log.info("to read: " + name);
        if (readException) {
            throw new IllegalStateException("read exception: " + name);
        }
    }

    public void close() {
        log.info("to close: " + name);
        if (closeException) {
            throw new IllegalStateException("close exception: " + name);
        }
    }

}
