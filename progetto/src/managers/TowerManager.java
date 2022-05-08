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
    private int aniTick,index6=0,index7=0,index10=0, aniSpeed = 22;
    
    public TowerManager(Level1 level1){
        this.level1= level1;
        loadTower(); 
    }
    private void loadTower(){
        BufferedImage towers = LoadSave.getImage(LoadSave.NARUTO_TOWER);
        
        towerImgs = new BufferedImage[3][10];
        for (int i = 0; i < towerImgs.length; i++) {
            for (int j = 0; j < towerImgs[i].length; j++) {
                towerImgs[i][j]=towers.getSubimage(j*128, i*128, 128, 128);
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
            drawTower(t, g);
        }
        updateAnimationTick();
    }
    private void drawTower(Tower t, Graphics g){
        switch(t.getTypetower()){
            case 0:
                g.drawImage(towerImgs[0][index6], (int)t.getX(), (int)t.getY(), Tile.spriteWidth, Tile.spriteHeight, null);
                break;
            case 1:
                g.drawImage(towerImgs[1][index7], (int)t.getX(), (int)t.getY(), Tile.spriteWidth, Tile.spriteHeight, null);
                break;
            case 2:
                g.drawImage(towerImgs[2][index10], (int)t.getX(), (int)t.getY(), Tile.spriteWidth, Tile.spriteHeight, null);
                break;
        }
    }
    
    private void updateAnimationTick() {
        aniTick++;
        if(aniTick>= aniSpeed){
            aniTick=0;
            index6++; 
            index7++; 
            index10++;
            if(index6>= 6)
                index6=0;
            if(index7>=7)
                index7=0;
            if(index10>=10){
                index10=0;
            }
        }
    }
    public BufferedImage[][] getTowerImgs(){
        return towerImgs;
    }
}
