
import java.util.Timer;
import java.util.TimerTask;

public class SpeedTimer {
    private static int millisecondsForIncrease = 30000;
    private static double initialVelocity;
    private static double invaderVelocity;

    static {
        invaderVelocity = SpeedTimer.initialVelocity = 0.01;
    }

    public static void main(String[] args) {
        SpeedTimer.startClock();
        do {
            System.out.println("Current px change value: " + SpeedTimer.getInvaderVelocity());
            try {
                Thread.sleep(1);
                continue;
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (true);
    }

    public static void startClock() {
        Timer speedTimer = new Timer();
        TimerTask speedTimerTask = new TimerTask(){

            @Override
            public void run() {
                SpeedTimer.velocity(invaderVelocity + 0.01);
            }
        };
        speedTimer.scheduleAtFixedRate(speedTimerTask, 0, (long)millisecondsForIncrease);
    }

    public static double getInitialVelocity() {
        return initialVelocity;
    }

    public static double getInvaderVelocity() {
        return invaderVelocity;
    }

    public static void resetVelocity() {
        invaderVelocity = initialVelocity;
    }

    static void velocity(double d) {
        invaderVelocity = d;
    }

}

