package ui;


import helpz.LoadSave;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import progetto.Game;
import scenes.*;
import managers.*;
import towers.*;

public class ActionBar extends Bar{
    private Font f;
    private Color c;
    private Level1 level1;
    
    private MyButton[] towerButtons;
    private Tower selectedTower;
    
    public ActionBar(int x, int y, int width, int height, Level1 level1) {
        super(x,y,width,height);
        this.level1=level1;
        
        initButton(); 
    }
    private void initButton() {
        towerButtons = new MyButton[3];
        
        int[][] lvl = LoadSave.loadLevel("level1");
        int tempHeight=(int)(Game.currentScreenHeight*0.17);
        int w = Game.currentScreenWidth/lvl[0].length-10;
        int h= (Game.currentScreenHeight-tempHeight)/lvl.length-10;
        int x = 50;
        int y = (int)(Game.currentScreenHeight*0.89);
        int xOffset= (int)(Game.currentScreenWidth*0.05);
        
        for (int i = 0; i < towerButtons.length; i++) {
            towerButtons[i] = new MyButton("", x+xOffset*i, y, w, h, i);
        }
    }  
    public void draw(Graphics g){
        g.setColor(new Color(220,123,15));
        g.fillRect(x, y, width, height);
        
        drawButton(g);
    }
    public void drawButton(Graphics g) {
        
        for (MyButton b : towerButtons) {
            b.drawA(g);
            g.drawImage(level1.getTowerManager().getTowerImgs()[b.getId()][0], b.x, b.y, b.width, b.height, null);
        }
    }
    public void mouseClicked(MouseEvent e) {
        for(MyButton b : towerButtons){
            if(b.getBounds().contains(e.getX(), e.getY())){
                selectedTower = new Tower(0, 0, -1, b.getId());
                level1.setSelectedTower(selectedTower);
                return;
            }
        }
    }
    public void mouseMoved(MouseEvent e) {
        
    }   
    public void mousePressed(MouseEvent e) {
        
    }
    public void mouseReleased(MouseEvent e) {
        
    }
}