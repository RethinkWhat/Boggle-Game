
package server.model;

import java.sql.Time;
import java.util.*;
import java.util.stream.Collectors;

import org.omg.CORBA.BooleanHolder;
import org.omg.CORBA.IntHolder;
import org.omg.CORBA.LongHolder;
import org.omg.CORBA.StringHolder;
import server.model.BoggleApp.*;

public class ServerImplementation extends BoggleClientPOA {

    private ArrayList<String> loggedIn = new ArrayList<>();

    /**
     * Lobby Variables
     */
    private long lobbyTimerValue = 10000L;
    private GameTimer lobbyTimer = new GameTimer(0, lobbyTimerValue);
    private ArrayList<String> currLobby = new ArrayList<>();


    private long roundDuration = 180000L;
    public ArrayList<GameTimer> ongoingGameTimers;


    /**
     * Default constructor for server implementation object
     */
    public ServerImplementation() {
        DataPB.setCon();
        ongoingGameTimers = new ArrayList<>();

    }

    /**
     * Handles the validation of an account upon logging in
     * @param var1
     * @param var2
     * @throws accountLoggedIn
     * @throws accountDoesNotExist
     */
    public void validateAccount(String var1, String var2) throws accountLoggedIn, accountDoesNotExist {
        if (!DataPB.validateAccount(var1,var2))
            throw new accountDoesNotExist();
        if (loggedIn.contains(var1))
            throw new accountLoggedIn();
    }

    /**
     * Method that handles a user's attempt to join a game
     * Creates the waiting room and is where the timer value is set
     * @param username
     */
    public synchronized void attemptJoin(String username) {
        if (lobbyTimer.getCurrTimerValue() == 0L) {
            lobbyTimer = new GameTimer(0, lobbyTimerValue);
            currLobby = new ArrayList<>();
        } if (lobbyTimer.getCurrTimerValue() == lobbyTimerValue)
            startLobbyTime();
        currLobby.add(username);
    }

    /**
     * Method to get all the users that are in the waiting lobby alongside their pfp address
     * @return
     */
    @Override
    public synchronized LobbyUser[] getLobbyMembers() {
        LobbyUser[] lobbyUsers = new LobbyUser[currLobby.size()];
        for (int x =0; x < currLobby.size(); x++)
            lobbyUsers[x] = new LobbyUser(currLobby.get(x), DataPB.getPFPOfUser(currLobby.get(x)));
        return lobbyUsers;
    }

    /**
     * Method to return the current lobby timer value.
     *
     * The validLobby parameter acts as a way of validating whether there is more
     * than 1 person in the lobby.
     * @param validLobby
     * @return currTimerValue
     */
    // @Override
    public long getCurrLobbyTimerValue(BooleanHolder validLobby) {
        validLobby.value = currLobby.size() > 1;
        return lobbyTimer.getCurrTimerValue();
    }

    @Override
    public int getGameID(String username) {
        return DataPB.getGameID(username);
    }

    /**
     * Method to handle creating a game room and populating database
     * @param players
     * @return
     */
    private void joinGameRoom(ArrayList<String> players) {

        int gameRoomID =  DataPB.createGameRoom(new Time(roundDuration));

        String letters = createRandomLetterSet();
        int roundID = DataPB.createRound(letters);
        for (String player : players) {
            DataPB.createRoundDetails(gameRoomID,roundID, 1, player);
        }

        startTimerForRound(roundID,roundDuration);
        ongoingGameTimers.add(new GameTimer(gameRoomID, lobbyTimerValue));
    }

    public String getLetters(int gameID) {
        return DataPB.getLetters(gameID).toString();
    }


    @Override
    /**
     * Method to get the current timer value of an ongoing game
     */
    public long getGameDurationVal(int gameID) {
        for (GameTimer ongoingGameTimer : ongoingGameTimers) {
            if (ongoingGameTimer.getID() == gameID) {
                return ongoingGameTimer.getCurrTimerValue();
            }
        }
        return -1;
    }

    /**
     * Acts as a way the user can send the word list at the end of a round
     * @param gameID
     * @param username
     * @param wordList
     */
    @Override
    public void sendUserWordList(int gameID, String username, String[] wordList) {
        DataPB.addUserWordList(username, gameID,wordList);
        //TODO: do solving and store points in database

    }

    /**
     * Method to get the winner of a specific round in a game.
     * Call this method after every round to show who won finished round.
     * @param gameID
     * @return
     */
    @Override
    public String getRoundWinner(int gameID) {
        return DataPB.getWinnerOfLatestRound(gameID);
    }


    /**
     *  Method to get the letter set of the next round
     */
    public String getNextRoundLetterSet(int gameID) {
        synchronized (this) {
            if (!DataPB.roundOngoing(gameID)) {
                String letters = createRandomLetterSet();
                DataPB.createRound(letters);
                int roundID = DataPB.createRound(letters);

                ArrayList<String> players = DataPB.getPlayersInGame(gameID);
                for (String player : players) {
                    DataPB.createRoundDetails(gameID, roundID, 1, player);
                }
                return letters;
            }
        }
        return DataPB.getLetters(gameID).toString();
    }


    /**
     * If a user happens to exit the game while the game ongoing
     * @param username
     */
    @Override
    public void exitGameRoom(String username) {
        //TODO: Define implementation
    }



    /**
     * Method to get the overall winner of a GAME if there is any. Otherwise, the method returns undecided.
     * Call this method after every round to check if there is already an overall winner.
     * @param gameId
     * @return winnerUsername
     */
    @Override
    public String getOverallWinner(int gameId) {
        DataPB.getGameWinner(gameId);
        return "undecided";
    }

    /**
     * Method to
     * @return
     */
    @Override
    public userInfo[] getLeaderboard() {
        Map<String, Integer> userPointsMap = DataPB.getTopPlayers();
        userInfo[] toReturn = new userInfo[userPointsMap.size()];
        int x =0;
        for (String key: userPointsMap.keySet()) {
            userInfo curr = new userInfo(key, DataPB.getPFPOfUser(key), userPointsMap.get(key));
            toReturn[x] = curr;
            x++;
        }
        return toReturn;
    }

    /**
     * Method to handle editing the personal information of a given user
     * @param username
     * @param toEdit
     * @param newInfo
     * @throws updateFailed
     */
    @Override
    public void editInfo(String username, String toEdit, String newInfo) throws updateFailed {
        try {
            DataPB.editInfo(username, toEdit, newInfo);
        } catch (Exception e) {
            throw new updateFailed();
        }
    }

    /**
     * Method to handle editing the password of a given account. Also, validates whether the old password input is correct,
     * prior to password change
     * @param username
     * @param oldPass
     * @param newPass
     * @throws updateFailed
     */
    @Override
    public void editPassword(String username, String oldPass, String newPass) throws updateFailed {
        try {
            DataPB.editPassword(username,oldPass,newPass);
        } catch (Exception e) {
            throw new updateFailed();
        }
    }


    /**
     * Method to handle getting the points the user was able to accumulate throughout the round THUS FAR
     * @param username
     * @return
     */
    @Override
    public int getUserTotalPoints(String username) {
        return DataPB.getUserGamePoints(username);
    }


    /**
     * Method to handle getting the points the user has collected thus far for an ongoing game
     * @param gameID
     * @param username
     * @return
     */
    @Override
    public int getUserPointsOngoingGame(int gameID, String username) {
        return DataPB.getUserRoundPoints(gameID, username);
    }

    /**
     * Method to handle getting the number of matches a user has played
     * @param username
     * @return
     */
    @Override
    public int getNumberOfMatches(String username) {
        return DataPB.getMatches(username);
    }

    /**
     * Method to handle getting the wins a specific user has
     * @param username
     * @return
     */
    @Override
    public int getNumberOfWins(String username) {
        return DataPB.getWins(username);
    }

    /**
     * Method to get the leaderboard of an ongoing game
     * @param gameID
     * @return
     */
    @Override
    public  userInfo[] getCurrGameLeaderboard(int gameID) {
        List<userInfo> leaderboard = new ArrayList<>();
        return leaderboard.toArray(new userInfo[0]);
    }




    /** END of IDL methods */




    /** PRIVATE Methods */

    private void startLobbyTime() {
        Thread t = new Thread(lobbyTimer);
        t.start();
    }

    private void startTimerForRound(int gameID, long duration) {
        GameTimer gameTimer = new GameTimer(gameID, duration);
        Thread t = new Thread(gameTimer);
        ongoingGameTimers.add(gameTimer);
        t.start();
    }

    private static String createRandomLetterSet() {
        int vowelCount= 7;
        int consonantCount = 13;
        String vowels = "AEIOU";
        String consonants = "BCDFGHJKLMNPQRSTVWXYZ";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < vowelCount; i++) {
            int index = random.nextInt(vowels.length());
            sb.append(vowels.charAt(index));
        }
        for (int i = 0; i < consonantCount; i++) {
            int index = random.nextInt(consonants.length());
            sb.append(consonants.charAt(index));
        }
        for (int i = sb.length() - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            char a = sb.charAt(index);
            sb.setCharAt(index, sb.charAt(i));
            sb.setCharAt(i, a);
        }
        return sb.toString();
    }


    private String createRandomVowelSet() {
        String vowel = "AEIOU";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for(int i = 0; i < 7; i++){
            int index= random.nextInt(vowel.length());
            sb.append(vowel.charAt(index));
        }
        System.out.println(sb.toString());
        return sb.toString();
    }

    private static String createRandomConsonantSet() {
        String consonant = "BCDFGHJKLMNPQRSTVWXYZ";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 13; i++) {
            int index = random.nextInt(consonant.length());
            sb.append(consonant.charAt(index));
        }
        System.out.println(sb.toString());
        return sb.toString();
    }


    /*
    public List<HashMap<String, List<String>>> storeWordList(List<List<String>> wordList) {
        for (String user : loggedIn) {
            HashMap<String, List<String>> userWordMap = new HashMap<>();

        }
        String username = null;
        List<String> words = new ArrayList<>();

        for (List<String> elements : wordList) {
            username = details.get(0);
            words.addAll(elements.get(1));
        }

        userWordMap.put(username, words);
    }

     */

    /**
     * Compares and cleans the specified userWordMapList by:
     *  1. Compiling all the word lists in one list/
     *  2. Getting the duplicates and adding it into a list.
     *  3. Set a current word from a user word list.
     *  4.
     *  5. If the word exists, it will be removed from all the word lists.
     *  6. Repeat Step 1.
     * @param userWordMapList The list of userWordMap (user as key, word list as value)
     * @return The cleaned compiledWordLists.
     */
    public List<Map<String, List<String>>> compareAllWordLists(List<Map<String, List<String>>> userWordMapList) {
        List<Map<String, List<String>>> cleanedUserWordList = new ArrayList<>();
        List<String> allWordsFromAllPlayers = new ArrayList<>();

        // adds all the elements of the players' word list in one list.
        for (Map<String, List<String>> userWordMap : userWordMapList) {
            for (Map.Entry<String, List<String>> entry : userWordMap.entrySet()) {
                allWordsFromAllPlayers.addAll(entry.getValue());
            }
        }

        // gets duplicate values from allWordFromAllPlayers list
        HashSet<String> uniqueWords = new HashSet<>();
        List<String> duplicates = allWordsFromAllPlayers.stream()
                .filter(w -> !uniqueWords.add(w))
                .collect(Collectors.toList());

        // checks for duplicates and removes ("cleans") the duplicate words from the player's word list
        for (Map<String, List<String>> userWordMap : userWordMapList) {
            for (Map.Entry<String, List<String>> entry : userWordMap.entrySet()) {
                String username = entry.getKey();
                List<String> currUserWordList = entry.getValue();
                for (String currWord : currUserWordList) {
                    for (String duplicateWord : duplicates) {
                        if (currWord.equals(duplicateWord)) {
                            currUserWordList.remove(duplicateWord);
                        }
                    }
                }
                Map<String, List<String>> cleanedUserWordMap = new HashMap<>();
                cleanedUserWordMap.put(username, currUserWordList);
                cleanedUserWordList.add(cleanedUserWordMap);
            }
        }
        return cleanedUserWordList;
    }

    /**
     * After the word lists have undergone comparison and cleaning, invoke this method.
     * Retrieves the total score of the user in a specified round by getting the length of the elements of the
     * specified "cleaned" word list.
     * Score for each word will be based on its length
     * @param userWordMap The current user with their word list.
     * @return The total score of the user of the specified round.
     */
    public int computeTotalScore(Map<String, List<String>> userWordMap) {
        int totalScore = 0;

        for (Map.Entry<String, List<String>> entry : userWordMap.entrySet()) {
            List<String> currUserWordList = entry.getValue();
            for (String word : currUserWordList) {
                totalScore += word.length();
            }
        }

        return totalScore;
    }



    /**
     * Method to handle setting the duration a game will go on for
     * @param time : long
     */
    public void setRoundDuration(long time) {
        roundDuration = time;
    }

    /**
     * Method to handle setting the duration the waiting lobby will last
     * @param time
     */
    public void setLobbyTimerValue(long time) {
        lobbyTimerValue = time;
    }



}

