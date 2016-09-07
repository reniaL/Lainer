package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Map.Entry;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class HttpUtil {
    
    private static final Log log = LogFactory.getLog(HttpUtil.class);
    
    public static void main(String[] args) {
        System.out.println(request("http://fanfou.com", null, null));
    }
    
    public static String request(String uri, Map<String, String> params, Map<String, String> headers) {
        HttpClient client = new HttpClient();
        HttpMethod method = new GetMethod(uri);
        if (params != null) {
            method.setQueryString(mapToNameValuePairs(params));
        }
        if (headers != null) {
            for (Entry<String, String> entry : headers.entrySet()) {
                method.setRequestHeader(entry.getKey(), entry.getValue());
            }
        }
        
        try {
            client.executeMethod(method);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream()));
            StringBuffer buffer = new StringBuffer();
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                buffer.append(line);
            }
            return buffer.toString();
        } catch (HttpException e) {
            log.error(e.getMessage(), e);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        
        return null;
    }
    
    public static JSONObject requestJson(String uri, Map<String, String> params, Map<String, String> headers) {
        return JSONObject.fromObject(request(uri, params, headers));
    }
    
    private static NameValuePair[] mapToNameValuePairs(Map<String, String> params) {
        NameValuePair[] pairs = new NameValuePair[params.size()];
        int i = 0;
        for (Entry<String, String> entry : params.entrySet()) {
            pairs[i] = new NameValuePair(entry.getKey(), entry.getValue());
            i++;
        }
        return pairs;
    }
    
}
