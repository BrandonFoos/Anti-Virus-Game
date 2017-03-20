
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class StatsPanel
extends JPanel {
    private static final long serialVersionUID = 1;
    private static int skyWidth = 1000;
    private static int skyHeight = 750;
    private static String position = "South";

    public static void main(String[] args) {
        JPanel testMainPanel = new JPanel();
        testMainPanel.setBackground(Color.GRAY);
        testMainPanel.setPreferredSize(new Dimension(skyWidth, skyHeight));
        testMainPanel.setLayout(new BorderLayout());
        testMainPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        testMainPanel.add((Component)new StatsPanel(new JLabel("0"), new JLabel("0"), new JLabel("0"), new JLabel("0"), new JLabel("0")), position);
        JOptionPane.showMessageDialog(null, testMainPanel, "Test Display", -1);
    }

    public StatsPanel(JLabel shots, JLabel destroyed, JLabel breached, JLabel score, JLabel remain) {
        this.setLayout(new FlowLayout());
        this.setBackground(Color.LIGHT_GRAY);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.add(new JLabel("Shots Fired:"));
        this.add(shots);
        this.add(new JLabel("                    Kills:"));
        this.add(destroyed);
        this.add(new JLabel("                    Lost:"));
        this.add(breached);
        this.add(new JLabel("                    Points:"));
        this.add(score);
        this.add(new JLabel("                     Remaining:"));
        this.add(remain);
    }

    public static int getSkyWidth() {
        return skyWidth;
    }

    public static int getSkyHeight() {
        return skyHeight;
    }

    public static String getBorderLayout() {
        return position;
    }
}

