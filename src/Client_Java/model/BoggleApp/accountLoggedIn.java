package Client_Java.model.BoggleApp;


/**
* BoggleApp/accountLoggedIn.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from BoggleClient.idl
* Sunday, May 12, 2024 2:16:16 PM PST
*/

public final class accountLoggedIn extends org.omg.CORBA.UserException
{
  public String reason = null;

  public accountLoggedIn ()
  {
    super(accountLoggedInHelper.id());
  } // ctor

  public accountLoggedIn (String _reason)
  {
    super(accountLoggedInHelper.id());
    reason = _reason;
  } // ctor


  public accountLoggedIn (String $reason, String _reason)
  {
    super(accountLoggedInHelper.id() + "  " + $reason);
    reason = _reason;
  } // ctor

} // class accountLoggedIn