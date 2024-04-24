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
     * Returns the total matches of the player
     * @return
     */
    public int getMatches(){
        return wfImpl.getMatches(this.username);
    }

    /**
     * Returns the total wins of the player
     * @return
     */
    public int getWins(){
        return wfImpl.getWins(this.username);
    }
}
