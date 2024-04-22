package client.model.BoggleApp;;


/**
* BoggleApp/BoggleClientHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from BoggleClient.idl
* Monday, April 22, 2024 4:42:17 PM PST
*/

abstract public class BoggleClientHelper
{
  private static String  _id = "IDL:BoggleApp/BoggleClient:1.0";

  public static void insert (org.omg.CORBA.Any a, BoggleClient that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static BoggleClient extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (BoggleClientHelper.id (), "BoggleClient");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static BoggleClient read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_BoggleClientStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, BoggleClient value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static BoggleClient narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof BoggleClient)
      return (BoggleClient)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      _BoggleClientStub stub = new _BoggleClientStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static BoggleClient unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof BoggleClient)
      return (BoggleClient)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      _BoggleClientStub stub = new _BoggleClientStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
