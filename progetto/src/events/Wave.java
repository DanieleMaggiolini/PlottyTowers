/**
* @author  Daniele Maggiolini
* @author  Mattia Minotti
* @version 0.0
* @file Wave.java 
* 
* @brief gestisce le ondate.
*
*/
package events;

import java.util.ArrayList;

/**
 * @class Wave
 *
 * @brief gestisce le ondate.
 *
 */
public class Wave {
    
    //ondate
    private ArrayList<Integer> enemieswave;

    /**
     @brief sett ondate.
     * 
     * @param enemieswave ondate di nemici
     */
    public Wave(ArrayList<Integer> enemieswave) {
        this.enemieswave = enemieswave;
    }
    
    /**
     @brief get ondate.
     * 
     * @return ondate nemici
     */
    public ArrayList<Integer> getEnemiesWave(){
        return enemieswave;
    }
}
