package ui;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import scenes.*;

public class ActionBar extends Bar{
    private Font f;
    private Color c;
    private Level1 level1;
    
    public ActionBar(int x, int y, int width, int height, Level1 level1) {
        super(x,y,width,height);
        this.level1=level1;
        
        initButton(); 
    }
    private void initButton() {
        
    }  
    public void draw(Graphics g){
        g.setColor(new Color(220,123,15));
        g.fillRect(x, y, width, height);
        
        drawButton(g);
    }
    public void drawButton(Graphics g) {
        
    }
    public void mouseClicked(MouseEvent e) {
        
    }
    public void mouseMoved(MouseEvent e) {
        
    }   
    public void mousePressed(MouseEvent e) {
        
    }
    public void mouseReleased(MouseEvent e) {
        
    }
}