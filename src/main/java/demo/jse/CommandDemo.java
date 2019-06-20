package demo.jse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

public class CommandDemo {
    
    private static final Logger logger = Logger.getLogger(CommandDemo.class);
    
    public static void main(String[] args) {
        runWithTimeout();
    }
    
    private static void runWithTimeout() {
        try {
            String command = "sh /data/scripts/test.sh";
            Runtime runtime = Runtime.getRuntime();
            Process process = runtime.exec(command);
            
            new Thread(() -> {
                try {
                    String processStr = process.toString();
                    if (!process.waitFor(5, TimeUnit.SECONDS)) {
                        logger.info("to kill " + processStr);
                        process.destroyForcibly();
                    } else {
                        logger.info("process exit normally. " + processStr);
                    }
                } catch (InterruptedException e) {
                    logger.error(e.getMessage(), e);
                }
            }).start();
    
            try (BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = br.readLine()) != null) {
                    logger.info(" -----------------start----------------------------");
                    logger.info(line);
                    Thread.sleep(1000);
                }
            }
    
        } catch (IOException | InterruptedException e) {
            logger.error(e.getMessage(), e);
        }
    
    }
    
    
}
