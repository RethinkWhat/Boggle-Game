package server.view.subpages;

import shared.SwingStylesheet;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Panel for displaying and managing players.
 * It consists of a function panel for search and adding players,
 * a table panel for displaying player information,
 * and buttons panel for actions like remove and cancel.
 */
public class PlayersView extends JPanel {

    /**
     * The button panel.
     */
    private JPanel pnlButtons; // Panel for buttons
    /**
     * The remove button.
     */
    private JButton btnRemove;
    /**
     * The cancel button.
     */
    private JButton btnCancel;
    /**
     * The stylesheet.
     */
    private SwingStylesheet style = new SwingStylesheet();

    private ButtonPanel buttonPanel;
    private FunctionPanel functionPanel;
    private TablePanel tablePanel;
    private AddPlayerPanel addPlayerPanel;

    public ButtonPanel getButtonPanel() {
        return buttonPanel;
    }

    public FunctionPanel getFunctionPanel() {
        return functionPanel;
    }

    public TablePanel getTablePanel() {
        return tablePanel;
    }

    public AddPlayerPanel getAddPlayerPanel() {
        return addPlayerPanel;
    }

    /**
     * Constructs a new PlayersView panel.
     * Initializes components, sets layout, and adds subpanels.
     */
    public PlayersView() {
        setBackground(style.deepSkyBlue);
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(1300, 750));

        functionPanel = new FunctionPanel();
        tablePanel = new TablePanel();
        buttonPanel = new ButtonPanel();
        addPlayerPanel = new AddPlayerPanel();

        add(functionPanel, BorderLayout.NORTH);
        add(tablePanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        buttonPanel.setVisible(true);

        this.setPreferredSize(new Dimension(1300, 750));
        this.setVisible(true);
    }

    /**
     * Panel for function buttons like search and add.
     * It contains text field for search, search button, add button, and add player button.
     */
    public class FunctionPanel extends JPanel {

        /**
         * The textfield for the search bar.
         */
        private JTextField txtSearchbar;
        /**
         * The search button.
         */
        private JButton btnSearch;
        /**
         * The add player button.
         */
        private JButton btnAdd;
        /**
         * The add button.
         */
        private JButton btnAddPlayer;

        /**
         * Constructs a new FunctionPanel.
         * Initializes components, sets layout, and adds subpanels.
         */
        public FunctionPanel() {
            setBackground(style.deepSkyBlue);
            setPreferredSize(new Dimension(1300, 70));

            JPanel pnlFunction = style.createPnlRounded(1200, 50, style.white, style.deepSkyBlue);
            pnlFunction.setLayout(new BorderLayout());
            pnlFunction.setBorder(style.padding);
            add(pnlFunction, BorderLayout.CENTER);

            // Search panel
            JPanel pnlSearch = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
            pnlSearch.setBackground(style.white);
            pnlFunction.add(pnlSearch, BorderLayout.WEST);

            txtSearchbar = style.createTxtRounded("Search Players", style.lightGray, style.gray, 20);
            pnlSearch.add(txtSearchbar);

            btnSearch = style.createBtnIconOnly(style.iconSearch, 25, 25);
            pnlSearch.add(btnSearch);

            // Button panel
            JPanel pnlButton = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
            pnlButton.setBackground(style.white);
            pnlFunction.add(pnlButton, BorderLayout.EAST);

            btnAdd = style.createBtnIconOnly(style.iconAdd, 25, 25);
            pnlButton.add(btnAdd);

            btnAddPlayer = style.createBtnTxtOnly("ADD PLAYER", style.deepSkyBlue);
            pnlButton.add(btnAddPlayer);
        }

        // Getters
        public JTextField getTxtSearchbar() {
            return txtSearchbar;
        }

        public JButton getBtnSearch() {
            return btnSearch;
        }

        public JButton getBtnAdd() {
            return btnAdd;
        }

        public JButton getBtnAddPlayer() {
            return btnAddPlayer;
        }

        // Setters
        public void setTxtSearchbarText(String text) {
            txtSearchbar.setText(text);
        }

        // Action listeners
        public void setSearchListener(ActionListener actionListener) {
            btnSearch.addActionListener(actionListener);
        }

        public void setAddListener(ActionListener actionListener) {
            btnAdd.addActionListener(actionListener);
        }

        public void setAddPlayerListener(ActionListener actionListener) {
            btnAddPlayer.addActionListener(actionListener);
        }
    }

    /**
     * Panel for displaying player information in a table.
     */
    public class TablePanel extends JPanel {
        private JTable tblPlayers; // Table for displaying players
        private DefaultTableModel tblPlayersModel; // Table model for players data
        private JPanel pnlTable; // Panel for table

        /**
         * Constructs a new TablePanel.
         * Initializes components, sets layout, and adds subpanels.
         */
        public TablePanel() {
            setBackground(style.deepSkyBlue);
            setPreferredSize(new Dimension(1300, 600));

            pnlTable = style.createPnlRounded(1200, 550, style.white, style.white);
            pnlTable.setBorder(style.padding);
            pnlTable.setLayout(new BorderLayout());
            add(pnlTable, BorderLayout.CENTER);

            // Initialize table model and columns
            tblPlayersModel = new DefaultTableModel();
            tblPlayersModel.addColumn("PLAYER ID");
            tblPlayersModel.addColumn("USERNAME");
            tblPlayersModel.addColumn("FULL NAME");

            // Create table with custom model
            tblPlayers = new JTable(tblPlayersModel);
            tblPlayers.setDragEnabled(false);
            tblPlayers.setOpaque(false);
            tblPlayers.setFillsViewportHeight(true);
            tblPlayers.getTableHeader().setResizingAllowed(false);
            tblPlayers.getTableHeader().setReorderingAllowed(false);
            tblPlayers.getTableHeader().setFont(style.bowlbyOne.deriveFont(Font.BOLD, 14));

            // Set preferred column widths
            tblPlayers.getColumnModel().getColumn(0).setPreferredWidth(200);
            tblPlayers.getColumnModel().getColumn(1).setPreferredWidth(350);
            tblPlayers.getColumnModel().getColumn(2).setPreferredWidth(350);

            // Set row height
            tblPlayers.setRowHeight(30);
            tblPlayers.setRowHeight(30);
            tblPlayers.setRowHeight(30);

            // Set background color
            tblPlayers.setBackground(style.white);
            tblPlayers.setFont(style.bowlbyOne.deriveFont(Font.PLAIN, 14));

            // Add table to scrollable pane
            JScrollPane scrollPane = new JScrollPane(tblPlayers);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            pnlTable.add(scrollPane, BorderLayout.CENTER);
        }
        // Getters
        public JTable getTblPlayers() {
            return tblPlayers;
        }

        public DefaultTableModel getTblPlayersModel() {
            return tblPlayersModel;
        }

        // Setters
        public void setTblPlayersModel(DefaultTableModel model) {
            tblPlayers.setModel(model);
        }

        // Methods
        public void addRow(Object[] rowData) {
            tblPlayersModel.addRow(rowData);
        }

        public void removeRow(int row) {
            tblPlayersModel.removeRow(row);
        }

        public void clearTable() {
            tblPlayersModel.setRowCount(0);
        }
    }

    public class ButtonPanel extends JPanel {
        private JButton btnRemove;
        private JButton btnCancel;

        public ButtonPanel() {
            setBackground(style.deepSkyBlue);
            setLayout(new FlowLayout());
            setPreferredSize(new Dimension(1100, 70));

            btnRemove = style.createBtnRoundedH2("REMOVE", style.red, style.white, 10);
            btnRemove.setPreferredSize(new Dimension(150,50));
            btnCancel = style.createBtnRoundedH2("CANCEL", style.goldenTainoi, style.white, 10);
            btnCancel.setPreferredSize(new Dimension(150,50));

            add(btnRemove);
            add(btnCancel);
        }

        public void setBtnRemoveActionListener(ActionListener listener) {
            btnRemove.addActionListener(listener);
        }

        public void setBtnCancelActionListener(ActionListener listener) {
            btnCancel.addActionListener(listener);
        }
    }

    /**
     * Dialog panel for adding a new player.
     */
    public class AddPlayerPanel extends JDialog {

        /**
         * The layout for this dialog.
         */
        private GridBagConstraints gbc;
        /**
         * The JLabel for the title.
         */
        private JLabel lblAddPlayer;
        /**
         * The placeholder for displaying the avatar.
         */
        private JLabel lblAvatar;
        /**
         * The edit button.
         */
        private JButton btnEdit;
        /**
         * The create button.
         */
        private JButton btnCreate;
        /**
         * The cancel text button.
         */
        private JButton btnTxtCancel;
        /**
         * The label for displaying the error message.
         */
        private JLabel lblErrorMessage;
        /**
         * The text field for the username
         */
        private JTextField txtUsername;
        /**
         * The text field for the full name
         */
        private JTextField txtFullName;
        /**
         * The password field for the password.
         */
        private JPasswordField txtPassword;
        /**
         * The password field for the confirm password.
         */
        private JPasswordField txtConfirmPassword;
        /**
         * The checkbox to hide or show the password.
         */
        private JCheckBox chkPassword;
        /**
         * The checkbox to hide or show the password.
         */
        private JCheckBox chkConfirmPassword;

        /**
         * Constructs a new AddPlayerPanel dialog.
         */
        public AddPlayerPanel() {
            setBackground(style.deepSkyBlue);
            setPreferredSize(new Dimension(500, 700));
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setLocationRelativeTo(null);
            setResizable(false);

            setLayout(new GridBagLayout());
            gbc = new GridBagConstraints();
            gbc.insets = new Insets(2, 80, 2, 80);
            gbc.anchor = GridBagConstraints.CENTER;
            gbc.weightx = 2;
            gbc.ipady = 40;
            gbc.gridx = 0;
            gbc.gridy = 0;

            lblAddPlayer = style.createLblStatus("Add Player", style.deepSkyBlue);
            lblAddPlayer.setHorizontalAlignment(SwingConstants.CENTER);
            add(lblAddPlayer, gbc);

            gbc.ipady = 10;
            gbc.gridy = 1;
            lblAvatar = new JLabel(style.iconPfpMale1);
            add(lblAvatar, gbc);

            gbc.gridy = 2;
            gbc.gridwidth = 1;
            gbc.fill = GridBagConstraints.NONE;
            btnEdit = style.createBtnRoundedH3("EDIT", style.deepSkyBlue, style.white, 10);
            btnEdit.setPreferredSize(new Dimension(150,30));
            add(btnEdit, gbc);

            gbc.gridy = 3;
            gbc.gridwidth = 2;
            gbc.fill = GridBagConstraints.BOTH;
            txtUsername = style.createTxtRounded("Username", style.lightGray, style.gray, 20);
            add(txtUsername, gbc);

            gbc.gridy = 4;
            txtFullName = style.createTxtRounded("Name", style.lightGray, style.gray, 20);
            add(txtFullName, gbc);

            gbc.gridy = 5;
            txtPassword = style.createPwdRounded(style.lightGray, style.gray, 20);
            txtPassword.setText("Password");
            txtPassword.setEchoChar((char) 0);
            add(txtPassword, gbc);

            gbc.gridy = 6;
            chkPassword = new JCheckBox("Show Password");
            chkPassword.setFont(new Font("Arial", Font.PLAIN, 14));
            chkPassword.setHorizontalAlignment(SwingConstants.LEFT);
            chkPassword.setBackground(style.lightGray);
            add(chkPassword, gbc);

            gbc.gridy = 7;
            txtConfirmPassword = style.createPwdRounded(style.lightGray, style.gray, 20);
            txtConfirmPassword.setText("Confirm Password");
            txtConfirmPassword.setEchoChar((char) 0);
            add(txtConfirmPassword, gbc);

            gbc.gridy = 8;
            chkConfirmPassword = new JCheckBox("Show Password");
            chkConfirmPassword.setFont(new Font("Arial", Font.PLAIN, 14));
            chkConfirmPassword.setHorizontalAlignment(SwingConstants.LEFT);
            chkConfirmPassword.setBackground(style.lightGray);
            add(chkConfirmPassword, gbc);

            gbc.gridy = 9;
            lblErrorMessage = style.createLblP("", style.red);
            add(lblErrorMessage, gbc);

            gbc.gridy = 10;
            btnCreate = style.createBtnRoundedH3("Create Account", style.deepSkyBlue, style.white, 10);
            add(btnCreate, gbc);

            gbc.gridy = 11;
            btnTxtCancel = style.createBtnTxtOnly("Cancel.", style.red);
            add(btnTxtCancel, gbc);

            pack();
            setVisible(true);
            setLocationRelativeTo(null);
        }

        public JLabel getLblErrorMessage() {
            return lblErrorMessage;
        }

        public String getAvatarImagePath() {
            if (lblAvatar != null && lblAvatar.getIcon() != null && lblAvatar.getIcon() instanceof ImageIcon) {
                ImageIcon icon = (ImageIcon) lblAvatar.getIcon();
                return icon.getDescription();
            }
            return null;
        }

        public JTextField getTxtUsername() {
            return txtUsername;
        }

        public JTextField getTxtFullName() {
            return txtFullName;
        }

        public JPasswordField getTxtPassword() {
            return txtPassword;
        }

        public JPasswordField getTxtConfirmPassword() {
            return txtConfirmPassword;
        }

        public JCheckBox getChkPassword() {
            return chkPassword;
        }

        public JCheckBox getChkConfirmPassword() {
            return chkConfirmPassword;
        }

        public void setLblErrorMessage(String text) {
            lblErrorMessage.setText(text);
        }

        public void setBtnEditActionListener(ActionListener listener) {
            btnEdit.addActionListener(listener);
        }

        public void setBtnCreateActionListener(ActionListener listener) {
            btnCreate.addActionListener(listener);
        }

        public void setBtnTxtCancelActionListener(ActionListener actionListener) {
            btnTxtCancel.addActionListener(actionListener);
        }
    }

    public void showAddPlayerPanel() {
        addPlayerPanel = new AddPlayerPanel();
        addPlayerPanel.setVisible(true);
        setEnabled(false);
    }

    public void hideAddPlayerPanel() {
        addPlayerPanel.dispose();
        setEnabled(true);
    }

}