package demo.jse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Demo {
    
    private String startTime;
    private String endTime;
    
    public static void main(String[] args) {
        Demo demo = new Demo();
        String[] startTimes = { "2011-07-31", "2011-06-01", "2011-06-30", "2000-02-29" };
        Integer[] periods = { 1, 2, 3 };
        for (String startTime : startTimes) {
            for (Integer period : periods) {
                demo.test(startTime, null, period);
            }
        }
    }
    
    public void test(String startTime, String endTime, Integer period) {
        this.startTime = startTime;
        this.endTime = endTime;
        timeArea(period);
        System.out.println(startTime + ", " + period + ": " + this.startTime + ", " + this.endTime);
    }
    
    private void timeArea(Integer cyclicality) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(formatter.parse(startTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (cyclicality == 1) {
            endTime = startTime;
        } else if (cyclicality == 2) {
            int curDay = calendar.get(Calendar.DAY_OF_WEEK);
            if (calendar.get(Calendar.DAY_OF_WEEK) != 1) {
                calendar.add(Calendar.DATE, 8 - curDay);
            }
            endTime = formatter.format(calendar.getTime());
            calendar.add(Calendar.DATE, -6);
            startTime = formatter.format(calendar.getTime());
        } else if (cyclicality == 3) {
            calendar.add(Calendar.MONTH, 1);
            calendar.set(Calendar.DATE, 0);
            endTime = formatter.format(calendar.getTime());
            calendar.set(Calendar.DATE, 1);
            startTime = formatter.format(calendar.getTime());
        }
    }
    
}
