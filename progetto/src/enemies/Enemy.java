
package enemies;

import java.awt.Rectangle;
import objects.Tile;
import static helpz.Constants.Direction.*;

public abstract class Enemy {
    protected float x, y;
    protected Rectangle bounds;
    protected int hp;
    protected int maxhp;
    protected int id;
    protected int enemytype;
    protected int lastDir;
    protected boolean alive=true;
    public Enemy(float x, float y, int id, int enemytype) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.enemytype = enemytype;
        bounds = new Rectangle((int)x , (int) y, Tile.spriteWidth, Tile.spriteHeight);
        lastDir=-1;
        setStartHp();
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
        updateBound();
    }
    public void updateBound(){
        bounds.x=(int)x;
        bounds.y=(int)y;
    }
    protected void setStartHp(){
        hp=helpz.Constants.Enemy.getStartHp(enemytype);
        maxhp=hp;
    }
    public void setPosition(int x, int y){
        this.x=x;
        this.y=y;
    }
    public void damage(int dmg){
        hp -= dmg;
        if(hp <= 0){
            alive=false;
        }
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
        return enemytype;
    }  
    public int getLastDir(){
        return lastDir;
    }
    public float getHpbar(){
        return hp/(float)maxhp;
    }
    public boolean isAlive(){
        return alive;
    }
}
