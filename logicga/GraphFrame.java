package logicga;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.*;


public class GraphFrame extends JFrame {

		/**
	 *
	 */
	private static final long serialVersionUID = 7287415224402617472L;
	private JButton startButton;
	private JPanel startPanel;
	private DrawPanel drawPanel;
	private JPanel countPanel;
	private int fitness;
	private int gen;
	Container contentPane;
	XYSeries series = new XYSeries("XYGraph");
	XYSeriesCollection dataset = new XYSeriesCollection();
	JFreeChart chart = ChartFactory.createXYLineChart(
			"Genetic Algorithm", // Title
			"Total Generations", // x-axis Label
			"Best Fitness", // y-axis Label
			dataset, // Dataset
			PlotOrientation.VERTICAL, // Plot Orientation
			true, // Show Legend
			true, // Use tooltips
			false // Configure chart to generate URLs?
	);
	ChartPanel cp = new ChartPanel(chart);
	private Vector<Integer> points = new Vector<Integer>();
	public GraphFrame(String title){
		super(title);
		dataset.addSeries(series);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    setBounds(0,0,screenSize.width, screenSize.height);

		setDefaultCloseOperation(EXIT_ON_CLOSE);



		setLayout(new BorderLayout());
		startButton = new JButton("Update Graph");
		startPanel = new JPanel();
		drawPanel = new DrawPanel();
		countPanel = new JPanel();
		//drawPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		JLabel name = new JLabel("Fitness Through the Generations",0);
		JLabel xLabel = new JLabel("Generations",0);
		JLabel yLabel = new JLabel("Fitness",0);

		countPanel.setLayout(new FlowLayout());
		final JTextArea genCount = new JTextArea("",1,10);



		//setContentPane(this);
		contentPane = getContentPane();

		drawPanel.add(name, BorderLayout.NORTH);
		contentPane.add(cp, BorderLayout.CENTER);
		contentPane.validate();
		//startPanel.add(startButton);


		gen = 0;


		setVisible(true);

	}

	public void drawGraph(int runs, int fitness){
				series.add(runs, fitness);
		contentPane.remove(cp);
				dataset = new XYSeriesCollection();
				dataset.addSeries(series);
				chart = ChartFactory.createXYLineChart(
						"Genetic Algorithm", // Title
						"Total Generations", // x-axis Label
						"Best Fitness", // y-axis Label
						dataset, // Dataset
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

	public void readFile(Vector<Integer> points, Scanner scan){
		while(scan.hasNext()) {

		    	int x = scan.nextInt();

				points.add(x);

		    }

	}

}
