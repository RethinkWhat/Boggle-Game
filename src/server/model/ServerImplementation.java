package server.model;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import org.omg.CORBA.BooleanHolder;
import org.omg.CORBA.IntHolder;
import org.omg.CORBA.LongHolder;
import org.omg.CORBA.StringHolder;
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
        DataPB.setCon();

    }

    public boolean validateAccount(String var1, String var2) {
        return DataPB.validateAccount(var1,var2);
    }

    public long attemptJoin() {
        if (this.currTimeValue <= 0L) {
            return 0;
        } else if (getCurrTimeValue() == 10000L) {
            this.startTimer();
            return 10000L;
        } else {
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

    //TODO: remove username
    @Override
    public int joinGameRoom(String username, LongHolder duration) {
        duration.value = gameDuration.getTime();
        return DataPB.createGameRoom(gameDuration);
    }

    @Override
    public int startRound(String username, int gameID, IntHolder roundNumber, StringHolder vowels, StringHolder consonants) {
        int roundID = DataPB.createRound(createRandomVowelSet(), createRandomConsonantSet());

        int newRoundNumber = DataPB.getLatestRound(gameID) +1;
        int id = DataPB.createRoundDetails(gameID,roundID, newRoundNumber, username);

        roundNumber.value = newRoundNumber;
        return id;
    }

    public String createRandomVowelSet() {
        return "";
    }
    public String createRandomConsonantSet() {
        return "";
    }


    @Override
    public long getGameDurationVal(int gameID, int roundID) {
        return 0;
    }

    @Override
    public String getRoundWinner(String username, int gameID, int roundID, String wordsEntered) {
        return null;
    }

    @Override
    public String getOverallWinner(int gameId) {
        return null;
    }

    @Override
    public int getPoints(String username) {
        return 0;
    }

    @Override
    public String getWinnerIfAny(int gameID) {
        return null;
    }

    @Override
    public String getWordList(int gameID, int roundID) {
        return null;
    }

    @Override
    public Leaderboard[] getLeaderboard() {
        return new Leaderboard[0];
    }

    @Override
    public int getUserPoints(String username) {
        return 0;
    }

    @Override
    public boolean editInfo(String username, String toEdit, String newInfo) {
        return false;
    }

    @Override
    public int getMatches(String username) {
        return 0;
    }

    @Override
    public int getWins(String username) {
        return 0;
    }

    @Override
    public boolean editPassword(String username, String oldPass, String newPass) {
        return false;
    }
}