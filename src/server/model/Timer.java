package server.model;

public class Timer implements Runnable {

    private int id;

    final private long timerDuration;

    private long currTimerValue;

    public Timer(int id, long timerDuration) {
        this.id = id;
        this.timerDuration = timerDuration;
        currTimerValue = timerDuration;

    }

    @Override
    public void run() {
        currTimerValue = timerDuration;
        while(currTimerValue > 0L) {
            try {
                Thread.sleep(1000L);
                currTimerValue -= 1000L;
            } catch (Exception var2) {
                var2.printStackTrace();
            }
        }
        try {
            Thread.sleep(1000L);
            ServerImplementation.solveRoundPoints(id);
            Thread.sleep(1000L);
            DataPB.updateRoundWinner(id);
            Thread.sleep(1000L);
            ServerImplementation.defineNextRound(id);
            ServerImplementation.startTimerForRound(id);

        } catch (Exception e) {
            e.printStackTrace();
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
