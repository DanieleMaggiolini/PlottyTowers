/**
 * @author  Daniele Maggiolini
 * @author Mattia Minotti
 * @version 1.0
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
    
    /**
     @brief crea il bottone basico.
     * 
     * @param text testo contenuto dal bottone
     * @param x coordinata
     * @param y coordinata
     * @param width larghezza
     * @param height altezza
     */
    public MyButton(String text, int x, int y, int width, int height){
       this.text=text;
       this.x=x;
       this.y=y;
       this.width=width;
       this.height=height;
       this.id=-1;
       
       initBounds();
    }
    
    /**
     @brief crea il bottone con un id.
     * 
     * @param text testo contenuto dal bottone
     * @param x coordinata
     * @param y coordinata
     * @param width larghezza
     * @param height altezza
     * @param id identificatore
     */
    public MyButton(String text, int x, int y, int width, int height, int id){
       this.text=text;
       this.x=x;
       this.y=y;
       this.width=width;
       this.height=height;
       this.id=id;
       
       initBounds();
    }
    
    /**
     @brief crea il bottone con font e colore.
     * 
     * @param text testo contenuto dal bottone
     * @param x coordinata
     * @param y coordinata
     * @param width larghezza
     * @param height altezza
     * @param f font
     * @param c colore
     */
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
    
    /**
     @brief crea il bottone con font, colore e immagine data dall'identificatore.
     * 
     * @param text testo contenuto dal bottone
     * @param x coordinata
     * @param y coordinata
     * @param width larghezza
     * @param height altezza
     * @param f font
     * @param c colore
     * @param id identificatore
     */
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
    
    /**
     @brief modifica la coordinata x del bottone.
     * 
     * @param x coordinata
     */
    public void moveX(int x){
        this.x+=x;
        initBounds();
    }
    
    /**
     @brief crea l'effettivo rettangolo del area del bottone.
     * 
     */
    private void initBounds(){
        this.bounds=new Rectangle(x, y ,width, height);
    }
    
    /**
     @brief getter del rettangolo per verificarne la pressione.
     * 
     * @return l'oggetto rettangolo del bottone
     */
    public Rectangle getBounds(){
        return bounds;
    }
    
    /**
     @brief richiama i metodi per disegnare il testo nel bottone.
     */
    public void draw(Graphics g){
        //TESTO NEL BOTTONE 
        drawText(g);      
    } 
    
    /**
     @brief richiama i metodi per disegnare il testo nel bottone e i bordi.
     */
    public void drawB(Graphics g){
        //BORDI DEL BOTTONE
        drawBorder(g);
        
        //TESTO NEL BOTTONE 
        drawText(g);      
    } 
    
    /**
     @brief richiama i metodi per disegnare il testo nel bottone, i bordi e lo sfondo.
     */
    public void drawA(Graphics g){
        //SFONDO DEL BOTTONE
        drawBody(g);
        
        //BORDI DEL BOTTONE
        drawBorder(g);
        
        
        //TESTO NEL BOTTONE 
        drawText(g);      
    } 
    
    /**
     @brief richiama i metodi per disegnare il testo centrato nel bottone, i bordi e lo sfondo.
     */
    public void drawAC(Graphics g){
        //SFONDO DEL BOTTONE
        drawBody(g);
        
        //BORDI DEL BOTTONE
        drawBorder(g);
        
        //TESTO NEL BOTTONE 
        drawCentralText2(g);      
    } 
    
    /**
     @brief richiama i metodi per disegnare il testo centrato nel bottone.
     */
    public void drawC(Graphics g){
        //TESTO CENTRALE NEL BOTTONE 
        drawCentralText(g);      
    }
    
    /**
     @brief disegna il testo nel bottone.
     */
    private void drawText(Graphics g) {
        g.setColor(c);       
        g.setFont(f);
        g.drawString(text,x , y + height);
    }
    
    /**
     @brief disegna il testo cenrato nel bottone.
     */
    private void drawCentralText(Graphics g){
        g.setFont(f);
        int w=g.getFontMetrics().stringWidth(text);
        int h=g.getFontMetrics().getHeight();
        g.setColor(c);       
        g.drawString(text,x + width/2 - w/2 , y + height - h/2);
    }
    
    /**
     @brief disegna il testo nel bottone ma più in alto rispetto a "drawCentralText".
     */
    private void drawCentralText2(Graphics g){
        g.setFont(f);
        int w=g.getFontMetrics().stringWidth(text);
        int h=g.getFontMetrics().getHeight();
        g.setColor(c);       
        g.drawString(text,x + width/2 - w/2 , y + height - h/3);
    }
    
    /**
     @brief disegna lo sfondo del bottone.
     */
    private void drawBody(Graphics g) {
        if(mouseover)
           g.setColor(Color.GRAY);
        else
            g.setColor(Color.white);
        g.fillRect(x, y, width, height);
    }
    
    /**
     @brief setter del colore.
     */
    public void setColor(Color c){
        this.c=c;
    }
    
    /**
     @brief setter della variabile per la verifica del mouse se si trova sul bottone.
     */
    public void setMouseOver(boolean mouseover){
        this.mouseover = mouseover;
    }
    
    /**
     @brief getter della variabile per la verifica del mouse se si trova sul bottone.
     * 
     * @return variabile mouseover
     */
    public boolean isMouseOver(){
        return mouseover;
    }
    
    /**
     @brief allunga il bottone in larghezza.
     */
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
    
    /**
     @brief disegna i bordi del bottone.
     */
    private void drawBorder(Graphics g) {
        g.setColor(Color.black);
        g.drawRect(x, y, width, height);
        if(mousepressed){
            g.drawRect(x + 1, y + 1, width - 2, height - 2);
            g.drawRect(x + 2, y + 2, width - 4, height - 4);
        }
        
    }
    
    /**
     @brief setter della variabile per la verifica del click del mouse se si trova sul bottone.
     */
    public void setMousePressed(boolean mousepressed){
        this.mousepressed = mousepressed;       
    }
    
    /**
     @brief setter della variabile per la verifica del click del mouse se si trova sul bottone.
     * 
     * @return variabile mousepressed
     */
    public boolean isMousePressed(){
        return mousepressed;
    }
    
    /**
     @brief resetta le variabili booleane.
     */
    public void resetBooleans(){
        mouseover = false;
        mousepressed = false;
        allunga(false);
    }
    
    /**
     @brief getter dell'identificatore.
     */
    public int getId(){
        return id;
    }
    
    /**
     @brief getter della coordinata x.
     */
    public int getX(){
        return x;
    }
    
    /**
     @brief getter della coordinata y.
     */
    public int getY(){
        return y;
    }
}
