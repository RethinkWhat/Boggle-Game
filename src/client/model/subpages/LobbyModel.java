package client.model.subpages;

import client.model.BoggleApp.BoggleClient;
import client.model.BoggleApp.LobbyUser;
import org.omg.CORBA.BooleanHolder;

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
