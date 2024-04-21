package server.model;

import java.sql.*;

public class DataPB {
    private static Connection con;

    public DataPB() {
    }

    public static void setCon() {
        try {
            String var0 = "jdbc:mysql://localhost:3306/wordFactory";
            String var1 = "root";
            String var2 = "root";
            con = DriverManager.getConnection(var0, var1, var2);
        } catch (Exception var3) {
            var3.printStackTrace();
        }

    }

    public static boolean validateAccount(String var0, String var1) {
        try {
            String var2 = "SELECT * FROM player WHERE username=? AND password=?";
            PreparedStatement var3 = con.prepareStatement(var2);
            var3.setString(1, var0);
            var3.setString(2, var1);
            ResultSet var4 = var3.executeQuery();
            return var4.first();
        } catch (Exception var5) {
            var5.printStackTrace();
            return false;
        }
    }

    public static int createGameRoom(Time var0) {
        try {
            String var1 = "INSERT INTO game(duration) VALUES(?)";
            PreparedStatement var2 = con.prepareStatement(var1);
            var2.setTime(1, var0);
            var2.execute();
            ResultSet var3 = var2.getGeneratedKeys();
            return var3.getInt(1);
        } catch (Exception var4) {
            var4.printStackTrace();
            return -1;
        }
    }

    public static int createGameRoom(int var0, Time var1) {
        try {
            String var2 = "INSERT INTO game(gameid, duration) VALUES(?)";
            PreparedStatement var3 = con.prepareStatement(var2);
            var3.setInt(1, var0);
            var3.setTime(1, var1);
            var3.execute();
            ResultSet var4 = var3.getGeneratedKeys();
            return var4.getInt(1);
        } catch (Exception var5) {
            var5.printStackTrace();
            return -1;
        }
    }

    public static int getLastGameID() {
        try {
            String var0 = "SELECT gameid from game ORDER BY gameid DESC LIMIT 1";
            Statement var1 = con.createStatement();
            ResultSet var2 = var1.executeQuery(var0);
            if (var2.next()) {
                return var2.getInt(1);
            }
        } catch (Exception var3) {
            var3.printStackTrace();
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
        DataPB.setCon();
        int size = 0;
        String query = "SELECT winner FROM game WHERE winner = ? AND gameStatus = 'done'";

        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();

        while (rs.next()){
            ++size;
        }

        return size;
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

    /**
     * TESTING PURPOSES ONLY
     * @param args
     */
    public static void main(String[] args) throws Exception{
        System.out.println(getWins("Rithik"));
    }
}
