package client.controller.subpages;

import client.controller.ClientApplicationController;
import client.model.BoggleApp.userInfo;
import client.model.subpages.HomeModel;
import client.model.subpages.LobbyModel;
import client.view.subpages.AvatarSelectionView;
import client.view.subpages.HomeView;
import client.view.subpages.SettingsView;
import server.model.DataPB;
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
     * The home view.
     */
    private HomeView homeView;
    /**
     * The model.
     */
    private HomeModel model;
    /**
     * The avatar selection view.
     */
    private AvatarSelectionView avatarSelectionView;
    /**
     * The settings view.
     */
    private SettingsView settingsView;

    /**
     * Constructs a HomeController with a specified view and model.
     * @param settingsView
     * @param homeView
     * @param model
     * @param parent
     */
    public HomeController(SettingsView settingsView, HomeView homeView, HomeModel model, ClientApplicationController parent) {
        this.settingsView = settingsView;
        this.homeView = homeView;
        this.model = model;
        this.parent = parent;

        parent.playDefaultMusic();

        // action listeners
        homeView.setJoinListener(new JoinGameListener(parent));
        homeView.setEditListener(new EditPfpListener());
        homeView.setTutorialListener(e -> parent.getView().showTutorial());

        homeView.getLblUsername().setText(model.getUsername());
        homeView.setAvatarImagePath(model.getPFPOFUser(model.getUsername()));

        // mouse listeners
        homeView.getBtnJoinGame().addMouseListener(new SwingResources.CursorChanger(homeView.getBtnJoinGame()));
        homeView.getBtnEditPfp().addMouseListener(new SwingResources.CursorChanger(homeView.getBtnEditPfp()));
        homeView.getBtnTutorial().addMouseListener(new SwingResources.CursorChanger(homeView.getBtnTutorial()));

         populateLeaderboard();
    }

    public void populateLeaderboard() {
        userInfo[] leaderboards = model.getLeaderboard();
        homeView.clearPnlLeaderBoard();
        for (userInfo leaderboard : leaderboards) {
            homeView.addPlayerInLeaderboard(leaderboard.username, leaderboard.pfpAddress, leaderboard.points);
        }
        homeView.revalidate();
        homeView.repaint();
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
         * Constructs a Join
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

                parent.getView().hideButtons();

                parent.playLobbyMusic();
                new LobbyController(new LobbyModel(parent.getModel().getUsername(), parent.getModel().getWfImpl()),
                        parent.getView().getLobbyView(), parent);
                System.out.println("new lobby created\n");

                /*
                parent.getView().getLobbyView().setExitLobbyListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                      parent.getModel().getWfImpl().exitLobby(model.getUsername());
                        parent.getView().showHome();
                        parent.getView().setNavLocationText("Home");
                        parent.getView().showButtons();
                        parent.stopMusic();
                        parent.playDefaultMusic();
                    }
                });

                 */
            });
        }
    }


    /**
     * Processes editing of player profile picture.
     */
    class EditPfpListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            avatarSelectionView = new AvatarSelectionView(model.getUsername(), new DataPB(), homeView, settingsView);
        }
    }
}
