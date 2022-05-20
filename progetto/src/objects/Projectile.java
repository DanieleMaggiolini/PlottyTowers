/**
* @author  Daniele Maggiolini
* @author  Mattia Minotti
* @version 0.0
* @file Projectile.java 
* 
* @brief gestisce gli oggetti proiettile.
*
*/
package objects;

import java.awt.geom.Point2D;
import towers.AttackAnimation;

/**
 * @class Playing
 *
 * @brief gestisce gli oggetti proiettile e tutti i loro attributi.
 *
 */
public class Projectile {
    
    //punto della posizione
    private Point2D.Float position;
    
    //identificatore del proiettile
    private int id;
    
    //tipo di proiettile
    private int projectiletype;
    
    //booleana se il proiettile esiste
    private boolean exist=true;
    
    //velocita sull'asse x
    private float speedx;
    
    //velocita sull'asse y
    private float speedy;
    
    //rotazione
    private float rotation;
    
    //danno
    private int dmg;
    
    //stato dell'animazione
    private AttackAnimation index;
    
    /**
     @brief costruttore setta le variabili
     * 
     * @param x coordinata
     * @param y coordinata
     * @param speedx velocita x
     * @param speedy velocita y
     * @param dmg danno
     * @param rotation rotazione
     * @param id identificatore
     * @param projectiletype tipo di proiettile
     */
    public Projectile(float x, float y, float speedx, float speedy, int dmg, float rotation, int id, int projectiletype) {
        this.position = new Point2D.Float(x,y);
        this.speedx=speedx;
        this.speedy=speedy;
        this.dmg=dmg;
        this.rotation=rotation;
        this.id = id;
        this.projectiletype = projectiletype;
        index = new AttackAnimation(projectiletype);
    }
   
    /**
     @brief muove il proiettile.
     * 
     */
    public void move(){
        position.x += speedx;
        position.y += speedy;
    }

    /**
     @brief imposta la posizione.
     * 
     * @param position posizione
     */
    public void setPosition(Point2D.Float position) {
        this.position = position;
    }
    
    /**
     @brief imposta l'esistenza del proiettile.
     * 
     * @param exist esistenza
     */
    public void setExist(boolean exist){
        this.exist=exist;
    }
    
    /**
     @brief ritorna la posizione
     * 
     * @return posizione
     */
    public Point2D.Float getPosition() {
        return position;
    }

    /**
     @brief ritorna l'id
     * 
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     @brief ritorna il tipo di proiettile
     * 
     * @return tipo di proiettile
     */
    public int getProjectiletype() {
        return projectiletype;
    }
    
    /**
     @brief ritorna se il proiettile esiste
     * 
     * @return esistenza
     */
    public boolean isExist() {
        return exist;
    }
    
    /**
     @brief ritorna il danno
     * 
     * @return danno
     */
    public int getDmg(){
        return dmg;
    }
    
    /**
     @brief ritorna la rotazione
     * 
     * @return rotazione
     */
    public float getRotation(){
        return rotation;
    }
    
    /**
     @brief lo stato dell'animazione
     * 
     * @return stato animazione
     */
    public int getIndex(){
        return index.getAnimationIndex();
    }
}
