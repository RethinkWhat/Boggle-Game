package server.controller;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.InvalidName;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import org.omg.PortableServer.POAPackage.ObjectNotActive;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;
import server.model.BoggleApp.BoggleClient;
import server.model.BoggleApp.BoggleClientHelper;
import server.model.DataPB;
import server.model.ServerImplementation;

public class Server{

    private ORB orb;

    private POA rootpoa;

    private ServerImplementation serverImpl;

    org.omg.CORBA.Object ref;

    BoggleClient href;

    org.omg.CORBA.Object objRef;

    NamingContextExt ncRef;

    String path = "WordFactory";

    NameComponent[] pathname;

    public void run(String[] args) {
        try {
            this.orb = ORB.init(args, null);

            this.rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootpoa.the_POAManager().activate();

            this.serverImpl = new ServerImplementation();

            this.ref = rootpoa.servant_to_reference(this.serverImpl);
            this.href = BoggleClientHelper.narrow(this.ref);

            this.objRef = orb.resolve_initial_references("NameService");

            this.ncRef = NamingContextExtHelper.narrow(objRef);
            this.path = "WordFactory";
            this.pathname = ncRef.to_name(path);

            ncRef.rebind(this.pathname, this.href);

            orb.run();

            System.out.println("Server is running...");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void stop(){
        try {
            rootpoa.deactivate_object(rootpoa.servant_to_id(serverImpl));
            System.out.println("Server is stopped.");
        } catch (WrongPolicy e) {
            throw new RuntimeException(e);
        } catch (ObjectNotActive e) {
            throw new RuntimeException(e);
        } catch (ServantNotActive e) {
            throw new RuntimeException(e);
        }
    }

    public ServerImplementation getServerImpl() {
        return serverImpl;
    }
}
