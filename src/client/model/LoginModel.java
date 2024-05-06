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
    public String validateAccount(String username, String password)throws accountLoggedIn,accountDoesNotExist {
        try {
            wfImpl.validateAccount(username, password);
            return "valid";
        }catch (Exception e){
            return "Already logged in or does not Exist";
        }
    }

    public BoggleClient getWfImpl() {
        return wfImpl;
    }

    public void setWfImpl(BoggleClient wfImpl) {
        this.wfImpl = wfImpl;
    }
}
