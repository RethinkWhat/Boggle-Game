package client;

import client.model.BoggleApp.BoggleClient;
import client.model.BoggleApp.BoggleClientHelper;
import org.omg.CORBA.IntHolder;
import org.omg.CORBA.LongHolder;
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

            wfImpl = BoggleClientHelper.narrow(ncRef.resolve_str(name));

            ExecutorService executor = Executors.newFixedThreadPool(10);
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    IntHolder timeRem = new IntHolder();
                    int gameID = wfImpl.attemptJoin("username", timeRem);

                    System.out.println("TIME REMAINING: " + timeRem.value);

                    System.out.println("GAME ID: " + gameID);

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
