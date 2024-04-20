package client.view.subpages;

import shared.SwingStylesheet;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * The GameRoomView contains the game elements where the main game takes place.
 */
public class GameRoomView extends JPanel {
    /**
     * The round number.
     */
    private int roundNumber = 1;
    /**
     * The score of the user.
     */
    private int score = 10000;
    /**
     * The round JLabel.
     */
    private JLabel lblRound;
    /**
     * The panel holding each player inside pnlPlayers.
     */
    private JPanel pnlPlayersContainer;
    /**
     * The round timer.
     */
    private JLabel lblTimer;
    /**
     * The given letter set.
     */
    private LeftPanel.LettersPanel pnlLettersPanel;
    /**
     * Error message for input.
     */
    private JLabel lblErrorMessage;
    /**
     * The input field of the user.
     */
    private JTextField txtWordInput;
    /**
     * The clear input button.
     */
    private JButton btnClear;
    /**
     * The score of the user in the game.
     */
    private JLabel lblScore;
    /**
     * The text area of the leaderboard stats.
     */
    private JTextArea txaLeaderboard;
    /**
     * The text area of the player's entered words.
     */
    private JTextArea txaEnteredWords;
    /**
     * The stylesheet.
     */
    private SwingStylesheet style = new SwingStylesheet();

    /**
     * Constructs a panel of GameRoomView.
     */
    public GameRoomView() {
        this.setBackground(style.white);
        this.setLayout(new BorderLayout());

        add(new LeftPanel(), BorderLayout.WEST);
        add(new RightPanel(), BorderLayout.EAST);

        this.setPreferredSize(new Dimension(1300,750));
    }

    /**
     * Holds the game elements.
     */
    class LeftPanel extends JPanel {
        /**
         * Constructs a panel of LeftPanel.
         */
        public LeftPanel() {
            this.setBackground(style.white);
            this.setLayout(new BorderLayout());
            this.setBorder(style.padding);

            JPanel pnlTop = new JPanel();
            pnlTop.setLayout(new FlowLayout());
            pnlTop.setBackground(style.white);
            pnlTop.setPreferredSize(new Dimension(900,40));
            add(pnlTop, BorderLayout.NORTH);

            JPanel pnlRound = style.createPnlRounded(150,35,style.goldenTainoi, style.white);
            pnlRound.setPreferredSize(new Dimension(150,35));
            pnlRound.setBackground(style.white);
            pnlRound.setLayout(new BorderLayout());
            pnlTop.add(pnlRound);

            lblRound = style.createLblH2("Round " + roundNumber, style.white);
            lblRound.setHorizontalAlignment(SwingConstants.CENTER);
            pnlRound.add(lblRound, BorderLayout.CENTER);

            add(new PlayersPanel(), BorderLayout.CENTER);
            add(new InputPanel(), BorderLayout.SOUTH);

            this.setPreferredSize(new Dimension(900,750));
        }

        /**
         * Holds the players currently in the game.
         */
        public class PlayersPanel extends JPanel {
            /**
             * Constructs a panel of PlayersPanel
             */
            public PlayersPanel() {
                this.setBackground(style.white);
                this.setLayout(new BorderLayout());

                pnlPlayersContainer = new JPanel(new FlowLayout(FlowLayout.LEFT, 30, 40));
                pnlPlayersContainer.setBackground(style.white);
                pnlPlayersContainer.setBorder(style.padding);
                pnlPlayersContainer.setPreferredSize(new Dimension(1100,100));

                JScrollPane scroll = new JScrollPane(pnlPlayersContainer);
                scroll.setBorder(BorderFactory.createEmptyBorder());
                scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
                add(scroll, BorderLayout.CENTER);

                this.setPreferredSize(new Dimension(900, 160));
            }
        }

        /**
         * Holds the input/output components.
         */
        class InputPanel extends JPanel {
            /**
             * Constructs a panel of InputPanel.
             */
            public InputPanel() {
                this.setBackground(style.white);
                this.setLayout(new GridBagLayout());

                GridBagConstraints gbc = new GridBagConstraints();
                gbc.fill = GridBagConstraints.NONE;
                gbc.anchor = GridBagConstraints.CENTER;
                gbc.gridwidth = 1;
                gbc.insets = new Insets(15,10,15,10);

                gbc.gridx = 0;
                gbc.gridy = 0;
                JPanel pnlTimer = style.createPnlRounded(120,30,style.red,style.white);
                pnlTimer.setBackground(style.white);
                add(pnlTimer, gbc);

                lblTimer = style.createLblP("00:30", style.white);
                lblTimer.setIcon(style.iconTimerWhite);
                lblTimer.setVerticalTextPosition(SwingConstants.CENTER);
                pnlTimer.add(lblTimer);

                gbc.gridy = 1;
                gbc.weightx = 400;
                add(new LettersPanel(), gbc);

                gbc.gridy = 2;
                lblErrorMessage = style.createLblH3("Invalid word.", style.red);
                lblErrorMessage.setHorizontalAlignment(SwingConstants.CENTER);
                add(lblErrorMessage, gbc);

                gbc.gridx = 0;
                gbc.gridy = 3;
                gbc.gridwidth = 5;
                gbc.weightx = 500;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                JPanel pnlInputComponents = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
                pnlInputComponents.setBackground(style.white);
                add(pnlInputComponents, gbc);

                txtWordInput = style.createTxtRounded("Enter word here.", style.lightGray, style.gray, 20);
                txtWordInput.setPreferredSize(new Dimension(330,30));
                pnlInputComponents.add(txtWordInput);

                btnClear = style.createBtnIconOnly(style.iconClear, 26,26);
                pnlInputComponents.add(btnClear, gbc);

                this.setPreferredSize(new Dimension(900,400));
            }
        }

        /**
         * Holds the stylized letter set.
         * The stylized letter set is a JavaFX component.
         */
        public class LettersPanel extends JPanel {
            /**
             * Constructs a panel of LettersPanel.
             */
            public LettersPanel() {
                this.setBackground(style.white);

                JLabel label = new JLabel("");
                add(label);

                this.setPreferredSize(new Dimension(700,400));
            }
        }
    }

    /**
     * Holds the score, leaderboard, and entered words panel.
     */
    class RightPanel extends JPanel {
        /**
         * Constructs a panel of RightPanel.
         */
        public RightPanel() {
            this.setBackground(style.lightGray);
            this.setLayout(new BorderLayout(0,20));
            this.setBorder(new EmptyBorder(20,20,20,20));

            JPanel pnlTop = new JPanel();
            pnlTop.setLayout(new FlowLayout(FlowLayout.CENTER, 35, 0));
            pnlTop.setBackground(style.lightGray);
            add(pnlTop, BorderLayout.NORTH);

            JPanel pnlScore = style.createPnlRounded(200,35, style.deepSkyBlue, style.lightGray);
            pnlTop.add(pnlScore);

            lblScore = style.createLblH3("Score " + score, style.white);
            lblScore.setHorizontalAlignment(SwingConstants.CENTER);
            pnlScore.add(lblScore);

            JPanel pnlMiddle = new JPanel();
            pnlMiddle.setLayout(new BorderLayout());
            pnlMiddle.setBackground(style.lightGray);
            pnlMiddle.setPreferredSize(new Dimension(350,250));
            add(pnlMiddle, BorderLayout.CENTER);

            JPanel pnlLeaderboard = style.createPnlRounded(350,250,style.white, style.lightGray);
            pnlLeaderboard.setLayout(new BorderLayout());
            pnlLeaderboard.setBorder(style.padding);
            pnlLeaderboard.setPreferredSize(new Dimension(350,700));
            pnlMiddle.add(pnlLeaderboard, BorderLayout.CENTER);

            JLabel lblLeaderboard = style.createLblH3("Leaderboard", style.black);
            lblLeaderboard.setHorizontalAlignment(SwingConstants.CENTER);
            pnlLeaderboard.add(lblLeaderboard, BorderLayout.NORTH);

            /*
            JScrollPane scrollPaneLeaderboard = new JScrollPane(pnlLeaderboard);
            scrollPaneLeaderboard.setBorder(BorderFactory.createEmptyBorder());
            scrollPaneLeaderboard.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            pnlMiddle.add(pnlLeaderboard);

             */

            JPanel pnlBottom = new JPanel();
            pnlBottom.setLayout(new BorderLayout());
            pnlBottom.setBackground(style.lightGray);
            pnlBottom.setPreferredSize(new Dimension(350,350));
            add(pnlBottom, BorderLayout.SOUTH);

            JPanel pnlEnteredWords = style.createPnlRounded(350,350,style.white, style.lightGray);
            pnlEnteredWords.setLayout(new BorderLayout(0,20));
            pnlEnteredWords.setBorder(new EmptyBorder(10,20,10,0));
            pnlEnteredWords.setPreferredSize(new Dimension(350,700));
            pnlBottom.add(pnlEnteredWords, BorderLayout.CENTER);

            JLabel lblEnteredWords = style.createLblH3("Entered Words", style.black);
            lblEnteredWords.setHorizontalAlignment(SwingConstants.CENTER);
            pnlEnteredWords.add(lblEnteredWords, BorderLayout.NORTH);

            txaEnteredWords = new JTextArea();
            txaEnteredWords.setFont(new Font("Arial", Font.PLAIN, 14));
            txaEnteredWords.setText("words, words, words, words, words, words, words, words, words, words");
            txaEnteredWords.setPreferredSize(new Dimension(350, 700));
            txaEnteredWords.setWrapStyleWord(true);
            txaEnteredWords.setLineWrap(true);
            txaEnteredWords.setOpaque(false);
            txaEnteredWords.setEditable(false);
            txaEnteredWords.setFocusable(false);
            txaEnteredWords.setForeground(style.black);

            JScrollPane scrollPaneEnteredWords = new JScrollPane(txaEnteredWords);
            scrollPaneEnteredWords.setBorder(BorderFactory.createEmptyBorder());
            scrollPaneEnteredWords.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            pnlEnteredWords.add(scrollPaneEnteredWords, BorderLayout.CENTER);

            this.setPreferredSize(new Dimension(400,750));
        }
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public int getScore() {
        return score;
    }

    public JLabel getLblRound() {
        return lblRound;
    }

    public JPanel getPnlPlayersContainer() {
        return pnlPlayersContainer;
    }

    public JLabel getLblTimer() {
        return lblTimer;
    }

    public LeftPanel.LettersPanel getPnlLettersPanel() {
        return pnlLettersPanel;
    }

    public JLabel getLblErrorMessage() {
        return lblErrorMessage;
    }

    public JTextField getTxtWordInput() {
        return txtWordInput;
    }

    public JButton getBtnClear() {
        return btnClear;
    }

    public JLabel getLblScore() {
        return lblScore;
    }

    public JTextArea getTxaLeaderboard() {
        return txaLeaderboard;
    }

    public JTextArea getTxaEnteredWords() {
        return txaEnteredWords;
    }

    public void setRoundNumber(int roundNumber) {
        this.roundNumber = roundNumber;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
