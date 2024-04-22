package server.view;

import shared.SwingStylesheet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SecuritySettingsView extends JFrame{
    private JLabel lblNavLocation; // the label showing the current page of the application
    private JButton btnNavHome; // the home navigation button
    private JButton btnNavSettings; // the settings navigation button
    private JButton btnNavLogout; // the logout navigation button
    private JPanel pnlCards; // the panel holding other view components
    private CardLayout cardLayout; // the card layout that controls other components
    private SwingStylesheet style = new SwingStylesheet(); // the stylesheet


    // constructs a frame of SecuritySettingsView
    public SecuritySettingsView() {
        super("Word Factory");

        Container contentArea = new JPanel(new BorderLayout());
        contentArea.setBackground(style.white);

        contentArea.add(new server.view.SecuritySettingsView.HeaderPanel(), BorderLayout.NORTH);

        contentArea.add(new server.view.SecuritySettingsView.LeftPanel(), BorderLayout.WEST);

        pnlCards = new JPanel(cardLayout = new CardLayout(0,0));
        contentArea.add(pnlCards, BorderLayout.CENTER);

        /**
         * Placeholder for calling a future view for the security settingsView
         *  //pnlCards.add(new DefaultGameSettingsView(), "defaultSettings");
         *  //cardLayout.show(pnlCards, "defaultSettings");
         */

        this.setContentPane(contentArea);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setSize(1300,800);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    class HeaderPanel extends JPanel {
        // constructs a panel of HeaderPanel
        public HeaderPanel() {
            this.setBackground(style.goldenTainoi);

            JPanel container = new JPanel(new GridLayout(0,2,770,0));
            container.setBackground(style.goldenTainoi);
            container.setBorder(style.padding);
            add(container);

            JPanel pnlLeft = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
            pnlLeft.setBackground(style.goldenTainoi);
            container.add(pnlLeft);

            lblNavLocation = style.createLblH3("Settings", style.white);
            lblNavLocation.setIcon(style.iconLogoWhiteScaled);
            lblNavLocation.setVerticalTextPosition(SwingConstants.BOTTOM);
            pnlLeft.add(lblNavLocation);

            JPanel pnlButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
            pnlButtons.setBackground(style.goldenTainoi);
            container.add(pnlButtons);

            btnNavHome = style.createBtnIconOnly(style.iconHome, 20,20);
            pnlButtons.add(btnNavHome);

            btnNavSettings = style.createBtnIconOnly(style.iconSettings, 20,20);
            pnlButtons.add(btnNavSettings);

            btnNavLogout = style.createBtnIconOnly(style.iconLogout, 20,20);
            pnlButtons.add(btnNavLogout);

            container.setPreferredSize(new Dimension(1300,50));
        }
    }

    class LeftPanel extends JPanel{
        // constructs a panel of LeftPanel
        public LeftPanel(){
            this.setBackground(style.mustard);

            JPanel container = new JPanel(new GridLayout(0,2,180,0));
            container.setBackground(style.mustard);
            container.setBorder(style.padding);
            add(container);
        }
    }

    // retrieves the current JLabel of lblNavLocation
    public JLabel getLblNavLocation() {
        return lblNavLocation;
    }

    // retrieves the current JButton of btnNavHome
    public JButton getBtnNavHome() {
        return btnNavHome;
    }

    // retrieves the current JButton of btnNavSettings
    public JButton getBtnNavSettings() {
        return btnNavSettings;
    }

    // retrieves the current JButton of btnNavLogout
    public JButton getBtnNavLogout() {
        return btnNavLogout;
    }

    // retrieves the current JPanel of pnlCards
    public JPanel getPnlCards() {
        return pnlCards;
    }

    // retrieves the current CardLayout of cardLayout
    public CardLayout getCardLayout() {
        return cardLayout;
    }

    // sets a specified action listener for btnNavHome
    public void setHomeListener(ActionListener actionListener) {
        btnNavHome.addActionListener(actionListener);
    }

    // sets a specified action listener for btnNavSettings
    public void setSettingsListener(ActionListener actionListener) {
        btnNavSettings.addActionListener(actionListener);
    }

    // sets a specified action listener for btnNavLogout
    public void setLogoutListener(ActionListener actionListener) {
        btnNavLogout.addActionListener(actionListener);
    }

    // sets a specified text for lblNavLocation
    public void setLocationText(String text) {
        lblNavLocation.setText(text);
    }

    public static void main(String[] args) {
        new server.view.SecuritySettingsView();
    }
}