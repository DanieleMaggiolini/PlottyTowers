package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import progetto.Game;

import static progetto.GameStates.*;

import progetto.GameStates;




public class KeyboardListener implements KeyListener{

    private Game game;
    public KeyboardListener(Game game){
        this.game=game;
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        
    }
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_A)
            System.out.println("A");
        else if(e.getKeyCode() == KeyEvent.VK_B)
            System.out.println("B");
         else if(e.getKeyCode() == KeyEvent.VK_S)
           GameStates.gamestates = PLAYING;
        else if(e.getKeyCode() == KeyEvent.VK_W)
           GameStates.gamestates = MENU;
        else if(e.getKeyCode() == KeyEvent.VK_D)
           GameStates.gamestates = SETTINGS;
        
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
           switch(GameStates.gamestates){
               case MENU:
                        
                   break;
               case PLAYING:
                        
                   break;
                   
               case SETTINGS: 
                   
                   break;   
               case LVL1:
                        game.getLevel1().setPaused(!(game.getLevel1().getPaused()));
                   break;
           }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
}
