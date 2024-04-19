package client_java.view.subelements;

import shared.SwingStylesheet;

import javax.swing.*;
import java.awt.*;

/**
 * Holds the player's profile picture and username.
 */
public class PlayerPanel extends JPanel {
    /**
     * The profile picture of the player.
     */
    private JLabel lblPfp;
    /**
     * The username of the player.
     */
    private JLabel lblUsername;
    /**
     * The width of the panel.
     */
    private int width;
    /**
     * The height of the panel.
     */
    private int height;
    /**
     * The stylesheet.
     */
    private SwingStylesheet style = new SwingStylesheet();

    /**
     * Constructs a panel of PlayerPanel.
     */
    public PlayerPanel(int width, int height, String username) {
        this.setBackground(style.black);
        this.setLayout(new BorderLayout());

        add(new PfpPanel(style.iconSuccess), BorderLayout.CENTER);

        lblUsername = style.createLblP2(username, style.black);
        lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblUsername, BorderLayout.SOUTH);

        this.setPreferredSize(new Dimension(width, height));
    }

    /**
     * Sets a new pro
     * @param lblPfp
     */
    public void setLblPfp(JLabel lblPfp) {
        this.lblPfp = lblPfp;
    }

    public void setLblUsername(JLabel lblUsername) {
        this.lblUsername = lblUsername;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public JLabel getLblPfp() {
        return lblPfp;
    }

    public JLabel getLblUsername() {
        return lblUsername;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    /**
     * Holds the profile picture.
     */
    class PfpPanel extends JPanel {
        /**
         * Constructs a panel of PfpPanel.
         */
        public PfpPanel(ImageIcon image) {
            this.setBackground(style.gray);
            this.setLayout(new BorderLayout());

            lblPfp = new JLabel();
            lblPfp.setIcon(image);
            add(lblPfp, BorderLayout.CENTER);

            this.setPreferredSize(new Dimension(100,100));
        }

        /**
         * Makes the object circular.
         * @param g the <code>Graphics</code> object to protect
         */
        @Override
        protected void paintComponent(Graphics g) {
            g.drawOval(0, 0, g.getClipBounds().width, g.getClipBounds().height);
        }
    }



}
