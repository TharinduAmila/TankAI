/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package programmingch2;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Dell
 */
public class Gui extends Thread{
AppGameContainer app;
static Game g;
    public Gui() throws SlickException{
        g= new Game();
        app = null;
    }
    
    @Override
    public void run() {
        System.out.println("aaaaaaaaaaaaaaaaaa");
    if(app==null)
    try {
        app = new AppGameContainer(g);
        app.setDisplayMode(1024+32, 640, false);
        app.start();
    } catch (SlickException ex) {
        Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
}
