package client;

import client.model.Client;

public class Main {
    public Main() {
    }

    public static void main(String[] var0) {
        String[] args = {"-ORBINITIALHOST", "localhost", "-ORBINITIALPORT", "5000"};
        (new Client()).run(args);
    }
}