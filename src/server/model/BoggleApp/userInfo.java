package server.model.BoggleApp;


/**
* BoggleApp/userInfo.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from BoggleClient.idl
* Saturday, May 4, 2024 8:19:55 AM PST
*/

public final class userInfo implements org.omg.CORBA.portable.IDLEntity
{
  public String username = null;
  public String pfpAddress = null;
  public int points = (int)0;

  public userInfo ()
  {
  } // ctor

  public userInfo (String _username, String _pfpAddress, int _points)
  {
    username = _username;
    pfpAddress = _pfpAddress;
    points = _points;
  } // ctor

} // class userInfo
