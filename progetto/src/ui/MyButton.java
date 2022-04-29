
package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MyButton {
    private String text;
    public int x, y, width, height, id;
    private Rectangle bounds;
    private boolean mouseover;
    private boolean mousepressed;
    private Font f;
    private Color c;
    
    //pulsanti NORMALI
    public MyButton(String text, int x, int y, int width, int height,Font f, Color c){
       this.text=text;
       this.x=x;
       this.y=y;
       this.width=width;
       this.height=height;
       this.id=-1;
       
       this.f=f;    
       this.c=c;
       initBounds();
    }
    //pulsanti CON L'IMMAGINE DELLA TILE 
    public MyButton(String text, int x, int y, int width, int height,Font f, Color c, int id){
       this.text=text;
       this.x=x;
       this.y=y;
       this.width=width;
       this.height=height;
       this.id=id;
       
       this.f=f;    
       this.c=c;
       
       initBounds();
    }
    public void moveX(int x){
        this.x+=x;
        initBounds();
    }
    private void initBounds(){
        this.bounds=new Rectangle(x, y ,width, height);
    }
    
    public Rectangle getBounds(){
        return bounds;
    }
    
    public void draw(Graphics g){
        //SFONDO DEL BOTTONE
        //drawBody(g);
        
        //BORDI DEL BOTTONE
        //drawBorder(g);
        
        
        //TESTO NEL BOTTONE 
        drawText(g);      
    } 
    public void drawB(Graphics g){
        //SFONDO DEL BOTTONE
        //drawBody(g);
        
        //BORDI DEL BOTTONE
        drawBorder(g);
        
        
        //TESTO NEL BOTTONE 
        drawText(g);      
    } 
    public void drawA(Graphics g){
        //SFONDO DEL BOTTONE
        drawBody(g);
        
        //BORDI DEL BOTTONE
        drawBorder(g);
        
        
        //TESTO NEL BOTTONE 
        drawText(g);      
    } 
    public void drawC(Graphics g){
        //SFONDO DEL BOTTONE
        //drawBody(g);
        
        //BORDI DEL BOTTONE
        //drawBorder(g);
        
        
        //TESTO NEL BOTTONE 
        drawCentralText(g);      
    }
    

    private void drawText(Graphics g) {
        g.setColor(c);       
        g.setFont(f);
        g.drawString(text,x , y + height);
    }
    
    private void drawCentralText(Graphics g){
        g.setFont(f);
        int w=g.getFontMetrics().stringWidth(text);
        int h=g.getFontMetrics().getHeight();
        g.setColor(c);       
        g.drawString(text,x + width/2 - w/2 , y + height - h/2);
    }
    
    private void drawBody(Graphics g) {
        if(mouseover)
           g.setColor(Color.GRAY);
        else
            g.setColor(Color.white);
        g.fillRect(x, y, width, height);
    }
    
    public void setMouseOver(boolean mouseover){
        this.mouseover = mouseover;
    }
    public boolean isMouseOver(){
        return mouseover;
    }
    private boolean passato=false;
    public void allunga(boolean allunga){
        if(allunga && passato==false){          
                width=width+20;
                height=height+4;
                x=x-10;
                passato=true;                     
        }else if(allunga==false && passato){         
                width=width-20;
                height=height-4;
                x=x+10;
                passato=false;            
        }
    }
    
    private void drawBorder(Graphics g) {
        g.setColor(Color.black);
        g.drawRect(x, y, width, height);
        if(mousepressed){
            g.drawRect(x + 1, y + 1, width - 2, height - 2);
            g.drawRect(x + 2, y + 2, width - 4, height - 4);
        }
        
    }
    public void setMousePressed(boolean mousepressed){
        this.mousepressed = mousepressed;       
    }
    public boolean isMousePressed(){
        return mousepressed;
    }
    public void resetBooleans(){
        mouseover = false;
        mousepressed = false;
        allunga(false);
    }
    
    public int getId(){
        return id;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
}
