package demo.commons;

import java.util.concurrent.Executors;

import org.apache.commons.lang3.concurrent.BackgroundInitializer;
import org.apache.commons.lang3.concurrent.ConcurrentException;
import org.apache.commons.lang3.concurrent.MultiBackgroundInitializer;
import org.apache.commons.lang3.concurrent.MultiBackgroundInitializer.MultiBackgroundInitializerResults;

public class MultiBackgroundInitializerDemo {
    
    public static void main(String[] args) {
        MultiBackgroundInitializer initializer = null;
        try {
            // 实际运行时的并发线程数，为传入参数减去1，因为要有一个线程用于监控运行状态
            initializer = new MultiBackgroundInitializer(Executors.newFixedThreadPool(3));
            int size = 3;
            for (int i = 1; i <= size; i++) {
                initializer.addInitializer(String.valueOf(i), new MyUrlLoader("url " + i, i * 1000));
            }
            long startTime = System.currentTimeMillis();
            initializer.start();
            
            MultiBackgroundInitializerResults results = initializer.get();
            System.out.println("time: " + (System.currentTimeMillis() - startTime) + " ms.");
            for (int i = 1; i <= size; i++) {
                System.out.println(results.getResultObject(String.valueOf(i)));
            }
        } catch (ConcurrentException e) {
            e.printStackTrace();
        } finally {
            if (initializer != null && initializer.getExternalExecutor() != null) {
                initializer.getExternalExecutor().shutdown(); // 注意：必须停止 ExecutorService，否则后台线程无法退出
            }
        }
    }
    
    public static class MyUrlLoader extends BackgroundInitializer<String> {
        
        private String url;
        private long sleepTime;
        
        public MyUrlLoader(String url, long sleepTime) {
            this.url = url;
            this.sleepTime = sleepTime;
        }
        
        @Override
        protected String initialize() throws Exception {
            System.out.println("start to sleep " + sleepTime);
            Thread.sleep(sleepTime);
            return "got : " + url;
        }
        
    }
    
}
