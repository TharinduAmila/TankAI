/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package programmingch2;

import static programmingch2.ReadMessage.*;
import programmingch2.Astarfor2dgrid.*;
/**
 *
 * @author Dell
 */

public class finiteStateMachine {
    /*states ->>
     *  Evade  ,  Attack,  [half]Collect coins, [ok]collect health
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
    private void clearLists(){
        Object[] nowCoins = ReadMessage.coinPiles.toArray();
        for(Object t : nowCoins){
            int[] it = (int[]) t;
            for(int i = 0; i<playFace.length;i++){
                if(playFace[i][4]>0 && it[0]==playFace[i][1]&&it[1]==playFace[i][2])
                {
                    if(ReadMessage.coinPiles.contains(it))
                        ReadMessage.coinPiles.remove(it);
                    break;
                }
                
            }
            it[2] -= 1000; 
        }
        Object[] nowHealth = ReadMessage.healthPacks.toArray();
        for(Object t : nowHealth){
            int[] it = (int[]) t;
            for(int i = 0; i<playFace.length;i++){
                if(playFace[i][4]>0 && it[0]==playFace[i][1]&&it[1]==playFace[i][2])
                {
                    if(ReadMessage.healthPacks.contains(it))
                        ReadMessage.healthPacks.remove(it);
                    break;
                }
                
            }
            it[2] -= 1000;
        }
    }
    public String getDecision(){
        //Arrays.fill(value,Integer.MAX_VALUE);
        valueReset();
        int x = 0,y = 0,weight = Integer.MAX_VALUE;
        String Ret = "";
        clearLists();
        int playX = playFace[playernumber][1],playY = playFace[playernumber][2];
        Object[] now;
        //state Selection
        if(playFace[playernumber][4]>50 && !ReadMessage.coinPiles.isEmpty()){
            now = ReadMessage.coinPiles.toArray();
        }else if(playFace[playernumber][4]<50 && !ReadMessage.healthPacks.isEmpty()){
            now = ReadMessage.healthPacks.toArray();
        }else{
            now = ReadMessage.coinPiles.toArray();
        }
        for(Object temp : now){
            int[] k = (int[])temp;
            if(Astarfor2dgrid.A(new int[]{playX,playY}, new int[]{k[0],k[1]}, map)){
                Astarfor2dgrid.getPathHeuristics(new int[]{playX,playY}, new int[]{k[0],k[1]},k);
                //System.out.println("Run Astar");
            }
        }   
        //System.out.println("Run Astar Finished");
        
        if(playY+1 <size && value[playX][playY+1]<weight){
            weight = value[playX][playY+1];
            x = playX;
            y = playY+1;
            Ret = "DOWN#";
        }
        if(playX+1 <size && value[playX+1][playY]<weight){
            weight = value[playX+1][playY];
            x = playX+1;
            y = playY;
            Ret = "RIGHT#";
        }
        if(playY-1 >-1 && value[playX][playY-1]<weight){
            weight = value[playX][playY-1];
            x = playX;
            y = playY-1;
            Ret = "UP#";
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
