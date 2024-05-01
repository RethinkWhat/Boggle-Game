package client.controller.subpages;

import client.controller.ClientApplicationController;
import client.model.subpages.GameRoomModel;
import client.view.subpages.GameRoomView;
import shared.SwingResources;
import shared.SwingStylesheet;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * The GameRoomController processes user requests for specifying their inputs, computing and comparing scores,
 * handling the local leaderboard, and concluding the current round and game.
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
     * The parent controller.
     */
    private ClientApplicationController parent;
    /**
     * Specifies whether the music is turned on or off.
     */
    private boolean musicOn;
    /**
     * Specifies whether the sound effects are turned on or off.
     */
    private boolean sfxOn;
    /**
     * The audio input stream for sfx.
     */
    private AudioInputStream audioSoundStream;
    /**
     * The SFX clip.
     */
    private Clip sfxClip;
    /**
     * The stylesheet.
     */
    private final SwingStylesheet style = new SwingStylesheet();
    /**
     * The bad input sfx file path.
     */
    private final String badInput = "res/audio/sfx/bad-input-sfx.wav";
    /**
     * The good input sfx file path.
     */
    private final String goodInput = "res/audio/sfx/good-input-sfx.wav";
    /**
     * The countdown sfx file path.
     */
    private final String countdown = "res/audio/sfx/countdown-10s-sfx.wav";
    /**
     * The round over sfx file path.
     */
    private final String roundOver = "res/audio/sfx/round-over-sfx.wav";
    /**
     * The lose sfx file path.
     */
    private final String lose = "res/audio/sfx/lose-sfx.wav";
    /**
     * The win sfx file path.
     */
    private final String win = "res/audio/sfx/winner-sfx.wav";

    /**
     * Constructs a GameRoomController with a specified view, model, and parent controller.
     *
     * @param model The specified model.
     * @param view  The specified view.
     */
    public GameRoomController(GameRoomModel model, GameRoomView view, ClientApplicationController parent) {
        this.model = model;
        this.view = view;
        this.parent = parent;

        musicOn = true; // music on by default
        sfxOn = true; // sfx on by default.

        // action listeners
        view.setMusicToggleListener(new MusicListener());
        view.setSoundToggleListener(new SoundListener());
        view.setInputListener(new InputListener());
        view.setClearListener(e -> {
            view.getTxtWordInput().setText("");
            view.setErrorMessage("");
        });

        // mouse listeners
        view.getBtnClear().addMouseListener(new SwingResources.CursorChanger(view.getBtnClear()));
        view.getBtnMusicToggle().addMouseListener(new SwingResources.CursorChanger(view.getBtnMusicToggle()));
        view.getBtnSoundToggle().addMouseListener(new SwingResources.CursorChanger(view.getBtnSoundToggle()));

        // focus listeners
        view.getTxtWordInput().addFocusListener(new SwingResources.TextFieldFocus(view.getTxtWordInput(),
                "Enter word here.", view.getLblErrorMessage()));

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
                    view.setPrgTimerMaxVal(inSeconds);
                    System.out.println(inSeconds);
                    while (inSeconds >= 0) {
                        Thread.sleep(1000);
                        view.setLblTimerTxt(inSeconds);
                        inSeconds -= 1;
                        view.setPrgTimerValue(inSeconds);
                        System.out.println(inSeconds);
                        if (inSeconds == 10) {
                            playSFX("countdown");
                            SwingUtilities.invokeLater(() -> {
                                view.getPrgTimer().setBackground(style.red);
                                view.getLblTimer().setForeground(style.red);
                            });
                        } else if (inSeconds == 0) {
                            playSFX("countdown");
                            SwingUtilities.invokeLater(() -> {
                                view.getPrgTimer().setBackground(style.goldenTainoi);
                                view.getLblTimer().setForeground(style.white);


                               // model.getWfImpl().getRoundWinner()
                                // TODO:  CustomizedMessageDialog to show winner
                                // TODO: Call model.wfImpl.getOverallWinner if return value != "invalid" game has ended and we can declare winner
                                // TODO: else
                                    // TODO: Call model.nextRound()
                                     // TODO: Start thread for next round... Thread timer = new Thread(gameTimer());
                                     //TODO: timer.start();
                            });
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        return toReturn;
    }

    /**
     * Processes player inputting a word.
     */
    class InputListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.setErrorMessage("");
            String input = view.getTxtWordInput().getText().trim();

            // TODO: compare word to valid word list. If valid, accept; else, reject.
            if (!input.contains(" ") && input.length() >= 4) {
                if (!validateInput(input)) {
                    view.setErrorMessage("Input must only contain LETTERS!");
                    view.getTxtWordInput().setText("");
                    playSFX("badInput");
                } else {
                    view.addUserInput(model.getUsername(), input);
                    view.updateTxaHeight();
                    model.getWordSet().add(input);
                    view.getTxtWordInput().setText("");
                    playSFX("goodInput");
                }
            } else if (input.length() < 4) {
                view.setErrorMessage("Input must be at least 4 CHARACTERS!");
                view.getTxtWordInput().setText("");
                playSFX("badInput");
            } else {
                view.setErrorMessage("Input must only be a WORD!");
                view.getTxtWordInput().setText("");
                playSFX("badInput");
            }
        }
    }

    /**
     * Turns the music on or off.
     */
    class MusicListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            SwingUtilities.invokeLater(() -> {
                if (musicOn) {
                    musicOn = false;
                    view.setBtnMusicIcon(style.iconMusicOff);
                    parent.getMusicClip().getMicrosecondPosition();
                    parent.getMusicClip().stop();
                } else {
                    musicOn = true;
                    view.setBtnMusicIcon(style.iconMusicOn);
                    parent.getMusicClip().getMicrosecondPosition();
                    parent.getMusicClip().start();
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
                    sfxOn = false;
                    view.setBtnSoundIcon(style.iconSoundOff);
                    try {
                        audioSoundStream.reset();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    sfxOn = true;
                    view.setBtnSoundIcon(style.iconSoundOn);
                }
            });
        }
    }

    /**
     * Validates each character of the input string to ensure that it is a valid word.
     *
     * @param input The specified player input.
     * @return True if valid letter, false if any other character.
     */
    private boolean validateInput(String input) {
        for (int i = 0; i < input.length(); i++) {
            if (!Character.isLetter(input.charAt(i)) || Character.isWhitespace(input.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * TODO: To debug
     * Plays an SFX based on the specified type.
     *
     * @param type The specified SFX type to play.
     */
    private void playSFX(String type) {
        if (sfxOn) {
            try {
                audioSoundStream = AudioSystem.getAudioInputStream(new File(badInput));
                sfxClip = AudioSystem.getClip();
                sfxClip.open(audioSoundStream);
                sfxClip.stop();
                switch (type) {
                    case "badInput": {
                        sfxClip.stop();
                        audioSoundStream = AudioSystem.getAudioInputStream(new File(badInput));
                        sfxClip = AudioSystem.getClip();
                        sfxClip.open(audioSoundStream);
                        sfxClip.start();
                    }
                    case "goodInput": {
                        sfxClip.stop();
                        audioSoundStream = AudioSystem.getAudioInputStream(new File(goodInput));
                        sfxClip = AudioSystem.getClip();
                        sfxClip.open(audioSoundStream);
                        sfxClip.start();
                    }
                    case "countdown": {
                        sfxClip.stop();
                        audioSoundStream = AudioSystem.getAudioInputStream(new File(countdown));
                        sfxClip = AudioSystem.getClip();
                        sfxClip.open(audioSoundStream);
                        sfxClip.start();
                    }
                    case "roundOver": {
                        sfxClip.stop();
                        audioSoundStream = AudioSystem.getAudioInputStream(new File(roundOver));
                        sfxClip = AudioSystem.getClip();
                        sfxClip.open(audioSoundStream);
                        sfxClip.start();
                    }
                    case "winner": {
                        sfxClip.stop();
                        audioSoundStream = AudioSystem.getAudioInputStream(new File(win));
                        sfxClip = AudioSystem.getClip();
                        sfxClip.open(audioSoundStream);
                        sfxClip.start();
                    }
                    case "lose": {
                        sfxClip.stop();
                        audioSoundStream = AudioSystem.getAudioInputStream(new File(lose));
                        sfxClip = AudioSystem.getClip();
                        sfxClip.open(audioSoundStream);
                        sfxClip.start();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Creates an ArrayList containing the username, the gameID and the roundNumber, and the user's word set.
     * The list will be sent to the server for score comparison.
     * The list is in the following format:
     * [[username, gameID, roundNumber], [word1, word2, word3, word4, word5, ...]]
     * @return The main list containing the username, gameId, roundNumber, and the word set.
     */
    public List<List<String>> getCurrentRoundWordList(int roundNumber) {
        List<List<String>> mainList = new ArrayList<>();
        List<String> roundDetails = new ArrayList<>();
        List<String> wordList = new ArrayList<>();

        roundDetails.add(model.getUsername());
        roundDetails.add(String.valueOf(model.getGameRoomID()));
        roundDetails.add(String.valueOf(roundNumber));

        wordList.add(Arrays.toString(model.getWordSet().toArray()));

        mainList.add(roundDetails);
        mainList.add(wordList);

        return mainList;
    }

    /**
     * Populates the letter set in the view by adding the elements of the specified vowel set and consonant set, then
     * randomizing its order of appearance.
     * @param vowelSet The specified vowel set.
     * @param consonantSet The specified consonant set.
     */
    private void populateLetterSet(char[] vowelSet, char[] consonantSet) {
        List<String> letterSet = new ArrayList<>();
        letterSet.add(Arrays.toString(vowelSet));
        letterSet.add(Arrays.toString(consonantSet));
        Color backgroundColor;

        Collections.shuffle(letterSet);

        for (String letter : letterSet) {
            backgroundColor = letter.matches("[AEIOU]") ? style.deepSkyBlue : style.goldenTainoi;
            view.addLetterToLetterSet(letter, backgroundColor);
        }
    }
}
