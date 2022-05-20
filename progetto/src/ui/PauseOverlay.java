/**
 * @author  Daniele Maggiolini
 * @author Mattia Minotti
 * @version 0.0
 * @file PauseOverlay.java
 *
 * @brief overlay del gioco quando un livello viene messo in pausa
 *
 */
package ui;

import helpz.LoadSave;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Set;
import progetto.*;
import static progetto.GameStates.PLAYING;
import static progetto.GameStates.setGameState;
import progetto.Sound;
import scenes.*;

/**
 * @class PauseOverlay
 *
 * @brief gestisce l'overlay di gioco in pausa
 *
 */
public class PauseOverlay {

    //sfondo overlay
    private BufferedImage background;
    
    //coordinata x
    private int bgX;
    
    //coordinata y
    private int bgY;
    
    //larghezza
    private int bgW;
    
    //altezza
    private int bgH;
    
    //bottone per mutare
    private SoundButton music;
    
    //bottone per smutare
    private SoundButton mute;
    
    //bottone riprendi
    private UshButton unplay;

    //bottone restart
    private UshButton restart;
         
    //bottone home
    private UshButton home;
            
    //larghezza bottoni
    private int buttonW;
    
    //altezza bottoni
    private int buttonH;

    //oggetto del gioco
    private Game game;
    
    //stato (per capire quale state ha invocato l'overlay button)
    private String state;
    
    //oggetto per gestione traccie musicali
    private Sound s;

    /**
     @brief costruttore del bottone.
     * 
     * setta le variabili
     * 
     * @param game oggetto del game
     * @param state stato dal quale viene invocata la pauseOverlay
     */
    public PauseOverlay(Game game, String state) {
        buttonW = (int) (Game.currentScreenWidth * 0.052);
        buttonH = (int) (Game.currentScreenHeight * 0.092);

        this.game = game;
        this.state=state;
        this.s = progetto.GameStates.s;
        
        loadBackground();
        initSoundButtons();
    }

    /**
     @brief carica lo sfondo.
     * 
     */
    private void loadBackground() {
        background = LoadSave.getImage(LoadSave.PAUSE_BACKGROUND);
        bgW = (int) (Game.currentScreenWidth * 0.41);
        bgH = (int) (Game.currentScreenHeight * 0.59);
        bgX = Game.currentScreenWidth / 2 - bgW / 2;
        bgY = Game.currentScreenHeight / 2 - bgH / 2;
    }

    /**
     @brief inizializza i bottoni.
     * 
     */
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

    /**
     @brief aggiorna il bottone dell'audio.
     * 
     */
    public void update() {
        music.update();
    }

    /**
     @brief disegna sofndo e bottoni.
     * 
     * @param g oggetto della grafica
     */
    public void draw(Graphics g) {
        g.drawImage(background, bgX, bgY, bgW, bgH, null);

        music.draw(g);
        unplay.draw(g);
        restart.draw(g);
        home.draw(g);
    }

    /**
     @brief gestisce i vari movimenti del mouse
     * 
     * @param e evento del mouse
     */
    public void mouseMoved(MouseEvent e) {

    }

    /**
     @brief gestisce i vari press del mouse
     * 
     * @param e evento del mouse
     */
    public void mousePressed(MouseEvent e) {
        resetBools();
        if (isIn(e, music)) {
            music.setMousePressed(true);
            s.mute();
        } else if (isIn(e, unplay)) {
            unplay.setMousePressed(true);
        } else if (isIn(e, restart)) {
            restart.setMousePressed(true);
        } else if (isIn(e, home)) {
            home.setMousePressed(true);
        }
    }

    /**
     @brief gestisce i vari release del mouse
     * 
     * @param e evento del mouse
     */
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
                    case "level4":
                        game.getLevel4().setPaused(false);
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

    /**
     @brief gestisce i vari drag del mouse
     * 
     * @param e evento del mouse
     */
    public void mouseDragged(MouseEvent e) {

    }

    /**
     @brief verifica se il mouse è all'interno del bottone
     * 
     * @param e evento del mouse
     * @param b bottone della pausa
     * 
     * @return booleana (true = è dentro), (false = è fuori)
     */
    public boolean isIn(MouseEvent e, PauseButton b) {
        return b.getBounds().contains(e.getX(), e.getY());
    }

    /**
     @brief resetta le variabili booleane.
     * 
     */
    public void resetBools() {
        music.setMousePressed(false);
        unplay.setMousePressed(false);
        restart.setMousePressed(false);
        home.setMousePressed(false);
    }
}