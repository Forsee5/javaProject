import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import java.awt.Color;
import java.util.Map;

public class Graphic extends JFrame {
    public Graphic(Map<String, Double> schools) {
        initUI(schools);
    }

    private void initUI(Map<String, Double> schools) {
        CategoryDataset dataset = createDataset(schools);

        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);
        add(chartPanel);

        pack();
        setTitle("Bar chart");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private CategoryDataset createDataset(Map<String, Double> schools) {
        var dataset = new DefaultCategoryDataset();
        schools.forEach((key, value) -> dataset.setValue(value, "country", key));
        return dataset;
    }

    private JFreeChart createChart(CategoryDataset dataset) {
        JFreeChart barChart = ChartFactory.createBarChart(
                "Average students",
                "",
                "Schools",
                dataset,
                PlotOrientation.VERTICAL,
                false, true, false);

        return barChart;
    }
}