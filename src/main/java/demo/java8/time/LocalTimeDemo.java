package demo.java8.time;

import java.time.Duration;
import java.time.LocalTime;

public class LocalTimeDemo {
    
    public static void main(String[] args) {
        plusAndMinus();
    }
    
    private static void test() {
        System.out.println(LocalTime.MIDNIGHT); // 00:00
        System.out.println(LocalTime.MAX); // 23:59:59.999999999
        System.out.println(LocalTime.of(12, 34, 56)); // 12:34:56
    
        LocalTime time = LocalTime.now();
        System.out.println(time);
        System.out.println(time.getHour());
        System.out.println(time.getMinute());
        
        // exception
//        System.out.println(LocalTime.of(32, 34, 56));
    }
    
    private static void plusAndMinus() {
        LocalTime now = LocalTime.now();
    
        LocalTime before = now.minusHours(30);
        System.out.println(before);
    
        LocalTime after = now.plus(Duration.ofMinutes(123));
        System.out.println(after);
    
        // java.time.temporal.UnsupportedTemporalTypeException: Unsupported unit: Days
//        now.plus(5, ChronoUnit.DAYS);
    }
}
