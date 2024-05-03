package client.model;

import client.controller.LoginController;
import client.model.BoggleApp.BoggleClient;
import client.model.BoggleApp.BoggleClientHelper;
import client.view.LoginView;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;


public class Client {

    private static BoggleClient wfImpl;

    public void run (String args[]) {
        try {
            System.out.println("started");
            ORB orb = ORB.init(args, null);
            System.out.println("orb initialized");
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            System.out.println("name service made + " + objRef);
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
            System.out.println("ncRef defined " + ncRef);
            String name = "WordFactory";
            System.out.println("Starting client.");

            System.out.println("reached");
            wfImpl = BoggleClientHelper.narrow(ncRef.resolve_str(name));
            System.out.println("reached2");
            new LoginController(new LoginView(), new LoginModel(wfImpl));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
