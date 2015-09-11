package demo.jfreechart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

public class JFreeChartTest {
    
    public static void main(String[] args) {
        
        // create a dataset...
        DefaultPieDataset data = new DefaultPieDataset();
        data.setValue("Category 1", 43.2);
        data.setValue("Category 2", 27.9);
        data.setValue("Category 3", 79.5);
        
        // create a chart...
        JFreeChart chart = ChartFactory.createPieChart("Sample Pie Chart", data, true, // legend?
                true, // tooltips?
                false // URLs?
                );
        
        // create and display a frame...
        ChartFrame frame = new ChartFrame("First", chart);
        frame.pack();
        frame.setVisible(true);
    }
    
    public static void pieChartTest() {
        // 创建饼图数据对象
        DefaultPieDataset dfp = new DefaultPieDataset();
        dfp.setValue("管理人员", 25);
        dfp.setValue("市场人员", 35);
        dfp.setValue("developer", 20);
        dfp.setValue("后勤人员", 5);
        dfp.setValue("财务人员", 15);
        
        // Create JFreeChart object
        JFreeChart a = ChartFactory.createPieChart("CityInfoPort公司组织架构图", dfp, true, true, true);
        ChartFrame frame = new ChartFrame("CityInfoPort公司组织架构图 ", a, true);
        frame.pack();
        frame.setVisible(true);
    }
}