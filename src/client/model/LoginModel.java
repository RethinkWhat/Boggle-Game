package client.model;

import client.model.BoggleApp.BoggleClient;
import client.model.BoggleApp.accountDoesNotExist;
import client.model.BoggleApp.accountLoggedIn;
import server.model.DataPB;

public class LoginModel {
    BoggleClient wfImpl;


    public LoginModel(BoggleClient wfImpl) {
        this.wfImpl = wfImpl;
    }

    /**
     * Returns a boolean if the entered account details exists in the database
     * @param username
     * @param password
     * @return
     * @throws Exception
     */
    public String validateAccount(String username, String password) {
        try {
            wfImpl.validateAccount(username, password);
            return "valid";
        } catch (accountLoggedIn e) {
            return "Account logged in elsewhere.";
        } catch (accountDoesNotExist er) {
            return "Wrong credentials. Try again.";
        }
    }

    public BoggleClient getWfImpl() {
        return wfImpl;
    }

    public void setWfImpl(BoggleClient wfImpl) {
        this.wfImpl = wfImpl;
    }
}
