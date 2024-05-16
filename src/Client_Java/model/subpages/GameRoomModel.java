package Client_Java.model.subpages;

import Client_Java.model.BoggleApp.BoggleClient;

import java.util.HashSet;
import java.util.Set;

public class GameRoomModel {
    private String username;

    private BoggleClient wfImpl;

    private int gameRoomID;

    private String letterList;
    private Set<String> wordSet;

    private long duration;

    public GameRoomModel(String username, BoggleClient wfImpl) {
        this.username = username;
        this.wfImpl = wfImpl;
        gameRoomID = wfImpl.getGameID(username);
        duration = wfImpl.getGameDurationVal(gameRoomID);
        letterList = wfImpl.getLetters(gameRoomID);
        wordSet = new HashSet<>();
    }

    public long getDuration() {
        return duration;
    }

    public void sendUserWordList() {
        if (!wordSet.isEmpty())
            wfImpl.sendUserWordList(gameRoomID,username, wordSet.toArray(new String[0]));
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