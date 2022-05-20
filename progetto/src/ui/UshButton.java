/**
 * @author  Daniele Maggiolini
 * @author Mattia Minotti
 * @version 0.0
 * @file UshButton.java
 *
 * @brief bottoni dell'pauseOverlay per restartare, riprendere ed uscire
 *
 */
package ui;

import helpz.LoadSave;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * @class UshButton
 *
 * @brief gestisce i bottoni adibiti all'resume, restart e quit presenti nella pauseOverlay.
 *
 */
public class UshButton extends PauseButton{
    
    //array di immagini dei bottoni
    private BufferedImage[] buttonImg; 
    
    //variabile se mouse è sopra il bottone
    private boolean mouseOver;
    
    //variabile se il mouse e press
    private boolean mousePressed;
    
    //variabile mutato
    private boolean muted;
    
    //variabile per alternare l'immagine del bottone se mutato o no
    private int rowIndex;
    
    /**
     @brief costruttore dei bottoni e carica l'immagine.
     
     * 
     * @param x coordinata
     * @param y coordinata
     * @param width larghezza
     * @param height altezza
     * @param rowIndex indice per l'array
     */
    public UshButton(int x, int y, int width, int height, int rowIndex) {
        super(x, y, width, height);
        this.rowIndex=rowIndex;
        loadImage();
    }          

    /**
     @brief carica l'immagine.
     * 
     */
    private void loadImage() {
       BufferedImage temp= LoadSave.getImage(LoadSave.MENU_BUTTONS);
       buttonImg = new BufferedImage[3];
       buttonImg[0]= temp.getSubimage(256*2, 0, 256, 256);
       buttonImg[1]= temp.getSubimage(256*3, 0, 256, 256);
       buttonImg[2]= temp.getSubimage(0, 256, 256*3, 256);
    }
    
    /**
     @brief aggiorna.
     * 
     */
    public void update(){      
    }
    
    /**
     @brief disegna l'immagine del bottone.
     * 
     * @param g oggetto della grafica
     */
    public void draw(Graphics g){
        g.drawImage(buttonImg[rowIndex], x, y, width, height, null);
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
     @brief resetta le variabili booleane.
     * 
     */
    public void resetBools(){
        mousePressed=false;
    }
}
