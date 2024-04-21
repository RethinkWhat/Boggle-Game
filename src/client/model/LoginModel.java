package client.model;

import server.model.DataPB;

public class LoginModel {

    /**
     * Returns a boolean if the entered account details exists in the database
     * @param username
     * @param password
     * @return
     * @throws Exception
     */
    public boolean validateAccount(String username, String password) throws Exception{
        return DataPB.validateAccount(username, password);
    }

}
