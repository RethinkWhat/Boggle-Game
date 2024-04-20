package server.controller;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import server.model.BoggleApp.BoggleClient;
import server.model.BoggleApp.BoggleClientHelper;
import server.model.ServerImplementation;

public class Server {

    public void run(String[] args) {
        try {
            ORB orb = ORB.init(args, null);

            POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootpoa.the_POAManager().activate();

            ServerImplementation serverImpl = new ServerImplementation();

            org.omg.CORBA.Object ref = rootpoa.servant_to_reference(serverImpl);
            BoggleClient href = BoggleClientHelper.narrow(ref);

            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");

            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
            String path = "WordFactory";
            NameComponent[] pathName = ncRef.to_name(path);

            ncRef.rebind(pathName, href);

            orb.run();

            System.out.println("Server is running...");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
