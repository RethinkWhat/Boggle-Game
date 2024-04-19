package client;

import client.model.BoggleApp.BoggleClient;
import client.model.BoggleApp.BoggleClientHelper;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

public class Main {

    private static BoggleClient wfImpl;

    public static void main(String[] args) {
        try {
            ORB orb = ORB.init(args, null);

            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            String name = "WordFactory";

            wfImpl = BoggleClientHelper.narrow(ncRef.resolve_str(name));

            System.out.println(wfImpl.attemptJoin("d"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
