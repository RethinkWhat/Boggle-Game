package server.model;

public class GameTimer implements Runnable {

    private int gameID;

    final private long timerDuration;

    private long toEdit;

    public GameTimer(int gameID, long timerDuration) {
        this.gameID = gameID;
        this.timerDuration = timerDuration;
        toEdit = timerDuration;

    }

    @Override
    public void run() {
        while(toEdit > 0L) {
            toEdit -= 1000L;
            try {
                Thread.sleep(1000L);
            } catch (Exception var2) {
                var2.printStackTrace();
            }
        }
    }

    public long getCurrValue() {
        return timerDuration;
    }

    public void reset() {
        toEdit = timerDuration;
    }
}
