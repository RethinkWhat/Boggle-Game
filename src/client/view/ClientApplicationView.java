package client.view;

import shared.SwingStylesheet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * The container for the client application view and its subviews.
 */
public class ClientApplicationView extends JFrame {
    /**
     * The label showing the current page of the application.
     */
    private JLabel lblNavLocation;
    /**
     * The home navigation button.
     */
    private JButton btnNavHome;
    /**
     * The settings navigation button.
     */
    private JButton btnNavSettings;
    /**
     * The logout navigation button.
     */
    private JButton btnNavLogout;
    /**
     * The panel holding other view components.
     */
    private JPanel pnlCards;
    /**
     * The card layout that controls other components.
     */
    private CardLayout cardLayout;
    /**
     * The stylesheet.
     */
    private SwingStylesheet style = new SwingStylesheet();

    /**
     * Constructs a frame of ClientApplication View.
     */
    public ClientApplicationView() {
        super("Word Factory");

        Container contentArea = new JPanel(new BorderLayout());
        contentArea.setBackground(style.white);

        contentArea.add(new HeaderPanel(), BorderLayout.NORTH);

        pnlCards = new JPanel(cardLayout = new CardLayout(0,0));
        contentArea.add(pnlCards, BorderLayout.CENTER);

        this.setContentPane(contentArea);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setSize(1300,800);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    /**
     * Holds the navigation elements.
     */
    class HeaderPanel extends JPanel {
        /**
         * Constructs a panel of HeaderPanel.
         */
        public HeaderPanel() {
            this.setBackground(style.goldenTainoi);

            JPanel container = new JPanel(new GridLayout(0,2,770,0));
            container.setBackground(style.goldenTainoi);
            container.setBorder(style.padding);
            add(container);

            JPanel pnlLeft = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
            pnlLeft.setBackground(style.goldenTainoi);
            container.add(pnlLeft);

            lblNavLocation = style.createLblH3("Home", style.white);
            lblNavLocation.setIcon(style.iconLogoWhiteScaled);
            lblNavLocation.setVerticalTextPosition(SwingConstants.BOTTOM);
            pnlLeft.add(lblNavLocation);

            JPanel pnlButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
            pnlButtons.setBackground(style.goldenTainoi);
            container.add(pnlButtons);

            btnNavHome = style.createBtnIconOnly(style.iconHome, 22,22);
            pnlButtons.add(btnNavHome);

            btnNavSettings = style.createBtnIconOnly(style.iconSettings, 22, 22);
            pnlButtons.add(btnNavSettings);

            btnNavLogout = style.createBtnIconOnly(style.iconLogout, 20,20);
            pnlButtons.add(btnNavLogout);

            container.setPreferredSize(new Dimension(1300,50));
        }
    }

    /**
     * Retrieves the current JLabel of lblNavLocation.
     * @return The current lblNavLocation.
     */
    public JLabel getLblNavLocation() {
        return lblNavLocation;
    }

    /**
     * Retrieves the current JButton of btnNavHome.
     * @return The current btnNavHome.
     */
    public JButton getBtnNavHome() {
        return btnNavHome;
    }

    /**
     * Retrieves the current JButton of btnNavSettings.
     * @return The current btnNavSettings.
     */
    public JButton getBtnNavSettings() {
        return btnNavSettings;
    }

    /**
     * Retrieves the current JButton of btnNavLogout.
     * @return The current btnNavLogout.
     */
    public JButton getBtnNavLogout() {
        return btnNavLogout;
    }

    /**
     * Retrieves the current JPanel of pnlCards.
     * @return The current pnlCards.
     */
    public JPanel getPnlCards() {
        return pnlCards;
    }

    /**
     * Retrieves the current CardLayout of cardLayout.
     * @return The current cardLayout.
     */
    public CardLayout getCardLayout() {
        return cardLayout;
    }

    /**
     * Sets a specified action listener for btnNavHome.
     * @param actionListener The specified action listener.
     */
    public void setHomeListener(ActionListener actionListener) {
        btnNavHome.addActionListener(actionListener);
    }

    /**
     * Sets a specified action listener for btnNavSettings.
     * @param actionListener The specified action listener.
     */
    public void setSettingsListener(ActionListener actionListener) {
        btnNavSettings.addActionListener(actionListener);
    }

    /**
     * Sets a specified action listener for btnNavLogout.
     * @param actionListener The specified action listener.
     */
    public void setLogoutListener(ActionListener actionListener) {
        btnNavLogout.addActionListener(actionListener);
    }

    /**
     * Sets a specified text for lblNavLocation.
     * @param text The specified text.
     */
    public void setLocationText(String text) {
        lblNavLocation.setText(text);
    }

    public static void main(String[] args) {
        new ClientApplicationView();
    }
}
