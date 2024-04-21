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
                if (model.validateAccount(view.getTxtUsername().getText(), view.getTxtPassword().getText())){
                    new ClientApplicationController(new ClientApplicationView(), new ClientApplicationModel(username, model.getWfImpl()));
                    view.dispose();
                }else {
                    //Error message na gagawin palang ni pat
                }
            }catch (SQLException sqle){
                sqle.printStackTrace();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }

            /*
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    final ClientApplicationView view = new ClientApplicationView();
                    final ClientApplicationModel model = new ClientApplicationModel(username);
                    final ClientApplicationController controller = new ClientApplicationController(view, model);
                }
            });
            view.dispose();

             */
        }
    }
}
