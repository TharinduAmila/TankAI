/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package programmingch2;
import java.io.*;
import java.net.*;
import java.util.Timer;
import java.util.TimerTask;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;
/**
 *
 * @author Dell
 */
public class Programmingch2 {
    static String read;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, SlickException {
        
        //ReadMessage.init(10);
        new Gui().start();
        Timer t = new Timer();
        t.schedule(new TimerTask() {
            String task ="";
            @Override
            public void run() {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                //System.out.println("aaaaaaaaaaaaaaaaaaaaaa");
                if(read!=null && Gui.g!=null && !task.equals(read))
                Gui.g.decode(read);
                if(read!=null)
                task = read;
            }
        } ,0,50);
        finiteStateMachine fm = new finiteStateMachine(20);
        ServerSocket server = null;
        Socket output = null;
        Socket input = null;
        PrintWriter out = null;
        BufferedReader in = null;
        String min, mout;
        try {
            server = new ServerSocket(7000);
            output = new Socket("127.0.0.1", 6000);
            out = new PrintWriter(output.getOutputStream(), true);
            
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host.");
            System.exit(1);
        }
        mout = "JOIN#";
        sendMessage(mout, out);
        out.close();
        do {
            input = server.accept();
            in = new BufferedReader(new InputStreamReader(input.getInputStream()));
            min = in.readLine();
            read = min;
            if(min.equals("GAME_HAS_FINISHED#"))
                System.exit(0);
            ReadMessage.read(min);
            if(min.startsWith("G:")){
                ReadMessage.playDisplay();
                for (int i = 0; i < 600; i++) {
                    for (int j = 0; j < 600; j++) {
                        System.out.print("");
                    }
                }
                System.out.println("");
                output = new Socket("127.0.0.1", 6000);
                out = new PrintWriter(output.getOutputStream(), true);
                mout = fm.getDecision();
                //algo to decide what to do
                sendMessage(mout, out);
                out.close();
            }
            System.out.println("client>" + min);
        } while (true);

        
//        server.close(); 
//        output.close();

    }

    static void sendMessage(String msg, PrintWriter out) {
        try {
            out.write(msg);
            out.flush();
            System.out.println("server>" + msg);
        } catch (Exception ioException) {
            ioException.printStackTrace();
        }
    }
}
