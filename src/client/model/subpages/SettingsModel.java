package client.model.subpages;

import client.model.BoggleApp.BoggleClient;
import server.model.DataPB;

import java.sql.SQLException;

public class SettingsModel {
    private String username;

    private BoggleClient wfImpl;

    public SettingsModel(String username, BoggleClient wfImpl) {
        this.username = username;
        this.wfImpl = wfImpl;
    }

    /**
     * Returns the username of the player
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns the BoggleClient object
     * @return
     */
    public BoggleClient getWfImpl() {
        return wfImpl;
    }

    /**
     * Sets the username of the player
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Sets the BoggleClient instance
     * @param wfImpl
     */
    public void setWfImpl(BoggleClient wfImpl) {
        this.wfImpl = wfImpl;
    }

    /**
     * Returns the total matches of the player
     * @return
     */
    public int getMatches(){                        // POSSIBLE BUG HERE
        return wfImpl.getMatches(this.username);
    }

    /**
     * Returns the total matches of the player
     * NOTE: THIS USES THE DATAPB INSTEAD OF THE BOGGLECLIENT. TLDR: IT WORKS, BUT IS ONLY TEMPORARY
     * @return
     */
    public int getMatchesPartTwo() throws SQLException {
        DataPB dataPB = new DataPB();
        dataPB.setCon();
        return dataPB.getMatches(this.username);
    }

    /**
     * Returns the total wins of the player
     * @return
     */
    public int getWins(){                           // POSSIBLE BUG HERE
        return wfImpl.getWins(this.username);
    }

    /**
     * Returns the games won of the player
     * NOTE: THIS USES THE DATAPB INSTEAD OF THE BOGGLECLIENT. TLDR: IT WORKS, BUT IS ONLY TEMPORARY
     * @return
     */
    public int getWinsPartTwo() throws SQLException {
        DataPB dataPB = new DataPB();
        dataPB.setCon();
        return dataPB.getWins(this.username);
    }

    /**
     * Returns the total points of the player
     * @return
     */
    public int getUserPoints(){                         // THIS IS WORKING
        return wfImpl.getUserPoints(this.username);
    }

    /**
     * Edits the full name of the player
     * NOTE: THIS USES THE DATAPB INSTEAD OF THE BOGGLECLIENT. TLDR: IT WORKS, BUT IS ONLY TEMPORARY
     * @return
     */
    public boolean editInfo (String username, String toEdit, String newInfo) throws SQLException{
        DataPB dataPB = new DataPB();
        dataPB.setCon();
        return dataPB.editInfo(this.username, toEdit, newInfo);
    }

    /**
     * Edits the password of the player
     * NOTE: THIS USES THE DATAPB INSTEAD OF THE BOGGLECLIENT. TLDR: IT WORKS, BUT IS ONLY TEMPORARY
     * @return
     */
    public boolean editPassword(String username, String oldPass, String newPass) throws SQLException{
        DataPB dataPB = new DataPB();
        dataPB.setCon();
        return dataPB.editPassword(this.username, oldPass, newPass);
    }
}