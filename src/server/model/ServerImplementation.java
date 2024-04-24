package server.model;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

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
    private long gameDuration = 180000L;

    private ArrayList<GameTimer> gamesStarted;

    private GameTimer lobbyTimer = new GameTimer(0, currTimeValue);

    public ServerImplementation() {
        DataPB.setCon();
        gamesStarted = new ArrayList<>();

    }

    public boolean validateAccount(String var1, String var2) {
        return DataPB.validateAccount(var1,var2);
    }

    public long attemptJoin() {
        if (this.currTimeValue <= 0L) {
            return 0;
        } else if (getCurrLobbyTimerValue() == 10000L) {
            startLobbyTime(currTimeValue);
            return 10000L;
        } else {
            return this.getCurrLobbyTimerValue();
        }
    }

    @Override
    public int joinGameRoom(LongHolder duration) {
        duration.value = gameDuration;
        return DataPB.createGameRoom();
    }

    public int getCurrLobbyTimerValue() {
        return (int)lobbyTimer.getCurrValue();
    }


    @Override
    public int startRound(String username, int gameID, IntHolder roundNumber, StringHolder vowels, StringHolder consonants) {
        int roundID = DataPB.createRound(createRandomVowelSet(), createRandomConsonantSet());

        int newRoundNumber = DataPB.getLatestRound(gameID) +1;
        System.out.println("---- start round ----");
        System.out.println("game ID: " + gameID);
        System.out.println("roundID: " + roundID);
        System.out.println("newRoundNumber: " + newRoundNumber);
        System.out.println("username: " + username);
        System.out.println("---- end round ----");
        int id = DataPB.createRoundDetails(gameID,roundID, newRoundNumber, username);
        startTimerForGame(gameID,  gameDuration);

        roundNumber.value = newRoundNumber;
        return id;
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

    private void startTimerForGame(int gameID, long duration) {
        GameTimer gameTimer = new GameTimer(gameID, duration);
        Thread t = new Thread(gameTimer);
        gamesStarted.add(gameTimer);
        t.start();
    }

    private void startLobbyTime(long duration) {
        Thread t = new Thread(lobbyTimer);
        t.start();
    }
    private String createRandomVowelSet() {
        String vowel = "AEIOU";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for(int i = 0; i < 7; i++){
            int index= random.nextInt(vowel.length());
            sb.append(vowel.charAt(index));
        }
        System.out.println(sb.toString());
        return sb.toString();
    }

    private static String createRandomConsonantSet() {
        String consonant = "BCDFGHJKLMNPQRSTVWXYZ";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 17; i++) {
            int index = random.nextInt(consonant.length());
            sb.append(consonant.charAt(index));
        }
        System.out.println(sb.toString());
        return sb.toString();
    }
}
