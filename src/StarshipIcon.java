import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class StarshipIcon extends JPanel
{
	private static final long serialVersionUID = 1l;
	
	private int height = 160;
	private int width = 100;
	
	public static void main(String[] args)
	{
		JOptionPane.showMessageDialog(null, new StarshipIcon(), "Starship test",
				JOptionPane.PLAIN_MESSAGE);
		
	}
	
	public StarshipIcon()
	{
		setSize(width, height);
		setOpaque(true);
		
	}
	public void paintComponent(Graphics g)
	{
		g.setColor(new Color(255,255,255));

		g.setColor(new Color(0,0,0));
		g.fillRect(1, 0, 98, 1);
		g.fillRect(0, 1, 2, 98);
		g.fillRect(99, 1, 2, 98);
		g.fillRect(1, 99, 98, 1);
		g.setColor(new Color(232,218,190));
		g.fillRect(1, 1, 98, 98);
		
		g.setColor(new Color(255,255,255));
		g.fillRect(2, 2, 96, 2);
		
		//screen
		g.setColor(new Color(0,0,0));
		g.fillRect(8, 6, 84, 84-15);
		
		g.setColor(new Color(232,218,190));
		g.fillRect(8, 6, 2, 2);
		g.fillRect(90, 6, 2, 2);
		g.setColor(new Color(255,255,255));
		g.fillRect(8, 88-15, 2, 2);
		g.fillRect(90, 88-15, 2, 2);
		g.fillRect(10, 90-15, 78, 2);
		
		//shadows
		g.setColor(new Color(167,159,140));
		g.fillRect(1, 82, 98, 5);
		
		//IO
		g.setColor(new Color(129,122,108));
		g.fillRect(25, 82, 50, 12);
		g.setColor(Color.white);
		g.fillRect(28, 85, 5, 4);
		g.fillRect(30, 87, 16, 2);
		g.setColor(new Color(146,41,141));
		g.fillRect(50, 84, 12, 2);
		g.setColor(new Color(236,32,39));
		g.fillRect(50, 86, 12, 2);
		g.setColor(new Color(252,238,33));
		g.fillRect(50, 88, 12, 2);
		g.setColor(new Color(142,199,64));
		g.fillRect(50, 90, 12, 2);
		g.setColor(new Color(142,199,64));
		g.fillRect(50, 90, 12, 2);
		
		g.setColor(new Color(0,0,0));
		g.fillRect(65, 84, 4, 8);
		
		//shadow
		g.setColor(new Color(129,122,108));
		g.fillRect(11, 100, 79, 8);
		g.setColor(Color.BLACK);
		g.fillRect(10, 100, 2, 6);
		g.fillRect(90, 100, 2, 6);
		g.fillRect(12, 106, 78, 2);
		
		//Keyboard fill
		g.setColor(new Color(232,218,190));
		g.fillRect(3, 116, 96, 40);
		
		
		//Keyboard border
		g.setColor(new Color(0,0,0));
		
		g.fillRect(2,115,96,1);
		g.fillRect(0,116,2,40);
		g.fillRect(98,116,2,40);
		g.fillRect(2, 156, 97,1);
		
		g.setColor(new Color(129,122,108));
		g.fillRect(8, 120, 60, 8);
		
		//f keys
		g.setColor(Color.white);
		g.fillRect(10, 122, 4, 4);
		g.fillRect(16, 122, 4, 4);
		g.fillRect(22, 122, 4, 4);
		g.fillRect(28, 122, 4, 4);
		g.fillRect(34, 122, 4, 4);
		g.fillRect(40, 122, 4, 4);
		g.fillRect(46, 122, 4, 4);
		g.fillRect(54, 122, 4, 4);
		g.fillRect(60, 122, 4, 4);
		
		//key background
		g.setColor(new Color(148,136,133 ));
		g.fillRect(10, 132, 70, 20);
		//key outline
		g.setColor(new Color(0,0,0));
		g.fillRect(8, 132, 2, 20);
		g.fillRect(10, 130, 70, 2);
		g.fillRect(80, 132, 2, 20);
		g.fillRect(10, 152, 70, 2);
		g.fillRect(10, 136, 70, 2);
		g.fillRect(10, 142, 70, 2);
		g.fillRect(10, 148, 70, 2);
		
		g.fillRect(14, 130, 2, 20);
		g.fillRect(20, 130, 2, 20);
		g.fillRect(26, 130, 2, 20);
		g.fillRect(32, 130, 2, 20);
		g.fillRect(38, 130, 2, 20);
		g.fillRect(44, 130, 2, 20);
		g.fillRect(50, 130, 2, 20);
		g.fillRect(56, 130, 2, 20);
		g.fillRect(62, 130, 2, 20);
		g.fillRect(68, 130, 2, 20);
		g.fillRect(74, 130, 2, 20);
		
		
		
		
	}
}
