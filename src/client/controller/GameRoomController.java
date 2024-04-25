package client.controller;

import client.model.subpages.GameRoomModel;
import client.view.subpages.GameRoomView;
import shared.SwingResources;
import shared.SwingStylesheet;

import javax.sound.sampled.AudioInputStream;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameRoomController {

    /**
     * The view.
     */
    private GameRoomView view;
    /**
     * The model.
     */
    private GameRoomModel model;
    /**
     * Specifies whether the music is turned on or off.
     */
    private boolean musicOn;
    /**
     * Specifies whether the sound effects are turned on or off.
     */
    private boolean sfxOn;
    /**
     * The audio input stream for music.
     */
    private AudioInputStream audioMusicStream;
    /**
     * The audio input stream for sfx.
     */
    private AudioInputStream audioSoundStream;
    /**
     * The stylesheet.
     */
    private SwingStylesheet style = new SwingStylesheet();

    public GameRoomController(GameRoomModel model, GameRoomView view) {
        this.model = model;
        this.view = view;

        musicOn = true; // default music on
        sfxOn = true; // default sfx on

        // action listeners
        view.setMusicToggleListener(new MusicListener());
        view.setSoundToggleListener(new SoundListener());

        // mouse listeners
        view.getBtnClear().addMouseListener(new SwingResources.CursorChanger(view.getBtnClear()));
        view.getBtnMusicToggle().addMouseListener(new SwingResources.CursorChanger(view.getBtnMusicToggle()));
        view.getBtnSoundToggle().addMouseListener(new SwingResources.CursorChanger(view.getBtnSoundToggle()));

        // focus listeners
        view.getTxtWordInput().addFocusListener(new SwingResources.TextFieldFocus(view.getTxtWordInput(), "Enter word here."));

        view.revalidate();
        view.repaint();

        Thread timer = new Thread(gameTimer());
        timer.start();
    }

    public Runnable gameTimer() {
        Runnable toReturn = new Runnable() {
            @Override
            public void run() {
                try {
                    long duration = model.getDuration();
                    int inSeconds = (int) duration / 1000;
                    System.out.println(inSeconds);
                    while (inSeconds >= 0) {
                        Thread.sleep(1000);
                        // view.setLblTimerTxt(inSeconds);
                        inSeconds -= 1;
                        System.out.println(inSeconds);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        return toReturn;
    }

    /**
     * Turns the music on or off.
     */
    class MusicListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            SwingUtilities.invokeLater(() -> {
                if (musicOn) {
                    view.setBtnSoundIcon(style.iconMusicOff);
                    // TODO: implementation wait lang ahh
                } else {
                    view.setBtnSoundIcon(style.iconMusicOn);
                    // TODO: implementation wait lang ahh
                }
            });
        }
    }

    /**
     * Turns the sound effects on or off.
     */
    class SoundListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            SwingUtilities.invokeLater(() -> {
                if (sfxOn) {
                    view.setBtnSoundIcon(style.iconSoundOff);
                    // TODO: implementation wait lang ahh
                } else {
                    view.setBtnSoundIcon(style.iconSoundOn);
                    // TODO: implementation wait lang ahh
                }
            });
        }
    }

    //TODO: Per user input, store in word set found in model
    //TODO: When timer above elapses, send word set to server using the getRoundWinner method
    //TODO: update entered words shown on game UI

}
