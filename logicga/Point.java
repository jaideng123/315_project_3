package logicga;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

import javax.swing.JComponent;


public class Point extends JComponent {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4712402732099319063L;
	private double x;
	private double y;
	
	public Point(double x2, double y2) {
		x = x2;
		y = y2;
	}
	
	public void paintComponent(Graphics g)
    {  
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.fill(new Ellipse2D.Double(x,y,5,5));
        repaint();
    } 
	
}
