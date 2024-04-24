package client.view.subpages;

import shared.SwingStylesheet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Contains the general instructions of how to play Word Factory.
 */
public class HowToPlayView extends JPanel {
    /**
     * The round duration.
     */
    private int roundDuration = 30;
    /**
     * The join game button.
     */
    private JButton btnJoinGame;
    /**
     * The stylesheet.
     */
    private SwingStylesheet style = new SwingStylesheet();

    /**
     * Constructs a panel of HowToPlayView.
     */
    public HowToPlayView() {
        setBackground(style.deepSkyBlue);
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(1300, 750));

        CenterPanel centerPanel = new CenterPanel();

        add(centerPanel, BorderLayout.CENTER);
    }

    /**
     * CenterPanel class containing the panel of the instructions.
     */
    class CenterPanel extends JPanel {

        public CenterPanel() {
            setBackground(style.deepSkyBlue);
            setLayout(new GridBagLayout());

            JLayeredPane layeredPane = new JLayeredPane();
            layeredPane.setLayout(null);
            layeredPane.setPreferredSize(new Dimension(700,600));
            add(layeredPane);

            JPanel pnlHeader = style.createPnlRounded(500, 50, style.goldenTainoi, style.white);
            pnlHeader.setBackground(style.white);
            pnlHeader.setBounds(160,0,400,45);
            layeredPane.add(pnlHeader, new Integer(1));

            JLabel lblHowToPlay = style.createLblH2("How To Play: ",style.white);
            lblHowToPlay.setVerticalAlignment(SwingConstants.CENTER);
            lblHowToPlay.setHorizontalAlignment(SwingConstants.CENTER);
            pnlHeader.add(lblHowToPlay);

            JPanel container = style.createPnlRounded(700,600,style.white, style.deepSkyBlue);
            container.setBorder(style.padding);
            container.setBounds(0,20,700,580);
            container.setBackground(style.deepSkyBlue);
            layeredPane.add(container, new Integer(0));

            container.add(new InstructionsPanel());

            this.setPreferredSize(new Dimension(700,600));
        }
    }


    /**
     * InstructionsPanel class containing the instruction's layout.
     */
    class InstructionsPanel extends JPanel {
        /**
         * The minimum players label.
         */
        private JLabel lblMinPlayers;
        /**
         * The label for instructions.
         */
        private JLabel lblInstructionOne;
        /**
         * The label for instructions.
         */
        private JLabel lblInstructionTwo;
        /**
         * The label for instructions.
         */
        private JLabel lblInstructionThree;
        /**
         * The label for instructions.
         */
        private JLabel lblInstructionFour;
        /**
         * The label for instructions.
         */
        private JLabel lblInstructionFive;

        /**
         * Constructs a panel of InstructionsPanel.
         */
        public InstructionsPanel() {
            setBackground(style.white);

            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10);
            gbc.anchor = GridBagConstraints.WEST;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.weightx = 1;
            gbc.gridy = 0;

            lblMinPlayers = style.createLblH2("MINIMUM OF 2 PLAYERS", style.black);
            lblMinPlayers.setHorizontalAlignment(SwingConstants.CENTER);
            add(lblMinPlayers, gbc);

            gbc.gridy = 2;
            lblInstructionOne = style.createLblP("<html>1. Each player will be given the same set of 7 vowels and 13 consonants.</html>", style.black);
            add(lblInstructionOne, gbc);

            gbc.gridy = 3;
            lblInstructionTwo = style.createLblP("<html>2. Each player is given 30 seconds in a single round to form as many unique English words possible.</html>", style.black);
            add(lblInstructionTwo, gbc);

            gbc.gridy = 4;
            lblInstructionThree = style.createLblP("<html>3. Scores are based on the length of the word(e.g., WORD = 4 points). Players who enter the same word will not be honored points.</html>", style.black);
            lblInstructionThree.setPreferredSize(new Dimension(500, 30));
            add(lblInstructionThree, gbc);

            gbc.gridy = 5;
            lblInstructionFour = style.createLblP("<html>4. After every round, a new set of vowels and consonants will be given.</html>", style.black);
            add(lblInstructionFour, gbc);

            gbc.gridy = 6;
            lblInstructionFive = style.createLblP("<html>5. The first player to win 3 rounds straight is declared the winner.</html>", style.black);
            add(lblInstructionFive, gbc);

            gbc.gridy = 9;
            gbc.anchor = GridBagConstraints.CENTER;
            btnJoinGame = style.createBtnRoundedH1("JOIN GAME", style.deepSkyBlue, style.white, 10);
            btnJoinGame.setPreferredSize(new Dimension(300,100));
            add(btnJoinGame, gbc);

            this.setPreferredSize(new Dimension(450,550));
        }
    }

    /**
     * Sets a specified action listener for btnJoinGame.
     * @param actionListener The specified action listener.
     */
    public void setJoinGameListener(ActionListener actionListener) {
        btnJoinGame.addActionListener(actionListener);
    }
}
