import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

// A class to enable the creation of client threads
public class ClientThread extends Thread {

    private Socket s;
    // Constructor
    public ClientThread(String address, int port) {
        try {
            s = new Socket(address, port);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Server connection could not be established.");
        }
    }
    // Method to set up a BufferedReader to take in user input
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            while (true) {
                String userInput = in.readLine();
                System.out.println(userInput);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IO error. Please try again.");
        }
    }

}
