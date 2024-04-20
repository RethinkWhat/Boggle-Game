package client_java;

import client_java.controller.LoginController;
import client_java.model.LoginModel;
import client_java.view.LoginView;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        new Main();
    }

    public Main() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                final LoginView view = new LoginView();
                final LoginModel model = new LoginModel();
                final LoginController controller = new LoginController(view, model);
            }
        });
    }
}
