package client.controller.subpages;

import client.controller.ClientApplicationController;
import client.model.BoggleApp.LobbyUser;
import client.model.subpages.GameRoomModel;
import client.model.subpages.LobbyModel;
import client.view.ClientApplicationView;
import client.view.subpages.LobbyView;
import org.omg.CORBA.BooleanHolder;

public class LobbyController {

    private LobbyModel model;
    private LobbyView view;

    private ClientApplicationController parent;

    public LobbyController(LobbyModel lobbyModel, LobbyView lobbyView, ClientApplicationController parent) {
        this.model = lobbyModel;
        this.view = lobbyView;
        this.parent = parent;

        Thread nT = new Thread(new Runnable() {
            @Override
            public void run() {
                timer();
            }
        });
        nT.start();
    }

    public void timer() {
        view.setLblTimerTxt("00:" + 10000 / 1000);
        long timerVal = -1;
        LobbyUser[] usersInLobby;
        LobbyUser[] tempUsersInLobby;
        try {
            model.getWfImpl().attemptJoin(model.getUsername());
            usersInLobby = model.getUsersInLobby();
            populateLobby(usersInLobby);

            BooleanHolder startLobby = new BooleanHolder(false);
            while (timerVal != 0) {
                timerVal = model.getWfImpl().getCurrLobbyTimerValue(startLobby);
                System.out.println("TIMER VALUE: " + timerVal);


                usersInLobby = model.getUsersInLobby();
                for (int i = 0; i < usersInLobby.length; i++) {
                    System.out.println("user sent: " + usersInLobby[i].username + " | " + usersInLobby[i].pfpAddress);
                }


                tempUsersInLobby = model.getUsersInLobby();
                if (tempUsersInLobby.length != usersInLobby.length) {
                    System.out.println("reached");
                    usersInLobby = tempUsersInLobby;
                    populateLobby(usersInLobby);
                }


                view.setLblTimerTxt("00:0" + timerVal / 1000);
            }
            if (startLobby.value) {
                parent.getView().showGameRoom();
                parent.stopMusic();
                parent.playGameMusic();
                new GameRoomController(new GameRoomModel(model.getUsername(), model.getWfImpl()),
                        parent.getView().getGameRoomView(), parent);
            } else {
                parent.getView().showHome();
                parent.playDefaultMusic();
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
}
