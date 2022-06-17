/**
 * @author  Daniele Maggiolini
 * @author Mattia Minotti
 * @version 1.0
 * @file Level1.java
 *
 * @brief file per gestire i nemici e il pathfinding.
 *
 */
package managers;

import enemies.Enemy;
import enemies.*;
import static helpz.Constants.Direction.*;
import static helpz.Constants.Enemy.*;
import static helpz.Constants.Tiles.*;
import helpz.LoadSave;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import objects.Tile;
import progetto.Game;
import scenes.Level1;
import ui.ActionBar;

/**
 * @class EnemyManager
 *
 * @brief classe per gestire il movimento, la vita e le animazioni dei nemici
 *
 */
public class EnemyManager {

    //oggeto del game
    private Game game;

    //stringa contenente lo stato(quindi il livello) dal quale la classe è stata invocata
    private String state;

    //contenitore di tutte le immagini dei nemici con animazioni
    private BufferedImage[][] enemyImgs;

    //contenitore di tutti i nemici presenti
    private ArrayList<Enemy> enemies = new ArrayList<>();

    //index delle animazioni dei nemici e la velocità di aggiornamento
    private int aniTick, index2 = 0, index4 = 0, index6 = 0, index8 = 0, aniSpeed = 15;

    //coordinate x e y dell'inizio e la fine del percorso che devono seguire i nemici
    private int startX, startY, endX, endY;

    //barra della vita dei nemici
    private int hpBarWidth = Tile.spriteWidth / 2;

    /**
     * @brief costruttore. assegna il game e lo stato(il livello in cui ci
     * troviamo), carica le immagini dei nemici e imposta il punto di inizio e
     * fine del percorso
     *
     * @param game
     * @param state
     */
    public EnemyManager(Game game, String state) {
        this.game = game;
        this.state = state;
        startX = 0 * Tile.spriteWidth;
        startY = 3 * Tile.spriteHeight;
        endX = 29 * Tile.spriteWidth;
        endY = 12 * Tile.spriteHeight;
        loadEnemyImgs();
    }

    /**
     * @brief reimpostare le coordinate del punto di inizio e di fine del
     * percorso
     *
     * @param sx coordinata x dell'inizio
     * @param sy coordinata y dell'inizio
     * @param ex coordinata x della fine
     * @param ey coordinata y della fine
     *
     */
    public void setStartEnd(int sx, int sy, int ex, int ey) {
        this.startX = sx;
        this.startY = sy;
        this.endX = ex;
        this.endY = ey;
    }

    /**
     * @brief assegnegnazioni delle immagini dei nemici con animazioni, diverse
     * per ogni livello
     */
    private void loadEnemyImgs() {
        BufferedImage temp;
        switch (state) {
            case "level1":
                enemyImgs = new BufferedImage[3][8];
                temp = LoadSave.getImage(LoadSave.NARUTO_ENEMY);
                for (int i = 0; i < enemyImgs.length; i++) {
                    for (int j = 0; j < enemyImgs[i].length; j++) {
                        enemyImgs[i][j] = temp.getSubimage(j * 64, i * 64, 64, 64);
                    }
                }
                break;
            case "level2":
                enemyImgs = new BufferedImage[3][4];
                temp = LoadSave.getImage(LoadSave.COC_ENEMY);
                for (int i = 0; i < enemyImgs.length; i++) {
                    for (int j = 0; j < enemyImgs[i].length; j++) {
                        enemyImgs[i][j] = temp.getSubimage(j * 256, i * 256, 256, 256);
                    }
                }
                break;
            case "level3":
                enemyImgs = new BufferedImage[4][4];
                temp = LoadSave.getImage(LoadSave.RICKEMORTY_ENEMY);
                for (int i = 0; i < enemyImgs.length; i++) {
                    for (int j = 0; j < enemyImgs[i].length; j++) {
                        enemyImgs[i][j] = temp.getSubimage(j * 128, i * 192, 128, 192);
                    }
                }
                break;
            case "level4":
                enemyImgs = new BufferedImage[3][8];
                temp = LoadSave.getImage(LoadSave.ONEPIECE_ENEMY);
                for (int i = 0; i < 8; i++) {
                    enemyImgs[0][i] = temp.getSubimage(i * 64, 0, 64, 64);
                }
                for (int i = 0; i < 6; i++) {
                    enemyImgs[1][i] = temp.getSubimage(i * 256, 64, 256, 256);
                }
                for (int i = 0; i < 8; i++) {
                    enemyImgs[2][i] = temp.getSubimage(i * 128, 320, 128, 128);
                }
                break;
        }
    }

    /**
     * @brief per ogni nemico controllo se vivo e lo muove
     */
    public void update() {
        if (enemies != null) {
            for(int i = 0; i < enemies.size(); i++){
                Enemy tmp = enemies.get(i);
                if (tmp.isAlive()) {
                    updateEnemyMove(tmp);
                }
            }
        }
    }

    /**
     * @brief aggiornamento della posizione di un nemico
     *
     * @param e nemico a cui verrà aggioranta la posizione
     */
    public void updateEnemyMove(Enemy e) {
        if (e.getLastDir() == -1) {
            setDirection(e);
        }
        int newX = (int) (e.getX() + getSpeedWidth(e.getLastDir(), e.getEnemyType()));
        int newY = (int) (e.getY() + getSpeedHeight(e.getLastDir(), e.getEnemyType()));
        if (getTileType(newX, newY) == ROAD_TILE) {
            e.move(getSpeed(e.getEnemyType()), e.getLastDir());
        } else if (isEnd(e)) {
            e.kill();
            switch (state) {
                case "level1":
                    game.getLevel1().rimuoviVita();
                    break;
                case "level2":
                    game.getLevel2().rimuoviVita();
                    break;
                case "level3":
                    game.getLevel3().rimuoviVita();
                    break;
                case "level4":
                    game.getLevel4().rimuoviVita();
                    break;
            }
        } else {
            setDirection(e);
        }
    }

    /**
     * @brief richiesta della tipologia di tile presente nel punto indicato
     *
     * @param x coordinata x del tile che vogliamo guardare
     * @param y coordinata y del tile che vogliamo guardare
     */
    private int getTileType(int x, int y) {
        switch (state) {
            case "level1":
                return game.getLevel1().getTileType(x, y);
            case "level2":
                return game.getLevel2().getTileType(x, y);
            case "level3":
                return game.getLevel3().getTileType(x, y);
            case "level4":
                return game.getLevel4().getTileType(x, y);
        }
        return -1;
    }

    /**
     * @brief controllo se il nemico si trova alla fine del percorso
     *
     * @param e nemico a cui verrà controllata la posizione
     *
     * @return se il nemico si trova alla fine
     */
    private boolean isEnd(Enemy e) {
        if (e.getX() == endX) {
            if (e.getY() == endY) {
                return true;
            }
        }
        return false;
    }

    /**
     * @brief controllo è pissibile procedere nella direzione in cui il nemico
     * stava andando altrimenti prova strade diverse in senso orario
     *
     * @param e nemico a cui verrà controllata la strada che lo precede
     *
     */
    private void setDirection(Enemy e) {
        int dir = e.getLastDir();

        int cordX = (int) (e.getX() / Tile.spriteWidth);
        int cordY = (int) (e.getY() / Tile.spriteHeight);

        fixTile(e, dir, cordX, cordY);

        if (isEnd(e)) {
            return;
        }

        if (dir == LEFT || dir == RIGHT) {
            int newY = (int) (e.getY() + getSpeedHeight(UP, e.getEnemyType()));
            if (getTileType((int) (e.getX()), newY) == ROAD_TILE) {
                e.move(getSpeed(e.getEnemyType()), UP);
                return;
            }
            newY = (int) (e.getY() + getSpeedHeight(DOWN, e.getEnemyType()));
            if (getTileType((int) (e.getX()), newY) == ROAD_TILE) {
                e.move(getSpeed(e.getEnemyType()), DOWN);
            }
        } else {
            int newX = (int) (e.getX() + getSpeedWidth(RIGHT, e.getEnemyType()));
            if (getTileType(newX, (int) (e.getY())) == ROAD_TILE) {
                e.move(getSpeed(e.getEnemyType()), RIGHT);
                return;
            }
            newX = (int) (e.getX() + getSpeedWidth(LEFT, e.getEnemyType()));
            if (getTileType(newX, (int) (e.getY())) == ROAD_TILE) {
                e.move(getSpeed(e.getEnemyType()), LEFT);
            }
        }
    }

    /**
     * @brief prendendo la posizione di un nemico si prende in considerazione il
     * suo angolo in alto a sinistra per controllare i tile successivi
     *
     * @param e nemico a cui verrà presa la posizione top-left della texture
     * @param x tile in cui si trova il nemico nell'asse x
     * @param y tile in cui si trova il nemico nell'asse y
     *
     */
    public void fixTile(Enemy e, int dir, int x, int y) {
        switch (dir) {
            case RIGHT:
                if (x < 29) {
                    x++;
                }
                break;
            case DOWN:
                if (y < 13) {
                    y++;
                }
                break;
        }
        e.setPosition(x * Tile.spriteWidth, y * Tile.spriteHeight);
    }
    
    /**
     * @brief alla direzione del nemico si applica lo spostamento sull'asse X
     * in base alla velocità
     *
     * @param dir direzione del nemico se sull'asse x
     * @param enemytype tipo di nemico a cui andremo si andrà a vedere la velocità
     *
     * @return pixel da aggiungere alla posizione
     */
    public float getSpeedWidth(int dir, int enemytype) {
        if (dir == LEFT) {
            return -getSpeed(enemytype);
        }
        if (dir == RIGHT) {
            return getSpeed(enemytype) + Tile.spriteWidth;
        }
        return 0;
    }
    /**
     * @brief alla direzione del nemico si applica lo spostamento sull'asse y
     * in base alla velocità
     *
     * @param dir direzione del nemico se sull'asse y
     * @param enemytype tipo di nemico a cui andremo si andrà a vedere la velocità
     *
     * @return pixel da aggiungere alla posizione
     */
    public float getSpeedHeight(int dir, int enemytype) {
        if (dir == UP) {
            return -getSpeed(enemytype);
        }
        if (dir == DOWN) {
            return getSpeed(enemytype) + Tile.spriteHeight;
        }
        return 0;
    }
    
    /**
     * @brief aggiunta nemico all'array
     *
     * @param enemytype tipologia del nemico da aggiungere
     *
     */
    public void addEnemy(int enemytype) {
        enemies.add(new Enemy(startX, startY, enemytype, this));
    }

     /**
     * @brief richiamo metodo per aggiunta passando la tipologia del nemico
     *
     * @param next prossimo dell'ondata che dovrà essere generato
     *
     */
    public void spawnEnemy(int next) {
        addEnemy(next);
    }

    /**
     * @brief ritorno dell'array contenente tutti i nemici
     *
     * @return array con tutti i nemici
     *
     */
    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }
    
    /**
     * @brief in base alla tipologia del nemico passato aggiunta di monete al giocatore
     *
     * @param type tipologia del nemico
     *
     */
    public void addCoin(int type) {
        switch (state) {
            case "level1":
                game.getLevel1().addCoin(type);
                break;
            case "level2":
                game.getLevel2().addCoin(type);
                break;
            case "level3":
                game.getLevel3().addCoin(type);
                break;
            case "level4":
                game.getLevel4().addCoin(type);
                break;
        }
    }

    /**
     * @brief per ogni nemico controllo se vivo, se vivo disegno del nemico e della
     * barra della vita
     *
     * @param g parte grafica
     *
     */
    
    public void draw(Graphics g) {
        for(int i = 0; i < enemies.size(); i++){
            Enemy e = enemies.get(i);
            if (e.isAlive()) {
                drawEnemy(e, g);
                drawHpBar(e, g);
            }
        }
        updateAnimationTick();
    }

    /**
     * @brief in base alla tipologia di nemico disegno con il suo index di animazione
     *
     * @param e nemico da disegnare
     * @param g parte grafica
     *
     */
    private void drawEnemy(Enemy e, Graphics g) {
        switch (state) {
            case "level1":
                switch (e.getEnemyType()) {
                    case OROCHIMARU:
                        g.drawImage(enemyImgs[0][index6], (int) e.getX(), (int) e.getY(), Tile.spriteWidth, Tile.spriteHeight, null);
                        break;
                    case TOBI:
                        g.drawImage(enemyImgs[1][index8], (int) e.getX(), (int) e.getY(), Tile.spriteWidth, Tile.spriteHeight, null);
                        break;
                    case MADARA:
                        g.drawImage(enemyImgs[2][index6], (int) e.getX(), (int) e.getY(), Tile.spriteWidth, Tile.spriteHeight, null);
                        break;
                }
                break;
            case "level2":
                switch (e.getEnemyType()) {
                    case POLLO:
                        g.drawImage(enemyImgs[0][index2], (int) e.getX(), (int) e.getY(), Tile.spriteWidth, Tile.spriteHeight, null);
                        break;
                    case MAIALE:
                        g.drawImage(enemyImgs[1][0], (int) e.getX(), (int) e.getY(), Tile.spriteWidth, Tile.spriteHeight, null);
                        break;
                    case GREG:
                        g.drawImage(enemyImgs[2][index4], (int) e.getX() - Tile.spriteWidth / 2, (int) e.getY() - Tile.spriteHeight, Tile.spriteWidth * 2, Tile.spriteHeight * 2, null);
                        break;
                }
                break;
            case "level3":
                switch (e.getEnemyType()) {
                    case JERRY:
                        g.drawImage(enemyImgs[0][index4], (int) e.getX(), (int) (e.getY() - Tile.spriteHeight / 2), Tile.spriteWidth, (int) (Tile.spriteHeight * 1.5), null);
                        break;
                    case SUMMER:
                        g.drawImage(enemyImgs[1][index4], (int) e.getX(), (int) (e.getY() - Tile.spriteHeight / 2), Tile.spriteWidth, (int) (Tile.spriteHeight * 1.5), null);
                        break;
                    case MORTY:
                        g.drawImage(enemyImgs[2][index4], (int) e.getX(), (int) (e.getY() - Tile.spriteHeight / 2), Tile.spriteWidth, (int) (Tile.spriteHeight * 1.5), null);
                        break;
                    case RICK:
                        g.drawImage(enemyImgs[3][index4], (int) e.getX(), (int) (e.getY() - Tile.spriteHeight / 2), Tile.spriteWidth, (int) (Tile.spriteHeight * 1.5), null);
                        break;
                }
                break;
            case "level4":
                switch (e.getEnemyType()) {
                    case LUFFY:
                        g.drawImage(enemyImgs[0][index8], (int) e.getX(), (int) e.getY(), Tile.spriteWidth, Tile.spriteHeight, null);
                        break;
                    case JINBE:
                        g.drawImage(enemyImgs[1][index6], (int) (e.getX() - Tile.spriteWidth / 2), (int) (e.getY() - Tile.spriteHeight), (int) (Tile.spriteWidth * 2), (int) (Tile.spriteHeight * 2), null);
                        break;
                    case BARBABIANCA:
                        g.drawImage(enemyImgs[2][index8], (int) (e.getX() - Tile.spriteWidth / 2), (int) e.getY() - Tile.spriteHeight, (int) (Tile.spriteWidth * 2), (int) (Tile.spriteHeight * 2), null);
                        break;
                }
                break;
        }

    }
    /**
     * @brief disegno rosso della barra della vita di un nemico
     *
     * @param e nemico a cui disegnare la barra della vita
     * @param g parte grafica
     *
     */
    private void drawHpBar(Enemy e, Graphics g) {
        g.setColor(Color.red);
        g.fillRect((int) e.getX() + (Tile.spriteWidth / 2) - (getNewBarWidth(e) / 2), (int) e.getY() + Tile.spriteHeight, getNewBarWidth(e), 5);

    }
    
    /**
     * @brief calcolo grandezza della barra della vita in base alla vita posseduta
     * dal nemico
     *
     * @param e nemico a cui calcolare la grandezza della barra
     *
     * @return larghezza barra
     */
    private int getNewBarWidth(Enemy e) {
        return (int) (hpBarWidth * e.getHpbar());
    }

    /**
     * @brief aggiornamento indici per l'animazione dei nemici
     */
    private void updateAnimationTick() {
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            index2++;
            index4++;
            index6++;
            index8++;
            if (index2 >= 2) {
                index2 = 0;
            }
            if (index4 >= 4) {
                index4 = 0;
            }
            if (index6 >= 6) {
                index6 = 0;
            }
            if (index8 >= 8) {
                index8 = 0;
            }
        }
    }
    
    /**
     * @brief ritorno di quanti nemici sono rimasti

     * @return nemici rimasti vivi
     */
    public int getEnemyRemaning() {
        int enemyremaning = 0;
        for (int i = 0; i < enemies.size(); i++) {
            Enemy e = enemies.get(i);
            if (e.isAlive()) {
                enemyremaning++;
            }
        }
        return enemyremaning;
    }
}
