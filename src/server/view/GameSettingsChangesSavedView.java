package server.view;

import shared.SwingStylesheet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import java.awt.geom.RoundRectangle2D;

public class GameSettingsChangesSavedView extends JFrame {
    private JLabel successIconLabel; // the label of the success icon
    private JLabel firstLabel; // the first text label
    private JLabel secondLabel; // the second text label
    private JLabel thirdLabel; // the third text label
    private JButton closeButton; // the close button
    private SwingStylesheet style = new SwingStylesheet(); // the stylesheet


    // constructor for the GameSettingsChangesSavedView
    public GameSettingsChangesSavedView() {
        this.setSize(600, 370);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setUndecorated(true);
        this.setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 40, 40));

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(style.white);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(6, 12, 12, 12);

        successIconLabel = style.createLblIconOnly(style.iconSuccess, 80, 80);
        successIconLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridy++;
        gbc.insets = new Insets(10, 12, 12, 12);
        panel.add(successIconLabel, gbc);

        gbc.gridy++;
        firstLabel = style.createLblH5("CHANGES SAVED", style.goldenTainoi);
        firstLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.insets = new Insets(12, 12, 6, 12);
        panel.add(firstLabel, gbc);

        gbc.gridy++;
        secondLabel = style.createLblH3("You have successfully changed the", style.black);
        secondLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.insets = new Insets(6, 12, 0, 12);
        panel.add(secondLabel, gbc);

        gbc.gridy++;
        thirdLabel = style.createLblH3("game settings.", style.black);
        thirdLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.insets = new Insets(0, 12, 12, 12);
        panel.add(thirdLabel, gbc);

        gbc.gridy++;
        closeButton = style.createBtnRounded("CLOSE", style.deepSkyBlue, style.white, 10);
        closeButton.setPreferredSize(new Dimension(100,35));
        closeButton.setFocusable(false);
        gbc.insets = new Insets(12, 12, 12, 12);
        panel.add(closeButton, gbc);

        add(panel);
        setVisible(true);
    }

    // retrieves the current JButton of closeButton
    // return the current closeButton
    public JButton getCloseButton() {
        return closeButton;
    }

    // sets a specified action listener for closeButton
    public void setCloseListener(ActionListener actionListener) {
        closeButton.addActionListener(actionListener);
    }

    public static void main(String[] args) {
        new GameSettingsChangesSavedView();
    }
}