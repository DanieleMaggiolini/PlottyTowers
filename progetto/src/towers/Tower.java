/**
* @author  Daniele Maggiolini
* @author  Mattia Minotti
* @version 0.0
* @file Tower.java 
* 
* @brief gestisce le torri.
*
*/
package towers;

import static helpz.Constants.Towers.*;

/**
 * @class Tower
 *
 * @brief gestisce gli oggetti delle torri.
 *
 */
public class Tower {
    
    //coordinata x
    private int x;
    
    //coordinata y
    private int y;
    
    //identificatore torre
    private int id;
    
    //tipo di torre
    private int typetower;
    
    //danno 
    private int damage;
    
    //raggio di attacco
    private float range;
    
    //tempo di ricarica del colpo
    private float cooldown;
    
    //tick per la ricarica del colpo
    private int cooldownTick=0;
    
    //numero del livello
    private int lvl;
    
    /**
     @brief costruttore che setta le variabili.
     *  
     * @param x coordinata
     * @param y coordinata
     * @param id identificatore torre
     * @param typetower tipo della torre
     */
    public Tower(int x, int y, int id, int typetower) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.typetower = typetower;
        lvl=1;
        setDamage();
        setRange();
        setCooldown();
    }
    
    /**
     @brief aggiorna il tick del tempo di ricarica
     */
    public void update(){
        cooldownTick++;
    }
    
    /**
     @brief gestisce gli update delle torri
     */
    public void levelUp(){
        this.lvl++;
        switch(typetower){
            case NARUTO:
                damage+=15;
                range+=20;
                break;
            case SASUKE:
                damage+=20;
                range+=30;
                break;
            case SAKURA:
                damage+=30;
                range+=25;
                break;
        }
    }
    
    /**
     @brief ritorna il livello
     * 
     * @return livello
     */
    public int getLvl(){
        return lvl;
    }
    
    /**
     @brief ritorna la x
     * 
     * @return coordinata x
     */
    public int getX() {
        return x;
    }

    /**
     @brief ritorna la y
     * 
     * @return coordinata y
     */
    public int getY() {
        return y;
    }

    /**
     @brief ritorna l'identificatore
     * 
     * @return identificatore
     */
    public int getId() {
        return id;
    }

    /**
     @brief ritorna il tipo di torre
     * 
     * @return tipo di torre
     */
    public int getTypetower() {
        return typetower;
    }

    /**
     @brief setta il danno.
     * 
     */
    private void setDamage() {
        damage=helpz.Constants.Towers.getDamage(typetower);
    }
    
    /**
     @brief setta il range.
     * 
     */
    private void setRange(){
        range=helpz.Constants.Towers.getRange(typetower);
    }
    
    /**
     @brief setta il cooldown.
     * 
     */
    private void setCooldown(){
        cooldown=helpz.Constants.Towers.getCooldown(typetower);
    }

    /**
     @brief ritorna il danno
     * 
     * @return danno
     */
    public int getDamage() {
        return damage;
    }

    /**
     @brief ritorna il range
     * 
     * @return range
     */
    public float getRange() {
        return range;
    }

    /**
     @brief ritorna il cooldown
     * 
     * @return cooldown
     */
    public float getCooldown() {
        return cooldown;
    }
    
    /**
     @brief ritorna se il cooldown è finito.
     * 
     * @return (true = cooldown finito)
     */
    public boolean isCooldownOver(){
        return cooldownTick > cooldown;
    }
    
    /**
     @brief resetta il cooldown.
     * 
     */
    public void resetCooldown(){
        cooldownTick=0;
    }
}
