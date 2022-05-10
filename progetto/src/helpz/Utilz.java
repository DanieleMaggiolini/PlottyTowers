
package helpz;

import java.util.ArrayList;

public class Utilz {
    //da un arraylist a una matrice
        public static int[][] ArrayListTo2dInt(ArrayList<Integer> list ,int Ysize, int Xsize){
        int[][] matrice = new int[Ysize][Xsize];
        int temp=0;
        for (int j = 0; j < Ysize; j++) {
            for (int i = 0; i < Xsize; i++) {
                //int index= j*Ysize + i;
                matrice[j][i]= list.get(temp);
                temp++;
            }
        }
        return matrice;
    }
    //da una matrice ad un array
    public static int[] twoDToOneDInt(int[][] matrice){
        int[] array= new int[matrice.length* matrice[0].length];
        int temp=0;
        for (int j = 0; j < matrice.length; j++) {
            for (int i = 0; i < matrice[0].length; i++) {
                //int index= j*matrice.length + i;
                array[temp] = matrice[j][i];
                temp++;
            }
        }
        return array;
    }
    public static int getDistance(float x1, float y1, float x2, float y2){
        float xdiff= Math.abs(x1-x2);
        float ydiff= Math.abs(y1-y2);
        return (int)(Math.hypot(xdiff, ydiff));
    }
}
