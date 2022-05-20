/**
* @author  Daniele Maggiolini
* @author  Mattia Minotti
* @version 1.0
* @file GameScene.java 
* 
* @brief file per intermediare fra le scene ed il game.
*
*/
package scenes;

import progetto.Game;

/** 
* @class GameScene
* 
* @brief setter e getter del game per "collegare" le scene con i metodi del game
* 
*/ 
public class GameScene {
    
    //oggetto del game
    protected Game game;
    
    /**
     @brief costruttore setta l'oggetto game.

     @param game oggetto game da settare
     */
    public GameScene(Game game){
        this.game = game;
    }
    
    /**
     @brief getter oggetto game.
     * 
     * @return game;
     */
    public Game getGame(){
        return game;
    }
}
