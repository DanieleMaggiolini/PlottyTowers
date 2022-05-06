
package managers;

import enemies.Enemy;
import static helpz.Constants.Direction.*;
import static helpz.Constants.Tiles.*;
import helpz.LoadSave;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import objects.Tile;
import scenes.Level1;

public class EnemyManager {
    Level1 level1;
    private BufferedImage[][] enemyImgs;
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private float speed=0.5f;
    private int aniTick,aniIndex, aniSpeed = 5;
    
    public EnemyManager(Level1 level1) {
        this.level1= level1;
        enemyImgs = new BufferedImage[3][6];
        loadEnemyImgs();
        addEnemy(0*Tile.spriteWidth, 3*Tile.spriteHeight);
    }
    private void loadEnemyImgs(){
        BufferedImage enemy1 = LoadSave.getImage(LoadSave.OROCHIMARU);
        for (int i = 0; i < 6; i++) {
            enemyImgs[0][i] = enemy1.getSubimage(i*64, 0, 64, 64);
        }
    }
    public void update(){
        for (Enemy e : enemies){
           updateEnemyMove(e);  
        } 
    }
    public void updateEnemyMove(Enemy e){
        int newX= (int)(e.getX() + getSpeedWidth(e.getLastDir()));
        int newY = (int)(e.getY() + getSpeedHeight(e.getLastDir()));
        if(getTileType(newX,newY)== ROAD_TILE){
            e.move(speed, e.getLastDir());
        }else if(isEnd(e)){
        }else{
            setDirection(e);
        }
    }
    private int getTileType(int x,int y){
       return level1.getTileType(x, y);
    }
    private boolean isEnd(Enemy e){
        return false;
    }        
    private void setDirection(Enemy e){
        int dir=e.getLastDir();
        
        int cordX = (int)(e.getX()/Tile.spriteWidth); 
        int cordY = (int)(e.getY()/Tile.spriteHeight);
        fixTile(e, dir, cordX, cordY);
        
        if(dir == LEFT || dir == RIGHT){
            int newY = (int)(e.getY() + getSpeedHeight(UP)); 
            if(getTileType((int)(e.getX()),newY)== ROAD_TILE){
                e.move(speed,UP);
                return;
            }
            newY = (int)(e.getY() + getSpeedHeight(DOWN));
            if(getTileType((int)(e.getX()),newY)== ROAD_TILE)
                e.move(speed,DOWN);    
        }else{
            int newX= (int)(e.getX() + getSpeedWidth(RIGHT));
            if(getTileType(newX,(int)(e.getY()))== ROAD_TILE){
                e.move(speed,RIGHT);
                return;
            }
            newX= (int)(e.getX() + getSpeedWidth(LEFT));
            if(getTileType(newX,(int)(e.getY()))== ROAD_TILE)
                e.move(speed,LEFT);    
        }  
    }     
    public void fixTile(Enemy e, int dir, int x, int y){
        switch(dir){
            case RIGHT:
                 if(x<29)
                    x++;
                break;
            case DOWN:
                if(y<13)
                    y++;
                break;
        }
        e.setPosition(x*Tile.spriteWidth,y*Tile.spriteHeight);
    }
    public float getSpeedWidth(int dir){
        if(dir==LEFT)
            return -speed;
        if(dir==RIGHT)
            return speed+Tile.spriteWidth;
        return 0;
    }
    public float getSpeedHeight(int dir){
        if(dir==UP)
            return -speed;
        if(dir==DOWN)
            return speed+Tile.spriteHeight;
        return 0;
    }
    
    public void addEnemy(int x, int y){
        enemies.add(new Enemy(x, y, 0, 0));//Tile.spriteWidth*x, Tile.spriteHeight*y
    }
    public void draw(Graphics g){
        for (Enemy e : enemies) {
            drawEnemy(e, g);
        }   
    }
    private void drawEnemy(Enemy e, Graphics g){
        g.drawImage(enemyImgs[0][aniIndex], (int)e.getX(), (int)e.getY(), Tile.spriteWidth, Tile.spriteHeight, null);
        updateAnimationTick();
    }
    private void updateAnimationTick() {
        aniTick++;
        if(aniTick>= aniSpeed){
            aniTick=0;
            aniIndex++; 
            
            if(aniIndex>= enemyImgs[0].length)
                aniIndex=0;
        }
    }
}
