package client.controller;

import client.model.subpages.GameRoomModel;
import client.view.subpages.GameRoomView;

import javax.swing.*;

public class GameRoomController {

    GameRoomModel model;
    GameRoomView view;

    public GameRoomController(GameRoomModel model, GameRoomView view) {
        this.model = model;
        this.view = view;

        Thread timer = new Thread(gameTimer());
        timer.start();
    }

    public Runnable gameTimer() {
        Runnable toReturn = new Runnable() {
            @Override
            public void run() {
                try {
                    long duration = model.getDuration();
                    int inSeconds = (int) duration / 1000;
                    System.out.println(inSeconds);
                    while (inSeconds >= 0) {
                        Thread.sleep(1000);
                        view.setLblTimerTxt(inSeconds);
                        inSeconds -= 1;
                        System.out.println(inSeconds);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        return toReturn;
    }

    //TODO: Per user input, store in word set found in model
    //TODO: When timer above elapses, send word set to server using the getRoundWinner method
    //TODO: update entered words shown on game UI

}
