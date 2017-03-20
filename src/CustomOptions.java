
import java.awt.Color;

public class CustomOptions {
    private static String title = "Anti Virus";
    private static Color skyColor = new Color(0,0,0);
    private static Color shotColor = Color.DARK_GRAY;
    private static double passPercent = 0.7;
    private static int invadersPerLevel = 6;
    private static int invaderFrequency = 750;
    private static int pointsPerHit = 10;
    private static int destroyBonus = 100;
    private static int accuracyBonus = 100;

    public static String getTitle() {
        return title;
    }

    public static Color getSkyColor() {
        return skyColor;
    }

    public static Color getShotColor() {
        return shotColor;
    }

    public static double getPassPercent() {
        return passPercent;
    }

    public static int getInvaderFrequency() {
        return invaderFrequency;
    }

    public static int getPointsPerHit() {
        return pointsPerHit;
    }

    public static int getDestroyBonus() {
        return destroyBonus;
    }

    public static int getAccuracyBonus() {
        return accuracyBonus;
    }

    public static int getInvadersPerLevel() {
        return invadersPerLevel;
    }
}

