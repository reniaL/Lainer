package demo.jse.nio;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

public class NioDemo {
    
    private static final Logger logger = Logger.getLogger(NioDemo.class);
    
    public static void main(String[] args) {
        int loop = 100;
        String sourcePath = "/data/temp/nio/source.mov";
        String targetPath = "/data/temp/nio/target.mov";
        long time1 = System.currentTimeMillis();
        for (int i = 0; i < loop; i++) {
            copyByNio(sourcePath, targetPath);
        }
        long time2 = System.currentTimeMillis();
    
        for (int i = 0; i < loop; i++) {
            copyByTraditional(sourcePath, targetPath);
            if (i % 10 == 0) {
                logger.info("done " + i);
            }
        }
        long time3 = System.currentTimeMillis();
        logger.info(String.format("nio: %s, traditional: %s", (time2 - time1), (time3 - time2)));
    }
    
    public static void copyByTraditional(String sourcePath, String targetPath) {
        try {
            FileUtils.copyFile(new File(sourcePath), new File(targetPath), false);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }
    
    public static void copyByNio(String sourcePath, String targetPath) {
        long start = System.currentTimeMillis();
        try {
            FileChannel source = new RandomAccessFile(sourcePath, "r").getChannel();
            FileChannel target = new RandomAccessFile(targetPath, "rw").getChannel();
            long position = 0;
            long count = source.size();
            source.transferTo(position, count, target);
            
            source.close();
            target.close();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
//        logger.info("time: " + (System.currentTimeMillis() - start) + " ms.");
    }
}
