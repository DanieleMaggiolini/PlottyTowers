/**
 * @author  Daniele Maggiolini
 * @author Mattia Minotti
 * @version 0.0
 * @file Bar.java
 *
 * @brief barra per gestire le classi figlie con posizione (x, y) e dimensioni
 *
 */
package ui;

import scenes.Level1;

/**
 * @class Bar
 *
 * @brief classe padre delle altre barre
 *
 */
public class Bar {
    
    //coordinata x della posizione
    protected int x;
    
    //coordinata y della posizione
    protected int y;
    
    //larghezza
    protected int width;
    
    //altezza
    protected int height;
    
    /**
     @brief costruttore inizializza tutte le variabili.
     * 
     * @param x coordinata
     * @param y coordinata
     * @param width larghezza
     * @param height altezza
     */
    public Bar(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
}
