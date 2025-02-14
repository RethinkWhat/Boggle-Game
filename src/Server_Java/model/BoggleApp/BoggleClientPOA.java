package Server_Java.model.BoggleApp;


/**
* BoggleApp/BoggleClientPOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from BoggleClient.idl
* Sunday, May 12, 2024 2:09:46 PM PST
*/

public abstract class BoggleClientPOA extends org.omg.PortableServer.Servant
 implements BoggleClientOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("validateAccount", new java.lang.Integer (0));
    _methods.put ("attemptJoin", new java.lang.Integer (1));
    _methods.put ("getLobbyMembers", new java.lang.Integer (2));
    _methods.put ("getCurrLobbyTimerValue", new java.lang.Integer (3));
    _methods.put ("getGameID", new java.lang.Integer (4));
    _methods.put ("getLetters", new java.lang.Integer (5));
    _methods.put ("getGameDurationVal", new java.lang.Integer (6));
    _methods.put ("sendUserWordList", new java.lang.Integer (7));
    _methods.put ("getRoundWinner", new java.lang.Integer (8));
    _methods.put ("getOverallWinner", new java.lang.Integer (9));
    _methods.put ("getLeaderboard", new java.lang.Integer (10));
    _methods.put ("editInfo", new java.lang.Integer (11));
    _methods.put ("editPassword", new java.lang.Integer (12));
    _methods.put ("getUserTotalPoints", new java.lang.Integer (13));
    _methods.put ("getUserPointsOngoingGame", new java.lang.Integer (14));
    _methods.put ("getNumberOfMatches", new java.lang.Integer (15));
    _methods.put ("getNumberOfWins", new java.lang.Integer (16));
    _methods.put ("getCurrGameLeaderboard", new java.lang.Integer (17));
    _methods.put ("getPFPOFUser", new java.lang.Integer (18));
    _methods.put ("getFullName", new java.lang.Integer (19));
    _methods.put ("isValidWord", new java.lang.Integer (20));
    _methods.put ("exitLobby", new java.lang.Integer (21));
    _methods.put ("logout", new java.lang.Integer (22));
  }

  public org.omg.CORBA.portable.OutputStream _invoke (String $method,
                                org.omg.CORBA.portable.InputStream in,
                                org.omg.CORBA.portable.ResponseHandler $rh)
  {
    org.omg.CORBA.portable.OutputStream out = null;
    java.lang.Integer __method = (java.lang.Integer)_methods.get ($method);
    if (__method == null)
      throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);

    switch (__method.intValue ())
    {
       case 0:  // BoggleApp/BoggleClient/validateAccount
       {
         try {
           String username = in.read_string ();
           String password = in.read_string ();
           this.validateAccount (username, password);
           out = $rh.createReply();
         } catch (accountLoggedIn $ex) {
           out = $rh.createExceptionReply ();
           accountLoggedInHelper.write (out, $ex);
         } catch (accountDoesNotExist $ex) {
           out = $rh.createExceptionReply ();
           accountDoesNotExistHelper.write (out, $ex);
         }
         break;
       }

       case 1:  // BoggleApp/BoggleClient/attemptJoin
       {
         String username = in.read_string ();
         this.attemptJoin (username);
         out = $rh.createReply();
         break;
       }

       case 2:  // BoggleApp/BoggleClient/getLobbyMembers
       {
         LobbyUser $result[] = null;
         $result = this.getLobbyMembers ();
         out = $rh.createReply();
         lobbyUserListHelper.write (out, $result);
         break;
       }

       case 3:  // BoggleApp/BoggleClient/getCurrLobbyTimerValue
       {
         org.omg.CORBA.BooleanHolder validLobby = new org.omg.CORBA.BooleanHolder ();
         long $result = (long)0;
         $result = this.getCurrLobbyTimerValue (validLobby);
         out = $rh.createReply();
         out.write_longlong ($result);
         out.write_boolean (validLobby.value);
         break;
       }

       case 4:  // BoggleApp/BoggleClient/getGameID
       {
         String username = in.read_string ();
         int $result = (int)0;
         $result = this.getGameID (username);
         out = $rh.createReply();
         out.write_long ($result);
         break;
       }

       case 5:  // BoggleApp/BoggleClient/getLetters
       {
         int gameID = in.read_long ();
         String $result = null;
         $result = this.getLetters (gameID);
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       case 6:  // BoggleApp/BoggleClient/getGameDurationVal
       {
         int gameID = in.read_long ();
         long $result = (long)0;
         $result = this.getGameDurationVal (gameID);
         out = $rh.createReply();
         out.write_longlong ($result);
         break;
       }

       case 7:  // BoggleApp/BoggleClient/sendUserWordList
       {
         int gameID = in.read_long ();
         String username = in.read_string ();
         String listOfWords[] = wordListHelper.read (in);
         this.sendUserWordList (gameID, username, listOfWords);
         out = $rh.createReply();
         break;
       }

       case 8:  // BoggleApp/BoggleClient/getRoundWinner
       {
         int gameID = in.read_long ();
         String $result = null;
         $result = this.getRoundWinner (gameID);
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       case 9:  // BoggleApp/BoggleClient/getOverallWinner
       {
         int gameID = in.read_long ();
         String $result = null;
         $result = this.getOverallWinner (gameID);
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       case 10:  // BoggleApp/BoggleClient/getLeaderboard
       {
         userInfo $result[] = null;
         $result = this.getLeaderboard ();
         out = $rh.createReply();
         leaderboardsHelper.write (out, $result);
         break;
       }

       case 11:  // BoggleApp/BoggleClient/editInfo
       {
         try {
           String username = in.read_string ();
           String toEdit = in.read_string ();
           String newInfo = in.read_string ();
           this.editInfo (username, toEdit, newInfo);
           out = $rh.createReply();
         } catch (updateFailed $ex) {
           out = $rh.createExceptionReply ();
           updateFailedHelper.write (out, $ex);
         }
         break;
       }

       case 12:  // BoggleApp/BoggleClient/editPassword
       {
         try {
           String username = in.read_string ();
           String oldPass = in.read_string ();
           String newPass = in.read_string ();
           this.editPassword (username, oldPass, newPass);
           out = $rh.createReply();
         } catch (updateFailed $ex) {
           out = $rh.createExceptionReply ();
           updateFailedHelper.write (out, $ex);
         }
         break;
       }

       case 13:  // BoggleApp/BoggleClient/getUserTotalPoints
       {
         String username = in.read_string ();
         int $result = (int)0;
         $result = this.getUserTotalPoints (username);
         out = $rh.createReply();
         out.write_long ($result);
         break;
       }

       case 14:  // BoggleApp/BoggleClient/getUserPointsOngoingGame
       {
         int gameID = in.read_long ();
         String username = in.read_string ();
         int $result = (int)0;
         $result = this.getUserPointsOngoingGame (gameID, username);
         out = $rh.createReply();
         out.write_long ($result);
         break;
       }

       case 15:  // BoggleApp/BoggleClient/getNumberOfMatches
       {
         String username = in.read_string ();
         int $result = (int)0;
         $result = this.getNumberOfMatches (username);
         out = $rh.createReply();
         out.write_long ($result);
         break;
       }

       case 16:  // BoggleApp/BoggleClient/getNumberOfWins
       {
         String username = in.read_string ();
         int $result = (int)0;
         $result = this.getNumberOfWins (username);
         out = $rh.createReply();
         out.write_long ($result);
         break;
       }

       case 17:  // BoggleApp/BoggleClient/getCurrGameLeaderboard
       {
         int gameID = in.read_long ();
         userInfo $result[] = null;
         $result = this.getCurrGameLeaderboard (gameID);
         out = $rh.createReply();
         leaderboardsHelper.write (out, $result);
         break;
       }

       case 18:  // BoggleApp/BoggleClient/getPFPOFUser
       {
         String username = in.read_string ();
         String $result = null;
         $result = this.getPFPOFUser (username);
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       case 19:  // BoggleApp/BoggleClient/getFullName
       {
         String username = in.read_string ();
         String $result = null;
         $result = this.getFullName (username);
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       case 20:  // BoggleApp/BoggleClient/isValidWord
       {
         String word = in.read_string ();
         boolean $result = false;
         $result = this.isValidWord (word);
         out = $rh.createReply();
         out.write_boolean ($result);
         break;
       }

       case 21:  // BoggleApp/BoggleClient/exitLobby
       {
         String username = in.read_string ();
         this.exitLobby (username);
         out = $rh.createReply();
         break;
       }

       case 22:  // BoggleApp/BoggleClient/logout
       {
         String username = in.read_string ();
         this.logout (username);
         out = $rh.createReply();
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:BoggleApp/BoggleClient:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public BoggleClient _this() 
  {
    return BoggleClientHelper.narrow(
    super._this_object());
  }

  public BoggleClient _this(org.omg.CORBA.ORB orb) 
  {
    return BoggleClientHelper.narrow(
    super._this_object(orb));
  }


} // class BoggleClientPOA
