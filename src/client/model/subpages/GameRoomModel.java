package client.model.subpages;

import client.model.BoggleApp.BoggleClient;

public class GameRoomModel {
    private String username;

    private BoggleClient wfImpl;

    private int gameRoomID;

    private long duration;

    public GameRoomModel(String username, BoggleClient wfImpl) {
        this.username = username;
        this.wfImpl = wfImpl;
    }
}
