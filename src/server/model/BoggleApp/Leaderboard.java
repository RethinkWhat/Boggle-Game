package server.model.BoggleApp;


/**
* BoggleApp/Leaderboard.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from BoggleClient.idl
* Monday, April 22, 2024 4:38:19 PM PST
*/

public final class Leaderboard implements org.omg.CORBA.portable.IDLEntity
{
  public String username = null;
  public int points = (int)0;

  public Leaderboard ()
  {
  } // ctor

  public Leaderboard (String _username, int _points)
  {
    username = _username;
    points = _points;
  } // ctor

} // class Leaderboard
