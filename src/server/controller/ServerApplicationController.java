package server.controller;

import server.model.ServerApplicationModel;
import server.view.ServerApplicationView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerApplicationController {
    private ServerApplicationView view;

    private ServerApplicationModel model;

    private Server server;
    private String[] args = {"-ORBINITIALHOST", "localhost", "-ORBINITIALPORT", "5000"};

    public ServerApplicationController(ServerApplicationView view, ServerApplicationModel model) {
        this.view = view;
        this.model = model;
        this.server = new Server();

        view.getServerStatusView().setServerListener(new ServerSwitchListener());
    }

    class ServerSwitchListener implements ActionListener {
        private boolean isServerRunning = false;

        @Override
        public void actionPerformed(ActionEvent e) {

            if (!isServerRunning) {
                // If the server is not running, start it
                view.getServerStatusView().setOnline();

                Thread thread = new Thread(() -> {
                    server.run(args);
                });

                thread.start();
                isServerRunning = true;
            } else {
                // If the server is running, stop it
                view.getServerStatusView().setOffline();
                try {
                    server.stop();
                }catch (Exception ignore){

                }
                isServerRunning = false;
            }
        }
    }

    public static void main(String[] args) {
        new ServerApplicationController(new ServerApplicationView(), new ServerApplicationModel());
    }
}
