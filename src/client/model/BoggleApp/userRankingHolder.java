package client.model.BoggleApp;


/**
* BoggleApp/userRankingHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from BoggleClient.idl
* Friday, April 26, 2024 2:48:46 PM PST
*/

public final class userRankingHolder implements org.omg.CORBA.portable.Streamable
{
  public Leaderboard value[] = null;

  public userRankingHolder ()
  {
  }

  public userRankingHolder (Leaderboard[] initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = userRankingHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    userRankingHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return userRankingHelper.type ();
  }

}
