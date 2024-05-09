
package server.model;

import server.model.BoggleApp.userInfo;
import org.omg.CORBA.ORB;
import shared.Player;

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

    public static int createRound(String letters) {
        try {
            System.out.println(letters);
            String stmt = "INSERT INTO round(letters) VALUES(?)";
            PreparedStatement preparedStatement = con.prepareStatement(stmt, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, letters);
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

    /**
     * STATUS: WORKING
     * Updates the points of the user
     * @param roundIdentifier
     * @param newPoints
     */
    public static void updatePoints(int gameID, int newPoints, String username) {
        String query = "UPDATE round_details SET points = ? WHERE gameID = ? AND username = ?";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, newPoints);
            ps.setInt(2, gameID);
            ps.setString(3, username);
            ps.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    /**
     * STATUS : WORKING
     * This query gets the points of a user.
     * @param username
     * @return
     */
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

    /**
     * STATUS : WORKING
     * This query gets the top 10 players based on their points
     * @return
     */
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

    /**
     * STATUS : WORKING
     * This query totals the points of a user given gameID and username
     * @param gameID
     * @param username
     * @return
     */
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

    /**
     * Status : WORKING
     * This query retrieves the profile picture of a user "location path"
     * @param username
     * @return
     */
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

    public static String getFullName(String username){
        String name = null;

        String query = "SELECT fullName FROM player WHERE username =? ";
        try (PreparedStatement ps = con.prepareStatement(query)){
            ps.setString(1,username);
            ResultSet rs=ps.executeQuery();

            if (rs.next()){
                name=rs.getString("fullName");
            }
        }catch (SQLException sqle){
            sqle.printStackTrace();
        }
        return name;
    }

    /**
     * Returns true if the updating of a profile picture is successful
     * @param username
     * @param newPfpPath
     * @return
     */
    public static boolean changeProfilePicture(String username, String newPfpPath) {
        try {
            String query = "UPDATE player SET pfp = ? WHERE username = ?";
            DataPB.setCon();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, newPfpPath);
            ps.setString(2, username);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * STATUS : WORKING
     * This query simply searches a username
     * @param username
     * @return
     */
    public static List<String> searchUsername(String username) {
        List<String> searchedUsernames = new ArrayList<>();
        try {
            String query = "SELECT playerID, username, fullName FROM player WHERE username LIKE ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, "%" + username + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                searchedUsernames.add(rs.getString("playerID"));
                searchedUsernames.add(rs.getString("username"));
                searchedUsernames.add(rs.getString("fullName"));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return searchedUsernames;
    }

    /**
     * Adds player in the player column
     * @param username
     * @param password
     * @param fullName
     * @param pfp
     * @return
     */
    public static boolean addPlayer(String username, String password, String fullName, String pfp) {
        try {
            String query = "INSERT INTO player(username, password, fullName, pfp) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, fullName);
            ps.setString(4, pfp);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Returns a result set of a given gameID's latest round containing the usernames and their corresponding submitted words.
     * @param gameID
     * @return
     */
    public static ResultSet getUsersWordlists(int gameID) {
        ResultSet resultSet = null;
        try {
            String query = "SELECT username, words FROM round_details " +
                    "WHERE gameID = ? AND roundNumber = (SELECT MAX(roundNumber) FROM round_details WHERE gameID = ?)";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, gameID);
            preparedStatement.setInt(2, gameID);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }
        return resultSet;
    }

    /**
     * Status :
     * @param username
     * @param gameID
     * @param wordList
     */
    public static void addUserWordList(String username, int gameID,String []wordList){
        try{
            String query = "UPDATE round_details SET words = ? WHERE gameID = ? AND username = ?";
            PreparedStatement ps = con.prepareStatement(query);
            String words = String.join("," , wordList);
            ps.setString(1,words);
            ps.setInt(2, gameID);
            ps.setString(3, username);

            ps.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }


    /**
     * Status : WORKING
     * This query gets the winner of the latest round.
     * @param gameID
     * @return winner
     */
    public static String getWinnerOfLatestRound(int gameID) {
        String winner = null;
        try {
            String query = "SELECT r.winner FROM round_details rd JOIN round r ON rd.roundID = r.roundID " +
                    "WHERE rd.gameID = ? ORDER BY rd.roundNumber DESC Limit 1";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, gameID);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                winner = rs.getString("winner");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return winner;
    }


    /**
     * STATUS : WORKING
     * This query returns true if round is ongoing
     * @param gameID
     * @return
     */
    public static Boolean roundOngoing(int gameID) {
        Boolean isOngoing = false;
        try {
            String query = "SELECT gameStatus FROM game WHERE gameID = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, gameID);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String gameStatus = rs.getString("gameStatus");
                if ("ongoing".equalsIgnoreCase(gameStatus)) {
                    isOngoing = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isOngoing;
    }


    /**
     * STATUS : WORKING to be improved
     * This query shows the current game winner given gameID.
     * if the database is empty, it returns undecided by default
     * @param gameID
     * @return
     */
    public static String getGameWinner(int gameID) {
        String winner = "undecided";
        try {
            String query = "SELECT winner FROM game WHERE gameID = ?";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, gameID);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                String dbWinner = rs.getString("winner");
                if (dbWinner != null && !dbWinner.trim().isEmpty()) {
                    winner = dbWinner;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return winner;
    }

    /**
     * STATUS : WORKING
     * This query retrieves the points of a user in a current game given gameID and username
     * @param gameID
     * @param username
     * @return
     */
    public static int getUserRoundPoints(int gameID, String username) {
        int totalPoints = 0;
        try {
            String query = "SELECT points FROM round_details WHERE gameID = ? AND username = ?";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, gameID);
            preparedStatement.setString(2, username);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int points = rs.getInt("points");
                totalPoints += points;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalPoints;
    }


    /**
     * STATUS : WORKING
     * @param gameID
     * @return
     */
    public static ArrayList<String> getPlayersInGame(int gameID) {
        ArrayList<String> players = new ArrayList<>();
        try {
            String query = "SELECT DISTINCT username FROM round_details WHERE gameID = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, gameID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String username = rs.getString("username");
                players.add(username);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return players;
    }


    /**
     * STATUS : WORKING
     * This query provides a leaderboard from current game.
     * @param gameID
     * @return
     */
    public static userInfo[] getCurrGameLeaderboard(int gameID) {
        String sql = "SELECT username, SUM(points) AS total_points FROM round_details WHERE gameID = ? GROUP BY username";

        List<userInfo> leaderboardList = new ArrayList<>();

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, gameID);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String username = rs.getString("username");
                System.out.println(gameID);
                System.out.println("usernames: " + username);
                int totalPoints = rs.getInt("total_points");
                String pfpAddress = getPFPOfUser(username);
                userInfo user = new userInfo(username, pfpAddress, totalPoints);
                leaderboardList.add(user);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        userInfo[] leaderboard = leaderboardList.toArray(new userInfo[0]);

        return leaderboard;
    }

    /**
     * STATUS : WORKING
     * This query locates what game a current user is in.
     * @param username
     * @return
     */
    public static int getGameID(String username) {
        int gameID = 0;
        String sql = "SELECT gameID FROM round_details WHERE username = ? ORDER BY roundIdentifier DESC LIMIT 1";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                gameID = rs.getInt("gameID");
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return gameID;
    }

    public static List<Player> getAllPlayers() {
        DataPB.setCon();
        List<Player> players = new ArrayList<>();
        try {
            String query = "SELECT playerID, username, fullName FROM player";
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Player player = new Player();
                player.setPlayerID(resultSet.getInt("playerID"));
                player.setUsername(resultSet.getString("username"));
                player.setFullName(resultSet.getString("fullName"));
                players.add(player);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return players;
    }

    public static boolean removePlayer(int playerID) {
        try {
            String query = "DELETE FROM player WHERE playerID = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, playerID);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * STATUS: WORKING
     * @param word
     * @return
     */
    public static boolean isValidWord(String word) {
        boolean isValid = false;
        String query = "SELECT COUNT(*) FROM words WHERE words = ?";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, word);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                isValid = (count > 0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isValid;
    }

    /**
     * STATUS WORKING
     */
    public static void defineWordList() {
        String query = "SELECT words FROM words";
        try (PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                String word = rs.getString("words");
                System.out.println(word);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getLetters(int gameID) {
        String letters = null;
        try {
            String query = "SELECT r.letters FROM round r JOIN round_details rd ON r.roundID = rd.roundID " +
                    "WHERE rd.gameID = ? ORDER BY r.roundID DESC LIMIT 1";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, gameID);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                letters = rs.getString("letters");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return letters;
    }

}