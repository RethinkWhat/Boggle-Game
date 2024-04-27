package server.controller.subpages;

import server.model.subpages.PlayersModel;
import server.view.subpages.PlayersView;
import shared.SwingResources;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;

public class PlayersController {
    private PlayersView view;
    private PlayersModel model;

    public PlayersController(PlayersView view, PlayersModel model) {
        this.view = view;
        this.model = model;

        // Add ActionListener to the Add Player button
        view.getFunctionPanel().setAddPlayerListener(new AddPlayerListener());

        //TO FIX: getAddPlayerPanel is returning null (problem might be with PlayersView
//        view.getAddPlayerPanel().setBtnTxtCancelActionListener(new CancelAddPlayerListener());

//        view.getAddPlayerPanel().getTxtUsername().addFocusListener(new SwingResources.TextFieldFocus(view.getAddPlayerPanel().
//                getTxtUsername(), "Username", view.getAddPlayerPanel().getLblErrorMessage()));
//        view.getAddPlayerPanel().getTxtFullName().addFocusListener(new SwingResources.TextFieldFocus(view.getAddPlayerPanel().
//                getTxtFullName(), "Name", view.getAddPlayerPanel().getLblErrorMessage()));
//        view.getAddPlayerPanel().getTxtPassword().addFocusListener(new SwingResources.PasswordFocusWithCheckbox
//                (view.getAddPlayerPanel().getTxtPassword(), view.getAddPlayerPanel().getChkPassword(), "Password",
//                        view.getAddPlayerPanel().getLblErrorMessage()));
//        view.getAddPlayerPanel().getTxtConfirmPassword().addFocusListener(new SwingResources.PasswordFocusWithCheckbox
//                (view.getAddPlayerPanel().getTxtConfirmPassword(), view.getAddPlayerPanel().getChkConfirmPassword(),
//                        "Confirm Password", view.getAddPlayerPanel().getLblErrorMessage()));

        view.revalidate();
        view.repaint();
    }

    // Inner class for handling Add Player button click
    class AddPlayerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Display the AddPlayerPanel
            view.showAddPlayerPanel();
        }
    }

    // Inner class for handling Cancel button click in AddPlayerPanel
    class CancelAddPlayerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Close the AddPlayerPanel
            view.hideAddPlayerPanel();
        }
    }
}

