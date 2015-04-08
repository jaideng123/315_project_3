package logicga;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import java.awt.*;
import javax.swing.*;


public class GraphFrame extends JFrame {

	Container contentPane;
	XYSeries series = new XYSeries("XYGraph");
	XYSeriesCollection dataSet = new XYSeriesCollection();
	JFreeChart chart;
	ChartPanel cp = new ChartPanel(chart);
	String xAxis;
	String yAxis;
	String title;

	public GraphFrame(String t, String x, String y){
		title = t;
		xAxis = x;
		yAxis = y;
		chart = ChartFactory.createXYLineChart(
				title, // Title
				xAxis, // x-axis Label
				yAxis, // y-axis Label
				dataSet, // Dataset
				PlotOrientation.VERTICAL, // Plot Orientation
				true, // Show Legend
				true, // Use tooltips
				false // Configure chart to generate URLs?
		);
		dataSet.addSeries(series);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    setBounds(0,0,screenSize.width, screenSize.height);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		contentPane = getContentPane();
		contentPane.add(cp, BorderLayout.CENTER);
		contentPane.validate();
		setVisible(true);
	}

	public void drawGraph(int runs, int fitness){
        series.add(runs, fitness);
        contentPane.remove(cp);
        dataSet = new XYSeriesCollection();
        dataSet.addSeries(series);
        chart = ChartFactory.createXYLineChart(
                title, // Title
                xAxis, // x-axis Label
                yAxis, // y-axis Label
                dataSet, // Dataset
                PlotOrientation.VERTICAL, // Plot Orientation
                true, // Show Legend
                true, // Use tooltips
                false // Configure chart to generate URLs?
        );
        cp = new ChartPanel(chart);
        contentPane.add(cp, BorderLayout.CENTER);
        contentPane.validate();
        repaint();
        setVisible(true);
	}
}
