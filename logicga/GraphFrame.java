package logicga;

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
	private LogicGA ga;
	private JButton startButton;
	private JPanel startPanel;
	private DrawPanel drawPanel;
	private JPanel countPanel;
	private int fitness;
	private int gen;
	private Vector<Integer> points = new Vector<Integer>();
	public GraphFrame(String title, LogicGA g){
		super(title);
		ga = g;
		
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
		Container contentPane = getContentPane();
	
		
		contentPane.add(startPanel,BorderLayout.NORTH);
		contentPane.add(drawPanel,BorderLayout.CENTER);
		drawPanel.add(name, BorderLayout.NORTH);
		contentPane.add(xLabel, BorderLayout.SOUTH);
		contentPane.add(yLabel, BorderLayout.WEST);
		contentPane.add(countPanel, BorderLayout.EAST);
		startPanel.add(startButton);
		countPanel.add(genCount,BorderLayout.CENTER);

		
		gen = 0;
		
		
		startButton.addActionListener(new ActionListener(){
			

			public void actionPerformed(ActionEvent e) {
				
				
				try {
					Scanner scanner = new Scanner(new File("Metadata.txt"));
					//while(true){
						
						readFile(points,scanner);
						
						
						for(int i = 0;i<points.size();i++){
							fitness = drawPanel.yOrigin() - (points.get(i)/drawPanel.yOrigin());
							drawPanel.plot(gen, fitness);
							System.out.println("("+gen+","+fitness+")");
							gen += 10;	
							repaint();
							setVisible(true);
						}
						ga.execute();
						
					//}
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			
		});
		
		setVisible(true);
			
	}
	
	public void incGen(int g){
		
		
	}
	
	public void readFile(Vector<Integer> points, Scanner scan){
		while(scan.hasNext()) {
	
		    	int x = scan.nextInt();
				
				points.add(x);
				
		    }  
		
	}	
	

	

	
	public static void main(String[] args) {
		LogicGA prog = new LogicGA();
		GraphFrame graph = new GraphFrame("Graph",prog);
		
	}

}
