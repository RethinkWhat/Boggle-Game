package Client_Java.view.subpages;

import Client_Java.view.prompts.AvatarChangedSuccessView;
import shared.SwingStylesheet;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class AvatarSelectionView extends JFrame {
    private JButton avatar1; // the avatar 1 button
    private JButton avatar2; // the avatar 2 button
    private JButton avatar3; // the avatar 3 button
    private JButton avatar4; // the avatar 4 button
    private JButton btnConfirm; // the confirm button
    private SwingStylesheet style = new SwingStylesheet(); // the stylesheet
    private JButton[] avatarButtons; // the avatar buttons
    private AvatarChangedSuccessView avatarChangedSuccessView; // the avatar changed success prompt view
    private HomeView homeView; // the home view
    private SettingsView settingsView; // the settings view

    public AvatarSelectionView(HomeView homeView, SettingsView settingsView) {
        this.homeView = homeView;
        this.settingsView = settingsView;
        avatarButtons = new JButton[4];

            JFrame frame = new JFrame("Avatar Selection");
            frame.setSize(412, 505);
            frame.setResizable(false);
            frame.setLocationRelativeTo(null);
            frame.setUndecorated(true);

            JPanel contentPane = new JPanel(new BorderLayout()) {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Dimension arcs = new Dimension(20, 20);
                    int width = getWidth();
                    int height = getHeight();
                    Graphics2D graphics = (Graphics2D) g;
                    graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    graphics.setColor(getBackground());
                    graphics.fillRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);
                    graphics.setColor(getForeground());
                    graphics.drawRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);
                }
            };
            contentPane.setBackground(Color.WHITE);
            contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
            frame.setContentPane(contentPane);

            JPanel titlePanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Dimension arcs = new Dimension(20, 20);
                    int width = getWidth();
                    int height = getHeight();
                    Graphics2D graphics = (Graphics2D) g;
                    graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    graphics.setColor(style.deepSkyBlue);
                    graphics.fillRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);
                    graphics.setColor(getForeground());
                    graphics.drawRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);
                }
            };
            titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER));

            JLabel titleLabel = style.createLblH1("AVATARS", style.white);
            titlePanel.add(titleLabel);

            contentPane.add(titlePanel, BorderLayout.NORTH);

            JPanel avatarPanel = new JPanel();
            avatarPanel.setLayout(new GridLayout(2, 2));

            avatar1 = style.createBtnRoundedH1(null, style.white, null, 10);
            avatar1.setIcon(new ImageIcon("res/drawable/images/pfp-male-1.png"));
            avatar1.setBorder(null);
            avatar1.setFocusable(false);

            avatar2 = style.createBtnRoundedH1(null, style.white, null, 10);
            avatar2.setIcon(new ImageIcon("res/drawable/images/pfp-man-2.png"));
            avatar2.setBorder(null);
            avatar2.setFocusable(false);

            avatar3 = style.createBtnRoundedH1(null, style.white, null, 10);
            avatar3.setIcon(new ImageIcon("res/drawable/images/pfp-woman-1.png"));
            avatar3.setBorder(null);
            avatar3.setFocusable(false);

            avatar4 = style.createBtnRoundedH1(null, style.white, null, 10);
            avatar4.setIcon(new ImageIcon("res/drawable/images/pfp-woman-2.png"));
            avatar4.setBorder(null);
            avatar4.setFocusable(false);

            avatarButtons[0] = avatar1;
            avatarButtons[1] = avatar2;
            avatarButtons[2] = avatar3;
            avatarButtons[3] = avatar4;

            avatarPanel.add(avatar1);
            avatarPanel.add(avatar2);
            avatarPanel.add(avatar3);
            avatarPanel.add(avatar4);

            JPanel buttonPanel = new JPanel();
            buttonPanel.setBackground(style.white);
            buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

            btnConfirm = style.createBtnRounded("CONFIRM", style.deepSkyBlue, style.white, 10);
            btnConfirm.setPreferredSize(new Dimension(200, 40));
            btnConfirm.setFocusable(false);
            buttonPanel.add(btnConfirm);

            contentPane.add(avatarPanel, BorderLayout.CENTER);
            contentPane.add(buttonPanel, BorderLayout.SOUTH);

            frame.setVisible(true);
    }

    public void setSelectionListener(ActionListener actionListener, JButton btnSelection) {
        btnSelection.addActionListener(actionListener);
    }

    public void setConfirmListener(ActionListener actionListener) {
        btnConfirm.addActionListener(actionListener);
    }

    public JButton getAvatar1() {
        return avatarButtons[0];
    }

    public JButton getAvatar2() {
        return avatar2;
    }

    public JButton getAvatar3() {
        return avatar3;
    }

    public JButton getAvatar4() {
        return avatar4;
    }

    public JButton getBtnConfirm() {
        return btnConfirm;
    }

    public JButton[] getAvatarButtons() {
        return avatarButtons;
    }

    public SettingsView getSettingsView() {
        return settingsView;
    }

    public HomeView getHomeView() {
        return homeView;
    }

}