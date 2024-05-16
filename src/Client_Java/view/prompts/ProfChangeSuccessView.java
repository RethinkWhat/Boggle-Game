package Client_Java.view.prompts;

import shared.CustomizedMessageDialog;
import shared.SwingStylesheet;
import javax.swing.*;

public class ProfChangeSuccessView {

    public static void main() {
        new ProfChangeSuccessView().run();
    }

    public void run() {
        SwingStylesheet style = new SwingStylesheet();
        CustomizedMessageDialog profileChangeSuccess = new CustomizedMessageDialog(
                "Profile Change Success",
                new ImageIcon("res/drawable/icons/success-tainoi-solid.png"),
                "PROFILE CHANGED",
                "You have successfully changed your profile information.",
                "CLOSE",
                style.deepSkyBlue,
                style.goldenTainoi,
                style.black,
                style.goldenTainoi,
                false
        );
    }
}