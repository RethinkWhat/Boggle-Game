package server.view.subpages;

import shared.SwingStylesheet;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Holds the server status.
 */
public class ServerStatusView extends JPanel {
    /**
     * The button of switching the server on or off.
     */
    private JButton btnServerSwitch;
    /**
     * The label of server status.
     */
    private JLabel lblServerPrompt;
    /**
     * The label of server status information.
     */
    private JLabel lblServerStatus;
    /**
     * Instance variable of GridBagConstraints used for JPanels using GridBagLayout.
     */
    private GridBagConstraints gbc;
    /**
     * The stylesheet.
     */
    private SwingStylesheet style = new SwingStylesheet();

    /**
     * Constructs a panel of ServerStatusView.
     */
    public ServerStatusView() {
        setLayout(new BorderLayout());
        setBackground(style.lightGray);
        setBorder(new EmptyBorder(25, 25, 25, 25));

        MainPanel pnlMain = new MainPanel();
        add(pnlMain, BorderLayout.CENTER);

        this.setPreferredSize(new Dimension(1300, 750));
        this.setVisible(true);
    }

    /**
     * The panel that contains the buttons.
     */
    public class MainPanel extends JPanel {
        /**
         * Construct a panel of MainPanel.
         */
        public MainPanel() {
            setLayout(new BorderLayout());

            JPanel container = style.createPnlRounded(1300, 700, style.white, style.lightGray);
            container.setLayout(new GridBagLayout());
            add(container, BorderLayout.CENTER);

            gbc = new GridBagConstraints();


            ImageIcon serverImageIcon = new ImageIcon("image-logo-server.png");
            JLabel lblPhoto = new JLabel(serverImageIcon);
            gbc.gridy = 0;
            container.add(lblPhoto, gbc);

            gbc.gridy = 1;
            lblServerPrompt = style.createLblH1("SERVER STATUS", style.goldenTainoi);
            lblServerPrompt.setHorizontalAlignment(SwingConstants.CENTER);
            container.add(lblServerPrompt, gbc);

            //To be dynamically changed in the controller
            gbc.gridy = 2;
            lblServerStatus = style.createLblH1("OFFLINE", style.red);
            lblServerStatus.setHorizontalAlignment(SwingConstants.CENTER);
            container.add(lblServerStatus, gbc);

            gbc.gridy = 4;
            gbc.ipady = 10;
            btnServerSwitch = style.createBtnRounded("Start Server", style.white, style.deepSkyBlue, 15);
            btnServerSwitch.setPreferredSize(new Dimension(200, 50));
            container.add(btnServerSwitch, gbc);

            setPreferredSize(new Dimension(1300, 700));
        }
    }

    /**
     * Retrieves the current JButton of btnServerSwitch.
     *
     * @return The current btnServerSwitch.
     */
    public JButton getServerSwitch() {
        return btnServerSwitch;
    }

    /**
     * Retrieves the current JLabel of lblServerPrompt.
     *
     * @return The current lblServerPrompt.
     */
    public JLabel getServerPrompt() {
        return lblServerPrompt;
    }

    /**
     * Retrieves the current JLabel of lblServerStatus.
     *
     * @return The current lblServerStatus.
     */
    public JLabel getServerStatus() {
        return lblServerStatus;
    }

    /**
     * Sets a specified action listener for btnServerSwitch.
     *
     * @param actionListener The specified action listener.
     */
    public void setServerListener(ActionListener actionListener) {
        btnServerSwitch.addActionListener(actionListener);
    }

    /**
     * Changes the labels when the server is online.
     */
    public void setOnline() {
        lblServerStatus.setText("ONLINE");
        lblServerStatus.setForeground(style.deepSkyBlue);

        btnServerSwitch.setText("Terminate Server");
        btnServerSwitch.setBackground(style.white);
        btnServerSwitch.setForeground(style.red);
    }

    /**
     * Changes the labels when the server is offline.
     */
    public void setOffline() {
        lblServerStatus.setText("OFFLINE");
        lblServerStatus.setForeground(style.red);
        btnServerSwitch.setText("Start Server");
        btnServerSwitch.setBackground(style.white);
        btnServerSwitch.setForeground(style.deepSkyBlue);
    }
}

