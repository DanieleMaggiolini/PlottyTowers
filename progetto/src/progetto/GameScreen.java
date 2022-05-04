/**
* @author  Daniele Maggiolini
* @author  Mattia Minotti
* @version 0.0
* @file GameScreen.java 
* 
* @brief file della classe JPanel GameScreen che gestisce tutte le print 
* che saranno eseguite nella schermata di gioco e gli imput da tastiera e mouse
*
*/
package progetto;

import inputs.KeyboardListener;
import inputs.MyMouseListener;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/** 
* @class GameScreen
* 
* @brief JFrame JPanel gestisce le print e i comandi input.
* 
*/ 

public class GameScreen extends JPanel{
    
    //oggeto del game
    private Game game;
    
    //dimensioni da applicare al panel
    private Dimension size;
    
   //oggetto lettura imput mouse
    private MyMouseListener mymouselistener;
    
    //oggetto lettura imput tastiera
    private KeyboardListener keyboardlistener;
    
    /**
     @brief costruttore setta il game e richiama il metodo per impostare la size.
     * 
     *@param game
     */
    public GameScreen(Game game){
        this.game = game;
        
        setPanelSize();
    }
    
    /**
     * @brief inizializza gli oggetti presenti nel file.
     */
    public void initImputs() {
        mymouselistener = new MyMouseListener(game);
        keyboardlistener = new KeyboardListener(game);

        addMouseListener(mymouselistener);
        addMouseMotionListener(mymouselistener);
        addKeyListener(keyboardlistener);

        requestFocus();
    }
    
    /**
     @brief imposta le dimensioni del panel.
     */
    private void setPanelSize() {
        size = new Dimension(Game.currentScreenWidth, Game.currentScreenHeight);//1792.1010
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }
    
    /**
     @brief stampa le componenti e richiama il render
     */
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        game.getRender().render(g);
        
    }
}