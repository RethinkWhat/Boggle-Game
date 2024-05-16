package Client_Java.model;

import Client_Java.model.BoggleApp.BoggleClient;
import Client_Java.model.BoggleApp.accountDoesNotExist;
import Client_Java.model.BoggleApp.accountLoggedIn;

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
            return "Already logged in or does not exist";
        }
    }

    public BoggleClient getWfImpl() {
        return wfImpl;
    }

    public void setWfImpl(BoggleClient wfImpl) {
        this.wfImpl = wfImpl;
    }
}
