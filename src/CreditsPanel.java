
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class CreditsPanel extends JPanel {
	private static final long serialVersionUID = 1;

	public static void main(String[] args) {
		JOptionPane.showMessageDialog(null, new CreditsPanel(), "Test Credits", -1);
	}

	public CreditsPanel() {
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.setBackground(Color.WHITE);
		JLabel TitleArea = new JLabel("Anti Virus - Version 1.0");
		TitleArea.setOpaque(false);
		TitleArea.setForeground(Color.GRAY);
		TitleArea.setHorizontalAlignment(0);
		JLabel ProgCredits = new JLabel("Game Programming & Icon Design");
		ProgCredits.setOpaque(false);
		ProgCredits.setForeground(Color.GRAY);
		ProgCredits.setHorizontalAlignment(0);
		JLabel ProgCredits2 = new JLabel("Brandon Foos");
		ProgCredits2.setOpaque(false);
		ProgCredits2.setForeground(Color.LIGHT_GRAY);
		ProgCredits2.setHorizontalAlignment(0);
		JLabel SoundCredits = new JLabel("Beta Testers");
		SoundCredits.setOpaque(false);
		SoundCredits.setForeground(Color.GRAY);
		SoundCredits.setHorizontalAlignment(0);
		JLabel SoundCredits2 = new JLabel("Ann Foos");
		SoundCredits2.setOpaque(false);
		SoundCredits2.setForeground(Color.LIGHT_GRAY);
		SoundCredits2.setHorizontalAlignment(0);
		JLabel SoundCredits3 = new JLabel(
				"Bella Foos");
		SoundCredits3.setOpaque(false);
		SoundCredits3.setForeground(Color.LIGHT_GRAY);
		SoundCredits3.setHorizontalAlignment(0);
		JLabel Completion = new JLabel("Completion Date");
		Completion.setOpaque(false);
		Completion.setForeground(Color.GRAY);
		Completion.setHorizontalAlignment(0);
		JLabel Completion2 = new JLabel("12/3/2016");
		Completion2.setOpaque(false);
		Completion2.setForeground(Color.LIGHT_GRAY);
		Completion2.setHorizontalAlignment(0);
		JLabel Copyright = new JLabel("Copyright Information:");
		Copyright.setOpaque(false);
		Copyright.setForeground(Color.GRAY);
		Copyright.setHorizontalAlignment(0);
		JLabel Copyright2 = new JLabel("© Foos, Brandon. all rights reserved.");
		Copyright2.setOpaque(false);
		Copyright2.setForeground(Color.LIGHT_GRAY);
		Copyright2.setHorizontalAlignment(0);
		this.setLayout(new GridLayout(11, 1));
		this.add(TitleArea);
		this.add(ProgCredits);
		this.add(ProgCredits2);
		this.add(SoundCredits);
		this.add(SoundCredits2);
		this.add(SoundCredits3);
		this.add(Completion);
		this.add(Completion2);
		this.add(Copyright);
		this.add(Copyright2);
	}
}
