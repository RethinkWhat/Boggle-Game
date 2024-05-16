package Client_Java.view;

import shared.SwingStylesheet;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * The RegisterView for the client.
 */
public class RegisterView extends JFrame {
    /**
     * The stylesheet.
     */
    private SwingStylesheet style = new SwingStylesheet();
    /**
     * Username input field.
     */
    private JTextField txtUsername;
    /**
     * Email input field.
     */
    private JTextField txtName;
    /**
     * Password input field.
     */
    private JPasswordField txtPassword;
    /**
     * Confirm password input field.
     */
    private JPasswordField txtConfirmPassword;
    /**
     * Show password check box.
     */
    private JCheckBox chkShowPassword;
    /**
     * Show confirm password check box.
     */
    private JCheckBox chkShowConfirmPassword;
    /**
     * Signup button.
     */
    private JButton btnSignup;
    /**
     * Login button.
     */
    private JButton btnLogin;
    /**
     * Error message.
     */
    private JLabel lblErrorMessage;

    /**
     * Constructs a RegisterView frame.
     */
    public RegisterView() {
        super("Sign Up");

        Container contentArea = new JPanel(new BorderLayout());
        contentArea.setBackground(style.goldenTainoi);

        contentArea.add(new SignupForm(), BorderLayout.CENTER);

        this.setContentPane(contentArea);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setSize(600,700);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    /**
     * Panel that contains signup I/O components.
     */
    class SignupForm extends JPanel {
        /**
         * Constructs a panel of SignupPanel.
         */
        public SignupForm() {
            this.setBackground(style.goldenTainoi);
            this.setBorder(new EmptyBorder(20,20,20,20));
            this.setLayout(new BorderLayout());

            JPanel container = style.createPnlRounded(550, 700, style.white, style.goldenTainoi);
            container.setBackground(style.goldenTainoi);
            container.setLayout(new GridBagLayout());
            container.setBorder(new EmptyBorder(10, 55, 10, 55));
            add(container, BorderLayout.CENTER);

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(2,80,2,80);
            gbc.anchor = GridBagConstraints.CENTER;
            gbc.fill = GridBagConstraints.BOTH;
            gbc.weightx = 2;

            gbc.ipady = 80;
            gbc.gridx = 0;
            gbc.gridy = 0;
            JLabel lblHeading = style.createLblH1("Sign Up", style.black);
            lblHeading.setHorizontalAlignment(SwingConstants.CENTER);
            container.add(lblHeading, gbc);

            gbc.ipady = 5;
            gbc.gridy = 1;
            JLabel lblUsername = style.createLblH4("Username", style.black);
            container.add(lblUsername, gbc);

            gbc.gridy = 2;
            txtUsername = style.createTxtRounded("Username", style.lightGray, style.gray, 20);
            container.add(txtUsername, gbc);

            gbc.gridy = 3;
            JLabel lblName = style.createLblH4("Full Name", style.black);
            container.add(lblName, gbc);

            gbc.gridy = 4;
            txtName = style.createTxtRounded("Full Name", style.lightGray, style.gray, 20);
            container.add(txtName, gbc);

            gbc.gridy = 5;
            JLabel lblPassword = style.createLblH4("Password", style.black);
            container.add(lblPassword, gbc);

            gbc.gridy = 6;
            txtPassword = style.createPwdRounded(style.lightGray, style.gray, 20);
            txtPassword.setText("Password");
            txtPassword.setEchoChar((char)0);
            container.add(txtPassword, gbc);

            gbc.gridy = 7;
            chkShowPassword = new JCheckBox("Show Password");
            chkShowPassword.setBorderPaintedFlat(true);
            chkShowPassword.setBackground(style.lightGray);
            chkShowPassword.setForeground(style.gray);
            chkShowPassword.setFont(new Font("Arial", Font.PLAIN, 12));
            container.add(chkShowPassword, gbc);

            gbc.gridy = 8;
            JLabel lblConfirmPassword = style.createLblH4("Confirm Password", style.black);
            container.add(lblConfirmPassword, gbc);

            gbc.gridy = 9;
            txtConfirmPassword = style.createPwdRounded(style.lightGray, style.gray, 20);
            txtConfirmPassword.setText("Confirm Password");
            txtConfirmPassword.setEchoChar((char)0);
            container.add(txtConfirmPassword, gbc);

            gbc.gridy = 10;
            chkShowConfirmPassword = new JCheckBox("Show Password");
            chkShowConfirmPassword.setBorderPaintedFlat(true);
            chkShowConfirmPassword.setBackground(style.lightGray);
            chkShowConfirmPassword.setForeground(style.gray);
            chkShowConfirmPassword.setFont(new Font("Arial", Font.PLAIN, 12));
            container.add(chkShowConfirmPassword, gbc);

            gbc.gridy = 11;
            lblErrorMessage = style.createLblH4("Username is already taken.", style.red);
            lblErrorMessage.setHorizontalAlignment(SwingConstants.CENTER);
            container.add(lblErrorMessage, gbc);

            gbc.ipady = 10;
            gbc.gridy = 12;
            btnSignup = style.createBtnRounded("Create account", style.deepSkyBlue, style.black,10);
            container.add(btnSignup, gbc);

            gbc.gridy = 13;
            gbc.ipady = 20;
            JLabel lblBr = new JLabel("");
            container.add(lblBr, gbc);

            gbc.ipady = 5;
            gbc.gridy = 14;
            FlowLayout flowLayout = new FlowLayout(FlowLayout.CENTER);
            flowLayout.setHgap(200);
            flowLayout.setVgap(0);
            JPanel pnlLogin = new JPanel(flowLayout);
            pnlLogin.setBackground(style.white);
            pnlLogin.setPreferredSize(new Dimension(450, 60));
            container.add(pnlLogin, gbc);

            JLabel lblHaveAccount = style.createLblP2("Have an account?", style.black);
            lblHaveAccount.setHorizontalAlignment(SwingConstants.CENTER);
            pnlLogin.add(lblHaveAccount, gbc);

            btnLogin = style.createBtnTxtOnly("Sign in.", style.deepSkyBlue);
            btnLogin.setFont(new Font("Arial", Font.PLAIN, 12));
            pnlLogin.add(btnLogin);

            this.setPreferredSize(new Dimension(500,700));
        }
    }

    public static void main(String[] args) {
        new RegisterView();
    }

    /**
     * Retrieves the current JTextField of txtUsername.
     * @return The current txtUsername.
     */
    public JTextField getTxtUsername() {
        return txtUsername;
    }

    /**
     * Retrieves the current JTextField of txtEmail.
     * @return The current txtEmail.
     */
    public JTextField getTxtName() {
        return txtName;
    }

    /**
     * Retrieves the current JPasswordField of txtPassword.
     * @return The current txtPassword.
     */
    public JPasswordField getTxtPassword() {
        return txtPassword;
    }

    /**
     * Retrieves the current JPasswordField of txtConfirmPassword.
     * @return The current txtConfirmPassword.
     */
    public JPasswordField getTxtConfirmPassword() {
        return txtConfirmPassword;
    }

    /**
     * Retrieves the current JCheckBox of chkShowPassword.
     * @return The current chkShowPassword.
     */
    public JCheckBox getChkShowPassword() {
        return chkShowPassword;
    }

    /**
     * Retrieves the current JCheckBox of chkShowConfirmPassword.
     * @return The current chkShowConfirmPassword.
     */
    public JCheckBox getChkShowConfirmPassword() {
        return chkShowConfirmPassword;
    }

    /**
     * Retrieves the current JButton of btnSignup.
     * @return The current btnSignup.
     */
    public JButton getBtnSignup() {
        return btnSignup;
    }

    /**
     * Retrieves the current JButton of btnLogin.
     * @return The current btnLogin.
     */
    public JButton getBtnLogin() {
        return btnLogin;
    }

    /**
     * Retrieves the current JLabel of lblErrorMessage.
     * @return The current lblErrorMessage.
     */
    public JLabel getLblErrorMessage() {
        return lblErrorMessage;
    }

    /**
     * Sets a specified action listener for btnSignup.
     * @param actionListener The specified action listener.
     */
    public void setSignupListener(ActionListener actionListener) {
        btnSignup.addActionListener(actionListener);
    }

    /**
     * Sets a specified action listener for btnLogin.
     * @param actionListener The specified action listener.
     */
    public void setLoginListener(ActionListener actionListener) {
        btnLogin.addActionListener(actionListener);
    }

    /**
     * Sets a new error message for lblErrorMessage.
     * @param text The new error message.
     */
    public void setErrorMessage(String text) {
        lblErrorMessage.setText(text);
    }
}
