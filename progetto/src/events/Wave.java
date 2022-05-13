package events;

import java.util.ArrayList;

public class Wave {
    private ArrayList<Integer> enemieswave;

    public Wave(ArrayList<Integer> enemieswave) {
        this.enemieswave = enemieswave;
    }
    
    
    public ArrayList<Integer> getEnemiesWave(){
        return enemieswave;
    }
}
