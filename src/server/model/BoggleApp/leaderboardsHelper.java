package server.model.BoggleApp;


/**
* BoggleApp/leaderboardsHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from BoggleClient.idl
* Saturday, May 4, 2024 8:19:55 AM PST
*/

abstract public class leaderboardsHelper
{
  private static String  _id = "IDL:BoggleApp/leaderboards:1.0";

  public static void insert (org.omg.CORBA.Any a, userInfo[] that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static userInfo[] extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = userInfoHelper.type ();
      __typeCode = org.omg.CORBA.ORB.init ().create_sequence_tc (0, __typeCode);
      __typeCode = org.omg.CORBA.ORB.init ().create_alias_tc (leaderboardsHelper.id (), "leaderboards", __typeCode);
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static userInfo[] read (org.omg.CORBA.portable.InputStream istream)
  {
    userInfo value[] = null;
    int _len0 = istream.read_long ();
    value = new userInfo[_len0];
    for (int _o1 = 0;_o1 < value.length; ++_o1)
      value[_o1] = userInfoHelper.read (istream);
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, userInfo[] value)
  {
    ostream.write_long (value.length);
    for (int _i0 = 0;_i0 < value.length; ++_i0)
      userInfoHelper.write (ostream, value[_i0]);
  }

}
