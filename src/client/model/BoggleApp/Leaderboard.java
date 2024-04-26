package client.model.BoggleApp;


/**
* BoggleApp/Leaderboard.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from BoggleClient.idl
* Friday, April 26, 2024 2:48:46 PM PST
*/

public final class Leaderboard implements org.omg.CORBA.portable.IDLEntity
{
  public String username = null;
  public String pfpAddress = null;
  public int points = (int)0;

  public Leaderboard ()
  {
  } // ctor

  public Leaderboard (String _username, String _pfpAddress, int _points)
  {
    username = _username;
    pfpAddress = _pfpAddress;
    points = _points;
  } // ctor

} // class Leaderboard
