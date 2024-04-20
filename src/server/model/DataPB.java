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

        if (!(toEdit.equalsIgnoreCase("username") || toEdit.equalsIgnoreCase("fullName"))){
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

    /**
     * Returns the number of the completed matches of a specific user
     * @param username
     * @return
     */
    public static int getMatches(String username) throws SQLException{
        int noOfMatches = 0;

        return noOfMatches;
    }

    /**
     * Returns the total wins of a particular user
     * @param username
     * @return
     * @throws SQLException
     */
    public static int getWins(String username) throws SQLException{
        int noOfWins = 0;

        return noOfWins;
    }

    /**
     * Returns true if the updating of a password is successful
     * @param username
     * @param oldPass
     * @param newPass
     * @return
     */
    public static boolean editPassword(String username, String oldPass, String newPass){
        try {
            String dbOldPass = "";

            String query1 = "SELECT password FROM player WHERE username = ?";

            PreparedStatement ps1 = con.prepareStatement(query1);
            ps1.setString(1, username);

            ResultSet rs = ps1.executeQuery();

            if (rs.next()){
                dbOldPass = rs.getString(1);
            }

            if (dbOldPass.equals(oldPass)){
                String query2 = "UPDATE player SET password = ? WHERE username = ?";

                PreparedStatement ps2 = con.prepareStatement(query2);
                ps2.setString(1, newPass);
                ps2.setString(2, username);

                ps2.executeUpdate();
                return true;
            }
        }catch (SQLException sqle){
            sqle.printStackTrace();
            return false;
        }

        return false;
    }
}
