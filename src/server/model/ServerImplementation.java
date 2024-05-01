package server.model;

import java.sql.Time;
import java.util.*;
import java.util.stream.Collectors;

import org.omg.CORBA.BooleanHolder;
import org.omg.CORBA.IntHolder;
import org.omg.CORBA.LongHolder;
import org.omg.CORBA.StringHolder;
import server.model.BoggleApp.BoggleClientPOA;
import server.model.BoggleApp.Leaderboard;
import server.model.BoggleApp.LobbyUser;

public class ServerImplementation extends BoggleClientPOA {

    private ArrayList<String> loggedIn = new ArrayList<>();

    /**
     * Lobby Variables
     */
    private long lobbyTimerValue = 10000L;
    private GameTimer lobbyTimer = new GameTimer(0, lobbyTimerValue);
    private ArrayList<String> currLobby = new ArrayList<>();




    private long gameDuration = 180000L;
    private ArrayList<GameTimer> gamesStarted;


    public ServerImplementation() {
        DataPB.setCon();
        gamesStarted = new ArrayList<>();

    }

    @Override
    public synchronized LobbyUser[] getLobbyMembers() {
        LobbyUser[] lobbyUsers = new LobbyUser[currLobby.size()];
        for (int x =0; x < currLobby.size(); x++) {
            lobbyUsers[x] = new LobbyUser(currLobby.get(x), DataPB.getPFPOfUser(currLobby.get(x)));
        }
        return lobbyUsers;
    }

    @Override
    public Leaderboard[] getCurrGameLeaderboard(int gameID) {
        return new Leaderboard[0];
    }

    public boolean validateAccount(String var1, String var2) {
        boolean exists = DataPB.validateAccount(var1,var2);
        boolean userIn = loggedIn.contains(var1);
        if (!userIn)
            loggedIn.add(var1);
        return exists && !userIn;
    }

    //TODO: make void
    public synchronized long attemptJoin(String username) {
        if (lobbyTimer.getCurrTimerValue() == 0L) {
            lobbyTimer = new GameTimer(0, lobbyTimerValue);
            currLobby = new ArrayList<>();
        } if (lobbyTimer.getCurrTimerValue() == 10000L)
            startLobbyTime();
        currLobby.add(username);
        System.out.println("Adding user: " + username);
        System.out.println(currLobby);
        return -1;
    }

    private void startLobbyTime() {
        Thread t = new Thread(lobbyTimer);
        t.start();
    }

    public long getCurrLobbyTimerValue(BooleanHolder validLobby) {
        validLobby.value = currLobby.size() > 1;
        return lobbyTimer.getCurrTimerValue();
    }



    @Override
    public void exitGameRoom(String username) {
        currLobby.remove(username);
    }

    @Override
    public int joinGameRoom(LongHolder duration) {
        duration.value = gameDuration;
        return DataPB.createGameRoom();
    }


    @Override
    public int startRound(String username, int gameID, IntHolder roundNumber, StringHolder vowelsHolder, StringHolder consonantsHolder) {
        String cons = createRandomConsonantSet();
        String vowel = createRandomVowelSet();
        int roundID = DataPB.createRound(vowel, cons);


        int newRoundNumber = DataPB.getLatestRound(gameID) +1;
        roundNumber.value = newRoundNumber;
        vowelsHolder.value = vowel;
        consonantsHolder.value = cons;
        int id = DataPB.createRoundDetails(gameID,roundID, newRoundNumber, username);
        startTimerForGame(gameID,  gameDuration);

        roundNumber.value = newRoundNumber;
        return id;
    }



    @Override
    public long getGameDurationVal(int gameID, int roundID) {
        return 0;
    }


    //TODO: Store the wordList of each user for the finished round in the game
    //TODO: Call the DataPB.updatePoints() method and update the points of user for round
    @Override
    public String getRoundWinner(String username, int gameID, int roundID, String wordsEntered) {
        return null;
    }

    @Override
    public String getOverallWinner(int gameId) {
        return null;
    }

    @Override
    public int getPoints(String username) {
        return 0;
    }

    @Override
    public String getWinnerIfAny(int gameID) {
        return null;
    }

    @Override
    public String getWordList(int gameID, int roundID) {
        return null;
    }

    @Override
    public Leaderboard[] getLeaderboard() {
        Leaderboard leaderboard = new Leaderboard("rithik", "res/drawable/images/pfp-male-1.png", 5000);
        Leaderboard ramon = new Leaderboard("ramon", "res/drawable/images/pfp-male-1.png", 10000);

        Leaderboard[] leaderboards = new Leaderboard[2];
        leaderboards[0] = leaderboard;
        leaderboards[1] = ramon;
        return leaderboards;
    }

    @Override
    public int getUserPoints(String username) {
        int points = DataPB.getUserPoints(username);
        return points;
    }

    @Override
    public boolean editInfo(String username, String toEdit, String newInfo) {
        return false;
    }

    @Override
    public int getMatches(String username) {
        return 0;
    }

    @Override
    public int getWins(String username) {
        return 0;
    }

    @Override
    public boolean editPassword(String username, String oldPass, String newPass) {
        return false;
    }

    private void startTimerForGame(int gameID, long duration) {
        GameTimer gameTimer = new GameTimer(gameID, duration);
        Thread t = new Thread(gameTimer);
        gamesStarted.add(gameTimer);
        t.start();
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

    public void setGameDuration(long time) {
        gameDuration = time;
    }

    public void setLobbyTimerValue(long time) {
        lobbyTimerValue = time;
    }

    public void setNumberOfPlayers(int number) {
        //TODO:
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
}
