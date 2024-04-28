package client.view.prompts;

import shared.CustomizedMessageDialog;
import shared.SwingStylesheet;
import javax.swing.*;

public class PassChangeSuccessView {

    public static void main() {
        new PassChangeSuccessView().run();
    }

    public void run() {
        SwingStylesheet style = new SwingStylesheet();
        CustomizedMessageDialog passwordChangeSuccess = new CustomizedMessageDialog(
                "Password Change Success",
                new ImageIcon("res/drawable/icons/success-tainoi-solid.png"),
                "PASSWORD CHANGED",
                "You have successfully changed your password.",
                "CLOSE",
                style.deepSkyBlue,
                style.goldenTainoi,
                style.black,
                style.goldenTainoi,
                false
        );
    }
}