/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package programmingch2;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 *
 * @author Dell
 */
public class ReadMessage {

    static int[][] map;//int[x][y]; //bricks = 7 ,stone = 8, water = 9, player = 10
    static int[][] playFace = new int[5][7]; // [player_number][face = 0 , x =1, y =2,shot = 3,health = 4,coins =5,points= 6]
    //static int[][][] cost = new int[10][10][3]; //
    static int playernumber = 0;
    static StringTokenizer part = null, part2 = null;
    static LinkedList<int[]> coinPiles = new LinkedList<int[]>();
    static LinkedList<int[]> healthPacks = new LinkedList<int[]>();
    static int size = 0;
    
    public static void init(int z){
        size = z;
        map = new int[size][size];
        for (int y = 0; y < size; y++) {
                for (int x = 0; x <size; x++) {
                        map[x][y] = 6;
                }
        }
        Astarfor2dgrid.init(z);
    }
    
    public static void main(String[] args) {
        //read("S:P0;0,0;0:P1;0,9;0:P2;9,0;0:P3;9,9;0:P4;5,5;0#");
        read("I:P0:5,4;7,6:3,1;5,3;7,2;1,4;8,6;0,8;2,4;9,3:1,3;0,4;7,1;7,8;2,3;6,8;4,3;9,8;3,6;2,6#");
        //read("C:4,4:58975:1542#");
        //read("L:4,4:589#");
        //read("G:P0;0,0;2;0;100;0;0:P1;0,9;1;0;100;0;0:P2;9,0;3;0;100;0;0:P3;9,9;3;0;100;0;0:P4;5,5;0;0;100;0;0:8,6,0;0,8,0;2,3,0;3,1,0;5,3,0#");
        for(int[] x: coinPiles){
            System.out.println(x[0]+" "+x[1]+": LT"+x[2]+" VAL"+x[3]);
        }
        for(int[] x: healthPacks){
            System.out.println(x[0]+" "+x[1]+": LT"+x[2]);
        }
        //System.out.println("My number :"+playernumber);
//        map[4][3] = 7;
//        map[6][8] = 7;
//        for (int y = 0; y < 10; y++) {
//                for (int x = 0; x <10; x++) {
//                    System.out.print(map[x][y]+" ");
//                }
//                System.out.println("");
//            }
//        for (int[] i : playFace) {
//            System.out.println(i[0]);
//        }
//        read("G:P0;3,0;1;0;100;0;0:P1;3,9;1;0;100;0;0:P2;9,0;0;0;100;0;0:4,3,4;6,8,2#");
//        for (int y = 0; y < 10; y++) {
//                for (int x = 0; x <10; x++) {
//                    System.out.print(map[x][y]+" ");
//                }
//                System.out.println("");
//            }
//        
//        for (int[] i : playFace) {
//            System.out.println(i[0]);
//        }
        ReadMessage.display();
        if(programmingch2.Astarfor2dgrid.A(new int[]{2,1}, new int[]{8,3}, map))
            programmingch2.Astarfor2dgrid.getPathHeuristics(new int[]{2,1}, new int[]{8,3});
//        if(programmingch2.Astarfor2dgrid.A(new int[]{2,1}, new int[]{2,1}, map))
//            programmingch2.Astarfor2dgrid.getPathHeuristics(new int[]{2,1}, new int[]{2,1});
//        if(programmingch2.Astarfor2dgrid.A(new int[]{2,1}, new int[]{5,5}, map))
//            programmingch2.Astarfor2dgrid.getPathHeuristics(new int[]{2,1}, new int[]{5,5});
            //System.out.println("   ");
        programmingch2.Astarfor2dgrid.showPath();
        
    }
   
    public static void read(String msg) {
        part = new StringTokenizer(msg, ":#");
        String type = part.nextToken();
        if (type.equals("I")) {
            playernumber = Integer.parseInt(part.nextToken().split("P")[1]);
            byte loop = 0;
            while (part.hasMoreTokens()) {
                byte set;
                if(loop==0){
                    set = 7; //Bricks
                }else if(loop==1){
                    set = 8; //Stone
                }else{
                    set = 9; //Water
                }
                part2 = new StringTokenizer(part.nextToken(), ";,");
                while (part2.hasMoreTokens()) {
                    map[Integer.parseInt(part2.nextToken())][Integer.parseInt(part2.nextToken())] = set;
                }
                loop++;
            }
            //display();
        }else if(type.equals("C")){
            String[] a = part.nextToken().split(",");
            int[] temp = new int[]{Integer.parseInt(a[0]),Integer.parseInt(a[1]),Integer.parseInt(part.nextToken()),Integer.parseInt(part.nextToken())};
            coinPiles.add(temp);
            new RemoveListObject(coinPiles,temp,temp[2]);
        }else if(type.equals("L")){
            String[] a = part.nextToken().split(",");
            //System.out.println("x :"+a[0]+" y :"+a[1]);           //map[Integer.parseInt(part2.nextToken())][Integer.parseInt(part2.nextToken())] = set;
            //System.out.println("LT :"+part.nextToken());
            int[] temp = new int[]{Integer.parseInt(a[0]),Integer.parseInt(a[1]),Integer.parseInt(part.nextToken())};
            healthPacks.add(temp);
            new RemoveListObject(healthPacks,temp,temp[2]);
        }else if(type.equals("S")){
            while(part.hasMoreTokens()){
                part2 = new StringTokenizer(part.nextToken(), "P;,");
                byte temp = Byte.parseByte(part2.nextToken());
                playFace[temp][1]= Integer.parseInt(part2.nextToken());
                playFace[temp][2] = Integer.parseInt(part2.nextToken());
                //map[playFace[temp][1]][playFace[temp][2]] = 10;
                playFace[temp][0] = Byte.parseByte(part2.nextToken());
            }
        }else if(type.equals("G")){
            while(part.hasMoreTokens()){
                String temp1 = part.nextToken();
            //System.out.println(temp);
                if(temp1.contains("P")){
                    part2 = new StringTokenizer(temp1, "P;,");
                    byte temp = Byte.parseByte(part2.nextToken());
                    playFace[temp][1]= Integer.parseInt(part2.nextToken());
                    playFace[temp][2] = Integer.parseInt(part2.nextToken());
                    //map[playFace[temp][1]][playFace[temp][2]] = 10;
                    playFace[temp][0] = Integer.parseInt(part2.nextToken());
                    playFace[temp][3] = Integer.parseInt(part2.nextToken());
                    playFace[temp][4] = Integer.parseInt(part2.nextToken());
                    playFace[temp][5] = Integer.parseInt(part2.nextToken());
                    playFace[temp][6] = Integer.parseInt(part2.nextToken());
                }else{
                    part2 = new StringTokenizer(temp1, ";,");
                    int x = Integer.parseInt(part2.nextToken());
                    int y = Integer.parseInt(part2.nextToken());
                    if(part2.nextToken().equals("4")){
                        map[x][y] = 6;
                    }
                }
            }
        } 
    }
    
    public static void display(){
        for(int[] x: coinPiles){
            System.out.println(x[0]+" "+x[1]+": LT"+x[2]+" VAL"+x[3]);
        }
        for(int[] x: healthPacks){
            System.out.println(x[0]+" "+x[1]+": LT"+x[2]);
        }
        for (int y = 0; y < size; y++) {
                for (int x = 0; x <size; x++) {
                    System.out.print(map[x][y]-6+" ");
                }
                System.out.println("");
            }
    }
}
