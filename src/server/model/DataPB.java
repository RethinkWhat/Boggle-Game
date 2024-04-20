package server.model;

import java.sql.*;
import java.util.ArrayList;

public class DataPB {

    private static Connection con;

    public static void setCon() {
        try {
            //Change port if necessary but RETURN IT TO 8889 KASI NAKAKAHIYA
            String url = "jdbc:mysql://localhost:3306/wordFactory";

            String user = "root";
            String pass = "root";
            con = DriverManager.getConnection(url, user, pass);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean validateAccount(String username, String password) {
        try {
            String stmt = "SELECT * FROM player WHERE username=? AND password=?";
            PreparedStatement prepared = con.prepareStatement(stmt);
            prepared.setString(1,username);
            prepared.setString(1,password);
            ResultSet rs = prepared.executeQuery();
            return rs.first();
        } catch (Exception e) {
        e.printStackTrace();
        return false;
        }
    }

    public static int createGameRoom(Time duration) {
        try {
            String stmt = "INSERT INTO game(duration) VALUES(?)";
            PreparedStatement preparedStatement = con.prepareStatement(stmt);
            preparedStatement.setTime(1,duration);
            preparedStatement.execute();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            return rs.getInt(1);

        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static int createGameRoom(int id, Time duration) {
        try {
            String stmt = "INSERT INTO game(gameid, duration) VALUES(?)";
            PreparedStatement preparedStatement = con.prepareStatement(stmt);
            preparedStatement.setInt(1,id);
            preparedStatement.setTime(1,duration);
            preparedStatement.execute();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            return rs.getInt(1);

        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static int getLastGameID() {
        try {
            String stmt = "SELECT gameid from game ORDER BY gameid DESC LIMIT 1";
            Statement query = con.createStatement();
            ResultSet rs = query.executeQuery(stmt);
            if (rs.next()) {
                return rs.getInt(1);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * Edits the info of a particular player
     * Returns true if the update is succesful, otherwise it returns falls
     * @param username
     * @param toEdit
     * @param newInfo
     * @return
     */
    public static boolean editInfo(String username, String toEdit, String newInfo){
        DataPB.setCon();

        if (!(toEdit.equalsIgnoreCase("username") || toEdit.equalsIgnoreCase("password"))){
            return false;
        }

        try {
            String query = "UPDATE player SET " + toEdit + " = ? WHERE username = ?";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, newInfo);
            ps.setString(2, username);

            ps.executeUpdate();
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
