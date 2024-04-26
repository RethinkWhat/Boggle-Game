package server.view.subpages;

import shared.SwingStylesheet;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class ServerStatusView extends JPanel {
    private JButton btnServerSwitch;
    private JLabel lblServerPrompt;
    private JLabel lblServerStatus;
    private GridBagConstraints gbc;
    private SwingStylesheet style = new SwingStylesheet();
    private JLabel lblLogoAnimated;
    // Timer for animating the logo
    private Timer logoTimer;

    public ServerStatusView() {
        setLayout(new BorderLayout());
        setBackground(style.deepSkyBlue);
        setBorder(new EmptyBorder(25, 25, 25, 25));

        MainPanel pnlMain = new MainPanel();
        add(pnlMain, BorderLayout.CENTER);

        this.setPreferredSize(new Dimension(1300, 750));
        this.setVisible(true);

    }

    public class MainPanel extends JPanel {
        public MainPanel() {
            setLayout(new BorderLayout());

            JPanel container = new JPanel();
            container.setBackground(style.deepSkyBlue);
            container.setLayout(new GridBagLayout());
            add(container, BorderLayout.CENTER);

            gbc = new GridBagConstraints();

            lblLogoAnimated = new JLabel(style.iconLogoSteady);
            gbc.gridy = 0;
            container.add(lblLogoAnimated, gbc);

            gbc.gridy = 1;
            lblServerStatus = style.createLblStatus("OFFLINE", style.red);
            lblServerStatus.setHorizontalAlignment(SwingConstants.CENTER);
            container.add(lblServerStatus, gbc);

            gbc.gridy = 3;
            gbc.ipady = 10;
            btnServerSwitch = style.createBtnRounded("Start Server", style.goldenTainoi, style.white, 15);
            btnServerSwitch.setPreferredSize(new Dimension(200, 50));
            container.add(btnServerSwitch, gbc);

            setPreferredSize(new Dimension(1300, 700));
        }
    }

    public JButton getServerSwitch() {
        return btnServerSwitch;
    }

    public JLabel getServerPrompt() {
        return lblServerPrompt;
    }

    public JLabel getServerStatus() {
        return lblServerStatus;
    }

    public void setServerListener(ActionListener actionListener) {
        btnServerSwitch.addActionListener(actionListener);
    }

    public void setOnline() {
        lblServerStatus.setText("ONLINE");
        lblServerStatus.setForeground(style.goldenTainoi);

        btnServerSwitch.setText("Terminate Server");
        btnServerSwitch.setBackground(style.red);
        btnServerSwitch.setForeground(style.white);

        // Start the logo animation (restart from the first frame)
        lblLogoAnimated.setIcon(style.iconLogoAnimated);
    }

    public void setOffline() {
        lblServerStatus.setText("OFFLINE");
        lblServerStatus.setForeground(style.red);
        btnServerSwitch.setText("Start Server");
        btnServerSwitch.setBackground(style.goldenTainoi);
        btnServerSwitch.setForeground(style.white);

        // Stop the logo animation
        lblLogoAnimated.setIcon(style.iconLogoAnimated);
    }
}
