package server.model;

public class ORBInitializer {
    public static void run(){
        String terminalCommand = "ORBD -ORBInitialPort 1500 -ORBInitialHost 192.168.23.183";//change as needed

        Thread thread = new Thread(() -> {
            try {
                while (true) {
                    Process process = Runtime.getRuntime().exec(terminalCommand);
                    process.waitFor();
                }
            } catch (Exception ORBError) {
                ORBError.printStackTrace();
            }
        });
        thread.start();
    }
}
