package objects;

import java.awt.geom.Point2D;
import towers.AttackAnimation;


public class Projectile {
    
    private Point2D.Float position;
    private int id, projectiletype;
    private boolean exist=true;
    private float speedx, speedy, rotation;
    private int dmg;
    private AttackAnimation index;
    public Projectile(float x, float y, float speedx, float speedy, int dmg, float rotation, int id, int projectiletype) {
        this.position = new Point2D.Float(x,y);
        this.speedx=speedx;
        this.speedy=speedy;
        this.dmg=dmg;
        this.rotation=rotation;
        this.id = id;
        this.projectiletype = projectiletype;
        index = new AttackAnimation(projectiletype);
    }
   
    public void move(){
        position.x += speedx;
        position.y += speedy;
    }

    public void setPosition(Point2D.Float position) {
        this.position = position;
    }
    public void setExist(boolean exist){
        this.exist=exist;
    }
    public Point2D.Float getPosition() {
        return position;
    }

    public int getId() {
        return id;
    }

    public int getProjectiletype() {
        return projectiletype;
    }
    
    public boolean isExist() {
        return exist;
    }
    public int getDmg(){
        return dmg;
    }
    public float getRotation(){
        return rotation;
    }
    public int getIndex(){
        return index.getAnimationIndex();
    }
}
