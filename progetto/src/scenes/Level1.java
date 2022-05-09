package scenes;

import static helpz.Constants.Tiles.*;
import helpz.LoadSave;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import managers.EnemyManager;
import managers.TowerManager;
import progetto.*;
import ui.ActionBar;
import ui.MyButton;
import ui.PauseOverlay;
import objects.Tile;
import towers.*;
import managers.*;

public class Level1 extends GameScene implements SceneMethods {

    private int aniTick, aniSpeed = 40;

    private EnemyManager enemymanager;
    private TowerManager towermanager;
    ////////////////////

    private int[] susanoIndex;
    private int susanoRow = 0;
    private BufferedImage[][] susano;
    private BufferedImage susanoall;
    /////////////////////

    private BufferedImage ingranaggio;

    private int[][] lvl;
    private ActionBar actionbar;

    private int mouseX, mouseY;

    private MyButton Bmenu;
    private Font f;
    private Color c;

    private PauseOverlay pauseoverlay;
    private boolean paused = false;

    private Tower selectedTower;

    public Level1(Game game) {
        super(game);

        //il livello
        initClasses();

        impImage();

        initButtons();

        loadAnimations();

    }

    public void initClasses() {
        loadLevel();
        enemymanager = new EnemyManager(game, "level1");
        towermanager = new TowerManager(game, "level1");
        int tempHeight = (int) (Game.currentScreenHeight * 0.17);
        actionbar = new ActionBar(0, Game.currentScreenHeight - tempHeight, Game.currentScreenWidth, tempHeight, game, "level1");
        pauseoverlay = new PauseOverlay(game, "level1");
        susanoIndex = new int[8];
    }

    private void impImage() {
        ingranaggio = LoadSave.getImage(LoadSave.INGRANAGGIO);
        susanoall = LoadSave.getImage(LoadSave.SUSANO);
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
    }

    private void loadAnimations() {
        loadSusano();
    }

    private void loadSusano() {
        susano = new BufferedImage[8][7];
        for (int i = 0; i < 7; i++) {
            susano[0][i] = susanoall.getSubimage(128 * i, 0, 128, 128);
        }
        for (int i = 0; i < 6; i++) {
            if (i != 5) {
                susano[1][i] = susanoall.getSubimage(128 * i, 128, 128, 128);
            } else {
                susano[1][5] = susanoall.getSubimage(128 * i, 128, 128 * 2, 128);
            }
        }
        susano[2][0] = susanoall.getSubimage(0, 256, 128 * 3, 128 * 2);
        susano[2][1] = susanoall.getSubimage(128 * 3, 256, 128 * 4, 128 * 3);
        susano[3][0] = susanoall.getSubimage(0, 128 * 5, 128 * 5, 128 * 4);
        for (int i = 0; i < 2; i++) {
            susano[4][i] = susanoall.getSubimage(448 * i, 128 * 9, 448, 128 * 4);
        }
        for (int i = 0; i < 3; i++) {
            susano[5][i] = susanoall.getSubimage(298 * i, 128 * 13, 298, 128 * 2);
        }
        for (int i = 0; i < 3; i++) {
            susano[6][i] = susanoall.getSubimage(298 * i, 128 * 15, 298, 128 * 2);
        }
        for (int i = 0; i < 3; i++) {
            susano[7][i] = susanoall.getSubimage(298 * i, 128 * 17, 298, 128 * 2);
        }
    }

    public void loadLevel() {
        lvl = LoadSave.loadLevel("level1");
    }

    public void setLevel(int[][] lvl) {
        this.lvl = lvl;
    }

    private void updateAnimationTick() {
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            susanoIndex[susanoRow]++;

            if (susanoIndex[0] >= 7) {
                susanoIndex[0] = 0;
                susanoRow++;
            } else if (susanoIndex[1] >= 6) {
                susanoIndex[1] = 0;
                susanoRow++;
            } else if (susanoIndex[2] >= 2) {
                susanoIndex[2] = 0;
                susanoRow++;
            } else if (susanoIndex[3] >= 1) {
                susanoIndex[3] = 0;
                susanoRow++;
            } else if (susanoIndex[4] >= 2) {
                susanoIndex[4] = 0;
                susanoRow++;
            } else if (susanoIndex[5] >= 3) {
                susanoIndex[5] = 0;
                susanoRow++;
            } else if (susanoIndex[6] >= 3) {
                susanoIndex[6] = 0;
                susanoRow++;
            } else if (susanoIndex[7] >= 1) {
                susanoIndex[7] = 0;
                susanoRow = 0;
            }
        }
    }

    public void updates() {
        enemymanager.update();
        towermanager.update();
    }

    @Override
    public void render(Graphics g) {
        drawBackground(g);
        g.drawImage(ingranaggio, 10, 10, 80, 80, null);
        drawButton(g);
        enemymanager.draw(g);
        towermanager.draw(g);
        drawTileOver(g);
        //////////////
        int susanoX = Tile.spriteWidth * 12 - 32;
        int susanoY = Tile.spriteHeight * 7 - 5;
        int dim = 110;
        switch (susanoRow) {
            case 0:
                g.drawImage(susano[0][susanoIndex[0]], susanoX - dim / 2, susanoY - dim, dim, dim, null);
                break;
            case 1:
                if (susanoIndex[1] != 5) {
                    g.drawImage(susano[1][susanoIndex[1]], susanoX - dim / 2, susanoY - dim, dim, dim, null);
                } else {
                    g.drawImage(susano[1][5], susanoX - dim, susanoY - dim, dim * 2, dim, null);
                }
                break;
            case 2:
                if (susanoIndex[2] == 0) {
                    g.drawImage(susano[2][susanoIndex[2]], susanoX - dim * 3 / 2, susanoY - dim * 2, dim * 3, dim * 2, null);
                } else {
                    g.drawImage(susano[2][susanoIndex[2]], susanoX - dim * 2, susanoY - dim * 3, dim * 4, dim * 3, null);
                }
                break;
            case 3:
                g.drawImage(susano[3][susanoIndex[3]], susanoX - dim * 5 / 2, susanoY - dim * 4, dim * 5, dim * 4, null);
                break;
            case 4:
                g.drawImage(susano[4][susanoIndex[4]], susanoX - ((int) (dim * 3.5)) / 2, susanoY - dim * 4, (int) (dim * 3.5), dim * 4, null);
                break;
            default:
                g.drawImage(susano[susanoRow][susanoIndex[susanoRow]], susanoX - ((int) (dim * 2.32)) / 2, susanoY - dim * 2, (int) (dim * 2.32), dim * 2, null);
                break;
        }
        //////////////
        if (paused) {
            pauseoverlay.draw(g);
            pauseoverlay.update();
        } else {

            updateAnimationTick();
        }
        actionbar.draw(g);

        drawSelectedTower(g);
    }
    
    private void drawBackground(Graphics g) {
        for (int y = 0; y < lvl.length; y++) {
            for (int x = 0; x < lvl[0].length; x++) {
                int id = lvl[y][x];
                g.drawImage(game.getTileManager().getSprite(id), x * Tile.spriteWidth, y * Tile.spriteHeight, Tile.spriteWidth, Tile.spriteHeight, null);
            }
        }
    }
    
    private void drawTileOver(Graphics g){
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

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getY() > (int) (Game.currentScreenHeight * 0.829)) {
            actionbar.mouseClicked(e);
        } else {
            if (Bmenu.getBounds().contains(e.getX(), e.getY())) {
                setPaused(!getPaused());
            }
            if (selectedTower != null) {
                if (isTileAvailable(mouseX, mouseY)) {
                    if (getTowerAt(mouseX, mouseY) == null) {
                        towermanager.addTower(selectedTower, mouseX, mouseY);
                        selectedTower = null;
                    }

                }

            }else{
                //prendere la torre se esiste nella posizione del click
                Tower t = getTowerAt(mouseX, mouseY);
                actionbar.displayTower(t);
                            
            }
        }
    }

    private Tower getTowerAt(int x, int y) {
        return towermanager.getTowerAt(x, y);
    }

    private boolean isTileAvailable(int x, int y) {
        int id = lvl[y / Tile.spriteHeight][x / Tile.spriteWidth];
        int tiletype = game.getTileManager().getTile(id).getTileType();
        return tiletype == GRASS_TILE;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Bmenu.setMouseOver(false);
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

    @Override
    public void mousePressed(MouseEvent e) {
        if (Bmenu.getBounds().contains(e.getX(), e.getY())) {
            Bmenu.setMousePressed(true);
        }
        if (e.getY() > (int) (Game.currentScreenHeight * 0.829)) {
            actionbar.mousePressed(e);
        }
        if (paused) {
            pauseoverlay.mousePressed(e);
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

    public void setSelectedTower(Tower selectedTower) {
        this.selectedTower = selectedTower;
    }

    private void drawSelectedTower(Graphics g) {
        if (selectedTower != null) {
            g.drawImage(towermanager.getTowerImgs()[selectedTower.getTypetower()][0], mouseX, mouseY, Tile.spriteWidth, Tile.spriteHeight, null);
        }
    }
    public void keyPressed(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
            if(selectedTower!=null)
                selectedTower=null;
            else
                setPaused(!(game.getLevel1().getPaused()));
        }

    }
}

