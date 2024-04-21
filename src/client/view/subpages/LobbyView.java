package client.view.subpages;

import client.view.subelements.PlayerPanel;
import shared.SwingStylesheet;

import javax.swing.*;
import java.awt.*;

/**
 * The LobbyView is the intermediary between the home page and the game room.
 */
public class LobbyView extends JPanel {
    /**
     * The timer before game room commences.
     */
    private JLabel lblTimer;
    /**
     * The exit lobby button.
     */
    private JButton btnExitLobby;
    /**
     * The panel containing the users joined.
     */
    private UsersPanel pnlUsers;
    /**
     * Panel holding the players.
     */
    private JPanel pnlPlayerContainer;
    /**
     * The stylesheet.
     */
    private SwingStylesheet style = new SwingStylesheet();

    /**
     * Constructs a panel of LobbyView.
     */
    public LobbyView() {
        this.setBackground(style.white);
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 500,20));
        this.setBorder(style.padding);

        JPanel pnlTimer = style.createPnlRounded(90,30,style.gray,style.white);
        pnlTimer.setBackground(style.white);
        add(pnlTimer, BorderLayout.NORTH);

        lblTimer = style.createLblP("00:10", style.white);
        lblTimer.setIcon(style.iconTimerWhite);
        lblTimer.setVerticalTextPosition(SwingConstants.CENTER);
        pnlTimer.add(lblTimer);

        add(pnlUsers = new UsersPanel());

        // sample only. delete when controller has logic already.
        pnlPlayerContainer.add(new PlayerPanel(150,150,"username"));
        pnlPlayerContainer.add(new PlayerPanel(150,150,"username"));
        pnlPlayerContainer.add(new PlayerPanel(150,150,"username"));


        btnExitLobby = style.createBtnRounded("Exit Lobby",style.red, style.black,10);
        btnExitLobby.setPreferredSize(new Dimension(260,40));
        add(btnExitLobby, BorderLayout.SOUTH);

        this.setPreferredSize(new Dimension(1300,750));
    }

    /**
     * Holds the users joining in a lobby.
     */
    public class UsersPanel extends JPanel {
        /**
         * Constructs a panel of UsersPanel.
         */
        public UsersPanel() {
            this.setBackground(style.white);
            this.setLayout(new BorderLayout());

            pnlPlayerContainer = new JPanel(new FlowLayout(FlowLayout.LEFT, 50,30));
            pnlPlayerContainer.setBackground(style.white);
            pnlPlayerContainer.setBorder(style.padding);
            pnlPlayerContainer.setPreferredSize(new Dimension(1100,900));

            JScrollPane scroll = new JScrollPane(pnlPlayerContainer);
            scroll.setBorder(BorderFactory.createEmptyBorder());
            scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            add(scroll, BorderLayout.CENTER);

            this.setPreferredSize(new Dimension(1300,500));
        }
    }

    public JLabel getLblTimer() {
        return lblTimer;
    }

    public void setLblTimerTxt(String lblTimer) {
        this.lblTimer.setText(lblTimer);
    }
}
