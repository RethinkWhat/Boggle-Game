package server.model.BoggleApp;

/**
* BoggleApp/userInfoHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from BoggleClient.idl
* Thursday, May 2, 2024 1:23:41 PM PST
*/

public final class userInfoHolder implements org.omg.CORBA.portable.Streamable
{
  public userInfo value = null;

  public userInfoHolder ()
  {
  }

  public userInfoHolder (userInfo initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = userInfoHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    userInfoHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return userInfoHelper.type ();
  }

}
