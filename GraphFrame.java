import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	public GraphFrame(String title){
		super(title);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    setBounds(0,0,screenSize.width, screenSize.height);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);	
		
		
		
		setLayout(new BorderLayout());
		startButton = new JButton("Run Algorithm");
		startPanel = new JPanel();
		drawPanel = new DrawPanel();
		countPanel = new JPanel();
		//drawPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		JLabel name = new JLabel("Fitness Through the Generations",0);
		JLabel xLabel = new JLabel("Generations",0);
		JLabel yLabel = new JLabel("Fitness",0);
		
		countPanel.setLayout(new FlowLayout());
		JTextArea genCount = new JTextArea("",1,10);
		
		
		
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
		fitness = (screenSize.height*7)/8;
		
		startButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				
				genCount.setText(drawPanel.plot(gen, fitness));
				gen++;
				fitness--;
				repaint();
				setVisible(true);
			}
			
		});
		
		setVisible(true);
			
	}
	
	public void incGen(int g){
		
		
	}
	
	
	
	public static void main(String[] args) {
		GraphFrame graph = new GraphFrame("Graph");
		
	}

}
