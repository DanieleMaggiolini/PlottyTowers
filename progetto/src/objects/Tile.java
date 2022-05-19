package objects;

import helpz.ImgFix;
import helpz.LoadSave;
import java.awt.image.BufferedImage;
import progetto.Game;

public class Tile {
    
    private BufferedImage sprite;
    private int id, tiletype;
    public static int spriteWidth;
    public static int spriteHeight;
    private int[][] lvl;
    private int tempHeight;
    
    public Tile(BufferedImage sprite, int id, int tiletype){
        this.sprite = sprite;
        this.id=id;    
        this.tiletype=tiletype;
        lvl = LoadSave.loadLevel("level1");;  
        tempHeight =(int)(Game.currentScreenHeight*0.17);
        spriteWidth = Game.currentScreenWidth/lvl[0].length;
        spriteHeight = (Game.currentScreenHeight-tempHeight)/lvl.length;
        if(Game.currentScreenWidth!=1920)
            spriteWidth+=0.5;
        if(Game.currentScreenHeight!=1080)
            spriteHeight+=0.5;
    }

    public BufferedImage getSprite() {
        return sprite;
    }

    public int getId() {
        return id;
    }
    
    public int getSpriteWidth(){
        return spriteWidth;
    }
    public int getSpriteHeight(){
        return spriteHeight;
    }
    public int getTileType(){
        return tiletype;
    }
}
