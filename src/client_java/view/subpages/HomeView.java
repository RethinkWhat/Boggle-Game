package client_java.view.subpages;

import shared.SwingStylesheet;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class HomeView extends JPanel {
    /**
     * The round length.
     */
    private int roundLength = 30;
    /**
     * The join game button.
     */
    private JButton btnJoinGame;
    /**
     * The stylesheet.
     */
    private SwingStylesheet style = new SwingStylesheet();

    /**
     * Constructs a panel of HomeView.
     */
    public HomeView() {
        this.setBackground(style.white);
        this.setLayout(new GridLayout(0,2, 20, 0));

        add(new LeftPanel());

        ImageIcon iconSample = new ImageIcon("res/drawable/images/img-sample.png");

        JLabel lblSample = new JLabel();
        lblSample.setHorizontalAlignment(SwingConstants.RIGHT);
        lblSample.setIcon(iconSample);
        add(lblSample);

        this.setPreferredSize(new Dimension(1300,750));
    }

    /**
     * Holds the general instructions of the game.
     */
    class LeftPanel extends JPanel {
        /**
         * Constructs a panel of LeftPanel.
         */
        public LeftPanel() {
            this.setBackground(style.white);
            this.setBorder(new EmptyBorder(90,150,0,0));
            this.setLayout(new FlowLayout(FlowLayout.CENTER, 300, 20));

            JLabel lblHowto = style.createLblH1("How to Play:", style.black);
            lblHowto.setHorizontalAlignment(SwingConstants.CENTER);
            lblHowto.setVerticalAlignment(SwingConstants.CENTER);
            add(lblHowto);

            JLabel lblMinPlayer = style.createLblH3("MINIMUM OF 2 PLAYERS", style.black);
            lblMinPlayer.setHorizontalAlignment(SwingConstants.CENTER);
            lblMinPlayer.setVerticalAlignment(SwingConstants.CENTER);
            add(lblMinPlayer);

            JTextArea txaInstructions = new JTextArea();
            txaInstructions.setFont(new Font("Arial", Font.PLAIN, 15));
            txaInstructions.setText("1. Each player will be given the same set of 7 vowels and 13 consonants. \n\n" +
                    "2. Each player is given " + roundLength + " seconds in a single round to form as many unique English words possible.\n\n" +
                    "Scores are based on the length of the word (e.g., WORD = 4 points). Players who enter the same word will not be honored points." +
                    " Scores are computed after each round.\n\n" +
                    "3. After every round, a new set of vowels and consonants will be given.\n\n" +
                    "4. The first player to win 3 rounds straight is declared the winner.");
            txaInstructions.setPreferredSize(new Dimension(350, 350));
            txaInstructions.setWrapStyleWord(true);
            txaInstructions.setLineWrap(true);
            txaInstructions.setOpaque(false);
            txaInstructions.setEditable(false);
            txaInstructions.setFocusable(false);
            txaInstructions.setForeground(style.black);
            add(txaInstructions);

            btnJoinGame = style.createBtnRounded("Join a Game", style.deepSkyBlue, style.black, 10);
            btnJoinGame.setPreferredSize(new Dimension(260,40));
            add(btnJoinGame);

            this.setPreferredSize(new Dimension(650,750));
        }
    }

    /**
     * Retrieves the current JButton of btnJoinGame.
     * @return The current btnJoinGame.
     */
    public JButton getBtnJoinGame() {
        return btnJoinGame;
    }

    /**
     * Sets a specified action listener for btnJoinGame.
     * @param actionListener The specified action listener.
     */
    public void setJoinListener(ActionListener actionListener) {
        btnJoinGame.addActionListener(actionListener);
    }
}
