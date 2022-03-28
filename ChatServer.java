import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

// A class for a chat server
public class ChatServer {

    private ServerSocket ss;
    private Thread t;
    private ArrayList<Thread> tList = new ArrayList<Thread>(); // Array list to store threads
    // Constructor
    public ChatServer(int port) {
        try {
            ss = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Server socket could not be created");
        }
    }
    // Method to check for server input and accept new client threads
    public void run() {
        try {
            Socket s;
            System.out.println("Server is ready");
            Thread tt = new ThreadCheck(this); // Declares a new thread to check server input
            tt.start(); // Starts thread
            while (true) {
                s = ss.accept();
                t = new ServerThread(s, this);
                tList.add(t); // Client added to thread list
                t.start();//
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IO error. Please try again.");
        }
    }
    // Method to establish default port value and allow a parameter to be passed through to alter this
    public static void main(String[] args) {
        int port = 14001;
        for (int i = 0; i < args.length - 1; i++) {
            if (args[i].equals("-ccp")) { // Request to ChatClient to bind to another port
                try {
                    port = Integer.parseInt(args[i + 1]);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid port. Please try again.");
                }
            } else {
                System.out.println("Invalid input");
            }
            // Starts server with either default port or new parameter entered
            ChatServer server = new ChatServer(port);
            server.run();
        }
    }
    // Method to iterate through threads and print a string to client
    public synchronized void sendAll(String send) {
        for (Thread t:tList) {
            ((ServerThread) t).printString(send);
        }
    }
    // Method to close socket and all threads
    public synchronized void closeThreads() {
        try {
            ss.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Socket could not be closed.");
        }
        for (Thread t : tList) {
            t.interrupt();
        }
    }


}
