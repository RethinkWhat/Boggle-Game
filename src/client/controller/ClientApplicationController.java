package client.controller;

import client.controller.subpages.LobbyController;
import client.model.ClientApplicationModel;
import client.model.subpages.LobbyModel;
import client.view.ClientApplicationView;

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
     * @param view The specified view.
     */
    public ClientApplicationController(ClientApplicationView view, ClientApplicationModel model) {
        this.model = model;
        this.view = view;

        // action listeners
        this.view.getHomeView().setJoinListener(new JoinGameListener());

        // mouse listeners
    }

    public class JoinGameListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.showLobby();
            System.out.println("creating new lobby");
            new LobbyController(new LobbyModel(model.getUsername(), model.getWfImpl()), view.getLobbyView(), view);
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
