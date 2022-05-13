package managers;

import enemies.Enemy;
import static helpz.Constants.Projectiles.*;
import static helpz.Constants.Towers.*;
import helpz.LoadSave;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashSet;
import objects.Projectile;
import objects.Tile;
import progetto.Game;
import towers.AttackAnimation;
import towers.Tower;

public class ProjectileManager {
    
    private Game game;
    private String state;
    
    private ArrayList<Projectile> projectiles = new ArrayList<>();
    
    private BufferedImage[][] naruto_attack;
    
    private int projId=0;
    
    public ProjectileManager(Game game, String state){
        this.game = game;
        this.state = state;
        importImgs();
    }
    
    private void importImgs(){
        naruto_attack = new BufferedImage[3][10];
        BufferedImage atlas = LoadSave.getImage(LoadSave.NARUTO_ATTACK);
        
        for (int i = 0; i < 4; i++) {
            naruto_attack[0][i] = atlas.getSubimage(64 * i, 0, 64, 64);
        }
        for (int i = 0; i < 3; i++) {
            naruto_attack[1][i] = atlas.getSubimage(64 * i, 64, 64, 64);
        }
        for (int i = 0; i < 10; i++) {
            naruto_attack[2][i] = atlas.getSubimage(0, 128+64*i, 64*4, 64);
        }
    }
    public void newProjectile(Tower t, Enemy e){
        int type  = getProjectileType(t);
        
        int distx = (int)(t.getX()- e.getX());
        int disty = (int)(t.getY()- e.getY());
        int totaldistance = Math.abs(distx) + Math.abs(disty);
            
        float xper = (float) Math.abs(distx)/totaldistance;
        float yper  = 1.0f - xper;
        float speedx = xper * helpz.Constants.Projectiles.getSpeed(type);
        float speedy = helpz.Constants.Projectiles.getSpeed(type) - speedx;
        
        if(t.getX()>e.getX())
            speedx*=-1;
        if(t.getY()>e.getY())
            speedy*=-1;
        
        float arc= (float)Math.atan(disty/(float)distx);
        float rotate = (float)Math.toDegrees(arc);
        if(distx<0)
            rotate+=180;
        projectiles.add(new Projectile(t.getX()+Tile.spriteWidth/2, t.getY()+Tile.spriteHeight/2, speedx, speedy, t.getDamage(), rotate, projId++, type));
    }
    public void update(){
        if(projectiles!=null){
            for(Projectile p: projectiles){
                if(p.isExist()){
                   p.move(); 
                   if(isProjHit(p)){
                        p.setExist(false);     
                   }         
                }         
            }
        }
    }
    public void draw(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        if(projectiles!=null){
            for(Projectile p: projectiles){
                if(p.isExist()){
                    
                    if(p.getProjectiletype()!=PUNCH){
                        g2d.translate(p.getPosition().x, p.getPosition().y);
                        g2d.rotate(Math.toRadians(p.getRotation())); 
                        g2d.drawImage(naruto_attack[p.getProjectiletype()][p.getIndex()], -Tile.spriteWidth/2, -Tile.spriteHeight/2, null);
                        g2d.rotate(-Math.toRadians(p.getRotation()));
                        g2d.translate(-p.getPosition().x, -p.getPosition().y);
                    }else{
                        g2d.translate(p.getPosition().x, p.getPosition().y);
                        g2d.drawImage(naruto_attack[p.getProjectiletype()][p.getIndex()], -2*Tile.spriteWidth, -Tile.spriteHeight/2, null);
                        g2d.translate(-p.getPosition().x, -p.getPosition().y);
                    }
                }   
            }
        }      
    }
    public boolean isProjHit(Projectile p){
        switch(state){
            case "level1":
                for(Enemy e: game.getLevel1().getEnemyManager().getEnemies()){
                    if(e.getBounds().contains(p.getPosition())){
                        e.damage(p.getDmg()); 
                        return true;
                    }
                }
                break;
        }  
        return false;
    }
    public int getProjectileType(Tower t){
        switch(t.getTypetower()){
            case NARUTO:
                return SHURIKEN;
            case SASUKE:
                return FIREBALL;
            case SAKURA:
                return PUNCH;
        }
        return 0;
    }
}
