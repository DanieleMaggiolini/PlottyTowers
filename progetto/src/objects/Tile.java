package objects;

import helpz.LevelBuild;
import java.awt.image.BufferedImage;
import progetto.Game;

public class Tile {
    
    private BufferedImage sprite;
    private int id;
    private String name;
    public Tile(BufferedImage sprite, int id, String name){
        this.sprite = sprite;
        this.id=id;
        this.name=name;
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
    private int[][] lvl = LevelBuild.getLevelData();  
    private int tempHeight=(int)(Game.currentScreenHeight*0.17);
    private int spriteWidth = Game.currentScreenWidth/lvl[0].length;
    private int spriteHeight= (Game.currentScreenHeight-tempHeight)/lvl.length;
    
    public int getSpriteWidth(){
        return spriteWidth;
    }
    public int getSpriteHeight(){
        return spriteHeight;
    }
}
