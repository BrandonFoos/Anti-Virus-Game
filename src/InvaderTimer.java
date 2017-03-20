
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class InvaderTimer {
	public static void main(String[] args) throws InterruptedException {
		JPanel testPanel = new JPanel();
		testPanel.setPreferredSize(new Dimension(StatsPanel.getSkyWidth(), StatsPanel.getSkyHeight()));
		JFrame testFrame = new JFrame();
		testFrame.setTitle("Invader Motion Test");
		testFrame.setSize(new Dimension(StatsPanel.getSkyWidth(), StatsPanel.getSkyHeight()));
		testFrame.setDefaultCloseOperation(3);
		testFrame.setLocationRelativeTo(null);
		testFrame.add(testPanel);
		testFrame.setVisible(true);
		try {
			Thread.sleep(50);
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
		InvaderTimer.throwInvaderIcon(new ArrayList<InvaderIcon>(), testPanel, new JLabel("0"),
				SpeedTimer.getInitialVelocity());
	}

	public static void throwInvaderIcon(final ArrayList<InvaderIcon> invaderList, JPanel skyscape,
			final JLabel breachLabel, double velocity) {
		final InvaderIcon invader = new InvaderIcon();
		invader.setLocation(-100, -100);
		invaderList.add(invader);
		skyscape.add(invader);
		final Timer InvaderTimer2 = new Timer();
		TimerTask InvaderTask = new TimerTask() {
			int setX = (int) (Math.random() * ((StatsPanel.getSkyWidth() - 100) - 100) + 100);
			double y;

			@Override
			public void run() {
				this.y += velocity;

				invader.setLocation(setX, (int) this.y);
				if (!invader.isInPlay()) {
					InvaderTimer2.cancel();
				}
				if (this.y > (double) (StatsPanel.getSkyHeight() + 10)) {
					invaderList.remove(invader);
					breachLabel.setText("" + (Integer.parseInt(breachLabel.getText()) + 1));
					InvaderTimer2.cancel();
				}
			}
		};
		InvaderTimer2.schedule(InvaderTask, 0, 3);
	}

}
