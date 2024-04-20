package server.model;

import java.sql.Time;
import java.util.Set;

public class GameRoom {
    private int gameID = 1;// TODO: when database is finished change to DataPB.getLastGameID() + 1;
    private Set<String> players;
    private Time duration;

    public GameRoom(Set<String> var1, Time var2) {
        this.duration = var2;
        this.players = var1;
    }

    public int getGameID() {
        return this.gameID;
    }

    public void setGameID(int var1) {
        this.gameID = var1;
    }

    public Set<String> getPlayers() {
        return this.players;
    }

    public void setPlayers(Set<String> var1) {
        this.players = var1;
    }
}