package server.model.BoggleApp;


/**
* BoggleApp/lobbyUserListHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from BoggleClient.idl
* Thursday, May 2, 2024 1:23:41 PM PST
*/

public final class lobbyUserListHolder implements org.omg.CORBA.portable.Streamable
{
  public LobbyUser value[] = null;

  public lobbyUserListHolder ()
  {
  }

  public lobbyUserListHolder (LobbyUser[] initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = lobbyUserListHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    lobbyUserListHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return lobbyUserListHelper.type ();
  }

}
