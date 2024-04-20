package server.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;

public class DataPB {
    private static Connection con;

    public DataPB() {
    }

    public static void setCon() {
        try {
            String var0 = "jdbc:mysql://localhost:8889/wordFactory";
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
            var3.setString(1, var1);
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
}