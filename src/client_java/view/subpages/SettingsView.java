package client_java.view.subpages;

import shared.SwingStylesheet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class SettingsView extends JPanel{
    private JLabel profileLabel; // the label of the profile panel
    private BufferedImage avatarImg; // the placeholder for the avatar
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
        gbc.insets = new Insets(-180, 0, 0, 0);
        this.add(profileLabel, gbc);

        gbc.gridy++;
        ProfPanel profPanel = new ProfPanel();
        gbc.insets = new Insets(-120, 0, 0, 0);
        this.add(profPanel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        securityLabel = style.createLblH5("SECURITY", style.white);
        gbc.insets = new Insets(-180, 100, 0, 0);
        this.add(securityLabel, gbc);

        gbc.gridy++;
        SecPanel secPanel = new SecPanel();
        gbc.insets = new Insets(-120, 100, 0, 0);
        this.add(secPanel, gbc);

        this.setPreferredSize(new Dimension(1200, 550));
    }

    class ProfPanel extends JPanel{
        public ProfPanel(){
            this.setBackground(style.white);
            this.setLayout(new GridBagLayout());
            this.setOpaque(false);

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10);

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

            g2d.setColor(style.lightYellow);

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
            errorMessageLabel.setVisible(false); // initially hide the error message
            this.add(errorMessageLabel, gbc);

            // resets grid x-position and increment y-position
            gbc.gridx = 0;
            gbc.gridy++;
            gbc.gridwidth = 3;
            gbc.anchor = GridBagConstraints.CENTER;
            gbc.insets = new Insets(40, 0, 0, 0);

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

            g2d.setColor(style.lightYellow);

            g2d.fillRoundRect(0, 0, width, height, arcWidth, arcWidth);

            g2d.dispose();
        }
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
}