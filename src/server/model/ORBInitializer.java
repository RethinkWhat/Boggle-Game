package server.model;

public class ORBInitializer {
    public static void run(){
        String terminalCommand = "ORBD -ORBInitialPort 1500 -ORBInitialHost localhost";//change as needed

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
