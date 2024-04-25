package server.view;

import server.view.subpages.ServerStatusView;
import server.view.subpages.GameSettingsView;
import server.view.subpages.PlayersView;
import shared.SwingStylesheet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * The container for the server application view and its subviews.
 */
public class ServerApplicationView extends JFrame {
    /**
     * The label showing the current page of the application.
     */
    private JLabel lblNavLocation;
    /**
     * The switch navigation button.
     */
    private JButton btnNavSwitch;
    /**
     * The game settings navigation button.
     */
    private JButton btnNavGameSettings;
    /**
     * The players navigation button.
     */
    private JButton btnNavPlayers;
    /**
     * The logout navigation button.
     */
    private JButton btnNavLogout;
    /**
     * The panel holding other view components.
     */
    private JPanel pnlCards;
    /**
     * The card layout that controls other components.
     */
    private CardLayout cardLayout;
    /**
     * The stylesheet.
     */
    private SwingStylesheet style = new SwingStylesheet();

    private ServerStatusView serverStatusView;
    private GameSettingsView gameSettingsView;
    private PlayersView playersView;

    public ServerStatusView getServerStatusView() {
        return serverStatusView;
    }

    public GameSettingsView getGameSettingsView() {
        return gameSettingsView;
    }

    public PlayersView getPlayersView() {
        return playersView;
    }

    /**
     * Constructs a frame of ServerApplication View.
     */
    public ServerApplicationView() {
        super("Server Application");

        Container contentArea = new JPanel(new BorderLayout());
        contentArea.setBackground(style.white);

        contentArea.add(new HeaderPanel(), BorderLayout.NORTH);

        pnlCards = new JPanel(cardLayout = new CardLayout(0,0));
        contentArea.add(pnlCards, BorderLayout.CENTER);

        serverStatusView = new ServerStatusView();
        gameSettingsView = new GameSettingsView();
        playersView = new PlayersView();
        pnlCards.add( serverStatusView, "server");
//        pnlCards.add( gameSettingsView, "gamesettings");
        pnlCards.add( playersView, "players");

        cardLayout.show(pnlCards, "server");

        this.setContentPane(contentArea);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setSize(1300,800);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    /**
     * Holds the navigation elements.
     */
    class HeaderPanel extends JPanel {
        /**
         * Constructs a panel of HeaderPanel.
         */
        public HeaderPanel() {
            this.setBackground(style.goldenTainoi);

            JPanel container = new JPanel(new GridLayout(0,2,600,0));
            container.setBackground(style.goldenTainoi);
            container.setBorder(style.padding);
            add(container);

            JPanel pnlLeft = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
            pnlLeft.setBackground(style.goldenTainoi);
            container.add(pnlLeft);

            lblNavLocation = style.createLblH3("Server Status", style.white);
            lblNavLocation.setIcon(style.iconLogoWhiteScaled);
            lblNavLocation.setVerticalTextPosition(SwingConstants.BOTTOM);
            pnlLeft.add(lblNavLocation);

            JPanel pnlButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
            pnlButtons.setBackground(style.goldenTainoi);
            container.add(pnlButtons);

            btnNavSwitch = style.createBtnIconOnly(style.iconSwitch, 22,22);
            pnlButtons.add(btnNavSwitch);

            btnNavGameSettings = style.createBtnIconOnly(style.iconGameSettings, 22, 22);
            pnlButtons.add(btnNavGameSettings);

            btnNavPlayers = style.createBtnIconOnly(style.iconPlayers, 20,20);
            pnlButtons.add(btnNavPlayers);

            btnNavLogout = style.createBtnIconOnly(style.iconLogout, 20,20);
            pnlButtons.add(btnNavLogout);

            container.setPreferredSize(new Dimension(1300,50));
        }
    }

    /**
     * Retrieves the current JLabel of lblNavLocation.
     * @return The current lblNavLocation.
     */
    public JLabel getLblNavLocation() {
        return lblNavLocation;
    }

    /**
     * Retrieves the current JButton of btnNavSwitch.
     * @return The current btnNavSwitch.
     */
    public JButton getBtnNavSwitch() {
        return btnNavSwitch;
    }

    /**
     * Retrieves the current JButton of btnNavGameSettings.
     * @return The current btnNavGameSettings.
     */
    public JButton getBtnNavGameSettings() {
        return btnNavGameSettings;
    }

    /**
     * Retrieves the current JButton of btnNavPlayers.
     * @return The current btnNavPlayers.
     */
    public JButton getBtnNavPlayers() {
        return btnNavPlayers;
    }

    /**
     * Retrieves the current JButton of btnNavLogout.
     * @return The current btnNavLogout.
     */
    public JButton getBtnNavLogout() {
        return btnNavLogout;
    }

    /**
     * Retrieves the current JPanel of pnlCards.
     * @return The current pnlCards.
     */
    public JPanel getPnlCards() {
        return pnlCards;
    }

    /**
     * Retrieves the current CardLayout of cardLayout.
     * @return The current cardLayout.
     */
    public CardLayout getCardLayout() {
        return cardLayout;
    }

    /**
     * Sets a specified action listener for btnNavSwitch.
     * @param actionListener The specified action listener.
     */
    public void setSwitchListener(ActionListener actionListener) {
        btnNavSwitch.addActionListener(actionListener);
    }

    /**
     * Sets a specified action listener for btnNavGameSettings.
     * @param actionListener The specified action listener.
     */
    public void setGameSettingsListener(ActionListener actionListener) {
        btnNavGameSettings.addActionListener(actionListener);
    }

    /**
     * Sets a specified action listener for btnNavPlayers.
     * @param actionListener The specified action listener.
     */
    public void setPlayersListener(ActionListener actionListener) {
        btnNavPlayers.addActionListener(actionListener);
    }

    /**
     * Sets a specified action listener for btnNavLogout.
     * @param actionListener The specified action listener.
     */
    public void setLogoutListener(ActionListener actionListener) {
        btnNavLogout.addActionListener(actionListener);
    }

    /**
     * Sets a specified text for lblNavLocation.
     * @param text The specified text.
     */
    public void setLocationText(String text) {
        lblNavLocation.setText(text);
    }

    public static void main(String[] args) {
        new ServerApplicationView();
    }
}

