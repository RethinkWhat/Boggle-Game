package server.model.BoggleApp;


/**
* BoggleApp/_BoggleClientStub.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from BoggleClient.idl
* Monday, May 6, 2024 7:22:35 PM SGT
*/

public class _BoggleClientStub extends org.omg.CORBA.portable.ObjectImpl implements BoggleClient
{

  public void validateAccount (String username, String password) throws accountLoggedIn, accountDoesNotExist
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("validateAccount", true);
                $out.write_string (username);
                $out.write_string (password);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:BoggleApp/accountLoggedIn:1.0"))
                    throw accountLoggedInHelper.read ($in);
                else if (_id.equals ("IDL:BoggleApp/accountDoesNotExist:1.0"))
                    throw accountDoesNotExistHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                validateAccount (username, password        );
            } finally {
                _releaseReply ($in);
            }
  } // validateAccount

  public void attemptJoin (String username)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("attemptJoin", true);
                $out.write_string (username);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                attemptJoin (username        );
            } finally {
                _releaseReply ($in);
            }
  } // attemptJoin

  public LobbyUser[] getLobbyMembers ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getLobbyMembers", true);
                $in = _invoke ($out);
                LobbyUser $result[] = lobbyUserListHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getLobbyMembers (        );
            } finally {
                _releaseReply ($in);
            }
  } // getLobbyMembers

  public long getCurrLobbyTimerValue (org.omg.CORBA.BooleanHolder validLobby)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getCurrLobbyTimerValue", true);
                $in = _invoke ($out);
                long $result = $in.read_longlong ();
                validLobby.value = $in.read_boolean ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getCurrLobbyTimerValue (validLobby        );
            } finally {
                _releaseReply ($in);
            }
  } // getCurrLobbyTimerValue

  public int getGameID (String username)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getGameID", true);
                $out.write_string (username);
                $in = _invoke ($out);
                int $result = $in.read_long ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getGameID (username        );
            } finally {
                _releaseReply ($in);
            }
  } // getGameID

  public String getLetters (int gameID)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getLetters", true);
                $out.write_long (gameID);
                $in = _invoke ($out);
                String $result = $in.read_string ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getLetters (gameID        );
            } finally {
                _releaseReply ($in);
            }
  } // getLetters

  public long getGameDurationVal (int gameID)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getGameDurationVal", true);
                $out.write_long (gameID);
                $in = _invoke ($out);
                long $result = $in.read_longlong ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getGameDurationVal (gameID        );
            } finally {
                _releaseReply ($in);
            }
  } // getGameDurationVal

  public void sendUserWordList (int gameID, String username, String[] listOfWords)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("sendUserWordList", true);
                $out.write_long (gameID);
                $out.write_string (username);
                wordListHelper.write ($out, listOfWords);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                sendUserWordList (gameID, username, listOfWords        );
            } finally {
                _releaseReply ($in);
            }
  } // sendUserWordList

  public String getRoundWinner (int gameID)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getRoundWinner", true);
                $out.write_long (gameID);
                $in = _invoke ($out);
                String $result = $in.read_string ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getRoundWinner (gameID        );
            } finally {
                _releaseReply ($in);
            }
  } // getRoundWinner

  public String getNextRoundLetterSet (int gameRoomID)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getNextRoundLetterSet", true);
                $out.write_long (gameRoomID);
                $in = _invoke ($out);
                String $result = $in.read_string ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getNextRoundLetterSet (gameRoomID        );
            } finally {
                _releaseReply ($in);
            }
  } // getNextRoundLetterSet

  public String getOverallWinner (int gameID)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getOverallWinner", true);
                $out.write_long (gameID);
                $in = _invoke ($out);
                String $result = $in.read_string ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getOverallWinner (gameID        );
            } finally {
                _releaseReply ($in);
            }
  } // getOverallWinner

  public userInfo[] getLeaderboard ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getLeaderboard", true);
                $in = _invoke ($out);
                userInfo $result[] = leaderboardsHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getLeaderboard (        );
            } finally {
                _releaseReply ($in);
            }
  } // getLeaderboard

  public void editInfo (String username, String toEdit, String newInfo) throws updateFailed
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("editInfo", true);
                $out.write_string (username);
                $out.write_string (toEdit);
                $out.write_string (newInfo);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:BoggleApp/updateFailed:1.0"))
                    throw updateFailedHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                editInfo (username, toEdit, newInfo        );
            } finally {
                _releaseReply ($in);
            }
  } // editInfo

  public void editPassword (String username, String oldPass, String newPass) throws updateFailed
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("editPassword", true);
                $out.write_string (username);
                $out.write_string (oldPass);
                $out.write_string (newPass);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:BoggleApp/updateFailed:1.0"))
                    throw updateFailedHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                editPassword (username, oldPass, newPass        );
            } finally {
                _releaseReply ($in);
            }
  } // editPassword

  public int getUserTotalPoints (String username)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getUserTotalPoints", true);
                $out.write_string (username);
                $in = _invoke ($out);
                int $result = $in.read_long ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getUserTotalPoints (username        );
            } finally {
                _releaseReply ($in);
            }
  } // getUserTotalPoints

  public int getUserPointsOngoingGame (int gameID, String username)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getUserPointsOngoingGame", true);
                $out.write_long (gameID);
                $out.write_string (username);
                $in = _invoke ($out);
                int $result = $in.read_long ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getUserPointsOngoingGame (gameID, username        );
            } finally {
                _releaseReply ($in);
            }
  } // getUserPointsOngoingGame

  public int getNumberOfMatches (String username)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getNumberOfMatches", true);
                $out.write_string (username);
                $in = _invoke ($out);
                int $result = $in.read_long ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getNumberOfMatches (username        );
            } finally {
                _releaseReply ($in);
            }
  } // getNumberOfMatches

  public int getNumberOfWins (String username)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getNumberOfWins", true);
                $out.write_string (username);
                $in = _invoke ($out);
                int $result = $in.read_long ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getNumberOfWins (username        );
            } finally {
                _releaseReply ($in);
            }
  } // getNumberOfWins

  public userInfo[] getCurrGameLeaderboard (int gameID)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getCurrGameLeaderboard", true);
                $out.write_long (gameID);
                $in = _invoke ($out);
                userInfo $result[] = leaderboardsHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getCurrGameLeaderboard (gameID        );
            } finally {
                _releaseReply ($in);
            }
  } // getCurrGameLeaderboard

  public String getPFPOFUser (String username)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getPFPOFUser", true);
                $out.write_string (username);
                $in = _invoke ($out);
                String $result = $in.read_string ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getPFPOFUser (username        );
            } finally {
                _releaseReply ($in);
            }
  } // getPFPOFUser

  public String getFullName (String username)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getFullName", true);
                $out.write_string (username);
                $in = _invoke ($out);
                String $result = $in.read_string ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getFullName (username        );
            } finally {
                _releaseReply ($in);
            }
  } // getFullName

  public boolean isValidWord (String word)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("isValidWord", true);
                $out.write_string (word);
                $in = _invoke ($out);
                boolean $result = $in.read_boolean ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return isValidWord (word        );
            } finally {
                _releaseReply ($in);
            }
  } // isValidWord

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:BoggleApp/BoggleClient:1.0"};

  public String[] _ids ()
  {
    return (String[])__ids.clone ();
  }

  private void readObject (java.io.ObjectInputStream s) throws java.io.IOException
  {
     String str = s.readUTF ();
     com.sun.corba.se.impl.orbutil.IORCheckImpl.check(str, "BoggleApp._BoggleClientStub");
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     org.omg.CORBA.Object obj = orb.string_to_object (str);
     org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl) obj)._get_delegate ();
     _set_delegate (delegate);
   } finally {
     orb.destroy() ;
   }
  }

  private void writeObject (java.io.ObjectOutputStream s) throws java.io.IOException
  {
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     String str = orb.object_to_string (this);
     s.writeUTF (str);
   } finally {
     orb.destroy() ;
   }
  }
} // class _BoggleClientStub
