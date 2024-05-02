package server.model;

import org.omg.CORBA.ORB;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.*;

public class DataPB {
    private static Connection con;

    public DataPB() {
    }

    public static void setCon() {
        try {
            String var0 = "jdbc:mysql://localhost:3306/boggle";
            String var1 = "root";
            String var2 = "";
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
            if (var4.next())
                return true;
            return false;
        } catch (Exception var5) {
            var5.printStackTrace();
            return false;
        }
    }

    public static int createRound(String vowelSet, String consonantSet) {
        try {
            System.out.println(consonantSet);
            String stmt = "INSERT INTO round(vowel,consonant) VALUES(?,?)";
            PreparedStatement preparedStatement = con.prepareStatement(stmt, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,vowelSet);
            preparedStatement.setString(2,consonantSet);
            preparedStatement.execute();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next())
                return rs.getInt(1);
            return -1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static int createRoundDetails(int gameID, int roundID, int roundNumber, String username) {
        try {
            System.out.println("game id: " + gameID);
            System.out.println("round id: " + roundID);
            System.out.println("round Number: " + roundNumber);
            System.out.println("username: " + username);
            String stmt = "INSERT INTO round_details(gameID, roundID, roundNumber, username) VALUES (?,?,?,?)";
            PreparedStatement preparedStatement = con.prepareStatement(stmt, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, gameID);
            preparedStatement.setInt(2, roundID);
            preparedStatement.setInt(3, roundNumber);
            preparedStatement.setString(4, username);
            preparedStatement.execute();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next())
                return rs.getInt(1);
            return rs.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;

        }
    }

    public static int getLatestRound(int gameID) {
        try {
            String stmt = "SELECT roundID FROM round_details WHERE gameID=? ORDER BY roundID DESC";
            PreparedStatement preparedStatement = con.prepareStatement(stmt, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1,gameID);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next())
                return rs.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int createGameRoom(Time time) {
        try {
            String insertStmt = "INSERT INTO game(duration) VALUES(?)";

            PreparedStatement stmt = con.prepareStatement(insertStmt, Statement.RETURN_GENERATED_KEYS);
            stmt.setTime(1,time);
            stmt.execute();
            ResultSet rs = stmt.getGeneratedKeys();
            while (rs.next()) {
                int id = rs.getInt(1);
                System.out.println("game room generated with id: " + id);
                return id;
            }
            return -1;
        } catch (Exception var5) {
            System.out.println("Exception frfr");
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
    public static int getMatches(String username) {
        int noOfMatches = 0;

        try {
            String query = "SELECT DISTINCT rd.gameID, rd.username, g.duration, g.gameStatus, g.winner AS gameWinner " +
                    "FROM round_details rd JOIN game g USING (gameID) " +
                    "WHERE username = ? AND g.gameStatus = 'done'";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ++noOfMatches;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return noOfMatches;
    }

    /**
     * Returns the total wins of a particular user
     * @param username
     * @return
     * @throws SQLException
     */
    public static int getWins(String username) {
        int size = 0;
        try {
            String query = "SELECT winner FROM game WHERE winner = ? AND gameStatus = 'done'";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ++size;
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    public static void updatePoints(int gameID, int roundID, int roundNumber, String username, int newPoints) {
        String query = "UPDATE round_details SET points = ? WHERE gameID = ? AND roundID = ? AND roundNumber = ? AND username = ?";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, newPoints);
            ps.setInt(2, gameID);
            ps.setInt(3, roundID);
            ps.setInt(4, roundNumber);
            ps.setString(5, username);

            ps.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    public static int getUserGamePoints(String username){
        String query = "SELECT points FROM player WHERE username = ?";
        int points = 0;
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    points = rs.getInt("points");
                }
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return points;
    }

    public static Map<String, Integer> getTopPlayers() {
        String query = "SELECT username, points FROM player ORDER BY points DESC LIMIT 10";
        Map<String, Integer> topPlayers = new LinkedHashMap();

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                topPlayers.put(rs.getString("username"), rs.getInt("points"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return topPlayers;
    }

    public static Map<String,Integer> getTotalPointsRoundDetails(int gameID, String username){
        Map<String,Integer> totalPointsMap = new HashMap<>();
        String key = username+gameID;

        String query = "SELECT sum(points)as total_points FROM round_details WHERE gameID =? AND username =?";
        try(PreparedStatement ps = con.prepareStatement(query)){
            ps.setInt(1,gameID);
            ps.setString(2,username);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int totalPoints = rs.getInt("total_points");
                totalPointsMap.put(key, totalPoints);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return totalPointsMap;
    }

    public static String getPFPOfUser(String username){
        String pfp = null;

        String query = "SELECT pfp FROM player WHERE username =? ";
        try(PreparedStatement ps = con.prepareStatement(query)){
            ps.setString(1,username);
            ResultSet rs=ps.executeQuery();

            if (rs.next()){
                pfp=rs.getString("pfp");
            }
        }catch (SQLException sqle){
            sqle.printStackTrace();
        }
        return pfp;
    }

    /**
     * Returns true if the updating of a profile picture is successful
     * @param username
     * @param newPFP
     * @return
     */
    public static boolean changePFPOfUser(String username, String newPFP) {
        try {
            String query = "UPDATE player SET pfp = ? WHERE username = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, newPFP);
            ps.setString(2, username);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static List<String> searchUsername(String username) {
        List<String> searchedUsernames = new ArrayList<>();
        try {
            String query = "SELECT username FROM player WHERE username LIKE ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, "%" + username + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                searchedUsernames.add(rs.getString("username"));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return searchedUsernames;
    }

}
