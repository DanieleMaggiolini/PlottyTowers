/**
* @author  Daniele Maggiolini
* @author  Mattia Minotti
* @version 0.0
* @file Render.java 
* 
* @brief file che gestisce il richiamo del metodo render nella classe interessata.
*
*/
package progetto;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/** 
* @class Render
* 
* @brief gestione rendering del gioco.
* 
*/ 
public class Render {
    
    //oggetto del game
    private Game game;
    
    /**
     @brief setta l'oggetto del game.

     @param game oggetto del game da settare
     */
    public Render(Game game){
        this.game = game;      
    }
    
    /**
     @brief in base al gamestate attuale richiama la render della classe interessata.

     @param g Graphics
     */
    public void render(Graphics g){
        switch(GameStates.gamestates){    
            case MENU:
               game.getMenu().render(g);
                break;
            case PLAYING:
                game.getPlaying().render(g);
                break;
            case SETTINGS:
                game.getSetting().render(g);
                break;
            case EDIT:
                game.getEditing().render(g);
                break;
            case LVL1:
                game.getLevel1().render(g);
        }
    }
}
