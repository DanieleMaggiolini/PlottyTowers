package enemies;

import static helpz.Constants.Enemy.OROCHIMARU;
import managers.EnemyManager;

public class Orochimaru extends Enemy{

    public Orochimaru(float x, float y, int id, EnemyManager em) {
        super(x, y, id, OROCHIMARU, em);
        
    }
    
}
