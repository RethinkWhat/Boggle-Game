package client.model;

import client.model.BoggleApp.BoggleClient;
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
    public boolean validateAccount(String username, String password) throws Exception{
        return wfImpl.validateAccount(username, password);
    }

    public BoggleClient getWfImpl() {
        return wfImpl;
    }

    public void setWfImpl(BoggleClient wfImpl) {
        this.wfImpl = wfImpl;
    }
}
