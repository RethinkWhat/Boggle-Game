package client_java.controller;

import client_java.model.LoginModel;
import client_java.view.ClientApplicationView;
import client_java.view.LoginView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * TODO: Documentation
 */
public class LoginController {
    /**
     * The view.
     */
    private LoginView view;
    /**
     * The model.
     */
    private LoginModel model;

    /**
     * Constructs a LoginController with a specified view and model.
     * @param view The specified view.
     * @param model The specified model.
     */
    public LoginController(LoginView view, LoginModel model) {
        this.view = view;
        this.model = model;

        // action listeners
        view.setLoginListener(new LoginListener());

        // mouse listeners

        // focus listeners
    }

    /**
     * Processes the request for loggin in.
     */
    class LoginListener implements ActionListener {
        /**
         * T
         * @param e The specified action event.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            // backend implementation
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    final ClientApplicationView view = new ClientApplicationView();
                    final ClientApplicationController controller = new ClientApplicationController(view);
                }
            });
            view.dispose();
        }
    }
}