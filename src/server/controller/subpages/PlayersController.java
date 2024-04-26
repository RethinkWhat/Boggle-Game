package server.controller.subpages;

import server.model.subpages.PlayersModel;
import server.view.subpages.PlayersView;

public class PlayersController {
    private PlayersView view;
    private server.model.subpages.PlayersModel model;

    public PlayersController(PlayersView view, PlayersModel model) {
        this.view = view;
        this.model = model;
    }
}
