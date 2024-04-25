package client.controller;

import client.controller.subpages.LobbyController;
import client.model.ClientApplicationModel;
import client.model.subpages.LobbyModel;
import client.view.ClientApplicationView;
import client.view.subpages.LobbyView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientApplicationController {
    /**
     * The view.
     */
    private ClientApplicationView view;

    /**
     * The model
     */
    private ClientApplicationModel model;


    /**
     * Constructs a ClientApplicationController with a specified view.
     *
     * @param view The specified view.
     */
    public ClientApplicationController(ClientApplicationView view, ClientApplicationModel model) {
        this.model = model;
        this.view = view;

        // action listeners
        this.view.getHomeView().setJoinListener(new JoinGameListener());
        this.view.setSettingsListener(new SettingsListener());
        this.view.setHomeListener(new HomeListener());
        this.view.setLogoutListener(new LogOutListener());

        // mouse listeners
    }

    public class SettingsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Switch to the settings view
            view.showSettings();
            view.setNavLocationText("Settings");
        }
    }

    public class HomeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.showHome();
            view.setNavLocationText("Home");
        }
    }
    public class LogOutListener implements ActionListener {
        //TEMPORARY PROMPT FOR LOGOUT
        @Override
        public void actionPerformed(ActionEvent e) {
            int choice = JOptionPane.showConfirmDialog(
                    view,
                    "Are you sure you want to log out?",
                    "TEMPORARY PROMPT",
                    JOptionPane.YES_NO_OPTION
            );

            if (choice == JOptionPane.YES_OPTION) {
                view.dispose();
            }
        }
    }
    public class JoinGameListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.showLobby();
            view.setNavLocationText("Lobby");
            System.out.println("creating new lobby");

            new LobbyController(new LobbyModel(model.getUsername(), model.getWfImpl()), view.getLobbyView(), view);

            view.getLobbyView().setExitLobbyListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
//                    model.getWfImpl().exitGameRoom(model.getUsername());
                    view.showHome();
                    view.setNavLocationText("Home");

                }
            });
            // Add action listener to the exit lobby button

            /*
            BooleanHolder startGame = new BooleanHolder(false);
            long id;
            while (!startGame.value) {
                id = model.getWfImpl().attemptJoin("username", startGame);
                System.out.println("TIME REMAINING: " + id);
                if (startGame.value) {

                    break;
                }try {
                    Thread.sleep(1000);
                } catch (Exception p) {
                p.printStackTrace();}
            }

             */
        }
    }
}
