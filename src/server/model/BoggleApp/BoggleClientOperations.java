package server.model.BoggleApp;


/**
* BoggleApp/BoggleClientOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from BoggleClient.idl
* Thursday, April 25, 2024 7:57:43 AM PST
*/

public interface BoggleClientOperations 
{
  boolean validateAccount (String username, String password);
  long attemptJoin (String username);
  int joinGameRoom (org.omg.CORBA.LongHolder duration);
  void exitGameRoom (String username);
  long getCurrLobbyTimerValue (org.omg.CORBA.BooleanHolder validLobby);
  int startRound (String username, int gameID, org.omg.CORBA.IntHolder roundNumber, org.omg.CORBA.StringHolder vowels, org.omg.CORBA.StringHolder consonants);
  long getGameDurationVal (int gameID, int roundID);
  String getRoundWinner (String username, int gameID, int roundID, String wordsEntered);
  String getOverallWinner (int gameId);
  int getPoints (String username);
  String getWinnerIfAny (int gameID);
  String getWordList (int gameID, int roundID);
  Leaderboard[] getLeaderboard ();
  int getUserPoints (String username);
  boolean editInfo (String username, String toEdit, String newInfo);
  int getMatches (String username);
  int getWins (String username);
  boolean editPassword (String username, String oldPass, String newPass);
} // interface BoggleClientOperations
