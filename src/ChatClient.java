import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

// A class for a chat client
public class ChatClient {

    private Socket s;
    private Thread t;
    // Constructor
    public ChatClient(String address, int port) {
        try {
            t = new ClientThread(address, port); // Declares a new client thread
            t.start(); // Starts thread to read output and print it to console
            s = new Socket(address, port);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Server connection could not be established.");
        }
    }
    // Method to set up a BufferedReader to take input and PrintWriter to send output to server
    public void go() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter out = new PrintWriter(s.getOutputStream(), true);
            while (true) {
                String userInput = in.readLine();
                out.println(userInput);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IO error. Please try again.");
        } finally {
            try {
                s.close();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Connection to server could not be closed.");
            } catch (NullPointerException e) {
                e.printStackTrace();
                System.out.println("Connection to server could not be closed.");
            }
        }
    }
    // Method to establish default variable values and allow parameters to be passed through to alter these
    public static void main(String[] args) {
        String ipAddress = "localhost";
        int port = 14005;
        for (int i = 0; i < args.length - 1; i++) {
            if (args[i].equals("-cca")) { // Request to ChatClient to bind to another IP address
                ipAddress = args[i + 1];
            } else {
                System.out.println("Invalid address. Please try again.");
            }
            if (args[i].equals("-ccp")) { // Request to ChatClient to bind to another port
                try {
                    port = Integer.parseInt(args[i + 1]);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid port. Please try again.");
                }
            }
        }
        // Creates new instance of ChatClient with either the default values or new parameters entered
        new ChatClient(ipAddress, port).go();
    }

}
