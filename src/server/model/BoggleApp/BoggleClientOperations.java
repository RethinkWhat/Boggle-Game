package server.model.BoggleApp;;


/**
* BoggleApp/BoggleClientOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from BoggleClient.idl
* Friday, April 19, 2024 5:36:41 PM PST
*/

public interface BoggleClientOperations 
{
  boolean validateAccount (String username, String password);
  int attemptJoin (String username, org.omg.CORBA.IntHolder timeRemaining);
  String getDuration (int gameID);
  int getRoundID (int gameID, String username);
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
