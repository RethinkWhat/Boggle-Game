package client.view.subpages;

import shared.SwingStylesheet;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SettingsView extends JPanel{
    private JLabel profileLabel; // the label of the profile panel
    private JButton btnAccMa; // the manage account button
    private JButton btnDelAcc; // the delete account button
    private JButton btnMusic; // the music toggle button
    private JPanel avatarPanel; // the panel for the avatar image
    private JLabel currentAvatarLabel; // the label of the current avatar
    private JButton btnChangeAvatar; // the change avatar button
    private JLabel personalInfoLabel; // the label of the personal info
    private JLabel fullNameLabel; // the label of the full name
    private JTextField fullNameTextField; // the text field for the full name
    private JLabel securityLabel; // the label of the security panel
    private JLabel passwordLabel; // the label of the password
    private JLabel currentPasswordLabel; // the label of the current password
    private JPasswordField currentPasswordTextField; // the password field for the current password
    private JLabel newPasswordLabel; // the label of the new password
    private JPasswordField newPasswordTextField; // the password field for the new password
    private JLabel confirmPasswordLabel; // the label of the confirmed password
    private JPasswordField confirmPasswordTextField; // the password field for the confirmed password
    private JLabel errorMessageLabel; // the label for the error message
    private JButton btnEdit; // the edit button
    private JButton btnSaveChanges; // the save changes button
    private JButton btnChangePass; // the change password button
    private JLabel statsLabel; // the label of the stats panel
    private JLabel gamesPlayedLabel; // the label of the games played
    private JLabel gplValue; // the label of the games played value
    private JLabel gamesWonLabel; // the label of the games won
    private JLabel gwlValue; // the label of the games won's value
    private JLabel totalPointsLabel; // the label of the total points
    private JLabel tplValue; // the label of the total points' value
    private JPanel statsPanel; // the panel for the stats
    private SwingStylesheet style = new SwingStylesheet(); // the stylesheet
    private final int arcWidth = 20; // the width for the rounded corner

    // constructor for the SettingsView
    public SettingsView(){
        this.setBackground(style.deepSkyBlue);
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTHWEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 3;
        gbc.insets = new Insets(-21, -71, 0, 50);
        LeftPanel leftPanel = new LeftPanel();
        this.add(leftPanel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        profileLabel = style.createLblH5("PROFILE", style.white);
        gbc.insets = new Insets(5, 0, 0, 0);
        this.add(profileLabel, gbc);

        gbc.gridy++;
        ProfPanel profPanel = new ProfPanel();
        gbc.insets = new Insets(0, 0, 0, 0);
        this.add(profPanel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        securityLabel = style.createLblH5("SECURITY", style.white);
        gbc.insets = new Insets(5, 500, 0, 0);
        this.add(securityLabel, gbc);

        gbc.gridy++;
        SecPanel secPanel = new SecPanel();
        gbc.insets = new Insets(0, 500, 0, 0);
        this.add(secPanel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        statsPanel = new StatsPanel();
        gbc.insets = new Insets(20, 0, 0, 0);
        this.add(statsPanel, gbc);

        this.setPreferredSize(new Dimension(1200, 550));
    }

    public class LeftPanel extends JPanel {
        // constructs a panel of LeftPanel
        public LeftPanel() {
            this.setBackground(style.mustard);
            this.setLayout(new BorderLayout());

            JPanel leftPanelContainer = new JPanel(new BorderLayout());
            leftPanelContainer.setBackground(style.mustard);

            JPanel container = new JPanel();
            container.setLayout(new GridBagLayout());
            container.setBackground(style.mustard);
            container.setBorder(style.padding);

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.anchor = GridBagConstraints.WEST;

            gbc.gridy++;
            gbc.insets = new Insets(0, -10, 10, 0);
            btnAccMa = navButtonWithLabel(container, style.iconAccMan, "Account Management", gbc);

            gbc.gridy++;
            gbc.insets = new Insets(0, -10, 15, 0);
            btnDelAcc = navButtonWithLabel(container, style.iconAccMan, "Delete Account", gbc);

            gbc.gridy++;
            gbc.insets = new Insets(0, -10, 0, 0);
            btnMusic = navButtonWithLabel(container, style.iconMusic, "Music: " + "ON", gbc);

            leftPanelContainer.add(container, BorderLayout.CENTER);

            this.add(leftPanelContainer, BorderLayout.NORTH);

            this.setPreferredSize(new Dimension(260, 714));
        }

        // joins the buttons' icons and text labels in a container
        private JButton navButtonWithLabel(JPanel container, Icon icon, String labelText, GridBagConstraints gbc) {
            JButton button = style.createBtnIconOnly((ImageIcon) icon, 30, 30);

            JLabel label = new JLabel(labelText);
            label.setFont(new Font("Arial", Font.BOLD, 16));
            label.setForeground(style.white);

            gbc.anchor = GridBagConstraints.WEST;
            gbc.gridx = 0;
            gbc.insets = new Insets(10, -10, 0, 0);
            container.add(button, gbc);

            gbc.gridx = 1;
            gbc.insets = new Insets(10, -10, 0, 0);
            container.add(label, gbc);

            return button;
        }
    }

    public class ProfPanel extends JPanel {
        // constructs a panel of ProfPanel
        public ProfPanel() {
            this.setBackground(style.white);
            this.setLayout(new GridBagLayout());
            this.setOpaque(false);

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(20, 15, 1, 1);
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

                    try {
                        BufferedImage image = ImageIO.read(new File("res/drawable/images/pfp-male-1.png"));
                        int newWidth = (int) (diameter * 1.1);
                        int newHeight = (int) (diameter * 1.1);
                        int newX = x + (diameter - newWidth) / 2;
                        int newY = y + (diameter - newHeight) / 2;
                        g2d.drawImage(image, newX, newY, newWidth, newHeight, null);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    g2d.dispose();
                }
            };
            avatarPanel.setPreferredSize(new Dimension(110, 110));
            avatarPanel.setBackground(style.white);
            this.add(avatarPanel, gbc);

            gbc.gridx++;
            gbc.insets = new Insets(20, 10, 0, 0);
            currentAvatarLabel = style.createLblH1("Current Avatar", style.black);
            this.add(currentAvatarLabel, gbc);

            gbc.gridy++;
            gbc.insets = new Insets(-60, 20, 0, 0);
            btnChangeAvatar = style.createBtnRounded("CHANGE AVATAR", style.deepSkyBlue, style.white, 10);
            btnChangeAvatar.setPreferredSize(new Dimension(180, 30));
            btnChangeAvatar.setFocusable(true);
            this.add(btnChangeAvatar, gbc);

            gbc.gridy++;
            gbc.insets = new Insets(20, -100, 10, 10);
            personalInfoLabel = style.createLblH2("Personal Information", style.black);
            this.add(personalInfoLabel, gbc);

            gbc.gridy++;
            gbc.insets = new Insets(5, -100, 0, 0);
            fullNameLabel = style.createLblH3("Full Name", style.black);
            this.add(fullNameLabel, gbc);

            gbc.gridy++;
            gbc.insets = new Insets(10, -100, 0, -50);
            fullNameTextField = new JTextField(25);
            fullNameTextField.setText("Ramon, Ang Tagabantay");
            fullNameTextField.setPreferredSize(new Dimension(300, 30));
            fullNameTextField.setEnabled(false);
            fullNameTextField.setFocusable(true);
            this.add(fullNameTextField, gbc);

            gbc.gridx = 1;
            gbc.anchor = GridBagConstraints.EAST;
            gbc.insets = new Insets(10, -130, 0, 0);
            btnEdit = style.createBtnIconOnly(style.iconEdit, 20,20);
            this.add(btnEdit, gbc);

            gbc.gridy++;
            gbc.anchor = GridBagConstraints.CENTER;
            errorMessageLabel = new JLabel("Full name must only contain");
            errorMessageLabel.setForeground(Color.RED);
            errorMessageLabel.setVisible(false);
            gbc.insets = new Insets(40, -120, -10, 0);
            this.add(errorMessageLabel, gbc);

            gbc.gridy++;
            gbc.anchor = GridBagConstraints.CENTER;
            errorMessageLabel = new JLabel("alphanumeric characters.");
            errorMessageLabel.setForeground(Color.RED);
            errorMessageLabel.setVisible(false);
            gbc.insets = new Insets(5, -120, -10, 0);
            this.add(errorMessageLabel, gbc);

            // resets grid x-position and increment y-position
            gbc.gridx = 0;
            gbc.gridy++;
            gbc.gridwidth = 3;
            gbc.anchor = GridBagConstraints.CENTER;
            gbc.insets = new Insets(20, 0, 0, 0);

            btnSaveChanges = style.createBtnRounded("SAVE CHANGES", style.deepSkyBlue, style.white, 10);
            btnSaveChanges.setPreferredSize(new Dimension(200,40));
            btnSaveChanges.setFocusable(true);
            this.add(btnSaveChanges, gbc);

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

    public class SecPanel extends JPanel{
        // constructs a panel of SecPanel
        public SecPanel() {
            this.setBackground(style.white);
            this.setLayout(new GridBagLayout());
            this.setOpaque(false);

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, -20, 5, 0);

            passwordLabel = style.createLblH1("Password", style.black);
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.anchor = GridBagConstraints.NORTHWEST;
            this.add(passwordLabel, gbc);

            gbc.gridy++;
            gbc.gridwidth = 3;
            gbc.insets = new Insets(10, -20, 0, 0);
            currentPasswordLabel = style.createLblH3("Current Password", style.black);
            this.add(currentPasswordLabel, gbc);

            gbc.gridy++;
            currentPasswordTextField = style.createPwdRounded(style.white, style.black, 25);
            currentPasswordTextField.setPreferredSize(new Dimension(300, 30));
            currentPasswordTextField.setFocusable(true);
            this.add(currentPasswordTextField, gbc);

            gbc.gridy++;
            newPasswordLabel = style.createLblH3("New Password", style.black);
            this.add(newPasswordLabel, gbc);

            gbc.gridy++;
            newPasswordTextField = style.createPwdRounded(style.white, style.black, 25);
            newPasswordTextField.setPreferredSize(new Dimension(300, 30));
            newPasswordTextField.setFocusable(true);
            this.add(newPasswordTextField, gbc);

            gbc.gridy++;
            confirmPasswordLabel = style.createLblH3("Confirm New Password", style.black);
            this.add(confirmPasswordLabel, gbc);

            gbc.gridy++;
            confirmPasswordTextField = style.createPwdRounded(style.white, style.black, 25);
            confirmPasswordTextField.setPreferredSize(new Dimension(300, 30));
            confirmPasswordTextField.setFocusable(true);
            this.add(confirmPasswordTextField, gbc);

            gbc.gridy++;
            errorMessageLabel = new JLabel("Passwords do not match. Try again.");
            errorMessageLabel.setForeground(Color.RED);
            errorMessageLabel.setVisible(false);
            gbc.insets = new Insets(20, 50, 0, 0);
            this.add(errorMessageLabel, gbc);

            // resets grid x-position and increment y-position
            gbc.gridx = 0;
            gbc.gridy++;
            gbc.gridwidth = 3;
            gbc.anchor = GridBagConstraints.CENTER;
            gbc.insets = new Insets(20, 0, 0, 0);

            btnChangePass = style.createBtnRounded("CHANGE PASSWORD", style.deepSkyBlue, style.white, 10);
            btnChangePass.setPreferredSize(new Dimension(200,40));
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

    public class StatsPanel extends JPanel {
        // constructs a panel of StatsPanel
        public StatsPanel() {
            this.setBackground(style.white);
            this.setLayout(new GridBagLayout());
            this.setOpaque(false);

            statsLabel = style.createLblH1("Player Stats", style.black);
            statsLabel.setHorizontalAlignment(JLabel.CENTER);
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 2;
            gbc.fill = GridBagConstraints.WEST;
            gbc.insets = new Insets(10, 10, 10, 100);
            this.add(statsLabel, gbc);

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

    //retrieves the current JButton of btnAccMa
    public JButton getBtnAccMa(){
        return btnAccMa;
    }

    // sets a specified action listener for btnAccMa
    public void setAccountManagementListener(ActionListener actionListener) {
        btnAccMa.addActionListener(actionListener);
    }

    //retrieves the current JButton of btnAccMa
    public JButton getBtnDelAcc(){
        return btnDelAcc;
    }

    // sets a specified action listener for btnDelAcc
    public void setDeleteAccountListener(ActionListener actionListener) {
        btnDelAcc.addActionListener(actionListener);
    }

    //retrieves the current JButton of btnAccMa
    public JButton getBtnMusic(){
        return btnMusic;
    }

    // sets a specified action listener for btnMusic
    public void setMusicListener(ActionListener actionListener) {
        btnMusic.addActionListener(actionListener);
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

    // retrieves the current JButton of btnSaveChanges
    // return the current btnSaveChanges
    public JButton getBtnSaveChanges() {
        return btnSaveChanges;
    }

    // sets a specified action listener for btnSaveChanges
    public void setSaveChangesListener(ActionListener actionListener) {
        btnSaveChanges.addActionListener(actionListener);
    }

    // retrieves the current JButton of btnEdit
    // return the current btnEdit
    public JButton getBtnEditChanges() {
        return btnEdit;
    }

    // sets a specified action listener for btnEdit
    public void setEditListener(ActionListener actionListener) {
        btnEdit.addActionListener(actionListener);
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

    // sets a specified text for fullNameTextField
    public void setFullNameText(String text) {
        fullNameTextField.setText(text);
    }

    // sets a specified text for gplValue
    public void setGamesPlayedText(int text)          {    // POSSIBLE BUG HERE
        gplValue.setText(String.valueOf("50"));
    }

    // sets a specified text for gwlValue
    public void setGamesWonText(int text) {                 // POSSIBLE BUG HERE
        gwlValue.setText(String.valueOf("50"));
    }

    // sets a specified text for tplValue
    public void setTotalPointsText(int text) {          // THIS IS WORKING
        tplValue.setText(String.valueOf(text));
    }

    public String getCurrentPassword() {
        return String.valueOf(currentPasswordTextField.getPassword());
    }

    public String getNewPassword() {
        return String.valueOf(newPasswordTextField.getPassword());
    }

    public String getConfirmPassword(){
        return String.valueOf(confirmPasswordTextField.getPassword());
    }

    public JTextField getFullNameTextField() {
        return fullNameTextField;
    }

    public JLabel getErrorMessageLabel() {
        return errorMessageLabel;
    }
}