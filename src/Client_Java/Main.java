package Client_Java;

import Client_Java.model.Client;
import shared.SwingStylesheet;

import java.util.Properties;

public class Main {
    public Main() {
    }

    public static void main(String[] var0) {
        SwingStylesheet.loadFonts();
        Properties props = new Properties();
        props.put("org.omg.CORBA.ORBInitialPort","1500");//change the port
        props.put("org.omg.CORBA.ORBInitialHost","100.84.168.124");//change the host
        (new Client()).run(props);

    }
}