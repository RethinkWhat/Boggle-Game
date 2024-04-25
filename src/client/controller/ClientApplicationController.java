package client.controller;

import client.controller.subpages.HomeController;
import client.controller.subpages.HowToPlayController;
import client.controller.subpages.SettingsController;
import client.model.ClientApplicationModel;
import client.model.subpages.HomeModel;
import client.model.subpages.HowToPlayModel;
import client.model.subpages.SettingsModel;
import client.view.ClientApplicationView;
import shared.SwingResources;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The ClientApplicationController controls application navigation and holds all the sub-controllers that control
 * their respective subviews and submodels.
 */
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
     * The home controller.
     */
    private HomeController homeController;
    /**
     * The how to play controller.
     */
    private HowToPlayController howToPlayController;
    /**
     * The setting controller.
     */
    private SettingsController settingsController;

    /**
     * Constructs a ClientApplicationController with a specified view.
     * @param view The specified view.
     */
    public ClientApplicationController(ClientApplicationView view, ClientApplicationModel model) {
        this.model = model;
        this.view = view;

        SwingUtilities.invokeLater(() -> {
            homeController = new HomeController(view.getHomeView(), new HomeModel(), this);
            howToPlayController = new HowToPlayController(view.getHowToPlayView(), new HowToPlayModel(), this);
            settingsController = new SettingsController(view.getSettingsView(),
                    new SettingsModel(model.getUsername(), model.getWfImpl()), view);
        });

        // action listeners
        view.setSettingsListener(new SettingsListener());
        view.setHomeListener(new HomeListener());
        view.setLogoutListener(new LogOutListener());

        // mouse listeners
        view.getBtnNavHome().addMouseListener(new SwingResources.CursorChanger(view.getBtnNavHome()));
        view.getBtnNavSettings().addMouseListener(new SwingResources.CursorChanger(view.getBtnNavSettings()));
        view.getBtnNavLogout().addMouseListener(new SwingResources.CursorChanger(view.getBtnNavLogout()));

        view.revalidate();
        view.repaint();
    }

    /**
     * Navigates the application to the SettingsView.
     */
    class SettingsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Switch to the settings view
            SwingUtilities.invokeLater(() -> {
                // new SettingsController(new SettingsView(), new SettingsModel());
            });
            view.showSettings();
            view.setNavLocationText("Settings");
        }
    }

    /**
     * Navigates the application to the HomeView.
     */
    class HomeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.showHome();
            view.setNavLocationText("Home");
        }
    }

    /**
     * Logs the user out of the application.
     */
    class LogOutListener implements ActionListener {
        //TEMPORARY PROMPT FOR LOGOUT
        @Override
        public void actionPerformed(ActionEvent e) {
            int choice = JOptionPane.showConfirmDialog(
                    view,
                    "Are you sure you want to log out?",
                    "TEMPORARY PROMPT",
                    JOptionPane.YES_NO_OPTION
            );

            if (choice == JOptionPane.YES_OPTION) {
                view.dispose();
            }
        }
    }

    /**
     * Retrieves the current ClientApplicationView.
     * @return The current view.
     */
    public ClientApplicationView getView() {
        return view;
    }

    /**
     * Retrieves the current ClientApplicationModel.
     * @return The curren model.
     */
    public ClientApplicationModel getModel() {
        return model;
    }
}
