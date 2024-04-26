package server.model;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.omg.CORBA.BooleanHolder;
import org.omg.CORBA.IntHolder;
import org.omg.CORBA.LongHolder;
import org.omg.CORBA.StringHolder;
import server.model.BoggleApp.BoggleClientPOA;
import server.model.BoggleApp.Leaderboard;

public class ServerImplementation extends BoggleClientPOA {
    private final long currTimeValue = 10000L;
    private long gameDuration = 180000L;

    private Set<String> currLobby = new HashSet<>();

    private ArrayList<GameTimer> gamesStarted;

    private GameTimer lobbyTimer = new GameTimer(0, currTimeValue);

    public ServerImplementation() {
        DataPB.setCon();
        gamesStarted = new ArrayList<>();

    }

    public boolean validateAccount(String var1, String var2) {
        return DataPB.validateAccount(var1,var2);
    }

    public long attemptJoin(String username) {
        currLobby.add(username);
        if (lobbyTimer.getCurrValue() == 0L) {
            lobbyTimer = new GameTimer(0, currTimeValue);
            currLobby = new HashSet<>();
            System.out.println("NEW TIMER VALUE: " + lobbyTimer.getCurrValue());
        } if (lobbyTimer.getCurrValue() == 10000L)
            startLobbyTime();
        return -1;
    }

    @Override
    public void exitGameRoom(String username) {
        currLobby.remove(username);
    }

    @Override
    public int joinGameRoom(LongHolder duration) {
        duration.value = gameDuration;
        return DataPB.createGameRoom();
    }

    public long getCurrLobbyTimerValue(BooleanHolder validLobby) {
        validLobby.value = currLobby.size() > 1;
        return lobbyTimer.getCurrValue();
    }


    @Override
    public int startRound(String username, int gameID, IntHolder roundNumber, StringHolder vowelsHolder, StringHolder consonantsHolder) {
        String cons = createRandomConsonantSet();
        String vowel = createRandomVowelSet();
        int roundID = DataPB.createRound(vowel, cons);


        int newRoundNumber = DataPB.getLatestRound(gameID) +1;
        roundNumber.value = newRoundNumber;
        vowelsHolder.value = vowel;
        consonantsHolder.value = cons;
        int id = DataPB.createRoundDetails(gameID,roundID, newRoundNumber, username);
        startTimerForGame(gameID,  gameDuration);

        roundNumber.value = newRoundNumber;
        return id;
    }



    @Override
    public long getGameDurationVal(int gameID, int roundID) {
        return 0;
    }

    //TODO: Store the wordList of each user for the finished round in the game
    //TODO: Compare the word lists. Considering the length of the words and the same words entered among players
    //TODO: Call the DataPB.updatePoints() method and update the points of user for round
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
        int points = DataPB.getUserPoints(username);
        return points;
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

    private void startLobbyTime() {
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
        for (int i = 0; i < 13; i++) {
            int index = random.nextInt(consonant.length());
            sb.append(consonant.charAt(index));
        }
        System.out.println(sb.toString());
        return sb.toString();
    }
}
