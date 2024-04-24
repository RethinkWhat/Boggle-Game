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
            try {
                System.out.println(toEdit);
                Thread.sleep(1000L);
                toEdit -= 1000L;
            } catch (Exception var2) {
                var2.printStackTrace();
            }
        }
    }

    public long getCurrValue() {
        return toEdit;
    }

    public void reset() {
        toEdit = timerDuration;
    }
}
