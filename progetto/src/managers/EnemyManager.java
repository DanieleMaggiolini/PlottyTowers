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

public class EnemyManager {

    private Game game;
    private String state;
    private BufferedImage[][] enemyImgs;
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private int aniTick, index2 = 0, index4 = 0, index6 = 0, index8 = 0, aniSpeed = 15;
    private int startX, startY, endX, endY;
    private int hpBarWidth = Tile.spriteWidth / 2;

    public EnemyManager(Game game, String state) {
        this.game = game;
        this.state = state;
        startX = 0 * Tile.spriteWidth;
        startY = 3 * Tile.spriteHeight;
        endX = 29 * Tile.spriteWidth;
        endY = 12 * Tile.spriteHeight;
        loadEnemyImgs();
//        addEnemy(OROCHIMARU);
//        addEnemy(TOBI);
//        addEnemy(MADARA);
    }

    public void setStartEnd(int sx, int sy, int ex, int ey) {
        this.startX = sx;
        this.startY = sy;
        this.endX = ex;
        this.endY = ey;
    }

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
        }
    }

    public void update() {
        for (Enemy e : enemies) {
            if (e.isAlive()) {
                updateEnemyMove(e);
            }
        }
    }

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
            }
        } else {
            setDirection(e);
        }
    }

    private int getTileType(int x, int y) {
        switch (state) {
            case "level1":
                return game.getLevel1().getTileType(x, y);
            case "level2":
                return game.getLevel2().getTileType(x, y);

        }
        return -1;
    }

    private boolean isEnd(Enemy e) {
        if (e.getX() == endX) {
            if (e.getY() == endY) {
                return true;
            }
        }
        return false;
    }

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

    public float getSpeedWidth(int dir, int enemytype) {
        if (dir == LEFT) {
            return -getSpeed(enemytype);
        }
        if (dir == RIGHT) {
            return getSpeed(enemytype) + Tile.spriteWidth;
        }
        return 0;
    }

    public float getSpeedHeight(int dir, int enemytype) {
        if (dir == UP) {
            return -getSpeed(enemytype);
        }
        if (dir == DOWN) {
            return getSpeed(enemytype) + Tile.spriteHeight;
        }
        return 0;
    }

    public void addEnemy(int enemytype) {
        switch (enemytype) {
            case OROCHIMARU:
                enemies.add(new Enemy(startX, startY, OROCHIMARU, this));
                break;
            case TOBI:
                enemies.add(new Enemy(startX, startY, TOBI, this));
                break;
            case MADARA:
                enemies.add(new Enemy(startX, startY, MADARA, this));
                break;
            case POLLO:
                enemies.add(new Enemy(startX, startY, POLLO, this));
                break;
            case MAIALE:
                enemies.add(new Enemy(startX, startY, MAIALE, this));
                break;
            case GREG:
                enemies.add(new Enemy(startX, startY, GREG, this));
                break;    
        }
    }

    public void spawnEnemy(int next) {
        addEnemy(next);
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public void addCoin(int type) {
        switch (state) {
            case "level1":
                game.getLevel1().addCoin(type);
                break;
            case "level2":
                game.getLevel2().addCoin(type);
                break;
        }
    }

    public void draw(Graphics g) {
        for (Enemy e : enemies) {
            if (e.isAlive()) {
                drawEnemy(e, g);
                drawHpBar(e, g);
            }
        }
        updateAnimationTick();
    }

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
                        g.drawImage(enemyImgs[2][index4], (int) e.getX()-Tile.spriteWidth/2, (int) e.getY()-Tile.spriteHeight, Tile.spriteWidth*2, Tile.spriteHeight*2, null);
                        break;
                }
                break;    
        }

    }

    private void drawHpBar(Enemy e, Graphics g) {
        g.setColor(Color.red);
        g.fillRect((int) e.getX() + (Tile.spriteWidth / 2) - (getNewBartWidth(e) / 2), (int) e.getY() + Tile.spriteHeight, getNewBartWidth(e), 5);

    }

    private int getNewBartWidth(Enemy e) {
        return (int) (hpBarWidth * e.getHpbar());
    }

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

    public int getEnemyRemaning() {
        int enemyremaning = 0;
        for (Enemy e : enemies) {
            if (e.isAlive()) {
                enemyremaning++;
            }
        }
        return enemyremaning;
    }
}
