package server.model.BoggleApp;


/**
* server.model.client.model.BoggleApp/accountDoesNotExist.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from shared/BoggleClient.idl
* Thursday, May 2, 2024 3:01:08 PM PST
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
