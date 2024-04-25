package server.model.BoggleApp;


/**
* BoggleApp/_BoggleClientStub.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from BoggleClient.idl
* Thursday, April 25, 2024 7:57:43 AM PST
*/

public class _BoggleClientStub extends org.omg.CORBA.portable.ObjectImpl implements BoggleClient
{

  public boolean validateAccount (String username, String password)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("validateAccount", true);
                $out.write_string (username);
                $out.write_string (password);
                $in = _invoke ($out);
                boolean $result = $in.read_boolean ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return validateAccount (username, password        );
            } finally {
                _releaseReply ($in);
            }
  } // validateAccount

  public long attemptJoin (String username)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("attemptJoin", true);
                $out.write_string (username);
                $in = _invoke ($out);
                long $result = $in.read_longlong ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return attemptJoin (username        );
            } finally {
                _releaseReply ($in);
            }
  } // attemptJoin

  public int joinGameRoom (org.omg.CORBA.LongHolder duration)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("joinGameRoom", true);
                $in = _invoke ($out);
                int $result = $in.read_long ();
                duration.value = $in.read_longlong ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return joinGameRoom (duration        );
            } finally {
                _releaseReply ($in);
            }
  } // joinGameRoom

  public void exitGameRoom (String username)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("exitGameRoom", true);
                $out.write_string (username);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                exitGameRoom (username        );
            } finally {
                _releaseReply ($in);
            }
  } // exitGameRoom

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

  public int startRound (String username, int gameID, org.omg.CORBA.IntHolder roundNumber, org.omg.CORBA.StringHolder vowels, org.omg.CORBA.StringHolder consonants)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("startRound", true);
                $out.write_string (username);
                $out.write_long (gameID);
                $in = _invoke ($out);
                int $result = $in.read_long ();
                roundNumber.value = $in.read_long ();
                vowels.value = $in.read_string ();
                consonants.value = $in.read_string ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return startRound (username, gameID, roundNumber, vowels, consonants        );
            } finally {
                _releaseReply ($in);
            }
  } // startRound

  public long getGameDurationVal (int gameID, int roundID)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getGameDurationVal", true);
                $out.write_long (gameID);
                $out.write_long (roundID);
                $in = _invoke ($out);
                long $result = $in.read_longlong ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getGameDurationVal (gameID, roundID        );
            } finally {
                _releaseReply ($in);
            }
  } // getGameDurationVal

  public String getRoundWinner (String username, int gameID, int roundID, String wordsEntered)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getRoundWinner", true);
                $out.write_string (username);
                $out.write_long (gameID);
                $out.write_long (roundID);
                $out.write_string (wordsEntered);
                $in = _invoke ($out);
                String $result = $in.read_string ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getRoundWinner (username, gameID, roundID, wordsEntered        );
            } finally {
                _releaseReply ($in);
            }
  } // getRoundWinner

  public String getOverallWinner (int gameId)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getOverallWinner", true);
                $out.write_long (gameId);
                $in = _invoke ($out);
                String $result = $in.read_string ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getOverallWinner (gameId        );
            } finally {
                _releaseReply ($in);
            }
  } // getOverallWinner

  public int getPoints (String username)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getPoints", true);
                $out.write_string (username);
                $in = _invoke ($out);
                int $result = $in.read_long ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getPoints (username        );
            } finally {
                _releaseReply ($in);
            }
  } // getPoints

  public String getWinnerIfAny (int gameID)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getWinnerIfAny", true);
                $out.write_long (gameID);
                $in = _invoke ($out);
                String $result = $in.read_string ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getWinnerIfAny (gameID        );
            } finally {
                _releaseReply ($in);
            }
  } // getWinnerIfAny

  public String getWordList (int gameID, int roundID)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getWordList", true);
                $out.write_long (gameID);
                $out.write_long (roundID);
                $in = _invoke ($out);
                String $result = $in.read_string ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getWordList (gameID, roundID        );
            } finally {
                _releaseReply ($in);
            }
  } // getWordList

  public Leaderboard[] getLeaderboard ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getLeaderboard", true);
                $in = _invoke ($out);
                Leaderboard $result[] = userRankingHelper.read ($in);
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

  public int getUserPoints (String username)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getUserPoints", true);
                $out.write_string (username);
                $in = _invoke ($out);
                int $result = $in.read_long ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getUserPoints (username        );
            } finally {
                _releaseReply ($in);
            }
  } // getUserPoints

  public boolean editInfo (String username, String toEdit, String newInfo)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("editInfo", true);
                $out.write_string (username);
                $out.write_string (toEdit);
                $out.write_string (newInfo);
                $in = _invoke ($out);
                boolean $result = $in.read_boolean ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return editInfo (username, toEdit, newInfo        );
            } finally {
                _releaseReply ($in);
            }
  } // editInfo

  public int getMatches (String username)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getMatches", true);
                $out.write_string (username);
                $in = _invoke ($out);
                int $result = $in.read_long ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getMatches (username        );
            } finally {
                _releaseReply ($in);
            }
  } // getMatches

  public int getWins (String username)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getWins", true);
                $out.write_string (username);
                $in = _invoke ($out);
                int $result = $in.read_long ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getWins (username        );
            } finally {
                _releaseReply ($in);
            }
  } // getWins

  public boolean editPassword (String username, String oldPass, String newPass)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("editPassword", true);
                $out.write_string (username);
                $out.write_string (oldPass);
                $out.write_string (newPass);
                $in = _invoke ($out);
                boolean $result = $in.read_boolean ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return editPassword (username, oldPass, newPass        );
            } finally {
                _releaseReply ($in);
            }
  } // editPassword

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
