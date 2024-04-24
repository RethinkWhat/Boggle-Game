package client.view.subpages;

import shared.SwingStylesheet;

import javax.swing.*;
import java.awt.*;

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
        this.setBackground(style.deepSkyBlue);
        this.setLayout(new BorderLayout());

        this.setPreferredSize(new Dimension(1300,750));
    }
}
