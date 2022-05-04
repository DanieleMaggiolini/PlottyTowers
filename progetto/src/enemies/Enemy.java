
package enemies;

import java.awt.Rectangle;
import objects.Tile;


public class Enemy {
    private float x, y;
    private Rectangle bounds;
    private int hp;
    private int id;
    private int enemyType;
    
    public Enemy(float x, float y, int id, int enemyType) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.enemyType = enemyType;
        bounds = new Rectangle((int)x , (int) y, Tile.spriteWidth, Tile.spriteHeight);
    }
    
    public void move(float x, float y){
        this.x+=x;
        this.y+=y;
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
}
