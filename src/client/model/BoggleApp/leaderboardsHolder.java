package client.model.BoggleApp;


/**
* client.model.BoggleApp/leaderboardsHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from shared/BoggleClient.idl
* Thursday, May 2, 2024 3:03:28 PM PST
*/

public final class leaderboardsHolder implements org.omg.CORBA.portable.Streamable
{
  public userInfo value[] = null;

  public leaderboardsHolder ()
  {
  }

  public leaderboardsHolder (userInfo[] initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = leaderboardsHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    leaderboardsHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return leaderboardsHelper.type ();
  }

}
