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

    class ServerSwitchListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (view.getServerStatusView().getServerStatus().getText().equalsIgnoreCase("OFFLINE")){
                view.getServerStatusView().setOnline();
                server.run(args);
            }else {
                view.getServerStatusView().setOffline();
                server.stop();
            }
        }
    }

    public static void main(String[] args) {
        new ServerApplicationController(new ServerApplicationView(), new ServerApplicationModel());
    }
}
