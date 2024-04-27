package client.view.prompts;

import shared.CustomizedMessageDialog;
import shared.SwingStylesheet;
import javax.swing.*;

public class ChangeProfInfoErrorView {

    public static void main() {
        new ChangeProfInfoErrorView().run();
    }

    public void run() {
        SwingStylesheet style = new SwingStylesheet();
        CustomizedMessageDialog changeProfileInfoError = new CustomizedMessageDialog(
                "Profile Change Error",
                new ImageIcon("res/drawable/icons/error-red-solid.png"),
                "ERROR",
                "An error occurred while changing your profile information.",
                "CLOSE",
                style.red,
                style.red,
                style.black,
                style.red,
                true
        );
    }
}