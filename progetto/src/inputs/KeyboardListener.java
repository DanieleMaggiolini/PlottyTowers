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
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            GameStates.gamestates = SETTINGS;
        } else if (e.getKeyCode() == KeyEvent.VK_M) {
            switch (GameStates.gamestates) {
                case MENU:

                    break;
                case PLAYING:

                    break;

                case SETTINGS:

                    break;
                case EDIT:
                    GameStates.gamestates = LVL1;
                    break;
                case LVL1:
                    GameStates.gamestates = EDIT;
                    break;
                case LVL2:
                    GameStates.gamestates = EDIT;
                    break;
            }
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            switch (GameStates.gamestates) {
                case MENU:

                    break;
                case PLAYING:

                    break;

                case SETTINGS:

                    break;
                case LVL1:
                    game.getLevel1().keyPressed(e);
                    break;
                case LVL2:
                    game.getLevel2().keyPressed(e);
                    break;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
