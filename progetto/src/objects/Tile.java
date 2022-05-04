package objects;

import helpz.ImgFix;
import helpz.LoadSave;
import java.awt.image.BufferedImage;
import progetto.Game;

public class Tile {
    
    private BufferedImage sprite;
    private int id;
    private String name;
    
    
    public static int spriteWidth;
    public static int spriteHeight;
    private int[][] lvl;
    private int tempHeight;
    
    public Tile(BufferedImage sprite, int id, String name){
        this.sprite = sprite;
        this.id=id;
        this.name=name;
        
        
        lvl = LoadSave.loadLevel("level1");;  
        tempHeight =(int)(Game.currentScreenHeight*0.17);
        spriteWidth = Game.currentScreenWidth/lvl[0].length;
        spriteHeight = (Game.currentScreenHeight-tempHeight)/lvl.length;
    }

    public BufferedImage getSprite() {
        return sprite;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    
    public int getSpriteWidth(){
        return spriteWidth;
    }
    public int getSpriteHeight(){
        return spriteHeight;
    }
}
