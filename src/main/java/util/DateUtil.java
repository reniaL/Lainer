package util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    
    public static final String FORMAT_DATE_NORMAL = "yyyy-MM-dd";
    
    public static final String FORMAT_DATE_SHORT = "yyyyMMdd";
    
    public static final String FORMAT_DATETIME_NORMAL = "yyyy-MM-dd HH:mm:ss";
    
    public static final String FORMAT_DATETIME_SHORT = "yyyyMMddHHmmss";
    
    /**
     * 将日期格式化为字符串
     * @return 出错时返回 null
     */
    public static String formatDate(Date date, String pattern) {
        String result;
        try {
            DateFormat df = new SimpleDateFormat(pattern);
            result = df.format(date);
        } catch (Exception e) {
            result = null;
        }
        return result;
    }
    
    /**
     * 将日期格式化为字符串，格式为 yyyy-MM-dd
     * @return 出错时返回 null
     */
    public static String formatDateNormal(Date date) {
        return formatDate(date, FORMAT_DATE_NORMAL);
    }
    
    /**
     * 将字符串转换为日期
     * 
     * @param str 进行转换的字符串
     * @param pattern 日期格式
     * @return 转换出错则返回 null
     */
    public static Date parseDate(String str, String pattern) {
        Date d;
        try {
            DateFormat df = new SimpleDateFormat(pattern);
            d = df.parse(str);
        } catch (Exception e) {
            d = null;
        }
        return d;
    }
    
    /**
     * 将字符串转换为日期，格式为 yyyy-MM-dd
     * 
     * @return 转换出错则返回 null
     */
    public static Date parseDateNormal(String str) {
        return parseDate(str, FORMAT_DATE_NORMAL);
    }
    
    public static Date parseDateTimeNormal(String str) {
        return parseDate(str, FORMAT_DATETIME_NORMAL);
    }
    
    public static DateFormat getFormatDateTimeNormal() {
        return new SimpleDateFormat(FORMAT_DATETIME_NORMAL);
    }
    
}
