package server.controller.subpages;

import server.model.DataPB;
import server.model.subpages.PlayersModel;
import server.view.subpages.AvatarOptionView;
import server.view.subpages.PlayersView;
import shared.CustomizedMessageDialog;
import shared.Player;
import shared.SwingResources;
import shared.SwingStylesheet;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.*;
import java.util.List;

public class PlayersController {
    private PlayersView view;
    private PlayersModel model;
    private AvatarOptionView avatarOptionView;

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

        view.getTablePanel().getTblPlayers().getSelectionModel().addListSelectionListener(new TableSelectionListener());

        view.getButtonPanel().setBtnRemoveActionListener(new RemovePlayerListener());
        view.getButtonPanel().setBtnCancelActionListener(new CancelRemoveButtonListener());

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

            view.getAddPlayerPanel().setBtnEditActionListener(new EditAvatarListener());

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
            view.closeAddPlayerPanel();
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
            try{
                boolean added = model.addPlayer(username, password, fullName, view.getAddPlayerPanel().getAvatarImagePath());

                if (added) {
                    SwingStylesheet style = new SwingStylesheet();
                    CustomizedMessageDialog playerCreated = new CustomizedMessageDialog(
                            "Player Created",
                            new ImageIcon("res/drawable/icons/success-tainoi-solid.png"),
                            "PLAYER CREATED",
                            "You have successfully created an account for this player.",
                            "CLOSE",
                            style.deepSkyBlue,
                            style.goldenTainoi,
                            style.black,
                            style.goldenTainoi,
                            false
                    );
                    view.hideAddPlayerPanel();
                } else {
                    System.out.println("Player adding failed!");
                    SwingStylesheet style = new SwingStylesheet();
                    CustomizedMessageDialog playerCreated = new CustomizedMessageDialog(
                            "Player Exists",
                            new ImageIcon("res/drawable/icons/error-red-solid.png"),
                            "PLAYER EXISTS",
                            "Player already exists. Enter a new player name.",
                            "OKAY",
                            style.deepSkyBlue,
                            style.red,
                            style.black,
                            style.red,
                            false
                    );
                }
            }catch (Exception ee){
                ee.printStackTrace();

            }
        }
    }

    // Inner class for handling Edit Avatar button click in AddPlayerPanel
    class EditAvatarListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            avatarOptionView = new AvatarOptionView(view.getAddPlayerPanel().getStringUsername(), new DataPB(), view);
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

    class TableSelectionListener implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (!e.getValueIsAdjusting()) {
                // Check if any row is selected
                if (view.getTablePanel().getTblPlayers().getSelectedRow() != -1) {
                    // Show the ButtonPanel if a row is selected
                    view.showButtonPanel();
                } else {
                    // Hide the ButtonPanel if no row is selected
                    view.hideButtonPanel();
                }
            }
        }
    }

    class RemovePlayerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = view.getTablePanel().getTblPlayers().getSelectedRow();
            if (selectedRow != -1) {
                int playerId = Integer.parseInt(view.getTablePanel().getTblPlayers().getValueAt(selectedRow, 0).toString());

                boolean removed = DataPB.removePlayer(playerId);

                if (removed) {
                    view.getTablePanel().removeRow(selectedRow);
                    SwingStylesheet style = new SwingStylesheet();
                    CustomizedMessageDialog playerRemoved = new CustomizedMessageDialog(
                            "Player Removed",
                            new ImageIcon("res/drawable/icons/success-tainoi-solid.png"),
                            "PLAYER REMOVED",
                            "You have successfully removed the player from the game.",
                            "CLOSE",
                            style.deepSkyBlue,
                            style.goldenTainoi,
                            style.black,
                            style.goldenTainoi,
                            false
                    );
                }
            }
        }
    }

    class CancelRemoveButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Unselect the player
            view.getTablePanel().getTblPlayers().clearSelection();
            // Hide the button panel
            view.getButtonPanel().setVisible(false);
        }
    }
}