package client.controller.subpages;

import client.controller.ClientApplicationController;
import client.model.subpages.AvatarSelectionModel;
import client.model.subpages.SettingsModel;
import client.view.subpages.AvatarSelectionView;
import client.view.ClientApplicationView;
import client.view.prompts.ChangePassErrorView;
import client.view.prompts.ChangeProfInfoErrorView;
import client.view.prompts.PassChangeSuccessView;
import client.view.prompts.ProfChangeSuccessView;
import client.view.subpages.HomeView;
import client.view.subpages.SettingsView;
import server.model.DataPB;
import shared.SwingResources;

import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;

public class SettingsController {
    private SettingsView settingsView; // the settings view
    private SettingsModel model; // the settings model
    private ClientApplicationView parentView; // the client application view
    private ClientApplicationController parentController; // the client application controller
    private ProfChangeSuccessView profChangeSuccessView; // the profile changed success prompt view
    private ChangeProfInfoErrorView changeProfInfoErrorView; // the profile changed failed prompt view
    private PassChangeSuccessView passChangeSuccessView; // the password changed success prompt view
    private ChangePassErrorView changePassErrorView; // the password changed failed prompt view
    private AvatarSelectionView avatarSelectionView; // the avatar selection view
    private HomeView homeView; // the home view

    public SettingsController(HomeView homeView, SettingsView view, SettingsModel model, ClientApplicationView parentView, ClientApplicationController parentController) throws SQLException {
        this.homeView = homeView;
        this.settingsView = view;
        this.model = model;
        this.parentView = parentView;
        this.parentController = parentController;

        // set texts
        this.settingsView.setFullNameText(model.getFullName());
        this.settingsView.setAvatarImagePath(model.getPFPOfUser(model.getUsername()));
        updateGameStats();

        // action listeners
        this.settingsView.setChangeAvatarListener(new ChangeAvatarListener());
        this.settingsView.setEditListener(new EditFullNameListener());
        this.settingsView.setSaveChangesListener(new SaveChangesListener());
        this.settingsView.setChangePassListener(new ChangePassListener());
        this.settingsView.setMusicListener(new MusicButtonListener());

        // mouse listeners
        this.settingsView.getBtnChangeAvatar().addMouseListener(new SwingResources.CursorChanger(view.getBtnChangeAvatar()));
        this.settingsView.getBtnEditChanges().addMouseListener(new SwingResources.CursorChanger(view.getBtnEditChanges()));
        this.settingsView.getBtnSaveChanges().addMouseListener(new SwingResources.CursorChanger(view.getBtnSaveChanges()));
        this.settingsView.getBtnChangePass().addMouseListener(new SwingResources.CursorChanger(view.getBtnChangePass()));
        this.settingsView.getBtnDelAcc().addMouseListener(new SwingResources.CursorChanger(view.getBtnDelAcc()));
        this.settingsView.getBtnMusic().addMouseListener(new SwingResources.CursorChanger(view.getBtnMusic()));

        // focus listeners

        this.settingsView.revalidate();
        this.settingsView.repaint();
        this.parentView.revalidate();
        this.parentView.repaint();
    }

    public void updateGameStats() {
        this.settingsView.setGamesPlayedText(model.getMatchesPartTwo());
        this.settingsView.setGamesWonText(model.getWinsPartTwo());
        this.settingsView.setTotalPointsText(model.getUserPoints());
    }

    class ChangeAvatarListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            AvatarSelectionView view = new AvatarSelectionView(homeView, settingsView);
            new AvatarSelectionController(view,
                    new AvatarSelectionModel(model.getUsername(), model.getWfImpl()));
            avatarSelectionView = new AvatarSelectionView(homeView , settingsView);
        }
    }

    class EditFullNameListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            settingsView.getFullNameTextField().setEnabled(true);
            settingsView.getFullNameTextField().setText("");

            Timer timer = new Timer(10000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    settingsView.getFullNameTextField().setText(model.getFullName());
                    settingsView.getFullNameTextField().setEnabled(false);
                }
            });
            timer.setRepeats(false);
            timer.start();
        }
    }

    class SaveChangesListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String newFullName = settingsView.getFullNameTextField().getText();
            boolean success = false;

            try {
                success = model.editInfo(model.getUsername(), "fullName", newFullName);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            if (success) {
                settingsView.setFullNameText(newFullName);
                settingsView.getFullNameTextField().setEnabled(false);
                profChangeSuccessView.main();
            } else {
                changeProfInfoErrorView.main();
            }
        }
    }

    class ChangePassListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String newPassword = settingsView.getNewPassword();
            String confirmPassword = settingsView.getConfirmPassword();

            if (newPassword.equals(confirmPassword)) {
                try {
                    boolean success = model.editPassword(model.getUsername(), settingsView.getCurrentPassword(), newPassword);
                    if (success) {
                        settingsView.clearPasswordFields();
                        passChangeSuccessView.main();
                    } else {
                        settingsView.clearPasswordFields();
                        changePassErrorView.main();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else {
                settingsView.getErrorMessageLabel().setVisible(true);
                settingsView.clearPasswordFields();
                changePassErrorView.main();

                Timer timer = new Timer(4000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        settingsView.getErrorMessageLabel().setVisible(false);
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
            String currentState = settingsView.getMusicState();

            if (currentState.equals("ON")) {
                settingsView.setMusicState("OFF");
                parentController.stopMusic();
            } else {
                settingsView.setMusicState("ON");
                parentController.playDefaultMusic();
            }
        }
    }
}