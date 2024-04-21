package client.model;

import client.controller.LoginController;
import client.model.BoggleApp.BoggleClient;
import client.model.BoggleApp.BoggleClientHelper;
import client.view.LoginView;
import org.omg.CORBA.BooleanHolder;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;


public class Client {

    private static BoggleClient wfImpl;

    public void run (String args[]) {
        try {

            ORB orb = ORB.init(args, null);

            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            String name = "WordFactory";
            System.out.println("Starting client.");

            wfImpl = BoggleClientHelper.narrow(ncRef.resolve_str(name));

            new LoginController(new LoginView(), new LoginModel(wfImpl));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
