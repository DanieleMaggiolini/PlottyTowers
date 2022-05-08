package managers;

import static helpz.Constants.Towers.*;
import helpz.LoadSave;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import objects.Tile;
import scenes.Level1;
import towers.Tower;

public class TowerManager {
    private Level1 level1;
    private BufferedImage[][] towerImgs;
    private ArrayList<Tower> towers = new ArrayList<>();
    private int towerAmmount = 0;
    
    public TowerManager(Level1 level1){
        this.level1= level1;
        loadTower(); 
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
    public void update(){
        
    }
    public void addTower(Tower selectedTower, int xPos, int yPos) {
        towers.add(new Tower(xPos, yPos, towerAmmount++, selectedTower.getTypetower()));
    }
    public void draw(Graphics g){
        for (Tower t : towers) {
            g.drawImage(towerImgs[t.getTypetower()][0], t.getX(), t.getY(), null);
        }
    }
    
    public BufferedImage[][] getTowerImgs(){
        return towerImgs;
    }

    
}
