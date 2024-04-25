package client.view.subpages;

import shared.SwingStylesheet;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

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
        this.setBackground(style.deepSkyBlue);
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(1300, 750));

        CenterPanel pnlCenter = new CenterPanel();

        add(pnlCenter, BorderLayout.CENTER);
    }

    /**
     * Represents the center panel of the lobby view.
     */
    public class CenterPanel extends JPanel {
        /**
         * Constructs a panel of CenterPanel.
         */
        public CenterPanel() {
            setBackground(style.deepSkyBlue);
            setLayout(new BorderLayout());

            JLayeredPane layeredPane = new JLayeredPane();
            layeredPane.setLayout(null);
            layeredPane.setPreferredSize(new Dimension(1100,620));
            add(layeredPane);

            JPanel pnlHeader = style.createPnlRounded(200,45,style.goldenTainoi, style.white);
            pnlHeader.setBackground(style.white);
            pnlHeader.setBounds(540,18,200,45);
            layeredPane.add(pnlHeader, new Integer(0));

            JLabel lblLobby = style.createLblH2("LOBBY",style.white);
            lblLobby.setVerticalAlignment(SwingConstants.CENTER);
            lblLobby.setHorizontalAlignment(SwingConstants.CENTER);
            pnlHeader.add(lblLobby);

            JPanel container = style.createPnlRounded(1000,620,style.white, style.deepSkyBlue);
            container.setBorder(new EmptyBorder(30,10,10,10));
            container.setBounds(140,40,1000,620);
            container.setBackground(style.deepSkyBlue);
            container.setLayout(new BorderLayout(10,10));
            layeredPane.add(container, new Integer(0));

            container.add(new TimerPanel(), BorderLayout.NORTH);
            container.add(new UsersPanel(), BorderLayout.CENTER);
            container.add(new ButtonPanel(), BorderLayout.SOUTH);
        }
    }

    /**
     * Represents the panel displaying the timer in the lobby view.
     */
    public class TimerPanel extends JPanel {
        /**
         * Constructs a panel of TimerPanel.
         */
        public TimerPanel() {
            setBackground(style.white);

            setPreferredSize(new Dimension(90, 60));

            JPanel pnlTimer = style.createPnlRounded(90,30,style.gray,style.white);
            pnlTimer.setBackground(style.white);
            add(pnlTimer, BorderLayout.SOUTH);


            lblTimer = style.createLblP("10" + "s", style.white);
            lblTimer.setIcon(style.iconTimerWhite);
            lblTimer.setVerticalTextPosition(SwingConstants.CENTER);
            pnlTimer.add(lblTimer);
        }

    }

    /**
     * Holds the users joining in a lobby.
     */
    public class UsersPanel extends JPanel {
        /**
         * Constructs a panel of UsersPanel.
         */
        public UsersPanel() {
            setBackground(style.white);
            setLayout(new BorderLayout());

            pnlPlayerContainer = new JPanel(new FlowLayout(FlowLayout.LEFT, 10,10));
            pnlPlayerContainer.setBackground(style.white);
            pnlPlayerContainer.setBorder(style.padding);
            pnlPlayerContainer.setPreferredSize(new Dimension(1100,920));

            pnlPlayerContainer.add(new UserPanel("res/drawable/images/pfp-male-1.png", "username"));
            pnlPlayerContainer.add(new UserPanel("res/drawable/images/pfp-male-1.png", "username"));
            pnlPlayerContainer.add(new UserPanel("res/drawable/images/pfp-male-1.png", "username"));
            pnlPlayerContainer.add(new UserPanel("res/drawable/images/pfp-male-1.png", "username"));
            pnlPlayerContainer.add(new UserPanel("res/drawable/images/pfp-male-1.png", "username"));
            pnlPlayerContainer.add(new UserPanel("res/drawable/images/pfp-male-1.png", "username"));
            pnlPlayerContainer.add(new UserPanel("res/drawable/images/pfp-male-1.png", "username"));
            pnlPlayerContainer.add(new UserPanel("res/drawable/images/pfp-male-1.png", "username"));
            pnlPlayerContainer.add(new UserPanel("res/drawable/images/pfp-male-1.png", "username"));
            pnlPlayerContainer.add(new UserPanel("res/drawable/images/pfp-male-1.png", "username"));


            JScrollPane scroll = new JScrollPane(pnlPlayerContainer);
            scroll.setBorder(BorderFactory.createEmptyBorder());
            scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            add(scroll, BorderLayout.CENTER);

            setPreferredSize(new Dimension(1300,500));
        }
    }

    /**
     * Holds the player profile picture and username.
     */
    public class UserPanel extends JPanel{
        /**
         * Constructs a panel of UserPanel.
         */
        public UserPanel(String pfpURL, String username) {
            this.setBackground(style.white);
            this.setBorder(style.padding);
            this.setLayout(new GridBagLayout());

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.fill = GridBagConstraints.BOTH;
            gbc.anchor = GridBagConstraints.CENTER;
            gbc.gridwidth = 1;

            ImageIcon iconPfp = new ImageIcon(pfpURL);

            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weightx = 200;
            JLabel lblUsername = style.createLblH4(username, style.deepSkyBlue);
            lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
            lblUsername.setHorizontalTextPosition(SwingConstants.CENTER);
            add(lblUsername, gbc);

            gbc.gridy = 1;
            JLabel lblPlayerPfp = style.createLblIconOnly(iconPfp, 120,120);
            add(lblPlayerPfp, gbc);

            this.setPreferredSize(new Dimension(180,180));
        }
    }

    /**
     * Represents the panel containing buttons in the lobby view.
     */
    public class ButtonPanel extends JPanel {
        /**
         * Constructs a panel of ButtonPanel.
         */
        public ButtonPanel() {
            setBackground(style.white);
            setPreferredSize(new Dimension(1300, 50));

            btnExitLobby = style.createBtnRounded("Exit Lobby", style.red, style.black, 10);
            btnExitLobby.setPreferredSize(new Dimension(260, 40));
            add(btnExitLobby);
        }
    }

    /**
     * Sets a specified action listener for btnExitLobby.
     * @param actionListener The specified action listener.
     */
    public void setExitLobbyListener(ActionListener actionListener) {
        btnExitLobby.addActionListener(actionListener);
    }

    /**
     * Retrieves the current JLabel of lblTimer.
     * @return The current lblTimer.
     */
    public JLabel getLblTimer() {
        return lblTimer;
    }

    /**
     * Sets a specified text for lblTimer.
     * @param lblTimer The specified lblTimer.
     */
    public void setLblTimerTxt(String lblTimer) {
        SwingUtilities.invokeLater(() -> this.lblTimer.setText(lblTimer + "s"));
    }

    /**
     * Adds a specified player in UsersPanel.
     * @param username The specified player username.
     * @param pfpURL The specified player profile picture URL.
     */
    public void addPlayerInUserPanel(String username, String pfpURL) {
        SwingUtilities.invokeLater(() -> pnlPlayerContainer.add(new UserPanel(username, pfpURL)));
    }
}
