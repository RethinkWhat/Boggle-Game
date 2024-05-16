package Server_Java.model;

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
        while (currTimerValue > 0L) {
            try {
                Thread.sleep(1000L);
                currTimerValue -= 1000L;
            } catch (Exception var2) {
                var2.printStackTrace();
            }
        }
        try {
            Thread.sleep(3000L);
            ServerImplementation.solveRoundPoints(id);
            Thread.sleep(2000L);
            DataPB.updateRoundWinner(id);
            String gameWinner = DataPB.checkGameWinner(id);
            if (gameWinner.equals("undecided")) {
                Thread.sleep(5000);
                ServerImplementation.defineNextRound(id);
                ServerImplementation.startTimerForRound(id);
            } else {
                DataPB.updateGameWinner(id, gameWinner);
            }

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
