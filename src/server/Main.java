package server;

import server.controller.Server;

public class Main {
    public static void main(String[] var0) {
        String[] args = {"-ORBINITIALHOST", "localhost", "-ORBINITIALPORT", "5000"};
        Server var1 = new Server();
        var1.run(args);
    }
}