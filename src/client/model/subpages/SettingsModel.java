package client.model.subpages;

import client.model.BoggleApp.BoggleClient;

public class SettingsModel {
    private String username;

    private BoggleClient wfImpl;

    public SettingsModel(String username, BoggleClient wfImpl) {
        this.username = username;
        this.wfImpl = wfImpl;
    }

    public String getUsername() {
        return username;
    }

    public BoggleClient getWfImpl() {
        return wfImpl;
    }


}
