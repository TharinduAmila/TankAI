package programmingch2;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author Dell PC
 */
public class Client {

    public void connect(String input) {

        String serverName = "127.0.0.1";
        Socket clientSoc;
        PrintWriter output;

        try {

            clientSoc = new Socket(serverName, 6000);
            output = new PrintWriter(clientSoc.getOutputStream(),true);
            output.print(input);
            output.close();
            clientSoc.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
