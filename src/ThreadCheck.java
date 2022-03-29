import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// A class to facilitate the closing of threads
public class ThreadCheck extends Thread {

    //Constructor
    public ThreadCheck(ChatServer server) {
        this.server = server;
    }
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    ChatServer server;
    // Method to read input and close all threads if user inputs 'EXIT'
    public void run() {
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


