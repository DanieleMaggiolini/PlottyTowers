package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import progetto.Game;

import static progetto.GameStates.*;

import progetto.GameStates;

public class KeyboardListener implements KeyListener {

    private Game game;

    public KeyboardListener(Game game) {
        this.game = game;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_S) {
            GameStates.gamestates = PLAYING;
        } else if (e.getKeyCode() == KeyEvent.VK_W) {
            GameStates.gamestates = MENU;
        }
        else if (e.getKeyCode() == KeyEvent.VK_M) {
            switch (GameStates.gamestates) {
                case MENU:

                    break;
                case PLAYING:

                    break;
                case EDIT:
                    switch(game.getEditing().getState()){
                        case "level1":
                            GameStates.gamestates = LVL1;
                            break;
                        case "level2":
                            GameStates.gamestates = LVL2;
                            break;
                        case "level3":
                            GameStates.gamestates = LVL3;
                            break; 
                        case "level4":
                            GameStates.gamestates = LVL4;
                            break;    
                    }
                    break;
                case LVL1:
                    GameStates.gamestates = EDIT;
                    game.getEditing().setState("level1");
                    break;
                case LVL2:
                    GameStates.gamestates = EDIT;
                    game.getEditing().setState("level2");
                    break;
                case LVL3:
                    GameStates.gamestates = EDIT;
                    game.getEditing().setState("level3");
                    break;
                case LVL4:
                    GameStates.gamestates = EDIT;
                    game.getEditing().setState("level4");
                    break;    
            }
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            switch (GameStates.gamestates) {
                case MENU:

                    break;
                case PLAYING:

                    break;
                case LVL1:
                    game.getLevel1().keyPressed(e);
                    break;
                case LVL2:
                    game.getLevel2().keyPressed(e);
                    break;
                case LVL3:
                    game.getLevel3().keyPressed(e);
                    break;
                case LVL4:
                    game.getLevel4().keyPressed(e);
                    break;    
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
