/**
* @author  Daniele Maggiolini
* @author  Mattia Minotti
* @version 0.0
* @file Editing.java 
* 
* @brief gestisce la schermata del menu.
*
*/
package scenes;

import helpz.LoadSave;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import progetto.Game;
import progetto.GameStates;
import ui.MyButton;
import static progetto.GameStates.*;

/**
 * @class Editing
 *
 * @brief gestice il menu: sfondo, bottoni.
 *
 */
public class Menu extends GameScene implements SceneMethods {
    //immagine dello sfondo
    private BufferedImage background;
    
    //bottone gioca
    private MyButton Bplaying;
    
    //bottone esci
    private MyButton Bquit;
    
    //font
    private Font f;
    
    //colore
    private Color c;
    
    /**
     @brief setta il game, inizializza i bottoni e importa le immagini
     * 
     * @param game oggetto del gioco
     */
    public Menu(Game game) {
        super(game);  
        initButtons();
        importImg();      
    }
    
    /**
     @brief inizializza i bottoni
     */
    private void initButtons() {

        int w = Game.currentScreenWidth/5;   //1792/4;
        int h = Game.currentScreenHeight/24;   //960/24;960/24;
        int x = (int)(Game.currentScreenWidth*0.025);   //;40;
        int y = Game.currentScreenHeight/3+ (int)(Game.currentScreenHeight*0.067);   //960/3+65;  
        int yOffset =(int)(Game.currentScreenHeight*0.072);    //70;
        f=new Font("Arial",Font.BOLD,h);
        c=new Color(255,255,255);
        Bplaying = new MyButton("GIOCA", x, y, w, h, f, c);
        Bquit = new MyButton("ESCI", x, y + yOffset, w, h, f, c);
    }
    
    /**
     @brief aggiorna
     */
    public void updates(){
        
    }
    
    /**
     @brief disegna lo sfondo e i bottoni
     * 
     * @param g oggetto della grafica
     */
    @Override
    public void render(Graphics g) {

        g.drawImage(background, 0, 0, Game.currentScreenWidth, Game.currentScreenHeight, null);
        drawButton(g);

    }

    /**
     @brief disegna i bottoni
     * 
     * @param game oggetto del gioco
     */
    private void drawButton(Graphics g) {
        Bplaying.draw(g);
        Bquit.draw(g);
    }

    /**
     @brief importa l'immagine dello sfondo.
     * 
     */
    private void importImg() {
        background= LoadSave.getImage(LoadSave.BACKGROUND_MENU);
    }

    /**
     @brief gestisce i click del mouse
     * 
     * @param e evento del mouse
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        if (Bplaying.getBounds().contains(e.getX(), e.getY())) {
            setGameState(PLAYING);
        }else if (Bquit.getBounds().contains(e.getX(), e.getY())) {
            System.exit(0);
        }
    }

    /**
     @brief gestisce le move del mouse
     * 
     * @param e evento del mouse
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        if (Bplaying.getBounds().contains(e.getX(), e.getY())) {
            Bplaying.setMouseOver(true);
            Bplaying.allunga(true);
            return;
        } else if (Bquit.getBounds().contains(e.getX(), e.getY())) {
            Bquit.setMouseOver(true);
            Bquit.allunga(true);
            return;
        }
        resetButtons();
    }
    
    /**
     @brief gestisce le press del mouse
     * 
     * @param e evento del mouse
     */
    @Override
    public void mousePressed(MouseEvent e) {
        if (Bplaying.getBounds().contains(e.getX(), e.getY())) {
            Bplaying.setMousePressed(true);
        }else if(Bquit.getBounds().contains(e.getX(), e.getY())){
            Bquit.setMousePressed(true);
        }
    }

    /**
     @brief gestisce le release del mouse
     * 
     * @param e evento del mouse
     */
    public void mouseReleased(MouseEvent e) {
        resetButtons();
    }

    /**
     @brief resetta i bottoni
     */
    public void resetButtons() {
        Bplaying.resetBooleans();
        Bquit.resetBooleans();
    }

    /**
     @brief gestisce le drag del mouse
     * 
     * @param e evento del mouse
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        
    }
}
