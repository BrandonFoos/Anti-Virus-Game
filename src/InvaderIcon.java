import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.RenderingHints;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class InvaderIcon extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static Graphics g;
		
	//TODO: set the width and hight to the desired icon size
	private int width = 100;
	private int height = 130;
	
	private boolean inPlay = true;
	
	public static void main(String[] args) throws HeadlessException
	{
		JOptionPane.showMessageDialog(null, new InvaderIcon(),
				"InvaderIcon Test",
				JOptionPane.PLAIN_MESSAGE);
		
	}
	
	public InvaderIcon() 
	{
		setSize(width,height);
		setPreferredSize(new Dimension(width, height+1));
		setOpaque(true);
		
		
	}
	
	public void setInPlay(boolean inPlay)
	{
		this.inPlay = inPlay;
		
	}
		
	public void setColor(Color color)
	{
		
	}
	
	public int getInvaderSize()
	{
		return this.width;
		
	}
	public void paintComponent(Graphics g)
	{
		Graphics2D graphics2D = (Graphics2D)g;
		
		//graphics2D.setColor(new Color(255,255,255));
		
		 graphics2D.setRenderingHint(
                 RenderingHints.KEY_ANTIALIASING, 
                 RenderingHints.VALUE_ANTIALIAS_ON);
		 
		graphics2D.setColor(new Color(137,200,116));
		 
		int[] x = {0,90,90,0};
		int[] y = {15,10,85,90};
		
		graphics2D.fillPolygon(x,y,4);
		
		graphics2D.setColor(Color.white);
		int[] x1 = {20,70,70,20};
		int[] y1 = {30,27,72,75};
		
		graphics2D.fillPolygon(x1,y1,4);
		
		//eye TODO: ANIMATE WITH FRAMES
		graphics2D.setColor(Color.black);
		int animateX = 0; 
		int animateY = 0;
		int[] x2 = {40 + animateX,70 + animateX,70 + animateX,40 + animateX};
		int[] y2 = {46 + animateY,44 + animateY,72 + animateY,74 + animateY};
		
		graphics2D.fillPolygon(x2,y2,4);
		
		//ears
		graphics2D.setColor(new Color(137,200,116));
		int[] x3 = {20,20,35,35};
		int[] y3 = {20,1,0,20};
		
		graphics2D.fillPolygon(x3,y3,4);
		
		int[] x4 = {60,60,75,75};
		int[] y4 = {20,1,0,20};
		
		graphics2D.fillPolygon(x4,y4,4);
	
		//body depth
		//int[] x = {0,90,90,0};
		//int[] y = {15,10,85,90};
		graphics2D.setColor(new Color(157,167,57));
		int[] x5 = {0,90,95,5};
		int[] y5 = {90,85,92,97};
		graphics2D.fillPolygon(x5, y5, 4);
		
		graphics2D.setColor(new Color(38,115,172));
		int[] x6 = {90,90,94,95};
		int[] y6 = {85,10,18,92};
		graphics2D.fillPolygon(x6, y6, 4);
		
		//ears depth
		
		//int[] x3 = {20,20,35,35};
		//int[] y3 = {20,1,0,20};
		
		//int[] x4 = {60,60,75,75};
		//int[] y4 = {20,1,0,20};
		
		int[] x7 = {35,37,37,35};
		int[] y7 = {0,2,14,13};
		
		graphics2D.fillPolygon(x7,y7,4);
		int[] x8 = {75,77,77,75};
		int[] y8 = {0,2,11,12};
		
		graphics2D.fillPolygon(x8,y8,4);
		
		
		
		//left foot
		graphics2D.setColor(new Color(137,200,116));
		int[] x9 ={20,32,32,30,30,18,18,20};
		int[] y9 ={93,92,105,105,117,117,105,105};
		graphics2D.fillPolygon(x9, y9, x9.length);
		
		// left foot shadow
		graphics2D.setColor(new Color(157,167,57));
		int[] x10 ={18,30,35,20};
		int[] y10 ={117,117,120,120};
		graphics2D.fillPolygon(x10, y10, x10.length);
		
		graphics2D.setColor(new Color(38,115,172));
		int[] x11 ={ 30, 30, 32, 32, 35,35};
		int[] y11 ={117,105,105, 92, 94,120};
		graphics2D.fillPolygon(x11, y11, x11.length);
		
		
		//left foot
		int xadj= 40;
		int yadj= -2;
		graphics2D.setColor(new Color(137,200,116));
		int[] x12 ={20 + xadj,32 + xadj,32 + xadj,30 + xadj,30+ xadj,18+ xadj,18 + xadj,20 + xadj};
		int[] y12 ={93 + yadj,92 + yadj,105 + yadj,105 + yadj,117 + yadj,117 + yadj,105 + yadj,105 + yadj};
		graphics2D.fillPolygon(x12, y12, x12.length);
				
		// left foot shadow
		graphics2D.setColor(new Color(157,167,57));
		int[] x13 ={18 + xadj,30 + xadj,35 + xadj,20 + xadj};
		int[] y13 ={117 + yadj,117 + yadj,120 + yadj,120 + yadj};
		graphics2D.fillPolygon(x13, y13, x1.length);
			
		graphics2D.setColor(new Color(38,115,172));
		int[] x14 ={ 30  + xadj, 30 + xadj, 32 + xadj, 32 + xadj, 35 + xadj,35 + xadj};
		int[] y14 ={117 + yadj,105 + yadj,105 + yadj, 92 + yadj, 94 + yadj,120 + yadj};
		graphics2D.fillPolygon(x14, y14, x14.length);
				
		/*
		//red
		g.fillRect(0, 0,10, 30);
		g.fillRect(30, 10, 10, 40);
		g.fillRect(30, 10, 30, 10);
		g.fillRect(50, 10, 10, 40);
		g.fillRect(30, 50, 30, 10);
		g.fillRect(30, 30, 30, 10);
		g.fillRect(80, 0, 10, 30);
		g.fillRect(10, 50, 10, 20);
		g.fillRect(70, 50, 10, 20);
		//blue
		g.setColor(new Color(69,140,203));
		g.fillRect(10, 10, 20, 10);
		g.fillRect(20, 20,10 , 10);
		
		g.fillRect(60, 10, 20, 10);
		g.fillRect(60, 20, 10, 10);
		
		g.fillRect(20, 50, 10, 10);
		
		g.fillRect(30, 60, 10, 20);
		g.fillRect(50, 60, 10, 20);
		
		g.fillRect(60, 50, 10, 10);
		
		//Yellow
		g.setColor(new Color(250,237,28));
		g.fillRect(40, 20, 10, 10);
		g.fillRect(40, 40, 10, 10);
		*/
		
	}
	public boolean isInPlay() {
        return this.inPlay;
    }
		

}
