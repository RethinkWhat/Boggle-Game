package server.model.subpages;

import server.model.ServerImplementation;

public class GameSettingsModel {

    ServerImplementation serverImplementation;

    public GameSettingsModel(ServerImplementation serverImplementation) {
        if (serverImplementation == null) {
            System.out.println("ServerImplementation is null in GameSettingsModel constructor");

        } else {
            System.out.println("ServerImplementation initialized in GameSettingsModel constructor");
        }
        this.serverImplementation = serverImplementation;
    }

    public void updateGameDuration(long newDuration) {
        serverImplementation.setRoundDuration(newDuration);
    }

    public void updateWaitingDuration(long newDuration) {
        serverImplementation.setLobbyTimerValue(newDuration);
    }

    public ServerImplementation getImpl(){
        return serverImplementation;
    }

}
