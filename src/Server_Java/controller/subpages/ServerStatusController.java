package Server_Java.controller.subpages;

import Server_Java.model.subpages.ServerStatusModel;
import Server_Java.view.subpages.ServerStatusView;

public class ServerStatusController {
    private ServerStatusView view;
    private ServerStatusModel  model;

    public ServerStatusController(ServerStatusView view, ServerStatusModel model) {
        this.view = view;
        this.model = model;

    }
}
