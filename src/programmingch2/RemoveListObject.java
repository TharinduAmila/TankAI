/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package programmingch2;

import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Dell
 */
public class RemoveListObject extends TimerTask{
    private LinkedList<int[]> list;
    private Timer timer;
    private int[] remove;
    RemoveListObject(LinkedList  in,int[] obj,int time){
        list = in;
        remove = obj;
        timer = new Timer();
        timer.schedule(this, time);
    }
    @Override
    public void run() {
        if(list.contains(remove))
        list.remove(remove);
        timer.cancel();
    }
    
}
