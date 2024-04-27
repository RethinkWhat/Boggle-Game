package server;

import server.controller.Server;
import server.controller.ServerApplicationController;
import server.model.ORBInitializer;
import server.model.ServerApplicationModel;
import server.view.ServerApplicationView;

public class Main {
    /*
    public static void main(String[] var0) {
        String[] args = {"-ORBINITIALHOST", "localhost", "-ORBINITIALPORT", "5000"};
        Server var1 = new Server();
        var1.run(args);
    }
     */
    public static void main(String[] args) {
        ORBInitializer.run();
        new ServerApplicationController(new ServerApplicationView(), new ServerApplicationModel());
    }
}