package client;

import client.model.Client;
import shared.SwingStylesheet;

public class Main {
    public Main() {
    }

    public static void main(String[] var0) {
        SwingStylesheet.loadFonts();
        String[] args = {"-ORBINITIALHOST", "localhost", "-ORBINITIALPORT", "5000"};
        (new Client()).run(args);

    }
}