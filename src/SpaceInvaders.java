/**********************************************************
 * SpaceInvaders.java
 * 
 * Program represents the base code for a Space Invaders-
 * type computer game.  It provides the primary frame, game
 * logic, controls for input, controls for sound, and some
 * game objects.  The code will not function without the 
 * following classes:
 * 
 * 		- CreditsPanel.java
 * 		- CustomOptions.java
 * 		- InvaderIcon.java
 * 		- InvaderTimer.java
 * 		- SpeedTimer.java
 * 		- StarshipIcon.java
 * 		- StatsPanel.java
 * 
 * Permission to copy and use this code is granted for 
 * students in an educational capacity without intent for 
 * proprietary use or monetary gain. 
 * 
 * (C) Copyright 2015 Lance P. Rhodes, All rights reserved.
 **********************************************************/

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

public class SpaceInvaders extends JFrame 
{
	private static final long serialVersionUID = 1L;
	
	//GAME VARIABLES AND OBJECTS********************************************************************************************
	
	private int width = StatsPanel.getSkyWidth();								//Width of display screen (in pixels)
	private int height = StatsPanel.getSkyHeight();								//Height of display screen (in pixels)
	
	//TO BE CUSTOMIZABLE ITEMS
	private double passPercent = CustomOptions.getPassPercent();				//Percent required to pass a level
	private int invadersPerLevel = CustomOptions.getInvadersPerLevel();			//The number of invaders per level
	private int invaderFrequency = CustomOptions.getInvaderFrequency();			//Rate at which invaders are released (lower is more)
	
	private int shotCount = 0;													//Number of shots fired
	private int destroyCount = 0;												//Number of invaders destroyed
	private int score = 0;														//Player score
	private int level = 1;														//Tracks current level
	private int invaderCount = (level * invadersPerLevel);						//Determines number of invaders in level
	private boolean quit = false;												//Toggle to quit game
	
	private JLabel shotLabel = new JLabel("0");									//Display panel for shots fired
	private JLabel destroyLabel = new JLabel("0");								//Display panel for invaders destroyed
	private JLabel breachLabel = new JLabel("0");								//Display panel for count of breached invaders
	private JLabel remainLabel = new JLabel("" + (level * invadersPerLevel));	//Display panel for remaining invaders
	private JLabel scoreLabel = new JLabel("0");								//Display panel for the score
	private StatsPanel statsPanel = new StatsPanel(shotLabel,					//Display panel for game statistics 
			destroyLabel, breachLabel, scoreLabel, remainLabel);
	
	private SkyScape skyScape = new SkyScape();									//Main background
	private StarshipIcon starShip = new StarshipIcon();							//Player star-ship object
	
	private ArrayList<InvaderIcon> invaderList = new ArrayList<InvaderIcon>();	//Houses all invaders in play
	
	//SECTION 1: DISPLAY AND PANELS*****************************************************************************************
	
	//Constructor: creates the JFrame used to house the game
	public SpaceInvaders()
	{
		setSize(width, height);
		setTitle(CustomOptions.getTitle());
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		addWindowListener(new WindowAdapter()
		{ public void windowClosing(WindowEvent e)
			{ 
				int response = JOptionPane.showConfirmDialog(null, 
						"Are you sure you want to quit?", 
						"Quit", 
						JOptionPane.YES_NO_OPTION, 
						JOptionPane.PLAIN_MESSAGE);
				
				if (response == 0)
				{
					setVisible(false);
					quit = true;
				}
			}
		});
		
		setLayout(new BorderLayout());
		
		add(statsPanel, StatsPanel.getBorderLayout());
		revalidate();
		setVisible(true);
		
		try 
		{
			Thread.sleep(50);
		} 
		catch (InterruptedException e1) 
		{		
			e1.printStackTrace();
		}
				
		startInvasion();		
	}
	
	//Builds the label that displayed after each level
	private JPanel buildPassLabel() 
	{
		String endLevel = "Level " + level;
		
		boolean win = ((double)destroyCount / (level * invadersPerLevel) >= passPercent); 
		
		if (win)
			endLevel += " COMPLETE";
		else
			endLevel += " FAIL";
		
		JLabel levelLabel = new JLabel(endLevel);
		levelLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		if (!win)
			levelLabel.setForeground(Color.RED);
		
		JPanel levelPanel = new JPanel();
		levelPanel.setLayout(new GridLayout(2, 1));
		
		levelPanel.add(levelLabel);
		levelPanel.add(new JLabel(""));
		
		JPanel totalsPanel = new JPanel();
		totalsPanel.setLayout(new GridLayout(10, 2));
		
		JLabel invaderLabel = new JLabel("Invader Count");
		JLabel invaderCountLabel = new JLabel("" + (level * invadersPerLevel));
		invaderCountLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel destroyLabel = new JLabel("Invaders Destroyed");
		JLabel destroyCountLabel = new JLabel("" + destroyCount);
		destroyCountLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		
		int killPercent = (int)(((double)destroyCount / (level * invadersPerLevel)) * 100);
		
		JLabel percentLabel = new JLabel("Percentage");
		JLabel percentTotal = new JLabel(killPercent + "%");
		percentTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel killBonusLabel = new JLabel("Destroy Bonus");
		JLabel killBonusTotal = new JLabel("" + (CustomOptions.getDestroyBonus() * killPercent / 100));
		killBonusTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		
		score += (CustomOptions.getDestroyBonus() * killPercent /  100);
		
		JLabel shotLabel = new JLabel("Shots Fired");
		JLabel shotLabelCount = new JLabel("" + shotCount);
		shotLabelCount.setHorizontalAlignment(SwingConstants.RIGHT);

		int accuracyPercent = (int)(((double)destroyCount / shotCount) * 100);
		
		JLabel accuracyLabel = new JLabel("Accuracy");
		JLabel accuracyTotal = new JLabel(accuracyPercent + "%");
		accuracyTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel accuracyBonusLabel = new JLabel("Accuracy Bonus");
		JLabel accuracyBonusTotal = new JLabel("" + (CustomOptions.getAccuracyBonus() * accuracyPercent / 100));
		accuracyBonusTotal.setHorizontalAlignment(SwingConstants.RIGHT);

		score += (CustomOptions.getAccuracyBonus() * accuracyPercent /  100);
		
		JLabel scoreLabel = new JLabel();
		
		if (win)
			scoreLabel.setText("Current Score");
		else
			scoreLabel.setText("Final Score");
		
		JLabel scoreTotal = new JLabel("" + score);
		scoreTotal.setHorizontalAlignment(SwingConstants.RIGHT);

		totalsPanel.add(invaderLabel);
		totalsPanel.add(invaderCountLabel);
		
		totalsPanel.add(destroyLabel);
		totalsPanel.add(destroyCountLabel);
		
		totalsPanel.add(percentLabel);
		totalsPanel.add(percentTotal);
		
		totalsPanel.add(killBonusLabel);
		totalsPanel.add(killBonusTotal);
		
		totalsPanel.add(new JLabel(""));
		totalsPanel.add(new JLabel(""));
		
		totalsPanel.add(shotLabel);
		totalsPanel.add(shotLabelCount);
		
		totalsPanel.add(accuracyLabel);
		totalsPanel.add(accuracyTotal);
		
		totalsPanel.add(accuracyBonusLabel);
		totalsPanel.add(accuracyBonusTotal);

		totalsPanel.add(new JLabel(""));
		totalsPanel.add(new JLabel(""));
		
		totalsPanel.add(scoreLabel);
		totalsPanel.add(scoreTotal);
		
		Border border = BorderFactory.createRaisedBevelBorder();
		Border margin = new EmptyBorder(10, 10, 10, 10);
		totalsPanel.setBorder(new CompoundBorder(border, margin));
		
		totalsPanel.setBackground(Color.WHITE);
		
		JPanel endPanel = new JPanel();
		endPanel.setLayout(new BorderLayout());
		endPanel.add(levelPanel, BorderLayout.NORTH);
		endPanel.add(totalsPanel, BorderLayout.CENTER);
		
		return endPanel;
	}

	//Resets the game for the next level
	public void resetGame()
	{
		skyScape = new SkyScape();
		shotCount = 0;
		destroyCount = 0;
		invaderCount = (level * invadersPerLevel);
		
		invaderFrequency = CustomOptions.getInvaderFrequency();
		SpeedTimer.resetVelocity();
		
		shotLabel.setText("0");
		destroyLabel.setText("0");
		breachLabel.setText("0");
		scoreLabel.setText("" + score);
		remainLabel.setText("" + (level * invadersPerLevel));
		
		invaderList.clear();
		statsPanel.revalidate();
	}
	
	public static void displayCredits()
	{
		JOptionPane.showMessageDialog(null, new CreditsPanel(), "Credits", JOptionPane.PLAIN_MESSAGE);
	}
	
	//SECTION 2: LOGIC AND METHODS******************************************************************************************

	//Adds the main game panel to JFrame and contains game repeat logic
	public void startInvasion()
	{
		GamePanel gamePanel = new GamePanel();
		add(gamePanel, BorderLayout.CENTER);
		revalidate();
		
		int playAgain = 0;
		
		do
		{
			remainLabel.setText("" + ((invadersPerLevel * level) 
					- Integer.parseInt(destroyLabel.getText()) 
					- Integer.parseInt(breachLabel.getText())));
			
			if (remainLabel.getText().equals("0"))
			{
				boolean win = ((double)destroyCount / (level * invadersPerLevel) >= passPercent);
				
				String[] options = {"", ""};
				
				if (win)
				{
					playSound("sounds/levelComplete.wav");
					options[0] = "Next Level";
					options[1] = "Quit";
				}
				else
				{
					playSound("sounds/levelFail.wav");
					options[0] = "Play Again";
					options[1] = "Quit";
				}
				
				playAgain = JOptionPane.showOptionDialog(null, 
						buildPassLabel(), 
						"End Level " + level, 
						JOptionPane.NO_OPTION, 
						JOptionPane.PLAIN_MESSAGE,
						null,
						options,
						options[0]);
				
				if (playAgain == 0)
				{
					if (win)
						level++;
					else
					{
						level = 1;
						score = 0;
					}
					remove(gamePanel);
					resetGame();
					gamePanel = new GamePanel();
					
					add(gamePanel, BorderLayout.CENTER);
					statsPanel.revalidate();
				}
			}
			delayThread(50);
		} while (playAgain == 0 && !quit);
		
		this.setVisible(false);
		displayCredits();
		System.exit(0);
	}
	
	//Game panel that displays the sky, launches invaders, and controls input
	public class GamePanel extends JPanel implements MouseMotionListener, MouseListener
	{
		private static final long serialVersionUID = 1L;
		
		int shipX;
		int shipY;
		
		public GamePanel()
		{
			setLayout(null);
			setSize(width, height);
			
			skyScape.addMouseMotionListener(this);
			skyScape.addMouseListener(this);
			add(skyScape);
			
			starShip.setLocation(width / 2, height - 250);
			skyScape.add(starShip);
			
			invaderThrower();
			SpeedTimer.startClock();
			
			revalidate();
		}

		public void mouseMoved(MouseEvent e) 
		{
			starShip.setLocation(e.getX() - 13, height - 250);
			repaint();
		}

		public void mousePressed(MouseEvent e) 
		{
			fireShot();
		}

		public void mouseDragged(MouseEvent e) {}
		public void mouseClicked(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}	
		public void mouseEntered(MouseEvent e) {}	
		public void mouseExited(MouseEvent e) {}	
	}

	//Logic to display and control shots
	public void fireShot()
	{
		playSound("sounds/playerWeapon.wav");

		shotLabel.setText("" + (++shotCount));
		
		Shot shot = new Shot(CustomOptions.getShotColor());
		skyScape.add(shot);
		
		Timer shotTimer = new Timer();
		
		TimerTask shotTask = new TimerTask()
		{

			double x = starShip.getX() + 12;
			double y = starShip.getY();
			boolean colorToggle = true;
			Color color;
			int ellapsed = 0;
			
			public void run() 
			{
				ellapsed++;
				y -= .5;
				
				if (colorToggle)
					color = CustomOptions.getShotColor();
				else
					color = Color.WHITE;
				
				if (ellapsed >= 100)
				{
					colorToggle = !colorToggle;
					ellapsed = 0;
				}
				
				shot.setColor(color);
				shot.setLocation((int)x, (int)y);
				repaint();
				
				if (y < -10)
					shotTimer.cancel();
				
				if (checkCollision((int)x, (int)y))
				{
					skyScape.remove(shot);
					shotTimer.cancel();
				}
			}
		};
		
		shotTimer.scheduleAtFixedRate(shotTask, 0, 1);
	}
	
	//Logic to check for shot collisions against invaders in play
	private boolean checkCollision(int x, int y) 
	{
		for (int i = 0; i < invaderList.size(); i++)
		{
			if ((y >= invaderList.get(i).getY() && y <= invaderList.get(i).getY() + invaderList.get(i).getInvaderSize()) &&
				(x >= invaderList.get(i).getX() && x <= invaderList.get(i).getX() + invaderList.get(i).getInvaderSize()))
			{
				destroyInvaderIcon(invaderList.get(i));
				return true;
			}
		}
		return false;
	}
	
	//Remove destroyed invader from game-play
	private void destroyInvaderIcon(InvaderIcon invader)
	{
		playSound("sounds/invaderKilled.wav");
		
		invaderList.remove(invader);
		destroyLabel.setText("" + (++destroyCount));
		
		score += CustomOptions.getPointsPerHit();
		scoreLabel.setText("" + score);
		
		boolean colorToggle = true;
		Color color = Color.WHITE;
		
		for (int j = 0; j < 10; j++)
		{
			if (colorToggle)
				color = Color.WHITE;
			else
				color = Color.GRAY;
			
			colorToggle = !colorToggle;
			
			invader.setColor(color);
			
			delayThread(25);			
		}
		
		invader.setInPlay(false);
		skyScape.remove(invader);
	}
	
	//SECTION 3: TIMERS AND ANIMATION***************************************************************************************

	//Throws new invader on the sky at regular intervals
	private void invaderThrower() 
	{
		Timer throwTimer = new Timer();
		
		TimerTask throwTask = new TimerTask()
		{
			public void run() 
			{
				if (invaderCount > 0)
				{
					playSound("sounds/invaderEnters.wav");
					InvaderTimer.throwInvaderIcon(invaderList, skyScape, breachLabel, SpeedTimer.getInvaderVelocity());
					invaderCount--;
				}
				else
					throwTimer.cancel();
			}
		};
		
		throwTimer.scheduleAtFixedRate(throwTask, 0, invaderFrequency);
	}
	
	//Tracks game time, used for increasing invader speed over time
	

	//SECTION 4: GAME OBJECTS***********************************************************************************************
	
	//Plain sky background
	public class SkyScape extends JPanel
	{
		private static final long serialVersionUID = 1L;

		public SkyScape()
		{
			this.setSize(new Dimension(width, height));
			this.setLayout(null);
		}
		
		public void paintComponent(Graphics g)
		{
			g.setColor(CustomOptions.getSkyColor());
			g.fillRect(0, 0, width, height);
		}
	}
	
	//Shot object
	public class Shot extends JPanel
	{
		private static final long serialVersionUID = 1L;

		Color color;
		
		public Shot(Color color)
		{
			this.color = color;
			setSize(2,2);
		}
		
		public void setColor(Color color) 
		{
			this.color = color;
		}

		public void paintComponent(Graphics g)
		{
			g.setColor(color);
			g.fillRect(0, 0, 2, 2);
		}
	}

	//SECTION 5: ADDITIONAL METHODS*****************************************************************************************

	//Plays game sounds
	public void playSound(String soundURL) 
	{
		try 
		{
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundURL).getAbsoluteFile());
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
	    } 
		catch(Exception e) 
		{
			System.out.println("Error with sound.");
			e.printStackTrace();
	    }
	}
	
	//Pauses thread of execution
	private void delayThread(int miliseconds) 
	{
		try 
		{
			Thread.sleep(miliseconds);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}
	
	//Main method
	public static void main(String[] args) 
	{
		new SpaceInvaders();
	}
}