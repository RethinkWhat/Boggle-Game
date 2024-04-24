package client.view.subpages;

import shared.SwingStylesheet;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * The HomeView
 */
public class HomeView extends JPanel {
    /**
     * The player's username.
     */
    private JLabel lblUsername;
    /**
     * The player's profile picture.
     */
    private JLabel lblPlayerPfp;
    /**
     * The edit profile picture button.
     */
    private JButton btnEditPfp;
    /**
     * The join game button.
     */
    private JButton btnJoinGame;
    /**
     * The tutorial button.
     */
    private JButton btnTutorial;
    /**
     * The leaderboard panel.
     */
    private LeaderboardPanel pnlLeaderboard;;
    /**
     * THe stylesheet.
     */
    private SwingStylesheet style = new SwingStylesheet();

    /**
     * Constructs a panel of HomeView.
     */
    public HomeView() {
        this.setBackground(style.deepSkyBlue);
        this.setBorder(style.padding);
        this.setLayout(new BorderLayout(20,0));

        JLabel lblLogoAnimated = new JLabel(style.iconLogoAnimated);
        lblLogoAnimated.setPreferredSize(new Dimension(100,150));
        add(lblLogoAnimated, BorderLayout.NORTH);

        add(new LeftPanel(), BorderLayout.WEST);
        add(new RightPanel(), BorderLayout.EAST);

        this.setPreferredSize(new Dimension(1300,750));
    }

    /**
     * Holds the leaderboards.
     */
    class LeftPanel extends JPanel {
        /**
         * Constructs a panel of LeftPanel.
         */
        public LeftPanel() {
            this.setBackground(style.deepSkyBlue);

            JLayeredPane layeredPane = new JLayeredPane();
            layeredPane.setLayout(null);
            layeredPane.setPreferredSize(new Dimension(550,520));
            add(layeredPane);

            JPanel pnlHeader = style.createPnlRounded(300,50,style.goldenTainoi, style.white);
            pnlHeader.setBackground(style.white);
            pnlHeader.setBounds(70,0,400,45);
            layeredPane.add(pnlHeader, new Integer(1));

            JLabel lblLeaderboard = style.createLblH2("Global Leaderboard",style.white);
            lblLeaderboard.setVerticalAlignment(SwingConstants.CENTER);
            lblLeaderboard.setHorizontalAlignment(SwingConstants.CENTER);
            pnlHeader.add(lblLeaderboard);

            JPanel container = style.createPnlRounded(550,520,style.white, style.deepSkyBlue);
            container.setBorder(style.padding);
            container.setBounds(0,20,550,500);
            container.setBackground(style.deepSkyBlue);
            layeredPane.add(container, new Integer(0));

            pnlLeaderboard = new LeaderboardPanel();

            JScrollPane scrollPane = new JScrollPane(pnlLeaderboard);
            scrollPane.setBorder(BorderFactory.createEmptyBorder());
            scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            container.add(scrollPane, BorderLayout.CENTER);

            this.setPreferredSize(new Dimension(550,500));
        }
    }

    /**
     * Holds the user joining prompt.
     */
    class RightPanel extends JPanel {
        /**
         * Constructs a panel of RightPanel.
         */
        public RightPanel() {
            this.setBackground(style.deepSkyBlue);

            JLayeredPane layeredPane = new JLayeredPane();
            layeredPane.setLayout(null);
            layeredPane.setPreferredSize(new Dimension(700,520));
            add(layeredPane);

            JPanel pnlHeader = style.createPnlRounded(300,50,style.goldenTainoi, style.white);
            pnlHeader.setBackground(style.white);
            pnlHeader.setBounds(200,0,300,45);
            layeredPane.add(pnlHeader, new Integer(1));

            JLabel lblHome = style.createLblH2("Getting Started",style.white);
            lblHome.setVerticalAlignment(SwingConstants.CENTER);
            lblHome.setHorizontalAlignment(SwingConstants.CENTER);
            pnlHeader.add(lblHome);

            JPanel container = style.createPnlRounded(700,520,style.white, style.deepSkyBlue);
            container.setBorder(style.padding);
            container.setBounds(0,20,700,500);
            container.setBackground(style.deepSkyBlue);
            layeredPane.add(container, new Integer(0));

            container.add(new GetStartedPanel());

            this.setPreferredSize(new Dimension(700,520));
        }
    }

    /**
     * Holds the players and their pertinent details in the global leaderboard.
     */
    class LeaderboardPanel extends JPanel {
        /**
         * Constructs a LeaderboardPanel.
         */
        public LeaderboardPanel() {
            this.setBackground(style.white);
            this.setLayout(new FlowLayout(FlowLayout.LEFT, 200,20));

            add(new PlayerLeaderboardPanel("res/drawable/images/pfp-male-1.png", "monem", 100));

            this.setPreferredSize(new Dimension(550,1000));
        }
    }

    /**
     * Holds the user information and components to start a game.
     */
    class GetStartedPanel extends JPanel {
        /**
         * Constructs a GetStartedPanel.
         */
        public GetStartedPanel() {
            this.setBackground(style.white);
            this.setLayout(new FlowLayout(FlowLayout.CENTER, 500,50));
            this.setBorder(style.padding);

            lblUsername = style.createLblH3("Username", style.deepSkyBlue);
            lblUsername.setVerticalAlignment(SwingConstants.CENTER);
            lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
            add(lblUsername);

            lblPlayerPfp = style.createLblIconOnly(style.iconPfpMale1, 120,120);
            add(lblPlayerPfp);

            btnJoinGame = style.createBtnRounded("JOIN GAME", style.deepSkyBlue, style.black, 10);
            btnJoinGame.setPreferredSize(new Dimension(420,70));
            btnJoinGame.setFont(style.bowlbyOne.deriveFont(48f));
            add(btnJoinGame);

            btnTutorial = style.createBtnTxtOnly("Learn How To Play", style.deepSkyBlue);
            add(btnTutorial);

            this.setPreferredSize(new Dimension(690,450));
        }
    }

    /**
     * Holds the user information.
     */
    public class PlayerLeaderboardPanel extends JPanel {
        /**
         * Constructs a panel of PlayerLeaderboardPanel.
         */
        public PlayerLeaderboardPanel(String pfpURL, String username, int totalPoints) {
            this.setBackground(style.white);
            this.setBorder(style.padding);
            this.setLayout(new BorderLayout());

            ImageIcon iconPfp = new ImageIcon(pfpURL);

            JLabel lblPlayerPfp = style.createLblIconOnly(iconPfp, 60,60);
            add(lblPlayerPfp, BorderLayout.WEST);

            JPanel pnlPlayerInfo = new JPanel();
            pnlPlayerInfo.setBackground(style.white);
            pnlPlayerInfo.setLayout(new FlowLayout(FlowLayout.LEFT, 500,10));
            pnlPlayerInfo.setPreferredSize(new Dimension(440,80));
            add(pnlPlayerInfo, BorderLayout.EAST);

            JLabel lblUsername = style.createLblH2(username, style.deepSkyBlue);
            pnlPlayerInfo.add(lblUsername);

            JLabel lblPoints = style.createLblH3((totalPoints) + " PTS", style.deepSkyBlue);
            pnlPlayerInfo.add(lblPoints);

            this.setPreferredSize(new Dimension(500,80));
        }
    }

    /**
     * Retrieves the current JLabel of lblUsername.
     * @return The current lblUsername.
     */
    public JLabel getLblUsername() {
        return lblUsername;
    }

    /**
     * Retrieves the current JLabel of lblPlayerPfp.
     * @return The current lblPlayerPfp.
     */
    public JLabel getLblPlayerPfp() {
        return lblPlayerPfp;
    }

    /**
     * Retrieves the current JButton of btnEditPfp.
     * @return The current btnEditPfp.
     */
    public JButton getBtnEditPfp() {
        return btnEditPfp;
    }

    /**
     * Retrieves the current JButton of btnJoinGame.
     * @return The current btnJoinGame.
     */
    public JButton getBtnJoinGame() {
        return btnJoinGame;
    }

    /**
     * Retrieves the current JButton of btnTutorial.
     * @return The current btnTutorial.
     */
    public JButton getBtnTutorial() {
        return btnTutorial;
    }

    /**
     * Sets a specified action listener for btnJoinGame.
     * @param actionListener The specified action listener.
     */
    public void setJoinListener(ActionListener actionListener) {
        btnJoinGame.addActionListener(actionListener);
    }

    /**
     * Sets a specified action listener for btnEditPfp.
     * @param actionListener The specified action listener.
     */
    public void setEditListener(ActionListener actionListener) {
        btnEditPfp.addActionListener(actionListener);
    }

    /**
     * Sets a specified text for lblUsername.
     * @param username The specified username.
     */
    public void setUsername(String username) {
        lblUsername.setText(username);
    }
}
