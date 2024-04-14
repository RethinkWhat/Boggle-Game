package server.model;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataPB {

    private static Connection con;

    public static void setCon() {
        try {
            String url = "jdbc:mysql://localhost:8889/wordFactory";

            String user = "root";
            String pass = "root";
            con = DriverManager.getConnection(url, user, pass);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** Insert queries here */
}
