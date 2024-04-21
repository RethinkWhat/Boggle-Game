package client.controller.subpages;

import client.controller.ClientApplicationController;
import client.controller.GameRoomController;
import client.model.subpages.GameRoomModel;
import client.model.subpages.LobbyModel;
import client.view.ClientApplicationView;
import client.view.subpages.GameRoomView;
import client.view.subpages.LobbyView;
import org.omg.CORBA.BooleanHolder;

import javax.swing.*;

public class LobbyController {

    private LobbyModel model;
    private LobbyView view;

    private ClientApplicationView parentView;

    public LobbyController(LobbyModel lobbyModel, LobbyView lobbyView, ClientApplicationView clientApplicationView) {
        this.model = lobbyModel;
        this.view = lobbyView;
        parentView = clientApplicationView;

        Thread nT = new Thread(new Runnable() {
            @Override
            public void run() {
                timer();
            }
        });
        nT.start();
    }

    public void timer() {
        BooleanHolder startGame = new BooleanHolder(false);
        System.out.println("reached");
        long id;
        try {
            id = model.getWfImpl().attemptJoin("username", startGame);
            System.out.println("ID: " + id);
            while (!startGame.value) {
                System.out.println("REACHED WHILE");
                view.setLblTimerTxt("00:" + id/1000);
                id = model.getWfImpl().attemptJoin("username", startGame);
                Thread.sleep(1000);
                System.out.println(id );
            }
            id = model.getWfImpl().attemptJoin("username", startGame);
            parentView.showGameRoom();
            new GameRoomController(new GameRoomModel(model.getUsername(), model.getWfImpl(), (int) id), parentView.getGameRoomView());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
