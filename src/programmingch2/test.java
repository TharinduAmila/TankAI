/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package programmingch2;

import java.util.Arrays;
import java.util.LinkedList;

/**
 *
 * @author Dell
 */
public class test {
    @SuppressWarnings("empty-statement")
    public static void main(String[] args) {
        LinkedList<int[]> k = new LinkedList();
        int[] b = new int[]{2,3};
        //RemoveListObject r = new RemoveListObject(k, b,1000);
        k.add(b);
        System.out.println("b :"+b);
        System.out.println("b1 :"+k.toArray()[0]);
        int[] f = new int[3];
        Arrays.fill(f,2);
        System.out.println(f[0]+" "+f[1]+" "+f[2]);
    }
}
