package client.controller;

import client.controller.subpages.GameRoomController;
import client.controller.subpages.HomeController;
import client.controller.subpages.HowToPlayController;
import client.controller.subpages.SettingsController;
import client.model.ClientApplicationModel;
import client.model.subpages.GameRoomModel;
import client.model.subpages.HomeModel;
import client.model.subpages.HowToPlayModel;
import client.model.subpages.SettingsModel;
import client.view.ClientApplicationView;
import shared.ExitDialog;
import shared.SwingResources;
import shared.SwingStylesheet;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.SQLException;

/**
 * The ClientApplicationController controls application navigation and holds all the sub-controllers that control
 * their respective subviews and submodels. This class also controls the music and sfx playback.
 */
public class ClientApplicationController {
    /**
     * The view.
     */
    private ClientApplicationView view;
    /**
     * The model
     */
    private ClientApplicationModel model;
    /**
     * The home controller.
     */
    private HomeController homeController;
    /**
     * The how to play controller.
     */
    private HowToPlayController howToPlayController;
    /**
     * The setting controller.
     */
    private SettingsController settingsController;
    /**
     * The audio input stream for music.
     */
    private AudioInputStream audioMusicStream;
    /**
     * The audio input stream for sfx.
     */
    private AudioInputStream audioSfxStream;
    /**
     * The music clip.
     */
    private Clip musicClip;
    /**
     * The SFX clip.
     */
    private Clip sfxClip;
    /**
     * The default music file path.
     */
    private String defaultMusic = "res/audio/music/8-bit-arcade-mode-158814.wav";
    /**
     * The lobby music file path.
     */
    private String lobbyMusic = "res/audio/music/loading-screen-music.wav";
    /**
     * The game music file path.
     */
    private String gameMusic = "res/audio/music/gameroom-music-v2.wav";

    /**
     * Constructs a ClientApplicationController with a specified view.
     * @param view The specified view.
     */
    public ClientApplicationController(ClientApplicationView view, ClientApplicationModel model) {
        this.model = model;
        this.view = view;

        try {
            musicClip = AudioSystem.getClip();
            sfxClip = AudioSystem.getClip();
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            homeController = new HomeController(view.getHomeView(), new HomeModel(model.getUsername(), model.getWfImpl()), this);
            howToPlayController = new HowToPlayController(view.getHowToPlayView(), new HowToPlayModel(), this);
            try {
                settingsController = new SettingsController(view.getSettingsView(),
                        new SettingsModel(model.getUsername(), model.getWfImpl()), view);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            new GameRoomController(new GameRoomModel(model.getUsername(), model.getWfImpl()), view.getGameRoomView(), this);
        });

        // action listeners
        view.setSettingsListener(new SettingsListener());
        view.setHomeListener(new HomeListener());
        view.setLogoutListener(new LogOutListener());

        // mouse listeners
        view.getBtnNavHome().addMouseListener(new SwingResources.CursorChanger(view.getBtnNavHome()));
        view.getBtnNavSettings().addMouseListener(new SwingResources.CursorChanger(view.getBtnNavSettings()));
        view.getBtnNavLogout().addMouseListener(new SwingResources.CursorChanger(view.getBtnNavLogout()));

        view.revalidate();
        view.repaint();
    }

    /**
     * Navigates the application to the SettingsView.
     */
    class SettingsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Switch to the settings view
            SwingUtilities.invokeLater(() -> {
                // new SettingsController(new SettingsView(), new SettingsModel());
            });
            view.showSettings();
            view.setNavLocationText("Settings");
        }
    }

    /**
     * Navigates the application to the HomeView.
     */
    class HomeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.showHome();
            view.setNavLocationText("Home");
        }
    }

    /**
     * Logs the user out of the application.
     */
    class LogOutListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            SwingStylesheet style = new SwingStylesheet();
            ExitDialog exitDialog = new ExitDialog(
                    "Exit Confirmation",
                    new ImageIcon("res/drawable/icons/alert-red-solid.png"),
                    "EXIT CONFIRMATION",
                    "Are you sure you want to exit the game?.",
                    "EXIT",
                    style.red,
                    style.white,
                    style.black,
                    style.red
            );
        }
    }

    /**
     * Plays the default music for the home and settings page.
     */
    public void playDefaultMusic() {
        try {
            musicClip.stop();
            audioMusicStream = AudioSystem.getAudioInputStream(new File(defaultMusic).getAbsoluteFile());
            musicClip = AudioSystem.getClip();
            musicClip.open(audioMusicStream);
            musicClip.start();
            musicClip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Plays the lobby music.
     */
    public void playLobbyMusic() {
        try {
            musicClip.stop();
            audioMusicStream = AudioSystem.getAudioInputStream(new File(lobbyMusic).getAbsoluteFile());
            musicClip = AudioSystem.getClip();
            musicClip.open(audioMusicStream);
            musicClip.start();
            musicClip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Plays the game room music.
     */
    public void playGameMusic() {
        try {
            musicClip.stop();
            audioMusicStream = AudioSystem.getAudioInputStream(new File(gameMusic).getAbsoluteFile());
            musicClip = AudioSystem.getClip();
            musicClip.open(audioMusicStream);
            musicClip.start();
            musicClip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Stops the current music.
     */
    public void stopMusic() {
        musicClip.stop();
    }

    /**
     * Retrieves the current ClientApplicationView.
     * @return The current view.
     */
    public ClientApplicationView getView() {
        return view;
    }

    /**
     * Retrieves the current ClientApplicationModel.
     * @return The curren model.
     */
    public ClientApplicationModel getModel() {
        return model;
    }

    /**
     * Retrieves the current Clip of sfxClip.
     * @return The current sfxClip.
     */
    public Clip getSfxClip() {
        return sfxClip;
    }

    /**
     * Retrieves the current Clip of musicClip.
     * @return The current musicClip.
     */
    public Clip getMusicClip() {
        return musicClip;
    }
}
