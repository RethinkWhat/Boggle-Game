package server;

import server.controller.Server;

public class Main {
    public static void main(String[] args) {
        Server server = new Server();
        server.run(args);
    }
}
