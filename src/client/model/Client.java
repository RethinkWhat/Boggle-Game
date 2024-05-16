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
            props.put("org.omg.CORBA.ORBInitialHost","192.168.23.183");//change the host
            ORB orb = ORB.init(args, props);
            System.out.println("started");
//          ORB orb = ORB.init(args, null);
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
