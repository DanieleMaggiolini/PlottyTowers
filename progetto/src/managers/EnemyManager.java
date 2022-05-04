
package managers;

import enemies.Enemy;
import helpz.LoadSave;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import objects.Tile;
import scenes.Level1;

public class EnemyManager {
    Level1 level1;
    private BufferedImage[] enemyImgs;
    private ArrayList<Enemy> enemies = new ArrayList<>();
    
    public EnemyManager(Level1 level1) {
        this.level1= level1;
        enemyImgs = new BufferedImage[3];
        loadEnemyImgs();
    }
    private void loadEnemyImgs(){
        BufferedImage enemy1 = LoadSave.getImage(LoadSave.NARUTO);
        enemyImgs[0] = enemy1.getSubimage(0, 0, 64, 64);
    }
    public void update(){
        for (Enemy e : enemies) {
            e.move(0.5f, 0);
        }
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
        g.drawImage(enemyImgs[0], (int)e.getX(), (int)e.getY(), null);
    }
}
