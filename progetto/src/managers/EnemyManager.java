package managers;

import enemies.Enemy;
import enemies.*;
import static helpz.Constants.Direction.*;
import static helpz.Constants.Enemy.*;
import static helpz.Constants.Tiles.*;
import helpz.LoadSave;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import objects.Tile;
import progetto.Game;
import scenes.Level1;

public class EnemyManager {
    private Game game;
    private String state;
    private BufferedImage[][] enemyImgs;
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private int aniTick,index6=0,index8=0, aniSpeed = 15;
    private int startX, startY, endX, endY;
    private int hpBarWidth=Tile.spriteWidth/2;
    
    public EnemyManager(Game game, String state) {
        this.game=game;
        this.state=state;
        enemyImgs = new BufferedImage[3][8];
        startX=0*Tile.spriteWidth;
        startY=3*Tile.spriteHeight;
        endX= 29*Tile.spriteWidth;
        endY= 12*Tile.spriteHeight;
        loadEnemyImgs();
//        addEnemy(OROCHIMARU);
//        addEnemy(TOBI);
//        addEnemy(MADARA);
    }
    public void setStartEnd(int sx, int sy, int ex, int ey){
        this.startX=sx;
        this.startY=sy;
        this.endX=ex;
        this.endY=ey;
    }
    private void loadEnemyImgs(){
        BufferedImage enemy1 = LoadSave.getImage(LoadSave.NARUTO_ENEMY);
        for (int i = 0; i < enemyImgs.length; i++) {    
            for(int j=0; j < enemyImgs[i].length; j++){
                if(i!=1 && j>5){
                    
                }else{
                    enemyImgs[i][j] = enemy1.getSubimage(j*64, i*64, 64, 64);
                }           
            }
        }
        
    }
    public void update(){
        for (Enemy e : enemies){
            if(e.isAlive())
                updateEnemyMove(e);  
        } 
    }
   
    public void updateEnemyMove(Enemy e){
        if(e.getLastDir()==-1)
            setDirection(e);
        int newX= (int)(e.getX() + getSpeedWidth(e.getLastDir(), e.getEnemyType()));
        int newY = (int)(e.getY() + getSpeedHeight(e.getLastDir(), e.getEnemyType()));
        if(getTileType(newX,newY)== ROAD_TILE){
            e.move(getSpeed(e.getEnemyType()), e.getLastDir());
        }else if(isEnd(e)){
            e.kill();
            System.out.println("live lost");
        }else{
            setDirection(e);
        }
    }
    private int getTileType(int x,int y){
        switch(state){
            case "level1":
                return game.getLevel1().getTileType(x, y);
           
        }
        return -1;
    }
    private boolean isEnd(Enemy e){
        if(e.getX()==endX)
            if(e.getY()==endY)
                return true;         
        return false;      
    }        
    private void setDirection(Enemy e){
        int dir=e.getLastDir();
        
        int cordX = (int)(e.getX()/Tile.spriteWidth); 
        int cordY = (int)(e.getY()/Tile.spriteHeight);
        
        fixTile(e, dir, cordX, cordY);
        
        if(isEnd(e))
            return;
        
        if(dir == LEFT || dir == RIGHT){
            int newY = (int)(e.getY() + getSpeedHeight(UP, e.getEnemyType())); 
            if(getTileType((int)(e.getX()),newY)== ROAD_TILE){
                e.move(getSpeed(e.getEnemyType()),UP);
                return;
            }
            newY = (int)(e.getY() + getSpeedHeight(DOWN, e.getEnemyType()));
            if(getTileType((int)(e.getX()),newY)== ROAD_TILE)
                e.move(getSpeed(e.getEnemyType()),DOWN);    
        }else{
            int newX= (int)(e.getX() + getSpeedWidth(RIGHT, e.getEnemyType()));
            if(getTileType(newX,(int)(e.getY()))== ROAD_TILE){
                e.move(getSpeed(e.getEnemyType()),RIGHT);
                return;
            }
            newX= (int)(e.getX() + getSpeedWidth(LEFT, e.getEnemyType()));
            if(getTileType(newX,(int)(e.getY()))== ROAD_TILE)
                e.move(getSpeed(e.getEnemyType()),LEFT);    
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
    public float getSpeedWidth(int dir, int enemytype){
        if(dir==LEFT)
            return -getSpeed(enemytype);
        if(dir==RIGHT)
            return getSpeed(enemytype)+Tile.spriteWidth;
        return 0;
    }
    public float getSpeedHeight(int dir, int enemytype){
        if(dir==UP)
            return -getSpeed(enemytype);
        if(dir==DOWN)
            return getSpeed(enemytype)+Tile.spriteHeight;
        return 0;
    }
    
    public void addEnemy(int enemytype){
        switch(enemytype){
            case OROCHIMARU:
                enemies.add(new Orochimaru(startX, startY, 0));
                break;
            case TOBI:
                enemies.add(new Tobi(startX, startY, 0));
                break;
            case MADARA:
                enemies.add(new Madara(startX, startY, 0));
                break;
        }     
    }
    public void spawnEnemy(int next){
        addEnemy(next);
    }
    public ArrayList<Enemy> getEnemies(){
        return enemies;
    }
    public void draw(Graphics g){
        for (Enemy e : enemies) {
            if(e.isAlive()){
                drawEnemy(e, g);
                drawHpBar(e, g);
            } 
        }   
        updateAnimationTick();
    }
    private void drawEnemy(Enemy e, Graphics g){
        switch(e.getEnemyType()){
            case 0:
                g.drawImage(enemyImgs[0][index6], (int)e.getX(), (int)e.getY(), Tile.spriteWidth, Tile.spriteHeight, null);
                break;
            case 1:
                g.drawImage(enemyImgs[1][index8], (int)e.getX(), (int)e.getY(), Tile.spriteWidth, Tile.spriteHeight, null);
                break;
            case 2:
                g.drawImage(enemyImgs[2][index6], (int)e.getX(), (int)e.getY(), Tile.spriteWidth, Tile.spriteHeight, null);
                break;
        }
        
    }
    private void drawHpBar(Enemy e, Graphics g){
        g.setColor(Color.red);
        g.fillRect((int)e.getX()+(Tile.spriteWidth/2)-(getNewBartWidth(e)/2), (int)e.getY()+Tile.spriteHeight, getNewBartWidth(e), 5);
       
    }
    private int getNewBartWidth(Enemy e){
        return (int)(hpBarWidth * e.getHpbar());
    }
    private void updateAnimationTick() {
        aniTick++;
        if(aniTick>= aniSpeed){
            aniTick=0;
            index6++; 
            index8++; 
            if(index6>= 6)
                index6=0;
            if(index8>=8)
                index8=0;
        }
    }
    public int getEnemyRemaning() {
        int enemyremaning=0;
        for (Enemy e : enemies)
            if(e.isAlive())
                enemyremaning++;     
        return enemyremaning;
    }

}