
package ui;

import helpz.LoadSave;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import progetto.Game;
import static progetto.GameStates.*;
import static progetto.GameStates.SETTINGS;
import static progetto.GameStates.setGameState;


public class LevelMenu {
    private BufferedImage background;
    private MyButton lvl1,lvl2,lvl3,lvl4;
    private int width,height;
    
    private Point relativeposition =new Point(0,0);
    private Point prevPt= new Point(0,0);

 
    public LevelMenu() {
        width=80;
        height=80;
        impImage();
        initButton();
    }
    
    private void impImage() {
       background = LoadSave.getImage(LoadSave.LEVEL_MENU);
    }
    private void initButton(){
        Font f=new Font("Arial",Font.BOLD,40);
        Color c=new Color(0,0,0);
        lvl1 = new MyButton("1", 380, 320, width, height, f, c);
        lvl2 = new MyButton("2", 760, 640, width, height, f, c);
        lvl3 = new MyButton("3", 1600, 650, width, height, f, c);
    }
    
    
    public void draw(Graphics g){
        g.drawImage(background, relativeposition.x, 0,3845, Game.currentScreenHeight, null);
        
        drawButton(g);
        lvl1.drawC(g);
        lvl2.drawC(g);
        lvl3.drawC(g);
    }
    public void drawButton(Graphics g){
        g.setColor(Color.BLACK);
        g.drawOval(lvl1.getX(), lvl1.getY(), width, height);
        g.drawOval(lvl2.getX(), lvl2.getY(), width, height);
        g.drawOval(lvl3.getX(), lvl3.getY(), width, height);
        
        g.setColor(Color.RED);
        g.fillOval(lvl1.getX(), lvl1.getY(), width, height);
        g.fillOval(lvl2.getX(), lvl2.getY(), width, height);
        g.fillOval(lvl3.getX(), lvl3.getY(), width, height);
    }
    
    
    public void mousePressed(MouseEvent e) {
        prevPt= e.getPoint();
    }
    
    public void mouseDragged(MouseEvent e) {
        Point currentPt= e.getPoint();       
        relativeposition.translate((int)(currentPt.getX()-prevPt.getX()), 0);
        if(relativeposition.x<-(background.getWidth()-Game.currentScreenWidth)){
            relativeposition.x=-(background.getWidth()-Game.currentScreenWidth);
        }else if(relativeposition.x>0){
            relativeposition.x=0;
        }else{
            int temp=(int)(currentPt.getX()-prevPt.getX());
            lvl1.moveX(temp);
            lvl2.moveX(temp);
            lvl3.moveX(temp);
        }
        
        prevPt=currentPt;
    }
    public void mouseClicked(MouseEvent e) {
        if (lvl1.getBounds().contains(e.getX(), e.getY())) {
            setGameState(LVL1);
        }else if (lvl2.getBounds().contains(e.getX(), e.getY())) {
            setGameState(SETTINGS);
        }else if (lvl3.getBounds().contains(e.getX(), e.getY())) {
            System.exit(0);
        }
    }
}
