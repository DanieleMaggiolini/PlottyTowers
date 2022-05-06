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
    private UshButton unplay, setting, home;

    private int buttonW;
    private int buttonH;

    private Level1 level1;
    private Game game;

    private String previous;

    public PauseOverlay(Level1 level1, Game game, String s) {
        buttonW = (int) (Game.currentScreenWidth * 0.052);
        buttonH = (int) (Game.currentScreenHeight * 0.092);

        this.level1 = level1;

        loadBackground();
        initSoundButtons();
        this.game = game;
        previous = s;
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

        setting = new UshButton(buttonX, buttonY, buttonW, buttonH, 0);

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
        setting.draw(g);
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
        } else if (isIn(e, setting)) {
            setting.setMousePressed(true);
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
                level1.setPaused(false);
            }
        } else if (isIn(e, setting)) {
            if (setting.isMousePressed()) {
                setGameState(SETTINGS);
                game.getSetting().setPrevious(previous);
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
        setting.setMousePressed(false);
        home.setMousePressed(false);
    }
}
