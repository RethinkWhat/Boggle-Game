package Server_Java.model.subpages;

import Server_Java.model.ServerImplementation;

public class GameSettingsModel {

    ServerImplementation serverImplementation;

    public GameSettingsModel(ServerImplementation serverImplementation) {
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
