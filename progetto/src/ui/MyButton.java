/**
 * @author  Daniele Maggiolini
 * @author Mattia Minotti
 * @version 0.0
 * @file MyButton.java
 *
 * @brief file per la gestione completa di ogni tipo di bottone
 *
 */
package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @class MyButton
 *
 * @brief gestisce i diversi tipi di bottone con i diversi costruttori
 * ed i diversi modi di stamparli
 *
 */
public class MyButton {
    
    //testo contenuto dal bottone
    private String text;
    
    //coordinata x della posizione
    protected int x;
    
    //coordinata y della posizione
    protected int y;
    
    //larghezza
    protected int width;
    
    //altezza
    protected int height; 
    
    //identificatore del bottone        
    public int id;
    
    //rettangolo per la definizione dei bordi ove è possibile cliccare
    public Rectangle bounds;
    
    //booleana che sara true quando il cursore si trovera sopra il bottone
    private boolean mouseover;
    
    //booleana che sara true quando avremo effettuato il click del bottone
    private boolean mousepressed;
    
    //font del testo
    private Font f;
    
    //colore
    private Color c;
    
    //pulsanti NORMALI
    public MyButton(String text, int x, int y, int width, int height){
       this.text=text;
       this.x=x;
       this.y=y;
       this.width=width;
       this.height=height;
       this.id=-1;
       
       initBounds();
    }
    
    //pulsanti NORMALI
    public MyButton(String text, int x, int y, int width, int height, int id){
       this.text=text;
       this.x=x;
       this.y=y;
       this.width=width;
       this.height=height;
       this.id=id;
       
       initBounds();
    }
    
    //pulsanti con font e colore
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
    public void drawAC(Graphics g){
        //SFONDO DEL BOTTONE
        drawBody(g);
        
        //BORDI DEL BOTTONE
        drawBorder(g);
        
        
        //TESTO NEL BOTTONE 
        drawCentralText2(g);      
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
    private void drawCentralText2(Graphics g){
        g.setFont(f);
        int w=g.getFontMetrics().stringWidth(text);
        int h=g.getFontMetrics().getHeight();
        g.setColor(c);       
        g.drawString(text,x + width/2 - w/2 , y + height - h/3);
    }
    
    private void drawBody(Graphics g) {
        if(mouseover)
           g.setColor(Color.GRAY);
        else
            g.setColor(Color.white);
        g.fillRect(x, y, width, height);
    }
    
    public void setColor(Color c){
        this.c=c;
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
