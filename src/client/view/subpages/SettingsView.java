package client.view.subpages;

import shared.SwingStylesheet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SettingsView extends JPanel{
    private JLabel profileLabel; // the label of the profile panel
    private JPanel avatarPanel; // the panel for the avatar image
    private JLabel currentAvatarLabel; // the label of the current avatar
    private JButton btnChangeAvatar; // the change avatar button
    private JLabel personalInfoLabel; // the label of the personal info
    private JLabel fullNameLabel; // the label of the full name
    private JTextField fullNameTextField; // the text field for the full name
    private JButton btnEdit; // the edit button
    private JButton btnSaveChanges; // the save changes button
    private JLabel securityLabel; // the label of the security panel
    private JLabel passwordLabel; // the label of the password
    private JLabel currentPasswordLabel; // the label of the current password
    private JPasswordField currentPasswordTextField; // the password field for the current password
    private JLabel newPasswordLabel; // the label of the new password
    private JPasswordField newPasswordTextField; // the password field for the new password
    private JLabel confirmPasswordLabel; // the label of the confirmed password
    private JPasswordField confirmPasswordTextField; // the password field for the confirmed password
    private JLabel errorMessageLabel; // the label for the error message
    private JButton btnChangePass; // the change password button
    private JLabel titleLabel; // the label of the stats panel
    private JLabel gamesPlayedLabel; // the label of the games played
    private JLabel gplValue; // the label of the games played's value
    private JLabel gamesWonLabel; // the label of the games won
    private JLabel gwlValue; // the label of the games won's value
    private JLabel totalPointsLabel; // the label of the total points
    private JLabel tplValue; // the label of the total points' value
    private JPanel statsPanel; // the panel for the stats
    private SwingStylesheet style = new SwingStylesheet(); // the stylesheet
    private final int arcWidth = 20; // the width for the rounded corner


    public SettingsView(){
        this.setBackground(style.deepSkyBlue);
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTHWEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        profileLabel = style.createLblH5("PROFILE", style.white);
        gbc.insets = new Insets(-10, 0, 0, 0);
        this.add(profileLabel, gbc);

        gbc.gridy++;
        ProfPanel profPanel = new ProfPanel();
        gbc.insets = new Insets(0, 0, 0, 0);
        this.add(profPanel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        securityLabel = style.createLblH5("SECURITY", style.white);
        gbc.insets = new Insets(-10, 100, 0, 0);
        this.add(securityLabel, gbc);

        gbc.gridy++;
        SecPanel secPanel = new SecPanel();
        gbc.insets = new Insets(0, 100, 0, 0);
        this.add(secPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        StatsPanel statsPanel = new StatsPanel();
        gbc.insets = new Insets(20, 30, 0, 0);
        this.add(statsPanel, gbc);

        this.setPreferredSize(new Dimension(1200, 550));
    }

    class ProfPanel extends JPanel {
        public ProfPanel() {
            this.setBackground(style.white);
            this.setLayout(new GridBagLayout());
            this.setOpaque(false);

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(20, -30, 1, 1);
            gbc.anchor = GridBagConstraints.NORTHWEST;

            gbc.gridx = 0;
            gbc.gridy = 0;
            avatarPanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Graphics2D g2d = (Graphics2D) g.create();

                    int diameter = Math.min(getWidth(), getHeight()) - 10;
                    int x = (getWidth() - diameter) / 2;
                    int y = (getHeight() - diameter) / 2;

                    g2d.setColor(style.white);
                    g2d.fillOval(x, y, diameter, diameter);

                    g2d.dispose();
                }
            };
            avatarPanel.setPreferredSize(new Dimension(110, 110));
            avatarPanel.setBackground(style.white);
            avatarPanel.setBorder(BorderFactory.createLineBorder(style.deepSkyBlue,3));
            this.add(avatarPanel, gbc); // initial placeholder for the avatar image

            gbc.gridx++;
            gbc.insets = new Insets(20, 20, 0, 0);
            currentAvatarLabel = style.createLblH1("Current Avatar", style.black);
            this.add(currentAvatarLabel, gbc);

            gbc.gridy++;
            gbc.insets = new Insets(-70, 20, 0, 0);
            btnChangeAvatar = style.createBtnRounded("CHANGE AVATAR", style.deepSkyBlue, style.white, 10);
            btnChangeAvatar.setPreferredSize(new Dimension(150, 30));
            this.add(btnChangeAvatar, gbc);

            gbc.gridy++;
            gbc.insets = new Insets(40, -100, 10, 10);
            personalInfoLabel = style.createLblH2("Personal Information", style.black);
            this.add(personalInfoLabel, gbc);

            gbc.gridy++;
            gbc.insets = new Insets(5, -100, 0, 0);
            fullNameLabel = style.createLblH3("Full Name", style.black);
            this.add(fullNameLabel, gbc);

            gbc.gridy++;
            gbc.insets = new Insets(10, -100, 0, 0);
            fullNameTextField = new JTextField(20);
            fullNameTextField.setPreferredSize(new Dimension(300, 30));
            fullNameTextField.setEnabled(false);
            this.add(fullNameTextField, gbc);

            gbc.gridx = 2;
            gbc.insets = new Insets(10, -40, 0, 0);
            btnEdit = style.createBtnIconOnly(style.iconEdit, 20,20);
            this.add(btnEdit, gbc);

            // resets grid x-position and increment y-position
            gbc.gridx = 0;
            gbc.gridy++;
            gbc.gridwidth = 3;
            gbc.anchor = GridBagConstraints.CENTER;
            gbc.insets = new Insets(55, 0, 0, 0);

            btnChangePass = style.createBtnRounded("SAVE CHANGES", style.deepSkyBlue, style.white, 10);
            btnChangePass.setPreferredSize(new Dimension(180,40));
            this.add(btnChangePass, gbc);
            this.setFocusable(true);

            gbc.gridy++;
            errorMessageLabel = style.createLblH3("Full name must only contain alphanumeric characters.", style.black);
            errorMessageLabel.setForeground(Color.RED);
            errorMessageLabel.setVisible(false); // initially hides the error message
            this.add(errorMessageLabel, gbc);

            this.setPreferredSize(new Dimension(400, 400));
            this.setMaximumSize(new Dimension(400, 400));
            this.setMinimumSize(new Dimension(400, 400));
        }

        // enables the panel to have rounded corners
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g.create();

            int width = getWidth();
            int height = getHeight();

            g2d.setColor(style.white);

            g2d.fillRoundRect(0, 0, width, height, arcWidth, arcWidth);

            g2d.dispose();
        }
    }

    class SecPanel extends JPanel{
        public SecPanel() {
            this.setBackground(style.white);
            this.setLayout(new GridBagLayout());
            this.setOpaque(false);

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, -20, 5, 0);

            passwordLabel = style.createLblH1("Password", style.black);
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.anchor = GridBagConstraints.NORTHWEST;
            this.add(passwordLabel, gbc);

            gbc.gridy++;
            gbc.gridwidth = 3;
            gbc.insets = new Insets(15, -20, 0, 0);
            currentPasswordLabel = style.createLblH3("Current Password", style.black);
            this.add(currentPasswordLabel, gbc);

            gbc.gridy++;
            currentPasswordTextField = new JPasswordField(25);
            currentPasswordTextField.setPreferredSize(new Dimension(300, 30));
            this.add(currentPasswordTextField, gbc);

            gbc.gridy++;
            newPasswordLabel = style.createLblH3("New Password", style.black);
            this.add(newPasswordLabel, gbc);

            gbc.gridy++;
            newPasswordTextField = new JPasswordField(25);
            newPasswordTextField.setPreferredSize(new Dimension(300, 30));
            this.add(newPasswordTextField, gbc);

            gbc.gridy++;
            confirmPasswordLabel = style.createLblH3("Confirm New Password", style.black);
            this.add(confirmPasswordLabel, gbc);

            gbc.gridy++;
            confirmPasswordTextField = new JPasswordField(25);
            confirmPasswordTextField.setPreferredSize(new Dimension(300, 30));
            this.add(confirmPasswordTextField, gbc);

            gbc.gridy++;
            errorMessageLabel = new JLabel("Passwords do not match. Try again.");
            errorMessageLabel.setForeground(Color.RED);
            errorMessageLabel.setVisible(false); // initially hides the error message
            this.add(errorMessageLabel, gbc);

            // resets grid x-position and increment y-position
            gbc.gridx = 0;
            gbc.gridy++;
            gbc.gridwidth = 3;
            gbc.anchor = GridBagConstraints.CENTER;
            gbc.insets = new Insets(35, 0, 0, 0);

            btnChangePass = style.createBtnRounded("CHANGE PASSWORD", style.deepSkyBlue, style.white, 10);
            btnChangePass.setPreferredSize(new Dimension(180,40));
            this.add(btnChangePass, gbc);
            this.setFocusable(true);

            this.setPreferredSize(new Dimension(400, 400));
            this.setMaximumSize(new Dimension(400, 400));
            this.setMinimumSize(new Dimension(400, 400));
        }

        // enables the panel to have rounded corners
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g.create();

            int width = getWidth();
            int height = getHeight();

            g2d.setColor(style.white);

            g2d.fillRoundRect(0, 0, width, height, arcWidth, arcWidth);

            g2d.dispose();
        }
    }

    class StatsPanel extends JPanel {
        public StatsPanel() {
            this.setBackground(style.white);
            this.setLayout(new GridBagLayout());
            this.setOpaque(false);

            titleLabel = style.createLblH1("Player Stats", style.black);
            titleLabel.setHorizontalAlignment(JLabel.CENTER);
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 2;
            gbc.fill = GridBagConstraints.WEST;
            gbc.insets = new Insets(10, 10, 10, 100);
            this.add(titleLabel, gbc);

            gamesPlayedLabel = style.createLblH3("Games Played: ", style.black);
            gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.anchor = GridBagConstraints.WEST;
            gbc.insets = new Insets(0, 10, 0, 10);
            this.add(gamesPlayedLabel, gbc);

            gplValue = style.createLblH3("50", style.black);
            gbc = new GridBagConstraints();
            gbc.gridx = 1;
            gbc.gridy = 1;
            gbc.anchor = GridBagConstraints.WEST;
            gbc.insets = new Insets(0, 0, 0, 10);
            this.add(gplValue, gbc);

            gamesWonLabel = style.createLblH3("Games Won: ", style.black);
            gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.anchor = GridBagConstraints.WEST;
            gbc.insets = new Insets(0, 10, 0, 10);
            this.add(gamesWonLabel, gbc);

            gwlValue = style.createLblH3("12", style.black);
            gbc = new GridBagConstraints();
            gbc.gridx = 1;
            gbc.gridy = 2;
            gbc.anchor = GridBagConstraints.WEST;
            gbc.insets = new Insets(0, 0, 0, 10);
            this.add(gwlValue, gbc);

            totalPointsLabel = style.createLblH3("Total Points: ", style.black);
            gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 3;
            gbc.anchor = GridBagConstraints.WEST;
            gbc.insets = new Insets(0, 10, 10, 10);
            this.add(totalPointsLabel, gbc);

            tplValue = style.createLblH3("42069", style.black);
            gbc = new GridBagConstraints();
            gbc.gridx = 1;
            gbc.gridy = 3;
            gbc.anchor = GridBagConstraints.WEST;
            gbc.insets = new Insets(0, 0, 10, 10);
            this.add(tplValue, gbc);

            this.setPreferredSize(new Dimension(350, 170));
            this.setMaximumSize(new Dimension(350, 170));
            this.setMinimumSize(new Dimension(350, 170));
        }

        // enables the panel to have rounded corners
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g.create();

            int width = getWidth();
            int height = getHeight();

            g2d.setColor(style.white);

            g2d.fillRoundRect(0, 0, width, height, arcWidth, arcWidth);

            g2d.dispose();
        }
    }

    // retrieves the current JButton of btnChangeAvatar
    // return the current btnChangeAvatar
    public JButton getBtnChangeAvatar() {
        return btnChangeAvatar;
    }

    // sets a specified action listener for btnChangeAvatar
    public void setChangeAvatarListener(ActionListener actionListener) {
        btnChangeAvatar.addActionListener(actionListener);
    }

    // retrieves the current JButton of btnEdit
    // return the current btnEdit
    public JButton getBtnEdit() {
        return btnEdit;
    }

    // sets a specified action listener for btnEdit
    public void setEditListener(ActionListener actionListener) {
        btnEdit.addActionListener(actionListener);
    }

    // retrieves the current JButton of btnSaveChanges
    // return the current btnSaveChanges
    public JButton getBtnSaveChanges() {
        return btnSaveChanges;
    }

    // sets a specified action listener for btnSaveChanges
    public void setSaveChangesListener(ActionListener actionListener) {
        btnSaveChanges.addActionListener(actionListener);
    }

    // retrieves the current JButton of btnChangePass
    // return the current btnChangePass
    public JButton getBtnChangePass() {
        return btnChangePass;
    }

    // sets a specified action listener for btnChangePass
    public void setChangePassListener(ActionListener actionListener) {
        btnChangePass.addActionListener(actionListener);
    }

    // sets a specified text for gplValue
    public void setGamesPlayedText(String text) {
        gplValue.setText(text);
    }

    // sets a specified text for gwlValue
    public void setGamesWonText(String text) {
        gwlValue.setText(text);
    }

    // sets a specified text for tplValue
    public void setTotalPointsText(String text) {
        tplValue.setText(text);
    }
}