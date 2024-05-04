package client.controller.subpages;

import client.controller.ClientApplicationController;
import client.model.subpages.SettingsModel;
import client.view.subpages.AvatarSelectionView;
import client.view.ClientApplicationView;
import client.view.prompts.ChangePassErrorView;
import client.view.prompts.ChangeProfInfoErrorView;
import client.view.prompts.PassChangeSuccessView;
import client.view.prompts.ProfChangeSuccessView;
import client.view.subpages.SettingsView;
import server.model.DataPB;
import shared.SwingResources;

import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;

public class SettingsController {
    private SettingsView view; // the settings view
    private SettingsModel model; // the settings model
    private ClientApplicationView parentView; // the client application view
    private ClientApplicationController parentController; // the client application controller
    private ProfChangeSuccessView profChangeSuccessView; // the profile changed success prompt view
    private ChangeProfInfoErrorView changeProfInfoErrorView; // the profile changed failed prompt view
    private PassChangeSuccessView passChangeSuccessView; // the password changed success prompt view
    private ChangePassErrorView changePassErrorView; // the password changed failed prompt view
    private AvatarSelectionView avatarSelectionView; // the avatar selection view

    public SettingsController(SettingsView view, SettingsModel model, ClientApplicationView parentView, ClientApplicationController parentController) throws SQLException {
        this.view = view;
        this.model = model;
        this.parentView = parentView;
        this.parentController = parentController;

        // set texts
        this.view.setFullNameText(model.getWfImpl().getFullName(model.getUsername()));     //TEMPORARY ONLY SINCE THERE IS NO GETFULLNAME IN MODEL
        this.view.setGamesPlayedText(model.getMatchesPartTwo());       // POSSIBLE BUG HERE SO I USED A TEMP METHOD
        this.view.setGamesWonText(model.getWinsPartTwo());             // POSSIBLE BUG HERE SO I USED A TEMP METHOD
        this.view.setTotalPointsText(model.getUserPoints());  // THIS IS WORKING
        this.view.setAvatar(model.getPFPOfUser(model.getUsername())); // POSSIBLE BUG HERE SO I USED A TEMP METHOD

        // action listeners
        this.view.setChangeAvatarListener(new ChangeAvatarListener());
        this.view.setEditListener(new EditFullNameListener());
        this.view.setSaveChangesListener(new SaveChangesListener());
        this.view.setChangePassListener(new ChangePassListener());
        this.view.setMusicListener(new MusicButtonListener());

        // mouse listeners
        this.view.getBtnChangeAvatar().addMouseListener(new SwingResources.CursorChanger(view.getBtnChangeAvatar()));
        this.view.getBtnEditChanges().addMouseListener(new SwingResources.CursorChanger(view.getBtnEditChanges()));
        this.view.getBtnSaveChanges().addMouseListener(new SwingResources.CursorChanger(view.getBtnSaveChanges()));
        this.view.getBtnChangePass().addMouseListener(new SwingResources.CursorChanger(view.getBtnChangePass()));
        this.view.getBtnDelAcc().addMouseListener(new SwingResources.CursorChanger(view.getBtnDelAcc()));
        this.view.getBtnMusic().addMouseListener(new SwingResources.CursorChanger(view.getBtnMusic()));

        // focus listeners

        this.view.revalidate();
        this.view.repaint();
        this.parentView.revalidate();
        this.parentView.repaint();
    }

    class ChangeAvatarListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            avatarSelectionView = new AvatarSelectionView(model.getUsername(), new DataPB());
        }
    }

    class EditFullNameListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.getFullNameTextField().setEnabled(true);
            view.getFullNameTextField().setText("");

            Timer timer = new Timer(10000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    view.getFullNameTextField().setText(model.getUsername());
                    view.getFullNameTextField().setEnabled(false);
                }
            });
            timer.setRepeats(false);
            timer.start();
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
                view.clearPasswordFields();
                System.out.println("Passwords Do Not Match!");
                changePassErrorView.main();

                Timer timer = new Timer(4000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        view.getErrorMessageLabel().setVisible(false);
                    }
                });
                timer.setRepeats(false);
                timer.start();
            }
        }
    }

    class MusicButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String currentState = view.getMusicState();

            if (currentState.equals("ON")) {
                view.setMusicState("OFF");
                parentController.stopMusic();
            } else {
                view.setMusicState("ON");
                parentController.playDefaultMusic();
            }
        }
    }
}