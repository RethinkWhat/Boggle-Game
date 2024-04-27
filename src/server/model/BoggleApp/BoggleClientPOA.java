package server.model.BoggleApp;


/**
* BoggleApp/BoggleClientPOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from BoggleClient.idl
* Saturday, April 27, 2024 3:33:41 PM CST
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
    _methods.put ("joinGameRoom", new java.lang.Integer (2));
    _methods.put ("exitGameRoom", new java.lang.Integer (3));
    _methods.put ("getCurrLobbyTimerValue", new java.lang.Integer (4));
    _methods.put ("startRound", new java.lang.Integer (5));
    _methods.put ("getGameDurationVal", new java.lang.Integer (6));
    _methods.put ("getRoundWinner", new java.lang.Integer (7));
    _methods.put ("getOverallWinner", new java.lang.Integer (8));
    _methods.put ("getPoints", new java.lang.Integer (9));
    _methods.put ("getWinnerIfAny", new java.lang.Integer (10));
    _methods.put ("getWordList", new java.lang.Integer (11));
    _methods.put ("getLeaderboard", new java.lang.Integer (12));
    _methods.put ("getUserPoints", new java.lang.Integer (13));
    _methods.put ("editInfo", new java.lang.Integer (14));
    _methods.put ("getMatches", new java.lang.Integer (15));
    _methods.put ("getWins", new java.lang.Integer (16));
    _methods.put ("editPassword", new java.lang.Integer (17));
    _methods.put ("getLobbyMembers", new java.lang.Integer (18));
    _methods.put ("getCurrGameLeaderboard", new java.lang.Integer (19));
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
         String username = in.read_string ();
         String password = in.read_string ();
         boolean $result = false;
         $result = this.validateAccount (username, password);
         out = $rh.createReply();
         out.write_boolean ($result);
         break;
       }

       case 1:  // BoggleApp/BoggleClient/attemptJoin
       {
         String username = in.read_string ();
         long $result = (long)0;
         $result = this.attemptJoin (username);
         out = $rh.createReply();
         out.write_longlong ($result);
         break;
       }

       case 2:  // BoggleApp/BoggleClient/joinGameRoom
       {
         org.omg.CORBA.LongHolder duration = new org.omg.CORBA.LongHolder ();
         int $result = (int)0;
         $result = this.joinGameRoom (duration);
         out = $rh.createReply();
         out.write_long ($result);
         out.write_longlong (duration.value);
         break;
       }

       case 3:  // BoggleApp/BoggleClient/exitGameRoom
       {
         String username = in.read_string ();
         this.exitGameRoom (username);
         out = $rh.createReply();
         break;
       }

       case 4:  // BoggleApp/BoggleClient/getCurrLobbyTimerValue
       {
         org.omg.CORBA.BooleanHolder validLobby = new org.omg.CORBA.BooleanHolder ();
         long $result = (long)0;
         $result = this.getCurrLobbyTimerValue (validLobby);
         out = $rh.createReply();
         out.write_longlong ($result);
         out.write_boolean (validLobby.value);
         break;
       }

       case 5:  // BoggleApp/BoggleClient/startRound
       {
         String username = in.read_string ();
         int gameID = in.read_long ();
         org.omg.CORBA.IntHolder roundNumber = new org.omg.CORBA.IntHolder ();
         org.omg.CORBA.StringHolder vowels = new org.omg.CORBA.StringHolder ();
         org.omg.CORBA.StringHolder consonants = new org.omg.CORBA.StringHolder ();
         int $result = (int)0;
         $result = this.startRound (username, gameID, roundNumber, vowels, consonants);
         out = $rh.createReply();
         out.write_long ($result);
         out.write_long (roundNumber.value);
         out.write_string (vowels.value);
         out.write_string (consonants.value);
         break;
       }

       case 6:  // BoggleApp/BoggleClient/getGameDurationVal
       {
         int gameID = in.read_long ();
         int roundID = in.read_long ();
         long $result = (long)0;
         $result = this.getGameDurationVal (gameID, roundID);
         out = $rh.createReply();
         out.write_longlong ($result);
         break;
       }

       case 7:  // BoggleApp/BoggleClient/getRoundWinner
       {
         String username = in.read_string ();
         int gameID = in.read_long ();
         int roundID = in.read_long ();
         String wordsEntered = in.read_string ();
         String $result = null;
         $result = this.getRoundWinner (username, gameID, roundID, wordsEntered);
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       case 8:  // BoggleApp/BoggleClient/getOverallWinner
       {
         int gameId = in.read_long ();
         String $result = null;
         $result = this.getOverallWinner (gameId);
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       case 9:  // BoggleApp/BoggleClient/getPoints
       {
         String username = in.read_string ();
         int $result = (int)0;
         $result = this.getPoints (username);
         out = $rh.createReply();
         out.write_long ($result);
         break;
       }

       case 10:  // BoggleApp/BoggleClient/getWinnerIfAny
       {
         int gameID = in.read_long ();
         String $result = null;
         $result = this.getWinnerIfAny (gameID);
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       case 11:  // BoggleApp/BoggleClient/getWordList
       {
         int gameID = in.read_long ();
         int roundID = in.read_long ();
         String $result = null;
         $result = this.getWordList (gameID, roundID);
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       case 12:  // BoggleApp/BoggleClient/getLeaderboard
       {
         Leaderboard $result[] = null;
         $result = this.getLeaderboard ();
         out = $rh.createReply();
         userRankingHelper.write (out, $result);
         break;
       }

       case 13:  // BoggleApp/BoggleClient/getUserPoints
       {
         String username = in.read_string ();
         int $result = (int)0;
         $result = this.getUserPoints (username);
         out = $rh.createReply();
         out.write_long ($result);
         break;
       }

       case 14:  // BoggleApp/BoggleClient/editInfo
       {
         String username = in.read_string ();
         String toEdit = in.read_string ();
         String newInfo = in.read_string ();
         boolean $result = false;
         $result = this.editInfo (username, toEdit, newInfo);
         out = $rh.createReply();
         out.write_boolean ($result);
         break;
       }

       case 15:  // BoggleApp/BoggleClient/getMatches
       {
         String username = in.read_string ();
         int $result = (int)0;
         $result = this.getMatches (username);
         out = $rh.createReply();
         out.write_long ($result);
         break;
       }

       case 16:  // BoggleApp/BoggleClient/getWins
       {
         String username = in.read_string ();
         int $result = (int)0;
         $result = this.getWins (username);
         out = $rh.createReply();
         out.write_long ($result);
         break;
       }

       case 17:  // BoggleApp/BoggleClient/editPassword
       {
         String username = in.read_string ();
         String oldPass = in.read_string ();
         String newPass = in.read_string ();
         boolean $result = false;
         $result = this.editPassword (username, oldPass, newPass);
         out = $rh.createReply();
         out.write_boolean ($result);
         break;
       }

       case 18:  // BoggleApp/BoggleClient/getLobbyMembers
       {
         LobbyUser $result[] = null;
         $result = this.getLobbyMembers ();
         out = $rh.createReply();
         lobbyUserListHelper.write (out, $result);
         break;
       }

       case 19:  // BoggleApp/BoggleClient/getCurrGameLeaderboard
       {
         int gameID = in.read_long ();
         Leaderboard $result[] = null;
         $result = this.getCurrGameLeaderboard (gameID);
         out = $rh.createReply();
         userRankingHelper.write (out, $result);
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
