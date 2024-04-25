package client.controller;

import client.controller.subpages.LobbyController;
import client.model.ClientApplicationModel;
import client.model.subpages.LobbyModel;
import client.view.ClientApplicationView;
import shared.SwingResources;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

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
     * The audio input stream for music.
     */
    private AudioInputStream audioMusicStream;
    /**
     * The audio input stream for sfx.
     */
    private AudioInputStream audioSoundStream;

    /**
     * Constructs a ClientApplicationController with a specified view.
     * @param view The specified view.
     */
    public ClientApplicationController(ClientApplicationView view, ClientApplicationModel model) {
        this.model = model;
        this.view = view;

        initializeMusic();

        // action listeners
        this.view.getHomeView().setJoinListener(new JoinGameListener());

        // mouse listeners
        view.getBtnNavHome().addMouseListener(new SwingResources.CursorChanger(view.getBtnNavHome()));
        view.getBtnNavSettings().addMouseListener(new SwingResources.CursorChanger(view.getBtnNavSettings()));
        view.getBtnNavLogout().addMouseListener(new SwingResources.CursorChanger(view.getBtnNavLogout()));


    }

    public class JoinGameListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            SwingUtilities.invokeLater(() -> {
                view.showLobby();
                view.setNavLocationText("Lobby");System.out.println("creating new lobby");
                new LobbyController(new LobbyModel(model.getUsername(), model.getWfImpl()), view.getLobbyView(), view);view.getLobbyView().setExitLobbyListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
//                    model.getWfImpl().exitGameRoom(model.getUsername());
                    view.showHome();
                    view.setNavLocationText("Home");

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

    public void initializeMusic() {
        String musicPath = "res/audio/music/lobby-music-v2.wav";
        try {
            audioMusicStream = AudioSystem.getAudioInputStream(new File(musicPath).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioMusicStream);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
