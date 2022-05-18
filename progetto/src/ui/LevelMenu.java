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
    private BufferedImage[] buttons;
    private MyButton lvl1,lvl2,lvl3,lvl4;
    private int width,height;
    
    private Point relativeposition =new Point(0,0);
    private Point prevPt= new Point(0,0);

    private int lvl1X=(int)(Game.currentScreenWidth*0.242);
    private int lvl1Y=(int)(Game.currentScreenHeight*0.476);
    private int lvl2X=(int)(Game.currentScreenWidth*0.723);
    private int lvl2Y=(int)(Game.currentScreenHeight*0.324);
    private int lvl3X=(int)(Game.currentScreenWidth*1.072);
    private int lvl3Y=(int)(Game.currentScreenHeight*0.481);
    private int lvl4X=(int)(Game.currentScreenWidth*1.693);
    private int lvl4Y=(int)(Game.currentScreenHeight*0.424);
    
    private boolean unlocklvl2=false;
    private boolean unlocklvl3=false;
    private boolean unlocklvl4=false;
    
    public LevelMenu() {
        width=62;
        height=45;
        impImage();
        initButton();
    }
    
    private void impImage() {
       background = LoadSave.getImage(LoadSave.LEVEL_MENU);
       BufferedImage tempButton = LoadSave.getImage(LoadSave.LEVEL_BUTTON);
       buttons= new BufferedImage[2];
       buttons[0] = tempButton.getSubimage(0, 0, 42, 31);
       buttons[1] = tempButton.getSubimage(42, 0, 42, 31);
    }
    private void initButton(){
        lvl1 = new MyButton("", lvl1X, lvl1Y, width, height);
        lvl2 = new MyButton("", lvl2X, lvl2Y, width, height);
        lvl3 = new MyButton("", lvl3X, lvl3Y, width, height);
        lvl4 = new MyButton("", lvl4X, lvl4Y, width, height);
    }
    
    
    public void draw(Graphics g){
        g.drawImage(background, relativeposition.x, 0,3845, Game.currentScreenHeight, null);
        
        drawButton(g);  
    }
    public void drawButton(Graphics g){   
        g.setFont(new Font("Arial",Font.BOLD,28));
        g.setColor(Color.black);
        int w=(int)(42*1.5);
        int h=(int)(31*1.5);
        g.drawImage(buttons[1], lvl1.getX(), lvl1.getY(),w,h, null);
                  
        if(unlocklvl2)
            g.drawImage(buttons[1], lvl2.getX(), lvl2.getY(),w,h, null);       
        else
            g.drawImage(buttons[0], lvl2.getX(), lvl2.getY(),w,h, null);
        if(unlocklvl3)
            g.drawImage(buttons[1], lvl3.getX(), lvl3.getY(),w,h, null);
        else
            g.drawImage(buttons[0], lvl3.getX(), lvl3.getY(),w,h, null);
        if(unlocklvl4)
            g.drawImage(buttons[1], lvl4.getX(), lvl4.getY(),w,h, null);
        else
            g.drawImage(buttons[0], lvl4.getX(), lvl4.getY(),w,h, null);
        
        g.drawString("1", lvl1.getX()+width/2-8, lvl1.getY()+height/2+5);
        g.drawString("2", lvl2.getX()+width/2-8, lvl2.getY()+height/2+5);
        g.drawString("3", lvl3.getX()+width/2-8, lvl3.getY()+height/2+5);
        g.drawString("4", lvl4.getX()+width/2-8, lvl4.getY()+height/2+5);        
    }
    
    public void unlockLevel(String state){
        switch(state){
            case "level1":
                unlocklvl2=true;
                break;
            case "level2":
                unlocklvl3=true;
                break;
            case "level3":
                unlocklvl4=true;
                break;
        }
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
            
            lvl1.x=lvl1X;
            lvl1.bounds.x=lvl1X;
            
            lvl2.x=lvl2X;
            lvl2.bounds.x=lvl2X;
            
            lvl3.x=lvl3X;
            lvl3.bounds.x=lvl3X;
            
            lvl4.x=lvl4X;
            lvl4.bounds.x=lvl4X;
        }else{
            int temp=(int)(currentPt.getX()-prevPt.getX());
            lvl1.moveX(temp);
            lvl2.moveX(temp);
            lvl3.moveX(temp);
            lvl4.moveX(temp);
        }
        prevPt=currentPt;
    }
    public void mouseClicked(MouseEvent e) {
        if (lvl1.getBounds().contains(e.getX(), e.getY())) {
            setGameState(LVL1);
        }else if (lvl2.getBounds().contains(e.getX(), e.getY()) && unlocklvl2) {
            setGameState(LVL2);
        }else if (lvl3.getBounds().contains(e.getX(), e.getY()) && unlocklvl3) {
            System.exit(0);
        }else if (lvl4.getBounds().contains(e.getX(), e.getY()) && unlocklvl4) {
            System.exit(0);
        }
    }
}
