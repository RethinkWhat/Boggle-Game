package server.controller.subpages;

import server.model.ServerApplicationModel;
import server.view.ServerApplicationView;

public class ServerApplicationController {
    private ServerApplicationView view;

    private ServerApplicationModel model;

    public ServerApplicationController(ServerApplicationView view, ServerApplicationModel model) {
        this.view = view;
        this.model = model;


    }


}
