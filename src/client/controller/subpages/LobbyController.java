package client.controller.subpages;

import client.controller.ClientApplicationController;
import client.model.BoggleApp.LobbyUser;
import client.model.subpages.GameRoomModel;
import client.model.subpages.LobbyModel;
import client.view.ClientApplicationView;
import client.view.subpages.LobbyView;
import org.omg.CORBA.BooleanHolder;

import javax.swing.*;

public class LobbyController {

    private LobbyModel model;
    private LobbyView view;

    private ClientApplicationController parent;
    private volatile boolean lobbyTimerRunning = true;


    public LobbyController(LobbyModel lobbyModel, LobbyView lobbyView, ClientApplicationController parent) {
        this.model = lobbyModel;
        this.view = lobbyView;
        this.parent = parent;

        startTimer();
    }

    public void timer() {
        view.setLblTimerTxt("00:00");
        long timerVal = -1;
        LobbyUser[] usersInLobby;
        LobbyUser[] tempUsersInLobby;

        try {
            model.getWfImpl().attemptJoin(model.getUsername());
            usersInLobby = model.getUsersInLobby();
            populateLobby(usersInLobby);

            BooleanHolder startLobby = new BooleanHolder(false);

            while (lobbyTimerRunning && timerVal != 0) {
                timerVal = model.getWfImpl().getCurrLobbyTimerValue(startLobby);
                String formattedTimer = String.format("%02d:%02d:%02d",
                        (timerVal / 3600000) % 60,
                        (timerVal / 60000) % 60,
                        (timerVal / 1000) % 60);

                SwingUtilities.invokeLater(() -> view.setLblTimerTxt(formattedTimer));

                System.out.println("TIMER VALUE: " + formattedTimer);

                tempUsersInLobby = model.getUsersInLobby();
                if (tempUsersInLobby.length != usersInLobby.length) {
                    System.out.println("reached");
                    usersInLobby = tempUsersInLobby;
                    populateLobby(usersInLobby);
                }

                Thread.sleep(1000);
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

    public void startTimer() {
        lobbyTimerRunning = true;
        Thread nT = new Thread(this::timer);
        nT.start();
    }

    public void stopTimer() {
        lobbyTimerRunning = false;
    }

    public void populateLobby(LobbyUser[] users) {
        view.removePlayersInUserPanel();
        for (LobbyUser user : users) {
            view.addPlayerInUserPanel(user.pfpAddress, user.username);
        }
    }
}