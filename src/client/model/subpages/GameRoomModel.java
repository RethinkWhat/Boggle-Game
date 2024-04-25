package client.model.subpages;

import client.model.BoggleApp.BoggleClient;
import org.omg.CORBA.IntHolder;
import org.omg.CORBA.LongHolder;
import org.omg.CORBA.StringHolder;

import java.util.HashMap;
import java.util.Set;

public class GameRoomModel {
    private String username;

    private BoggleClient wfImpl;

    private int gameRoomID;

    //Holds all the UNIQUE rounds ids for a game room and whether it is round 1, round 2...
    private HashMap<Integer, Integer> roundIDRoundNo = new HashMap<>();
    private long duration;

    private char[] vowelSet = new char[7];
    private char[] consonantSet = new char[13];

    public GameRoomModel(String username, BoggleClient wfImpl) {
        this.username = username;
        this.wfImpl = wfImpl;
        LongHolder durHolder = new LongHolder(0);
        gameRoomID = wfImpl.joinGameRoom(durHolder);
        duration = durHolder.value;

        nextRound();
    }

    public void nextRound() {
        StringHolder vHolder = new StringHolder();
        StringHolder cHolder = new StringHolder();
        IntHolder rnHolder = new IntHolder(0);
        int roundID = wfImpl.startRound(username, gameRoomID,rnHolder, vHolder, cHolder);
        int roundNumber = rnHolder.value;
        roundIDRoundNo.put(roundID, roundNumber);

        String vowels = vHolder.value;
        String cons = cHolder.value;

        for (int x =0; x<vHolder.value.length(); x++) {
            vowelSet[x] = vowels.charAt(x);
        }
        for (int x = 0; x <cHolder.value.length(); x++) {
            consonantSet[x] = cons.charAt(x);
        }
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

    public HashMap<Integer, Integer> getRoundIDRoundNo() {
        return roundIDRoundNo;
    }

    public void setRoundIDRoundNo(HashMap<Integer, Integer> roundIDRoundNo) {
        this.roundIDRoundNo = roundIDRoundNo;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public char[] getVowelSet() {
        return vowelSet;
    }

    public void setVowelSet(char[] vowelSet) {
        this.vowelSet = vowelSet;
    }

    public char[] getConsonantSet() {
        return consonantSet;
    }

    public void setConsonantSet(char[] consonantSet) {
        this.consonantSet = consonantSet;
    }
}
