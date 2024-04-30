package server.controller.subpages;

import server.model.ServerImplementation;
import server.model.subpages.GameSettingsModel;
import server.view.subpages.GameSettingsView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameSettingsController {

    private GameSettingsModel model;
    private GameSettingsView view;


    private int[] gameDurationInMins = {1,2,3,4};
    private int[] waitTimeInSeconds = {10, 15, 20, 25};
    private int[] noOfPlayers = {2,3,4,5,6,7,8,9, Integer.MAX_VALUE};

    int selectedGameDur;
    int selectedWaitDur;
    int selectedPlayers;




    public GameSettingsController(GameSettingsModel model, GameSettingsView view) {
        this.model = model;
        this.view = view;

        selectedGameDur = view.getGameDurationComboBox().getSelectedIndex();
        selectedWaitDur = view.getWaitingDurationComboBox().getSelectedIndex();
        selectedPlayers = view.getNumberOfPlayersComboBox().getSelectedIndex();


        view.getBtnSave().addActionListener(new SaveListener());
        //view.getBtnEdit().addActionListener(new EditListener());
        view.getBtnCancel().addActionListener(new CancelListener());
        view.getBtnBackToDefault().addActionListener(new BackToDefaultListener());
    }

    public class CancelListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            view.getGameDurationComboBox().setSelectedIndex(selectedGameDur);
            view.getWaitingDurationComboBox().setSelectedIndex(selectedWaitDur);
            view.getNumberOfPlayersComboBox().setSelectedIndex(selectedPlayers);
        }
    }

    public class BackToDefaultListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.getGameDurationComboBox().setSelectedIndex(0);
            view.getWaitingDurationComboBox().setSelectedIndex(0);
            view.getNumberOfPlayersComboBox().setSelectedIndex(0);
            model.updateGameDuration(gameDurationInMins[0] * 60000L);
            model.updateWaitingDuration(waitTimeInSeconds[0] * 1000 );
            model.updateNumberOfPlayers(noOfPlayers[0]);
        }
    }


    public class SaveListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            int gameDurChoice = view.getGameDurationComboBox().getSelectedIndex();
            model.updateGameDuration(gameDurationInMins[gameDurChoice] * 60000L);

            int waitChoice = view.getWaitingDurationComboBox().getSelectedIndex();
            model.updateWaitingDuration(waitTimeInSeconds[waitChoice] * 1000L);

            int noPlayersChoice = view.getNumberOfPlayersComboBox().getSelectedIndex();
            model.updateNumberOfPlayers(noOfPlayers[noPlayersChoice]);

        }
    }


}
