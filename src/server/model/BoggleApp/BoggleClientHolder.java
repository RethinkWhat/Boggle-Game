package server.model.BoggleApp;

/**
* server.model.client.model.BoggleApp/BoggleClientHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from shared/BoggleClient.idl
* Thursday, May 2, 2024 3:01:08 PM PST
*/

public final class BoggleClientHolder implements org.omg.CORBA.portable.Streamable
{
  public BoggleClient value = null;

  public BoggleClientHolder ()
  {
  }

  public BoggleClientHolder (BoggleClient initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = BoggleClientHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    BoggleClientHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return BoggleClientHelper.type ();
  }

}
