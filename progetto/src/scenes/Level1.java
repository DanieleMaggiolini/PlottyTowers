/**
* @author  Daniele Maggiolini
* @author  Mattia Minotti
* @version 0.0
* @file Level1.java 
* 
* @brief file per gestire tutto cio che è presente nel level1.
*
*/
package scenes;

import enemies.Enemy;
import static helpz.Constants.Tiles.*;
import helpz.LoadSave;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Set;
import managers.EnemyManager;
import managers.TowerManager;
import progetto.*;
import ui.ActionBar;
import ui.MyButton;
import ui.PauseOverlay;
import objects.Tile;
import towers.*;
import managers.*;
import static progetto.GameStates.PLAYING;
import static progetto.GameStates.setGameState;
import ui.UshButton;

/** 
* @class Level1
* 
* @brief classe che gestisce tutti gli elementi e i metodi del Level1.
* 
* gestione di bottoni, immagini, scritte, Tile, oggetti delle torri,
* oggetti dei nemici e di tutti gli elementi contenuti dal level1
* 
*/ 
public class Level1 extends GameScene implements SceneMethods {

    //stringa per gestire il richiamo di alcuni metodi
    private String LEVELNAME = "level1";
    
    //oggetto Sound per la gestitione degli effetti sonori
    private Sound s = new Sound(1);
    
    //oggetto gestione dei nemici
    private EnemyManager enemymanager;
    
    //oggetto gestione delle torri
    private TowerManager towermanager;
    
    //oggetto gestione dei proiettili
    private ProjectileManager projmanager;
    
    //oggetto gestione delle ondate
    private WaveManager wavemanager;

    //immagine dell'ingranaggio
    private BufferedImage ingranaggio;

    //matrice per la griglia della mappa
    private int[][] lvl;
    
    //Bar in basso per la gestione delle torri
    private ActionBar actionbar;

    //coordinata x del mouse
    private int mouseX;
    
    //coordinata y del mouse
    private int mouseY;
    
    //bottone per la pausa
    private MyButton Bmenu;
    
    //font testo
    private Font f;
    
    //colore 
    private Color c;
    
    //overlay della pausa con pulsanti(muta, restart, esci, riprendi)
    private PauseOverlay pauseoverlay;
    
    //booleana verifica gioco in pausa (false == gioco in corso), (true == gioco in pausa)
    private boolean paused = false;

    //torre selezionata
    private Tower selectedTower;

    //frequenza aumento monete
    private int coinTick;

    //booleana verifica la fine del livello (false == gioco in corso), (true == gioco terminato)
    private boolean gameover = false;
    
    //booleana verifica la'eventuale vittoria del livello (false == gioco in corso), (true == vittoria)
    private boolean win=false;
    
    //bottone riavvio nel overlayPause
    private UshButton restart;
    
    //bottone uscita dal livello nel overlayPause
    private UshButton exit;

    /**
     @brief costruttore che setta il game e richiama i metodi per inizializzare classi, bottoni e importare immagini.

     @param game oggetto del game
     */
    public Level1(Game game) {
        super(game);

        initClasses();

        impImage();

        initButtons();
    }

    /**
     @brief inizializza tutte le classi presenti.
     */
    public void initClasses() {
        loadLevel();
        enemymanager = new EnemyManager(game, LEVELNAME);
        towermanager = new TowerManager(game, LEVELNAME);
        projmanager = new ProjectileManager(game, LEVELNAME);
        wavemanager = new WaveManager(game, LEVELNAME);
        int tempHeight = (int) (Game.currentScreenHeight * 0.17);
        actionbar = new ActionBar(0, Game.currentScreenHeight - tempHeight, Game.currentScreenWidth, tempHeight, game, LEVELNAME);
        pauseoverlay = new PauseOverlay(game, LEVELNAME);
    }

    private void impImage() {
        ingranaggio = LoadSave.getImage(LoadSave.INGRANAGGIO);
    }

    private void initButtons() {
        int w = 80;
        int h = 80;
        int x = 10;
        int y = 10;
        int yOffset = 100;
        f = new Font("Arial", Font.BOLD, 40);
        c = new Color(255, 0, 0);
        Bmenu = new MyButton("", x, y, w, h, f, c);

        int buttonW = (int) (Game.currentScreenWidth * 0.052);
        int buttonH = (int) (Game.currentScreenHeight * 0.092);

        int buttonX = (int) (Game.currentScreenWidth * 0.42);
        int buttonY = (int) (Game.currentScreenHeight * 0.5);
        restart = new UshButton(buttonX, buttonY, buttonW, buttonH, 0);

        buttonX = (int) (Game.currentScreenWidth * 0.52);
        buttonY = (int) (Game.currentScreenHeight * 0.5);
        exit = new UshButton(buttonX, buttonY, buttonW, buttonH, 1);
    }

    public void loadLevel() {
        lvl = LoadSave.loadLevel(LEVELNAME);
    }

    public void setLevel(int[][] lvl) {
        this.lvl = lvl;
    }

//    public void updates() {
//        if (!gameover) {
//            if (!paused) {
//                wavemanager.update();
//                //gold
//                coinTick++;
//                if (coinTick % (40) == 0) {
//                    actionbar.addCoin(1);
//                }
//                //enemy
//                if (isAllDead()) {
//                    if (isMoreWaves()) {
//                        wavemanager.startTimer();
//                        if (isTimerOver()) {
//                            wavemanager.increaseWaveIndex();
//                            enemymanager.getEnemies().clear();
//                            wavemanager.resetIndex();
//                        }
//                    }else{
//                        win=true;
//                        gameover=true;
//                        s.stop();
//                        game.getPlaying().getLevelMenu().unlockLevel(LEVELNAME);
//                    }
//                }
//                //wave
//                if (isEnemySpawn()) {
//                    spawnEnemy();
//                }
//
//                enemymanager.update();
//                towermanager.update();
//                projmanager.update();
//            }
//        }
//    }
    public void updateEnemy(){
        if (!gameover) {
            if (!paused) {
                if (isAllDead()) {
                    if (isMoreWaves()) {
                        wavemanager.startTimer();
                        if (isTimerOver()) {
                            wavemanager.increaseWaveIndex();
                            enemymanager.getEnemies().clear();
                            wavemanager.resetIndex();
                        }
                    }else{
                        win=true;
                        gameover=true;
                        s.stop();
                        game.getPlaying().getLevelMenu().unlockLevel(LEVELNAME);
                    }
                }
                enemymanager.update();
            }
        }
    }
    public void updateTower(){
        if (!gameover) 
            if (!paused) 
                towermanager.update();
    }
    public void updateWave(){
        if (!gameover) {
            if (!paused) {
                wavemanager.update();    
                if (isEnemySpawn()) 
                    spawnEnemy();
            }
        }
    }
    public void updateProj(){
        if (!gameover) 
            if (!paused) 
                projmanager.update();
    }
    public void updateCoin(){
        if (!gameover) {
            if (!paused) {
                coinTick++;
                if (coinTick % (40) == 0) 
                    actionbar.addCoin(1);  
            }
        }
    }
    
    private boolean isEnemySpawn() {
        if (getWaveManager().isEnemySpawn()) {
            if (!getWaveManager().isWaveEnd()) {
                return true;
            }
        }
        return false;
    }

    private void spawnEnemy() {        
        enemymanager.spawnEnemy(getWaveManager().getNextEnemy());
    }

    private boolean isAllDead() {
        if (!wavemanager.isWaveEnd()) {
            return false;
        }
        for (Enemy e : enemymanager.getEnemies()) {
            if (e.isAlive()) {
                return false;
            }
        }
        return true;
    }

    private boolean isMoreWaves() {
        return wavemanager.isMoreWaves();
    }

    private boolean isTimerOver() {
        return wavemanager.isTimerOver();
    }

    @Override
    public void render(Graphics g) {
        if (!gameover) {
            drawBackground(g);
            g.drawImage(ingranaggio, 10, 10, 80, 80, null);
            drawButton(g);
            if (!paused) {
                enemymanager.draw(g);
                towermanager.draw(g);
                projmanager.draw(g);
                drawSelectedTower(g);
            }
            drawTileOver(g);
            //guarda su ds per susano
            if (paused) {
                pauseoverlay.draw(g);
                pauseoverlay.update();
            }
            actionbar.draw(g);
        } else {
            restart.draw(g);
            exit.draw(g);
            g.setColor(new Color(0, 0, 0));
            g.setFont(new Font("Arial", Font.BOLD, 80));
            if(win)
                g.drawString("HAI VINTO!", (int) (Game.currentScreenWidth * 0.39), (int) (Game.currentScreenHeight * 0.42));
            else      
                g.drawString("GAME OVER!", (int) (Game.currentScreenWidth * 0.37), (int) (Game.currentScreenHeight * 0.42));
        }
    }

    private void drawBackground(Graphics g) {
        for (int y = 0; y < lvl.length; y++) {
            for (int x = 0; x < lvl[0].length; x++) {
                int id = lvl[y][x];
                g.drawImage(game.getTileManager().getSprite(id), x * Tile.spriteWidth, y * Tile.spriteHeight, Tile.spriteWidth, Tile.spriteHeight, null);
            }
        }
    }

    private void drawTileOver(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawRect(mouseX, mouseY, Tile.spriteWidth, Tile.spriteHeight);
    }

    private void drawButton(Graphics g) {
        Bmenu.draw(g);
    }

    public void setPaused(boolean paused) {
        this.paused = paused;

    }

    public boolean getPaused() {
        return paused;
    }

    public int getTileType(int x, int y) {
        if (x >= Game.currentScreenWidth || x < 0) {
            return 0;
        }
        if (y >= Tile.spriteHeight * 14 || y < 0) {
            return 0;
        }
        int id = lvl[y / Tile.spriteHeight][x / Tile.spriteWidth];
        return game.getTileManager().getTile(id).getTileType();
    }

    private Tower getTowerAt(int x, int y) {
        return towermanager.getTowerAt(x, y);
    }

    public void shoot(Tower t, Enemy e) {
        projmanager.newProjectile(t, e);
    }

    public void addCoin(int type) {
        actionbar.addCoin(helpz.Constants.Enemy.getCoin(type));
        s.playSE(0);
    }

    public void rimuoviVita() {
        actionbar.rimuoviVita();
    }

    private boolean isTileAvailable(int x, int y) {
        int id = lvl[y / Tile.spriteHeight][x / Tile.spriteWidth];
        int tiletype = game.getTileManager().getTile(id).getTileType();
        return tiletype == GRASS_TILE;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (!gameover) {
            if (Bmenu.getBounds().contains(e.getX(), e.getY())) {
                setPaused(!getPaused());
            }
            if (!paused) {
                if (e.getY() > (int) (Game.currentScreenHeight * 0.829)) {
                    actionbar.mouseClicked(e);
                } else {
                    if (selectedTower != null) {
                        if (isTileAvailable(mouseX, mouseY)) {
                            if (getTowerAt(mouseX, mouseY) == null) {
                                towermanager.addTower(selectedTower, mouseX, mouseY);
                                actionbar.removeCoin(selectedTower.getTypetower());
                                selectedTower = null;
                            }
                        }
                    } else {
                        //prendere la torre se esiste nella posizione del click
                        Tower t = getTowerAt(mouseX, mouseY);
                        actionbar.displayTower(t);
                    }
                }
            }
        }else{
            if (restart.getBounds().contains(e.getX(), e.getY())) {
                game.restartLevel(LEVELNAME);
            }else if(exit.getBounds().contains(e.getX(), e.getY())){
                game.restartLevel(LEVELNAME);
                setGameState(PLAYING);
            }          
        }    
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (!gameover) {
            Bmenu.setMouseOver(false);
            if (!paused) {
                if (Bmenu.getBounds().contains(e.getX(), e.getY())) {
                    Bmenu.setMouseOver(true);
                }
                if (e.getY() > (int) (Game.currentScreenHeight * 0.829)) {
                    actionbar.mouseMoved(e);
                } else {
                    mouseX = (e.getX() / Tile.spriteWidth) * Tile.spriteWidth; //tile viene spostato in una grlia e non pixel per pixel
                    mouseY = (e.getY() / Tile.spriteHeight) * Tile.spriteHeight;
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (!gameover) {
            if (Bmenu.getBounds().contains(e.getX(), e.getY())) {
                Bmenu.setMousePressed(true);
            }
            if (paused) {
                pauseoverlay.mousePressed(e);
            } else {
                if (e.getY() > (int) (Game.currentScreenHeight * 0.829)) {
                    actionbar.mousePressed(e);
                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        resetButtons();
        if (e.getY() > (int) (Game.currentScreenHeight * 0.829)) {
            actionbar.mouseReleased(e);
        }
        if (paused) {
            pauseoverlay.mouseReleased(e);
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    private void resetButtons() {
        Bmenu.resetBooleans();
    }

    public TowerManager getTowerManager() {
        return towermanager;
    }

    public EnemyManager getEnemyManager() {
        return enemymanager;
    }

    public WaveManager getWaveManager() {
        return wavemanager;
    }

    public void setSelectedTower(Tower selectedTower) {
        this.selectedTower = selectedTower;
    }

    private void drawSelectedTower(Graphics g) {
        if (selectedTower != null) {
            g.drawImage(towermanager.getTowerImgs()[selectedTower.getTypetower()][0], mouseX, mouseY, Tile.spriteWidth, Tile.spriteHeight, null);
        }
    }

    public void towerLevelUp(Tower displayedTower) {
        towermanager.towerLevelUp(displayedTower);
    }

    public void removeTower(Tower displayedTower) {
        towermanager.removeTower(displayedTower);
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            if (selectedTower != null) {
                selectedTower = null;
            } else {
                setPaused(!getPaused());
            }
        }
    }

    public void setGameOver() {
        gameover = true;
    }
}
