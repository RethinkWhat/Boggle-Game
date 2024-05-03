package server.model.subpages;

import server.model.ServerImplementation;

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

}
