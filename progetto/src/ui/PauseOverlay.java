package ui;

import helpz.LoadSave;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Set;
import progetto.Game;
import static progetto.GameStates.SETTINGS;
import static progetto.GameStates.PLAYING;
import static progetto.GameStates.setGameState;
import scenes.*;

public class PauseOverlay {

    private BufferedImage background;
    private int bgX, bgY, bgW, bgH;
    private SoundButton music, mute;
    private UshButton unplay, restart, home;

    private int buttonW;
    private int buttonH;

    private Game game;
    private String state;
    


    public PauseOverlay(Game game, String state) {
        buttonW = (int) (Game.currentScreenWidth * 0.052);
        buttonH = (int) (Game.currentScreenHeight * 0.092);

        this.game = game;
        this.state=state;
        
        loadBackground();
        initSoundButtons();
    }

    private void loadBackground() {
        background = LoadSave.getImage(LoadSave.PAUSE_BACKGROUND);
        bgW = (int) (Game.currentScreenWidth * 0.41);
        bgH = (int) (Game.currentScreenHeight * 0.59);
        bgX = Game.currentScreenWidth / 2 - bgW / 2;
        bgY = Game.currentScreenHeight / 2 - bgH / 2;
    }

    private void initSoundButtons() {
        int buttonX = (int) (Game.currentScreenWidth * 0.385);
        int buttonY = (int) (Game.currentScreenHeight * 0.425);

        music = new SoundButton(buttonX, buttonY, buttonW, buttonH);

        buttonX = (int) (Game.currentScreenWidth * 0.470);
        buttonY = (int) (Game.currentScreenHeight * 0.425);

        restart = new UshButton(buttonX, buttonY, buttonW, buttonH, 0);

        buttonX = (int) (Game.currentScreenWidth * 0.555);
        buttonY = (int) (Game.currentScreenHeight * 0.425);

        home = new UshButton(buttonX, buttonY, buttonW, buttonH, 1);

        buttonX = (int) (Game.currentScreenWidth * 0.417);
        buttonY = (int) (Game.currentScreenHeight * 0.55);

        unplay = new UshButton(buttonX, buttonY, buttonW * 3, buttonH, 2);

    }

    public void update() {
        music.update();
    }

    public void draw(Graphics g) {
        g.drawImage(background, bgX, bgY, bgW, bgH, null);

        music.draw(g);
        unplay.draw(g);
        restart.draw(g);
        home.draw(g);
    }

    public void mouseMoved(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {
        resetBools();
        if (isIn(e, music)) {
            music.setMousePressed(true);
        } else if (isIn(e, unplay)) {
            unplay.setMousePressed(true);
        } else if (isIn(e, restart)) {
            restart.setMousePressed(true);
        } else if (isIn(e, home)) {
            home.setMousePressed(true);
        }
    }

    public void mouseReleased(MouseEvent e) {
        if (isIn(e, music)) {
            if (music.isMousePressed()) {
                music.setMuted(!music.isMuted());
            }
        } else if (isIn(e, unplay)) {
            if (unplay.isMousePressed()) {
                switch(state){
                    case "level1":
                        game.getLevel1().setPaused(false);
                        break;
                    case "level2":
                        game.getLevel2().setPaused(false);
                        break;    
                    case "level3":
                        game.getLevel3().setPaused(false);
                        break;       
                }
            }
        } else if (isIn(e, restart)) {
            if (restart.isMousePressed()) {
                game.restartLevel(state);
            }
        } else if (isIn(e, home)) {
            if (home.isMousePressed()) {
                setGameState(PLAYING);
            }
        }
    }

    public void mouseDragged(MouseEvent e) {

    }

    public boolean isIn(MouseEvent e, PauseButton b) {
        return b.getBounds().contains(e.getX(), e.getY());
    }

    public void resetBools() {
        music.setMousePressed(false);
        unplay.setMousePressed(false);
        restart.setMousePressed(false);
        home.setMousePressed(false);
    }
}