import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ThreadCheck extends Thread {

    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    ChatServer server;

    public ThreadCheck(ChatServer server) {
        this.server = server;
    }

    public void go() {
        try {
            while (true) {
                String userInput = in.readLine();
                if (userInput.equals("EXIT")) {
                    server.closeThreads();
                    System.exit(0);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IO error. Please try again.");
        }
    }
}


