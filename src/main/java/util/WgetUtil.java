package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLConnection;

/**
 * @author reniaL
 */
public class WgetUtil {
    
    /**
     * 通过URLConnection抓取网页内容(纯文本，不包含换行)，此方法兼容HTTP和FTP协议
     */
    public static String wgetTxt(URLConnection conn) {
        StringBuffer document = new StringBuffer();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = null;
            while ((line = reader.readLine()) != null) {
                document.append(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return document.toString();
    }
}
