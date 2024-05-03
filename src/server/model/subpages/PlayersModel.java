package server.model.subpages;

import server.model.DataPB;
import server.model.ServerImplementation;

public class PlayersModel {
    ServerImplementation serverImplementation;

    public PlayersModel(ServerImplementation serverImplementation) {
        this.serverImplementation = serverImplementation;
    }

    /**
     * Adds a player
     * NOTE: THIS USES THE DATAPB INSTEAD OF THE BOGGLECLIENT. TLDR: IT WORKS, BUT IS ONLY TEMPORARY
     * @return
     */
    public boolean addPlayer(String username, String password, String fullName, String pfp){
        DataPB dataPB = new DataPB();
        dataPB.setCon();
        return dataPB.addPlayer(username, password,fullName, pfp);
    }

}