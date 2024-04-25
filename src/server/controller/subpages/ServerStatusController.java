package server.controller.subpages;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import server.controller.Server;
import server.model.BoggleApp.BoggleClient;
import server.model.BoggleApp.BoggleClientHelper;
import server.model.ServerImplementation;
import server.model.subpages.ServerStatusModel;
import server.view.subpages.ServerStatusView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerStatusController {
    private ServerStatusView view;
    private ServerStatusModel  model;

    private Server server = new Server();

    private String[] args = {"-ORBINITIALHOST", "localhost", "-ORBINITIALPORT", "5000"};

    public ServerStatusController(ServerStatusView view, ServerStatusModel model) {
        this.view = view;
        this.model = model;

        view.setServerListener(new ServerSwitchListener());
    }

    class ServerSwitchListener implements ActionListener{


        @Override
        public void actionPerformed(ActionEvent e) {

            if (view.getServerStatus().getText().equals("OFFLINE")){
                try {
                    view.setOnline();
                    server.run(args);
                }catch (Exception serverError){
                    view.setOffline();
                    server.stop();
                }

            }
        }
    }

}
