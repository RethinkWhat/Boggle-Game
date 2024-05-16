package Client_Java.view.prompts;

import shared.CustomizedMessageDialog;
import shared.SwingStylesheet;
import javax.swing.*;

public class AvatarChangedSuccessView {

    public static void main() {
        new AvatarChangedSuccessView().run();
    }

    public void run() {
        SwingStylesheet style = new SwingStylesheet();
        CustomizedMessageDialog avatarChangeSuccess = new CustomizedMessageDialog(
                "Avatar Change Success",
                new ImageIcon("res/drawable/icons/success-tainoi-solid.png"),
                "PROFILE CHANGED",
                "Restart the application for changes to reflect.",
                "CLOSE",
                style.deepSkyBlue,
                style.goldenTainoi,
                style.black,
                style.goldenTainoi,
                false
        );
    }
}