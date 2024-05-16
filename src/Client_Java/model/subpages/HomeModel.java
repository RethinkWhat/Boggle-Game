package Client_Java.model.subpages;

import Client_Java.model.BoggleApp.BoggleClient;
import Client_Java.model.BoggleApp.userInfo;

public class HomeModel {
    private String username;

    private BoggleClient wfImpl;

    public HomeModel(String username, BoggleClient wfImpl) {
        this.username = username;
        this.wfImpl = wfImpl;
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

    public userInfo[] getLeaderboard() {
        return wfImpl.getLeaderboard();
    }

    /**
     * Gets the profile picture of the user
     * @param username
     * @return
     */
    public String getPFPOFUser(String username){
        return wfImpl.getPFPOFUser(username);
    }
}