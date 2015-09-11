package demo.jfreechart;

import java.awt.Color;
import java.io.File;
import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

public class AreaChartDemo {
    
    private static final Log log = LogFactory.getLog(AreaChartDemo.class);
    
    public static void main(String[] args) {
        areaChart();
        log.info("done~");
    }
    
    public static void areaChart() {
        String title = "title";
        String categoryAxisLabel = "label";
        String valueAxisLabel = "value";
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        // dataset.addValue(1, "row1", "a1");
        // dataset.addValue(4, "row1", "a2");
        // dataset.addValue(5, "row1", "a3");
        //
        // dataset.addValue(3, "row2", "a1");
        // dataset.addValue(1, "row2", "a2");
        // dataset.addValue(7, "row2", "a3");
        //
        // dataset.addValue(2, "row3", "a1");
        // dataset.addValue(9, "row3", "a2");
        // dataset.addValue(6, "row3", "a3");
        
        dataset.addValue(5, "row3", "a1");
        dataset.addValue(6, "row3", "a2");
        dataset.addValue(8, "row3", "a3");
        
        dataset.addValue(7, "row2", "a1");
        dataset.addValue(5, "row2", "a2");
        dataset.addValue(9, "row2", "a3");
        
        dataset.addValue(2, "row1", "a1");
        dataset.addValue(3, "row1", "a2");
        dataset.addValue(2, "row1", "a3");
        
        JFreeChart chart = ChartFactory.createAreaChart(title, categoryAxisLabel, valueAxisLabel, dataset,
                PlotOrientation.VERTICAL, true, true, false);
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setForegroundAlpha(0.5f);
        CategoryItemRenderer renderer = plot.getRenderer();
        renderer.setSeriesPaint(0, Color.magenta);
        renderer.setSeriesPaint(1, Color.blue);
        renderer.setSeriesPaint(2, Color.green);
        // renderer.setSeriesPaint(2, Color.magenta);
        // renderer.setSeriesPaint(1, Color.blue);
        // renderer.setSeriesPaint(0, Color.green);
        
        // 输出图片文件
        int width = 400;
        int height = 400;
        try {
            ChartUtilities.saveChartAsJPEG(new File("/home/b.jpeg"), chart, width, height);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
