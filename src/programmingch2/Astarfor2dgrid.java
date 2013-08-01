package programmingch2;


import java.util.ArrayList;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
/**
 *
 * @author Dell
 */
public class Astarfor2dgrid {
    static boolean[][] closed;// = new boolean[10][10];
    static boolean[][] open ;//= new boolean[10][10];
    static int[][][] score ;//= new int[10][10][2];
    static int[][][] camefrom;// = new int[10][10][2];
    static int size;
    public static void init(int z){
        size = z;
        closed = new boolean[z][z];
        open = new boolean[z][z];
        score = new int[z][z][2];
        camefrom = new int[z][z][2];
    }
    public static int h(int si, int sj, int gi, int gj) {
        if((si - gi)!=0 && (sj - gj)!=0)
            return Math.abs(si - gi) + Math.abs(sj - gj)+1;
        else
            return Math.abs(si - gi) + Math.abs(sj - gj);
    }

    static int partition(ArrayList<int[]> A, int p, int r) {
        int x = score[A.get(r)[0]][A.get(r)[1]][1];
        int[] temp;
        int i = p - 1;
        for (int j = p; j <= r - 1; j++) {
            if (score[A.get(j)[0]][A.get(j)[1]][1] <= x) {
                i++;
                temp = A.get(i);
                A.set(i, A.get(j));
                A.set(j, temp);
            }
        }
        temp = A.get(i + 1);
        A.set(i + 1, A.get(r));
        A.set(r, temp);
        return i + 1;
    }

    static void QuickSort(ArrayList<int[]> A, int p, int r) {
        if (p < r) {
            int q = partition(A, p, r);
            QuickSort(A, p, q - 1);
            QuickSort(A, q + 1, r);
        }
    }
    //bricks =7, stones =8, water = 9; travel = 6;
    //up = 0, down = 1, left = 2, right = 3
    public static void showPath(){
        for (int y = 0; y < size; y++) {
                    for (int x = 0; x < size; x++) {
                        System.out.print(" "+camefrom[x][y][0]+" "+camefrom[x][y][1]+"   ");
                    }
                    System.out.println("");
                }
    }
    
    public static void getPathHeuristics(int[] start,int[] goal,int obj[]){
        //System.out.println("Score goal :"+score[goal[0]][goal[1]][0]+"  "+score[goal[0]][goal[1]][1]);
        if(finiteStateMachine.value==null){
            finiteStateMachine.value = new int[10][10];
            for (int i = 0; i < size; i++) {
                    for (int j = 0; j < size; j++) {
                        finiteStateMachine.value[i][j] = 60;
                    }
                }
        }
        //System.out.println("goal :"+goal[0]+"  "+goal[1]);
        //System.out.println("start :"+start[0]+"  "+start[1]);
        int currentX = goal[0], currentY = goal[1],weight = score[goal[0]][goal[1]][0];
        while(true){
            if(currentX==start[0] && currentY==start[1] || weight>obj[2]/1000){
                break;
            }else{
                //System.out.print("("+currentX+","+currentY+")");
                if(finiteStateMachine.value[currentX][currentY]>weight)
                    finiteStateMachine.value[currentX][currentY] = weight;
                int yhold = currentY;
                int xhold = currentX;
                currentX = camefrom[xhold][yhold][0];
                currentY = camefrom[xhold][yhold][1];
            }
        }
        System.out.println("");
    }
    
    public static boolean A(int[] start, int[] goal, int map[][]) {
        init(size);
        ArrayList<int[]> openset = new ArrayList<int[]>();    // The set of tentative nodes to be evaluated, initially containing the start node
        openset.add(start);
        camefrom[start[0]][start[1]][0] = start[0];
        camefrom[start[0]][start[1]][1] = start[1];
        open[start[0]][start[1]] = true;
        score[start[0]][start[1]][0] = 0;
        // Cost from start along best known path.
        // Estimated total cost from start to goal through y.
        //f_score[start] := g_score[start] + heuristic_cost_estimate(start, goal)
        int[] temp = openset.get(0);
        score[temp[0]][temp[1]][1] = 0 + h(temp[0], temp[1], goal[0], goal[1]);
        while (!openset.isEmpty()) {
            QuickSort(openset, 0, openset.size() - 1);
            int[] current = openset.get(0);//the node in openset having the lowest f_score[] value
            if (current[0] == goal[0] && current[1] == goal[1]) {
                return true;
            } else {
                openset.remove(0);
                open[current[0]][current[1]] = false;
                closed[current[0]][current[1]] = true;

                //each neighbor in neighbor_nodes(current)
                if (current[0] + 1 < size && !closed[current[0] + 1][current[1]] && map[current[0] + 1][current[1]] < 7) {
                    int tentative_g_score;
                    int x = current[0] + 1, y = current[1];
                    if (current[0] - camefrom[x][y][0] > 0) {
                        tentative_g_score = score[current[0]][current[1]][0] + 1;
                    } else {
                        tentative_g_score = score[current[0]][current[1]][0] + 2;
                    }
                    if (!open[x][y] || tentative_g_score <= score[x][y][0]) {
                        camefrom[x][y][0] = current[0];
                        camefrom[x][y][1] = current[1];
                        score[x][y][0] = tentative_g_score;
                        if (!open[x][y]) {
                            openset.add(0, new int[2]);
                            open[x][y] = true;
                            openset.get(0)[0] = x;
                            openset.get(0)[1] = y;
                        }
                            score[x][y][1] = score[x][y][0] + h(x, y, goal[0], goal[1]);
                    }
                }
                if (current[0] - 1 > -1 && !closed[current[0] - 1][current[1]] && map[current[0] - 1][current[1]] < 7) {
                    int tentative_g_score;
                    int x = current[0] - 1, y = current[1];
                    if (current[0] - camefrom[x][y][0] < 0) {
                        tentative_g_score = score[current[0]][current[1]][0] + 1;
                    } else {
                        tentative_g_score = score[current[0]][current[1]][0] + 2;
                    }
                    if (!open[x][y] || tentative_g_score <= score[x][y][0]) {
                        camefrom[x][y][0] = current[0];
                        camefrom[x][y][1] = current[1];
                        score[x][y][0] = tentative_g_score;
                        if (!open[x][y]) {
                            openset.add(0, new int[2]);
                            open[x][y] = true;
                            openset.get(0)[0] = x;
                            openset.get(0)[1] = y;
                        }
                            score[x][y][1] = score[x][y][0] + h(x, y, goal[0], goal[1]);
                        
                    }
                }
                if (current[1] + 1 < size && !closed[current[0]][current[1] + 1] && map[current[0]][current[1] + 1] < 7) {
                    int tentative_g_score;
                    int x = current[0], y = current[1] + 1;
                    if (current[1] - camefrom[x][y][1] > 0) {
                        tentative_g_score = score[current[0]][current[1]][0] + 1;
                    } else {
                        tentative_g_score = score[current[0]][current[1]][0] + 2;
                    }
                    if (!open[x][y] || tentative_g_score <= score[x][y][0]) {
                        camefrom[x][y][0] = current[0];
                        camefrom[x][y][1] = current[1];
                        score[x][y][0] = tentative_g_score;
                        if (!open[x][y]) {
                            openset.add(0, new int[2]);
                            open[x][y] = true;
                            openset.get(0)[0] = x;
                            openset.get(0)[1] = y;
                        }
                            score[x][y][1] = score[x][y][0] + h(x, y, goal[0], goal[1]);
                        
                    }
                }
                if (current[1] - 1 > -1 && !closed[current[0]][current[1] - 1] && map[current[0]][current[1] - 1] < 7) {
                    int tentative_g_score;
                    int x = current[0], y = current[1] - 1;
                    if (current[1] - camefrom[x][y][1] < 0) {
                        tentative_g_score = score[current[0]][current[1]][0] + 1;
                    } else {
                        tentative_g_score = score[current[0]][current[1]][0] + 2;
                    }
                    if (!open[x][y] || tentative_g_score <= score[x][y][0]) {
                        camefrom[x][y][0] = current[0];
                        camefrom[x][y][1] = current[1];
                        score[x][y][0] = tentative_g_score;
                        if (!open[x][y]) {
                            openset.add(0, new int[2]);
                            open[x][y] = true;
                            openset.get(0)[0] = x;
                            openset.get(0)[1] = y;
                        } 
                            score[x][y][1] = score[x][y][0] + h(x, y, goal[0], goal[1]);
                    }
                }
            }
        }
        return false;
    }
}