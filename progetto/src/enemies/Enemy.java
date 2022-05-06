
package enemies;

import java.awt.Rectangle;
import objects.Tile;
import static helpz.Constants.Direction.*;

public abstract class Enemy {
    private float x, y;
    private Rectangle bounds;
    private int hp;
    private int id;
    private int enemyType;
    private int lastDir;
    
    public Enemy(float x, float y, int id, int enemyType) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.enemyType = enemyType;
        bounds = new Rectangle((int)x , (int) y, Tile.spriteWidth, Tile.spriteHeight);
        lastDir=-1;
    }
    
    public void move(float s, int dir){
        lastDir=dir;
        switch(dir){
            case LEFT:
                this.x-=s;
                break;
            case RIGHT:
                this.x+=s;
                break;
            case UP:
                this.y-=s;
                break;
            case DOWN:
                this.y+=s;
                break;
        }  
    }
    
    public void setPosition(int x, int y){
        this.x=x;
        this.y=y;
    }
    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }
    public Rectangle getBounds() {
        return bounds;
    }
    public int getHp() {
        return hp;
    }
    public int getId() {
        return id;
    }
    public int getEnemyType() {
        return enemyType;
    }  
    public int getLastDir(){
        return lastDir;
    }
}
