package server.model;

import org.omg.CORBA.IntHolder;
import org.omg.CORBA.LongHolder;
import org.omg.CORBA.StringHolder;
import server.model.BoggleApp.BoggleClientPOA;
import server.model.BoggleApp.Leaderboard;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class ServerImplementation extends BoggleClientPOA {

    private int timeOfWaiting = 10000;

    private long endTimeInMillis;

    // Will be the holder for a game room that is waiting for users to join
    ArrayList<String> gameRoomUsers = new ArrayList<>();

    ArrayList<Integer> gameIDList = new ArrayList<>();

    int lastGameID = 0;            // TODO: Change to when DB not null DataPB.getLastGameID();

    int gameIDForSession;

    @Override
    public boolean validateAccount(String username, String password) {
        return false;
    }


    /** Change to LongHolder on next update of idl */
    public int attemptJoin(String username, IntHolder timeRemaining) {

        Object waitObject = new Object();

        // make entering the game room synchronized
        synchronized (this) {
            try {
                System.out.println("adding " + username + " to game room.");
                // Add the user to game room
                gameRoomUsers.add(username);

                // If user is the first to enter the game room start a timer
                if (gameRoomUsers.size() == 1) {
                    System.out.println("game room size == 1");
                    endTimeInMillis = System.currentTimeMillis() + 10000;

                    System.out.println("assigning timeRemaining the value " + (endTimeInMillis - System.currentTimeMillis()));
                    timeRemaining.value = (int) (endTimeInMillis - System.currentTimeMillis());

                    gameIDForSession += 1;
                    System.out.println("Starting count down");
                    System.out.println(timeRemaining.value);

                    while (timeRemaining.value > 0) {
                        // Update time remaining every second
                        timeRemaining.value -= 1000; // Decrease by 1 second (1000 milliseconds)
                        System.out.println("VALUE: " + timeRemaining.value);
                        if (timeRemaining.value <= 0) {
                            // Stop the timer if countdown is over
                            break;
                        }
                        try {
                            Thread.sleep(1000);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    // Notify waiting threads after timer finishes
                    synchronized(waitObject) {
                        waitObject.notify();
                    }

                } else {
                    gameRoomUsers.add(username);
                    // Spawn a new thread to wait on separate object
                    Thread waitingThread = new Thread(() -> {
                        synchronized(waitObject) {
                            try {
                                waitObject.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    waitingThread.start(); // Start the waiting thread
                }
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
