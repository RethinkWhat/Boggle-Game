package client.controller.subpages;

import client.controller.LoginController;
import client.model.subpages.SettingsModel;
import client.view.ClientApplicationView;
import client.view.subpages.SettingsView;
import shared.SwingResources;

public class SettingsController {
    private SettingsView view;
    private SettingsModel model;
    private ClientApplicationView parentView;

    public SettingsController(SettingsView view, SettingsModel model, ClientApplicationView parentView) {
        this.view = view;
        this.model = model;
        this.parentView = parentView;

        // action listeners


        // mouse listeners


        // focus listeners
    }
}
