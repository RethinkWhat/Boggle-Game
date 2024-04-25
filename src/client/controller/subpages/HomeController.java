package client.controller.subpages;

import client.controller.ClientApplicationController;
import client.model.subpages.HomeModel;
import client.model.subpages.LobbyModel;
import client.view.subpages.HomeView;
import shared.SwingResources;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * The home controller processes user requests in joining the game, changing player profile picture, viewing
 * the global leaderboard and viewing the tutorial.
 */
public class HomeController {
    /**
     * The parent controller.
     */
    private ClientApplicationController parent;
    /**
     * The view.
     */
    private HomeView view;
    /**
     * The model.
     */
    private HomeModel model;
    /**
     * The audio input stream for music.
     */
    private AudioInputStream audioMusicStream;
    /**
     * The audio input stream for sfx.
     */
    private AudioInputStream audioSoundStream;
    /**
     * The music clip.
     */
    private Clip musicClip;
    /**
     * The SFX clip.
     */
    private Clip sfxClip;

    /**
     * Constructs a HomeController with a specified view and model.
     * @param view The specified view.
     * @param model The specified model.
     */
    public HomeController(HomeView view, HomeModel model, ClientApplicationController parent) {
        this.view = view;
        this.model = model;
        this.parent = parent;

        // action listeners
        view.setJoinListener(new JoinGameListener());

        // mouse listeners
        view.getBtnJoinGame().addMouseListener(new SwingResources.CursorChanger(view.getBtnJoinGame()));
        view.getBtnEditPfp().addMouseListener(new SwingResources.CursorChanger(view.getBtnEditPfp()));
        view.getBtnTutorial().addMouseListener(new SwingResources.CursorChanger(view.getBtnTutorial()));
    }

    /**
     * Processes the joining of a lobby
     */
    class JoinGameListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            SwingUtilities.invokeLater(() -> {
                parent.getView().showLobby();
                parent.getView().setNavLocationText("Lobby");System.out.println("creating new lobby");

                new LobbyController(new LobbyModel(parent.getModel().getUsername(), parent.getModel().getWfImpl()),
                        parent.getView().getLobbyView(), parent.getView());

                parent.getView().getLobbyView().setExitLobbyListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
//                      parent.getModel().getWfImpl().exitGameRoom(model.getUsername());
                        parent.getView().showHome();
                        parent.getView().setNavLocationText("Home");
                    }
                });

                // Add action listener to the exit lobby button
                /*
                BooleanHolder startGame = new BooleanHolder(false);
                long id;
                while (!startGame.value) {
                    id = model.getWfImpl().attemptJoin("username", startGame);
                    System.out.println("TIME REMAINING: " + id);
                    if (startGame.value) {

                        break;
                    }try {
                        Thread.sleep(1000);
                    } catch (Exception p) {
                    p.printStackTrace();}
                }
                */
            });
        }
    }

    /**
     * Initializes the music while in the home page.
     */
    public void initializeHomeMusic() {
        String musicPath = "res/audio/music/8-bit-arcade-mode-158814.wav";
        try {
            audioMusicStream = AudioSystem.getAudioInputStream(new File(musicPath).getAbsoluteFile());
            musicClip = AudioSystem.getClip();
            musicClip.open(audioMusicStream);
            musicClip.start();
            musicClip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
