package client.model.subpages;

import client.model.BoggleApp.BoggleClient;
import client.model.BoggleApp.updateFailed;
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
        return wfImpl.getNumberOfMatches(this.username);
    }

    /**
     * Returns the total matches of the player
     * NOTE: THIS USES THE DATAPB INSTEAD OF THE BOGGLECLIENT. TLDR: IT WORKS, BUT IS ONLY TEMPORARY
     * @return
     */
    public int getMatchesPartTwo() throws SQLException {
        try {
            return wfImpl.getNumberOfMatches(username);
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * Returns the total wins of the player
     * @return
     */
    public int getWins(){                           // POSSIBLE BUG HERE
        return wfImpl.getNumberOfWins(this.username);
    }

    /**
     * Returns the games won of the player
     * NOTE: THIS USES THE DATAPB INSTEAD OF THE BOGGLECLIENT. TLDR: IT WORKS, BUT IS ONLY TEMPORARY
     * @return
     */
    public int getWinsPartTwo() throws SQLException {
        try {
            return wfImpl.getNumberOfWins(username);
        }catch (Exception e) {
            return 0;
        }
    }

    /**
     * Returns the total points of the player
     * @return
     */
    public int getUserPoints(){                         // THIS IS WORKING
        return wfImpl.getUserTotalPoints(this.username);
    }

    /**
     * Edits the full name of the player
     * NOTE: THIS USES THE DATAPB INSTEAD OF THE BOGGLECLIENT. TLDR: IT WORKS, BUT IS ONLY TEMPORARY
     * @return
     */
    public boolean editInfo (String username, String toEdit, String newInfo) throws SQLException{
        try {
            wfImpl.editInfo(username, toEdit, newInfo);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Edits the password of the player
     * NOTE: THIS USES THE DATAPB INSTEAD OF THE BOGGLECLIENT. TLDR: IT WORKS, BUT IS ONLY TEMPORARY
     * @return
     */
    public boolean editPassword(String username, String oldPass, String newPass) throws SQLException{
        try {
            wfImpl.editPassword(username, oldPass, newPass);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Edits the profile picture of the player
     * NOTE: THIS USES THE DATAPB INSTEAD OF THE BOGGLECLIENT. TLDR: IT WORKS, BUT IS ONLY TEMPORARY
     * @return
     */
    public String getPFPOfUser(String username){
        try {
            return "";
            //return wfImpl.getPFPOfUser(username);
        } catch (Exception e) {
            return "";
        }
    }
}