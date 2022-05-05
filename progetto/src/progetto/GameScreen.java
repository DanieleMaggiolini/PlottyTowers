
package progetto;

import inputs.KeyboardListener;
import inputs.MyMouseListener;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class GameScreen extends JPanel{
    
    private Game game;
   
    private Dimension size;
    
   
    private MyMouseListener mymouselistener;
    private KeyboardListener keyboardlistener;
    
    //costruttore (@param buffer di immagini)  
    public GameScreen(Game game){
        this.game = game;
        
        
        setPanelSize();
        
        
        
    }
    
    public void initImputs() {
        mymouselistener = new MyMouseListener(game);
        keyboardlistener = new KeyboardListener(game);

        addMouseListener(mymouselistener);
        addMouseMotionListener(mymouselistener);
        addKeyListener(keyboardlistener);

        requestFocus();
    }
    
    //metodo per impostale le dimensioni della schermata
    private void setPanelSize() {
        size = new Dimension(Game.currentScreenWidth, Game.currentScreenHeight);//1792.1010
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        game.getRender().render(g);
        
    }
    
    
    
    
}