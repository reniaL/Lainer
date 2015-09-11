package demo.jfreechart;

import java.io.File;
import java.io.IOException;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

public class BarChartDemo {
    
    public static void main(String[] args) {
        barchart();
    }
    
    public static void barchart() {
        String title = "title";
        String categoryAxisLabel = "label";
        String valueAxisLabel = "value";
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i = 1; i <= 288; i++) {
            if (i % 10 == 0) {
                dataset.addValue(0, "row", "a" + i);
            } else {
                dataset.addValue(i, "row", "a" + i);
            }
        }
        JFreeChart chart = ChartFactory.createBarChart(title, categoryAxisLabel, valueAxisLabel, dataset,
                PlotOrientation.VERTICAL, true, true, false);
        CategoryPlot cplot = chart.getCategoryPlot();
        // cplot.setDomainGridlinesVisible(false);
        BarRenderer renderer = new BarRenderer();
        renderer.setShadowVisible(false); // 不显示阴影
        cplot.setRenderer(renderer);
        
        // 输出图片文件
        int width = 600;
        int height = 300;
        try {
            ChartUtilities.saveChartAsJPEG(new File("/home/a2.jpeg"), chart, width, height);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
