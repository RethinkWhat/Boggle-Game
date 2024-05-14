package server.controller.subpages;

import server.model.ServerImplementation;
import server.model.subpages.GameSettingsModel;
import server.view.subpages.GameSettingsView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameSettingsController {

    private GameSettingsModel model;
    private GameSettingsView view;


    private int[] gameDurationInMins = {1,2,3,4};
    private int[] waitTimeInSeconds = {10, 15, 20, 25};

    int selectedGameDur;
    int selectedWaitDur;




    public GameSettingsController(GameSettingsModel model, GameSettingsView view) {
        this.model = model;
        this.view = view;

        selectedGameDur = view.getGameDurationComboBox().getSelectedIndex();
        selectedWaitDur = view.getWaitingDurationComboBox().getSelectedIndex();

        view.getBtnEdit().addActionListener(new EditListener());

    }

    public class CancelListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            view.showSaved();
            view.getBtnEdit().addActionListener(new EditListener());
        }
    }

    public class DuraBoxListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            // Get the selected index
            int selectedIndex = view.getGameDurationComboBox().getSelectedIndex();
            System.out.println("option:" + selectedIndex);
            selectedGameDur = selectedIndex;
        }
    }
    public class WaitBoxListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            // Get the selected index
            int selectedIndex = view.getWaitingDurationComboBox().getSelectedIndex();
            System.out.println("option:" + selectedIndex);
            selectedWaitDur = selectedIndex;
        }
    }
    public class EditListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("button clicked");
            view.showEditView();
            view.getGameDurationComboBox().addActionListener(new DuraBoxListener());
            view.getWaitingDurationComboBox().addActionListener(new WaitBoxListener());
            view.getBtnCancel().addActionListener(new CancelListener());
            view.getBtnSave().addActionListener(new SaveListener());
            view.getBtnBackToDefault().addActionListener(new BackToDefaultListener());
        }
    }

    public class BackToDefaultListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("button BTD clicked");
            view.showSaved();
            view.getBtnEdit().addActionListener(new EditListener());
            view.getGameDurationComboBox().setSelectedIndex(0);
            view.getWaitingDurationComboBox().setSelectedIndex(0);
            view.getNumberOfPlayersComboBox().setSelectedIndex(0);
            model.updateGameDuration(gameDurationInMins[0] * 60000L);
            model.updateWaitingDuration(waitTimeInSeconds[0] * 1000 );
        }
    }


    public class SaveListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("button SAVE clicked");
            view.showSaved();
            view.getBtnEdit().addActionListener(new EditListener());
            view.setGameDurationComboBox(selectedGameDur);
            System.out.println(view.getGameDurationComboBox().getSelectedIndex());
            int gameDurChoice = view.getGameDurationComboBox().getSelectedIndex();
            System.out.println(gameDurationInMins[gameDurChoice] * 60000L);
            model.updateGameDuration(gameDurationInMins[gameDurChoice] * 60000L);

            view.setWaitingDurationComboBox(selectedWaitDur);
            int waitChoice = view.getWaitingDurationComboBox().getSelectedIndex();
            model.updateWaitingDuration(waitTimeInSeconds[waitChoice] * 1000L);

        }
    }


}
