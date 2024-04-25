package server.view;

import server.view.subpages.DefaultGameSettingsView;
import shared.SwingStylesheet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class AdminDefaultGameSettingsView extends JFrame{
    private JLabel lblNavLocation; // the label showing the current page of the application
    private JButton btnNavPlayers; // the player's navigation button
    private JButton btnNavGameSettings; // the game settings navigation button
    private JButton btnNavServerSwitch; // the server switch navigation button
    private JButton btnNavLogout; // the logout navigation button
    private JPanel pnlCards; // the panel holding other view components
    private CardLayout cardLayout; // the card layout that controls other components
    private SwingStylesheet style = new SwingStylesheet(); // the stylesheet


    // constructs a frame of AdminDefaultGameSettingsView
    public AdminDefaultGameSettingsView() {
        super("Word Factory");

        Container contentArea = new JPanel(new BorderLayout());
        contentArea.setBackground(style.deepSkyBlue);

        contentArea.add(new AdminDefaultGameSettingsView.HeaderPanel(), BorderLayout.NORTH);

        pnlCards = new JPanel(cardLayout = new CardLayout(0,0));
        contentArea.add(pnlCards, BorderLayout.CENTER);

        pnlCards.add(new DefaultGameSettingsView(), "defaultSettings");

        cardLayout.show(pnlCards, "defaultSettings");

        this.setContentPane(contentArea);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setSize(1300,800);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    class HeaderPanel extends JPanel {
        // constructs a panel of HeaderPanel
        public HeaderPanel() {
            this.setBackground(style.goldenTainoi);

            JPanel container = new JPanel(new GridLayout(0,2,770,0));
            container.setBackground(style.goldenTainoi);
            container.setBorder(style.padding);
            add(container);

            JPanel pnlLeft = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
            pnlLeft.setBackground(style.goldenTainoi);
            container.add(pnlLeft);

            lblNavLocation = style.createLblH3("Game Settings", style.white);
            lblNavLocation.setIcon(style.iconLogoWhiteScaled);
            lblNavLocation.setVerticalTextPosition(SwingConstants.BOTTOM);
            pnlLeft.add(lblNavLocation);

            JPanel pnlButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
            pnlButtons.setBackground(style.goldenTainoi);
            container.add(pnlButtons);

            btnNavServerSwitch = style.createBtnIconOnly(style.iconSwitch, 23,23);
            pnlButtons.add(btnNavServerSwitch);

            btnNavGameSettings = style.createBtnIconOnly(style.iconGameSettings, 23,23);
            pnlButtons.add(btnNavGameSettings);

            btnNavPlayers = style.createBtnIconOnly(style.iconPlayers, 23,23);
            pnlButtons.add(btnNavPlayers);

            btnNavLogout = style.createBtnIconOnly(style.iconLogout, 20,20);
            pnlButtons.add(btnNavLogout);

            container.setPreferredSize(new Dimension(1300,50));
        }
    }

    // retrieves the current JLabel of lblNavLocation
    public JLabel getLblNavLocation() {
        return lblNavLocation;
    }

    // retrieves the current JButton of btnNavServerSwitch
    public JButton getBtnNavServerSwitch() {
        return btnNavServerSwitch;
    }

    // retrieves the current JButton of btnNavGameSettings
    public JButton getBtnNavGameSettings() {
        return btnNavGameSettings;
    }

    // retrieves the current JButton of btnNavPlayers
    public JButton getBtnNavPlayers() {
        return btnNavPlayers;
    }

    // retrieves the current JButton of btnNavLogout
    public JButton getBtnNavLogout() {
        return btnNavLogout;
    }

    // retrieves the current JPanel of pnlCards
    public JPanel getPnlCards() {
        return pnlCards;
    }

    // retrieves the current CardLayout of cardLayout
    public CardLayout getCardLayout() {
        return cardLayout;
    }

    // sets a specified action listener for btnNavServerSwitch
    public void setServerSwitchListener(ActionListener actionListener) {
        btnNavServerSwitch.addActionListener(actionListener);
    }

    // sets a specified action listener for btnNavGameSettings
    public void setGameSettingsListener(ActionListener actionListener) {
        btnNavGameSettings.addActionListener(actionListener);
    }

    // sets a specified action listener for btnNavPlayers
    public void setPlayersListener(ActionListener actionListener) {
        btnNavPlayers.addActionListener(actionListener);
    }

    // sets a specified action listener for btnNavLogout
    public void setLogoutListener(ActionListener actionListener) {
        btnNavLogout.addActionListener(actionListener);
    }

    // sets a specified text for lblNavLocation
    public void setLocationText(String text) {
        lblNavLocation.setText(text);
    }

    public static void main(String[] args) {
        new AdminDefaultGameSettingsView();
    }
}