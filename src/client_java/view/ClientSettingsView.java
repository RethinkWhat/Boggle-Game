package client_java.view;

import client_java.view.subpages.SettingsView;
import shared.SwingStylesheet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ClientSettingsView extends JFrame{
    private JLabel lblNavLocation; // the label showing the current page of the application
    private JButton btnNavHome; // the home navigation button
    private JButton btnNavSettings; // the settings navigation button
    private JButton btnNavLogout; // the logout navigation button
    private JButton leftPnlButtons; // the left panel's buttons with labels
    private JButton btnAccMa; // the manage account button
    private JButton btnDelAcc; // the delete account button
    private JButton btnMusic; // the music toggle button
    private JPanel pnlCards; // the panel holding other view components
    private CardLayout cardLayout; // the card layout that controls other components
    private SwingStylesheet style = new SwingStylesheet(); // the stylesheet


    // constructs a frame of ClientSettingsView
    public ClientSettingsView() {
        super("Word Factory");

        Container contentArea = new JPanel(new BorderLayout());
        contentArea.setBackground(style.white);

        contentArea.add(new ClientSettingsView.HeaderPanel(), BorderLayout.NORTH);

        contentArea.add(new ClientSettingsView.LeftPanel(), BorderLayout.WEST);

        pnlCards = new JPanel(cardLayout = new CardLayout(0,0));
        pnlCards.setBackground(style.deepSkyBlue);
        contentArea.add(pnlCards, BorderLayout.CENTER);

        pnlCards.add(new SettingsView(), "settings");
        cardLayout.show(pnlCards, "settings");

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

    class LeftPanel extends JPanel {
        // constructs a panel of LeftPanel
        public LeftPanel() {
            this.setBackground(style.mustard);

            JPanel container = new JPanel();
            container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
            container.setBackground(style.mustard);
            container.setBorder(style.padding);
            add(container);

            btnAccMa = navButtonWithLabel(container, style.iconAccMan, "Account Management");
            btnDelAcc = navButtonWithLabel(container, style.iconAccMan, "Delete Account");
            btnMusic = navButtonWithLabel(container, style.iconMusic, "Music:");

            container.setPreferredSize(new Dimension(250, 300));

            container.setBorder(BorderFactory.createEmptyBorder(0, -50, 0, 0));
        }

        private JButton navButtonWithLabel(JPanel container, Icon icon, String labelText) {
            JPanel panel = new JPanel(new GridLayout(1, 2, -50, 0));
            panel.setBackground(style.mustard);

            leftPnlButtons = style.createBtnIconOnly((ImageIcon) icon, 30, 30);
            leftPnlButtons.setBorder(BorderFactory.createEmptyBorder(20, -10, 20, -10));

            JLabel label = new JLabel(labelText);
            label.setFont(new Font("Arial", Font.BOLD, 16));
            label.setForeground(style.white);
            label.setBorder(BorderFactory.createEmptyBorder(20, 1, 20, -10));

            panel.add(leftPnlButtons);
            panel.add(label);

            container.add(panel);
            return null;
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

    //retrieves the current JButton of btnAccMa
    public JButton getBtnAccMa(){
        return btnAccMa;
    }

    //retrieves the current JButton of btnAccMa
    public JButton getBtnDelAcc(){
        return btnDelAcc;
    }

    //retrieves the current JButton of btnAccMa
    public JButton getBtnMusic(){
        return btnMusic;
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

    // sets a specified action listener for btnAccMa
    public void setAccountManagementListener(ActionListener actionListener) {
        btnAccMa.addActionListener(actionListener);
    }

    // sets a specified action listener for btnDelAcc
    public void setDeleteAccountListener(ActionListener actionListener) {
        btnDelAcc.addActionListener(actionListener);
    }

    // sets a specified action listener for btnMusic
    public void setMusicListener(ActionListener actionListener) {
        btnMusic.addActionListener(actionListener);
    }

    // sets a specified text for lblNavLocation
    public void setLocationText(String text) {
        lblNavLocation.setText(text);
    }

    public static void main(String[] args) {
        new ClientSettingsView();
    }
}