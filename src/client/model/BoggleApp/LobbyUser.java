package client.model.BoggleApp;


/**
* BoggleApp/LobbyUser.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from BoggleClient.idl
* Friday, May 3, 2024 8:24:51 AM PST
*/

public final class LobbyUser implements org.omg.CORBA.portable.IDLEntity
{
  public String username = null;
  public String pfpAddress = null;

  public LobbyUser ()
  {
  } // ctor

  public LobbyUser (String _username, String _pfpAddress)
  {
    username = _username;
    pfpAddress = _pfpAddress;
  } // ctor

} // class LobbyUser
