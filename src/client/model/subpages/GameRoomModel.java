package client.model.subpages;

import client.model.BoggleApp.BoggleClient;

public class GameRoomModel {
    private String username;

    private BoggleClient wfImpl;

    private int gameRoomID;

    public GameRoomModel(String username, BoggleClient wfImpl, int gameRoomID) {
        this.username = username;
        this.wfImpl = wfImpl;
        this.gameRoomID = gameRoomID;
    }
}
