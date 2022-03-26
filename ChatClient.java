import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

// Class for a chat client
public class ChatClient {

    private static Socket client;

    public static void main(String[] args) {
        try {
            Socket s = new Socket("localhost",14001);

            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintWriter out = new PrintWriter(s.getOutputStream(),true);

            BufferedReader user = new BufferedReader(new InputStreamReader(System.in));

            while(true) {
                String userInput = user.readLine();
                out.println(userInput);
                String back = in.readLine();
                System.out.println(back);
            }
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