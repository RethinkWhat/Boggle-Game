package server.model;

import server.model.BoggleApp.BoggleClientPOA;
import server.model.BoggleApp.Leaderboard;

public class ServerImplementation extends BoggleClientPOA {

    @Override
    public boolean validateAccount(String username, String password) {
        return false;
    }

    @Override
    public int attemptJoin(String username) {
        return 0;
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
