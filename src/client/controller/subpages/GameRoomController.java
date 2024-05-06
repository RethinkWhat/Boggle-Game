package client.controller.subpages;

import client.controller.ClientApplicationController;
import client.model.subpages.GameRoomModel;
import client.view.subpages.GameRoomView;
import server.model.DataPB;
import shared.CustomizedMessageDialog;
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
import java.util.*;
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
     * The round number to update the view.
     */
    private int roundNumber;

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

        roundNumber = 1;
        view.setRoundNumber(roundNumber);

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

        startNextRound();
    }

    private void startNextRound() {
        Thread timer = new Thread(gameTimer());
        timer.start();
    }


    public Runnable gameTimer() {
        Runnable toReturn = new Runnable() {
            @Override
            public void run() {
                try {
                    model.getWordSet().clear();
                    populateLetterSet(model.getLetterList());
                    int inSeconds = (int) model.getWfImpl().getGameDurationVal(model.getGameRoomID() / 1000);
                    System.out.println("IN SECONDS: " + inSeconds);
                    view.setPrgTimerMaxVal(inSeconds);
                    System.out.println(inSeconds);
                    while (inSeconds >= 0) {
                        Thread.sleep(1000);
                        view.setLblTimerTxt(inSeconds);
                        inSeconds = (int) model.getWfImpl().getGameDurationVal(model.getGameRoomID() / 1000);
                        view.setPrgTimerValue(inSeconds);
                        System.out.println(inSeconds);
                        if (inSeconds == 10) {
                            sfxCountdown();
                            SwingUtilities.invokeLater(() -> {
                                view.getPrgTimer().setBackground(style.red);
                                view.getLblTimer().setForeground(style.red);
                            });
                        } else if (inSeconds == 0) {
                            SwingUtilities.invokeLater(() -> {
                                view.getPrgTimer().setBackground(style.goldenTainoi);
                                view.getLblTimer().setForeground(style.white);
                            });
                            model.sendUserWordList();

                            String usernameWinnerRound = model.getWfImpl().getRoundWinner(model.getGameRoomID());
                            String usernameWinnerGame = model.getWfImpl().getOverallWinner(model.getGameRoomID());

                            // displays dialog messages and plays respective sfx.
                            if (!usernameWinnerGame.equals("undecided")) {
                                if (model.getUsername().equals(usernameWinnerGame)) {
                                    sfxWinner();
                                    new CustomizedMessageDialog("Game Winner", style.iconWinner, "YOU WON!",
                                            "You have won the game.", "EXIT GAME", style.deepSkyBlue,
                                            style.goldenTainoi, style.black, style.goldenTainoi, false);
                                } else {
                                    sfxLose();
                                    CustomizedMessageDialog dialog = new CustomizedMessageDialog("Game Winner",
                                            style.iconWinner, "WE HAVE A WINNER!",
                                            usernameWinnerGame + " has won the game.", "EXIT GAME",
                                            style.deepSkyBlue, style.goldenTainoi, style.black,
                                            style.goldenTainoi, false);
                                    dialog.setBtnDialogListener(e -> parent.getView().getCardLayout().show(parent.getView().getPnlCards(), "home"));
                                }
                            } else {
                                sfxRoundOver();
                                if (model.getUsername().equals(usernameWinnerRound)) {
                                    new CustomizedMessageDialog("Round Winner", style.iconWinner, "YOU WON THE ROUND!",
                                            "You had the most points this round.", "NEXT ROUND", style.deepSkyBlue,
                                            style.goldenTainoi, style.black, style.goldenTainoi, false);
                                } else {
                                    new CustomizedMessageDialog("Round Done", style.iconWinner, "ROUND DONE",
                                            usernameWinnerRound + " had the most points this round.",
                                            "NEXT ROUND", style.deepSkyBlue, style.goldenTainoi, style.black,
                                            style.goldenTainoi, false);
                                }
                                model.setLetterList(model.getWfImpl().getNextRoundLetterSet(model.getGameRoomID()));
                                view.setRoundNumber(roundNumber++);
                                startNextRound();
                            }
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

            // TODO: check word text if it conforms to the letter set
            if (!input.contains(" ") && input.length() >= 4) {
                if (!validateInput(input)) {
                    view.setErrorMessage("Input must only contain LETTERS!");
                    view.getTxtWordInput().setText("");
                    sfxBadInput();
                } else {
                    view.addUserInput(model.getUsername(), input);
                    view.updateTxaHeight();
                    model.getWordSet().add(input);
                    view.getTxtWordInput().setText("");
                    sfxGoodInput();
                }
            } else if (input.length() < 4) {
                view.setErrorMessage("Input must be at least 4 CHARACTERS!");
                view.getTxtWordInput().setText("");
                sfxBadInput();
            } else {
                view.setErrorMessage("Input must only be a WORD!");
                view.getTxtWordInput().setText("");
                sfxBadInput();
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
        return model.getWfImpl().isValidWord(input);
    }

    /**
     * Populates the letter set in the view by adding the elements of the specified vowel set and consonant set, then
     * randomizing its order of appearance.
     * @param letterList the specified letter list containing the random vowels and consonants.
     */
    private void populateLetterSet(String letterList) {
        List<String> letterSet = new ArrayList<>();
        Color backgroundColor;

        for (int i = 0; i < letterList.length(); i++) {
            letterSet.add(letterList.substring(i, i + 1));
        }

        Collections.shuffle(letterSet);

        for (String letter : letterSet) {
            backgroundColor = letter.matches("[AEIOU]") ? style.deepSkyBlue : style.goldenTainoi;
            view.addLetterToLetterSet(letter, backgroundColor);
        }
    }

    /**
     * Plays the bad input sfx.
     */
    private void sfxBadInput() {
        if (sfxOn) {
            try {
                sfxClip.stop();
                audioSoundStream = AudioSystem.getAudioInputStream(new File(badInput));
                sfxClip = AudioSystem.getClip();
                sfxClip.open(audioSoundStream);
                sfxClip.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Plays the good input sfx.
     */
    private void sfxGoodInput() {
        if (sfxOn) {
            try {
                sfxClip.stop();
                audioSoundStream = AudioSystem.getAudioInputStream(new File(goodInput));
                sfxClip = AudioSystem.getClip();
                sfxClip.open(audioSoundStream);
                sfxClip.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Plays the countdown sfx.
     */
    private void sfxCountdown() {
        if (sfxOn) {
            try {
                sfxClip.stop();
                audioSoundStream = AudioSystem.getAudioInputStream(new File(countdown));
                sfxClip = AudioSystem.getClip();
                sfxClip.open(audioSoundStream);
                sfxClip.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Plays the round over sfx.
     */
    private void sfxRoundOver() {
        if (sfxOn) {
            try {
                sfxClip.stop();
                audioSoundStream = AudioSystem.getAudioInputStream(new File(roundOver));
                sfxClip = AudioSystem.getClip();
                sfxClip.open(audioSoundStream);
                sfxClip.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Plays the winner sfx.
     */
    private void sfxWinner() {
        if (sfxOn) {
            try {
                sfxClip.stop();
                audioSoundStream = AudioSystem.getAudioInputStream(new File(win));
                sfxClip = AudioSystem.getClip();
                sfxClip.open(audioSoundStream);
                sfxClip.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Plays the lose sfx.
     */
    private void sfxLose() {
        if (sfxOn) {
            try {
                sfxClip.stop();
                audioSoundStream = AudioSystem.getAudioInputStream(new File(lose));
                sfxClip = AudioSystem.getClip();
                sfxClip.open(audioSoundStream);
                sfxClip.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
