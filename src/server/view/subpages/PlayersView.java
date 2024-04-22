package server.view.subpages;

import shared.SwingStylesheet;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PlayersView extends JPanel {

    private JPanel pnlButtons;
    private JButton btnRemove;
    private JButton btnCancel;
    /**
     * The stylesheet.
     */
    private SwingStylesheet style = new SwingStylesheet();

    public PlayersView() {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(1300, 750));

        FunctionPanel functionPanel = new FunctionPanel();
        TablePanel tablePanel = new TablePanel();


        pnlButtons = new JPanel();
        btnRemove = style.createBtnRounded("REMOVE", style.red,style.white,10);
        btnCancel = style.createBtnRounded("CANCEL", style.deepSkyBlue,style.white,10);

        // Add the buttons to pnlButtons
        pnlButtons.setLayout(new FlowLayout());
        pnlButtons.setPreferredSize(new Dimension(1100, 70));
        pnlButtons.setBackground(style.lightGray);
        pnlButtons.add(btnRemove);
        pnlButtons.add(btnCancel);

        this.add(functionPanel, BorderLayout.NORTH);
        this.add(tablePanel, BorderLayout.CENTER);
        this.add(pnlButtons, BorderLayout.SOUTH);

        this.setPreferredSize(new Dimension(1300, 750));
        this.setVisible(true);
    }

    class FunctionPanel extends JPanel {

        private JTextField txtSearchbar;
        private JButton btnSearch;
        private JButton btnAddPlayer;

        public FunctionPanel() {
            this.setPreferredSize(new Dimension(1300, 70));

            JPanel pnlFunction = style.createPnlRounded(1200, 50, style.goldenTainoi, style.lightGray);
            pnlFunction.setLayout(new BorderLayout());
            pnlFunction.setBorder(style.padding);
            add(pnlFunction, BorderLayout.CENTER);

            // Search panel
            JPanel pnlSearch = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
            pnlSearch.setBackground(style.goldenTainoi);
            pnlSearch.setBorder(new EmptyBorder(0, 0, 0, 50));
            pnlFunction.add(pnlSearch, BorderLayout.WEST);

            txtSearchbar = style.createTxtRounded("Search Players", style.lightGray, style.gray, 20);
            pnlSearch.add(txtSearchbar);

            btnSearch = style.createBtnIconOnly(style.iconSearch, 25, 25);
            pnlSearch.add(btnSearch);

            JPanel pnlButton = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
            pnlButton.setBackground(style.goldenTainoi);
            pnlButton.setBorder(new EmptyBorder(0, 0, 0, 50));
            pnlSearch.add(pnlButton, BorderLayout.EAST);

            btnAddPlayer = style.createBtnTxtOnly("ADD PLAYER", style.deepSkyBlue);
//            btnAddPlayer.setEnabled(false);
            pnlFunction.add(btnAddPlayer, BorderLayout.EAST);
        }
    }



    class TablePanel extends JPanel {
        private JTable tblPlayers;
        private DefaultTableModel tblPlayersModel;
        private JPanel pnlTable;
        private Font headerFont;

        public TablePanel() {
            this.setPreferredSize(new Dimension(1300, 600));

            pnlTable = style.createPnlRounded(1200, 550, style.goldenTainoi, style.white);
            pnlTable.setBorder(style.padding);
            pnlTable.setLayout(new BorderLayout());
            add(pnlTable, BorderLayout.CENTER);

            tblPlayersModel = new DefaultTableModel();
            tblPlayersModel.addColumn("");
            tblPlayersModel.addColumn("USERNAME");
            tblPlayersModel.addColumn("FULL NAME");

            tblPlayers = new JTable(tblPlayersModel);
            tblPlayers.getColumnModel().getColumn(0).setPreferredWidth(200);
            tblPlayers.getColumnModel().getColumn(1).setPreferredWidth(350);
            tblPlayers.getColumnModel().getColumn(2).setPreferredWidth(350);

            tblPlayers.setRowHeight(30);
            tblPlayers.setRowHeight(30);
            tblPlayers.setRowHeight(30);

            headerFont = tblPlayers.getTableHeader().getFont();
            tblPlayers.getTableHeader().setFont(headerFont.deriveFont(Font.BOLD, 14));

            tblPlayers.setBackground(style.mustard);

            JScrollPane scrollPane = new JScrollPane(tblPlayers);
            scrollPane.setPreferredSize(new Dimension(1200, 520));
            pnlTable.add(scrollPane, BorderLayout.CENTER);
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Players View");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(new PlayersView());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}

