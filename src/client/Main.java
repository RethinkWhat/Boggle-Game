package client;

import client.model.BoggleApp.BoggleClient;
import client.model.BoggleApp.BoggleClientHelper;
import org.omg.CORBA.BooleanHolder;
import org.omg.CORBA.IntHolder;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    private static BoggleClient wfImpl;

    public static void main(String[] args) {
        try {
            ORB orb = ORB.init(args, null);

            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            String name = "WordFactory";
            System.out.println("Starting client.");

            wfImpl = BoggleClientHelper.narrow(ncRef.resolve_str(name));

            long id =0;
            BooleanHolder startGame = new BooleanHolder(false);
            while (!startGame.value) {
                id = wfImpl.attemptJoin("username", startGame);
                System.out.println("TIME REMAINING: " + id);
                if (id == 0)
                    break;
                Thread.sleep(1000);
            }
            System.out.println("GAME ID: " + id);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
