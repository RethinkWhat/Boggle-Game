package shared;

import javax.swing.*;
import java.awt.*;

/**
 * A dialog for selecting avatar images.
 */
public class AvatarDialog extends JDialog {

    /**
     * The panel for the title.
     */
    private JPanel pnlTitle;
    /**
     * The panel for the avatar images.
     */
    private JPanel pnlAvatar;
    /**
     * The panel for the row 1 of avatar images.
     */
    private JPanel pnlAvatarRow1;
    /**
     * The panel for the row 2 of avatar images.
     */
    private JPanel pnlAvatarRow2;
    /**
     * The panel for the confirm button.
     */
    private JPanel pnlButton;
    /**
     * The JLabel for the title.
     */
    private JLabel lblAvatar;
    /**
     * The button for the avatar male 1.
     */
    private JButton btnAvatar1;
    /**
     * The button for the avatar male 2.
     */
    private JButton btnAvatar2;
    /**
     * The button for the avatar woman 1.
     */
    private JButton btnAvatar3;
    /**
     * The button for the avatar woman 2.
     */
    private JButton btnAvatar4;
    /**
     * The confirm button.
     */
    private JButton btnConfirm;
    /**
     * The stylesheet.
     */
    private SwingStylesheet style = new SwingStylesheet();

    public AvatarDialog() {
        setBackground(style.deepSkyBlue);
        setLayout(new BorderLayout());

        // Panel for title
        pnlTitle = new JPanel();
        pnlTitle.setPreferredSize(new Dimension(500, 70));
        pnlTitle.setBackground(style.white);
        add(pnlTitle, BorderLayout.NORTH);

        lblAvatar = style.createLblH5("AVATARS", style.deepSkyBlue);
        pnlTitle.add(lblAvatar);

        // Panel for avatar buttons
        pnlAvatar = new JPanel(new GridLayout(2, 1));
        pnlAvatar.setBackground(style.white);
        pnlAvatar.setPreferredSize(new Dimension(400, 400));
        add(pnlAvatar, BorderLayout.CENTER);

        // Panel for avatar buttons row 1
        pnlAvatarRow1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        pnlAvatarRow1.setBackground(style.white);
        pnlAvatar.add(pnlAvatarRow1);

        btnAvatar1 = style.createBtnIconOnly(style.iconPfpMale1, 180, 180);
        pnlAvatarRow1.add(btnAvatar1);
        btnAvatar2 = style.createBtnIconOnly(style.iconPfpMale2, 180, 180);
        pnlAvatarRow1.add(btnAvatar2);

        // Panel for avatar buttons row 2
        pnlAvatarRow2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        pnlAvatarRow2.setBackground(style.white);
        pnlAvatar.add(pnlAvatarRow2);

        btnAvatar3 = style.createBtnIconOnly(style.iconPfpWoman1, 180, 180);
        pnlAvatarRow2.add(btnAvatar3);
        btnAvatar4 = style.createBtnIconOnly(style.iconPfpWoman2, 180, 180);
        pnlAvatarRow2.add(btnAvatar4);

        // Panel for buttons
        pnlButton = new JPanel();
        pnlButton.setBackground(style.white);
        pnlButton.setPreferredSize(new Dimension(500, 90));
        add(pnlButton, BorderLayout.SOUTH);

        btnConfirm = style.createBtnRoundedH2("CONFIRM", style.deepSkyBlue, style.white, 10);
        btnConfirm.setPreferredSize(new Dimension(170,70));
        pnlButton.add(btnConfirm, BorderLayout.CENTER);

        setPreferredSize(new Dimension(500, 600));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        pack();
        setVisible(true);
    }

    // FOR TESTING
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AvatarDialog dialog = new AvatarDialog();
            dialog.pack();
            dialog.setVisible(true);
        });
    }
}
