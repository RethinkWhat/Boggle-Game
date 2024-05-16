package Client_Java.controller.subpages;

import Client_Java.model.subpages.AvatarSelectionModel;
import Client_Java.view.subpages.AvatarSelectionView;
import shared.SwingResources;
import shared.SwingStylesheet;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AvatarSelectionController {
    /**
     * The view.
     */
    private AvatarSelectionView view;
    /**
     * The username.
     */
    private AvatarSelectionModel model;
    /**
     * The selected avatar file path.
     */
    private String selectedAvatarPath;
    /**
     * The stylesheet to control UI elements.
     */
    private SwingStylesheet style = new SwingStylesheet();

    /**
     * Constructs a controller with a specified view and a specified model.
     * @param view The specified view.
     * @param model The specified model.
     */
    public AvatarSelectionController(AvatarSelectionView view, AvatarSelectionModel model) {
        this.view = view;
        this.model = model;
        selectedAvatarPath = null;
        // action listeners
        for (JButton btnAvatar: view.getAvatarButtons()) {
            view.setSelectionListener(new AvatarSelectionListener(), btnAvatar);
        }

        view.setConfirmListener(new ConfirmListener());

        // mouse listeners
        view.getBtnConfirm().addMouseListener(new SwingResources.CursorChanger(view.getBtnConfirm()));

        for (JButton btnAvatar: view.getAvatarButtons()) {
            btnAvatar.addMouseListener(new SwingResources.CursorChanger(btnAvatar));
        }

        view.revalidate();
        view.repaint();
    }

    class AvatarSelectionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton clickedButton = (JButton) e.getSource();

            for (JButton avatarButton : view.getAvatarButtons()) {
                avatarButton.setBackground(style.white);
            }

            clickedButton.setBackground(style.gray);
            selectedAvatarPath = ((ImageIcon) clickedButton.getIcon()).getDescription();
        }
    }

    class ConfirmListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            model.editAvatar(model.getUsername(), selectedAvatarPath);
            view.getHomeView().setAvatarImagePath(selectedAvatarPath);
            view.getSettingsView().setAvatarImagePath(selectedAvatarPath);

            for (JButton btnAvatar: view.getAvatarButtons()) {
                btnAvatar.setBackground(style.white);
            }
            SwingUtilities.getWindowAncestor(view.getBtnConfirm()).dispose();
        }
    }
}
