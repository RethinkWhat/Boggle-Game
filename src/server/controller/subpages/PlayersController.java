package server.controller.subpages;

import server.model.DataPB;
import server.model.subpages.PlayersModel;
import server.view.subpages.PlayersView;
import shared.Player;
import shared.SwingResources;
import shared.SwingStylesheet;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;

public class PlayersController {
    private PlayersView view;
    private PlayersModel model;

    public PlayersController(PlayersModel model, PlayersView view) {
        this.view = view;
        this.model = model;

        // Add ActionListener to the Add Player button
        view.getFunctionPanel().setAddPlayerListener(new AddPlayerListener());


        view.getFunctionPanel().getTxtSearchbar().addKeyListener(new KeyAdapter() {
            SwingStylesheet style = new SwingStylesheet();

            @Override
            public void keyPressed(KeyEvent e) {
                // This will clear when you start typing
                if (view.getFunctionPanel().getTxtSearchbar().getText().equals("Search Players")) {
                    view.getFunctionPanel().getTxtSearchbar().setText("");
                    view.getFunctionPanel().getTxtSearchbar().setForeground(style.black);
                }


                // If the text is empty, it will set it back to "Search Players"
                if (view.getFunctionPanel().getTxtSearchbar().getText().isEmpty() && e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    view.getFunctionPanel().getTxtSearchbar().setText("Search Players");
                    view.getFunctionPanel().getTxtSearchbar().setForeground(style.gray);
                }

                // Prevent deletion of "Search Players"
                if (view.getFunctionPanel().getTxtSearchbar().getText().equals("Search Players") && e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    e.consume();
                }

                // Trigger validation when Enter key is pressed
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    performSearch();
                }
            }
        });

        view.getFunctionPanel().setSearchListener(new SearchListener());
        populatePlayersTable();

        view.hideAddPlayerPanel();

        view.revalidate();
        view.repaint();
    }

    // Inner class for handling Add Player button click
    class AddPlayerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Display the AddPlayerPanel
            view.showAddPlayerPanel();

            view.getAddPlayerPanel().setBtnCreateActionListener(new CreateAccountListener());

            view.getAddPlayerPanel().setBtnTxtCancelActionListener(new CancelAddPlayerListener());

            view.getAddPlayerPanel().getTxtUsername().addFocusListener(new SwingResources.TextFieldFocus(view.getAddPlayerPanel().
                    getTxtUsername(), "Username", view.getAddPlayerPanel().getLblErrorMessage()));

            view.getAddPlayerPanel().getTxtFullName().addFocusListener(new SwingResources.TextFieldFocus(view.getAddPlayerPanel().
                    getTxtFullName(), "Name", view.getAddPlayerPanel().getLblErrorMessage()));

            view.getAddPlayerPanel().getTxtPassword().addFocusListener(new SwingResources.PasswordFocusWithCheckbox
                    (view.getAddPlayerPanel().getTxtPassword(), view.getAddPlayerPanel().getChkPassword(), "Password",
                            view.getAddPlayerPanel().getLblErrorMessage()));

            view.getAddPlayerPanel().getTxtConfirmPassword().addFocusListener(new SwingResources.PasswordFocusWithCheckbox
                    (view.getAddPlayerPanel().getTxtConfirmPassword(), view.getAddPlayerPanel().getChkConfirmPassword(),
                            "Confirm Password", view.getAddPlayerPanel().getLblErrorMessage()));
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

    // Inner class for handling Create Account button click in AddPlayerPanel
    class CreateAccountListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = view.getAddPlayerPanel().getTxtUsername().getText();
            String fullName = view.getAddPlayerPanel().getTxtFullName().getText();
            String password = new String(view.getAddPlayerPanel().getTxtPassword().getPassword());
            String confirmPassword = new String(view.getAddPlayerPanel().getTxtConfirmPassword().getPassword());

            if (!password.equals(confirmPassword)) {
                view.getAddPlayerPanel().setLblErrorMessage("Passwords do not match!");
                return;
            }

            boolean added = model.addPlayer(username, password, fullName, view.getAddPlayerPanel().getAvatarImagePath());

            if (added) {
                System.out.println("Player added successfully!");
                view.hideAddPlayerPanel();
            } else {
                System.out.println("Player adding failed!");
            }
        }
    }

    class SearchListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            performSearch();
        }
    }

    // Method to perform search
    private void performSearch() {
        String searchText = view.getFunctionPanel().getTxtSearchbar().getText();
        if (!searchText.isEmpty()) {
            if (searchText.equalsIgnoreCase("All")) {
                populatePlayersTable();
            } else {
                List<String> searchedPlayers = DataPB.searchUsername(searchText);
                updateTable(searchedPlayers);
            }
        } else {
            populatePlayersTable();
        }
    }


    private void populatePlayersTable() {
        view.getTablePanel().clearTable();
        List<Player> player = DataPB.getAllPlayers();
        for (Player players : player) {
            view.getTablePanel().addRow(new Object[]{ players.getPlayerID(), players.getUsername(), players.getFullName()});
        }
    }

    private void updateTable(List<String> searchedUsernames) {
        // Clear existing table data
        view.getTablePanel().clearTable();

        for (int i = 0; i < searchedUsernames.size(); i += 3) {
            view.getTablePanel().addRow(new Object[]{searchedUsernames.get(i), searchedUsernames.get(i + 1), searchedUsernames.get(i + 2)});
        }
    }

}