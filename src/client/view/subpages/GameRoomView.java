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
     * The chat input text field.
     */
    private JTextField txtChatInput;
    /**
     * The text area containing the player input.
     */
    private JTextArea txaPlayerInputs;
    /**
     * The text area containing the player chat.
     */
    private JTextArea txaPlayerChat;
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

            JScrollPane scrollPane = new JScrollPane(pnlLeaderboard);
            scrollPane.setBorder(BorderFactory.createEmptyBorder());
            scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
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

        }
    }

    /**
     * Holds the player information.
     */
    class PlayerPanel extends JPanel {
        /**
         * Constructs a panel of PlayerPanel.
         */
        public PlayerPanel() {

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

            JPanel pnlLetter = style.createPnlRounded(80,80,style.deepSkyBlue,style.white);
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

            JPanel pnlTimer = style.createPnlRounded(100,100,style.deepSkyBlue, style.white);
            pnlTimer.setBorder(style.padding);
            pnlTimer.setLayout(new BorderLayout());
            add(pnlTimer);

            JPanel pnlProgressBar = style.createPnlRounded(600,40,style.deepSkyBlue,style.white);
            pnlProgressBar.setPreferredSize(new Dimension(610,40));
            add(pnlProgressBar);

            prgTimer = new JProgressBar(0,800);
            prgTimer.setStringPainted(true);
            prgTimer.setBorderPainted(false);
            prgTimer.setUI(new SwingStylesheet.FancyProgressBar());
            prgTimer.setPreferredSize(new Dimension(600,30));
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

            this.setPreferredSize(new Dimension(900,350));
        }
    }
}
