
package server.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.*;
import java.util.stream.Collectors;

import org.omg.CORBA.BooleanHolder;
import server.model.BoggleApp.*;

public class ServerImplementation extends BoggleClientPOA {

    private ArrayList<String> loggedIn = new ArrayList<>();

    /**
     * Lobby Variables
     */
    private long lobbyTimerValue = 10000L;
//    private Timer lobbyTimer = new Timer(0, lobbyTimerValue);
    private ArrayList<String> currLobby = new ArrayList<>();


    private long roundDuration = 30000L;
    public ArrayList<Timer> ongoingGameTimers;


    private long currLobbyTimerValue = lobbyTimerValue;

    /**
     * Default constructor for server implementation object
     */
    public ServerImplementation() {
        DataPB.setCon();
        ongoingGameTimers = new ArrayList<Timer>();

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
        else loggedIn.add(var1);

    }

    /**
     * Method that handles a user's attempt to join a game
     * Creates the waiting room and is where the timer value is set
     * @param username
     */
    public synchronized void attemptJoin(String username) {
        System.out.println("attempting join");
        if (currLobbyTimerValue == lobbyTimerValue) {
            System.out.println("starting lobby");
            startLobbyTime();
        }
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
        return currLobbyTimerValue;
    }

    @Override
    public int getGameID(String username) {
        return DataPB.getGameID(username);
    }

    @Override
    public String getLetters(int gameID) {
        return DataPB.getLetters(gameID);
    }

    /**
     * Method to handle creating a game room and populating database
     * @param players
     * @return
     */
    private void joinGameRoom(ArrayList<String> players) {

        int gameRoomID =  DataPB.createGameRoom(new Time(roundDuration));
        System.out.println("created game room " + gameRoomID);

        String letters = createRandomLetterSet();
        int roundID = DataPB.createRound(letters);
        for (String player : players) {
            DataPB.createRoundDetails(gameRoomID,roundID, 1, player);
        }

        System.out.println(gameRoomID + " " + roundID);
        ongoingGameTimers.add(new Timer(gameRoomID, roundDuration));
        startTimerForRound(gameRoomID);
    }



    @Override
    /**
     * Method to get the current timer value of an ongoing game
     */
    public long getGameDurationVal(int gameID) {
        for (Timer ongoingGameTimer : ongoingGameTimers) {

            if (ongoingGameTimer.getID() == gameID) {
                long timer = ongoingGameTimer.getCurrTimerValue();
                if (timer == 0) {
                    try {
                        Thread.sleep(2000);
                        solveRoundPoints(gameID);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                return timer;
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
        DataPB.addUserWordList(username,gameID, wordList);
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
        return DataPB.getUsersWordlists(gameID).toString();
    }

    /**
     * Method to get the overall winner of a GAME if there is any. Otherwise, the method returns undecided.
     * Call this method after every round to check if there is already an overall winner.
     * @param gameId
     * @return winnerUsername
     */
    @Override
    public String getOverallWinner(int gameId) {
        return DataPB.getGameWinner(gameId);
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
            String pfp = DataPB.getPFPOfUser(key);
            if (pfp == null)
                pfp = "";
            userInfo curr = new userInfo(key, pfp, userPointsMap.get(key));

            System.out.println("leaderboard: " + curr.username + " " + curr.pfpAddress + " " + userPointsMap.get(key));
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
     *
     * @param gameID
     * @return
     */
    @Override
    public userInfo[] getCurrGameLeaderboard(int gameID) {
        return DataPB.getCurrGameLeaderboard(gameID);
    }

    @Override
    public String getPFPOFUser(String username) {
        return DataPB.getPFPOfUser(username);
    }

    @Override
    public String getFullName(String username) {
        return DataPB.getFullName(username);
    }

    @Override
    public boolean isValidWord(String word) {
        return DataPB.isValidWord(word);
    }

    /** END of IDL methods */




    /** PRIVATE Methods */

    private void startLobbyTime() {
        Runnable t = () -> {
            while (currLobbyTimerValue > 0L) {
                try {
                    Thread.sleep(1000L);
                    currLobbyTimerValue -= 1000L;
                } catch (Exception var2) {
                    var2.printStackTrace();
                }
            }
            if (currLobby.size() > 0) {
                System.out.println("joining game room.");
                joinGameRoom(currLobby);
                currLobby = new ArrayList<>();
                currLobbyTimerValue = lobbyTimerValue;
            }
        };
        new Thread(t).start();
    }

    private void startTimerForRound(int gameID) {
        for (Timer timer : ongoingGameTimers) {
            if (timer.getID() == gameID) {
                Thread t = new Thread(timer);
                t.start();
            }
        }

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

    /**
     * Method to handle solving of points.
     * At the moment this method is called, the database is already populated with the wordlist for a round that has finished
     *      The points need to be solved.
     *      To solve the points, given the gameID, find the most recent round that finished, get the wordList of ALL the users
     *      from the said round, perform the computations, and eventually update the database.
     * @param gameID
     */
    private void solveRoundPoints(int gameID) {
        List<Map<String, List<String>>> uncleanedUserWordMapList = getUsersWordlists(gameID);
        List<Map<String, List<String>>> cleanedUserWordMapList = compareAllWordLists(uncleanedUserWordMapList);

        for (Map<String, List<String>> userWordMap : cleanedUserWordMapList) {
            for (String username : userWordMap.keySet()) {
                int prevScore = getUserPointsOngoingGame(gameID, username);
                int currentTotalScore = computeTotalScore(userWordMap) + prevScore;
                DataPB.updatePoints(gameID, currentTotalScore, username);
            }
        }
    }

    /**
     * Returns a map of the users and their corresponding submitted words on a given gameID's latest round
     * EXAMPLE:
     * Ramon : [Lorem, Ipsum]
     * Shander: [Burger, Loan, Interest]
     * Holy : [Burger]
     * @param gameID
     * @return
     */
    private List<Map<String, List<String>>> getUsersWordlists(int gameID){
        ResultSet rs = DataPB.getUsersWordlists(gameID);

        List<Map<String, List<String>>> userWordMapList = new ArrayList<>();
        Map<String, List<String>> usersWordListsMap = new HashMap<>();

        try {
            while (rs.next()){
                String username = rs.getString(1);
                String[] wordsArr = rs.getString(2).split(",");
                List<String> wordsList = Arrays.asList(wordsArr);

                usersWordListsMap.put(username, wordsList);
                userWordMapList.add(usersWordListsMap);
            }
        }catch (SQLException sqle){
            sqle.printStackTrace();
        }

        return userWordMapList;
    }

    /**
     * Compares and cleans the specified userWordMapList by:
     *  1. Compiling all the word lists in one list.
     *  2. Getting the duplicates and adding it into a list.
     *  3. Set a current word from a user word list.
     *  4. Compare the word to the elements of the duplicates.
     *  5. If the word exists in the duplicates, it will be removed from all the word lists.
     *  6. Repeat Step 3.
     *  7. Once the current list has been cleaned completely, it will be added to the new clean list of the user.
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
                currUserWordList.removeIf(duplicates::contains);
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

