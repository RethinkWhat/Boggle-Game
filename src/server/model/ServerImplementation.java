package server.model;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import org.omg.CORBA.BooleanHolder;
import server.model.BoggleApp.BoggleClientPOA;
import server.model.BoggleApp.Leaderboard;

public class ServerImplementation extends BoggleClientPOA {
    private int timeOfWaiting = 10000;
    private long endTimeInMillis;
    Set<String> gameRoomUsers = new HashSet();
    ArrayList<Integer> gameIDList = new ArrayList();
    int lastGameID = 0;
    int gameIDForSession;
    private long currTimeValue = 10000;
    private Time gameDuration = new Time(0, 3, 0);

    public ServerImplementation() {
    }

    public boolean validateAccount(String var1, String var2) {
        return false;
    }

    public long attemptJoin(String var1, BooleanHolder var2) {
        if (this.currTimeValue <= 0L) {
            var2.value = true;
            GameRoom var3 = new GameRoom(this.gameRoomUsers, this.gameDuration);
            return (long)var3.getGameID();
        } else if (this.gameRoomUsers.isEmpty()) {
            var2.value = false;
            this.gameRoomUsers.add(var1);
            this.startTimer();
            return 10000L;
        } else {
            var2.value = false;
            this.gameRoomUsers.add(var1);
            return this.getCurrTimeValue();
        }
    }

    public long getCurrTimeValue() {
        return this.currTimeValue;
    }

    public void setCurrTimeValue(long var1) {
        this.currTimeValue = var1;
    }

    private void startTimer() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    if (currTimeValue > 0L) {
                        currTimeValue -= 1000L;
                        System.out.println("VALUE: " + currTimeValue);
                        if (currTimeValue > 0L) {
                            try {
                                Thread.sleep(1000L);
                            } catch (Exception var2) {
                                var2.printStackTrace();
                            }
                            continue;
                        }
                    }

                    return;
                }
            }
        });

        thread.start();
    }

    public String getDuration(int var1) {
        return null;
    }

    public int getRoundID(int var1, String var2) {
        return 0;
    }

    public int getPoints(String var1) {
        return 0;
    }

    public String getWinnerIfAny(int var1) {
        return null;
    }

    public String getWordList(int var1, int var2) {
        return null;
    }

    public Leaderboard[] getLeaderboard() {
        return new Leaderboard[0];
    }

    public int getUserPoints(String var1) {
        return 0;
    }

    public boolean editInfo(String var1, String var2, String var3) {
        return false;
    }

    public int getMatches(String var1) {
        return 0;
    }

    public int getWins(String var1) {
        return 0;
    }

    public boolean editPassword(String var1, String var2, String var3) {
        return false;
    }
}