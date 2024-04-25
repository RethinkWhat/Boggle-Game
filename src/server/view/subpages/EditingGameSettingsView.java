package server.view.subpages;

import shared.SwingStylesheet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class EditingGameSettingsView extends JPanel {
    private JLabel gameSettingsLabel; // the label of the main panel
    private JLabel gameDurationLabel; // the label of the game duration
    private JLabel waitingDurationLabel; // the label of the waiting duration
    private JLabel numberOfPlayersLabel; // the label of the number of players
    private JComboBox<String> gameDurationComboBox; // the game duration dropdown box
    private JComboBox<String> waitingDurationComboBox; // the waiting duration dropdown box
    private JComboBox<String> numberOfPlayersComboBox; // the number of players dropdown box
    private JButton sveChanges; // the edit button
    private JButton btnCancel; // the cancel button
    private JButton btnBackToDefault; // the back to default button
    private JPanel buttonPanel; // the panel for the save changes and cancel button
    private JPanel backToDefaultPanel; // the panel for the back to default button
    private SwingStylesheet style = new SwingStylesheet(); // the stylesheet
    private final int arcWidth = 20; // the width for the rounded corner


    // constructor panel for the EditingGameSettingsView
    public EditingGameSettingsView() {
        this.setBackground(style.deepSkyBlue);
        this.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 10, 10);

        gameSettingsLabel = style.createLblH5("GAME SETTINGS", style.white);
        gameSettingsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gameSettingsLabel.setVerticalAlignment(SwingConstants.CENTER);
        this.add(gameSettingsLabel, gbc);

        gbc.gridy++;
        Panel panel = new Panel();
        panel.setPreferredSize(new Dimension(1200, 550));
        this.add(panel, gbc);

        this.setPreferredSize(new Dimension(1200, 550));
    }

    // holds the dropdown boxes and edit button
    class Panel extends JPanel {
        public Panel() {
            this.setBackground(style.white);
            this.setLayout(new GridBagLayout());
            this.setOpaque(false);

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10);

            gbc.anchor = GridBagConstraints.LINE_START;
            gbc.gridy++;
            gbc.gridwidth = 1;
            gameDurationLabel = style.createLblH3("Game Duration", style.black);
            gbc.gridx = 0;
            this.add(gameDurationLabel, gbc);

            gbc.gridx = 1;
            waitingDurationLabel = style.createLblH3("Waiting Duration", style.black);
            this.add(waitingDurationLabel, gbc);

            gbc.gridx = 2;
            numberOfPlayersLabel = style.createLblH3("Number of Players", style.black);
            this.add(numberOfPlayersLabel, gbc);

            gbc.insets = new Insets(10, 10, 10, 10);

            // resets grid x-position and increment y-position
            gbc.anchor = GridBagConstraints.WEST;
            gbc.gridy++;

            gbc.gridx = 0;
            gameDurationComboBox = new JComboBox<>(new String[]{"1 minute/s", "2 minute/s", "3 minute/s", "4 minute/s"});
            gameDurationComboBox.setSelectedIndex(0);
            gameDurationComboBox.setPreferredSize(new Dimension(200, 50));
            gameDurationComboBox.setFont(new Font("Arial", Font.BOLD, 18));
            gameDurationComboBox.setFocusable(false);
            this.add(gameDurationComboBox, gbc);

            gbc.gridx = 1;
            waitingDurationComboBox = new JComboBox<>(new String[]{"10 second/s", "15 second/s", "20 second/s", "25 second/s"});
            waitingDurationComboBox.setSelectedIndex(0);
            waitingDurationComboBox.setPreferredSize(new Dimension(200, 50));
            waitingDurationComboBox.setFont(new Font("Arial", Font.BOLD, 18));
            waitingDurationComboBox.setFocusable(false);
            this.add(waitingDurationComboBox, gbc);

            gbc.gridx = 2;
            numberOfPlayersComboBox = new JComboBox<>(new String[]{"2 players", "3 players", "4 players", "5 players"});
            numberOfPlayersComboBox.setSelectedIndex(0);
            numberOfPlayersComboBox.setPreferredSize(new Dimension(200, 50));
            numberOfPlayersComboBox.setFont(new Font("Arial", Font.BOLD, 18));
            numberOfPlayersComboBox.setFocusable(false);
            this.add(numberOfPlayersComboBox, gbc);

            // resets grid x-position and increment y-position
            gbc.gridx = 0;
            gbc.gridy++;
            gbc.gridwidth = 3;
            gbc.anchor = GridBagConstraints.CENTER;
            gbc.insets = new Insets(200, 10, 10, 10);

            sveChanges = style.createBtnRounded("SAVE CHANGES", style.deepSkyBlue, style.white, 10);
            sveChanges.setPreferredSize(new Dimension(170,45));
            sveChanges.setFocusable(false);

            btnCancel = style.createBtnRounded("CANCEL", style.red, style.white, 10);
            btnCancel.setPreferredSize(new Dimension(150,45));
            btnCancel.setFocusable(false);

            btnBackToDefault = style.createBtnRounded("BACK TO DEFAULT", style.white, style.deepSkyBlue, 10);
            btnBackToDefault.setPreferredSize(new Dimension(180,35));
            btnBackToDefault.setFont(new Font("Arial", Font.BOLD, 15));
            btnBackToDefault.setFocusable(false);
            btnBackToDefault.setBorder(null);

            buttonPanel = new JPanel();
            buttonPanel.add(sveChanges);
            buttonPanel.add(btnCancel);
            buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 0));
            buttonPanel.setBackground(style.white);
            this.add(buttonPanel, gbc);

            gbc.gridy++;
            gbc.gridwidth = 3;
            gbc.anchor = GridBagConstraints.CENTER;
            gbc.insets = new Insets(10, 10, 10, 10);

            backToDefaultPanel = new JPanel();
            backToDefaultPanel.add(btnBackToDefault);
            backToDefaultPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
            backToDefaultPanel.setBackground(style.white);
            this.add(backToDefaultPanel, gbc);

            this.setPreferredSize(new Dimension(1200, 550));
            this.setMaximumSize(new Dimension(1200, 550));
            this.setMinimumSize(new Dimension(1200, 550));
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

    // retrieves the current JComboBox of gameDurationComboBox
    // return the current gameDurationComboBox
    public JComboBox<String> getGameDurationComboBox(){
        return gameDurationComboBox;
    }

    // sets a specified action listener for gameDurationComboBox
    public void setGameDurListener(ActionListener actionListener) {
        gameDurationComboBox.addActionListener(actionListener);
    }

    // retrieves the current JComboBox of waitingDurationComboBox
    // return the current waitingDurationComboBox
    public JComboBox<String> getWaitingDurationComboBox(){
        return waitingDurationComboBox;
    }

    // sets a specified action listener for waitingDurationComboBox
    public void setWaitDurListener(ActionListener actionListener) {
        waitingDurationComboBox.addActionListener(actionListener);
    }

    // retrieves the current JComboBox of numberOfPlayersComboBox
    // return the current numberOfPlayersComboBox
    public JComboBox<String> getNumberOfPlayersComboBox(){
        return numberOfPlayersComboBox;
    }

    // sets a specified action listener for numberOfPlayersComboBox
    public void setNumberOfPlyrListener(ActionListener actionListener) {
        numberOfPlayersComboBox.addActionListener(actionListener);
    }

    // retrieves the current JButton of btnSave
    // return the current btnSave
    public JButton getBtnSave() {
        return sveChanges;
    }

    // sets a specified action listener for btnSave
    public void setSaveListener(ActionListener actionListener) {
        sveChanges.addActionListener(actionListener);
    }

    // retrieves the current JButton of btnCancel
    // return the current btnCancel
    public JButton getBtnCancel() {
        return btnCancel;
    }

    // sets a specified action listener for btnCancel
    public void setCancelListener(ActionListener actionListener) {
        btnCancel.addActionListener(actionListener);
    }

    // retrieves the current JButton of btnBackToDefault
    // return the current btnBackToDefault
    public JButton getBtnBackToDefault() {
        return btnBackToDefault;
    }

    // sets a specified action listener for btnBackToDefault
    public void setBackToDefaultListener(ActionListener actionListener) {
        btnBackToDefault.addActionListener(actionListener);
    }
}