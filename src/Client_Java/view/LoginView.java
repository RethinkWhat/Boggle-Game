package Client_Java.view;

import shared.SwingStylesheet;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * The LoginView for the client.
 */
public class LoginView extends JFrame {
    /**
     * The username text field.
     */
    private JTextField txtUsername;
    /**
     * The password text field.
     */
    private JPasswordField txtPassword;
    /**
     * The show password check box.
     */
    private JCheckBox chkShowPassword;
    /**
     * The error message.
     */
    private JLabel lblErrorMessage;
    /**
     * The login button.
     */
    private JButton btnLogin;
    /**
     * The stylesheet for UI elements.
     */
    private final SwingStylesheet style = new SwingStylesheet();

    /**
     * Constructs a frame of LoginView.
     */
    public LoginView() {
        super("Login");

        Container contentArea = new JPanel();
        contentArea.setBackground(style.goldenTainoi);
        contentArea.setLayout(new BorderLayout());

        contentArea.add(new LogoPanel(), BorderLayout.WEST);
        contentArea.add(new FormPanel(), BorderLayout.EAST);

        this.setContentPane(contentArea);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setSize(900, 550);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    /**
     * Holds the login form components.
     */
    class FormPanel extends JPanel {
        /**
         * Constructs a panel of FormPanel.
         */
        public FormPanel() {
            this.setLayout(new BorderLayout());
            this.setBackground(style.goldenTainoi);
            this.setBorder(new EmptyBorder(20, 20, 20, 20));

            JPanel container = style.createPnlRounded(400, 450, style.white, style.goldenTainoi);
            container.setBackground(style.goldenTainoi);
            container.setLayout(new GridBagLayout());
            container.setBorder(new EmptyBorder(10, 55, 10, 55));
            add(container, BorderLayout.CENTER);

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.anchor = GridBagConstraints.CENTER;
            gbc.fill = GridBagConstraints.BOTH;

            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 3;
            gbc.weightx = 300;
            gbc.ipadx = 20;
            gbc.ipady = 80;
            JLabel lblLogin = style.createLblH1("Log In", style.deepSkyBlue);
            lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
            container.add(lblLogin, gbc);

            gbc.gridy = 1;
            gbc.ipady = 10;
            JLabel lblUsername = style.createLblH4("Username", style.deepSkyBlue);
            lblUsername.setHorizontalTextPosition(SwingConstants.LEFT);
            container.add(lblUsername, gbc);

            gbc.gridy = 2;
            gbc.ipady = 10;
            txtUsername = style.createTxtRounded("Username", style.lightGray, style.gray, 20);
            container.add(txtUsername, gbc);

            gbc.gridy = 3;
            gbc.ipady = 10;
            JLabel lblPassword = style.createLblH4("Password", style.deepSkyBlue);
            lblPassword.setHorizontalTextPosition(SwingConstants.LEFT);
            container.add(lblPassword, gbc);

            gbc.gridy = 4;
            gbc.ipady = 10;
            txtPassword = style.createPwdRounded(style.lightGray, style.gray, 20);
            txtPassword.setText("Password");
            txtPassword.setEchoChar((char)0);
            container.add(txtPassword, gbc);

            gbc.gridy = 5;
            gbc.ipady = 10;
            chkShowPassword = new JCheckBox("Show Password");
            chkShowPassword.setBorderPaintedFlat(true);
            chkShowPassword.setBackground(style.white);
            chkShowPassword.setForeground(style.gray);
            chkShowPassword.setFont(new Font("Arial", Font.PLAIN, 12));
            container.add(chkShowPassword, gbc);

            gbc.gridy = 6;
            gbc.ipady = 20;
            lblErrorMessage = style.createLblH4("", style.red);
            lblErrorMessage.setHorizontalAlignment(SwingConstants.CENTER);
            container.add(lblErrorMessage, gbc);

            gbc.gridy = 7;
            gbc.ipady = 10;
            btnLogin = style.createBtnRounded("Log In", style.deepSkyBlue, style.black, 10);
            btnLogin.setPreferredSize(new Dimension(260, 20));
            container.add(btnLogin, gbc);

            gbc.gridy = 8;
            gbc.ipady = 20;
            JLabel lblBr = new JLabel("");
            container.add(lblBr, gbc);

            gbc.gridy = 9;
            FlowLayout flowLayout = new FlowLayout(FlowLayout.CENTER);
            flowLayout.setHgap(100);
            flowLayout.setVgap(0);
            JPanel pnlSignup = new JPanel(flowLayout);
            pnlSignup.setBackground(style.white);
            pnlSignup.setPreferredSize(new Dimension(450, 40));
            container.add(pnlSignup, gbc);

            this.setPreferredSize(new Dimension(450, 550));
        }
    }

    /**
     * Holds the logo and tagline.
     */
    class LogoPanel extends JPanel {
        /**
         * Constructs a panel of LogoPanel.
         */
        public LogoPanel() {
            this.setBackground(style.goldenTainoi);
            this.setLayout(new GridBagLayout());
            this.setBorder(style.padding);

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.anchor = GridBagConstraints.CENTER;
            gbc.fill = GridBagConstraints.CENTER;

            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 1;
            gbc.weightx = 300;
            gbc.ipady = 80;

            JLabel lblLogo = new JLabel(style.iconLogoWhite);
            lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
            lblLogo.setHorizontalTextPosition(SwingConstants.CENTER);
            add(lblLogo, gbc);

            gbc.gridy = 1;
            JLabel lblTagline = style.createLblH3("Test your vocabulary!", style.white);
            lblTagline.setHorizontalAlignment(SwingConstants.CENTER);
            add(lblTagline, gbc);

            this.setPreferredSize(new Dimension(450, 550));
        }
    }

    /**
     * Retrieves the current JTextField of txtUsername.
     * @return The current txtUsername.
     */
    public JTextField getTxtUsername() {
        return txtUsername;
    }

    /**
     * Retrieves the current JPasswordField of txtPassword.
     * @return The current txtPassword.
     */
    public JPasswordField getTxtPassword() {
        return txtPassword;
    }

    /**
     * Retrieves the current JCheckBox of chkShowPassword.
     * @return The current chkShowPassword.
     */
    public JCheckBox getChkShowPassword() {
        return chkShowPassword;
    }

    /**
     * Retrieves the current JLabel of lblErrorMessage.
     * @return The current lblErrorMessage.
     */
    public JLabel getLblErrorMessage() {
        return lblErrorMessage;
    }

    /**
     * Retrieves the current JButton of btnLogin.
     * @return The current btnLogin.
     */
    public JButton getBtnLogin() {
        return btnLogin;
    }

    /**
     * Sets a specified action listener for btnLogin.
     * @param actionListener The specified action listener.
     */
    public void setLoginListener(ActionListener actionListener) {
        btnLogin.addActionListener(actionListener);
    }

    /**
     * Adds a specified action listener to txtPassword.
     * @param actionListener The specified action listener.
     */
    public void setPasswordListener(ActionListener actionListener) {
        txtPassword.addActionListener(actionListener);
    }

    /**
     * Sets a specified action listener for chkPassword.
     * @param actionListener The specified action listener.
     */
    public void setShowPasswordListener(ActionListener actionListener) {
        chkShowPassword.addActionListener(actionListener);
    }

    /**
     * Sets a new error message for lblErrorMessage.
     * @param text The new error message.
     */
    public void setErrorMessage(String text) {
        SwingUtilities.invokeLater(() ->lblErrorMessage.setText(text));
    }
}
