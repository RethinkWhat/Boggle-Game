package server.model.BoggleApp;

/**
* BoggleApp/LobbyUserHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from BoggleClient.idl
* Saturday, May 4, 2024 8:19:55 AM PST
*/

public final class LobbyUserHolder implements org.omg.CORBA.portable.Streamable
{
  public LobbyUser value = null;

  public LobbyUserHolder ()
  {
  }

  public LobbyUserHolder (LobbyUser initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = LobbyUserHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    LobbyUserHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return LobbyUserHelper.type ();
  }

}
