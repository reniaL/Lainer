package demo.jfreechart;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Paint;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.time.Minute;
import org.jfree.data.time.TimeTableXYDataset;
import org.jfree.data.xy.XYDataset;

public class XYAreaChartDemo {
    
    public static void main(String[] args) {
        XYAreaChartDemo demo = new XYAreaChartDemo();
        demo.draw();
    }
    
    public void draw() {
        String title = "title";
        String xAxisLabel = "time";
        String yAxisLabel = "traffic";
        XYDataset dataset = getDataset();
        JFreeChart chart = ChartFactory.createXYAreaChart(title, xAxisLabel, yAxisLabel, dataset,
                PlotOrientation.VERTICAL, true, true, false);
        
        // 背景
        Paint bgPaint = new GradientPaint(50, 0, new Color(0xD1DEEF), 50, 700, new Color(0xFFFFFF));
        chart.setBackgroundPaint(bgPaint); // set chart bg
        chart.setBorderPaint(Color.GREEN);
        
        // 输出图片文件
        int width = 600;
        int height = 300;
        try {
            ChartUtilities.saveChartAsJPEG(new File("/home/b3.jpeg"), chart, width, height);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public XYDataset getDataset() {
        TimeTableXYDataset dataset = new TimeTableXYDataset();
        String seriesName = "traffic";
        Calendar cal = Calendar.getInstance();
        for (int i = 0; i < 288; i++) {
            if (i % 10 == 0) {
                dataset.add(new Minute(cal.getTime()), 0, seriesName);
            } else {
                dataset.add(new Minute(cal.getTime()), i * 2, seriesName);
            }
            cal.add(Calendar.MINUTE, 5);
        }
        return dataset;
    }
    
}
