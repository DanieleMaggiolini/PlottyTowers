package objects;

import java.awt.geom.Point2D;


public class Projectile {
    
    private Point2D.Float position;
    private int id, projectiletype;
    private boolean exist=true;

    public Projectile(float x, float y, int id, int projectiletype) {
        this.position = new Point2D.Float(x,y);
        this.id = id;
        this.projectiletype = projectiletype;
    }
   
    public void move(float x, float y){
        position.x +=x;
        position.y +=y;
    }

    public void setPosition(Point2D.Float position) {
        this.position = position;
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
   
}
