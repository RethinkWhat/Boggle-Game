package client.model;

import client.controller.LoginController;
import client.model.BoggleApp.BoggleClient;
import client.model.BoggleApp.BoggleClientHelper;
import client.view.LoginView;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import java.util.Properties;


public class Client {

    private static BoggleClient wfImpl;

    public void run (String args[]) {
        try {
            Properties props = new Properties();
            props.put("org.omg.CORBA.ORBInitialPort","1500");//change the port
            props.put("org.omg.CORBA.ORBInitialHost","100.84.168.124");//change the host
            ORB orb = ORB.init(args, props);
//          ORB orb = ORB.init(args, null);
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
