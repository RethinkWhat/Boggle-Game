package client.controller.subpages;

import client.controller.LoginController;
import client.model.subpages.SettingsModel;
import client.view.ClientApplicationView;
import client.view.prompts.ProfChangeSuccessView;
import client.view.subpages.SettingsView;
import shared.SwingResources;

import javax.swing.*;
import java.awt.event.*;

public class SettingsController {
    private SettingsView view;
    private SettingsModel model;
    private ClientApplicationView parentView;
    private ProfChangeSuccessView profChangeSuccessView;

    public SettingsController(SettingsView view, SettingsModel model, ClientApplicationView parentView) {
        this.view = view;
        this.model = model;
        this.parentView = parentView;

        // set texts
        this.view.setFullNameText(model.getUsername());         //TEMPORARY ONLY SINCE THERE IS NO GETFULLNAME IN MODEL
        this.view.setGamesPlayedText(model.getMatches());       // POSSIBLE BUG HERE
        this.view.setGamesWonText(model.getWins());             // POSSIBLE BUG HERE
        this.view.setTotalPointsText(model.getUserPoints());  // THIS IS WORKING

        // action listeners
        this.view.setChangeAvatarListener(new ChangeAvatarListener());
        this.view.setEditListener(new EditFullNameListener());
        this.view.setSaveChangesListener(new SaveChangesListener());
        this.view.setChangePassListener(new ChangePassListener());

        // mouse listeners

        // focus listeners

    }

    class ChangeAvatarListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            // LOGIC FOR OPENING A VIEW THAT HOLDS THE AVATAR CHANGE FEATURE; BELOW IS THE LOGIC AFTER CONFIRMING

            int choice = JOptionPane.showConfirmDialog(
                    view,
                    "Are you sure you want to change your avatar?",
                    "TEMPORARY PROMPT",
                    JOptionPane.YES_NO_OPTION
            );

            if (choice == JOptionPane.YES_OPTION) {
                System.out.println("Avatar successfully changed...");
                profChangeSuccessView.main();
            }
        }
    }

    class EditFullNameListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.getFullNameTextField().setEnabled(true);
            view.getErrorMessageLabel().setText("");

            System.out.println("Editing full name...");
        }
    }

    class SaveChangesListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int choice = JOptionPane.showConfirmDialog(
                    view,
                    "Are you sure you want to save changes?",
                    "TEMPORARY PROMPT",
                    JOptionPane.YES_NO_OPTION
            );

            if (choice == JOptionPane.YES_OPTION) {
                System.out.println("Full name successfully changed...");

                // LOGIC FOR FULL NAME CHANGE SOON

                profChangeSuccessView.main();
            }
        }
    }

    class ChangePassListener implements ActionListener {        //NEWPASS == CONFIRMPASS IS WORKING BUT NOT THE REST
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = model.getUsername();
            String oldPass = view.getCurrentPassword();
            String newPass = view.getNewPassword();
            String confirmPass = view.getConfirmPassword();

            if (!newPass.equals(confirmPass)) {
                System.out.println("New password and confirmation password do not match.");
                return;
            }

            try {
                if (model.editPassword(username, oldPass, newPass)) {
                    System.out.println("Password changed successfully!");
                } else {
                    System.out.println("Failed to change password. Please try again.");
                }
            } catch (Exception ex) {
                System.out.println("An error occurred: " + ex.getMessage());
            }
        }
    }
}