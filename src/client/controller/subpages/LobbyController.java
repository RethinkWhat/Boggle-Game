package client.controller.subpages;

import client.controller.ClientApplicationController;
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
                parent.getView().showGameRoom();
                parent.stopMusic();
                parent.playGameMusic();
                new GameRoomController(new GameRoomModel(model.getUsername(), model.getWfImpl()),
                        parent.getView().getGameRoomView());
            } else {
                parent.getView().showHome();
                parent.playDefaultMusic();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
