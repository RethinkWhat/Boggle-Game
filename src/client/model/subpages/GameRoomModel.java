package client.model.subpages;

import client.model.BoggleApp.BoggleClient;
import org.omg.CORBA.IntHolder;
import org.omg.CORBA.LongHolder;
import org.omg.CORBA.StringHolder;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class GameRoomModel {
    private String username;

    private BoggleClient wfImpl;

    private int gameRoomID;

    private String letterList;
    private Set<String> wordSet;

    public GameRoomModel(String username, BoggleClient wfImpl) {
        this.username = username;
        this.wfImpl = wfImpl;
        gameRoomID = wfImpl.getGameID(username);

        letterList = wfImpl.getLetters(gameRoomID);
    }

    public void sendUserWordList() {
        wfImpl.sendUserWordList(gameRoomID,username, (String[]) wordSet.toArray());
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

    public int getGameRoomID() {
        return gameRoomID;
    }

    public void setGameRoomID(int gameRoomID) {
        this.gameRoomID = gameRoomID;
    }

    public String getLetterList() {
        return letterList;
    }

    public void setLetterList(String letterList) {
        this.letterList = letterList;
    }

    public Set<String> getWordSet() {
        return wordSet;
    }

    public void setWordSet(Set<String> wordSet) {
        this.wordSet = wordSet;
    }
}