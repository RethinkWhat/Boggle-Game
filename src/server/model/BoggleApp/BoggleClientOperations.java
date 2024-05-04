package server.model.BoggleApp;


/**
* BoggleApp/BoggleClientOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from BoggleClient.idl
* Saturday, May 4, 2024 8:19:55 AM PST
*/

public interface BoggleClientOperations 
{
  void validateAccount (String username, String password) throws accountLoggedIn, accountDoesNotExist;
  void attemptJoin (String username);
  LobbyUser[] getLobbyMembers ();
  long getCurrLobbyTimerValue (org.omg.CORBA.BooleanHolder validLobby);
  int getGameID (String username);
  String getLetters (int gameID);
  long getGameDurationVal (int gameID);
  void sendUserWordList (int gameID, String username, String[] wordList);
  String getRoundWinner (int gameID);
  String getNextRoundLetterSet (int gameRoomID);
  void exitGameRoom (String username);
  String getOverallWinner (int gameID);
  userInfo[] getLeaderboard ();
  void editInfo (String username, String toEdit, String newInfo) throws updateFailed;
  void editPassword (String username, String oldPass, String newPass) throws updateFailed;
  int getUserTotalPoints (String username);
  int getUserPointsOngoingGame (int gameID, String username);
  int getNumberOfMatches (String username);
  int getNumberOfWins (String username);
  userInfo[] getCurrGameLeaderboard (int gameID);
  String getPFPOFUser (String username);
  String getFullName (String username);
} // interface BoggleClientOperations
