package logicga;

import java.awt.*;

import javax.swing.*;


public class DrawPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4744334222787515638L;
	private int xaxis;
	private int xoffset;
	private int yoffset;

	public DrawPanel(){
		setLayout(new BorderLayout());
		setSize(screenSize().width,screenSize().height);
		xaxis = getSize().width;
		yoffset = (getSize().height*7)/8;
		
	}
	
	public int xOrigin(){
		
		return 0;
	}
	public int yOrigin(){
		//System.out.println((getSize().height*7)/8);
		return (getSize().height*6)/7;
	}
	public Dimension screenSize(){
		
		return Toolkit.getDefaultToolkit().getScreenSize();
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawLine(0, yoffset, xaxis, yoffset);
		System.out.println(yoffset);
		g.drawLine(0, yoffset ,0,0);
	}
	public String plot(int gen, int fitness){
		
		this.add(new Point(gen,fitness));
		String count = Integer.toString(gen);
		return count;
		
	}
	
}
