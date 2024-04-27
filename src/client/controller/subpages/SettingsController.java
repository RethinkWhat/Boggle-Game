package client.controller.subpages;

import client.controller.LoginController;
import client.model.subpages.SettingsModel;
import client.view.ClientApplicationView;
import client.view.prompts.ChangePassErrorView;
import client.view.prompts.ChangeProfInfoErrorView;
import client.view.prompts.PassChangeSuccessView;
import client.view.prompts.ProfChangeSuccessView;
import client.view.subpages.SettingsView;
import shared.SwingResources;

import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;

public class SettingsController {
    private SettingsView view;
    private SettingsModel model;
    private ClientApplicationView parentView;
    private ProfChangeSuccessView profChangeSuccessView;
    private ChangeProfInfoErrorView changeProfInfoErrorView;
    private PassChangeSuccessView passChangeSuccessView;
    private ChangePassErrorView changePassErrorView;

    public SettingsController(SettingsView view, SettingsModel model, ClientApplicationView parentView) throws SQLException {
        this.view = view;
        this.model = model;
        this.parentView = parentView;

        // set texts
        this.view.setFullNameText(model.getUsername());     //TEMPORARY ONLY SINCE THERE IS NO GETFULLNAME IN MODEL
        this.view.setGamesPlayedText(model.getMatchesPartTwo());       // POSSIBLE BUG HERE
        this.view.setGamesWonText(model.getWinsPartTwo());             // POSSIBLE BUG HERE
        this.view.setTotalPointsText(model.getUserPoints());  // THIS IS WORKING

        // action listeners
        this.view.setChangeAvatarListener(new ChangeAvatarListener());
        this.view.setEditListener(new EditFullNameListener());
        this.view.setSaveChangesListener(new SaveChangesListener());
        this.view.setChangePassListener(new ChangePassListener());

        // mouse listeners
        this.view.getBtnChangeAvatar().addMouseListener(new SwingResources.CursorChanger(view.getBtnChangeAvatar()));
        this.view.getBtnEditChanges().addMouseListener(new SwingResources.CursorChanger(view.getBtnEditChanges()));
        this.view.getBtnSaveChanges().addMouseListener(new SwingResources.CursorChanger(view.getBtnSaveChanges()));
        this.view.getBtnChangePass().addMouseListener(new SwingResources.CursorChanger(view.getBtnChangePass()));
        this.view.getBtnDelAcc().addMouseListener(new SwingResources.CursorChanger(view.getBtnDelAcc()));
        this.view.getBtnMusic().addMouseListener(new SwingResources.CursorChanger(view.getBtnMusic()));

        // focus listeners
        this.view.getFullNameTextField().addFocusListener(new FullNameTextFieldFocusListener());
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
                System.out.println("Avatar successfully changed!");
                profChangeSuccessView.main();
            }
        }
    }

    class EditFullNameListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.getFullNameTextField().setEnabled(true);
            view.getFullNameTextField().setText("");
        }
    }

    class SaveChangesListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String newFullName = view.getFullNameTextField().getText();
            boolean success = false;
            try {
                success = model.editInfo(model.getUsername(), "fullName", newFullName);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            if (success) {
                System.out.println("FullName Change Success!");
                view.setFullNameText(newFullName);
                view.getFullNameTextField().setEnabled(false);
                profChangeSuccessView.main();
            } else {
                System.out.println("FullName Change Failed!");
                changeProfInfoErrorView.main();
            }
        }
    }

    class ChangePassListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String newPassword = view.getNewPassword();
            String confirmPassword = view.getConfirmPassword();
            if (newPassword.equals(confirmPassword)) {
                try {
                    boolean success = model.editPassword(model.getUsername(), view.getCurrentPassword(), newPassword);
                    if (success) {
                        System.out.println("Change Password Success!");
                        view.clearPasswordFields();
                        passChangeSuccessView.main();
                    } else {
                        System.out.println("Change Password Failed!");
                        view.clearPasswordFields();
                        changePassErrorView.main();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else {
                view.getErrorMessageLabel().setVisible(true);
                view.getErrorMessageLabel().transferFocus();
                view.clearPasswordFields();
                System.out.println("Passwords Do Not Match!");
                changePassErrorView.main();
            }
        }
    }

    class FullNameTextFieldFocusListener implements FocusListener {
        @Override
        public void focusGained(FocusEvent e) {

        }

        @Override
        public void focusLost(FocusEvent e) {
            view.getFullNameTextField().setEnabled(false);
        }
    }
}