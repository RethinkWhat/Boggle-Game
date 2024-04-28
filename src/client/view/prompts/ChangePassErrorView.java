package client.view.prompts;

import shared.CustomizedMessageDialog;
import shared.SwingStylesheet;

import javax.swing.*;

public class ChangePassErrorView {

    public static void main() {
        new ChangePassErrorView().run();
    }

    public void run() {
        SwingStylesheet style = new SwingStylesheet();
        CustomizedMessageDialog changeProfileInfoError = new CustomizedMessageDialog(
                "Password Change Error",
                new ImageIcon("res/drawable/icons/error-red-solid.png"),
                "ERROR",
                "An error occurred while changing your password.",
                "CLOSE",
                style.red,
                style.red,
                style.black,
                style.red,
                false
        );
    }
}