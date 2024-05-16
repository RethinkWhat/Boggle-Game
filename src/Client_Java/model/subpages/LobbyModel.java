package Client_Java.model.subpages;

import Client_Java.model.BoggleApp.BoggleClient;
import Client_Java.model.BoggleApp.LobbyUser;

public class LobbyModel {

    private String username;

    BoggleClient wfImpl;

    public LobbyModel(String username, BoggleClient wfImpl) {
        this.username = username;
        this.wfImpl = wfImpl;
    }


    public BoggleClient getWfImpl() {
        return wfImpl;
    }

    public void setWfImpl(BoggleClient wfImpl) {
        this.wfImpl = wfImpl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LobbyUser[] getUsersInLobby() {
        return wfImpl.getLobbyMembers();
    }
}
