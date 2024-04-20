package client.controller;

import client.model.LoginModel;
import client.view.ClientApplicationView;
import client.view.LoginView;
import shared.SwingResources;

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
        view.getBtnLogin().addMouseListener(new SwingResources.CursorChanger(view.getBtnLogin()));

        // focus listeners
        view.getTxtUsername().addFocusListener(new SwingResources.TextFieldFocus(view.getTxtUsername(), "Username"));
        view.getTxtPassword().addFocusListener(new SwingResources.PasswordFocusWithCheckbox(view.getTxtPassword(), view.getChkShowPassword(), "Password"));

        view.revalidate();
        view.repaint();
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
