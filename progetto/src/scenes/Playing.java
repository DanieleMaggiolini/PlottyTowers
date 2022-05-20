/**
* @author  Daniele Maggiolini
* @author  Mattia Minotti
* @version 1.0
* @file Playing.java 
* 
* @brief gestisce la schermata del menu dei livelli.
*
*/
package scenes;

import helpz.ImgFix;
import helpz.LoadSave;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import managers.TileManager;
import progetto.Game;
import static progetto.GameStates.PLAYING;
import static progetto.GameStates.MENU;
import static progetto.GameStates.setGameState;
import ui.ActionBar;
import ui.LevelMenu;
import ui.MyButton;
import ui.PauseOverlay;

/**
 * @class Playing
 *
 * @brief gestice il menu dei livelli: sfondo, bottoni.
 *
 */
public class Playing extends GameScene implements SceneMethods{
    
    //immagine casetta
    private BufferedImage home;
    
    //oggetto gestione livelli
    private LevelMenu levelmenu;
    
    //oggetto gestione mappe
    private TileManager tilemanager;
    
    //bottone home
    private MyButton Bmenu;
    
    //font
    private Font f;
    
    //colore
    private Color c;        
    
    /**
     @brief setta il game, inizializza le classi, inizializza i bottoni e importa le immagini
     * 
     * @param game oggetto del gioco
     */
    public Playing(Game game) {
        super(game);
 
        //il livello
        initClasses();
           
        impImage();   
        
        initButtons();        
    }  
    
    /**
     @brief inizializza le classi.
     * 
     */
    public void initClasses(){
        //TileManager
        tilemanager = new TileManager();
        levelmenu = new LevelMenu();
    }
    /**
     @brief importa le immagini.
     * 
     */
    private void impImage() {
        home= LoadSave.getImage(LoadSave.HOME);    
    }
    
    /**
     @brief inizializza i bottoni.
     * 
     */
    private void initButtons() {
        int w = 80;
        int h = 80;
        int x = 10;
        int y = 10;
        int yOffset = 100;
        f=new Font("Arial",Font.BOLD,40);
        c=new Color(255,0,0);
        Bmenu = new MyButton("", x, y, w, h, f, c);
    } 
    
    /**
     @brief aggiorna.
     * 
     */
    public void updates(){
        
    }
    
    /**
     @brief disegna levelmenu e bottone home
     * 
     * @param g oggetto della grafica
     */
    @Override
    public void render(Graphics g) {
        levelmenu.draw(g);
        
        g.drawImage(home, 10, 10, 80, 80, null);
        Bmenu.draw(g);      
    }
    
    /**
     @brief ritorna tilemanager
     * 
     * @return tilemanager
     */
    public TileManager getTileManager(){
        return tilemanager;
    }
    
    /**
     @brief gestisce i click del mouse
     * 
     * @param e evento del mouse
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        if (Bmenu.getBounds().contains(e.getX(), e.getY())) {
            setGameState(MENU);
        }
        levelmenu.mouseClicked(e);   
    }

    /**
     @brief gestisce le move del mouse
     * 
     * @param e evento del mouse
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        Bmenu.setMouseOver(false);
        if (Bmenu.getBounds().contains(e.getX(), e.getY())) {
            Bmenu.setMouseOver(true);
        }
    }

    /**
     @brief gestisce le press del mouse
     * 
     * @param e evento del mouse
     */
    @Override
    public void mousePressed(MouseEvent e) {
        levelmenu.mousePressed(e);
        if (Bmenu.getBounds().contains(e.getX(), e.getY())) {
            Bmenu.setMousePressed(true);
        }
    }

    /**
     @brief gestisce le release del mouse
     * 
     * @param e evento del mouse
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        resetButtons();
    }
    
    /**
     @brief gestisce le drag del mouse
     * 
     * @param e evento del mouse
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        levelmenu.mouseDragged(e);
    }
    
    /**
     @brief resetta il bottone
     */
    private void resetButtons() {
        Bmenu.resetBooleans();
    }
    
    /**
     @brief ritorna levelmenu
     * 
     * @return levelmenu
     */
    public LevelMenu getLevelMenu(){
        return levelmenu;
    }
}
