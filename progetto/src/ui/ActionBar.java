/**
 * @author  Daniele Maggiolini
 * @author Mattia Minotti
 * @version 0.0
 * @file ActionBar.java
 *
 * @brief barra visualizzata nei livelli contenente le varie difese, con le loro
 * info, e informazioni vita ondata ecc.
 *
 */
package ui;

import helpz.Constants.Towers;
import helpz.LoadSave;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import progetto.Game;
import objects.Tile;
import towers.*;

/**
 * @class ActionBar
 *
 * @brief classe figlia della classe "Bar" per gestire la barra presente nei
 * livelli
 *
 */
public class ActionBar extends Bar {

    //oggetto per la gestione del font delle scritte
    private Font f;

    //oggetto per la gestione del colore degli elemeni da draware
    private Color c;

    //oggeto del game
    private Game game;

    //stringa contenente lo stato(quindi il livello) dal quale la classe è stata invocata
    private String state;

    //array di bottoni 1*torre
    private MyButton[] towerButtons;

    //oggetto della torre  selezionata (ovvero clickata) per essere piazzata
    private Tower selectedTower;

    //oggetto della torre che abbiamo selezionato (ovvero clickato) per essere visualizzata
    private Tower displayedTower;

    //valuta nel gioco per acquistare torri e potenziarle
    private int coin = 50;

    private boolean showtowercost;

    private int towercostType;

    private MyButton upgrade, vendi;

    private int vite = 3;

    private BufferedImage[] cuori;

    public ActionBar(int x, int y, int width, int height, Game game, String state) {
        super(x, y, width, height);
        this.game = game;
        this.state = state;

        initButton();

        cuori = new BufferedImage[2];
        BufferedImage temp = LoadSave.getImage(LoadSave.CUORI);
        cuori[0] = temp.getSubimage(0, 0, 32, 32);
        cuori[1] = temp.getSubimage(32, 0, 32, 32);
    }

    private void initButton() {
        towerButtons = new MyButton[3];

        int[][] lvl = LoadSave.loadLevel("level1");
        int tempHeight = (int) (Game.currentScreenHeight * 0.17);
        int w = Game.currentScreenWidth / lvl[0].length - 10;
        int h = (Game.currentScreenHeight - tempHeight) / lvl.length - 10;
        int x = 50;
        int y = (int) (Game.currentScreenHeight * 0.88);
        int xOffset = (int) (Game.currentScreenWidth * 0.05);

        for (int i = 0; i < towerButtons.length; i++) {
            towerButtons[i] = new MyButton("", x + xOffset * i, y, w, h, i);
        }
        f = new Font("LucidaSans", Font.BOLD, 25);
        x = (int) (Game.currentScreenWidth * 0.372);
        y = (int) (Game.currentScreenHeight * 0.934);
        w = (int) (Game.currentScreenWidth * 0.12);
        h = (int) (Game.currentScreenHeight * 0.03);
        int xoff= (int) (Game.currentScreenWidth * 0.135);
        upgrade = new MyButton("UPGRADE", x, y, w, h, f, Color.black);
        vendi = new MyButton("VENDI", x + xoff, y, w, h, f, Color.black);
    }

    public void draw(Graphics g) {
        g.setColor(new Color(247, 178, 82));
        g.fillRect(x, y, width, height);

        drawButton(g);

        drawDiaplayedTower(g);

        //wave info 
        drawWaveInfo(g);

        //money
        drawCoin(g);

        //prezzo delle torri
        if (showtowercost) {
            drawTowerCost(g);
        }

        //disegna vite con i cuori
        drawVite(g);
    }

    public void drawButton(Graphics g) {

        for (MyButton b : towerButtons) {
            b.drawA(g);
            switch (state) {
                case "level1":
                    g.drawImage(game.getLevel1().getTowerManager().getTowerImgs()[b.getId()][0], b.x, b.y, b.width, b.height, null);
                    break;
                case "level2":
                    g.drawImage(game.getLevel2().getTowerManager().getTowerImgs()[b.getId()][0], b.x, b.y, b.width, b.height, null);
                    break;
                case "level3":
                    g.drawImage(game.getLevel3().getTowerManager().getTowerImgs()[b.getId()][0], b.x, b.y, b.width, b.height, null);
                    break;  
                case "level4":
                    g.drawImage(game.getLevel4().getTowerManager().getTowerImgs()[b.getId()][0], b.x, b.y, b.width, b.height, null);
                    break;    
            }
        }
    }

    private void drawDiaplayedTower(Graphics g) {
        int w = (int) (Game.currentScreenWidth * 0.28);
        int h = (int) (Game.currentScreenHeight * 0.120);
        int x = (int) (Game.currentScreenWidth * 0.36);
        int y = (int) (Game.currentScreenHeight * 0.85);
        int xOffset = (int) (Game.currentScreenWidth * 0.05);
        if (displayedTower != null) {
            g.setColor(Color.white);
            g.fillRect(x, y, w, h);
            g.setColor(Color.black);
            g.drawRect(x, y, w, h);
            g.drawRect(x + 10, y + 10, Tile.spriteWidth, Tile.spriteHeight);
            switch (state) {
                case "level1":
                    g.drawImage(game.getLevel1().getTowerManager().getTowerImgs()[displayedTower.getTypetower()][0], x + 8, y + 10, Tile.spriteWidth, Tile.spriteHeight, null);
                    break;
                case "level2":
                    g.drawImage(game.getLevel2().getTowerManager().getTowerImgs()[displayedTower.getTypetower()-3][0], x + 8, y + 10, Tile.spriteWidth, Tile.spriteHeight, null);
                    break;
                case "level3":
                    g.drawImage(game.getLevel3().getTowerManager().getTowerImgs()[displayedTower.getTypetower()-6][0], x + 8, y + 10, Tile.spriteWidth, Tile.spriteHeight, null);
                    break; 
                case "level4":
                    g.drawImage(game.getLevel4().getTowerManager().getTowerImgs()[displayedTower.getTypetower()-9][0], x + 8, y + 10, Tile.spriteWidth, Tile.spriteHeight, null);
                    break;      
            }
            g.setFont(new Font("LucidaSans", Font.BOLD, 20));
            g.drawString("" + Towers.getName(displayedTower.getTypetower()), x + Tile.spriteWidth + 30, y + 25);
            g.drawString("ID: " + displayedTower.getId(), x + Tile.spriteWidth + 30, y + 50);
            g.drawString("Level: " + displayedTower.getLvl(), x + Tile.spriteWidth + 30, y + 75);
            //drawDisplayedTowerBorder(g);
            drawDisplayedTowerRange(g);

            f = new Font("LucidaSans", Font.BOLD, 16);
            if (displayedTower.getLvl() < 3) {
                if (coin < getLevelUpCost(displayedTower)) {
                    upgrade.setColor(Color.red);
                } else {
                    upgrade.setColor(Color.black);
                }
                upgrade.drawAC(g);
                if (upgrade.isMouseOver()) {
                    g.drawString("costo: " + getLevelUpCost(displayedTower), upgrade.getX() + Tile.spriteWidth, upgrade.getY() + upgrade.getBounds().height + 28);
                }
            }
            vendi.drawAC(g);

            if (vendi.isMouseOver()) {
                g.drawString("vendi per :" + getVendiCost(displayedTower), vendi.getX() + Tile.spriteWidth / 2, vendi.getY() + vendi.getBounds().height + 28);
            }

        }
    }

    public int getLevelUpCost(Tower displayedTower) {
        return (int) (helpz.Constants.Towers.getCost(displayedTower.getTypetower()) * 0.8f);
    }

    public int getVendiCost(Tower displayedTower) {
        float addcost = ((displayedTower.getLvl() - 1) * getLevelUpCost(displayedTower)) * 0.5f;
        return (int) (helpz.Constants.Towers.getCost(displayedTower.getTypetower()) / 2 + addcost);
    }

    public void drawDisplayedTowerBorder(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawRect(displayedTower.getX(), displayedTower.getY(), Tile.spriteWidth, Tile.spriteHeight);
    }

    public void drawDisplayedTowerRange(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawOval(displayedTower.getX() - (int) (displayedTower.getRange() / 2) * 2 + Tile.spriteWidth / 2, displayedTower.getY() - (int) (displayedTower.getRange() / 2) * 2 + Tile.spriteHeight / 2, (int) displayedTower.getRange() * 2, (int) displayedTower.getRange() * 2);
    }

    public void drawWaveInfo(Graphics g) {
        drawWaveTimer(g);
        drawEnemyLeft(g);
        drawWaveLeft(g);
    }

    private DecimalFormat decimal = new DecimalFormat("0.0");

    private void drawWaveTimer(Graphics g) {
        int timeleftx = (int) (Game.currentScreenWidth * 0.850);
        int timelefty = (int) (Game.currentScreenHeight * 0.870);
        g.setColor(Color.BLACK);
        g.setFont(new Font("LucidaSans", Font.BOLD, 22));
        switch (state) {
            case "level1":
                if (game.getLevel1().getWaveManager().isTimerStart()) {
                    g.drawString("prossima ondata tra: " + decimal.format(game.getLevel1().getWaveManager().getTimeLeft()), timeleftx, timelefty);
                }
                break;
            case "level2":
                if (game.getLevel2().getWaveManager().isTimerStart()) {
                    g.drawString("prossima ondata tra: " + decimal.format(game.getLevel2().getWaveManager().getTimeLeft()), timeleftx, timelefty);
                }
                break;
            case "level3":
                if (game.getLevel3().getWaveManager().isTimerStart()) {
                    g.drawString("prossima ondata tra: " + decimal.format(game.getLevel3().getWaveManager().getTimeLeft()), timeleftx, timelefty);
                }
                break; 
            case "level4":
                if (game.getLevel4().getWaveManager().isTimerStart()) {
                    g.drawString("prossima ondata tra: " + decimal.format(game.getLevel4().getWaveManager().getTimeLeft()), timeleftx, timelefty);
                }
                break;     
        }
    }

    private void drawEnemyLeft(Graphics g) {
        int current = 0;
        int size = 0;
        int waveleftx = (int) (Game.currentScreenWidth * 0.850);
        int wavelefty = (int) (Game.currentScreenHeight * 0.925);
        g.setColor(Color.BLACK);
        g.setFont(new Font("LucidaSans", Font.BOLD, 30));
        switch (state) {
            case "level1":
                current = game.getLevel1().getWaveManager().getWaveIndex();
                size = game.getLevel1().getWaveManager().getWaves().size();
                break;
            case "level2":
                current = game.getLevel2().getWaveManager().getWaveIndex();
                size = game.getLevel2().getWaveManager().getWaves().size();
                break;
            case "level3":
                current = game.getLevel3().getWaveManager().getWaveIndex();
                size = game.getLevel3().getWaveManager().getWaves().size();
                break;   
            case "level4":
                current = game.getLevel4().getWaveManager().getWaveIndex();
                size = game.getLevel4().getWaveManager().getWaves().size();
                break;    
        }
        g.drawString("ondata: " + (current + 1) + "/" + size, waveleftx, wavelefty);
    }

    private void drawWaveLeft(Graphics g) {
        int enemyleft = 0;
        int enemyleftx = (int) (Game.currentScreenWidth * 0.850);
        int enemylefty = (int) (Game.currentScreenHeight * 0.980);
        g.setColor(Color.BLACK);
        g.setFont(new Font("LucidaSans", Font.BOLD, 22));
        switch (state) {
            case "level1":
                enemyleft = game.getLevel1().getEnemyManager().getEnemyRemaning();
                break;
            case "level2":
                enemyleft = game.getLevel2().getEnemyManager().getEnemyRemaning();
                break;
            case "level3":
                enemyleft = game.getLevel3().getEnemyManager().getEnemyRemaning();
                break;  
            case "level4":
                enemyleft = game.getLevel4().getEnemyManager().getEnemyRemaning();
                break;     
        }
        g.drawString("nemici rimasti: " + enemyleft, enemyleftx, enemylefty);
    }

    public void drawCoin(Graphics g) {
        g.setColor(Color.BLACK);
        g.setFont(new Font("LucidaSans", Font.BOLD, 30));
        g.drawString("monete: " + coin, 40, (int) (Game.currentScreenHeight * 0.981));
    }

    public void drawVite(Graphics g) {
        if (vite < 0) {
            vite = 0;
        }
        switch (vite) {
            case 0:
                g.drawImage(cuori[1], Tile.spriteWidth * 27, 0, Tile.spriteWidth, Tile.spriteHeight, null);
                g.drawImage(cuori[1], Tile.spriteWidth * 28, 0, Tile.spriteWidth, Tile.spriteHeight, null);
                g.drawImage(cuori[1], Tile.spriteWidth * 29, 0, Tile.spriteWidth, Tile.spriteHeight, null);
                break;
            case 1:
                g.drawImage(cuori[0], Tile.spriteWidth * 27, 0, Tile.spriteWidth, Tile.spriteHeight, null);
                g.drawImage(cuori[1], Tile.spriteWidth * 28, 0, Tile.spriteWidth, Tile.spriteHeight, null);
                g.drawImage(cuori[1], Tile.spriteWidth * 29, 0, Tile.spriteWidth, Tile.spriteHeight, null);
                break;
            case 2:
                g.drawImage(cuori[0], Tile.spriteWidth * 27, 0, Tile.spriteWidth, Tile.spriteHeight, null);
                g.drawImage(cuori[0], Tile.spriteWidth * 28, 0, Tile.spriteWidth, Tile.spriteHeight, null);
                g.drawImage(cuori[1], Tile.spriteWidth * 29, 0, Tile.spriteWidth, Tile.spriteHeight, null);
                break;
            case 3:
                g.drawImage(cuori[0], Tile.spriteWidth * 27, 0, Tile.spriteWidth, Tile.spriteHeight, null);
                g.drawImage(cuori[0], Tile.spriteWidth * 28, 0, Tile.spriteWidth, Tile.spriteHeight, null);
                g.drawImage(cuori[0], Tile.spriteWidth * 29, 0, Tile.spriteWidth, Tile.spriteHeight, null);
                break;
        }
    }

    public void removeCoin(int type) {
        this.coin -= helpz.Constants.Towers.getCost(type);
    }

    public void addCoin(int coin) {
        this.coin += coin;
    }

    public void drawTowerCost(Graphics g) {
        int w = (int) (Game.currentScreenWidth * 0.114);
        int h = (int) (Game.currentScreenHeight * 0.078);
        int x = (int) (Game.currentScreenWidth * 0.20);
        int y = (int) (Game.currentScreenHeight * 0.874);
        int xOffset = (int) (Game.currentScreenWidth * 0.05);
        g.setFont(new Font("LucidaSans", Font.BOLD, 20));

        g.setColor(Color.white);
        g.fillRect(x, y, w, h);
        g.setColor(Color.black);
        g.drawRect(x, y, w, h);
        g.drawString("" + helpz.Constants.Towers.getName(towercostType), x + 15, y + h / 3);
        if (coin < helpz.Constants.Towers.getCost(towercostType)) {
            g.setColor(Color.red);
        }
        g.drawString("prezzo: " + helpz.Constants.Towers.getCost(towercostType), x + 15, y + (int) (h / 1.3));
    }

    public void displayTower(Tower t) {
        displayedTower = t;
    }

    public int getVite() {
        return vite;
    }

    public void rimuoviVita() {
        vite--;
        if (vite <= 0) {
            switch (state) {
                case "level1":
                    game.getLevel1().setGameOver();
                    break;
                case "level2":
                    game.getLevel2().setGameOver();
                    break;
                case "level3":
                    game.getLevel3().setGameOver();
                    break;
                case "level4":
                    game.getLevel4().setGameOver();
                    break;     
            }
        }

    }

    public void mouseClicked(MouseEvent e) {
        if (displayedTower != null) {
            if (upgrade.getBounds().contains(e.getX(), e.getY()) && displayedTower.getLvl() < 3 && coin >= getLevelUpCost(displayedTower)) {
                switch (state) {
                    case "level1":
                        game.getLevel1().towerLevelUp(displayedTower);
                        coin -= getLevelUpCost(displayedTower);
                        break;
                    case "level2":
                        game.getLevel2().towerLevelUp(displayedTower);
                        coin -= getLevelUpCost(displayedTower);
                        break;
                    case "level3":
                        game.getLevel3().towerLevelUp(displayedTower);
                        coin -= getLevelUpCost(displayedTower);
                        break; 
                    case "level4":
                        game.getLevel4().towerLevelUp(displayedTower);
                        coin -= getLevelUpCost(displayedTower);
                        break;     
                }
                return;
            } else if (vendi.getBounds().contains(e.getX(), e.getY())) {
                switch (state) {
                    case "level1":
                        game.getLevel1().removeTower(displayedTower);
                        coin += getVendiCost(displayedTower);
                        displayedTower = null;
                        break;
                    case "level2":
                        game.getLevel2().removeTower(displayedTower);
                        coin += getVendiCost(displayedTower);
                        displayedTower = null;
                        break;
                    case "level3":
                        game.getLevel3().removeTower(displayedTower);
                        coin += getVendiCost(displayedTower);
                        displayedTower = null;
                        break; 
                    case "level4":
                        game.getLevel4().removeTower(displayedTower);
                        coin += getVendiCost(displayedTower);
                        displayedTower = null;
                        break;     
                }
                return;
            }
        }
    }

    public void mouseMoved(MouseEvent e) {
        showtowercost = false;
        upgrade.setMouseOver(false);
        vendi.setMouseOver(false);
        for (MyButton b : towerButtons) {
            b.setMouseOver(false);
        }
        if (displayedTower != null) {
            if (upgrade.getBounds().contains(e.getX(), e.getY())) {
                upgrade.setMouseOver(true);
                return;
            } else if (vendi.getBounds().contains(e.getX(), e.getY())) {
                vendi.setMouseOver(true);
                return;
            }
        }
        for (MyButton b : towerButtons) {
            if (b.getBounds().contains(e.getX(), e.getY())) {
                b.setMouseOver(true);
                showtowercost = true;
                switch(state){
                    case "level1":
                        towercostType = b.getId();
                        break;
                    case "level2":
                        towercostType = b.getId()+3;
                        break;
                    case "level3":
                        towercostType = b.getId()+6;
                        break;
                    case "level4":
                        towercostType = b.getId()+9;
                        break;     
                }   
                return;
            }
        }

    }

    public void mousePressed(MouseEvent e) {
        if (displayedTower != null) {
            if (upgrade.getBounds().contains(e.getX(), e.getY()) && displayedTower.getLvl() < 3) {
                upgrade.setMousePressed(true);
                return;
            } else if (vendi.getBounds().contains(e.getX(), e.getY())) {
                vendi.setMousePressed(true);
                return;
            }
        }
        for (MyButton b : towerButtons) {
            if (b.getBounds().contains(e.getX(), e.getY())) {
                if (coin < helpz.Constants.Towers.getCost(towercostType)) {
                    return;
                }
                switch (state) {
                    case "level1":
                        selectedTower = new Tower(0, 0, -1, b.getId());
                        break;
                    case "level2":
                        selectedTower = new Tower(0, 0, -1, b.getId()+3);
                        break;
                    case "level3":
                        selectedTower = new Tower(0, 0, -1, b.getId()+6);
                        break;
                    case "level4":
                        selectedTower = new Tower(0, 0, -1, b.getId()+9);
                        break;     
                }
                
                switch (state) {
                    case "level1":
                        game.getLevel1().setSelectedTower(selectedTower);
                        break;
                    case "level2":
                        game.getLevel2().setSelectedTower(selectedTower);
                        break;
                    case "level3":
                        game.getLevel3().setSelectedTower(selectedTower);
                        break;
                    case "level4":
                        game.getLevel4().setSelectedTower(selectedTower);
                        break;    
                }
                return;
            }
        }
    }

    public void mouseReleased(MouseEvent e) {
        for (MyButton b : towerButtons) {
            b.resetBooleans();
        }
        upgrade.resetBooleans();
        vendi.resetBooleans();
    }
}