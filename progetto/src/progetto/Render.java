package progetto;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Render {
    private Game game;
    
    public Render(Game game){
        this.game = game;      
    }
    
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
