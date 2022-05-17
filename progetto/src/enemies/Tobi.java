package enemies;

import static helpz.Constants.Enemy.TOBI;
import managers.EnemyManager;

public class Tobi extends Enemy{

    public Tobi(float x, float y, int id, EnemyManager em) {
        super(x, y, id, TOBI, em);
     
    }
    
}
