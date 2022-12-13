/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.nhom4.reponse;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import poly.nhom4.domainmodel.ThongKee;
import poly.nhom4.repository.TKDTRepository;
import poly.nhom4.service.TKDTService;
import poly.nhom4.service.impl.TKDTServiceImpl;

/**
 *
 * @author Admin
 */
public class ThongKeReponse {

    private TKDTService ser = null;

    public ThongKeReponse() {

        ser = new TKDTServiceImpl();
    }

    public void Char1(JPanel jpanelItem1) {
        List<Object[]> list = ser.getAll();
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        if (list != null) {

            for (Object[] x : list) {
                ThongKee tk = new ThongKee();
                tk.setNgayTao((Date) x[0]);
               
                BigDecimal big = (BigDecimal) x[1];
                tk.setDoanhThu(big);
                Double db = tk.getDoanhThu().doubleValue();
                dataset.addValue(db, "Doanh thu", tk.getNgayTao());
            }
        }
        JFreeChart barChart = ChartFactory.createBarChart(
                "Biểu đồ thống kê doanh thu ".toUpperCase(),
                "Thời gian", "Doanh Thu",
                dataset, PlotOrientation.VERTICAL, false, true, false);
        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(jpanelItem1.getWidth(), 321));

        jpanelItem1.removeAll();
        jpanelItem1.setLayout(new CardLayout());
        jpanelItem1.add(chartPanel);
        jpanelItem1.validate();
        jpanelItem1.repaint();
    }
    public void Char2(List<Object[]> list,JPanel jpanelItem1) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        if (list != null) {

            for (Object[] x : list) {
                ThongKee tk = new ThongKee();
                tk.setNgayTao((Date) x[0]);
               
                BigDecimal big = (BigDecimal) x[1];
                tk.setDoanhThu(big);
                Double db = tk.getDoanhThu().doubleValue();
                dataset.addValue(db, "Doanh thu", tk.getNgayTao());
            }
        }
        JFreeChart barChart = ChartFactory.createBarChart(
                "Biểu đồ thống kê doanh thu ".toUpperCase(),
                "Thời gian", "Doanh Thu",
                dataset, PlotOrientation.VERTICAL, false, true, false);
        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(jpanelItem1.getWidth(), 321));

        jpanelItem1.removeAll();
        jpanelItem1.setLayout(new CardLayout());
        jpanelItem1.add(chartPanel);
        jpanelItem1.validate();
        jpanelItem1.repaint();
    }

}
