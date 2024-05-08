package client;

import client.model.Client;
import shared.SwingStylesheet;

public class Main {
    public Main() {
    }

    public static void main(String[] var0) {
        SwingStylesheet.loadFonts();
//      String[] args = {"-ORBInitialPort", "1500", "-ORBInitialHost", "192.168.1.2"};
        //edit Configuration before running
        (new Client()).run(var0);

    }
}