package programmingch2;
import java.util.TimerTask;
import java.util.logging.Logger;
import java.util.logging.Level;

public class GameTimer extends TimerTask {

    private int timerVal;

    @Override
    public void run() {

        try {
            this.runTimer();
        } catch (InterruptedException e) {
            Logger.getLogger(GameTimer.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void runTimer() throws InterruptedException {
        while (true) {
            this.timerVal += 1;
            Thread.sleep(1000);
        }
    }

    public int getTimerVal() {
        return timerVal;
    }
}
