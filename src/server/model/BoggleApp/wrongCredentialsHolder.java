package server.model.BoggleApp;

/**
* BoggleApp/wrongCredentialsHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from BoggleClient.idl
* Thursday, May 2, 2024 1:23:41 PM PST
*/

public final class wrongCredentialsHolder implements org.omg.CORBA.portable.Streamable
{
  public wrongCredentials value = null;

  public wrongCredentialsHolder ()
  {
  }

  public wrongCredentialsHolder (wrongCredentials initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = wrongCredentialsHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    wrongCredentialsHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return wrongCredentialsHelper.type ();
  }

}
