
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
}
