package demo.java8.time;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class InstantDemo {
    
    public static void main(String[] args) {
        truncate();
    }
    
    private static void test() {
        Instant instant = Instant.now();
        System.out.println(instant);
        System.out.println("epoch: " + instant.getEpochSecond());
        System.out.println("nano: " + instant.getNano());
    
        Instant after = instant.plus(Duration.ofDays(2));
        System.out.println(after);
    }
    
    private static void plusAndMinus() {
        Instant now = Instant.now();
        System.out.println(now);
        
        Instant before = now.minus(Duration.ofDays(2));
        System.out.println(before);
        
        // java.time.temporal.UnsupportedTemporalTypeException: Unsupported unit: Months
        // can't add months or years, since duration of a month or year is NOT accurate!
//        Instant after = now.plus(Period.of(0, 11, 30));

        Instant after = now.plus(Duration.of(30, ChronoUnit.DAYS));
        System.out.println(after);
    }
    
    private static void truncate() {
        Instant now = Instant.now();
        Instant truncated = now.truncatedTo(ChronoUnit.HOURS);
        System.out.println(truncated); // 2019-06-06T10:00:00Z
    }
}
