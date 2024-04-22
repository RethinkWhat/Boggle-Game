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
        long timerVal=10000;
        try {
            while (timerVal!=0) {
                timerVal = model.getWfImpl().attemptJoin();
                view.setLblTimerTxt("00:" + timerVal / 1000);
                Thread.sleep(1000);
            }
            parentView.showGameRoom();
            new GameRoomController(new GameRoomModel(model.getUsername(), model.getWfImpl()), new GameRoomView());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
