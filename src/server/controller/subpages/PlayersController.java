package server.controller.subpages;

import server.model.subpages.PlayersModel;
import server.view.subpages.PlayersView;
import shared.SwingResources;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;

public class PlayersController {
    private PlayersView view;
    private PlayersModel model;

    public PlayersController(PlayersModel model, PlayersView view) {
        this.view = view;
        this.model = model;

        // Add ActionListener to the Add Player button
        view.getFunctionPanel().setAddPlayerListener(new AddPlayerListener());

        view.hideAddPlayerPanel();

        view.revalidate();
        view.repaint();
    }

    // Inner class for handling Add Player button click
    class AddPlayerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Display the AddPlayerPanel
            view.showAddPlayerPanel();

            view.getAddPlayerPanel().setBtnCreateActionListener(new CreateAccountListener());

            view.getAddPlayerPanel().setBtnTxtCancelActionListener(new CancelAddPlayerListener());

            view.getAddPlayerPanel().getTxtUsername().addFocusListener(new SwingResources.TextFieldFocus(view.getAddPlayerPanel().
                    getTxtUsername(), "Username", view.getAddPlayerPanel().getLblErrorMessage()));

            view.getAddPlayerPanel().getTxtFullName().addFocusListener(new SwingResources.TextFieldFocus(view.getAddPlayerPanel().
                    getTxtFullName(), "Name", view.getAddPlayerPanel().getLblErrorMessage()));

            view.getAddPlayerPanel().getTxtPassword().addFocusListener(new SwingResources.PasswordFocusWithCheckbox
                    (view.getAddPlayerPanel().getTxtPassword(), view.getAddPlayerPanel().getChkPassword(), "Password",
                            view.getAddPlayerPanel().getLblErrorMessage()));

            view.getAddPlayerPanel().getTxtConfirmPassword().addFocusListener(new SwingResources.PasswordFocusWithCheckbox
                    (view.getAddPlayerPanel().getTxtConfirmPassword(), view.getAddPlayerPanel().getChkConfirmPassword(),
                            "Confirm Password", view.getAddPlayerPanel().getLblErrorMessage()));
        }
    }

    // Inner class for handling Cancel button click in AddPlayerPanel
    class CancelAddPlayerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Close the AddPlayerPanel
            view.hideAddPlayerPanel();
        }
    }

    // Inner class for handling Create Account button click in AddPlayerPanel
    class CreateAccountListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = view.getAddPlayerPanel().getTxtUsername().getText();
            String fullName = view.getAddPlayerPanel().getTxtFullName().getText();
            String password = new String(view.getAddPlayerPanel().getTxtPassword().getPassword());
            String confirmPassword = new String(view.getAddPlayerPanel().getTxtConfirmPassword().getPassword());

            if (!password.equals(confirmPassword)) {
                view.getAddPlayerPanel().setLblErrorMessage("Passwords do not match!");
                return;
            }

            boolean added = model.addPlayer(username, password, fullName, view.getAddPlayerPanel().getAvatarImagePath());

            if (added) {
                System.out.println("Player added successfully!");
                view.hideAddPlayerPanel();
            } else {
                System.out.println("Player adding failed!");
            }
        }
    }
}