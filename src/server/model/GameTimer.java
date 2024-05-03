package server.model;

public class GameTimer implements Runnable {

    private int id;

    final private long timerDuration;

    private long currTimerValue;

    public GameTimer(int id, long timerDuration) {
        this.id = id;
        this.timerDuration = timerDuration;
        currTimerValue = timerDuration;

    }

    @Override
    public void run() {
        while(currTimerValue > 0L) {
            try {
                Thread.sleep(1000L);
                currTimerValue -= 1000L;
                System.out.println("CURR TIME VALUE: " + currTimerValue);
            } catch (Exception var2) {
                var2.printStackTrace();
            }
        }
    }

    public long getCurrTimerValue() {
        return currTimerValue;
    }

    public int getID() {
        return id;
    }

    public void reset() {
        currTimerValue = timerDuration;
    }
}
