package client.controller;

import client.model.ClientApplicationModel;
import client.model.LoginModel;
import client.view.ClientApplicationView;
import client.view.LoginView;
import shared.SwingResources;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Arrays;

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
                if (model.validateAccount(view.getTxtUsername().getText(), Arrays.toString(view.getTxtPassword().getPassword()))){
                    SwingUtilities.invokeLater(() -> new ClientApplicationController(new ClientApplicationView(),
                            new ClientApplicationModel(username, model.getWfImpl())));
                    view.dispose();
                } else {
                    SwingUtilities.invokeLater(() -> {
                        view.setErrorMessage("Wrong credentials. Try again.");
                        view.getTxtPassword().setText("Password");
                        view.getTxtPassword().setEchoChar((char) 0);
                        view.getChkShowPassword().setSelected(false);
                    });
                }
            } catch (SQLException sqle){
                sqle.printStackTrace();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
