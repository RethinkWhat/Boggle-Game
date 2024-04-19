package server.model;

import org.omg.CORBA.IntHolder;
import org.omg.CORBA.LongHolder;
import org.omg.CORBA.StringHolder;
import server.model.BoggleApp.BoggleClientPOA;
import server.model.BoggleApp.Leaderboard;

import javax.swing.Timer;
import java.util.ArrayList;

public class ServerImplementation extends BoggleClientPOA {

    private int timeOfWaiting;

    private long startTimeInMillis;

    // Will be the holder for a game room that is waiting for users to join
    ArrayList<String> gameRoomUsers = new ArrayList<>();

    ArrayList<Integer> gameIDList = new ArrayList<>();

    int lastGameID = DataPB.getLastGameID();

    int gameIDForSession;

    @Override
    public boolean validateAccount(String username, String password) {
        return false;
    }


    /** Change to LongHolder on next update of idl */
    public int attemptJoin(String username, IntHolder timeRemaining) {

        // make entering the game room synchronized
        synchronized (this) {
            try {
                // Add the user to game room
                gameRoomUsers.add(username);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // If user is the first to enter the game room start a timer
        if (gameRoomUsers.size() == 1) {
            startTimeInMillis = System.currentTimeMillis();
            timeRemaining.value = (int) ( startTimeInMillis - System.currentTimeMillis());
            gameIDForSession +=1;
            try {
                Thread.sleep(timeOfWaiting);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (gameRoomUsers.size() > 1)
            return gameIDForSession;
        else
            return -1;
    }


    @Override
    public String getDuration(int gameID) {
        return null;
    }

    @Override
    public int getRoundID(int gameID, String username) {
        return 0;
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
