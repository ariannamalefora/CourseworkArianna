import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

// Class for a chat server
public class ChatServer {

    private static Socket client;

    public static void main(String[] args) {
        try {
            ServerSocket s = new ServerSocket(14001);
            Socket client = s.accept();
            System.out.println("Server Started and listening to the port 14001");

            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter out = new PrintWriter(client.getOutputStream(), true);

            String userInput = "";
            while(!userInput.equals("end")) {
                userInput = in.readLine();
                out.println(userInput);
            }

            client.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                client.close();
            }
            catch (Exception e) {
            }
        }


    }

}