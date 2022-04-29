package inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import progetto.Game;
import progetto.GameStates;

public class MyMouseListener implements MouseListener, MouseMotionListener{
    private Game game;
    public MyMouseListener(Game game){
        this.game=game;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1){
           switch(GameStates.gamestates){
               case MENU:
                        game.getMenu().mouseClicked(e);
                   break;
               case PLAYING:
                        game.getPlaying().mouseClicked(e);
                   break;
                   
               case SETTINGS: 
                   
                   break;   
               case LVL1:
                        game.getLevel1().mouseClicked(e);
                   break;
           }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        switch(GameStates.gamestates){
                case MENU:
                        game.getMenu().mousePressed(e);
                   break;
                case PLAYING:
                        game.getPlaying().mousePressed(e);
                   break;
                   
                case SETTINGS: 
                   
                   break; 
                case LVL1:
                        game.getLevel1().mousePressed(e);
                break;
           }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        switch(GameStates.gamestates){
                case MENU:
                        game.getMenu().mouseReleased(e);
                   break;
                case PLAYING:
                        game.getPlaying().mouseReleased(e);
                   break;
                   
                case SETTINGS: 
                   
                   break; 
                case LVL1:
                        game.getLevel1().mouseReleased(e);
                   break;
           }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        switch(GameStates.gamestates){
                case MENU:
                      
                   break;
                case PLAYING:
                        game.getPlaying().mouseDragged(e);
                   break;
                   
                case SETTINGS: 
                   
                   break; 
                case LVL1:
                        game.getLevel1().mouseDragged(e);
                   break;
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        switch(GameStates.gamestates){
                case MENU:
                        game.getMenu().mouseMoved(e);
                   break;
                case PLAYING:
                        game.getPlaying().mouseMoved(e);
                   break;
                   
                case SETTINGS: 
                   
                   break; 
                case LVL1:
                        game.getLevel1().mouseMoved(e);
                   break;  
           }
    }
    
}