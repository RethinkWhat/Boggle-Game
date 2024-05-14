package client.controller.subpages;

import client.controller.ClientApplicationController;
import client.model.BoggleApp.LobbyUser;
import client.model.subpages.GameRoomModel;
import client.model.subpages.LobbyModel;
import client.view.ClientApplicationView;
import client.view.subpages.LobbyView;
import org.omg.CORBA.BooleanHolder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LobbyController {

    private LobbyModel model;
    private LobbyView view;

    private ClientApplicationController parent;

    Thread timerThread;

    private boolean exitLobby;

    public LobbyController(LobbyModel lobbyModel, LobbyView lobbyView, ClientApplicationController parent) {
        this.model = lobbyModel;
        this.view = lobbyView;
        this.parent = parent;

        timerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                timer();
            }
        });
        timerThread.start();

        view.setExitLobbyListener(new ExitListener());
    }

    public void timer() {
        view.setLblTimerTxt("00:" + 10000 / 1000);
        long timerVal = -1;
        LobbyUser[] usersInLobby;
        LobbyUser[] tempUsersInLobby;
        exitLobby = false;
        try {
            model.getWfImpl().attemptJoin(model.getUsername());
            usersInLobby = model.getUsersInLobby();
            populateLobby(usersInLobby);

            BooleanHolder startLobby = new BooleanHolder(false);
            while (timerVal != 0) {
                if (exitLobby)
                    break;
                timerVal = model.getWfImpl().getCurrLobbyTimerValue(startLobby);
                String formattedTimer = String.format("%02d:%02d:%02d",
                        (timerVal / 3600000) % 60,
                        (timerVal / 60000) % 60,
                        (timerVal / 1000) % 60);
                System.out.println("TIMER VALUE: " + formattedTimer);

                tempUsersInLobby = model.getUsersInLobby();
                if (tempUsersInLobby.length != usersInLobby.length) {
                    System.out.println("reached");
                    usersInLobby = tempUsersInLobby;
                    populateLobby(usersInLobby);
                }

                view.setLblTimerTxt(formattedTimer);

            }
            if (startLobby.value) {
                System.out.println("start lobby is valid");
                parent.getView().showGameRoom();
                parent.stopMusic();
                parent.playGameMusic();
                new GameRoomController(new GameRoomModel(model.getUsername(), model.getWfImpl()),
                        parent.getView().getGameRoomView(), parent);
            } else {
                parent.getView().showHome();
                parent.playDefaultMusic();
                parent.getView().showButtons();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void populateLobby(LobbyUser[] users) {
        view.removePlayersInUserPanel();
        for (LobbyUser user : users) {
            view.addPlayerInUserPanel(user.pfpAddress, user.username);
        }
    }

    public class ExitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            timerThread.interrupt();
            exitLobby = true;
            model.getWfImpl().exitLobby(model.getUsername());
            parent.getView().showHome();
            parent.getView().setNavLocationText("Home");
            parent.getView().showButtons();
            parent.stopMusic();
            parent.playDefaultMusic();

        }
    }
}