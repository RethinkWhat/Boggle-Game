package server.model.BoggleApp;

/**
* server.model.client.model.BoggleApp/updateFailedHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from shared/BoggleClient.idl
* Thursday, May 2, 2024 3:01:08 PM PST
*/

public final class updateFailedHolder implements org.omg.CORBA.portable.Streamable
{
  public updateFailed value = null;

  public updateFailedHolder ()
  {
  }

  public updateFailedHolder (updateFailed initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = updateFailedHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    updateFailedHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return updateFailedHelper.type ();
  }

}