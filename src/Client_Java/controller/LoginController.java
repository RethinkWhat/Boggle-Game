package Client_Java.controller;

import Client_Java.model.ClientApplicationModel;
import Client_Java.model.LoginModel;
import Client_Java.view.ClientApplicationView;
import Client_Java.view.LoginView;
import shared.SwingResources;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

/**
 * The LoginController processes user requests when trying to access the application through their existing account.
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
        view.setPasswordListener(new LoginListener());
        view.setLoginListener(new LoginListener());
        view.setShowPasswordListener(new SwingResources.ShowPasswordListener(view.getChkShowPassword(),
                view.getTxtPassword()));

        // mouse listeners
        view.getBtnLogin().addMouseListener(new SwingResources.CursorChanger(view.getBtnLogin()));

        // focus listeners
        view.getTxtUsername().addFocusListener(new SwingResources.TextFieldFocus(view.getTxtUsername(),
                "Username", view.getLblErrorMessage()));
        view.getTxtPassword().addFocusListener(new SwingResources.PasswordFocusWithCheckbox(view.getTxtPassword(),
                view.getChkShowPassword(), "Password", view.getLblErrorMessage()));

        view.revalidate();
        view.repaint();
    }

    /**
     * Processes the request for loggin in.
     *
     * FOR CODE CLEANING
     */
    class LoginListener implements ActionListener {
        /**
         * T
         * @param e The specified action event.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = view.getTxtUsername().getText();
            try {
                String msg = model.validateAccount(view.getTxtUsername().getText(), view.getTxtPassword().getText());
                if (msg.equals("valid")) {
                    SwingUtilities.invokeLater(() -> new ClientApplicationController(new ClientApplicationView(),
                            new ClientApplicationModel(username.toLowerCase(Locale.ROOT), model.getWfImpl())));
                    view.dispose();
                } else {
                    SwingUtilities.invokeLater(() -> {
                        view.setErrorMessage(msg);
                        view.getTxtPassword().setText("Password");
                        view.getTxtPassword().setEchoChar((char) 0);
                        view.getChkShowPassword().setSelected(false);
                    });
                }
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
