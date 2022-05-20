/**
* @author  Daniele Maggiolini
* @author  Mattia Minotti
* @version 0.0
* @file Tile.java 
* 
* @brief gestisce gli oggetti Tile.
*
*/
package objects;

import helpz.ImgFix;
import helpz.LoadSave;
import java.awt.image.BufferedImage;
import progetto.Game;

/**
 * @class Playing
 *
 * @brief gestisce gli oggetti tile e i loro attributi.
 *
 */
public class Tile {
    
    //immagine della tile
    private BufferedImage sprite;
    
    //identificatore
    private int id;
    
    //tipo di tile
    private int tiletype;
    
    //larghezza
    public static int spriteWidth;
    
    //altezza
    public static int spriteHeight;
    
    //matrice della mappa
    private int[][] lvl;
    
    //altezza temporanea
    private int tempHeight;
    
    /**
     @brief costruttore setta le variabili
     * 
     * @param sprite immagine
     * @param id identificatore
     * @param tiletype tipo di tile
     */
    public Tile(BufferedImage sprite, int id, int tiletype){
        this.sprite = sprite;
        this.id=id;    
        this.tiletype=tiletype;
        lvl = LoadSave.loadLevel("level1");;  
        tempHeight =(int)(Game.currentScreenHeight*0.17);
        spriteWidth = Game.currentScreenWidth/lvl[0].length;
        spriteHeight = (Game.currentScreenHeight-tempHeight)/lvl.length;
        if(Game.currentScreenWidth!=1920)
            spriteWidth+=0.5;
        if(Game.currentScreenHeight!=1080)
            spriteHeight+=0.5;
    }

    /**
     @brief ritorna l'immagine
     * 
     * @return immagine
     */
    public BufferedImage getSprite() {
        return sprite;
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
     @brief ritorna la larghezza
     * 
     * @return larghezza
     */
    public int getSpriteWidth(){
        return spriteWidth;
    }
    
    /**
     @brief ritorna l'altezza
     * 
     * @return altezza
     */
    public int getSpriteHeight(){
        return spriteHeight;
    }
    
    /**
     @brief ritorna il tipo di tile
     * 
     * @return tipo di tile
     */
    public int getTileType(){
        return tiletype;
    }
}
