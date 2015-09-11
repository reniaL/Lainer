package util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

/**
 * @author reniaL
 */
public class MyUtil {
    
    // SQL关键字
    private static final String[] SQL_KEYWORDS = { "select", "from", "as", "where", "order by", "group by", "asc",
            "desc", "join", "on", "max", "min", "sum", "avg", "limit", "update", "set", "and", "or", "date_add",
            "curdate", "interval", "day", "left", "right", "inner", "count" };
    
    /**
     * 获取客户端的真实IP
     */
    public static String getOriginallyAddr(HttpServletRequest request) {
        if (request == null) {
            return "";
        }
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null) {
            return request.getRemoteAddr();
        }
        if (ip.indexOf(",") != -1) {
            String newip = ip.substring(0, ip.indexOf(","));
            if (newip.substring(0, 3).equals("192")) {
                newip = ip.substring(0, ip.lastIndexOf(",") + 1);
            }
            return newip;
        }
        if (!isLicitIp(ip)) {
            return request.getRemoteAddr();
        }
        return ip;
    }
    
    /**
     * 将sql语句中的关键字转换为大写，其它转换为小写。
     */
    public static String formatSql(String sql) {
        String result = sql.toLowerCase();
        for (String keyword : SQL_KEYWORDS) {
            result = result.replaceAll("(\\b)" + keyword + "(\\b)", "$1" + keyword.toUpperCase() + "$2");
        }
        return result;
    }
    
    /**
     * 去除字符串中的非法XML字符
     */
    public static String stripNonValidXMLChars(String str) {
        if (str == null || "".equals(str)) {
            return str;
        }
        return str.replaceAll("[\\x00-\\x08\\x0b-\\x0c\\x0e-\\x1f]", "");
    }
    
    /**
     * 过滤指定的html标签（只去除标签，保留内容）
     * 
     * @param txt - 进行过滤的文本
     * @param tags - 要过滤的标签
     */
    public static String removeHtmlTag(String txt, String[] tags) {
        String result = txt;
        for (String tag : tags) { // 循环每个标签
            String[] regs = { "<" + tag + "\\s[^>]*>", "</" + tag + ">" }; // 包括开始标签和结束标签
            for (String reg : regs) {
                Pattern pattern = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
                Matcher matcher = pattern.matcher(result);
                while (matcher.find()) {
                    result = matcher.replaceAll("");
                }
            }
        }
        return result;
    }
    
    /**
     * 过滤指定的html标签（包括标签内容），不支持同标签嵌套
     * 
     * @param txt - 进行过滤的文本
     * @param tags - 要过滤的标签
     */
    public static String removeHtmlTagAndContent(String txt, String[] tags) {
        String result = txt;
        for (String tag : tags) { // 循环每个标签
            String reg = "<" + tag + "\\s[^>]*>([\\w\\W]*)?</" + tag + ">"; // 进行匹配的正则表达式
            Pattern pattern = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(result);
            while (matcher.find()) {
                result = matcher.replaceAll("");
            }
        }
        return result;
    }
    
    /**
     * 将数组转换为 List
     */
    public static <T> List<T> toList(T... arr) {
        List<T> list = new ArrayList<T>();
        for (T t : arr) {
            list.add(t);
        }
        return list;
    }
    
    /**
     * 验证是否有效IP
     */
    public static boolean isLicitIp(String ip) {
        if (ip == null || ip.length() == 0) {
            return false;
        }
        String regex = "^[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(ip);
        if (!m.find()) {
            return false;
        }
        return true;
    }
}
