package client.model.subpages;

import client.model.BoggleApp.BoggleClient;
import client.model.BoggleApp.Leaderboard;

public class HomeModel {
    private String username;

    private BoggleClient wfImpl;

    public HomeModel(String username, BoggleClient wfImpl) {
        this.username = username;
        this.wfImpl = wfImpl;
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

    public Leaderboard[] getLeaderboard() {
        return wfImpl.getLeaderboard();
    }
}
