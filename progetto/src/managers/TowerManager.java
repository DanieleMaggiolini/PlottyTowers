package managers;

import static helpz.Constants.Towers.*;
import helpz.LoadSave;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import objects.Tile;
import scenes.Level1;
import towers.Tower;

public class TowerManager {
    private Level1 level1;
    private BufferedImage[][] towerImgs;
    private Tower tower;
    
    public TowerManager(Level1 level1){
        this.level1= level1;
        loadTower();
        initTower();
    }
    private void loadTower(){
        BufferedImage towers = LoadSave.getImage(LoadSave.SASUKE_BASE);
        
        towerImgs = new BufferedImage[3][7];
        for (int i = 0; i < towerImgs.length; i++) {
            for (int j = 0; j < towerImgs[i].length; j++) {
                towerImgs[i][j]=towers.getSubimage(0*64, 0, 64, 128);
            }
        }
    }
    private void initTower(){
        tower=new Tower(3* Tile.spriteWidth, 6*Tile.spriteHeight, 0, SASUKE);
    }
    public void update(){
        
    }
    public void draw(Graphics g){
         drawTower(g);
    }
    public void drawTower(Graphics g){
        g.drawImage(towerImgs[SASUKE][0], tower.getX(), tower.getY(), null);
    }
}
