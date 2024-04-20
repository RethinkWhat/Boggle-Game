package client.model;

import client.model.WordFactoryApp.WordFactory;
import client.model.WordFactoryApp.WordFactoryHelper;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

public class Client {

    private static WordFactory wfImpl;

    public void run(String[] args) {
        try {
            ORB orb = ORB.init(args, null);

            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            String name = "WordFactory";

            wfImpl = WordFactoryHelper.narrow(ncRef.resolve_str(name));

            System.out.println(wfImpl.sayHello());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
