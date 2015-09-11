package demo.jse;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class HttpClientDemo {
    
    private static final Log log = LogFactory.getLog(HttpClientDemo.class);
    
    public static void main(String[] args) {
        userAgentDemo();
    }
    
    /**
     * 设置自定义的 User-Agent
     */
    public static void userAgentDemo() {
        String uri = "http://news.163.com/11/0929/00/7F3373FP00014AED.html";
        HttpClient client = new HttpClient();
        HttpMethod method = new GetMethod(uri);
        method.addRequestHeader("User-Agent", "Python-httplib2/0.7.6 (gzip)");
        executeHttpMethod(client, method);
    }
    
    /**
     * 虚拟主机头例子
     */
    public static void virtualHostDemo() {
        String uri = "http://123.125.162.11/11/0929/00/7F3373FP00014AED.html";
        String hostname = "news.163.com";
        HttpClient client = new HttpClient();
        HttpMethod method = new GetMethod(uri);
        method.getParams().setVirtualHost(hostname); // 设置实际发送请求的 host
        executeHttpMethod(client, method);
    }
    
    /**
     * 执行 HttpMethod
     */
    private static void executeHttpMethod(HttpClient client, HttpMethod method) {
        try {
            int status = client.executeMethod(method);
            String content = method.getResponseBodyAsString();
            log.info("status: " + status);
            log.info("content: " + content);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
    
}
