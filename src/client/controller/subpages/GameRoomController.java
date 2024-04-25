package client.controller.subpages;

import client.model.subpages.GameRoomModel;
import client.view.subpages.GameRoomView;
import shared.SwingResources;
import shared.SwingStylesheet;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * The GameRoomController processes the gameplay.
 */
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

    /**
     * Constructs a GameRoomController with a specified view and model.
     * @param view The specified view.
     * @param model The specified model.
     */
    public GameRoomController(GameRoomView view, GameRoomModel model) {
        this.view = view;
        this.model = model;

        boolean musicOn = true; // default music on
        boolean musicOff = true; // default sfx on

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
}
