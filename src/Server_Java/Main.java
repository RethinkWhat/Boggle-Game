package Server_Java;

import Server_Java.controller.ServerApplicationController;
import Server_Java.model.ORBInitializer;
import Server_Java.model.ServerApplicationModel;
import Server_Java.view.ServerApplicationView;

public class Main {
    public static void main(String[] args) {
        String argAsCommand = "ORBD -ORBInitialPort 1500 -ORBInitialHost 100.84.168.124";
        ORBInitializer.run(argAsCommand);

        String[] arg = {"-ORBInitialPort", "1500", "-ORBInitialHost", "100.84.168.124"};//change as needed
        new ServerApplicationController(new ServerApplicationView(), new ServerApplicationModel(), arg);
    }

}