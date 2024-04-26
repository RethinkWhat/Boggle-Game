package client.model;

import client.model.BoggleApp.BoggleClient;

/**
 * The ClientApplicationModel sets the pertinent information of the current player.
 */
public class ClientApplicationModel {
    private String username;
    BoggleClient wfImpl;


    public ClientApplicationModel(String username, BoggleClient client) {
        this.username = username;
        this.wfImpl = client;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public BoggleClient getWfImpl() {
        return wfImpl;
    }

    public void setWfImpl(BoggleClient wfImpl) {
        this.wfImpl = wfImpl;
    }
}
