package Client_Java.view.prompts;

import shared.CustomizedMessageDialog;
import shared.SwingStylesheet;
import javax.swing.*;

public class DelAccErrorView {

    public static void main() {
        new DelAccErrorView().run();
    }

    public void run() {
        SwingStylesheet style = new SwingStylesheet();
        CustomizedMessageDialog deleteAccountError = new CustomizedMessageDialog(
                "Delete Account Error",
                new ImageIcon("res/drawable/icons/error-red-solid.png"),
                "ERROR",
                "An error occurred while deleting your account.",
                "CLOSE",
                style.red,
                style.red,
                style.black,
                style.red,
                false
        );
    }
}