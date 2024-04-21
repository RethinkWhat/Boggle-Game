package client.controller;

import client.model.ClientApplicationModel;
import client.view.ClientApplicationView;

public class ClientApplicationController {
    /**
     * The view.
     */
    private ClientApplicationView view;

    /**
     * The model
     */
    private ClientApplicationModel model;

    /**
     * Constructs a ClientApplicationController with a specified view.
     * @param view The specified view.
     */
    public ClientApplicationController(ClientApplicationView view, ClientApplicationModel model) {
        this.model = model;
        this.view = view;

        // action listeners

        // mouse listeners
    }
}
