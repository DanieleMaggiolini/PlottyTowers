/**
 * @author  Daniele Maggiolini
 * @author Mattia Minotti
 * @version 0.0
 * @file SoundButton.java
 *
 * @brief bottone per mutare e smutare la musica
 *
 */
package ui;

import helpz.LoadSave;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * @class SoundButton
 *
 * @brief gestice il bottone che smuta e muta la musica
 *
 */
public class SoundButton extends PauseButton{
    
    //array di immagini
    private BufferedImage[] soundImg; 
    
    //variabile se mouse è sopra il bottone
    private boolean mouseOver;
    
    //variabile se il mouse e press
    private boolean mousePressed;
    
    //variabile mutato
    private boolean muted;
    
    //variabile per alternare l'immagine del bottone se mutato o no
    private int rowIndex;
    
    /**
     @brief costruttore del bottone e carica l'immagine.
     * 
     * setta le variabili
     * 
     * @param x coordinata
     * @param y coordinata
     * @param width larghezza
     * @param height altezza
     */
    public SoundButton(int x, int y, int width, int height) {
        super(x, y, width, height);
        loadImage();
    }          

    /**
     @brief carica le immagini
     */
    private void loadImage() {
       BufferedImage temp= LoadSave.getImage(LoadSave.MENU_BUTTONS);
       soundImg = new BufferedImage[2];
       soundImg[0]= temp.getSubimage(0, 0, 256, 256);
       soundImg[1]= temp.getSubimage(256, 0, 256, 256);
    }
    
    /**
     @brief aggiorna l'immagine del bottone se cambia lo stato da smutato a mutato e viceversa.
     * 
     */
    public void update(){
        if(muted)
            rowIndex=1;
        else
            rowIndex=0;      
    }
    
    /**
     @brief disegna il bottone
     * 
     * @param g oggetto della grafica
     */
    public void draw(Graphics g){
        g.drawImage(soundImg[rowIndex], x, y, width, height, null);
    }

    /**
     @brief getter della variabile per la verifica del mouse se si trova sul bottone.
     * 
     * @return booleana (true = sopra), (false = NON sopra)
     */
    public boolean isMouseOver() {
        return mouseOver;
    }

    /**
     @brief getter della variabile per la verifica del mouse se ha premuto il bottone.
     * 
     * @return booleana (true = pressed), (false = NON pressed)
     */
    public boolean isMousePressed() {
        return mousePressed;
    }

    /**
     @brief getter della variabile muted.
     * 
     * @return booleana (true = mutato), (false = NON mutato)
     */
    public boolean isMuted() {
        return muted;
    }
    
    /**
     @brief setter della variabile per la verifica del mouse se ha premuto il bottone.
     * 
     * @param mousePressed (mouse premuto = true)
     */
    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

    /**
     @brief setter della variabile per la verifica del mouse se ha premuto il bottone.
     * 
     * @param muted (mutato = true)
     */
    public void setMuted(boolean muted) {
        this.muted = muted;
    }
    
    /**
     @brief resetta le variabili booleane.
     * 
     */
    public void resetBools(){
        mousePressed=false;
    }
}
