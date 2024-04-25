package client.controller.subpages;

import client.model.subpages.GameRoomModel;
import client.model.subpages.LobbyModel;
import client.view.ClientApplicationView;
import client.view.subpages.LobbyView;
import org.omg.CORBA.BooleanHolder;

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
        view.setLblTimerTxt("00:" + 10000 / 1000);
        long timerVal = -1;
        try {
            timerVal = model.getWfImpl().attemptJoin(model.getUsername());
            Thread.sleep(1000);

            BooleanHolder startLobby = new BooleanHolder(false);
            while (timerVal != 0) {
                timerVal = model.getWfImpl().getCurrLobbyTimerValue(startLobby);
                System.out.println(timerVal);
                view.setLblTimerTxt("00:0" + timerVal / 1000);
            }
            System.out.println("THIS IS TIMER VAL: " + timerVal);
            if (startLobby.value) {
                parentView.showGameRoom();
                new GameRoomController(new GameRoomModel(model.getUsername(), model.getWfImpl()), parentView.getGameRoomView());
            } else {
                parentView.showHome();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
