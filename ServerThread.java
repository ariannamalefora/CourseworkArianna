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

    public ServerThread(Socket client, ChatServer chatServer) {
        s = client;
        this.server = chatServer;
    }

    public void go() {
        try {
            InputStreamReader inp = new InputStreamReader(s.getInputStream());
            BufferedReader in = new BufferedReader(inp);
            out = new PrintWriter(s.getOutputStream(), true);

            while (true) {
                String userInput = in.readLine();
                out.println(userInput);
                server.sendAll(userInput); // Sends input to all clients
            }
        } catch (IOException e1) {
            System.out.println("Disconnected");
        }
    }

    public void printString(String Print) {
        // Prints string when called by ChatServer class
        out.println(Print);
        // used to force to print
       out.flush();
    }

}
