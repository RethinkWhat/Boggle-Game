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
     * The timer.
     */
    private JLabel lblTimer;
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
     * The chat input text field.
     */
    private JTextField txtChatInput;
    /**
     * The text area containing the player input.
     */
    private JEditorPane edtPlayerInputs;
    /**
     * The LeaderBoard panel containing the players' ranks.
     */
    private LeaderboardPanel pnlLeaderboard;

    /**
     * The stylesheet.
     */
    private SwingStylesheet style = new SwingStylesheet();

    /**
     * Constructs a panel of GameRoomView.
     */
    public GameRoomView() {
        this.setBackground(style.deepSkyBlue);
        this.setBorder(style.padding);
        this.setLayout(new BorderLayout(0,0));

        add(new LeftPanel(), BorderLayout.WEST);

        JPanel pnlRight = new JPanel();
        pnlRight.setLayout(new BorderLayout());
        pnlRight.setPreferredSize(new Dimension(800,750));
        add(pnlRight, BorderLayout.EAST);

        pnlRight.add(new TopRightPanel(), BorderLayout.NORTH);
        pnlRight.add(new BottomRightPanel(), BorderLayout.SOUTH);

        this.setPreferredSize(new Dimension(1300,750));
    }

    /**
     * Holds the leaderboard and players.
     */
    class LeftPanel extends JPanel {
        /**
         * Constructs a panel of LeftPanel.
         */
        public LeftPanel() {
            this.setBackground(style.deepSkyBlue);

            JLayeredPane layeredPane = new JLayeredPane();
            layeredPane.setLayout(null);
            layeredPane.setPreferredSize(new Dimension(400,750));
            add(layeredPane);

            JPanel pnlHeader = style.createPnlRounded(200,50,style.goldenTainoi, style.white);
            pnlHeader.setBackground(style.white);
            pnlHeader.setBounds(100,0,200,45);
            layeredPane.add(pnlHeader, new Integer(1));

            lblRoundNumber = style.createLblH2("Round " + roundNumber, style.white);
            lblRoundNumber.setVerticalAlignment(SwingConstants.CENTER);
            lblRoundNumber.setHorizontalAlignment(SwingConstants.CENTER);
            pnlHeader.add(lblRoundNumber);

            JPanel container = style.createPnlRounded(400,660,style.white, style.deepSkyBlue);
            container.setBorder(style.padding);
            container.setBounds(0,20,400,660);
            container.setBackground(style.deepSkyBlue);
            layeredPane.add(container, new Integer(0));

            pnlLeaderboard = new LeaderboardPanel();
            pnlLeaderboard.add(new PlayerPanel("res/drawable/images/pfp-male-1.png", "monem", 100));

            JScrollPane scrollPane = new JScrollPane(pnlLeaderboard);
            scrollPane.setBorder(BorderFactory.createEmptyBorder());
            scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            scrollPane.setPreferredSize(new Dimension(380,640));
            container.add(scrollPane, BorderLayout.CENTER);

            this.setPreferredSize(new Dimension(400,750));
        }
    }

    /**
     * Holds the player leaderboard.
     */
    class LeaderboardPanel extends JPanel {
        /**
         * Constructs a panel of LeaderboardPanel.
         */
        public LeaderboardPanel() {
            this.setBackground(style.white);
            this.setBorder(style.padding);
            this.setLayout(new FlowLayout(FlowLayout.CENTER, 300,20));

            this.setPreferredSize(new Dimension(350,1200));
        }
    }

    /**
     * Holds the player information.
     */
    class PlayerPanel extends JPanel {
        /**
         * Constructs a panel of PlayerPanel.
         */
        public PlayerPanel(String username, String pfpURL, int points) {
            this.setBackground(style.white);
            this.setLayout(new BorderLayout());

            ImageIcon iconPfp = new ImageIcon(pfpURL);

            JLabel lblPlayerPfp = style.createLblIconOnly(iconPfp, 60,60);
            lblPlayerPfp.setPreferredSize(new Dimension(60,60));
            add(lblPlayerPfp, BorderLayout.WEST);

            JPanel pnlPlayerInfo = new JPanel();
            pnlPlayerInfo.setBackground(style.white);
            pnlPlayerInfo.setLayout(new FlowLayout(FlowLayout.LEFT, 500,10));
            pnlPlayerInfo.setPreferredSize(new Dimension(300,80));
            add(pnlPlayerInfo, BorderLayout.EAST);

            JLabel lblUsername = style.createLblH2(username, style.deepSkyBlue);
            pnlPlayerInfo.add(lblUsername);

            JLabel lblPoints = style.createLblH3(points + " PTS", style.deepSkyBlue);
            pnlPlayerInfo.add(lblPoints);

            this.setPreferredSize(new Dimension(400,100));
        }
    }

    /**
     * Holds the letter set, timer, and toggle buttons.
     */
    class TopRightPanel extends JPanel {
        /**
         * Constructs a panel of TopRightPanel.
         */
        public TopRightPanel() {
            this.setBackground(style.deepSkyBlue);

            JLayeredPane layeredPane = new JLayeredPane();
            layeredPane.setLayout(null);
            layeredPane.setPreferredSize(new Dimension(900,400));
            add(layeredPane);

            JPanel pnlHeader = style.createPnlRounded(200,50,style.goldenTainoi, style.white);
            pnlHeader.setBackground(style.white);
            pnlHeader.setBounds(350,4,200,45);
            layeredPane.add(pnlHeader, new Integer(1));

            JLabel lblLetterSet = style.createLblH2("Letter Set",style.white);
            lblLetterSet.setVerticalAlignment(SwingConstants.CENTER);
            lblLetterSet.setHorizontalAlignment(SwingConstants.CENTER);
            pnlHeader.add(lblLetterSet);

            JPanel container = style.createPnlRounded(900,380,style.white, style.deepSkyBlue);
            container.setBorder(style.padding);
            container.setBounds(50,20,800,375);
            container.setBackground(style.deepSkyBlue);
            container.setLayout(new BorderLayout(0,10));
            layeredPane.add(container, new Integer(0));

            container.add(new ButtonsPanel(), BorderLayout.NORTH);
            container.add(new LetterSetPanel(), BorderLayout.CENTER);
            container.add(new TimerPanel(), BorderLayout.SOUTH);

            this.setPreferredSize(new Dimension(900,400));
        }
    }

    /**
     * Holds the toggle buttons.
     */
    class ButtonsPanel extends JPanel {
        /**
         * Constructs a panel of ButtonsPanel.
         */
        public ButtonsPanel() {
           this.setBackground(style.white);
           this.setLayout(new FlowLayout(FlowLayout.LEFT, 0,0));

           btnMusicToggle = style.createBtnIconOnly(style.iconMusicOn, 25,25);
           add(btnMusicToggle);

           btnSoundToggle = style.createBtnIconOnly(style.iconSoundOn, 25,25);
           add(btnSoundToggle);
        }
    }

    /**
     * Holds the given letter set.
     */
    class LetterSetPanel extends JPanel {
        /**
         * Constructs a panel of LetterSetPanel
         */
        public LetterSetPanel() {
            this.setBackground(style.white);
            this.setLayout(new GridLayout(2,10, 5,0));

            for (int i = 0; i < 20; i++) {
                add(new LetterPanel(String.valueOf(i)));
            }

            this.setPreferredSize(new Dimension(800,50));
        }
    }

    /**
     * Holds a certain letter
     */
    class LetterPanel extends JPanel {
        /**
         * Constructs a panel of LetterPanel.
         */
        public LetterPanel(String letter) {
            this.setBackground(style.white);

            JPanel pnlLetter = style.createPnlRounded(70,80,style.deepSkyBlue,style.white);
            add(pnlLetter);

            JLabel lblLetter = style.createLblH1(letter, style.white);
            lblLetter.setVerticalAlignment(SwingConstants.CENTER);
            lblLetter.setVerticalTextPosition(SwingConstants.CENTER);
            lblLetter.setHorizontalAlignment(SwingConstants.CENTER);
            lblLetter.setHorizontalTextPosition(SwingConstants.CENTER);
            pnlLetter.add(lblLetter);

            this.setPreferredSize(new Dimension(80,80));
        }
    }

    /**
     * Holds the timer and progress bar.
     */
    class TimerPanel extends JPanel {
        /**
         * Constructs a panel of TimerPanel.
         */
        public TimerPanel() {
            this.setBackground(style.white);
            this.setLayout(new FlowLayout(FlowLayout.CENTER, -6,0));

            JPanel pnlTimer = style.createPnlRounded(160,80,style.deepSkyBlue, style.white);
            pnlTimer.setBorder(style.padding);
            pnlTimer.setLayout(new BorderLayout());
            add(pnlTimer);

            lblTimer = style.createLblH1(roundDuration + "s", style.white);
            lblTimer.setIcon(style.iconRoundTimer);
            lblTimer.setVerticalAlignment(SwingConstants.CENTER);
            lblTimer.setHorizontalAlignment(SwingConstants.CENTER);
            pnlTimer.add(lblTimer, BorderLayout.CENTER);

            JPanel pnlProgressBar = style.createPnlRounded(560,40,style.deepSkyBlue,style.white);
            pnlProgressBar.setPreferredSize(new Dimension(560,40));
            add(pnlProgressBar);

            prgTimer = new JProgressBar(0,100);
            prgTimer.setBackground(style.goldenTainoi);
            prgTimer.setStringPainted(true);
            prgTimer.setBorderPainted(false);
            prgTimer.setValue(100);
            prgTimer.setUI(new SwingStylesheet.FancyProgressBar());
            prgTimer.setPreferredSize(new Dimension(550,30));
            pnlProgressBar.add(prgTimer);

            this.setPreferredSize(new Dimension(900, 100));
        }
    }

    /**
     * Holds the inputs and text areas containing the chat and the word inputs.
     */
    class BottomRightPanel extends JPanel {
        /**
         * Constructs a panel of BottomRightPanel.
         */
        public BottomRightPanel() {
            this.setBackground(style.deepSkyBlue);

            JLayeredPane layeredPane = new JLayeredPane();
            layeredPane.setLayout(null);
            layeredPane.setPreferredSize(new Dimension(900,350));
            add(layeredPane);

            JPanel pnlHeader = style.createPnlRounded(170,50,style.goldenTainoi, style.white);
            pnlHeader.setBackground(style.white);
            pnlHeader.setBounds(360,68,170,45);
            layeredPane.add(pnlHeader, new Integer(1));

            JLabel lblLetterSet = style.createLblH2("Answers",style.white);
            lblLetterSet.setVerticalAlignment(SwingConstants.CENTER);
            lblLetterSet.setHorizontalAlignment(SwingConstants.CENTER);
            pnlHeader.add(lblLetterSet);

            JPanel container = style.createPnlRounded(900,250,style.white, style.deepSkyBlue);
            container.setBorder(style.padding);
            container.setBounds(50,88,800,250);
            container.setBackground(style.deepSkyBlue);
            layeredPane.add(container, new Integer(0));

            container.add(new InputPanel());

            this.setPreferredSize(new Dimension(900,350));
        }
    }

    /**
     * Holds the text field and text area.
     */
    class InputPanel extends JPanel {
        /**
         * Constructs a panel of InputPanel.
         */
        public InputPanel() {
            this.setBackground(style.white);
            this.setLayout(new BorderLayout());
            this.setBorder(style.padding);

            edtPlayerInputs = new JEditorPane();
            edtPlayerInputs.setEditable(false);
            edtPlayerInputs.setContentType("text/html");
            edtPlayerInputs.setBackground(style.white);
            edtPlayerInputs.setBorder(style.padding);
            edtPlayerInputs.setPreferredSize(new Dimension(800,500));

            JScrollPane scrollPane = new JScrollPane(edtPlayerInputs);
            scrollPane.setBorder(BorderFactory.createEmptyBorder());
            scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            scrollPane.setPreferredSize(new Dimension(850,100));
            scrollPane.setMinimumSize(new Dimension(850,100));
            add(scrollPane, BorderLayout.CENTER);

            JPanel pnlButtons = new JPanel(new FlowLayout(FlowLayout.CENTER, 10,0));
            pnlButtons.setBackground(style.white);
            pnlButtons.setPreferredSize(new Dimension(900,60));
            add(pnlButtons, BorderLayout.SOUTH);

            txtWordInput = style.createTxtRounded("Enter word here.", style.lightGray, style.gray, 20);
            txtWordInput.setPreferredSize(new Dimension(800,40));
            pnlButtons.add(txtWordInput);

            btnClear = style.createBtnIconOnly(style.iconClear, 30,30);
            pnlButtons.add(btnClear);

            this.setPreferredSize(new Dimension(900,150));
        }
    }
}
