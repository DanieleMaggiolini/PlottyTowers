/**
* @author  Daniele Maggiolini
* @author  Mattia Minotti
* @version 1.0
* @file Enemy.java 
* 
* @brief gestisce dei nemici.
*
*/
package enemies;

import java.awt.Rectangle;
import objects.Tile;
import static helpz.Constants.Direction.*;
import managers.EnemyManager;

/**
 * @class Enemy
 *
 * @brief gestisce i nemici e i loro attributi.
 *
 */
public class Enemy {

    private float x, y;
    private Rectangle bounds;
    private int hp;
    private int maxhp;
    private int enemytype;
    private int lastDir;
    private boolean alive = true;
    private EnemyManager em;

    public Enemy(float x, float y, int enemytype, EnemyManager em) {
        this.x = x;
        this.y = y;
        this.enemytype = enemytype;
        this.em = em;
        bounds = new Rectangle((int) x, (int) y, Tile.spriteWidth, Tile.spriteHeight);
        lastDir = -1;
        setStartHp();
    }

    public void move(float s, int dir) {
        lastDir = dir;
        switch (dir) {
            case LEFT:
                this.x -= s;
                break;
            case RIGHT:
                this.x += s;
                break;
            case UP:
                this.y -= s;
                break;
            case DOWN:
                this.y += s;
                break;
        }
        updateBound();
    }

    public void updateBound() {
        bounds.x = (int) x;
        bounds.y = (int) y;
    }

    protected void setStartHp() {
        hp = helpz.Constants.Enemy.getStartHp(enemytype);
        maxhp = hp;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void damage(int dmg) {
        if (alive) {
            hp -= dmg;
            if (hp <= 0) {
                alive = false;
                em.addCoin(enemytype);
            }
        }

    }

    public void kill() {
        alive = false;
        hp = 0;
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
    
    public int getEnemyType() {
        return enemytype;
    }

    public int getLastDir() {
        return lastDir;
    }

    public float getHpbar() {
        return hp / (float) maxhp;
    }

    public boolean isAlive() {
        return alive;
    }
}
