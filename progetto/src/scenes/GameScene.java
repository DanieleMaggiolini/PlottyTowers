package scenes;

import progetto.Game;

public class GameScene {
    private Game game;
    public GameScene(Game game){
        this.game = game;
    }
    
    public Game getGame(){
        return game;
    }
}
