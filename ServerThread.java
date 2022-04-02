import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

// A class to enable the creation of a multithreaded server
public class ServerThread extends Thread {

    PrintWriter out;
    private Socket s;
    private ChatServer server;
    String username;
    // Constructor
    public ServerThread(Socket client, ChatServer chatServer) {
        s = client;
        this.server = chatServer;
    }
    // Method to set up a BufferedReader to take input and PrintWriter to send output to server
    public void run() {
        try {
            InputStreamReader inp = new InputStreamReader(s.getInputStream());
            BufferedReader in = new BufferedReader(inp);
            out = new PrintWriter(s.getOutputStream(), true);
            username = in.readLine();
            while (true) {
                String userInput = in.readLine();
                out.println(userInput);
                server.sendAll(userInput); // Sends input to all clients
            }
        } catch (IOException e1) {
            System.out.println("This client is disconnected.");
        }
    }
    // Method to print string when called by ChatServer class
    public void printString(String Print) {
        out.println(Print);
        out.flush();
    }

}
