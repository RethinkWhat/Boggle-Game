package client.controller.subpages;

import client.controller.ClientApplicationController;
import client.model.subpages.HomeModel;
import client.model.subpages.LobbyModel;
import client.view.subpages.HomeView;
import shared.SwingResources;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The home controller processes user requests in joining the game, changing player profile picture, viewing
 * the global leaderboard and viewing the tutorial.
 */
public class HomeController {
    /**
     * The parent controller.
     */
    private ClientApplicationController parent;
    /**
     * The view.
     */
    private HomeView view;
    /**
     * The model.
     */
    private HomeModel model;

    /**
     * Constructs a HomeController with a specified view and model.
     * @param view The specified view.
     * @param model The specified model.
     */
    public HomeController(HomeView view, HomeModel model, ClientApplicationController parent) {
        this.view = view;
        this.model = model;
        this.parent = parent;

        parent.playDefaultMusic();

        // action listeners
        view.setJoinListener(new JoinGameListener(parent));
        view.setTutorialListener(e -> parent.getView().showTutorial());

        // mouse listeners
        view.getBtnJoinGame().addMouseListener(new SwingResources.CursorChanger(view.getBtnJoinGame()));
        view.getBtnEditPfp().addMouseListener(new SwingResources.CursorChanger(view.getBtnEditPfp()));
        view.getBtnTutorial().addMouseListener(new SwingResources.CursorChanger(view.getBtnTutorial()));
    }

    /**
     * Processes the joining of a lobby.
     */
    static class JoinGameListener implements ActionListener {
        /**
         * The parent controller.
         */
        private ClientApplicationController parent;

        /**
         * Constructs a JOin
         * @param parent
         */
        public JoinGameListener(ClientApplicationController parent) {
            this.parent = parent;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            SwingUtilities.invokeLater(() -> {
                parent.getView().showLobby();
                parent.getView().setNavLocationText("Lobby");System.out.println("creating new lobby");

                parent.playLobbyMusic();
                LobbyController lobbyController = new LobbyController(new LobbyModel(parent.getModel().getUsername(), parent.getModel().getWfImpl()),
                        parent.getView().getLobbyView(), parent);

                parent.getView().getLobbyView().setExitLobbyListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
//                      parent.getModel().getWfImpl().exitGameRoom(model.getUsername());
                        parent.getView().showHome();
                        parent.getView().setNavLocationText("Home");
                        parent.stopMusic();
                        parent.playDefaultMusic();
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
            });
        }
    }
}
