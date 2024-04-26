package client.model.subpages;

import client.model.BoggleApp.BoggleClient;

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
     * Returns the total wins of the player
     * @return
     */
    public int getWins(){                           // POSSIBLE BUG HERE
        return wfImpl.getWins(this.username);
    }

    /**
     * Returns the total points of the player
     * @return
     */
    public int getUserPoints(){                         // THIS IS WORKING
        return wfImpl.getUserPoints(this.username);
    }

    public boolean editPassword(String username, String oldPass, String newPass) throws Exception{
        return wfImpl.editPassword(this.username, oldPass, newPass);
    }
}