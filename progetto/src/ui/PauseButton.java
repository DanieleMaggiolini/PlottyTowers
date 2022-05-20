/**
 * @author  Daniele Maggiolini
 * @author Mattia Minotti
 * @version 1.0
 * @file PauseButton.java
 *
 * @brief bottone per mettere in pausa le schermate di gioco e aprire l'overlayPause
 *
 */
package ui;

import java.awt.Rectangle;

/**
 * @class PauseButton
 *
 * @brief gestisce il bottone di pausa
 *
 */
public class PauseButton {
    
    //coordinata x della posizione
    protected int x;
    
    //coordinata y della posizione
    protected int y;
    
    //larghezza
    protected int width;
    
    //altezza
    protected int height; 
    
    //rettangolo per la definizione dei bordi ove è possibile cliccare
    protected Rectangle bounds;

    /**
     @brief costruttore del bottone.
     * 
     * setta le variabili
     * 
     * @param x coordinata
     * @param y coordinata
     * @param width larghezza
     * @param height altezza
     */
    public PauseButton(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        createBounds();
    }

    /**
     @brief crea il rettangolo.
     * 
     */
    private void createBounds() {
       bounds= new Rectangle(x,y,width,height);
    }

    /**
     @brief restituisce x
     * 
     * @return x coordinata
     */
    public int getX() {
        return x;
    }

    /**
     @brief restituisce y
     * 
     * @return y coordinata
     */
    public int getY() {
        return y;
    }

    /**
     @brief restituisce la width
     * 
     * @return width larghezza
     */
    public int getWidth() {
        return width;
    }

    /**
     @brief restituisce la height
     * 
     * @return height altezza
     */
    public int getHeight() {
        return height;
    }
    
    /**
     @brief setta x
     * 
     * @param x coordinata
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     @brief setta y
     * 
     * @param y coordinata
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     @brief setta width
     * 
     * @param width larghezza
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     @brief setta height
     * 
     * @param height altezza
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     @brief restituisce il bounds
     * 
     * @return bounds rettangolo
     */
    public Rectangle getBounds() {
        return bounds;
    }

    /**
     @brief setta bounds
     * 
     * @param bounds Rectangle
     */
    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }
}
