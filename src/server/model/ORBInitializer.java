package server.model;

public class ORBInitializer {
    public static void run(){
        String terminalCommand = "ORBD -INITIALPORT 5000 -INITIALHOST localhost";

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
