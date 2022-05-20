/**
 * @author  Daniele Maggiolini
 * @author Mattia Minotti
 * @version 1.0
 * @file AttackAnimation.java
 *
 * @brief gestisce le animazioni dei proiettili/attacchi
 *
 */
package towers;

import static helpz.Constants.Projectiles.*;

/**
 * @class AttackAnimation
 *
 * @brief gestisce le varie animazioni per ogni proiettile.
 *
 */
public class AttackAnimation {
    
    //indice dell'animzione del proiettile di naruto
    private int narutoIndex=0;
    
    //indice dell'animzione del proiettile di sasuke
    private int sasukeIndex=0;
    
    //indice dell'animzione del proiettile di sakura
    private int sakuraIndex=0;
    
    //tipo di proiettile
    private int type;
    
    /**
     @brief costruttore setta il tipo di proiettile
     * 
     * @param type proiettile
     */
    public AttackAnimation(int type){
        this.type=type;
    }
    
    /**
     @brief ritorna l'indice dell'animazione del type corrente.
     * 
     * @return indice dell'animazione
     */
    public int getAnimationIndex(){
        switch(type){
            case SHURIKEN:
                return updateNarutoIndex();
            case FIREBALL:
                return updateSasukeIndex();
            case PUNCH:
                return updateSakuraIndex();
            case CRATERE:
                return updateSakuraIndex();
        }
        return 0;
    }
    
    //velocita dell'animazione del proiettile di naruto
    int per1=5;
    
    /**
     @brief aggiorna e ritorna l'indice dell'animazione di naruto.
     * 
     * @return index dell'animazione
     */
    private int updateNarutoIndex() {
        int index=0;
        if(narutoIndex<4*per1){
           index=narutoIndex;
           narutoIndex++;
        }else{
           narutoIndex=0;
        }     
        return index/per1;
    }
    
    //velocita dell'animazione del proiettile di sasuke
    int per2=20;
    
    /**
     @brief aggiorna e ritorna l'indice dell'animazione di sasuke.
     * 
     * @return index dell'animazione
     */
    private int updateSasukeIndex() {
        int index=0;
        if(sasukeIndex<3*per2){
           index=sasukeIndex;
           sasukeIndex++;
        }else{
            return 2;
        }     
        return index/per2;
    }
    
    //velocita dell'animazione del proiettile di sakura
    int per3=5; 
    
    /**
     @brief aggiorna e ritorna l'indice dell'animazione di sakura.
     * 
     * @return index dell'animazione
     */
    private int updateSakuraIndex() {
        int index=0;
        if(sakuraIndex<10*per3){
           index=sakuraIndex;
           sakuraIndex++;
        }else{
            return 9;
        }     
        return index/per3;
    }
}
