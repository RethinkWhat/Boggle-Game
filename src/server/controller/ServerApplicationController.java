package server.controller;

import server.model.ServerApplicationModel;
import server.view.ServerApplicationView;
import shared.ExitDialog;
import shared.SwingResources;
import shared.SwingStylesheet;

import javax.swing.*;
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
        view.setSwitchListener(new SwitchListener());
        view.setGameSettingsListener(new GameSettingsListener());
        view.setPlayersListener(new PlayersListener());
        view.setLogoutListener(new LogoutListener());

        view.getBtnNavSwitch().addMouseListener(new SwingResources.CursorChanger(view.getBtnNavSwitch()));
        view.getBtnNavGameSettings().addMouseListener(new SwingResources.CursorChanger(view.getBtnNavGameSettings()));
        view.getBtnNavPlayers().addMouseListener(new SwingResources.CursorChanger(view.getBtnNavPlayers()));
        view.getBtnNavLogout().addMouseListener(new SwingResources.CursorChanger(view.getBtnNavLogout()));
    }

    // Action listener for switching to Server Status panel
    class SwitchListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.showServerStatusPanel();
            view.setLocationText("Server Status");
        }
    }

    // Action listener for switching to Game Settings panel
    class GameSettingsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.showGameSettingsPanel();
            view.setLocationText("Game Settings");
        }
    }

    // Action listener for switching to Players panel
    class PlayersListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.showPlayersPanel();
            view.setLocationText("Players");
        }
    }

    // Action listener for logging out
    class LogoutListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            SwingStylesheet style = new SwingStylesheet();
            ExitDialog exitDialog = new ExitDialog(
                    "Exit Confirmation",
                    new ImageIcon("res/drawable/icons/alert-red-solid.png"),
                    "EXIT CONFIRMATION",
                    "Are you sure you want to exit the game?.",
                    "EXIT",
                    style.red,
                    style.white,
                    style.black,
                    style.red
            );
        }
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
}
