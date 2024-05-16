package Server_Java.model;

public class ORBInitializer {
    public static void run(String args){
        String terminalCommand = "ORBD -ORBInitialPort 1500 -ORBInitialHost 100.84.168.124";//change as needed

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
