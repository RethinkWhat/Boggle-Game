package shared;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * The SwingResources provide implementation to Swing UI components.
 */
public class SwingResources {
    /**
     * Clears the text in a specified JPasswordField when it is focused, and inserts a specified placeholder
     * text when unfocused.
     */
    public static class PasswordFocus implements FocusListener {
        /**
         * The specified password field.
         */
        private JPasswordField passwordField;
        /**
         * The specified placeholder text.
         */
        private String placeholder;

        /**
         * Constructs an object of PasswordFocus with a specified password field, show password text box and
         * placeholder text.
         *
         * @param passwordField The specified password field.
         * @param placeholder   The specified placeholder text.
         */
        public PasswordFocus(JPasswordField passwordField, String placeholder) {
            this.passwordField = passwordField;
            this.placeholder = placeholder;
            this.passwordField.setText(placeholder);
        }

        /**
         * Processes the event when focused. Clears the password field of its placeholder text.
         *
         * @param e the event to be processed
         */
        @Override
        public void focusGained(FocusEvent e) {
            passwordField.setEchoChar('●');
            if (String.valueOf(passwordField.getPassword()).equals(placeholder)) {
                passwordField.setText("");
            }
        }

        /**
         * Processes the event when focused. The checkbox is overridden and displays the password in plain text, and
         * adds a placeholder text.
         *
         * @param e the event to be processed
         */
        @Override
        public void focusLost(FocusEvent e) {
            if (String.valueOf(passwordField.getPassword()).isEmpty()) {
                passwordField.setText(placeholder);
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('●');
            }
        }
    }

    /**
     * Clears the text in a specified JPasswordField when it is focused, and inserts a specified placeholder
     * text when unfocused.
     */
    public static class PasswordFocusWithCheckbox implements FocusListener {
        /**
         * The specified password field.
         */
        private JPasswordField passwordField;
        /**
         * The specified show password checkbox.
         */
        private JCheckBox chkShowPassword;
        /**
         * The specified placeholder text.
         */
        private String placeholder;

        /**
         * Constructs an object of PasswordFocus with a specified password field, show password text box and
         * placeholder text.
         * @param passwordField The specified password field.
         * @param chkShowPassword The specified show password checkbox.
         * @param placeholder The specified placeholder text.
         */
        public PasswordFocusWithCheckbox(JPasswordField passwordField, JCheckBox chkShowPassword, String placeholder) {
            this.passwordField = passwordField;
            this.placeholder = placeholder;
            this.passwordField.setText(placeholder);
            this.chkShowPassword = chkShowPassword;
        }

        /**
         * Processes the event when focused. The checkbox is overridden and hides the password input, and clears
         * the password field of its placeholder text.
         * @param e the event to be processed
         */
        @Override
        public void focusGained(FocusEvent e) {
            if (!chkShowPassword.isSelected()) {
                passwordField.setEchoChar('●');
            }
            if (String.valueOf(passwordField.getPassword()).equals(placeholder)) {
                passwordField.setText("");
            }
        }

        /**
         * Processes the event when focused. The checkbox is overridden and displays the password in plain text, and
         * adds a placeholder text.
         * @param e the event to be processed
         */
        @Override
        public void focusLost(FocusEvent e) {
            if (!chkShowPassword.isSelected()) {
                passwordField.setEchoChar('●');
            }
            if (String.valueOf(passwordField.getPassword()).isEmpty()) {
                passwordField.setText(placeholder);
                passwordField.setEchoChar((char) 0);
            }
        }
    }

    /**
     * Shows the password of a specified JPasswordField.
     */
    public static class ShowPasswordListener implements ActionListener {
        /**
         * The specified checkbox of show password.
         */
        private JCheckBox checkBox;
        /**
         * The specified password field.
         */
        private JPasswordField passwordField;

        /**
         * Constructs an object of ShowPasswordField listener with a specified JCheckBox and JPasswordField.
         * @param checkBox The specified "show password" checkbox.
         * @param passwordField The specified password field.
         */
        public ShowPasswordListener(JCheckBox checkBox, JPasswordField passwordField) {
            this.checkBox = checkBox;
            this.passwordField = passwordField;
        }

        /**
         * Processes the user request.
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            if (checkBox.isSelected()) {
                passwordField.setEchoChar((char) 0); // shows password in characters
            } else if (!checkBox.isSelected()) {
                passwordField.setEchoChar('●');
            }
        }
    }




    /**
     * Clears the text in a specified JTextField when it is focused, and inserts a specified placeholder
     * text when unfocused.
     */
    public static class TextFieldFocus implements FocusListener {
        /**
         * The specified text field.
         */
        private JTextField textField;
        /**
         * The specified placeholder text.
         */
        private String placeholder;

        /**
         * Constructs an object of TextFieldFocus with a specified text field and placeholder text.
         *
         * @param textField   The specified text field.
         * @param placeholder The specified placeholder text.
         */
        public TextFieldFocus(JTextField textField, String placeholder) {
            this.textField = textField;
            this.placeholder = placeholder;
        }

        /**
         * Processes the event when focused. The text field contents are cleared to accommodate user input.
         *
         * @param e the event to be processed
         */
        @Override
        public void focusGained(FocusEvent e) {
            if (textField.getText().equals(placeholder)) {
                textField.setText("");
            }
        }

        /**
         * Processes the event when unfocused. A placeholder text is inserted in the text field.
         *
         * @param e the event to be processed
         */
        @Override
        public void focusLost(FocusEvent e) {
            if (textField.getText().isEmpty()) {
                textField.setText(placeholder);
            }
        }
    }
    /**
     * Changes the Cursor when hovered to a specific UI component.
     */
    public static class CursorChanger extends MouseAdapter {
        /**
         * The specified button.
         */
        private JButton button;

        /**
         * Constructs an object of CursorChanger with a specified JButton.
         *
         * @param button The specified button.
         */
        public CursorChanger(JButton button) {
            this.button = button;
        }

        /**
         * When the mouse hovers inside the vicinity of the UI component.
         *
         * @param e the event to be processed
         */
        @Override
        public void mouseEntered(MouseEvent e) {
            button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }

        /**
         * When the mouse hovers outside the UI component.
         *
         * @param e the event to be processed
         */
        @Override
        public void mouseExited(MouseEvent e) {
            button.setCursor(Cursor.getDefaultCursor());
        }
    }

}
