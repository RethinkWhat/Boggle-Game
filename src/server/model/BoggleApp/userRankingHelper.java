package server.model.BoggleApp;


/**
* BoggleApp/userRankingHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from BoggleClient.idl
* Monday, April 22, 2024 4:38:19 PM PST
*/

abstract public class userRankingHelper
{
  private static String  _id = "IDL:BoggleApp/userRanking:1.0";

  public static void insert (org.omg.CORBA.Any a, Leaderboard[] that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static Leaderboard[] extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = LeaderboardHelper.type ();
      __typeCode = org.omg.CORBA.ORB.init ().create_sequence_tc (0, __typeCode);
      __typeCode = org.omg.CORBA.ORB.init ().create_alias_tc (userRankingHelper.id (), "userRanking", __typeCode);
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static Leaderboard[] read (org.omg.CORBA.portable.InputStream istream)
  {
    Leaderboard value[] = null;
    int _len0 = istream.read_long ();
    value = new Leaderboard[_len0];
    for (int _o1 = 0;_o1 < value.length; ++_o1)
      value[_o1] = LeaderboardHelper.read (istream);
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, Leaderboard[] value)
  {
    ostream.write_long (value.length);
    for (int _i0 = 0;_i0 < value.length; ++_i0)
      LeaderboardHelper.write (ostream, value[_i0]);
  }

}
