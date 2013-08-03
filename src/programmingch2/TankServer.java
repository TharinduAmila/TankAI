package programmingch2;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dell PC
 */
public class TankServer extends Thread {
    
    ServerSocket serverSoc;
    Socket soc;
    BufferedReader output;
    InputStreamReader st;
    Game game;
    String serverResponse;
    
    
    public TankServer(Game g){
        this.game=g;
        
    
    }
   
    @Override
    public void run(){
        try {
            serverSoc = new ServerSocket(7000);
            while(true)
            {
                soc= serverSoc.accept();
                st=new InputStreamReader(soc.getInputStream());
                output=new BufferedReader(st);
                serverResponse=output.readLine();
                System.out.println(serverResponse);
                this.game.decode(serverResponse);
                
            }
            
        } catch (IOException ex) {
            Logger.getLogger(TankServer.class.getName()).log(Level.SEVERE, null, ex);
        }
     
    }
        
}
