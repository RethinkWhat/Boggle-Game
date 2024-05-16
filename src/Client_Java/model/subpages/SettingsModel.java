package Client_Java.model.subpages;

import Client_Java.model.BoggleApp.BoggleClient;

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
    public int getMatches(){
        return wfImpl.getNumberOfMatches(this.username);
    }

    /**
     * Returns the total matches of the player (option2)
     * @return
     */
    public int getMatchesPartTwo() {
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
    public int getWins(){
        return wfImpl.getNumberOfWins(this.username);
    }

    /**
     * Returns the games won of the player
     * @return
     */
    public int getWinsPartTwo() {
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
    public int getUserPoints(){
        return wfImpl.getUserTotalPoints(this.username);
    }

    /**
     * Edits the full name of the player
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
     * @return
     */
    public String getPFPOfUser(String username){
        try {
            return wfImpl.getPFPOFUser(username);
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * Gets the full name of the player
     * @return
     */
    public String getFullName() {
        try{
            return wfImpl.getFullName(this.username);
        }catch (Exception e){
            return "";
        }
    }
}