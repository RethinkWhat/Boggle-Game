package client.view.subpages;

import shared.SwingStylesheet;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * The GameRoomView contains the game elements where the main game takes place.
 */
public class GameRoomView extends JPanel {
    /**
     * The timer.
     */
    private JLabel lblTimer;
    /**
     * The round number.
     */
    private int roundNumber = 1;
    /**
     * The round duration.
     */
    private int roundDuration = 30;
    /**
     * The round number.
     */
    private JLabel lblRoundNumber;
    /**
     * The toggle button to turn on/off the music.
     */
    private JButton btnMusicToggle;
    /**
     * The toggle button to turn on/off the sound.
     */
    private JButton btnSoundToggle;
    /**
     * The progressbar for the timer.
     */
    private JProgressBar prgTimer;
    /**
     * The word input text field.
     */
    private JTextField txtWordInput;
    /**
     * The clear input button for txtWordInput.
     */
    private JButton btnClear;
    /**
     * The text area containing the player input.
     */
    private JTextArea txaPlayerInputs;
    /**
     * The error message.
     */
    private JLabel lblErrorMessage;
    /**
     * The height of txaPlayerInputs.
     */
    private int txaHeight = 100;
    /**
     * The scroll pane used for txaPlayerInputs.
     */
    private JScrollPane scrollPane;
    /**
     * The LeaderBoard panel containing the players' ranks.
     */
    private LeaderboardPanel pnlLeaderboard;
    /**
     * The LetterSetPanel containing the given letter set for user input.
     */
    private LetterSetPanel pnlLetterSet;
    /**
     * The stylesheet.
     */
    private SwingStylesheet style = new SwingStylesheet();
    /**
     * The LeftPanel.
     */
    private LeftPanel pnlLeft;
    /**
     * The RightPanel.
     */
    private JPanel pnlRight;
    /**
     * The TopRightPanel.
     */
    private TopRightPanel pnlTopRight;
    /**
     * The BottomRightPanel.
     */
    private BottomRightPanel pnlBottomRight;

    /**
     * Constructs a panel of GameRoomView.
     */
    public GameRoomView() {
        this.setBackground(style.deepSkyBlue);
        this.setBorder(style.padding);
        this.setLayout(new BorderLayout(0, 0));

        pnlLeft = new LeftPanel();
        add(pnlLeft, BorderLayout.WEST);

        pnlRight = new JPanel();
        pnlRight.setLayout(new BorderLayout());
        pnlRight.setPreferredSize(new Dimension(800, 750));
        add(pnlRight, BorderLayout.EAST);

        pnlTopRight = new TopRightPanel();
        pnlBottomRight = new BottomRightPanel();
        pnlRight.add(pnlTopRight, BorderLayout.NORTH);
        pnlRight.add(pnlBottomRight, BorderLayout.SOUTH);

        this.setPreferredSize(new Dimension(1300, 750));
    }

    /**
     * Holds the leaderboard and players.
     */
    public class LeftPanel extends JPanel {
        /**
         * Constructs a panel of LeftPanel.
         */
        public LeftPanel() {
            this.setBackground(style.deepSkyBlue);

            JLayeredPane layeredPane = new JLayeredPane();
            layeredPane.setLayout(null);
            layeredPane.setPreferredSize(new Dimension(400, 750));
            add(layeredPane);

            JPanel pnlHeader = style.createPnlRounded(200, 50, style.goldenTainoi, style.white);
            pnlHeader.setBackground(style.white);
            pnlHeader.setBounds(100, 0, 200, 45);
            layeredPane.add(pnlHeader, new Integer(1));

            lblRoundNumber = style.createLblH2("Round " + roundNumber, style.white);
            lblRoundNumber.setVerticalAlignment(SwingConstants.CENTER);
            lblRoundNumber.setHorizontalAlignment(SwingConstants.CENTER);
            pnlHeader.add(lblRoundNumber);

            JPanel container = style.createPnlRounded(400, 660, style.white, style.deepSkyBlue);
            container.setBorder(style.padding);
            container.setBounds(0, 20, 400, 660);
            container.setBackground(style.deepSkyBlue);
            layeredPane.add(container, new Integer(0));

            pnlLeaderboard = new LeaderboardPanel();
       /*     pnlLeaderboard.add(new PlayerPanel("res/drawable/images/pfp-male-1.png", "asdwerfteq", 100));
            pnlLeaderboard.add(new PlayerPanel("res/drawable/images/pfp-male-1.png", "gfgfewgwegewg", 100));
            pnlLeaderboard.add(new PlayerPanel("res/drawable/images/pfp-male-1.png", "monfabdgaaem", 100));
            pnlLeaderboard.add(new PlayerPanel("res/drawable/images/pfp-male-1.png", "mosdgg2gadf", 100));

        */

            JScrollPane scrollPane = new JScrollPane(pnlLeaderboard);
            scrollPane.setBorder(BorderFactory.createEmptyBorder());
            scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            scrollPane.setPreferredSize(new Dimension(380, 640));
            container.add(scrollPane, BorderLayout.CENTER);

            this.setPreferredSize(new Dimension(400, 750));
        }
    }

    /**
     * Holds the player leaderboard.
     */
    public class LeaderboardPanel extends JPanel {
        /**
         * Constructs a panel of LeaderboardPanel.
         */
        public LeaderboardPanel() {
            this.setBackground(style.white);
            this.setBorder(style.padding);
            this.setLayout(new FlowLayout(FlowLayout.CENTER, 300, 5));

            this.setPreferredSize(new Dimension(360, 1200));
        }
    }

    /**
     * Holds the player information.
     */
    public class PlayerPanel extends JPanel {
        /**
         * Constructs a panel of PlayerPanel.
         */
        public PlayerPanel(String pfpURL, String username, int points) {
            this.setBackground(style.white);
            this.setBorder(style.padding);
            this.setLayout(new GridBagLayout());

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.weightx = 60;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.anchor = GridBagConstraints.WEST;
            gbc.gridwidth = 1;

            ImageIcon iconPfp = new ImageIcon(pfpURL);

            gbc.gridx = 0;
            gbc.gridy = 0;
            JLabel lblPlayerPfp = style.createLblIconOnly(iconPfp, 60, 60);
            add(lblPlayerPfp, gbc);

            gbc.weightx = 100;
            gbc.gridx = 1;
            gbc.gridwidth = 3;
            gbc.anchor = GridBagConstraints.WEST;
            JLabel lblUsername = style.createLblH3("<html>" + username + "<br>" + points + " pts" + "</html>",
                    style.deepSkyBlue);
            lblUsername.setHorizontalAlignment(SwingConstants.LEFT);
            add(lblUsername, gbc);

            this.setPreferredSize(new Dimension(360, 100));
        }
    }

    /**
     * Holds the letter set, timer, and toggle buttons.
     */
    public class TopRightPanel extends JPanel {
        /**
         * The timer panel.
         */
        private TimerPanel timerPanel;
        /**
         * The buttons panel.
         */
        private ButtonsPanel buttonsPanel;

        /**
         * Constructs a panel of TopRightPanel.
         */
        public TopRightPanel() {
            this.setBackground(style.deepSkyBlue);

            JLayeredPane layeredPane = new JLayeredPane();
            layeredPane.setLayout(null);
            layeredPane.setPreferredSize(new Dimension(900, 400));
            add(layeredPane);

            JPanel pnlHeader = style.createPnlRounded(200, 50, style.goldenTainoi, style.white);
            pnlHeader.setBackground(style.white);
            pnlHeader.setBounds(350, 4, 200, 45);
            layeredPane.add(pnlHeader, new Integer(1));

            JLabel lblLetterSet = style.createLblH2("Letter Set", style.white);
            lblLetterSet.setVerticalAlignment(SwingConstants.CENTER);
            lblLetterSet.setHorizontalAlignment(SwingConstants.CENTER);
            pnlHeader.add(lblLetterSet);

            JPanel container = style.createPnlRounded(900, 380, style.white, style.deepSkyBlue);
            container.setBorder(style.padding);
            container.setBounds(50, 20, 800, 375);
            container.setBackground(style.deepSkyBlue);
            container.setLayout(new BorderLayout(0, 10));
            layeredPane.add(container, new Integer(0));

            timerPanel = new TimerPanel();
            buttonsPanel = new ButtonsPanel();
            pnlLetterSet = new LetterSetPanel();

            container.add(buttonsPanel, BorderLayout.NORTH);
            container.add(pnlLetterSet, BorderLayout.CENTER);
            container.add(timerPanel, BorderLayout.SOUTH);

            this.setPreferredSize(new Dimension(900, 400));
        }

        /**
         * Retrieves the TimerPanel of timerPanel.
         *
         * @return The current pnlTimer
         */
        public TimerPanel getTimerPanel() {
            return timerPanel;
        }

        public ButtonsPanel getButtonsPanel() {
            return buttonsPanel;
        }
    }

    /**
     * Holds the toggle buttons.
     */
    public class ButtonsPanel extends JPanel {
        /**
         * Constructs a panel of ButtonsPanel.
         */
        public ButtonsPanel() {
            this.setBackground(style.white);
            this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

            btnMusicToggle = style.createBtnIconOnly(style.iconMusicOn, 25, 25);
            add(btnMusicToggle);

            btnSoundToggle = style.createBtnIconOnly(style.iconSoundOn, 25, 25);
            add(btnSoundToggle);
        }
    }

    /**
     * Holds the given letter set.
     */
    public class LetterSetPanel extends JPanel {
        /**
         * Constructs a panel of LetterSetPanel
         */
        public LetterSetPanel() {
            this.setBackground(style.white);
            this.setLayout(new GridLayout(2, 10, 5, 0));

            this.setPreferredSize(new Dimension(800, 50));
        }
    }

    /**
     * Holds a certain letter.
     */
    public class LetterPanel extends JPanel {
        /**
         * Constructs a panel of LetterPanel.
         */
        public LetterPanel(String letter, Color backgroundColor) {
            this.setBackground(style.white);

            JPanel pnlLetter = style.createPnlRounded(70, 80, style.deepSkyBlue, style.white);
            pnlLetter.setBackground(backgroundColor);
            add(pnlLetter);

            JLabel lblLetter = style.createLblH1(letter, style.white);
            lblLetter.setVerticalAlignment(SwingConstants.CENTER);
            lblLetter.setVerticalTextPosition(SwingConstants.CENTER);
            lblLetter.setHorizontalAlignment(SwingConstants.CENTER);
            lblLetter.setHorizontalTextPosition(SwingConstants.CENTER);
            pnlLetter.add(lblLetter);

            this.setPreferredSize(new Dimension(80, 80));
        }
    }

    /**
     * Holds the timer and progress bar.
     */

    public class TimerPanel extends JPanel {
        /**
         * Constructs a panel of TimerPanel.
         */
        public TimerPanel() {
            this.setBackground(style.white);
            this.setLayout(new FlowLayout(FlowLayout.CENTER, -6, 0));

            JPanel pnlTimer = style.createPnlRounded(160, 80, style.deepSkyBlue, style.white);
            pnlTimer.setBorder(style.padding);
            pnlTimer.setLayout(new BorderLayout());
            add(pnlTimer);

            lblTimer = style.createLblH1(" ", style.white);
            lblTimer.setIcon(style.iconRoundTimer);
            lblTimer.setVerticalAlignment(SwingConstants.CENTER);
            lblTimer.setHorizontalAlignment(SwingConstants.CENTER);
            pnlTimer.add(lblTimer, BorderLayout.CENTER);

            JPanel pnlProgressBar = style.createPnlRounded(560, 40, style.deepSkyBlue, style.white);
            pnlProgressBar.setPreferredSize(new Dimension(560, 40));
            add(pnlProgressBar);

            prgTimer = new JProgressBar(0, 100);
            prgTimer.setBackground(style.goldenTainoi);
            prgTimer.setStringPainted(true);
            prgTimer.setBorderPainted(false);
            prgTimer.setValue(100);
            prgTimer.setUI(new SwingStylesheet.FancyProgressBar());
            prgTimer.setPreferredSize(new Dimension(550, 30));
            pnlProgressBar.add(prgTimer);

            this.setPreferredSize(new Dimension(900, 100));
        }
    }

    /**
     * Holds the inputs and text areas containing the chat and the word inputs.
     */
    public class BottomRightPanel extends JPanel {
        /**
         * Constructs a panel of BottomRightPanel.
         */
        public BottomRightPanel() {
            this.setBackground(style.deepSkyBlue);

            JLayeredPane layeredPane = new JLayeredPane();
            layeredPane.setLayout(null);
            layeredPane.setPreferredSize(new Dimension(900, 350));
            add(layeredPane);

            JPanel pnlHeader = style.createPnlRounded(170, 50, style.goldenTainoi, style.white);
            pnlHeader.setBackground(style.white);
            pnlHeader.setBounds(360, 68, 170, 45);
            layeredPane.add(pnlHeader, new Integer(1));

            JLabel lblLetterSet = style.createLblH2("Answers", style.white);
            lblLetterSet.setVerticalAlignment(SwingConstants.CENTER);
            lblLetterSet.setHorizontalAlignment(SwingConstants.CENTER);
            pnlHeader.add(lblLetterSet);

            JPanel container = style.createPnlRounded(900, 250, style.white, style.deepSkyBlue);
            container.setBorder(style.padding);
            container.setBounds(50, 88, 800, 250);
            container.setBackground(style.deepSkyBlue);
            layeredPane.add(container, new Integer(0));

            container.add(new InputPanel());

            this.setPreferredSize(new Dimension(900, 350));
        }
    }

    /**
     * Holds the text field and text area.
     */
    public class InputPanel extends JPanel {
        /**
         * Constructs a panel of InputPanel.
         */
        public InputPanel() {
            this.setBackground(style.white);
            this.setLayout(new GridBagLayout());
            this.setBorder(style.padding);

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.weightx = 100;
            gbc.anchor = GridBagConstraints.CENTER;
            gbc.fill = GridBagConstraints.BOTH;
            gbc.gridwidth = 1;

            txaPlayerInputs = new JTextArea();
            txaPlayerInputs.setEditable(false);
            txaPlayerInputs.setFont(style.bowlbyOne.deriveFont(12f));
            txaPlayerInputs.setBackground(style.white);
            txaPlayerInputs.setBorder(style.padding);
            txaPlayerInputs.setPreferredSize(new Dimension(190, txaHeight));

            DefaultCaret caret = (DefaultCaret) txaPlayerInputs.getCaret();
            caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

            gbc.gridx = 0;
            gbc.gridy = 0;
            scrollPane = new JScrollPane(txaPlayerInputs);
            scrollPane.setBorder(BorderFactory.createEmptyBorder());
            scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            scrollPane.setPreferredSize(txaPlayerInputs.getPreferredSize());
            scrollPane.setMinimumSize(new Dimension(100, 100));
            add(scrollPane, gbc);

            gbc.gridy = 1;
            JPanel pnlButtons = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
            pnlButtons.setBackground(style.white);
            pnlButtons.setPreferredSize(new Dimension(800, 60));
            add(pnlButtons, gbc);

            txtWordInput = style.createTxtRounded("Enter word here.", style.lightGray, style.gray, 20);
            txtWordInput.setPreferredSize(new Dimension(300, 40));
            pnlButtons.add(txtWordInput);

            btnClear = style.createBtnIconOnly(style.iconClear, 30, 30);
            pnlButtons.add(btnClear);

            gbc.gridy = 2;
            lblErrorMessage = style.createLblH4("", style.red);
            lblErrorMessage.setHorizontalAlignment(SwingConstants.CENTER);
            add(lblErrorMessage, gbc);

            this.setPreferredSize(new Dimension(800, 160));
        }
    }

    /**
     * Retrieves the current round number.
     *
     * @return The current round number.
     */
    public int getRoundNumber() {
        return roundNumber;
    }

    /**
     * Retrieves the current round duration.
     *
     * @return The current round duration.
     */
    public int getRoundDuration() {
        return roundDuration;
    }

    /**
     * Retrieves the current JLabel of lblRoundNumber.
     *
     * @return The current lblRoundNumber.
     */
    public JLabel getLblRoundNumber() {
        return lblRoundNumber;
    }

    /**
     * Retrieves the current JButton of btnMusicToggle.
     *
     * @return The current btnMusicToggle.
     */
    public JButton getBtnMusicToggle() {
        return btnMusicToggle;
    }

    /**
     * Retrieves the current JButton of btnSoundToggle.
     *
     * @return The current btnSoundToggle.
     */
    public JButton getBtnSoundToggle() {
        return btnSoundToggle;
    }


    /**
     * Retrieves the current JProgressBar of prgTimer.
     *
     * @return The current prgTimer.
     */
    public JProgressBar getPrgTimer() {
        return prgTimer;
    }

    /**
     * Retrieves the current JTextArea of txaPlayerInputs.
     *
     * @return THe current txaPlayerInputs.
     */
    public JTextArea getTxaPlayerInputs() {
        return txaPlayerInputs;
    }

    /**
     * Retrieves the current JScrollPane of scrollPane.
     *
     * @return The current scrollPane.
     */
    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    /**
     * Retrieves the current JTextField of txtWordInput.
     *
     * @return The current txtWordInput.
     */
    public JTextField getTxtWordInput() {
        return txtWordInput;
    }

    /**
     * Retrieves the current JButton of btnClear.
     *
     * @return The current btnClear.
     */
    public JButton getBtnClear() {
        return btnClear;
    }

    /**
     * Retrieves the current JLabel of lblErrorMessage.
     *
     * @return The current lblErrorMessage.
     */
    public JLabel getLblErrorMessage() {
        return lblErrorMessage;
    }

    /**
     * Retrieves the current LeaderboardPanel of pnlLeaderboard.
     *
     * @return The current pnlLeaderboard.
     */
    public LeaderboardPanel getPnlLeaderboard() {
        return pnlLeaderboard;
    }

    /**
     * Retrieves the current RightPanel of pnlRight.
     *
     * @return The current pnlRight.
     */
    public JPanel getPnlRight() {
        return pnlRight;
    }

    /**
     * Retrieves the current TopRightPanel of pnlTopRight.
     *
     * @return The current pnlTopRight.
     */
    public TopRightPanel getPnlTopRight() {
        return pnlTopRight;
    }

    /**
     * Retrieves the current BottomRightPanel of pnlBottomRight.
     *
     * @return The current pnlBottomRight.
     */
    public BottomRightPanel getPnlBottomRight() {
        return pnlBottomRight;
    }

    /**
     * Sets a new round number.
     *
     * @param roundNumber The new round number.
     */
    public void setRoundNumber(int roundNumber) {
        this.roundNumber = roundNumber;
    }

    /**
     * Sets a new round duration.
     *
     * @param roundDuration The new round duration.
     */
    public void setRoundDuration(int roundDuration) {
        this.roundDuration = roundDuration;
    }

    /**
     * Sets a specified action listener for btnMusicToggle.
     *
     * @param actionListener The specified action listener.
     */
    public void setMusicToggleListener(ActionListener actionListener) {
        btnMusicToggle.addActionListener(actionListener);
    }

    /**
     * Sets a specified action listener for btnSoundToggle.
     *
     * @param actionListener The specified action listener.
     */
    public void setSoundToggleListener(ActionListener actionListener) {
        btnSoundToggle.addActionListener(actionListener);
    }

    /**
     * Sets a specified action listener for txtWordInput.
     *
     * @param actionListener The specified action listener.
     */
    public void setInputListener(ActionListener actionListener) {
        txtWordInput.addActionListener(actionListener);
    }

    /**
     * Sets a specified error message to lblErrorMessage.
     *
     * @param text The specified text.
     */
    public void setErrorMessage(String text) {
        SwingUtilities.invokeLater(() -> lblErrorMessage.setText(text));
    }

    /**
     * Sets a specified max value for prgTimer as a starting point for the progress bar decrement.
     *
     * @param maxValue The specified maxValue.
     */
    public void setPrgTimerMaxValue(int maxValue) {
        SwingUtilities.invokeLater(() -> prgTimer.setMaximum(maxValue));
    }

    /**
     * Sets a specified action listener for btnClear.
     *
     * @param actionListener The specified action listener.
     */
    public void setClearListener(ActionListener actionListener) {
        btnClear.addActionListener(actionListener);
    }

    /**
     * Sets a specified duration for lblTimer.
     *
     * @param duration The specified duration.
     */
    public void setLblTimerTxt(long duration) {
        SwingUtilities.invokeLater(() -> lblTimer.setText(duration + "s"));
    }

    /**
     * Sets a specified icon to btnMusic.
     *
     * @param icon The specified icon.
     */
    public void setBtnMusicIcon(ImageIcon icon) {
        Image scaledImage = icon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        SwingUtilities.invokeLater(() -> btnMusicToggle.setIcon(scaledIcon));
    }

    /**
     * Sets a specified icon to btnSound.
     *
     * @param icon The specified icon.
     */
    public void setBtnSoundIcon(ImageIcon icon) {
        Image scaledImage = icon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        SwingUtilities.invokeLater(() -> btnSoundToggle.setIcon(scaledIcon));
    }

    /**
     * Adds a specified player in the game leaderboard.
     *
     * @param username The specified player username.
     * @param pfpURL   The specified player profile picture URL.
     * @param points   The specified player points accumulated from the previous rounds.
     */
    public void addPlayerInLeaderboard(String username, String pfpURL, int points) {
        SwingUtilities.invokeLater(() -> pnlLeaderboard.add(new PlayerPanel(pfpURL, username, points)));
    }

    public void removeAllInLeaderboard() {
        pnlLeaderboard.removeAll();
    }

    public void addLetterToLetterSet(String letter, Color backgroundColor) {
        SwingUtilities.invokeLater(() -> pnlLetterSet.add(new LetterPanel(letter, backgroundColor)));
    }

    public void removeAllLetters() {
        pnlLetterSet.removeAll();
    }

    /**
     * Adds a specified text in txaPlayerInputs.
     *
     * @param username The specified username.
     * @param input    The specified input (word).
     */
    public void addUserInput(String username, String input) {
        SwingUtilities.invokeLater(() -> txaPlayerInputs.append(username + ": " + input + "\n"));
    }

    /**
     * Sets a specified value of prgTimer.
     *
     * @param value The sepcified value.
     */
    public void setPrgTimerValue(int value) {
        SwingUtilities.invokeLater(() -> prgTimer.setValue(value));
    }

    /**
     * Sets a specified value of prgTimer.
     *
     * @param value The specified maximum value.
     */
    public void setPrgTimerMaxVal(int value) {
        SwingUtilities.invokeLater(() -> {
            prgTimer.setMaximum(value);
            prgTimer.setMinimum(0);
        });
    }

    /**
     * Updates the viewport of txaPlayerInputs.
     */
    public void updateTxaHeight() {
        txaHeight += 20;
        SwingUtilities.invokeLater(() -> {
            txaPlayerInputs.setPreferredSize(new Dimension(190, txaHeight));
            repaint();
            revalidate();
        });
    }

    /**
     * Retrieves the current JLabel of lblTimer.
     * @return The current lblTimer.
     */
    public JLabel getLblTimer() {
        return lblTimer;
    }

    /**
     * Retrieves the current LetterSetPanel of pnlLetterSet.
     * @return The current pnlLetterSet.
     */
    public LetterSetPanel getPnlLetterSet() {
        return pnlLetterSet;
    }
}
