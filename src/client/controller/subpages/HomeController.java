package client.controller.subpages;

import client.controller.ClientApplicationController;
import client.model.BoggleApp.userInfo;
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
        view.setEditListener(new EditPfpListener());
        view.setTutorialListener(e -> parent.getView().showTutorial());

        view.getLblUsername().setText(model.getUsername());
      //  view.getLblPlayerPfp().set

       // ImageIcon iconPfp = new ImageIcon(model.getWfImpl().getP);

       // gbc.gridx = 0;
       // gbc.gridy = 0;
       // JLabel lblPlayerPfp = style.createLblIconOnly(iconPfp, 60,60);
        //add(lblPlayerPfp, gbc);
        // mouse listeners
        view.getBtnJoinGame().addMouseListener(new SwingResources.CursorChanger(view.getBtnJoinGame()));
        view.getBtnEditPfp().addMouseListener(new SwingResources.CursorChanger(view.getBtnEditPfp()));
        view.getBtnTutorial().addMouseListener(new SwingResources.CursorChanger(view.getBtnTutorial()));

        //TODO: DEBUG
         populateLeaderboard();
    }

    public void populateLeaderboard() {
        userInfo[] leaderboards = model.getLeaderboard();
        view.clearPnlLeaderBoard();
        for (userInfo leaderboard : leaderboards) {
            view.addPlayerInLeaderboard(leaderboard.username, leaderboard.pfpAddress, leaderboard.points);
        }
        view.revalidate();
        view.repaint();
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

                parent.playLobbyMusic();
                new LobbyController(new LobbyModel(parent.getModel().getUsername(), parent.getModel().getWfImpl()),
                        parent.getView().getLobbyView(), parent);
                System.out.println("new lobby created\n");

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
            });
        }
    }

    /**
     * Processes editing of player profile picture.
     */
    class EditPfpListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}
