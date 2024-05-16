package Client_Java.model;

import Client_Java.controller.LoginController;
import Client_Java.model.BoggleApp.BoggleClient;
import Client_Java.model.BoggleApp.BoggleClientHelper;
import Client_Java.view.LoginView;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import java.util.Properties;


public class Client {

    private static BoggleClient wfImpl;

    public void run (Properties props) {
        try {
            ORB orb = ORB.init((String[]) null, props);

            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            System.out.println("name service made + " + objRef);
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
            System.out.println("ncRef defined " + ncRef);
            String name = "WordFactory";

            wfImpl = BoggleClientHelper.narrow(ncRef.resolve_str(name));
            new LoginController(new LoginView(), new LoginModel(wfImpl));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
