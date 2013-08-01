/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package programmingch2;

import java.util.Arrays;
import java.util.ListIterator;
import static programmingch2.ReadMessage.*;
import programmingch2.Astarfor2dgrid.*;
/**
 *
 * @author Dell
 */

public class finiteStateMachine {
    /*states ->>
     *  Evade  ,  Attack,  Collect coins, collect health
     * Needed heuristics ->>
     *  Threat level, Coin state
     */ 
    static int[][] value;
    static int size = 0;
    public finiteStateMachine(int size) {
        finiteStateMachine.size = size;
        Astarfor2dgrid.init(size);
        ReadMessage.init(size);
        value = new int[size][size];
    }
    private void valueReset(){
        for (int i = 0; i < size; i++) {
                    for (int j = 0; j < size; j++) {
                        finiteStateMachine.value[i][j] = Integer.MAX_VALUE;
                    }
                }
    } 
    public String getDecision(){
        //Arrays.fill(value,Integer.MAX_VALUE);
        valueReset();
        int x = 0,y = 0,weight = Integer.MAX_VALUE;
        String Ret = "";
        Object[] now = ReadMessage.coinPiles.toArray();
        for(Object t : now){
            int[] it = (int[]) t;
            for(int i = 0; i<playFace.length;i++){
                if(it[0]==playFace[i][1]&&it[1]==playFace[i][2])
                {
                    if(ReadMessage.coinPiles.contains(it))
                        ReadMessage.coinPiles.remove(it);
                    break;
                }
            }
        }
        int playX = playFace[playernumber][1],playY = playFace[playernumber][2];
        now = ReadMessage.coinPiles.toArray();
        for(Object temp : now){
            int[] k = (int[])temp;
            if(Astarfor2dgrid.A(new int[]{playX,playY}, new int[]{k[0],k[1]}, map)){
                Astarfor2dgrid.getPathHeuristics(new int[]{playX,playY}, new int[]{k[0],k[1]});
                System.out.println("Run Astar");
            }
        }   
        System.out.println("Run Astar Finished");
        
        if(playY+1 <size && value[playX][playY+1]<weight){
            weight = value[playX][playY+1];
            x = playX;
            y = playY+1;
            Ret = "DOWN#";
        }
        if(playY-1 >-1 && value[playX][playY-1]<weight){
            weight = value[playX][playY-1];
            x = playX;
            y = playY-1;
            Ret = "UP#";
        }
        if(playX+1 <size && value[playX+1][playY]<weight){
            weight = value[playX+1][playY];
            x = playX+1;
            y = playY;
            Ret = "RIGHT#";
        }
        if(playX-1 >-1 && value[playX-1][playY]<weight){
            weight = value[playX-1][playY];
            x = playX-1;
            y = playY;
            Ret = "LEFT#";
        }
        System.out.println("X : "+x+" Y :"+y);
        if(Ret != "")
            return Ret;
        else
           return "SHOOT#";
    }
}