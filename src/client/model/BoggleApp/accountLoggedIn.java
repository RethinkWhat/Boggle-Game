package client.model.BoggleApp;


/**
* BoggleApp/accountLoggedIn.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from BoggleClient.idl
* Monday, May 6, 2024 7:26:54 PM SGT
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
