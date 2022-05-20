package managers;

import enemies.Enemy;
import static helpz.Constants.Towers.*;
import helpz.LoadSave;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import objects.Tile;
import progetto.Game;
import scenes.Level1;
import towers.Tower;

public class TowerManager {

    private Game game;
    private String state;
    private BufferedImage[][] towerImgs;
    private ArrayList<Tower> towers = new ArrayList<>();
    private int towerAmmount = 0;
    private int aniTick, index6 = 0, index7 = 0, index10 = 0, aniSpeed = 8;

    public TowerManager(Game game, String state) {
        this.game = game;
        this.state = state;
        loadTower();
    }

    private void loadTower() {
        BufferedImage towers;
        switch (state) {
            case "level1":
                towers = LoadSave.getImage(LoadSave.NARUTO_TOWER);
                towerImgs = new BufferedImage[3][10];
                for (int i = 0; i < towerImgs.length; i++) {
                    for (int j = 0; j < towerImgs[i].length; j++) {
                        towerImgs[i][j] = towers.getSubimage(j * 128, i * 128, 128, 128);
                    }
                }
                break;
            case "level2":
                towers = LoadSave.getImage(LoadSave.COC_TOWER);
                towerImgs = new BufferedImage[3][1];
                for (int i = 0; i < 3; i++) {
                        towerImgs[i][0] = towers.getSubimage(i * 128, 0, 128, 128);
                    }
                break;
            case "level3":
                towers = LoadSave.getImage(LoadSave.RICKEMORTY_TOWER);
                towerImgs = new BufferedImage[3][1];
                for (int i = 0; i < 3; i++) {
                        towerImgs[i][0] = towers.getSubimage(i * 256, 0, 256, 256);
                    }
                break;
            case "level4":
                towers = LoadSave.getImage(LoadSave.ONEPIECE_TOWER);
                towerImgs = new BufferedImage[3][10];
                    for (int i = 0; i < 7; i++) {
                        towerImgs[0][i] = towers.getSubimage(i * 128, 0, 128, 128);
                    }
                    for (int i = 0; i < 7; i++) {
                        towerImgs[1][i] = towers.getSubimage(i * 128, 128, 128, 160);  
                    } 
                    //akainu
                    for (int i = 0; i < 2; i++) {
                        towerImgs[2][i] = towers.getSubimage(i * 128, 288, 128, 160);  
                    }
                    for (int i = 0; i < 4; i++) {
                        towerImgs[2][i+2] = towers.getSubimage(256+i*192, 288, 192, 160);  
                    }
                    for (int i = 0; i < 4; i++) {
                        towerImgs[2][i+6] = towers.getSubimage(1024+i*128, 288, 128, 160);  
                    }
                    //
                break;
        }
    }

    public void update() {
        for (Tower t : towers) {
            t.update();
            attack(t);
        }
    }

    public void attack(Tower t) {
        switch (state) {
            case "level1":
                for (Enemy e : game.getLevel1().getEnemyManager().getEnemies()) {
                    if (e.isAlive()) {
                        if (isInRange(t, e)) {
                            if (t.isCooldownOver()) {
                                switch (t.getTypetower()) {
                                    case NARUTO:
                                        if (index6 == 2) {
                                            game.getLevel1().shoot(t, e);
                                        }
                                        break;
                                    case SASUKE:
                                        if (index7 == 5) {
                                            game.getLevel1().shoot(t, e);
                                        }
                                        break;
                                    case SAKURA:
                                        if (index10 == 6) {
                                            game.getLevel1().shoot(t, e);
                                        }
                                        break;
                                }

                                t.resetCooldown();
                            }
                        }
                    }
                }
                break;
            case "level2":
                for (Enemy e : game.getLevel2().getEnemyManager().getEnemies()) {
                    if (e.isAlive()) {
                        if (isInRange(t, e)) {
                            if (t.isCooldownOver()) {
                                game.getLevel2().shoot(t, e);
                                t.resetCooldown();
                            }
                        }
                    }
                }
                break;  
            case "level3":
                for (Enemy e : game.getLevel3().getEnemyManager().getEnemies()) {
                    if (e.isAlive()) {
                        if (isInRange(t, e)) {
                            if (t.isCooldownOver()) {
                                game.getLevel3().shoot(t, e);
                                t.resetCooldown();
                            }
                        }
                    }
                }
                break;
            case "level4":
                for (Enemy e : game.getLevel4().getEnemyManager().getEnemies()) {
                    if (e.isAlive()) {
                        if (isInRange(t, e)) {
                            if (t.isCooldownOver()) {
                                switch (t.getTypetower()) {
                                    case KIZARU:
                                        if (index6 == 4) {
                                            game.getLevel4().shoot(t, e);
                                        }
                                        break;
                                    case AOKIJI:
                                        if (index7 == 3) {
                                            game.getLevel4().shoot(t, e);
                                        }
                                        break;
                                    case AKAINU:
                                        if (index10 == 6) {
                                            game.getLevel4().shoot(t, e);
                                        }
                                        break;
                                }
                                t.resetCooldown();
                            }
                        }
                    }
                }
                break;    
        }
    }

    public boolean isInRange(Tower t, Enemy e) {
        int distance = helpz.Utilz.getDistance(t.getX(), t.getY(), e.getX(), e.getY());
        return distance < t.getRange();
    }

    public void addTower(Tower selectedTower, int xPos, int yPos) {
        towers.add(new Tower(xPos, yPos, towerAmmount++, selectedTower.getTypetower()));
    }
    
    public void towerLevelUp(Tower displayedTower) {
        for(Tower t: towers){
            if(t.getId()== displayedTower.getId()){
                t.levelUp();
            }
        }
    }
    
    public void removeTower(Tower displayedTower) {
        for (int i = 0; i < towers.size(); i++) 
            if(towers.get(i).getId()==displayedTower.getId())
                towers.remove(i);      
    }
    
    public void draw(Graphics g) {
        for (Tower t : towers) {
            drawTower(t, g);
        }
        if(state=="level1" || state=="level4")
            updateAnimationTick();
    }

    private void drawTower(Tower t, Graphics g) {
        boolean fermo = true;
        switch (state) {
            case "level1":
                for (Enemy e : game.getLevel1().getEnemyManager().getEnemies()) {
                    if (e.isAlive()) {
                        if (isInRange(t, e)) {
                            fermo = false;
                        }
                    }
                }
                break;
            case "level4":
                for (Enemy e : game.getLevel4().getEnemyManager().getEnemies()) {
                    if (e.isAlive()) {
                        if (isInRange(t, e)) {
                            fermo = false;
                        }
                    }
                }
                break;    
        }
        if (fermo || state=="level2" || state=="level3") {
            switch(t.getTypetower()){
                case NARUTO:
                    g.drawImage(towerImgs[0][0], (int) t.getX(), (int) t.getY(), Tile.spriteWidth, Tile.spriteHeight, null);
                    break;
                case SASUKE:
                    g.drawImage(towerImgs[1][0], (int) t.getX(), (int) t.getY(), Tile.spriteWidth, Tile.spriteHeight, null);
                    break;
                case SAKURA:
                    g.drawImage(towerImgs[2][0], (int) t.getX(), (int) t.getY(), Tile.spriteWidth, Tile.spriteHeight, null);
                    break;
                case CANNONE:
                    g.drawImage(towerImgs[0][0], (int) t.getX(), (int) t.getY(), Tile.spriteWidth, Tile.spriteHeight, null);
                    break;
                case TESLA:
                    g.drawImage(towerImgs[1][0], (int) t.getX(), (int) t.getY(), Tile.spriteWidth, Tile.spriteHeight, null);
                    break;
                case ARCOX:
                    g.drawImage(towerImgs[2][0], (int) t.getX(), (int) t.getY(), Tile.spriteWidth, Tile.spriteHeight, null);
                    break;
                case MOSQUITOS:
                    g.drawImage(towerImgs[0][0], (int) t.getX(), (int) t.getY(), Tile.spriteWidth, Tile.spriteHeight, null);
                    break;
                case DOG:
                    g.drawImage(towerImgs[1][0], (int) t.getX(), (int) t.getY(), Tile.spriteWidth, Tile.spriteHeight, null);
                    break;
                case HEAD:
                    g.drawImage(towerImgs[2][0], (int) t.getX(), (int) t.getY(), Tile.spriteWidth, Tile.spriteHeight, null);
                    break;
                case KIZARU:
                    g.drawImage(towerImgs[0][0], (int) t.getX(), (int) t.getY(), Tile.spriteWidth, Tile.spriteHeight, null);
                    break;
                case AOKIJI:
                    g.drawImage(towerImgs[1][0], (int) t.getX(), (int) t.getY(), Tile.spriteWidth, Tile.spriteHeight, null);
                    break;
                case AKAINU:
                    g.drawImage(towerImgs[2][0], (int) t.getX(), (int) t.getY(), Tile.spriteWidth, Tile.spriteHeight, null);
                    break;    
            }
        } else {
            switch (t.getTypetower()) {
                case NARUTO:
                    g.drawImage(towerImgs[0][index6], (int) t.getX(), (int) t.getY(), Tile.spriteWidth, Tile.spriteHeight, null);
                    break;
                case SASUKE:
                    g.drawImage(towerImgs[1][index7], (int) t.getX(), (int) t.getY(), Tile.spriteWidth, Tile.spriteHeight, null);
                    break;
                case SAKURA:
                    g.drawImage(towerImgs[2][index10], (int) t.getX(), (int) t.getY(), Tile.spriteWidth, Tile.spriteHeight, null);
                    break;
                case KIZARU:
                    g.drawImage(towerImgs[0][index7], (int) t.getX(), (int) t.getY(), Tile.spriteWidth, Tile.spriteHeight, null);
                    break;
                case AOKIJI:
                    g.drawImage(towerImgs[1][index7], (int) t.getX(), (int)(t.getY()-Tile.spriteHeight/4), Tile.spriteWidth, (int)(Tile.spriteHeight*1.25), null);
                    break;
                case AKAINU:
                    g.drawImage(towerImgs[2][index10], (int) t.getX(), (int)(t.getY()-Tile.spriteHeight/4), Tile.spriteWidth, (int)(Tile.spriteHeight*1.25), null);
                    break;    
            }
        }
    }

    private void updateAnimationTick() {
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            index6++;
            index7++;
            index10++;

            if (index6 >= 6) {
                index6 = 0;
            }
            if (index7 >= 7) {
                index7 = 0;
            }
            if (index10 >= 10) {
                index10 = 0;
            }
        }
    }

    public BufferedImage[][] getTowerImgs() {
        return towerImgs;
    }

    public Tower getTowerAt(int x, int y) {
        for (Tower t : towers) {
            if ((t.getX() == x) && (t.getY() == y)) {
                return t;
            }
        }
        return null;
    }  
}
