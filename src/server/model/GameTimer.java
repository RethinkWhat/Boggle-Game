package server.model;

public class GameTimer implements Runnable {

    private int gameID;

    final private long timerDuration;

    private long currTimerValue;

    public GameTimer(int gameID, long timerDuration) {
        this.gameID = gameID;
        this.timerDuration = timerDuration;
        currTimerValue = timerDuration;

    }

    @Override
    public void run() {
        while(currTimerValue > 0L) {
            try {
                Thread.sleep(1000L);
                currTimerValue -= 1000L;
            } catch (Exception var2) {
                var2.printStackTrace();
            }
        }
    }

    public long getCurrTimerValue() {
        return currTimerValue;
    }

    public void reset() {
        currTimerValue = timerDuration;
    }
}
