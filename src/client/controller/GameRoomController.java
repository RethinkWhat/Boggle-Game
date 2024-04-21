package client.controller;

import client.model.subpages.GameRoomModel;
import client.view.subpages.GameRoomView;

public class GameRoomController {

    GameRoomModel model;
    GameRoomView view;

    public GameRoomController(GameRoomModel model, GameRoomView view) {
        this.model = model;
        this.view = view;
    }
}
