package client.model.BoggleApp;

/**
* client.model.BoggleApp/accountDoesNotExistHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from shared/BoggleClient.idl
* Thursday, May 2, 2024 6:17:38 PM PST
*/

public final class accountDoesNotExistHolder implements org.omg.CORBA.portable.Streamable
{
  public accountDoesNotExist value = null;

  public accountDoesNotExistHolder ()
  {
  }

  public accountDoesNotExistHolder (accountDoesNotExist initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = accountDoesNotExistHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    accountDoesNotExistHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return accountDoesNotExistHelper.type ();
  }

}
