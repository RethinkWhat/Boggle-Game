package client.model.BoggleApp;


/**
* BoggleApp/accountDoesNotExist.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from BoggleClient.idl
* Monday, May 6, 2024 7:26:54 PM SGT
*/

public final class accountDoesNotExist extends org.omg.CORBA.UserException
{
  public String reason = null;

  public accountDoesNotExist ()
  {
    super(accountDoesNotExistHelper.id());
  } // ctor

  public accountDoesNotExist (String _reason)
  {
    super(accountDoesNotExistHelper.id());
    reason = _reason;
  } // ctor


  public accountDoesNotExist (String $reason, String _reason)
  {
    super(accountDoesNotExistHelper.id() + "  " + $reason);
    reason = _reason;
  } // ctor

} // class accountDoesNotExist
