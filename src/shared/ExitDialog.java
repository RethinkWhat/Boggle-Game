package shared;

import javax.swing.*;
import java.awt.*;

/**
 * A customizable message dialog for exiting the game.
 */
public class ExitDialog extends JDialog {
    private String title;
    private ImageIcon icon;
    private String titleMessage;
    private String message;
    private String buttonText;
    private Color buttonColor;
    private Color iconColor;
    private Color textColor;
    private Color titleMessageColor;
    private JButton btnDialog;
    private JButton btnCancel;
    private JLabel iconLabel;
    private JLabel lblSmall;
    private JLabel lblBig;
    private SwingStylesheet style = new SwingStylesheet();

    /**
     * Constructs a ExitDialog with customizable content.
     *
     * @param title             The title of the dialog.
     * @param icon              The icon to display in the dialog.
     * @param titleMessage      The title message to display in the dialog.
     * @param message           The message to display in the dialog.
     * @param buttonText        The text for the button in the dialog.
     * @param buttonColor       The background color of the button.
     * @param iconColor         The color of the icon.
     * @param textColor         The color of the text in the dialog.
     * @param titleMessageColor The color of the title message.
     */
    public ExitDialog(String title, ImageIcon icon, String titleMessage, String message, String buttonText, Color buttonColor, Color iconColor, Color textColor, Color titleMessageColor) {
        this.title = title;
        this.icon = icon;
        this.titleMessage = titleMessage;
        this.message = message;
        this.buttonText = buttonText;
        this.buttonColor = buttonColor;
        this.iconColor = iconColor;
        this.textColor = textColor;
        this.titleMessageColor = titleMessageColor;

        createDialog();
    }

    private void createDialog() {
        JFrame mainFrame = new JFrame();
        JDialog dialog = new JDialog(mainFrame, title, true);
        dialog.setTitle(title);
        dialog.setLayout(new GridLayout(3, 1));
        dialog.setSize(500, 300);

        // Create pnlIcon panel
        JPanel pnlIcon = new JPanel();
        pnlIcon.setLayout(new BorderLayout());
        pnlIcon.setPreferredSize(new Dimension(600, 200));

        iconLabel = new JLabel(icon);
        iconLabel.setForeground(iconColor);
        pnlIcon.add(iconLabel, BorderLayout.CENTER);

        // Create pnlMessage panel
        JPanel pnlMessage = new JPanel(new GridBagLayout());
        pnlMessage.setPreferredSize(new Dimension(600, 170));
        lblBig = style.createLblH1(titleMessage, textColor);
        lblSmall = style.createLblP(message, textColor);
        lblBig.setForeground(titleMessageColor);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        pnlMessage.add(lblBig, gbc);

        gbc.gridy = 1;
        pnlMessage.add(lblSmall, gbc);

        // Create pnlButton panel
        JPanel pnlButton = new JPanel(new FlowLayout());
        pnlButton.setPreferredSize(new Dimension(600, 30));
        btnDialog = style.createBtnRounded(buttonText, buttonColor, textColor, 10);

        // Exiting the dialog
        btnDialog.addActionListener(e -> {
            System.exit(0);
        });
        pnlButton.add(btnDialog);

        btnCancel = style.createBtnRounded("CANCEL", style.deepSkyBlue, style.white, 10);
        btnCancel.addActionListener(e -> {
            dialog.dispose();
            dialog.setVisible(false);
        });
        pnlButton.add(btnCancel);

        // Add panels to the dialog
        dialog.add(pnlIcon);
        dialog.add(pnlMessage);
        dialog.add(pnlButton);

        dialog.setLocationRelativeTo(null);
        dialog.setResizable(false);
        dialog.setVisible(true);
    }

    /**
     * Sets the title of the dialog.
     *
     * @param title The title to set.
     */
    public void setDialogTitle(String title) {
        this.title = title;
        setTitle(title);
    }

    /**
     * Sets the icon of the dialog.
     *
     * @param icon The icon to set.
     */
    public void setDialogIcon(ImageIcon icon) {
        this.icon = icon;
        iconLabel.setIcon(icon);
    }

    /**
     * Sets the message of the dialog.
     *
     * @param message The message to set.
     */
    public void setDialogMessage(String message) {
        this.message = message;
        lblSmall.setText(message);
    }

    /**
     * Sets the text of the button in the dialog.
     *
     * @param buttonText The text to set.
     */
    public void setDialogButtonText(String buttonText) {
        this.buttonText = buttonText;
        btnDialog.setText(buttonText);
    }

    /**
     * Sets the background color of the button in the dialog.
     *
     * @param buttonColor The color to set.
     */
    public void setDialogButtonColor(Color buttonColor) {
        this.buttonColor = buttonColor;
        btnDialog.setBackground(buttonColor);
    }

    /**
     * Sets the foreground color of the icon in the dialog.
     *
     * @param iconColor The color to set.
     */
    public void setDialogIconColor(Color iconColor) {
        this.iconColor = iconColor;
        iconLabel.setForeground(iconColor);
    }

    /**
     * Sets the foreground color of the text in the dialog.
     *
     * @param textColor The color to set.
     */
    public void setDialogTextColor(Color textColor) {
        this.textColor = textColor;
        lblBig.setForeground(textColor);
        lblSmall.setForeground(textColor);
    }

    /**
     * Sets the color of the title message in the dialog.
     *
     * @param titleMessageColor The color to set.
     */
    public void setDialogTitleMessageColor(Color titleMessageColor) {
        this.titleMessageColor = titleMessageColor;
        lblBig.setForeground(titleMessageColor);
    }
}


